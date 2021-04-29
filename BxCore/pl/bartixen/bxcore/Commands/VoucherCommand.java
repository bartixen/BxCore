package pl.bartixen.bxcore.Commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.bartixen.bxcore.Main;

import java.util.Arrays;

public class VoucherCommand implements CommandExecutor {

    Main plugin;

    public VoucherCommand(Main m) {
        plugin = m;
        m.getCommand("voucher").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.voucher") || sender.isOp()) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cTa komenda jest przeznaczona tylko dla graczy");
                return false;
            }
            Player p = (Player) sender;
            ItemStack mala = new ItemStack(Material.PAPER);
            ItemMeta malam = mala.getItemMeta();
            malam.setDisplayName("§7» §f§lVoucher na §9§lHOME §7«");
            malam.setLore(Arrays.asList("§7§o╔════════════╗" , "§7» §fKliknij prawym aby użyć" , "§7§o╚════════════╝"));
            mala.setItemMeta(malam);
            p.getInventory().addItem(mala);
            p.sendMessage("§7Pomyślnie dodano voucher do ekwipunku");
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.voucher");
        }
        return false;
    }
}