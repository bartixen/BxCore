package pl.bartixen.bxcore.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.bartixen.bxcore.Main;

public class NightCommand implements CommandExecutor {

    Main plugin;

    public NightCommand(Main m) {
        plugin = m;
        m.getCommand("night").setExecutor(this);
        m.getCommand("noc").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.night") || sender.isOp()) {
            plugin.getServer().getWorld("world").setTime(39000);
            sender.sendMessage("§7Ustawiono §9noc §7na świecie");
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.night");
        }
        return false;
    }
}