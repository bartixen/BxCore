package pl.bartixen.bxcore.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import pl.bartixen.bxcore.Main;

public class BedSleep implements Listener {

    Main plugin;

    public BedSleep(Main m) {
        plugin = m;
    }

    @EventHandler
    public void onBed(PlayerBedEnterEvent e) {
        if (plugin.getConfig().getBoolean("oneplayersleep", true)) {
            Player p = e.getPlayer();
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                public void run() {
                    if (p.isSleeping()) {
                        p.getLocation().getWorld().setTime(1000L);
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.sendMessage("§7Gracz §9" + p.getDisplayName() + " §7poszedl spać");
                        }
                    }
                }
            }, 99);
        }
    }
}
