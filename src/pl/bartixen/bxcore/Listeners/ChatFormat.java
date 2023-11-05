package pl.bartixen.bxcore.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.bartixen.bxcore.Data.UserDataManager;
import pl.bartixen.bxcore.Main;
import pl.bartixen.bxcore.Permission.PermissionConfig;
import pl.bartixen.bxteam.Data.DataManager;

import java.util.UUID;

public class ChatFormat implements Listener {

    Main plugin;

    static UserDataManager userd;

    PermissionConfig permd;

    static DataManager teamd;

    public ChatFormat(Main m) {
        plugin = m;
        userd = UserDataManager.getInstance();
        permd = PermissionConfig.getInstance();
        teamd = DataManager.getInstance();
    }

    @EventHandler
    public void onPrefix(AsyncPlayerChatEvent e) {
        String original = e.getMessage();
        String formatted = ChatColor.translateAlternateColorCodes('&', original);
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        String team = "";

        String rank = permd.getData().getString("users." + uuid);
        if (teamd.getData().getString(String.valueOf(p.getUniqueId()) + ".player") != null) {
            team = teamd.getData().getString((String.valueOf(p.getUniqueId()) + ".player"));
            team = " " + team;
        }

        if (teamd.getData().getString(String.valueOf(p.getUniqueId()) + ".team_leader") != null) {
            team = teamd.getData().getString((String.valueOf(p.getUniqueId()) + ".team_leader"));
            team = " " + team;
        }

        if (plugin.getConfig().getString("chat.group-formats." + rank) != null) {
            String format = plugin.getConfig().getString("chat.group-formats." + rank).replace("%nick%", p.getName()).replace("%formatted%", formatted).replace("%team%", team.toUpperCase()).replace("&", "§").replace("%message%", original);
            e.setFormat(format);

        }
    }
}
