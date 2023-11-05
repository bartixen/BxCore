package pl.bartixen.bxcore.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

public class LogBlockCommand implements CommandExecutor {

    Main plugin;

    public LogBlockCommand(Main m) {
        plugin = m;
        m.getCommand("logblock").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.performCommand("co inspect");
        }
        return false;
    }
}
