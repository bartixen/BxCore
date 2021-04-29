package pl.bartixen.bxcore.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class VoucherHome implements Listener {

    @EventHandler(priority= EventPriority.HIGH)
    public void onPlayerUse(PlayerInteractEvent event){
        if (event.getAction() != Action.RIGHT_CLICK_AIR) return;
        Player p = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (p.getItemInHand().getType() == Material.PAPER && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§7» §f§lVoucher na §9§lHOME §7«")) {
                p.sendMessage("§7Wykorzystales voucher na home");
                ItemStack mala = new ItemStack(Material.PAPER);
                ItemMeta malam = mala.getItemMeta();
                malam.setDisplayName("§7» §f§lVoucher na §9§lHOME §7«");
                malam.setLore(Arrays.asList("§7§o╔════════════╗" , "§7» §fKliknij prawym aby użyć" , "§7§o╚════════════╝"));
                mala.setItemMeta(malam);
                p.getInventory().removeItem(mala);
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp user " + p.getName() + " add bxcore.user.home");
            }
        }
    }
}
