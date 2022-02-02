package pl.bartixen.bxcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

public class EnderchestCommand implements CommandExecutor {

    Main plugin;

    public EnderchestCommand(Main m) {
        plugin = m;
        m.getCommand("ec").setExecutor(this);
        m.getCommand("enderchest").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cTa komenda jest przeznaczona tylko dla graczy");
            return false;
        }
        Player p = (Player) sender;
        if (p.hasPermission("bxcore.commands.enderchest") || p.isOp()) {
            if (args.length == 0) {
                p.openInventory(p.getEnderChest());
            } else {
                if (args.length == 1) {
                    if (p.hasPermission("bxcore.commands.enderchest.admin") || p.isOp()) {
                        Player cel = Bukkit.getPlayerExact(args[0]);
                        if (cel == null) {
                            p.sendMessage("§7Ten gracz jest §coffline");
                            return true;
                        }
                        p.openInventory(cel.getEnderChest());
                    } else {
                        p.openInventory(p.getEnderChest());
                    }
                } else {
                    p.sendMessage("§7Poprawne użycie: §9/ec [gracz]");
                }
            }
        } else {
            p.sendMessage("§7Brak permisji: §9bxcore.commands.enderchest");
        }
        return false;
    }
}