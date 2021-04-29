package pl.bartixen.bxcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.bartixen.bxcore.Main;

import java.util.Arrays;
import java.util.List;

public class RepairCommand implements CommandExecutor {

    Main plugin;

    public RepairCommand(Main m) {
        plugin = m;
        m.getCommand("repair").setExecutor(this);
    }

    private static List<Material> items = Arrays.asList(new Material[] {
            Material.WOODEN_AXE,
            Material.WOODEN_HOE,
            Material.WOODEN_SWORD,
            Material.WOODEN_PICKAXE,
            Material.WOODEN_SHOVEL,
            Material.BOW,
            Material.SHEARS,
            Material.FISHING_ROD,
            Material.FLINT_AND_STEEL,
            Material.CARROT_ON_A_STICK,
            Material.IRON_AXE,
            Material.IRON_HOE,
            Material.IRON_SWORD,
            Material.IRON_PICKAXE,
            Material.IRON_SHOVEL,
            Material.GOLDEN_AXE,
            Material.GOLDEN_HOE,
            Material.GOLDEN_SWORD,
            Material.GOLDEN_PICKAXE,
            Material.GOLDEN_SHOVEL,
            Material.DIAMOND_AXE,
            Material.DIAMOND_HOE,
            Material.DIAMOND_SWORD,
            Material.DIAMOND_PICKAXE,
            Material.DIAMOND_SHOVEL,
            Material.STONE_AXE,
            Material.STONE_HOE,
            Material.STONE_SWORD,
            Material.STONE_PICKAXE,
            Material.STONE_SHOVEL,
            Material.LEATHER_BOOTS,
            Material.LEATHER_CHESTPLATE,
            Material.LEATHER_HELMET,
            Material.LEATHER_LEGGINGS,
            Material.IRON_BOOTS,
            Material.IRON_CHESTPLATE,
            Material.IRON_HELMET,
            Material.IRON_LEGGINGS,
            Material.GOLDEN_BOOTS,
            Material.GOLDEN_CHESTPLATE,
            Material.GOLDEN_HELMET,
            Material.GOLDEN_LEGGINGS,
            Material.DIAMOND_BOOTS,
            Material.DIAMOND_CHESTPLATE,
            Material.DIAMOND_HELMET,
            Material.DIAMOND_LEGGINGS,
            Material.CHAINMAIL_BOOTS,
            Material.CHAINMAIL_CHESTPLATE,
            Material.CHAINMAIL_HELMET,
            Material.CHAINMAIL_LEGGINGS,
            Material.TURTLE_HELMET,
            Material.FLINT_AND_STEEL,
            Material.LEATHER_HELMET,
            Material.LEATHER_LEGGINGS,
            Material.LEATHER_BOOTS,
            Material.LEATHER_CHESTPLATE,
            Material.ELYTRA,
            Material.SHIELD,
            Material.TRIDENT,
            Material.CROSSBOW,
            Material.NETHERITE_SWORD,
            Material.NETHERITE_SHOVEL,
            Material.NETHERITE_PICKAXE,
            Material.NETHERITE_AXE,
            Material.NETHERITE_HOE,
            Material.NETHERITE_HELMET,
            Material.NETHERITE_CHESTPLATE,
            Material.NETHERITE_LEGGINGS,
            Material.NETHERITE_BOOTS,
            Material.WARPED_FUNGUS_ON_A_STICK,
    });
    
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

                for(int length = (contents = cel.getInventory().getContents()).length, i = 0; i < length; i++) {
                    ItemStack itemStack = contents[i];

                    if(itemStack != null) {
                        itemStack.setDurability((short) 0);

                    }
                }
                ItemStack[] armorContents;
                for(int lengyh2 = (armorContents = cel.getEquipment().getArmorContents()).length, j = 0; j < lengyh2; ++j) {
                    ItemStack itemStack2 = armorContents[j];

                    if(itemStack2 != null) {
                        itemStack2.setDurability((short) 0);
                    }
                }
                cel.sendMessage("§7Naprawiono wszystkie przedmioty w ekwipunku przez §9" + sender.getName());
                sender.sendMessage("§7Naprawiono wszystkie przedmioty w ekwipunku gracza §9" + cel.getName());
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

                    for(int length = (contents = p.getInventory().getContents()).length, i = 0; i < length; i++) {
                        ItemStack itemStack = contents[i];

                        if(itemStack != null) {
                            itemStack.setDurability((short) 0);

                        }
                    }
                    ItemStack[] armorContents;
                    for(int lengyh2 = (armorContents = p.getEquipment().getArmorContents()).length, j = 0; j < lengyh2; ++j) {
                        ItemStack itemStack2 = armorContents[j];

                        if(itemStack2 != null) {
                            itemStack2.setDurability((short) 0);
                        }
                    }
                    p.sendMessage("§7Naprawiono wszystkie przedmioty w ekwipunku");
                } else {
                    p.sendMessage("§7Poprawne użycie: §9/repair [all]");
                }
            } else {
                p.sendMessage("§7Brak permisji: §9bxcore.commands.repairall");
            }
        } else {
            if (args.length == 0) {
                if (p.hasPermission("bxcore.commands.repair") || p.isOp()) {
                    if (canRepair(p.getInventory().getItemInMainHand())) {
                        p.getInventory().getItemInMainHand().setDurability((short) 0);
                    } else {
                        p.sendMessage("§7Nie można naprawić tego przedmiotu");
                        return false;
                    }
                    p.sendMessage("§7Naprawiono przedmiot");
                } else {
                    p.sendMessage("§7Brak permisji: §9bxcore.commands.repair");
                }
            } else {
                p.sendMessage("§7Poprawne użycie: §9/repair [all]");
            }
        }
        return false;
    }
    public static boolean canRepair(ItemStack is) {
        return items.contains(is.getType());
    }
}
