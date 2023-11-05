package pl.bartixen.bxcore.Home;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import pl.bartixen.bxcore.Data.HomeDataManager;
import pl.bartixen.bxcore.Main;
import pl.bartixen.bxcore.Utils.ItemBuilder;

import java.util.UUID;

public class HomeCommand implements CommandExecutor {

    static Main plugin;

    static HomeDataManager hd;

    public HomeCommand(Main m) {
        plugin = m;
        hd = HomeDataManager.getInstance();
        m.getCommand("home").setExecutor(this);
        m.getCommand("sethome").setExecutor(this);
        m.getCommand("delhome").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cTa komenda jest przeznaczona tylko dla graczy");
            return false;
        }
        Player p = (Player)sender;
        this.home(p);
        return false;
    }

    public static InventoryView home(Player p) {
        UUID uuid = p.getUniqueId();
        Inventory inventory = Bukkit.createInventory((InventoryHolder)p, 27, ("§9§lTwoje domy"));
        ItemBuilder slotbez = (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lBrak dostępu");
        inventory.setItem(11, slotbez.build());
        inventory.setItem(12, slotbez.build());
        inventory.setItem(13, slotbez.build());
        inventory.setItem(14, slotbez.build());
        inventory.setItem(15, slotbez.build());
        int everyone = plugin.getConfig().getInt("home.everyone");
        int free = plugin.getConfig().getInt("home.free");
        if (everyone >= 1) {
            if (free >= 1) {
                if(hd.getData().getConfigurationSection(uuid + ".homes.home1") !=null) {
                    String name = hd.getData().getString(uuid + ".homes.home1" + ".name");
                    ItemBuilder slot11 = (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lDom #1").addLore("§7§lNazwa: §f§l" + name);
                    inventory.setItem(11, slot11.build());
                } else {
                    ItemBuilder slot11 = (new ItemBuilder(Material.YELLOW_CONCRETE, 1)).setTitle("§e§lUstaw dom").addLore("§7Kliknij aby ustawić domu w obecnej lokalizacji");
                    inventory.setItem(11, slot11.build());
                }
            } else {
                if (p.hasPermission("bxcore.user.home") || p.isOp()) {
                    if(hd.getData().getConfigurationSection(uuid + ".homes.home1") !=null) {
                        String name = hd.getData().getString(uuid + ".homes.home1" + ".name");
                        ItemBuilder slot11 = (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lDom #1").addLore("§7§lNazwa: §f§l" + name);
                        inventory.setItem(11, slot11.build());
                    } else {
                        ItemBuilder slot11 = (new ItemBuilder(Material.YELLOW_CONCRETE, 1)).setTitle("§e§lUstaw dom").addLore("§7Kliknij aby ustawić domu w obecnej lokalizacji");
                        inventory.setItem(11, slot11.build());
                    }
                }
            }
        }
        if (everyone >= 2) {
            if (free >= 2) {
                if(hd.getData().getConfigurationSection(uuid + ".homes.home2") !=null) {
                    String name = hd.getData().getString(uuid + ".homes.home2" + ".name");
                    ItemBuilder slot12 = (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lDom #2").addLore("§7§lNazwa: §f§l" + name);
                    inventory.setItem(12, slot12.build());
                } else {
                    ItemBuilder slot12 = (new ItemBuilder(Material.YELLOW_CONCRETE, 1)).setTitle("§e§lUstaw dom").addLore("§7Kliknij aby ustawić domu w obecnej lokalizacji");
                    inventory.setItem(12, slot12.build());
                }
            } else {
                if (p.hasPermission("bxcore.user.home") || p.isOp()) {
                    if(hd.getData().getConfigurationSection(uuid + ".homes.home2") !=null) {
                        String name = hd.getData().getString(uuid + ".homes.home2" + ".name");
                        ItemBuilder slot12 = (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lDom #2").addLore("§7§lNazwa: §f§l" + name);
                        inventory.setItem(12, slot12.build());
                    } else {
                        ItemBuilder slot12 = (new ItemBuilder(Material.YELLOW_CONCRETE, 1)).setTitle("§e§lUstaw dom").addLore("§7Kliknij aby ustawić domu w obecnej lokalizacji");
                        inventory.setItem(12, slot12.build());
                    }
                }
            }
        }
        if (everyone >= 3) {
            if (free >= 3) {
                if(hd.getData().getConfigurationSection(uuid + ".homes.home3") !=null) {
                    String name = hd.getData().getString(uuid + ".homes.home3" + ".name");
                    ItemBuilder slot13 = (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lDom #3").addLore("§7§lNazwa: §f§l" + name);
                    inventory.setItem(13, slot13.build());
                } else {
                    ItemBuilder slot13 = (new ItemBuilder(Material.YELLOW_CONCRETE, 1)).setTitle("§e§lUstaw dom").addLore("§7Kliknij aby ustawić domu w obecnej lokalizacji");
                    inventory.setItem(13, slot13.build());
                }
            } else {
                if (p.hasPermission("bxcore.user.home") || p.isOp()) {
                    if(hd.getData().getConfigurationSection(uuid + ".homes.home3") !=null) {
                        String name = hd.getData().getString(uuid + ".homes.home3" + ".name");
                        ItemBuilder slot13 = (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lDom #1").addLore("§7§lNazwa: §f§l" + name);
                        inventory.setItem(13, slot13.build());
                    } else {
                        ItemBuilder slot13 = (new ItemBuilder(Material.YELLOW_CONCRETE, 1)).setTitle("§e§lUstaw dom").addLore("§7Kliknij aby ustawić domu w obecnej lokalizacji");
                        inventory.setItem(13, slot13.build());
                    }
                }
            }
        }
        if (everyone >= 4) {
            if (free >= 4) {
                if(hd.getData().getConfigurationSection(uuid + ".homes.home4") !=null) {
                    String name = hd.getData().getString(uuid + ".homes.home4" + ".name");
                    ItemBuilder slot14 = (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lDom #4").addLore("§7§lNazwa: §f§l" + name);
                    inventory.setItem(14, slot14.build());
                } else {
                    ItemBuilder slot14 = (new ItemBuilder(Material.YELLOW_CONCRETE, 1)).setTitle("§e§lUstaw dom").addLore("§7Kliknij aby ustawić domu w obecnej lokalizacji");
                    inventory.setItem(14, slot14.build());
                }
            } else {
                if (p.hasPermission("bxcore.user.home") || p.isOp()) {
                    if(hd.getData().getConfigurationSection(uuid + ".homes.home4") !=null) {
                        String name = hd.getData().getString(uuid + ".homes.home4" + ".name");
                        ItemBuilder slot14 = (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lDom #4").addLore("§7§lNazwa: §f§l" + name);
                        inventory.setItem(14, slot14.build());
                    } else {
                        ItemBuilder slot14 = (new ItemBuilder(Material.YELLOW_CONCRETE, 1)).setTitle("§e§lUstaw dom").addLore("§7Kliknij aby ustawić domu w obecnej lokalizacji");
                        inventory.setItem(14, slot14.build());
                    }
                }
            }
        }
        if (everyone == 5) {
            if (free == 5) {
                if(hd.getData().getConfigurationSection(uuid + ".homes.home5") !=null) {
                    String name = hd.getData().getString(uuid + ".homes.home5" + ".name");
                    ItemBuilder slot15 = (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lDom #5").addLore("§7§lNazwa: §f§l" + name);
                    inventory.setItem(15, slot15.build());
                } else {
                    ItemBuilder slot14 = (new ItemBuilder(Material.YELLOW_CONCRETE, 1)).setTitle("§e§lUstaw dom").addLore("§7Kliknij aby ustawić domu w obecnej lokalizacji");
                    inventory.setItem(14, slot14.build());
                }
            } else {
                if (p.hasPermission("bxcore.user.home") || p.isOp()) {
                    if(hd.getData().getConfigurationSection(uuid + ".homes.home5") !=null) {
                        String name = hd.getData().getString(uuid + ".homes.home5" + ".name");
                        ItemBuilder slot15 = (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lDom #4").addLore("§7§lNazwa: §f§l" + name);
                        inventory.setItem(15, slot15.build());
                    } else {
                        ItemBuilder slot15 = (new ItemBuilder(Material.YELLOW_CONCRETE, 1)).setTitle("§e§lUstaw dom").addLore("§7Kliknij aby ustawić domu w obecnej lokalizacji");
                        inventory.setItem(15, slot15.build());
                    }
                }
            }
        }
        ItemBuilder backguard = (new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, 1)).setTitle("§7");
        ItemBuilder slot26 = (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lUsuń wybrany dom");
        ItemBuilder slot18 = (new ItemBuilder(Material.LIGHT_BLUE_CONCRETE, 1)).setTitle("§b§lUstaw nazwe domu");
        inventory.setItem(18, slot18.build());
        inventory.setItem(26, slot26.build());
        inventory.setItem(0, backguard.build());
        inventory.setItem(1, backguard.build());
        inventory.setItem(2, backguard.build());
        inventory.setItem(3, backguard.build());
        inventory.setItem(4, backguard.build());
        inventory.setItem(5, backguard.build());
        inventory.setItem(6, backguard.build());
        inventory.setItem(7, backguard.build());
        inventory.setItem(8, backguard.build());
        inventory.setItem(9, backguard.build());
        inventory.setItem(10, backguard.build());
        inventory.setItem(16, backguard.build());
        inventory.setItem(17, backguard.build());
        inventory.setItem(19, backguard.build());
        inventory.setItem(20, backguard.build());
        inventory.setItem(21, backguard.build());
        inventory.setItem(22, backguard.build());
        inventory.setItem(23, backguard.build());
        inventory.setItem(24, backguard.build());
        inventory.setItem(25, backguard.build());
        return p.openInventory(inventory);
    }
}

