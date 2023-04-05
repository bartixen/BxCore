package pl.bartixen.bxcore.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.bartixen.bxcore.Main;

public class VanishEvents implements Listener {

    Main plugin;

    public VanishEvents(Main m) {
        plugin = m;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        for (int i = 0; i < plugin.invisible.size(); i++) {
            if (!(p.hasPermission("bxcore.commands.vanish"))) {
                p.hidePlayer(plugin.invisible.get(i));
            }
        }
    }
}
