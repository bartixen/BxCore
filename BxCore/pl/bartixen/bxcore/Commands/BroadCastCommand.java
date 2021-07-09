package pl.bartixen.bxcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

import java.util.logging.Level;

public class BroadCastCommand implements CommandExecutor {

    Main plugin;

    public BroadCastCommand(Main m) {
        plugin = m;
        m.getCommand("bc").setExecutor(this);
        m.getCommand("broadcast").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.alert") || sender.isOp()) {
            if (args.length > 0) {
                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < args.length; ++i) {
                    sb.append(args[i]).append(" ");
                }

                String msg = sb.toString().replace("&", "§");

                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage("§c§l" + msg);
                }
                sender.sendMessage("§7Wyslano pomyślnie alert");
                if (plugin.getConfig().getBoolean("logs")) {
                    plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " wyslal alert o tresci §9" + msg);
                }
            } else {
                sender.sendMessage("§7Poprawne użycie: §9/bc [wiadomosc]");
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.alert");
        }
        return false;
    }
}
