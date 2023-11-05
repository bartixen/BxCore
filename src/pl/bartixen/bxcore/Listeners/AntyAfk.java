package pl.bartixen.bxcore.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import pl.bartixen.bxcore.Data.AntyLogutDataManager;
import pl.bartixen.bxcore.Main;

import java.util.HashMap;
import java.util.Map;

public class AntyAfk implements Listener {

    Main plugin;

    public AntyAfk(Main m) {
        plugin = m;
    }

    Map<Player, Integer> afkTimers = new HashMap<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        resetTimer(player);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Bukkit.getScheduler().cancelTask(afkTimers.get(player));
        resetTimer(player);
    }

    public void resetTimer(final Player p) {
        String name = plugin.getConfig().getString("name");
        int time = plugin.getConfig().getInt("antyspam.time");
        afkTimers.put(p, Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getServer().getPluginManager().getPlugin("BxCore"), new Runnable() {
            @Override
            public void run() {
                if (Bukkit.getPlayer(p.getName()) != null) {
                    if (plugin.getConfig().getBoolean("antyafk.works", true)) {
                        p.kickPlayer("\n§8• — • — • — • §9§lKICK §8• — • — • — •\n\n§7Powód: §9AFK (5 min)\n§7Administrator: §9CONSOLE\n\n§8• — • — • — • §f§l" + name + " §8• — • — • — •\n");
                    }
                }
            }
        }, (long) time * 60 * 20));
    }
}
