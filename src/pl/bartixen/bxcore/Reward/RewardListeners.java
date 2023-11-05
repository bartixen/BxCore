package pl.bartixen.bxcore.Reward;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import pl.bartixen.bxcore.Data.StatyDataManager;
import pl.bartixen.bxcore.Main;

import java.io.IOException;
import java.util.Arrays;

public class RewardListeners implements Listener {

    Main plugin;

    static StatyDataManager statyd;

    public RewardListeners(Main m) {
        plugin = m;
        statyd = StatyDataManager.getInstance();
    }

    @EventHandler
    public void onOpenMenu(InventoryClickEvent e) throws IOException {
        Player p = (Player) e.getWhoClicked();
        String uuid = String.valueOf(p.getUniqueId());

        int postawione = statyd.getData().getInt(uuid + ".places");
        int wykopane = statyd.getData().getInt(uuid + ".breaks");
        int diamenty = statyd.getData().getInt(uuid + ".diamonds");
        int swierci = statyd.getData().getInt(uuid + ".deaths");
        int czas = statyd.getData().getInt(uuid + ".times");
        int noce = statyd.getData().getInt(uuid + ".sleeps");
        int smoki = statyd.getData().getInt(uuid + ".dragons");

        if (e.getView().getTitle().startsWith("§9§lNagrody dla ciebie")) {
            if (e.getRawSlot() == -999) p.closeInventory();
            if (e.getRawSlot() < e.getInventory().getSize()) {
                e.setCancelled(true);
                if (e.getRawSlot() == 10) {
                    if (postawione >= 2500) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.place.first")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.RED_GLAZED_TERRACOTTA, 16);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.place.first", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 19) {
                    if (postawione >= 5000) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.place.second")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.CHEST, 6);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.place.second", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 28) {
                    if (postawione >= 10000) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.place.third")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.DIAMOND_AXE, 1);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.place.third", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 37) {
                    if (postawione >= 15000) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.place.fourth")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.OBSIDIAN, 13);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.place.fourth", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 46) {
                    if (postawione >= 25000) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.place.fifth")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.DIAMOND, 16);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.place.fifth", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }

                if (e.getRawSlot() == 11) {
                    if (wykopane >= 5000) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.break.first")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.ANVIL, 1);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.break.first", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 20) {
                    if (wykopane >= 15000) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.break.second")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE, 1);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.break.second", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 29) {
                    if (wykopane >= 25000) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.break.third")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
                            EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
                            meta.addStoredEnchant(Enchantment.MENDING, 1, false);
                            item.setItemMeta(meta);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.break.third", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 38) {
                    if (wykopane >= 50000) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.break.fourth")) {
                            p.closeInventory();
                            PotionEffect hasteEffect = new PotionEffect(PotionEffectType.FAST_DIGGING, 15 * 60 * 20, 1);
                            p.addPotionEffect(hasteEffect);
                            statyd.getData().set(uuid + ".reward.break.fourth", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została nadana graczowi");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 47) {
                    if (wykopane >= 100000) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.break.fifth")) {
                            p.closeInventory();
                            ItemStack itemStack = new ItemStack(Material.BOW);
                            ItemMeta itemMeta = itemStack.getItemMeta();
                            itemMeta.setDisplayName("§e§lAK-47");
                            itemMeta.setLore(Arrays.asList("§7[ID:NAGRODA]"));
                            itemStack.setItemMeta(itemMeta);
                            p.getInventory().addItem(itemStack);
                            statyd.getData().set(uuid + ".reward.break.fifth", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }

                if (e.getRawSlot() == 12) {
                    if (diamenty >= 16) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.diamond.first")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.DIAMOND_SWORD, 1);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.diamond.first", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 21) {
                    if (diamenty >= 32) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.diamond.second")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.ENDER_EYE, 3);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.diamond.second", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 30) {
                    if (diamenty >= 64) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.diamond.third")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.NETHERITE_INGOT, 1);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.diamond.third", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 39) {
                    if (diamenty >= 128) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.diamond.fourth")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.diamond.fourth", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 48) {
                    if (diamenty >= 256) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.diamond.fifth")) {
                            p.closeInventory();
                            ItemStack itemStack = new ItemStack(Material.NETHERITE_SWORD);
                            ItemMeta itemMeta = itemStack.getItemMeta();
                            itemMeta.setDisplayName("§e§lSkull Cutlass");
                            itemMeta.setLore(Arrays.asList("§7[ID:NAGRODA]"));
                            itemStack.setItemMeta(itemMeta);
                            p.getInventory().addItem(itemStack);
                            statyd.getData().set(uuid + ".reward.diamond.fifth", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }

                if (e.getRawSlot() == 13) {
                    if (swierci >= 10) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.death.first")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.SHIELD, 1);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.death.first", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 22) {
                    if (swierci >= 25) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.death.second")) {
                            p.closeInventory();
                            ItemStack item1 = new ItemStack(Material.LEATHER_BOOTS, 1);
                            p.getInventory().addItem(item1);
                            ItemStack item2 = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
                            p.getInventory().addItem(item2);
                            ItemStack item3 = new ItemStack(Material.LEATHER_LEGGINGS, 1);
                            p.getInventory().addItem(item3);
                            ItemStack item4 = new ItemStack(Material.LEATHER_HELMET, 1);
                            p.getInventory().addItem(item4);
                            statyd.getData().set(uuid + ".reward.death.second", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 31) {
                    if (swierci >= 50) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.death.third")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.BOW, 1);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.death.third", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 40) {
                    if (swierci >= 100) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.death.fourth")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.STONE_SWORD, 1);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.death.fourth", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została nadana graczowi");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 49) {
                    if (swierci >= 250) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.death.fifth")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
                            SkullMeta meta = (SkullMeta) item.getItemMeta();
                            meta.setOwningPlayer(p);
                            item.setItemMeta(meta);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.death.fifth", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }

                if (e.getRawSlot() == 14) {
                    if (czas >= 120) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.time.first")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.RED_BED, 1);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.time.first", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 23) {
                    if (czas >= 600) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.time.second")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.POTION, 1);
                            PotionMeta meta = (PotionMeta) item.getItemMeta();
                            meta.setBasePotionData(new PotionData(PotionType.SPEED, true, false));
                            item.setItemMeta(meta);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.time.second", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 32) {
                    if (czas >= 1500) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.time.third")) {
                            p.closeInventory();
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "pex ustaw " + p.getName() + " graczplus");
                            statyd.getData().set(uuid + ".reward.time.third", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 41) {
                    if (czas >= 3000) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.time.fourth")) {
                            p.closeInventory();
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "pex ustaw " + p.getName() + " graczelita");
                            statyd.getData().set(uuid + ".reward.time.fourth", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została nadana graczowi");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 50) {
                    if (czas >= 6000) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.time.fifth")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.OCELOT_SPAWN_EGG, 1);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.time.fifth", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }

                if (e.getRawSlot() == 15) {
                    if (noce >= 25) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.sleep.first")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.LANTERN, 32);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.sleep.first", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 24) {
                    if (noce >= 50) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.sleep.second")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.NAME_TAG, 1);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.sleep.second", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 33) {
                    if (noce >= 100) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.sleep.third")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.SADDLE, 1);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.sleep.third", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 42) {
                    if (noce >= 150) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.sleep.fourth")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.EXPERIENCE_BOTTLE, 32);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.sleep.fourth", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została nadana graczowi");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 51) {
                    if (noce >= 250) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.sleep.fifth")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.CREEPER_HEAD, 1);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.sleep.fifth", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }

                if (e.getRawSlot() == 16) {
                    if (smoki >= 2) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.dragon.first")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.dragon.first", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 25) {
                    if (smoki >= 5) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.dragon.second")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.SHULKER_BOX, 1);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.dragon.second", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 34) {
                    if (smoki >= 10) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.dragon.third")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.FIREWORK_ROCKET, 128);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.dragon.third", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 43) {
                    if (smoki >= 15) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.dragon.fourth")) {
                            p.closeInventory();
                            ItemStack item = new ItemStack(Material.DIAMOND_HORSE_ARMOR, 1);
                            p.getInventory().addItem(item);
                            statyd.getData().set(uuid + ".reward.dragon.fourth", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została nadana graczowi");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }
                if (e.getRawSlot() == 52) {
                    if (smoki >= 25) {
                        if (!statyd.getData().getBoolean(uuid + ".reward.dragon.fifth")) {
                            p.closeInventory();
                            ItemStack itemStack = new ItemStack(Material.ELYTRA);
                            ItemMeta itemMeta = itemStack.getItemMeta();
                            itemMeta.setDisplayName("§e§lEnderman Minecon");
                            itemMeta.setLore(Arrays.asList("§7[ID:NAGRODA]"));
                            itemStack.setItemMeta(itemMeta);
                            p.getInventory().addItem(itemStack);
                            statyd.getData().set(uuid + ".reward.dragon.fifth", true);
                            statyd.saveData();
                            p.sendMessage("§7Nagroda została dodana do ekwipunku");
                            p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 100);
                        }
                    }
                }

            }
        }
    }
}
