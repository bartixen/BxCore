package pl.bartixen.bxcore.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;
import pl.bartixen.bxcore.Main;

public class UnknownCommand implements Listener {

    Main plugin;

    public UnknownCommand(Main m) { plugin = m; }

    @EventHandler
    public void onHelp(PlayerCommandPreprocessEvent e) {
        if (plugin.getConfig().getBoolean("unknowncommand", true)) {
            String s = e.getMessage().split(" ")[0];
            HelpTopic t = Bukkit.getHelpMap().getHelpTopic(s);
            if (t == null) {
                Player p = (Player) e.getPlayer();
                p.sendMessage("§7Komenda nieznana. Sprawdź §9/pomoc");
                e.setCancelled(true);
            }
        }
    }
}
