package pl.bartixen.bxcore.Staty;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import pl.bartixen.bxcore.Data.StatyDataManager;
import pl.bartixen.bxcore.Main;
import pl.bartixen.bxcore.Reward.RewardCommand;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class StatyListeners implements Listener {

    Main plugin;

    static StatyDataManager statyd;

    public StatyListeners(Main m) {
        plugin = m;
        statyd = StatyDataManager.getInstance();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws IOException {
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        String nick = p.getName();
        if (statyd.getData().getConfigurationSection(String.valueOf(uuid)) == null) {
            statyd.getData().set(uuid + ".nick", nick);
            statyd.saveData();
        }

        if (statyd.getData().getConfigurationSection("list") == null) {
            statyd.getData().createSection("list");
            statyd.saveData();
        }

        String uuidString = p.getUniqueId().toString();
        FileConfiguration config = statyd.getData();

        if (config.contains("list")) {
            List<String> lista = config.getStringList("list.uuid");
            if (!lista.contains(uuidString)) {
                lista.add(uuidString);
                config.set("list.uuid", lista);
                statyd.saveData();
            }
        }

    }

    @EventHandler
    public void onOpenMenu(InventoryClickEvent e) throws IOException {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().startsWith("§9§lStatystyki gracza")) {
            if (e.getRawSlot() == -999) p.closeInventory();
            if (e.getRawSlot() < e.getInventory().getSize()) {
                e.setCancelled(true);
                if (e.getRawSlot() == 28) {
                    p.closeInventory();
                    RewardCommand.reward(String.valueOf(p.getUniqueId()), p);
                }
                if (e.getRawSlot() == 29) {
                    p.closeInventory();
                    RewardCommand.reward(String.valueOf(p.getUniqueId()), p);
                }
                if (e.getRawSlot() == 30) {
                    p.closeInventory();
                    RewardCommand.reward(String.valueOf(p.getUniqueId()), p);
                }
                if (e.getRawSlot() == 31) {
                    p.closeInventory();
                    RewardCommand.reward(String.valueOf(p.getUniqueId()), p);
                }
                if (e.getRawSlot() == 32) {
                    p.closeInventory();
                    RewardCommand.reward(String.valueOf(p.getUniqueId()), p);
                }
                if (e.getRawSlot() == 33) {
                    p.closeInventory();
                    RewardCommand.reward(String.valueOf(p.getUniqueId()), p);
                }
                if (e.getRawSlot() == 34) {
                    p.closeInventory();
                    RewardCommand.reward(String.valueOf(p.getUniqueId()), p);
                }

            }
        }
    }

    @EventHandler
    public void OnPlace(BlockPlaceEvent e) throws IOException {
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();

        statyd.getData().set(uuid + ".places", statyd.getData().getInt(uuid + ".places") +1 );
        statyd.saveData();
    }

    @EventHandler
    public void OnBreak(BlockBreakEvent e) throws IOException {
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();

        statyd.getData().set(uuid + ".breaks", statyd.getData().getInt(uuid + ".breaks") +1 );
        statyd.saveData();

        ItemStack item = p.getInventory().getItemInMainHand();

        List<Material> pickaxes = Arrays.asList(
                Material.WOODEN_PICKAXE,
                Material.STONE_PICKAXE,
                Material.IRON_PICKAXE,
                Material.GOLDEN_PICKAXE,
                Material.DIAMOND_PICKAXE,
                Material.NETHERITE_PICKAXE
        );

        if (e.getBlock().getType().equals(Material.DIAMOND_ORE) || e.getBlock().getType().equals(Material.DEEPSLATE_DIAMOND_ORE)) {
            if (pickaxes.contains(item.getType())) {
                if (!item.containsEnchantment(Enchantment.SILK_TOUCH)) {
                    statyd.getData().set(uuid + ".diamonds", statyd.getData().getInt(uuid + ".diamonds") +1 );
                    statyd.saveData();
                }
            }
        }
    }

    @EventHandler
    public void OnDeath(PlayerDeathEvent e) throws IOException {
        Player p = e.getEntity();
        UUID uuid = p.getUniqueId();

        statyd.getData().set(uuid + ".deaths", statyd.getData().getInt(uuid + ".deaths") +1 );
        statyd.saveData();
    }

    @EventHandler
    public void OnBed(PlayerBedEnterEvent e) {
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                if (p.isSleeping()) {
                    statyd.getData().set(uuid + ".sleeps", statyd.getData().getInt(uuid + ".sleeps") +1 );
                    try {
                        statyd.saveData();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        }, 99);
    }

    @EventHandler
    public void OnDragon(EntityDeathEvent e) throws IOException {
        if(e.getEntity() instanceof EnderDragon) {
            LivingEntity Dragon = e.getEntity();
            Player p = Dragon.getKiller();
            UUID uuid = p.getUniqueId();

            statyd.getData().set(uuid + ".dragons", statyd.getData().getInt(uuid + ".dragons") +1 );
            statyd.saveData();
        }
    }
}
