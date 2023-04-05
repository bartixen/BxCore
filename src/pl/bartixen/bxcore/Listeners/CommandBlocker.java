package pl.bartixen.bxcore.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import pl.bartixen.bxcore.Main;

import java.util.List;

public class CommandBlocker implements Listener {

    Main plugin;

    public CommandBlocker(Main m) {
        plugin = m;
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        if (!(p.hasPermission("*") || p.isOp())) {
            List<String> wiadomosci = plugin.getConfig().getStringList("commandblock");
            String polecenie = e.getMessage();
            for (String blacklist : wiadomosci) {
                if ((polecenie.startsWith("/" + blacklist.toLowerCase() + " ")) || (polecenie.equals("/" + blacklist.toLowerCase()))) {
                    e.setCancelled(true);
                    p.sendMessage("§7Komenda nieznana. Sprawdź §9/pomoc");
                    break;
                }
            }
        }
    }
}
