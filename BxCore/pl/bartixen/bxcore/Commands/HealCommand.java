package pl.bartixen.bxcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

public class HealCommand implements CommandExecutor {

    Main plugin;

    public HealCommand(Main m) {
        plugin = m;
        m.getCommand("heal").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.heal") || sender.isOp()) {
            if (args.length == 0) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§7Poprawne użycie: §9/heal [gracz]");
                    return false;
                }
                Player p = (Player) sender;
                p.setHealth(20);
                p.setFireTicks(0);
                p.setFoodLevel(20);
                p.sendMessage("§7Zostaleś uleczony");
                return false;
            } else {
                if (args.length == 1) {
                    Player cel = Bukkit.getPlayerExact(args[0]);
                    if (cel == null) {
                        sender.sendMessage("§7Ten gracz jest §coffline");
                        return true;
                    }
                    cel.setHealth(20);
                    cel.setFireTicks(0);
                    cel.setFoodLevel(20);
                    cel.sendMessage("§7Zostaleś uleczony przez §9" + sender.getName());
                    sender.sendMessage("§7Poprawne uleczono gracza §9" + cel.getName());
                } else {
                    sender.sendMessage("§7Poprawne użycie: §9/heal [gracz]");
                }
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.heal");
        }
        return false;
    }
}