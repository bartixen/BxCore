package pl.bartixen.bxcore.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

public class CraftingCommand implements CommandExecutor {

    Main plugin;

    public CraftingCommand(Main m) {
        plugin = m;
        m.getCommand("wb").setExecutor(this);
        m.getCommand("crafting").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.crafting") || sender.isOp()) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cTa komenda jest przeznaczona tylko dla graczy");
                return false;
            }
            Player p = (Player) sender;
            p.openWorkbench(null, true);
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.crafting");
        }
        return false;
    }

}
