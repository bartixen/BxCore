package pl.bartixen.bxcore.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.bartixen.bxcore.Main;

import java.util.logging.Level;

public class DayCommand implements CommandExecutor {

    Main plugin;

    public DayCommand(Main m) {
        plugin = m;
        m.getCommand("day").setExecutor(this);
        m.getCommand("dzien").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.day") || sender.isOp()) {
            plugin.getServer().getWorld("world").setTime(1000);
            sender.sendMessage("§7Ustawiono §9dzień §7na świecie");
            if (plugin.getConfig().getBoolean("logs")) {
                plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " ustawil dzien na serwerze");
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.day");
        }
        return false;
    }

}
