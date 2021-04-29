package pl.bartixen.bxcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

public class TphereCommand implements CommandExecutor {

    Main plugin;

    public TphereCommand(Main m) {
        plugin = m;
        m.getCommand("tphere").setExecutor(this);
        m.getCommand("s").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.tp") || sender.isOp()) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cTa komenda jest przeznaczona tylko dla graczy");
                return false;
            }
            if (args.length == 1) {
                Player p = (Player) sender;
                Player cel = Bukkit.getPlayerExact(args[0]);
                if (cel == null) {
                    p.sendMessage("§7Ten gracz jest §coffline");
                    return true;
                }
                cel.teleport(p.getLocation());
                p.sendMessage("§7Przeteleportowano gracza §9" + cel.getName() + " §7do ciebie");
                cel.sendMessage("§7Przeteleportowano ciebie do gracza §9" + p.getName());
            } else {
                sender.sendMessage("§7Poprawne użycie: §9/s [gracz]");
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.tp");
        }
        return false;
    }
}