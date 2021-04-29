package pl.bartixen.bxcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

public class InvseeCommand implements CommandExecutor {

    Main plugin;

    public InvseeCommand(Main m) {
        plugin = m;
        m.getCommand("invsee").setExecutor(this);
        m.getCommand("oi").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cTa komenda jest przeznaczona tylko dla graczy");
            return false;
        }
        Player p = (Player) sender;
        if (p.hasPermission("bxcore.commands.invsee") || p.isOp()) {
            if (args.length == 1) {
                Player cel = Bukkit.getPlayerExact(args[0]);
                if (cel == null) {
                    p.sendMessage("§7Ten gracz jest §coffline");
                    return true;
                }
                if (p.getName() == cel.getName()) {
                    sender.sendMessage("§7Nie możesz użyć tej komendy na sobie");
                } else {
                    p.openInventory(cel.getInventory());
                }
            } else {
                p.sendMessage("§7Poprawne użycie: §9/invsee [gracz]");
            }
        } else {
            p.sendMessage("§7Brak permisji: §9bxcore.commands.invsee");
        }
        return false;
    }
}