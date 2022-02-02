package pl.bartixen.bxcore.Staty;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import pl.bartixen.bxcore.Data.StatyDataManager;
import pl.bartixen.bxcore.Data.UserDataManager;
import pl.bartixen.bxcore.Main;
import pl.bartixen.bxcore.Utils.ItemBuilder;

public class StatyCommand implements CommandExecutor {

    Main plugin;

    static StatyDataManager statyd;

    static UserDataManager userd;

    public StatyCommand(Main m) {
        plugin = m;
        m.getCommand("staty").setExecutor(this);
        m.getCommand("statystyki").setExecutor(this);
        m.getCommand("top").setExecutor(this);
        m.getCommand("topki").setExecutor(this);
        statyd = StatyDataManager.getInstance();
        userd = UserDataManager.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player dowyslania = (Player) sender;
        if (args.length == 1) {
            Player p = Bukkit.getPlayerExact(args[0]);
            if ((userd.getData().getString(args[0])) == null) {
                sender.sendMessage("§7Brak danych o graczu §9" + args[0]);
                return false;
            }
            String uuid = userd.getData().getString(args[0] + ".uuid");
            this.staty(uuid, dowyslania, args[0]);
        } else {
            Player p = (Player)sender;
            String uuid = String.valueOf(p.getUniqueId());
            this.staty(uuid, dowyslania, p.getDisplayName());
        }
        return false;
    }

    public InventoryView staty(String uuid, Player sender, String gracz) {
        Inventory inventory = Bukkit.createInventory(sender, 27, ("§9§lStatystyki gracza §9§l§o" + gracz));

        int postawione = statyd.getData().getInt(uuid + ".places");
        int wykopane = statyd.getData().getInt(uuid + ".breaks");
        int diamenty = statyd.getData().getInt(uuid + ".diamonds");
        int swierci = statyd.getData().getInt(uuid + ".deaths");
        int czas = statyd.getData().getInt(uuid + ".times");
        int noce = statyd.getData().getInt(uuid + ".sleeps");
        int smoki = statyd.getData().getInt(uuid + ".dragons");

        int godziny = czas / 60;
        int minuty = czas % 60;

        ItemBuilder slot10 = (new ItemBuilder(Material.STONE, 1)).setTitle("§f§lPostawione bloki: §9§l" + postawione);
        inventory.setItem(10, slot10.build());
        ItemBuilder slot11 = (new ItemBuilder(Material.GOLDEN_PICKAXE, 1)).setTitle("§f§lWykopane bloki: §9§l" + wykopane);
        inventory.setItem(11, slot11.build());
        ItemBuilder slot12 = (new ItemBuilder(Material.DIAMOND, 1)).setTitle("§f§lWykopane diamenty: §9§l" + diamenty);
        inventory.setItem(12, slot12.build());
        ItemBuilder slot13 = (new ItemBuilder(Material.SKELETON_SKULL, 1)).setTitle("§f§lŚmierci: §9§l" + swierci);
        inventory.setItem(13, slot13.build());
        ItemBuilder slot14 = (new ItemBuilder(Material.CLOCK, 1)).setTitle("§f§lSpędzony czas: §9§l" + godziny + "h " + minuty + "min");
        inventory.setItem(14, slot14.build());
        ItemBuilder slot15 = (new ItemBuilder(Material.RED_BED, 1)).setTitle("§f§lPrzespane noce: §9§l" + noce);
        inventory.setItem(15, slot15.build());
        ItemBuilder slot16 = (new ItemBuilder(Material.DRAGON_HEAD, 1)).setTitle("§f§lZabitych smoków: §9§l" + smoki);
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
        return sender.openInventory(inventory);
    }

}
