package pl.bartixen.bxcore.Ban;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

public class KickCommand implements CommandExecutor {

    Main plugin;

    String msg;

    public KickCommand(Main m) {
        plugin = m;
        m.getCommand("kick").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.kick") || sender.isOp()) {
            if (args.length > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < args.length; ++i) {
                    sb.append(args[i]).append(" ");
                }
                msg = sb.toString().replace("&", "§");
                Player cel = Bukkit.getPlayerExact(args[0]);
                String nazwa = plugin.getConfig().getString("nazwa");
                if (cel == null) {
                    sender.sendMessage("§7Gracz §9" + args[0] + " §7jest §coffline");
                    return false;
                }
                if (!(args.length > 1)) { msg = "brak"; }
                cel.kickPlayer("\n§8• — • — • — • §9§lKICK §8• — • — • — •\n\n§7Powód: §9" + msg + "\n§7Administrator: §9" + sender.getName() + "\n\n§8• — • — • — • §f§l" + nazwa + " §8• — • — • — •\n");
                sender.sendMessage("§7Gracz §9" + cel.getName() + " §7zostal pomyślnie wyrzucony");
                for (Player players : Bukkit.getOnlinePlayers()) {
                    if (players.hasPermission("bxcore.commands.kick") || players.isOp()) {
                        players.sendMessage("§7Gracz §9" + cel.getName() + " §7zostal wyrzucony przez §9" + sender.getName() +  "§7 z powodem §9" + msg);
                    }
                }
            } else {
                sender.sendMessage("§7Poprawne użycie: §9/kick [gracz] [powód]");
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.kick");
        }
        return false;
    }
}
