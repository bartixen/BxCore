package pl.bartixen.bxcore.Home;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import pl.bartixen.bxcore.Data.HomeDataManager;
import pl.bartixen.bxcore.Main;
import pl.bartixen.bxcore.Utils.ItemBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;

public class HomeListener implements Listener {

    Main plugin;

    static HomeDataManager hd;

    public HomeListener(Main m) {
        hd = HomeDataManager.getInstance();
        plugin = m;
    }

    @EventHandler
    public void onAnvil(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getCurrentItem() == null) {
            return;
        }
        if (e.getCurrentItem().getType() == Material.AIR) {
            return;
        }
        if (e.getInventory().getType() == InventoryType.ANVIL) {
            if(e.getSlotType() == InventoryType.SlotType.RESULT) {
                if (e.getCurrentItem().getType().equals(Material.DIAMOND)) {
                    e.setCancelled(true);
                    player.closeInventory();
                    player.kickPlayer("§7Ta czynność jest zablokowana");
                }
            }
        }
    }

    public static int removeItems(Inventory inventory, Material type, int amount) {
        if(type == null || inventory == null)
            return -1;
        if (amount <= 0)
            return -1;
        if (amount == Integer.MAX_VALUE) {
            inventory.remove(type);
            return 0;
        }
        HashMap<Integer,ItemStack> retVal = inventory.removeItem(new ItemStack(type,amount));

        int notRemoved = 0;
        for(ItemStack item: retVal.values()) {
            notRemoved+=item.getAmount();
        }
        return notRemoved;
    }

    public void teleport(Player p, int jakihome) throws IOException {
        int ilehome = plugin.getConfig().getInt("home.wszystkich");
        int iledarmowych = plugin.getConfig().getInt("home.darmowych");
        UUID uuid = p.getUniqueId();
        if (ilehome >= jakihome) {
            if (iledarmowych >= jakihome) {
                if (hd.getData().getConfigurationSection(uuid + ".homes.home" + jakihome) != null) {
                    p.closeInventory();
                    double X = hd.getData().getDouble(uuid + ".homes.home" + jakihome + ".x");
                    double Y = hd.getData().getDouble(uuid + ".homes.home" + jakihome + ".y");
                    double Z = hd.getData().getDouble(uuid + ".homes.home" + jakihome + ".z");
                    String world = hd.getData().getString(uuid + ".homes.home" + jakihome + ".world");
                    if (!(p.hasPermission("bxcore.user.home") || p.isOp())) {
                        double tpX = p.getLocation().getX();
                        double tpY = p.getLocation().getY();
                        double tpZ = p.getLocation().getZ();
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §98s"));
                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                            public void run(){
                                double tpX1 = p.getLocation().getX();
                                double tpY1 = p.getLocation().getY();
                                double tpZ1 = p.getLocation().getZ();
                                if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                    p.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                                }
                                if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §97s"));
                                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                        public void run() {
                                            double tpX1 = p.getLocation().getX();
                                            double tpY1 = p.getLocation().getY();
                                            double tpZ1 = p.getLocation().getZ();
                                            if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                p.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                                            }
                                            if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §96s"));
                                                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                    public void run() {
                                                        double tpX1 = p.getLocation().getX();
                                                        double tpY1 = p.getLocation().getY();
                                                        double tpZ1 = p.getLocation().getZ();
                                                        if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                            p.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                                                        }
                                                        if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §95s"));
                                                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                public void run() {
                                                                    double tpX1 = p.getLocation().getX();
                                                                    double tpY1 = p.getLocation().getY();
                                                                    double tpZ1 = p.getLocation().getZ();
                                                                    if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                                        p.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                                                                    }
                                                                    if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §94s"));
                                                                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                            public void run() {
                                                                                double tpX1 = p.getLocation().getX();
                                                                                double tpY1 = p.getLocation().getY();
                                                                                double tpZ1 = p.getLocation().getZ();
                                                                                if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                                                    p.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                                                                                }
                                                                                if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                                                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §93s"));
                                                                                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                                        public void run() {
                                                                                            double tpX1 = p.getLocation().getX();
                                                                                            double tpY1 = p.getLocation().getY();
                                                                                            double tpZ1 = p.getLocation().getZ();
                                                                                            if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                                                                p.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                                                                                            }
                                                                                            if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                                                                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §92s"));
                                                                                                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                                                    public void run() {
                                                                                                        double tpX1 = p.getLocation().getX();
                                                                                                        double tpY1 = p.getLocation().getY();
                                                                                                        double tpZ1 = p.getLocation().getZ();
                                                                                                        if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                                                                            p.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                                                                                                        }
                                                                                                        if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                                                                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §91s"));
                                                                                                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                                                                public void run() {
                                                                                                                    if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                                                                                        p.teleport(new Location(p.getServer().getWorld(world), X, Y, Z));
                                                                                                                        p.sendMessage("§7Pomyślnie przeteleportowano ciebie do §9Dom #" + jakihome);
                                                                                                                    }
                                                                                                                }
                                                                                                            }, 20);
                                                                                                        }
                                                                                                    }
                                                                                                }, 20);
                                                                                            }
                                                                                        }
                                                                                    }, 20);
                                                                                }
                                                                            }
                                                                        }, 20);
                                                                    }
                                                                }
                                                            }, 20);
                                                        }
                                                    }
                                                }, 20);
                                            }
                                        }
                                    }, 20);
                                }
                            }
                        }, 20);
                    } else {
                        p.teleport(new Location(p.getServer().getWorld(world), X, Y, Z));
                        p.sendMessage("§7Pomyślnie przeteleportowano ciebie do §9Dom #" + jakihome);
                    }
                } else {
                    p.closeInventory();
                    double X = p.getLocation().getX();
                    double Y = p.getLocation().getY();
                    double Z = p.getLocation().getZ();
                    String world = p.getLocation().getWorld().getName();
                    hd.getData().set(uuid + ".homes.home" + jakihome + ".name", "Brak");
                    hd.getData().set(uuid + ".homes.home" + jakihome + ".x", X);
                    hd.getData().set(uuid + ".homes.home" + jakihome + ".y", Y);
                    hd.getData().set(uuid + ".homes.home" + jakihome + ".z", Z);
                    hd.getData().set(uuid + ".homes.home" + jakihome + ".world", world);
                    hd.saveData();
                    p.sendMessage("§7Pomyślnie ustawileś §9Dom #" + jakihome);
                }
            } else {
                if (p.hasPermission("bxcore.user.home") || p.isOp()) {
                    if (hd.getData().getConfigurationSection(uuid + ".homes.home" + jakihome) != null) {
                        p.closeInventory();
                        double X = hd.getData().getDouble(uuid + ".homes.home" + jakihome + ".x");
                        double Y = hd.getData().getDouble(uuid + ".homes.home" + jakihome + ".y");
                        double Z = hd.getData().getDouble(uuid + ".homes.home" + jakihome + ".z");
                        String world = hd.getData().getString(uuid + ".homes.home" + jakihome + ".world");
                        if (!(p.hasPermission("bxcore.user.home") || p.isOp())) {
                            double tpX = p.getLocation().getX();
                            double tpY = p.getLocation().getY();
                            double tpZ = p.getLocation().getZ();
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §98s"));
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                public void run(){
                                    double tpX1 = p.getLocation().getX();
                                    double tpY1 = p.getLocation().getY();
                                    double tpZ1 = p.getLocation().getZ();
                                    if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                        p.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                                    }
                                    if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §97s"));
                                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                            public void run() {
                                                double tpX1 = p.getLocation().getX();
                                                double tpY1 = p.getLocation().getY();
                                                double tpZ1 = p.getLocation().getZ();
                                                if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                    p.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                                                }
                                                if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §96s"));
                                                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                        public void run() {
                                                            double tpX1 = p.getLocation().getX();
                                                            double tpY1 = p.getLocation().getY();
                                                            double tpZ1 = p.getLocation().getZ();
                                                            if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                                p.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                                                            }
                                                            if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §95s"));
                                                                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                    public void run() {
                                                                        double tpX1 = p.getLocation().getX();
                                                                        double tpY1 = p.getLocation().getY();
                                                                        double tpZ1 = p.getLocation().getZ();
                                                                        if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                                            p.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                                                                        }
                                                                        if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §94s"));
                                                                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                                public void run() {
                                                                                    double tpX1 = p.getLocation().getX();
                                                                                    double tpY1 = p.getLocation().getY();
                                                                                    double tpZ1 = p.getLocation().getZ();
                                                                                    if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                                                        p.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                                                                                    }
                                                                                    if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                                                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §93s"));
                                                                                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                                            public void run() {
                                                                                                double tpX1 = p.getLocation().getX();
                                                                                                double tpY1 = p.getLocation().getY();
                                                                                                double tpZ1 = p.getLocation().getZ();
                                                                                                if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                                                                    p.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                                                                                                }
                                                                                                if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                                                                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §92s"));
                                                                                                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                                                        public void run() {
                                                                                                            double tpX1 = p.getLocation().getX();
                                                                                                            double tpY1 = p.getLocation().getY();
                                                                                                            double tpZ1 = p.getLocation().getZ();
                                                                                                            if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                                                                                p.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                                                                                                            }
                                                                                                            if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                                                                                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §91s"));
                                                                                                                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                                                                    public void run() {
                                                                                                                        if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                                                                                            p.teleport(new Location(p.getServer().getWorld(world), X, Y, Z));
                                                                                                                            p.sendMessage("§7Pomyślnie przeteleportowano ciebie do §9Dom #" + jakihome);
                                                                                                                        }
                                                                                                                    }
                                                                                                                }, 20);
                                                                                                            }
                                                                                                        }
                                                                                                    }, 20);
                                                                                                }
                                                                                            }
                                                                                        }, 20);
                                                                                    }
                                                                                }
                                                                            }, 20);
                                                                        }
                                                                    }
                                                                }, 20);
                                                            }
                                                        }
                                                    }, 20);
                                                }
                                            }
                                        }, 20);
                                    }
                                }
                            }, 20);
                        } else {
                            p.teleport(new Location(p.getServer().getWorld(world), X, Y, Z));
                            p.sendMessage("§7Pomyślnie przeteleportowano ciebie do §9Dom #" + jakihome);
                        }
                    } else {
                        p.closeInventory();
                        double X = p.getLocation().getX();
                        double Y = p.getLocation().getY();
                        double Z = p.getLocation().getZ();
                        String world = p.getLocation().getWorld().getName();
                        hd.getData().set(uuid + ".homes.home" + jakihome + ".name", "Brak");
                        hd.getData().set(uuid + ".homes.home" + jakihome + ".x", X);
                        hd.getData().set(uuid + ".homes.home" + jakihome + ".y", Y);
                        hd.getData().set(uuid + ".homes.home" + jakihome + ".z", Z);
                        hd.getData().set(uuid + ".homes.home" + jakihome + ".world", world);
                        hd.saveData();
                        p.sendMessage("§7Pomyślnie ustawileś §9Dom #" + jakihome);
                    }
                }
            }
        }
    }

    public void usuwaniehome(Player p, int jakihome) throws IOException {
        UUID uuid = p.getUniqueId();
        if (hd.getData().getConfigurationSection(uuid + ".homes.home" + jakihome) != null) {
            p.closeInventory();
            hd.getData().set(uuid + ".homes.home" + jakihome, null);
            hd.saveData();
            p.sendMessage("§7Twój dom §9Dom #" + jakihome + " §7zostal pomyślnie usuniety");
        }
    }

    public void ustawdome(Player p, int jakihome) throws IOException {
        UUID uuid = p.getUniqueId();
        if (hd.getData().getConfigurationSection(uuid + ".homes.home" + jakihome) != null) {
            p.closeInventory();
            p.sendMessage("§7Napisz swoja nazwe domu na czacie, masz na to §930s");
            hd.getData().set(uuid + ".homes.setname.home" + jakihome, "yes");
            hd.saveData();
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                public void run() {
                    String home1 = hd.getData().getString(uuid + ".homes.setname.home" + jakihome);
                    if (home1 == "yes") {
                        hd.getData().set(uuid + ".homes.setname.home" + jakihome, null);
                        try {
                            hd.saveData();
                        } catch (IOException ioException) {
                            Bukkit.getServer().getLogger().log(Level.INFO, "§cNie udalo sie zapisac pliku §ehome.yml");
                        }
                        p.sendMessage("§7Twój czas na napisanie nazwy minąl");
                    }
                }
            }, 600);
        }
    }

    @EventHandler
    public void onOpenMenu(InventoryClickEvent e) throws IOException {
        Player p = (Player) e.getWhoClicked();
        UUID uuid = p.getUniqueId();
        if (e.getView().getTitle().equals("§9§lTwoje domy")) {
            if (e.getRawSlot() == -999) p.closeInventory();
            if (e.getRawSlot() < e.getInventory().getSize()) {
                e.setCancelled(true);
                if (e.getRawSlot() == 11) {
                    teleport(p, 1);
                }
                if (e.getRawSlot() == 12) {
                    teleport(p, 2);
                }
                if (e.getRawSlot() == 13) {
                    teleport(p, 3);
                }
                if (e.getRawSlot() == 14) {
                    teleport(p, 4);
                }
                if (e.getRawSlot() == 15) {
                    teleport(p, 5);
                }
                if (e.getRawSlot() == 18) {
                    p.closeInventory();
                    this.namehome(p);
                }
                if (e.getRawSlot() == 26) {
                    p.closeInventory();
                    this.delhome(p);
                }
            }
        } else {
            if (e.getView().getTitle().equals("§9§lUsuń wybrany dom")) {
                if (e.getRawSlot() == -999) p.closeInventory();
                if (e.getRawSlot() < e.getInventory().getSize()) {
                    e.setCancelled(true);
                    if (e.getRawSlot() == 11) {
                        usuwaniehome(p, 1);
                    }
                    if (e.getRawSlot() == 12) {
                        usuwaniehome(p, 2);
                    }
                    if (e.getRawSlot() == 13) {
                        usuwaniehome(p, 3);
                    }
                    if (e.getRawSlot() == 14) {
                        usuwaniehome(p, 4);
                    }
                    if (e.getRawSlot() == 15) {
                        usuwaniehome(p, 5);
                    }
                    if (e.getRawSlot() == 26) {
                        p.closeInventory();
                        HomeCommand.home(p);
                    }
                }
            } else {
                if (e.getView().getTitle().equals("§9§lUstaw nazwe domu")) {
                    if (e.getRawSlot() == -999) p.closeInventory();
                    if (e.getRawSlot() < e.getInventory().getSize()) {
                        e.setCancelled(true);
                        if (e.getRawSlot() == 11) {
                            ustawdome(p, 1);
                        }
                        if (e.getRawSlot() == 12) {
                            ustawdome(p, 2);
                        }
                        if (e.getRawSlot() == 13) {
                            ustawdome(p, 3);
                        }
                        if (e.getRawSlot() == 14) {
                            ustawdome(p, 4);
                        }
                        if (e.getRawSlot() == 15) {
                            ustawdome(p, 5);
                        }
                        if (e.getRawSlot() == 26) {
                            p.closeInventory();
                            HomeCommand.home(p);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void OnChat(AsyncPlayerChatEvent e) throws IOException {
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        String home1 = hd.getData().getString(uuid + ".homes.setname.home1");
        if (home1 == "yes") {
            String messange = e.getMessage();
            hd.getData().set(uuid + ".homes.home1.name", messange);
            hd.getData().set(uuid + ".homes.setname.home1", null);
            hd.saveData();
            p.sendMessage("§7Twoja nazwa dla domu §9Dom #1 §7to: §9" + messange);
            e.setCancelled(true);
        }
        String home2 = hd.getData().getString(uuid + ".homes.setname.home2");
        if (home2 == "yes") {
            String messange = e.getMessage();
            hd.getData().set(uuid + ".homes.home2.name", messange);
            hd.getData().set(uuid + ".homes.setname.home2", null);
            hd.saveData();
            p.sendMessage("§7Twoja nazwa dla domu §9Dom #2 §7to: §9" + messange);
            e.setCancelled(true);
        }
        String home3 = hd.getData().getString(uuid + ".homes.setname.home3");
        if (home3 == "yes") {
            String messange = e.getMessage();
            hd.getData().set(uuid + ".homes.home3.name", messange);
            hd.getData().set(uuid + ".homes.setname.home3", null);
            hd.saveData();
            p.sendMessage("§7Twoja nazwa dla domu §9Dom #3 §7to: §9" + messange);
            e.setCancelled(true);
        }
        String home4 = hd.getData().getString(uuid + ".homes.setname.home4");
        if (home4 == "yes") {
            String messange = e.getMessage();
            hd.getData().set(uuid + ".homes.home4.name", messange);
            hd.getData().set(uuid + ".homes.setname.home4", null);
            hd.saveData();
            p.sendMessage("§7Twoja nazwa dla domu §9Dom #4 §7to: §9" + messange);
            e.setCancelled(true);
        }
        String home5 = hd.getData().getString(uuid + ".homes.setname.home5");
        if (home5 == "yes") {
            String messange = e.getMessage();
            hd.getData().set(uuid + ".homes.home5.name", messange);
            hd.getData().set(uuid + ".homes.setname.home5", null);
            hd.saveData();
            p.sendMessage("§7Twoja nazwa dla domu §9Dom #5 §7to: §9" + messange);
            e.setCancelled(true);
        }
    }

    public void namehome(Player p) {
        UUID uuid = p.getUniqueId();
        Inventory inventory = Bukkit.createInventory((InventoryHolder)p, 27, ("§9§lUstaw nazwe domu"));
        if(hd.getData().getConfigurationSection(uuid + ".homes.home1") !=null) {
            String nazwa = hd.getData().getString(uuid + ".homes.home1" + ".name");
            ItemBuilder slot11 = (new ItemBuilder(Material.LIGHT_BLUE_CONCRETE, 1)).setTitle("§b§lDom #1").addLore("§7§lNazwa: §f§l" + nazwa);
            inventory.setItem(11, slot11.build());
        } else {
            ItemBuilder slot11 = (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lDom #1");
            inventory.setItem(11, slot11.build());
        }
        if(hd.getData().getConfigurationSection(uuid + ".homes.home2") !=null) {
            String nazwa = hd.getData().getString(uuid + ".homes.home2" + ".name");
            ItemBuilder slot11 = (new ItemBuilder(Material.LIGHT_BLUE_CONCRETE, 1)).setTitle("§b§lDom #2").addLore("§7§lNazwa: §f§l" + nazwa);
            inventory.setItem(12, slot11.build());
        } else {
            ItemBuilder slot11 = (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lDom #2");
            inventory.setItem(12, slot11.build());
        }
        if(hd.getData().getConfigurationSection(uuid + ".homes.home3") !=null) {
            String nazwa = hd.getData().getString(uuid + ".homes.home3" + ".name");
            ItemBuilder slot11 = (new ItemBuilder(Material.LIGHT_BLUE_CONCRETE, 1)).setTitle("§b§lDom #3").addLore("§7§lNazwa: §f§l" + nazwa);
            inventory.setItem(13, slot11.build());
        } else {
            ItemBuilder slot11 = (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lDom #3");
            inventory.setItem(13, slot11.build());
        }
        if(hd.getData().getConfigurationSection(uuid + ".homes.home4") !=null) {
            String nazwa = hd.getData().getString(uuid + ".homes.home4" + ".name");
            ItemBuilder slot11 = (new ItemBuilder(Material.LIGHT_BLUE_CONCRETE, 1)).setTitle("§b§lDom #4").addLore("§7§lNazwa: §f§l" + nazwa);
            inventory.setItem(14, slot11.build());
        } else {
            ItemBuilder slot11 = (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lDom #4");
            inventory.setItem(14, slot11.build());
        }
        if(hd.getData().getConfigurationSection(uuid + ".homes.home5") !=null) {
            String nazwa = hd.getData().getString(uuid + ".homes.home5" + ".name");
            ItemBuilder slot11 = (new ItemBuilder(Material.LIGHT_BLUE_CONCRETE, 1)).setTitle("§b§lDom #5").addLore("§7§lNazwa: §f§l" + nazwa);
            inventory.setItem(15, slot11.build());
        } else {
            ItemBuilder slot11 = (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lDom #5");
            inventory.setItem(15, slot11.build());
        }
        ItemBuilder backguard = (new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, 1)).setTitle("§7");
        ItemBuilder slot26 = (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lAnuluj");
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
        inventory.setItem(18, backguard.build());
        inventory.setItem(19, backguard.build());
        inventory.setItem(20, backguard.build());
        inventory.setItem(21, backguard.build());
        inventory.setItem(22, backguard.build());
        inventory.setItem(23, backguard.build());
        inventory.setItem(24, backguard.build());
        inventory.setItem(25, backguard.build());
        inventory.setItem(26, slot26.build());
        p.openInventory(inventory);
    }

    public void delhome(Player p) {
        UUID uuid = p.getUniqueId();
        Inventory inventory = Bukkit.createInventory((InventoryHolder)p, 27, ("§9§lUsuń wybrany dom"));
        if(hd.getData().getConfigurationSection(uuid + ".homes.home1") !=null) {
            String nazwa = hd.getData().getString(uuid + ".homes.home1" + ".name");
            ItemBuilder slot11 = (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lDom #1").addLore("§7§lNazwa: §f§l" + nazwa);
            inventory.setItem(11, slot11.build());
        } else {
            ItemBuilder slot11 = (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lDom #1");
            inventory.setItem(11, slot11.build());
        }
        if(hd.getData().getConfigurationSection(uuid + ".homes.home2") !=null) {
            String nazwa = hd.getData().getString(uuid + ".homes.home2" + ".name");
            ItemBuilder slot11 = (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lDom #2").addLore("§7§lNazwa: §f§l" + nazwa);
            inventory.setItem(12, slot11.build());
        } else {
            ItemBuilder slot11 = (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lDom #2");
            inventory.setItem(12, slot11.build());
        }
        if(hd.getData().getConfigurationSection(uuid + ".homes.home3") !=null) {
            String nazwa = hd.getData().getString(uuid + ".homes.home3" + ".name");
            ItemBuilder slot11 = (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lDom #3").addLore("§7§lNazwa: §f§l" + nazwa);
            inventory.setItem(13, slot11.build());
        } else {
            ItemBuilder slot11 = (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lDom #3");
            inventory.setItem(13, slot11.build());
        }
        if(hd.getData().getConfigurationSection(uuid + ".homes.home4") !=null) {
            String nazwa = hd.getData().getString(uuid + ".homes.home4" + ".name");
            ItemBuilder slot11 = (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lDom #4").addLore("§7§lNazwa: §f§l" + nazwa);
            inventory.setItem(14, slot11.build());
        } else {
            ItemBuilder slot11 = (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lDom #4");
            inventory.setItem(14, slot11.build());
        }
        if(hd.getData().getConfigurationSection(uuid + ".homes.home5") !=null) {
            String nazwa = hd.getData().getString(uuid + ".homes.home5" + ".name");
            ItemBuilder slot11 = (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lDom #5").addLore("§7§lNazwa: §f§l" + nazwa);
            inventory.setItem(15, slot11.build());
        } else {
            ItemBuilder slot11 = (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lDom #5");
            inventory.setItem(15, slot11.build());
        }
        ItemBuilder backguard = (new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, 1)).setTitle("§7");
        ItemBuilder slot26 = (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lAnuluj");
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
        inventory.setItem(18, backguard.build());
        inventory.setItem(19, backguard.build());
        inventory.setItem(20, backguard.build());
        inventory.setItem(21, backguard.build());
        inventory.setItem(22, backguard.build());
        inventory.setItem(23, backguard.build());
        inventory.setItem(24, backguard.build());
        inventory.setItem(25, backguard.build());
        inventory.setItem(26, slot26.build());
        p.openInventory(inventory);
    }
}