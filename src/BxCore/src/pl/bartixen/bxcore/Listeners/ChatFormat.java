package pl.bartixen.bxcore.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.bartixen.bxcore.Data.UserDataManager;
import pl.bartixen.bxcore.Main;
import pl.bartixen.bxcore.Permission.PermissionConfig;

import java.util.UUID;

public class ChatFormat implements Listener {

    Main plugin;

    static UserDataManager userd;

    PermissionConfig permd;

    public ChatFormat(Main m) {
        plugin = m;
        userd = UserDataManager.getInstance();
        permd = PermissionConfig.getInstance();
    }

    @EventHandler
    public void onPrefix(AsyncPlayerChatEvent e) {
        String original = e.getMessage();
        String formatted = ChatColor.translateAlternateColorCodes('&', original);
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();

        String rank = permd.getData().getString("users." + uuid);

        if (plugin.getConfig().getString("chat.group-formats." + rank) != null) {
            String format = plugin.getConfig().getString("chat.group-formats." + rank).replace("%nick%", p.getName()).replace("%message%", original).replace("%formatted%", formatted).replace("&", "§");
            e.setFormat(format);

        }
    }
}
