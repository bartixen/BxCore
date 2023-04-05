package pl.bartixen.bxcore.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.bartixen.bxcore.Main;

public class PluginsCommand implements CommandExecutor {

    Main plugin;

    public PluginsCommand(Main m) {
        plugin = m;
        m.getCommand("pl").setExecutor(this);
        m.getCommand("plugins").setExecutor(this);
        m.getCommand("ver").setExecutor(this);
        m.getCommand("version").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String nazwa = plugin.serwer;

        sender.sendMessage("§7");
        sender.sendMessage("§7Server §e" + nazwa + " §7powered by §eBARTIXEN");
        sender.sendMessage("§7Author: §eBartixen");
        sender.sendMessage("§7Website: §ehttps://bartixen.pl");
        sender.sendMessage("§7");
        return false;
    }

}
