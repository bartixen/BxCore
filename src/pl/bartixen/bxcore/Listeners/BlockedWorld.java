package pl.bartixen.bxcore.Listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import pl.bartixen.bxcore.Main;

import java.io.IOException;
import java.util.HashMap;

public class BlockedWorld implements Listener {

    HashMap<Player, Long> spam = new HashMap<>();

    Main plugin;

    public BlockedWorld(Main m) {
        plugin = m;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerTeleport(PlayerPortalEvent e) throws IOException {
        Player p = e.getPlayer();
        if (e.getTo().getWorld().equals(plugin.getServer().getWorld("world_nether"))) {
            if (plugin.getConfig().getBoolean("blockworld.nether.blocked")) {
                e.setCancelled(true);
                String time = plugin.getConfig().getString("blockworld.nether.message-date");
                p.sendTitle("§f§lNether jest zablokowany", "§f§lodblokuje sie: §9§l" + time, 5, 50, 5);
            }
        }
        if (e.getTo().getWorld().equals(plugin.getServer().getWorld("world_the_end"))) {
            if (plugin.getConfig().getBoolean("blockworld.end.blocked")) {
                String time = plugin.getConfig().getString("blockworld.end.message-date");
                e.setCancelled(true);
                if (spam.containsKey(p)) {
                    if (!(spam.get(p) > System.currentTimeMillis())) {
                        spam.put(p, System.currentTimeMillis() + 5 * 1000);
                        double x = plugin.getConfig().getDouble("spawn.x");
                        double y = plugin.getConfig().getDouble("spawn.y");
                        double z = plugin.getConfig().getDouble("spawn.z");
                        float yaw = plugin.getConfig().getInt("spawn.yaw");
                        float pitch = plugin.getConfig().getInt("spawn.pitch");
                        String world = plugin.getConfig().getString("spawn.world");
                        p.teleport(new Location(p.getServer().getWorld(world), x, y, z, yaw, pitch));
                        p.sendTitle("§f§lEnd jest zablokowany", "§f§lodblokuje sie: §9§l" + time, 5, 50, 5);
                    }
                } else {
                    spam.put(p, System.currentTimeMillis() + 5 * 1000);
                    double x = plugin.getConfig().getDouble("spawn.x");
                    double y = plugin.getConfig().getDouble("spawn.y");
                    double z = plugin.getConfig().getDouble("spawn.z");
                    float yaw = plugin.getConfig().getInt("spawn.yaw");
                    float pitch = plugin.getConfig().getInt("spawn.pitch");
                    String world = plugin.getConfig().getString("spawn.world");
                    p.teleport(new Location(p.getServer().getWorld(world), x, y, z, yaw, pitch));
                    p.sendTitle("§f§lEnd jest zablokowany", "§f§lodblokuje sie: §9§l" + time, 5, 50, 5);
                }
            }
        }
    }
}
