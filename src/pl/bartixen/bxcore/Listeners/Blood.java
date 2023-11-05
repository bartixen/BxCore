package pl.bartixen.bxcore.Listeners;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import pl.bartixen.bxcore.Main;

public class Blood implements Listener {

    Main plugin;

    public Blood(Main m) {
        plugin = m;
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (plugin.getConfig().getBoolean("blood", true)) {
            Entity damager = event.getDamager();
            if (damager instanceof Player) {
                Player player = (Player) damager;
                Entity entity = event.getEntity();
                entity.getLocation().getWorld().playEffect(entity.getLocation().add(0.0D, 0.5D, 0.0D),
                        Effect.STEP_SOUND,
                        Material.REDSTONE_BLOCK);
            }
        }
    }

    @EventHandler
    public void onDeathEvent(EntityDeathEvent event) {
        if (plugin.getConfig().getBoolean("blood", true)) {
            Entity entity = event.getEntity();
            entity.getLocation().getWorld().playEffect(entity.getLocation().add(0.0D, 0.5D, 0.0D),
                    Effect.STEP_SOUND,
                    Material.REDSTONE_BLOCK);
        }
    }
}
