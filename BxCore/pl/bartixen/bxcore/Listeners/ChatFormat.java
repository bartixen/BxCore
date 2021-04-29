package pl.bartixen.bxcore.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.bartixen.bxcore.Main;

public class ChatFormat implements Listener {

    Main plugin;

    public ChatFormat(Main m) {
        plugin = m;
    }

    @EventHandler
    public void onPrefix(AsyncPlayerChatEvent e) {
        String original = e.getMessage();
        String formatted = ChatColor.translateAlternateColorCodes('&', original);
        Player p = e.getPlayer();

        if (p.hasPermission("wlasciciel")) {
            String msg = formatted;
            e.setFormat("§8§l[§c§l%s§8§l] §7-> §c" + formatted);
        } else {
            if (p.hasPermission("admin")) {
                String msg = formatted;
                e.setFormat("§8§l[§b§l%s§8§l] §7-> §b" + formatted);
            } else {
                if (p.hasPermission("pomocnik")) {
                    String msg = formatted;
                    e.setFormat("§8§l[§d§l%s§8§l] §7-> §d" + formatted);
                } else {
                    e.setFormat("§8§l[§9§l%s§8§l] §7-> §f%s");
                }
            }
        }
    }
}
