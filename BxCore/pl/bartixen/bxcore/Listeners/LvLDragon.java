package pl.bartixen.bxcore.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import pl.bartixen.bxcore.Main;

import java.io.IOException;
import java.util.Random;

public class LvLDragon implements Listener {

    Main plugin;

    public LvLDragon(Main m) {
        plugin = m;
    }

    @EventHandler
    public void DragonSpawn(EntitySpawnEvent event) throws IOException {
        if (plugin.getConfig().getBoolean("end.dragon-lvl.dzialanie")) {
            if (event.getEntity() instanceof org.bukkit.entity.EnderDragon) {
                Entity dragon = event.getEntity();
                if (plugin.getConfig().getString("end.dragon-lvl.lvl") == null) {
                    plugin.getConfig().set("end.dragon-lvl.lvl", 1);
                    plugin.saveConfig();
                }
                int poziom = plugin.getConfig().getInt("end.dragon-lvl.lvl");
                int lvl = poziom * 10 + 200;
                ((Attributable) dragon).getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(lvl);
                dragon.setCustomName("§9§lEnder Dragon §8§l(§7§lLVL §f§l" + poziom + "§8§l)");
                ((Damageable) dragon).setHealth(lvl);
                int usawtpoziom = poziom + 1;
                plugin.getConfig().set("end.dragon-lvl.lvl", usawtpoziom);
                plugin.saveConfig();
            }
        }
    }

    @EventHandler
    public void DragonDeath(EntityDeathEvent event) throws IOException {
        if (plugin.getConfig().getBoolean("end.dragon-elytra")) {
            if (event.getEntityType().equals(EntityType.ENDER_DRAGON)) {
                Random rand = new Random();
                int n = rand.nextInt(2);
                if (n == 1) {
                    event.getEntity().getKiller().getInventory().addItem(new ItemStack[]{new ItemStack(Material.ELYTRA)});
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.sendMessage("§fGracz §9" + event.getEntity().getKiller().getDisplayName() + " §fzabil smoka §7(przez co zdobyl elytre)");
                    }
                } else {
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.sendMessage("§fGracz §9" + event.getEntity().getKiller().getDisplayName() + " §fzabil smoka §7(niestety elytra nie wypadla)");
                    }
                }
            }
        }
    }

    @EventHandler
    public void OnPlace(BlockPlaceEvent e) throws IOException {
        if (plugin.getConfig().getBoolean("end.blocked-bed")) {
            Player p = e.getPlayer();
            Material blok = e.getBlock().getType();
            World end = p.getServer().getWorld("world_the_end");
            if ((blok == Material.WHITE_BED || blok == Material.ORANGE_BED || blok == Material.MAGENTA_BED || blok == Material.LIGHT_BLUE_BED || blok == Material.YELLOW_BED || blok == Material.LIME_BED || blok == Material.PINK_BED || blok == Material.GRAY_BED || blok == Material.LIGHT_GRAY_BED || blok == Material.CYAN_BED || blok == Material.PURPLE_BED || blok == Material.BLUE_BED || blok == Material.BROWN_BED || blok == Material.GREEN_BED || blok == Material.RED_BED || blok == Material.BLACK_BED) && e.getBlock().getWorld() == end) {
                p.sendMessage("§cStawianie §eBED§c w§e world_the_end§c jest zabronione");
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntityExplodetnt(EntityExplodeEvent e) {
        if (plugin.getConfig().getBoolean("end.blocked-tnt")) {
            World end = this.plugin.getServer().getWorld("world_the_end");
            if (e.getEntityType() == EntityType.PRIMED_TNT && e.getEntity().getWorld() == end) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (plugin.getConfig().getBoolean("end.blocked-damage-entity")) {
            World end = this.plugin.getServer().getWorld("world_the_end");
            if (e.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION && e.getEntity().getWorld() == end) {
                e.setCancelled(true);
            }
        }
    }
}
