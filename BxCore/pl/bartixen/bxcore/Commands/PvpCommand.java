package pl.bartixen.bxcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.bartixen.bxcore.Data.AntyLogutDataManager;
import pl.bartixen.bxcore.Data.UserDataManager;
import pl.bartixen.bxcore.Main;

import java.io.IOException;
import java.util.HashMap;

public class PvpCommand implements CommandExecutor, Listener {

    Main plugin;

    static UserDataManager userd;
    static AntyLogutDataManager antylogutd;

    public PvpCommand(Main m) {
        plugin = m;
        m.getCommand("pvp").setExecutor(this);
        userd = UserDataManager.getInstance();
        antylogutd = AntyLogutDataManager.getInstance();
    }

    public static int removeItems(Inventory inventory, Material type, int amount) {
        if (type == null || inventory == null)
            return -1;
        if (amount <= 0)
            return -1;
        if (amount == Integer.MAX_VALUE) {
            inventory.remove(type);
            return 0;
        }
        HashMap<Integer, ItemStack> retVal = inventory.removeItem(new ItemStack[] { new ItemStack(type, amount) });
        int notRemoved = 0;
        for (ItemStack item : retVal.values())
            notRemoved += item.getAmount();
        return notRemoved;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (args.length == 0) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§7Poprawne użycie: §9/pvp [gracz]");
                    return false;
                }
                Player p = (Player) sender;
                if (userd.getData().getBoolean(sender.getName() + ".pvp")) {
                    if (p.getInventory().contains(Material.DIAMOND, 8)) {
                        removeItems((Inventory) p.getInventory(), Material.DIAMOND, 8);
                        userd.getData().set(sender.getName() + ".pvp", false);
                        try {
                            userd.saveData();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        sender.sendMessage("§7Pomyślnie §cwylaczono §7PVP");
                    } else {
                        sender.sendMessage("§7Aby wylaczyc PVP należy mieć §98 diax");
                    }
                } else {
                    userd.getData().set(sender.getName() + ".pvp", true);
                    try {
                        userd.saveData();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    sender.sendMessage("§7Pomyślnie §awlaczono §7PVP");
                }
            } else {
                if (args.length == 1) {
                    if (sender.hasPermission("bxcore.commands.pvp") || sender.isOp()) {
                        Player cel = Bukkit.getPlayerExact(args[0]);
                        if (cel == null) {
                            sender.sendMessage("§7Ten gracz jest §coffline");
                            return true;
                        }
                        if (userd.getData().getBoolean(cel.getName() + ".pvp")) {
                            userd.getData().set(cel.getName() + ".pvp", false);
                            try {
                                userd.saveData();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            sender.sendMessage("§7Pomyślnie §cwylaczono §7PVP dla gracza §9" + cel.getName());
                            cel.sendMessage("§7Pomyślnie §cwylaczono §7PVP przez §9" + cel.getName());
                        } else {
                            userd.getData().set(sender.getName() + ".pvp", true);
                            try {
                                userd.saveData();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            sender.sendMessage("§7Pomyślnie §awlaczono §7PVP dla gracza §9" + cel.getName());
                            cel.sendMessage("§7Pomyślnie §awlaczono §7PVP przez gracza §9" + cel.getName());
                        }
                    } else {
                        sender.sendMessage("§7Brak permisji: §9bxcore.commands.pvp");
                    }
                } else {
                    sender.sendMessage("§7Poprawne użycie: §9/pvp [gracz]");
                }
            }
        return false;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamage(EntityDamageByEntityEvent e) throws IOException {
        if (e.getEntity() instanceof Player) {
            final Player player = (Player)e.getEntity();
            if (e.getDamager() instanceof Player) {

                if (!userd.getData().getBoolean(e.getEntity().getName() + ".pvp")) {
                    e.setCancelled(true);
                    return;
                }
                if (!userd.getData().getBoolean(e.getDamager().getName() + ".pvp")) {
                    e.setCancelled(true);
                    return;
                }
                int czas = plugin.getConfig().getInt("antylogut.czas");
                Player p1 = (Player) e.getEntity();
                Player p2 = (Player) e.getDamager();
                antylogutd.getData().set(p1.getDisplayName() + ".czas", czas);
                antylogutd.getData().set(p2.getDisplayName() + ".czas", czas);
                antylogutd.saveData();
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws IOException {
        Player p = e.getPlayer();
        String nick = p.getName();
        if (userd.getData().getString(nick + ".pvp") == null) {
            userd.getData().set(nick + ".pvp", false);
            userd.saveData();
        }
    }
}
