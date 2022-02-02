package pl.bartixen.bxcore.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import pl.bartixen.bxcore.Data.UserDataManager;
import pl.bartixen.bxcore.Main;
import pl.bartixen.bxcore.Permission.PermissionConfig;

import java.util.ArrayList;
import java.util.UUID;

public class TabList implements Listener {

    static Main plugin;

    static UserDataManager userd;

    static PermissionConfig permd;

    public TabList(Main m) {
        plugin = m;
        userd = UserDataManager.getInstance();
        permd = PermissionConfig.getInstance();
    }

    //W RefreshAction.java jest kod odswiezania tabu

    @EventHandler
    public void Tablist(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        RefreshPlayerName(p, TeamAction.CREATE);
    }

    public static void RefreshPlayerName(Player p, TeamAction action) {
        Team team;
        Scoreboard scoreboard;

        UUID uuid = p.getUniqueId();

        String rank = permd.getData().getString("users." + uuid);


        String prefix = permd.getData().getString("groups." + rank + ".prefix").replace("&", "§") + " ";
        String suffix = "";

        if (userd.getData().getBoolean(p.getName() + ".pvp")) {
            suffix = suffix + " §c☠";
        }

        if (plugin.invisible.contains(p)) {
            suffix = suffix + " §8[§a✪§8]";
        }

        if (p.getScoreboard() == null) {
            return;
        }

        scoreboard = p.getScoreboard();

        if (scoreboard.getTeam(p.getName()) == null) {
            scoreboard.registerNewTeam(p.getName());
        }

        team = scoreboard.getTeam(p.getName());
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);

        switch (action) {
            case CREATE:
                team.addPlayer(p);
                break;
            case UPDATE:
                team.unregister();
                scoreboard.registerNewTeam(p.getName());
                team = scoreboard.getTeam(p.getName());
                team.setPrefix(prefix);
                team.setSuffix(suffix);
                team.setNameTagVisibility(NameTagVisibility.ALWAYS);
                team.addPlayer(p);
                break;
            case DESTROY:
                team.unregister();
                break;
        }
    }

    public enum TeamAction {
        CREATE, DESTROY, UPDATE
    }

}
