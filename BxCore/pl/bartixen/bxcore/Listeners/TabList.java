package pl.bartixen.bxcore.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.bartixen.bxcore.Main;

public class TabList implements Listener {

    Main plugin;

    public TabList(Main m) {
        plugin = m;
    }

    @EventHandler
    public void Tablist(PlayerJoinEvent e) {

    }

}
