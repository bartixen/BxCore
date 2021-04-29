package pl.bartixen.bxcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import pl.bartixen.bxcore.Main;

public class AnvilCommand implements CommandExecutor {

    Main plugin;

    public AnvilCommand(Main m) {
        plugin = m;
        m.getCommand("anvil").setExecutor(this);
        m.getCommand("kowadlo").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.anvil") || sender.isOp()) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cTa komenda jest przeznaczona tylko dla graczy");
                return false;
            }
            Player p = (Player) sender;
            Inventory anvil = Bukkit.createInventory(p, InventoryType.ANVIL);
            p.openInventory(anvil);
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.anvil");
        }
        return false;
    }

}
