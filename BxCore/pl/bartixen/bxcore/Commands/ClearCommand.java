package pl.bartixen.bxcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

public class ClearCommand implements CommandExecutor {

    Main plugin;

    public ClearCommand(Main m) {
        plugin = m;
        m.getCommand("clear").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.clear") || sender.isOp()) {
            if (args.length == 0) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§7Poprawne użycie: §9/clear [gracz]");
                    return false;
                }
                Player p = (Player) sender;
                p.getInventory().clear();
                p.getInventory().setBoots(null);
                p.getInventory().setLeggings(null);
                p.getInventory().setChestplate(null);
                p.getInventory().setHelmet(null);
                p.getInventory().setHeldItemSlot(4);
                p.sendMessage("§7Twój ekwipunek zostal wyczyszczony");
            } else {
                if (args.length == 1) {
                    Player cel = Bukkit.getPlayerExact(args[0]);
                    if (cel == null) {
                        sender.sendMessage("§7Ten gracz jest §coffline");
                        return true;
                    }
                    cel.getInventory().clear();
                    cel.getInventory().setBoots(null);
                    cel.getInventory().setLeggings(null);
                    cel.getInventory().setChestplate(null);
                    cel.getInventory().setHelmet(null);
                    cel.getInventory().setHeldItemSlot(4);
                    cel.sendMessage("§7Twój ekwipunek zostal wyczyszczony przez gracza §9" + sender.getName());
                    sender.sendMessage("§7Poprawne wyczyszczono graczu §9" + cel.getName() + " §7ekwipunek");
                } else {
                    sender.sendMessage("§7Poprawne użycie: §9/clear [gracz]");
                }
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.clear");
        }
        return false;
    }
}