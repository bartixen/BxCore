package pl.bartixen.bxcore.Staty;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import pl.bartixen.bxcore.Data.StatyDataManager;
import pl.bartixen.bxcore.Data.UserDataManager;
import pl.bartixen.bxcore.Main;
import pl.bartixen.bxcore.Utils.ItemBuilder;

import java.util.*;

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
        Inventory inventory = Bukkit.createInventory(sender, 45, ("§9§lStatystyki gracza §0§l" + gracz));

        int postawione = statyd.getData().getInt(uuid + ".places");
        int wykopane = statyd.getData().getInt(uuid + ".breaks");
        int diamenty = statyd.getData().getInt(uuid + ".diamonds");
        int swierci = statyd.getData().getInt(uuid + ".deaths");
        int czas = statyd.getData().getInt(uuid + ".times");
        int noce = statyd.getData().getInt(uuid + ".sleeps");
        int smoki = statyd.getData().getInt(uuid + ".dragons");

        int godziny = czas / 60;
        int minuty = czas % 60;

        String death_top1 = "Brak", death_top2 = "Brak", death_top3 = "Brak", death_top4 = "Brak", death_top5 = "Brak";
        String time_top1 = "Brak", time_top2 = "Brak", time_top3 = "Brak", time_top4 = "Brak", time_top5 = "Brak";
        String break_top1 = "Brak", break_top2 = "Brak", break_top3 = "Brak", break_top4 = "Brak", break_top5 = "Brak";
        String place_top1 = "Brak", place_top2 = "Brak", place_top3 = "Brak", place_top4 = "Brak", place_top5 = "Brak";
        String sleep_top1 = "Brak", sleep_top2 = "Brak", sleep_top3 = "Brak", sleep_top4 = "Brak", sleep_top5 = "Brak";
        String diamond_top1 = "Brak", diamond_top2 = "Brak", diamond_top3 = "Brak", diamond_top4 = "Brak", diamond_top5 = "Brak";
        String dragon_top1 = "Brak", dragon_top2 = "Brak", dragon_top3 = "Brak", dragon_top4 = "Brak", dragon_top5 = "Brak";
        String update = "Brak";
        if (statyd.getData().getString("top.death.top1") != null) {
            death_top1 = statyd.getData().getString("top.death.top1");
            death_top2 = statyd.getData().getString("top.death.top2");
            death_top3 = statyd.getData().getString("top.death.top3");
            death_top4 = statyd.getData().getString("top.death.top4");
            death_top5 = statyd.getData().getString("top.death.top5");
            time_top1 = statyd.getData().getString("top.time.top1");
            time_top2 = statyd.getData().getString("top.time.top2");
            time_top3 = statyd.getData().getString("top.time.top3");
            time_top4 = statyd.getData().getString("top.time.top4");
            time_top5 = statyd.getData().getString("top.time.top5");
            break_top1 = statyd.getData().getString("top.break.top1");
            break_top2 = statyd.getData().getString("top.break.top2");
            break_top3 = statyd.getData().getString("top.break.top3");
            break_top4 = statyd.getData().getString("top.break.top4");
            break_top5 = statyd.getData().getString("top.break.top5");
            place_top1 = statyd.getData().getString("top.place.top1");
            place_top2 = statyd.getData().getString("top.place.top2");
            place_top3 = statyd.getData().getString("top.place.top3");
            place_top4 = statyd.getData().getString("top.place.top4");
            place_top5 = statyd.getData().getString("top.place.top5");
            sleep_top1 = statyd.getData().getString("top.sleep.top1");
            sleep_top2 = statyd.getData().getString("top.sleep.top2");
            sleep_top3 = statyd.getData().getString("top.sleep.top3");
            sleep_top4 = statyd.getData().getString("top.sleep.top4");
            sleep_top5 = statyd.getData().getString("top.sleep.top5");
            diamond_top1 = statyd.getData().getString("top.diamond.top1");
            diamond_top2 = statyd.getData().getString("top.diamond.top2");
            diamond_top3 = statyd.getData().getString("top.diamond.top3");
            diamond_top4 = statyd.getData().getString("top.diamond.top4");
            diamond_top5 = statyd.getData().getString("top.diamond.top5");
            dragon_top1 = statyd.getData().getString("top.dragon.top1");
            dragon_top2 = statyd.getData().getString("top.dragon.top2");
            dragon_top3 = statyd.getData().getString("top.dragon.top3");
            dragon_top4 = statyd.getData().getString("top.dragon.top4");
            dragon_top5 = statyd.getData().getString("top.dragon.top5");
            update = statyd.getData().getString("top.update");
        }


        ItemBuilder slot10 = (new ItemBuilder(Material.STONE, 1)).setTitle("§f§lPostawione bloki: §9§l" + postawione).addLore(" ").addLore("§7§lTOP 1:§e§l " + place_top1).addLore("§7TOP 2:§f " + place_top2).addLore("§7TOP 3:§f " + place_top3).addLore("§7TOP 4:§f " + place_top4).addLore("§7TOP 5:§f " + place_top5).addLore("").addLore("§7Statystyki są aktualizowane co 10 min").addLore("§7Ostatnia aktualizacja: §f" + update);
        inventory.setItem(10, slot10.build());
        ItemBuilder slot11 = (new ItemBuilder(Material.GOLDEN_PICKAXE, 1)).setTitle("§f§lWykopane bloki: §9§l" + wykopane).addLore(" ").addLore("§7§lTOP 1:§e§l " + break_top1).addLore("§7TOP 2:§f " + break_top2).addLore("§7TOP 3:§f " + break_top3).addLore("§7TOP 4:§f " + break_top4).addLore("§7TOP 5:§f " + break_top5).addLore("").addLore("§7Statystyki są aktualizowane co 10 min").addLore("§7Ostatnia aktualizacja: §f" + update);
        inventory.setItem(11, slot11.build());
        ItemBuilder slot12 = (new ItemBuilder(Material.DIAMOND, 1)).setTitle("§f§lWykopane diamenty: §9§l" + diamenty).addLore(" ").addLore("§7§lTOP 1:§e§l " + diamond_top1).addLore("§7TOP 2:§f " + diamond_top2).addLore("§7TOP 3:§f " + diamond_top3).addLore("§7TOP 4:§f " + diamond_top4).addLore("§7TOP 5:§f " + diamond_top5).addLore("").addLore("§7Statystyki są aktualizowane co 10 min").addLore("§7Ostatnia aktualizacja: §f" + update);
        inventory.setItem(12, slot12.build());
        ItemBuilder slot13 = (new ItemBuilder(Material.SKELETON_SKULL, 1)).setTitle("§f§lŚmierci: §9§l" + swierci).addLore(" ").addLore("§7§lTOP 1:§e§l " + death_top1).addLore("§7TOP 2:§f " + death_top2).addLore("§7TOP 3:§f " + death_top3).addLore("§7TOP 4:§f " + death_top4).addLore("§7TOP 5:§f " + death_top5).addLore("").addLore("§7Statystyki są aktualizowane co 10 min").addLore("§7Ostatnia aktualizacja: §f" + update);
        inventory.setItem(13, slot13.build());
        ItemBuilder slot14 = (new ItemBuilder(Material.CLOCK, 1)).setTitle("§f§lSpędzony czas: §9§l" + godziny + "h " + minuty + "min").addLore(" ").addLore("§7§lTOP 1:§e§l " + time_top1).addLore("§7TOP 2:§f " + time_top2).addLore("§7TOP 3:§f " + time_top3).addLore("§7TOP 4:§f " + time_top4).addLore("§7TOP 5:§f " + time_top5).addLore("").addLore("§7Statystyki są aktualizowane co 10 min").addLore("§7Ostatnia aktualizacja: §f" + update);
        inventory.setItem(14, slot14.build());
        ItemBuilder slot15 = (new ItemBuilder(Material.RED_BED, 1)).setTitle("§f§lPrzespane noce: §9§l" + noce).addLore(" ").addLore("§7§lTOP 1:§e§l " + sleep_top1).addLore("§7TOP 2:§f " + sleep_top2).addLore("§7TOP 3:§f " + sleep_top3).addLore("§7TOP 4:§f " + sleep_top4).addLore("§7TOP 5:§f " + sleep_top5).addLore("").addLore("§7Statystyki są aktualizowane co 10 min").addLore("§7Ostatnia aktualizacja: §f" + update);
        inventory.setItem(15, slot15.build());
        ItemBuilder slot16 = (new ItemBuilder(Material.DRAGON_HEAD, 1)).setTitle("§f§lZabitych smoków: §9§l" + smoki).addLore(" ").addLore("§7§lTOP 1:§e§l " + dragon_top1).addLore("§7TOP 2:§f " + dragon_top2).addLore("§7TOP 3:§f " + dragon_top3).addLore("§7TOP 4:§f " + dragon_top4).addLore("§7TOP 5:§f " + dragon_top5).addLore("").addLore("§7Statystyki są aktualizowane co 10 min").addLore("§7Ostatnia aktualizacja: §f" + update);
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
        inventory.setItem(27, backguard.build());

        ItemBuilder slot28 = (new ItemBuilder(Material.CHEST, 1)).setTitle("§f§lNAGRODY §9za postawione bloki").addLore(" ").addLore("§8==========================").addLore(" ").addLore("§7- §92 500 §fpostawionych bloków").addLore("§e\uD83C\uDF81 1x Zestaw Treakota").addLore(" ").addLore("§7- §95 000 §fpostawionych bloków").addLore("§e\uD83C\uDF81 6x Skrzynia").addLore(" ").addLore("§7- §910 000 §fpostawionych bloków").addLore("§e\uD83C\uDF81 1x Diamentowa Siekiera").addLore(" ").addLore("§7- §915 000 §fpostawionych bloków").addLore("§e\uD83C\uDF81 13x Obsydian").addLore(" ").addLore("§7- §925 000 §fpostawionych bloków").addLore("§e\uD83C\uDF81 16x Diament").addLore(" ").addLore("§8==========================").addLore(" ").addLore("§fKliknij aby przejść do §9/nagrody");
        inventory.setItem(28, slot28.build());

        ItemBuilder slot29 = (new ItemBuilder(Material.CHEST, 1)).setTitle("§f§lNAGRODY §9za wykopane bloki").addLore(" ").addLore("§8==========================").addLore(" ").addLore("§7- §95 000 §fwykopanych bloków").addLore("§e\uD83C\uDF81 1x Kowadło").addLore(" ").addLore("§7- §915 000 §fwykopanych bloków").addLore("§e\uD83C\uDF81 1x Diamentowy Kilof").addLore(" ").addLore("§7- §925 000 §fwykopanych bloków").addLore("§e\uD83C\uDF81 1x Książka Mending").addLore(" ").addLore("§7- §950 000 §fwykopanych bloków").addLore("§e\uD83C\uDF81 1x Efekt Haste II na 15 min").addLore(" ").addLore("§7- §9100 000 §fwykopanych bloków").addLore("§e\uD83C\uDF81 1x Łuk z Skinem AK-47").addLore(" ").addLore("§8==========================").addLore(" ").addLore("§fKliknij aby przejść do §9/nagrody");
        inventory.setItem(29, slot29.build());

        ItemBuilder slot30 = (new ItemBuilder(Material.CHEST, 1)).setTitle("§f§lNAGRODY §9za wykopane diaxy").addLore(" ").addLore("§8==========================").addLore(" ").addLore("§7- §916 §fwykopanych diaxów").addLore("§e\uD83C\uDF81 1x Diamentowy Miecz").addLore(" ").addLore("§7- §932 §fwykopanych diaxów").addLore("§e\uD83C\uDF81 3x Oko Endu").addLore(" ").addLore("§7- §964 §fwykopanych diaxów").addLore("§e\uD83C\uDF81 1x Sztabka Netherytu").addLore(" ").addLore("§7- §9128 §fwykopanych diaxów").addLore("§e\uD83C\uDF81 1x Totem Nieśmiertelności").addLore(" ").addLore("§7- §9256 §fwykopanych diaxów").addLore("§e\uD83C\uDF81 1x Miecz z Netherytu z Skinem Skull Cutlass").addLore(" ").addLore("§8==========================").addLore(" ").addLore("§fKliknij aby przejść do §9/nagrody");
        inventory.setItem(30, slot30.build());

        ItemBuilder slot31 = (new ItemBuilder(Material.CHEST, 1)).setTitle("§f§lNAGRODY §9za śmierci").addLore(" ").addLore("§8==========================").addLore(" ").addLore("§7- §910 §fśmierci").addLore("§e\uD83C\uDF81 1x Tarcza").addLore(" ").addLore("§7- §925 §fśmierci").addLore("§e\uD83C\uDF81 1x Skórzana Zbroja").addLore(" ").addLore("§7- §950 §fśmierci").addLore("§e\uD83C\uDF81 1x Łuk").addLore(" ").addLore("§7- §9100 §fśmierci").addLore("§e\uD83C\uDF81 1x Kamienny Miecz").addLore(" ").addLore("§7- §9250 §fśmierci").addLore("§e\uD83C\uDF81 1x Twoja Główka").addLore(" ").addLore("§8==========================").addLore(" ").addLore("§fKliknij aby przejść do §9/nagrody");
        inventory.setItem(31, slot31.build());

        ItemBuilder slot32 = (new ItemBuilder(Material.CHEST, 1)).setTitle("§f§lNAGRODY §9za spędzony czas").addLore(" ").addLore("§8==========================").addLore(" ").addLore("§7- §92h §faktywnej gry").addLore("§e\uD83C\uDF81 1x Łóżko").addLore(" ").addLore("§7- §910h §faktywnej gry").addLore("§e\uD83C\uDF81 1x Mikstura Szybkość").addLore(" ").addLore("§7- §925h §faktywnej gry").addLore("§e\uD83C\uDF81 Dostęp do 1 /home").addLore(" ").addLore("§7- §950h §faktywnej gry").addLore("§e\uD83C\uDF81 Dostęp do /tpa").addLore(" ").addLore("§7- §9100h §faktywnej gry").addLore("§e\uD83C\uDF81 1x Jajko Ocelota").addLore(" ").addLore("§8==========================").addLore(" ").addLore("§fKliknij aby przejść do §9/nagrody");
        inventory.setItem(32, slot32.build());

        ItemBuilder slot33 = (new ItemBuilder(Material.CHEST, 1)).setTitle("§f§lNAGRODY §9za przespane noce").addLore(" ").addLore("§8==========================").addLore(" ").addLore("§7- §925 §fprzespanych nocy").addLore("§e\uD83C\uDF81 32x Magiczna Lampa Oświetleniowa").addLore(" ").addLore("§7- §950 §fprzespanych nocy").addLore("§e\uD83C\uDF81 1x Znacznik").addLore(" ").addLore("§7- §9100 §fprzespanych nocy").addLore("§e\uD83C\uDF81 1x Siodło").addLore(" ").addLore("§7- §9150 §fprzespanych nocy").addLore("§e\uD83C\uDF81 32x Butelka Expa").addLore(" ").addLore("§7- §9250 §fprzespanych nocy").addLore("§e\uD83C\uDF81 1x Głowa Creepera").addLore(" ").addLore("§8==========================").addLore(" ").addLore("§fKliknij aby przejść do §9/nagrody");
        inventory.setItem(33, slot33.build());

        ItemBuilder slot34 = (new ItemBuilder(Material.CHEST, 1)).setTitle("§f§lNAGRODY §9za zabicie smoków").addLore(" ").addLore("§8==========================").addLore(" ").addLore("§7- §92 §fzabite smoki").addLore("§e\uD83C\uDF81 1x Totem Nieśmiertelności").addLore(" ").addLore("§7- §95 §fzabitych smoków").addLore("§e\uD83C\uDF81 1x Skulkerowa Skrzynia").addLore(" ").addLore("§7- §910 §fzabitych smoków").addLore("§e\uD83C\uDF81 128x Fajerwerek (moc 3)").addLore(" ").addLore("§7- §915 §fzabitych smoków").addLore("§e\uD83C\uDF81 1x Diamentowa Zbroja Końska").addLore(" ").addLore("§7- §925 §fzabitych smoków").addLore("§e\uD83C\uDF81 1x Elytra z Skinem Enderman Minecon").addLore(" ").addLore("§8==========================").addLore(" ").addLore("§fKliknij aby przejść do §9/nagrody");
        inventory.setItem(34, slot34.build());

        inventory.setItem(35, backguard.build());
        inventory.setItem(36, backguard.build());
        inventory.setItem(37, backguard.build());
        inventory.setItem(38, backguard.build());
        inventory.setItem(39, backguard.build());
        inventory.setItem(40, backguard.build());
        inventory.setItem(41, backguard.build());
        inventory.setItem(42, backguard.build());
        inventory.setItem(43, backguard.build());
        inventory.setItem(44, backguard.build());

        return sender.openInventory(inventory);
    }

}
