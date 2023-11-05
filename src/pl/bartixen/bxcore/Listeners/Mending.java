package pl.bartixen.bxcore.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemMendEvent;
import pl.bartixen.bxcore.Main;

import java.util.Random;

public class Mending implements Listener {

    Main plugin;

    public Mending(Main m) {
        plugin = m;
    }

    @EventHandler(priority= EventPriority.HIGHEST)
    public void mending(PlayerItemMendEvent e) {
        if (plugin.getConfig().getBoolean("mending.works")) {
            int liczba = plugin.getConfig().getInt("mending.count");
            Random rand = new Random();
            int n = rand.nextInt(liczba);
            if (n >= 3) {
                e.setCancelled(true);
            }
        }
    }
}
