package pl.bartixen.bxcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.bartixen.bxcore.Main;

import java.util.logging.Level;

public class RepairCommand implements CommandExecutor {

    Main plugin;

    public RepairCommand(Main m) {
        plugin = m;
        m.getCommand("repair").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length == 1) {
                Player cel = Bukkit.getPlayerExact(args[0]);
                if (cel == null) {
                    sender.sendMessage("§7Ten gracz jest §coffline");
                    return true;
                }
                ItemStack[] contents;

                for (int length = (contents = cel.getInventory().getContents()).length, i = 0; i < length; i++) {
                    ItemStack itemStack = contents[i];

                    if (itemStack != null) {
                        itemStack.setDurability((short) 0);

                    }
                }
                ItemStack[] armorContents;
                for (int lengyh2 = (armorContents = cel.getEquipment().getArmorContents()).length, j = 0; j < lengyh2; ++j) {
                    ItemStack itemStack2 = armorContents[j];

                    if (itemStack2 != null) {
                        itemStack2.setDurability((short) 0);
                    }
                }
                cel.sendMessage("§7Naprawiono wszystkie przedmioty w ekwipunku przez §9" + sender.getName());
                sender.sendMessage("§7Naprawiono wszystkie przedmioty w ekwipunku gracza §9" + cel.getName());
                if (plugin.getConfig().getBoolean("logs")) {
                    plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " naprawil wszystkie przedmioty gracza " + cel.getName());
                }
            } else {
                sender.sendMessage("§7Poprawne użycie: §9/repair [gracz]");
                return false;
            }
        }
        Player p = (Player) sender;
        if (args.length == 1) {
            if (p.hasPermission("bxcore.commands.repairall") || p.isOp()) {
                if (args[0].equalsIgnoreCase("all")) {
                    ItemStack[] contents;

                    for (int length = (contents = p.getInventory().getContents()).length, i = 0; i < length; i++) {
                        ItemStack itemStack = contents[i];

                        if (itemStack != null) {
                            itemStack.setDurability((short) 0);

                        }
                    }
                    ItemStack[] armorContents;
                    for (int lengyh2 = (armorContents = p.getEquipment().getArmorContents()).length, j = 0; j < lengyh2; ++j) {
                        ItemStack itemStack2 = armorContents[j];

                        if (itemStack2 != null) {
                            itemStack2.setDurability((short) 0);
                        }
                    }
                    p.sendMessage("§7Naprawiono wszystkie przedmioty w ekwipunku");
                    if (plugin.getConfig().getBoolean("logs")) {
                        plugin.getLogger().log(Level.INFO, "Gracz " + p.getName() + " naprawil wszystkie przedmioty");
                    }
                } else {
                    p.sendMessage("§7Poprawne użycie: §9/repair [all]");
                }
            } else {
                p.sendMessage("§7Brak permisji: §9bxcore.commands.repairall");
            }
        } else {
            if (args.length == 0) {
                if (p.hasPermission("bxcore.commands.repair") || p.isOp()) {
                    p.getInventory().getItemInMainHand().setDurability((short) 0);
                    p.sendMessage("§7Naprawiono przedmiot");
                    if (plugin.getConfig().getBoolean("logs")) {
                        plugin.getLogger().log(Level.INFO, "Gracz " + p.getName() + " naprawil przedmiot trzymany w reku");
                    }
                } else {
                    p.sendMessage("§7Brak permisji: §9bxcore.commands.repair");
                }
            } else {
                p.sendMessage("§7Poprawne użycie: §9/repair [all]");
            }
        }
        return false;
    }
}

