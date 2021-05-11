package pl.bartixen.bxcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.InventoryView;
import pl.bartixen.bxcore.Data.TeamDataManager;
import pl.bartixen.bxcore.Main;
import pl.bartixen.bxcore.Utils.ItemBuilder;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class TeamyCommand  implements CommandExecutor, Listener {

    static Main plugin;

    static TeamDataManager teamd;

    public TeamyCommand(Main m) throws IOException {
        plugin = m;
        teamd = TeamDataManager.getInstance();
        m.getCommand("teamy").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cTa komenda jest przeznaczona tylko dla graczy");
            return false;
        }
        Player p = (Player) sender;
        try {
            team(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static InventoryView team(Player p) throws IOException {
        int czerwoni_ile = 0;
        List lista_czerwoni = Collections.singletonList("Brak");
        if (!(teamd.getData().getList("czerwony.osoby") == null)) {
            czerwoni_ile = teamd.getData().getList("czerwony.osoby").size();
            lista_czerwoni = teamd.getData().getStringList("czerwony.osoby");
        }
        int czerwoni_limit = teamd.getData().getInt("czerwony.limit");
        int jasnoniebieski_ile = 0;
        List lista_jasnoniebieski = Collections.singletonList("Brak");
        if (!(teamd.getData().getList("jasnoniebieski.osoby") == null)) {
            jasnoniebieski_ile = teamd.getData().getList("jasnoniebieski.osoby").size();
            lista_jasnoniebieski = teamd.getData().getStringList("jasnoniebieski.osoby");
        }
        int jasnoniebieski_limit = teamd.getData().getInt("jasnoniebieski.limit");
        int zolty_ile = 0;
        List lista_zolty = Collections.singletonList("Brak");
        if (!(teamd.getData().getList("zolty.osoby") == null)) {
            zolty_ile = teamd.getData().getList("zolty.osoby").size();
            lista_zolty = teamd.getData().getStringList("zolty.osoby");
        }
        int zolty_limit = teamd.getData().getInt("zolty.limit");
        int rozowy_ile = 0;
        List lista_rozowy = Collections.singletonList("Brak");
        if (!(teamd.getData().getList("rozowy.osoby") == null)) {
            rozowy_ile = teamd.getData().getList("rozowy.osoby").size();
            lista_rozowy = teamd.getData().getStringList("rozowy.osoby");
        }
        int rozowy_limit = teamd.getData().getInt("rozowy.limit");
        int zielony_ile = 0;
        List lista_zielony = Collections.singletonList("Brak");
        if (!(teamd.getData().getList("zielony.osoby") == null)) {
            zielony_ile = teamd.getData().getList("zielony.osoby").size();
            lista_zielony = teamd.getData().getStringList("zielony.osoby");
        }
        int zielony_limit = teamd.getData().getInt("zielony.limit");
        int pomaranczowy_ile = 0;
        List lista_pomaranczowy = Collections.singletonList("Brak");
        if (!(teamd.getData().getList("pomaranczowy.osoby") == null)) {
            pomaranczowy_ile = teamd.getData().getList("pomaranczowy.osoby").size();
            lista_pomaranczowy = teamd.getData().getStringList("pomaranczowy.osoby");
        }
        int pomaranczowy_limit = teamd.getData().getInt("pomaranczowy.limit");
        int niebieski_ile = 0;
        List lista_niebieski = Collections.singletonList("Brak");
        if (!(teamd.getData().getList("niebieski.osoby") == null)) {
            niebieski_ile = teamd.getData().getList("niebieski.osoby").size();
            lista_niebieski = teamd.getData().getStringList("niebieski.osoby");
        }
        int niebieski_limit = teamd.getData().getInt("niebieski.limit");

        Inventory inventory = Bukkit.createInventory((InventoryHolder) p, 27, ("§9§lTEAMY"));
        ItemBuilder slot10 = (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lTEAM CZERWONY").addLore("§7").addLore("§fLimit: §7(§9" + czerwoni_ile + "§7/§9" + czerwoni_limit + "§7)").addLore("§fOsoby: §9" + lista_czerwoni).addLore("§7");
        inventory.setItem(10, slot10.build());
        ItemBuilder slot11 = (new ItemBuilder(Material.LIGHT_BLUE_CONCRETE, 1)).setTitle("§b§lTEAM JASNO NIEBIESKI").addLore("§7").addLore("§fLimit: §7(§9" + jasnoniebieski_ile + "§7/§9" + jasnoniebieski_limit + "§7)").addLore("§fOsoby: §9" + lista_jasnoniebieski).addLore("§7");
        inventory.setItem(11, slot11.build());
        ItemBuilder slot12 = (new ItemBuilder(Material.YELLOW_CONCRETE, 1)).setTitle("§e§lTEAM ZOLTY").addLore("§7").addLore("§fLimit: §7(§9" + zolty_ile + "§7/§9" + zolty_limit + "§7)").addLore("§fOsoby: §9" + lista_zolty).addLore("§7");
        inventory.setItem(12, slot12.build());
        ItemBuilder slot13 = (new ItemBuilder(Material.PINK_CONCRETE, 1)).setTitle("§d§lTEAM ROZOWY").addLore("§7").addLore("§fLimit: §7(§9" + rozowy_ile + "§7/§9" + rozowy_limit + "§7)").addLore("§fOsoby: §9" + lista_rozowy).addLore("§7");
        inventory.setItem(13, slot13.build());
        ItemBuilder slot14 = (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lTEAM JASNO ZIELONY").addLore("§7").addLore("§fLimit: §7(§9" + zielony_ile + "§7/§9" + zielony_limit + "§7)").addLore("§fOsoby: §9" + lista_zielony).addLore("§7");
        inventory.setItem(14, slot14.build());
        ItemBuilder slot15 = (new ItemBuilder(Material.ORANGE_CONCRETE, 1)).setTitle("§6§lTEAM POMARANCZOWY").addLore("§7").addLore("§fLimit: §7(§9" + pomaranczowy_ile + "§7/§9" + pomaranczowy_limit + "§7)").addLore("§fOsoby: §9" + lista_pomaranczowy).addLore("§7");
        inventory.setItem(15, slot15.build());
        ItemBuilder slot16 = (new ItemBuilder(Material.BLUE_CONCRETE, 1)).setTitle("§9§lTEAM NIEBIESKI").addLore("§7").addLore("§fLimit: §7(§9" + niebieski_ile + "§7/§9" + niebieski_limit + "§7)").addLore("§fOsoby: §9" + lista_niebieski).addLore("§7");
        inventory.setItem(16, slot16.build());

        ItemBuilder backguard = (new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, 1)).setTitle("§7");
        inventory.setItem(0, backguard.build());
        inventory.setItem(1, backguard.build());
        inventory.setItem(2, backguard.build());
        inventory.setItem(3, backguard.build());
        inventory.setItem(4, backguard.build());
        inventory.setItem(5, backguard.build());
        inventory.setItem(6, backguard.build());
        inventory.setItem(7, backguard.build());
        inventory.setItem(8, backguard.build());
        inventory.setItem(9, backguard.build());
        inventory.setItem(17, backguard.build());
        inventory.setItem(18, backguard.build());
        inventory.setItem(19, backguard.build());
        inventory.setItem(20, backguard.build());
        inventory.setItem(21, backguard.build());
        inventory.setItem(22, backguard.build());
        inventory.setItem(23, backguard.build());
        inventory.setItem(24, backguard.build());
        inventory.setItem(25, backguard.build());
        inventory.setItem(26, backguard.build());
        return p.openInventory(inventory);
    }

    @EventHandler
    public void onOpenMenu(InventoryClickEvent e) throws IOException {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals("§9§lTEAMY")) {
            if (e.getRawSlot() == -999) p.closeInventory();
            if (e.getRawSlot() < e.getInventory().getSize()) {
                e.setCancelled(true);
            }
        }
    }

}
