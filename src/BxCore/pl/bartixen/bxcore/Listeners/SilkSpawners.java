package pl.bartixen.bxcore.Listeners;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.bartixen.bxcore.Main;

public class SilkSpawners implements Listener {

    Main plugin;

    public SilkSpawners(Main m) {
        plugin = m;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        World world = e.getPlayer().getServer().getWorld("world");
        if (e.getPlayer().getLocation().getWorld() == world) {
            if (e.getBlock().getType() == Material.SPAWNER && plugin.getConfig().getBoolean("silkspawners", true)) {
                ItemStack tool = e.getPlayer().getInventory().getItemInMainHand();
                if (tool.getType().toString().contains("PICKAXE") && tool.containsEnchantment(Enchantment.SILK_TOUCH)) {
                    ItemStack spawnerItem = new ItemStack(Material.SPAWNER);
                    ItemMeta spawnerMeta = spawnerItem.getItemMeta();

                    CreatureSpawner spawner = (CreatureSpawner) e.getBlock().getState();
                    EntityType entityType = spawner.getSpawnedType();

                    spawnerMeta.setDisplayName(entityType.toString() + " Spawner");
                    spawnerItem.setItemMeta(spawnerMeta);

                    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), spawnerItem);
                }
            }
        } else {
            if (e.getBlock().getType() == Material.SPAWNER) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (e.getItemInHand().getType() == Material.SPAWNER && e.getItemInHand().hasItemMeta() && plugin.getConfig().getBoolean("silkspawners", true)) {
            ItemMeta spawnerMeta = e.getItemInHand().getItemMeta();
            String name = spawnerMeta.getDisplayName();
            int entityTypeLenght = name.length() - 8;
            String entityTypeString = name.substring(0, entityTypeLenght);
            EntityType entityType = EntityType.valueOf(entityTypeString);
            Block spawnerBlock = e.getBlock();
            CreatureSpawner spawner = (CreatureSpawner) spawnerBlock.getState();
            spawner.setSpawnedType(entityType);
            spawner.update();
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.SPAWNER) {
            event.setExpToDrop(0);
        }
    }

    @EventHandler
    public void onAnvil(InventoryClickEvent e) {
        if (e.getCurrentItem() == null) {
            return;
        }
        if (e.getCurrentItem().getType() == Material.AIR) {
            return;
        }
        if (e.getInventory().getType() == InventoryType.ANVIL) {
            if(e.getSlotType() == InventoryType.SlotType.RESULT) {
                if (e.getCurrentItem().getType() == Material.SPAWNER) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
