package pl.bartixen.bxcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

import java.util.logging.Level;

public class HelpopCommand implements CommandExecutor {

    Main plugin;

    public HelpopCommand(Main m) {
        plugin = m;
        m.getCommand("helpop").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("§cTa komenda jest przeznaczona tylko dla graczy");
            return false;
        }
        Player p = (Player) sender;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < args.length; ++i) {
            sb.append(args[i]).append(" ");
        }

        String msg = sb.toString();
        if (args.length > 0) {
            sender.sendMessage("§7Twoja wiadomość zostala wyslana do administracji");
            for (Player players : Bukkit.getOnlinePlayers()) {
                if (players.hasPermission("bxcore.alert.helpop") || players.isOp()) {
                    players.sendMessage("§8§l[§c§lHELPOP§8§l] §e§lGracz §c§l" + p.getName() + " §e§lnapisal: §c§l" + msg);
                }
            }
            plugin.getLogger().log(Level.WARNING, "§c[HELPOP] Gracz " + p.getName() + " napisal: " + msg);
        } else {
            sender.sendMessage("§7Poprawne użycie: §9/helpop [wiadomosc]");
        }
        return false;
    }
}