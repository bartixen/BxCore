package pl.bartixen.bxcore.Permission;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;
import pl.bartixen.bxcore.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;

public class PermAddition implements Listener {

    Main plugin;

    PermissionConfig permd;

    public PermAddition(Main m) {
        plugin = m;
        permd = PermissionConfig.getInstance();
    }

    @EventHandler
    public void JoinPlayer(PlayerJoinEvent e) throws IOException {
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        if (permd.getData().getString("users." + uuid) != null) {
            String rank = permd.getData().getString("users." + uuid);
            setPermission(p, rank);
        } else {
            if (permd.getData().getString("expectancy." + p.getDisplayName()) != null) {
                permd.getData().set("users." + uuid, permd.getData().getString("expectancy." + p.getDisplayName()));
                permd.getData().set("expectancy." + p.getDisplayName(), null);
                permd.saveData();
                setPermission(p, permd.getData().getString("expectancy." + p.getDisplayName()));
            } else {
                String defaultRank = permd.getData().getString("default");
                permd.getData().set("users." + uuid, defaultRank);
                permd.saveData();
                setPermission(p, defaultRank);
            }
        }
    }

    public void setPermission(Player p, String rank) {
        ArrayList<String> listPermission = new ArrayList<>(permd.getData().getStringList("groups." + rank + ".permissions"));
        for (String permission : listPermission) {
            PermissionAttachment at = p.addAttachment(plugin);
            at.setPermission(permission, true);
        }
    }

}
