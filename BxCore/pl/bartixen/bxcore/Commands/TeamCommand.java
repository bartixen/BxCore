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
import org.bukkit.inventory.ItemStack;
import pl.bartixen.bxcore.Data.TeamDataManager;
import pl.bartixen.bxcore.Main;
import pl.bartixen.bxcore.Utils.ItemBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TeamCommand implements CommandExecutor, Listener {

    static Main plugin;

    static TeamDataManager teamd;

    public TeamCommand(Main m) throws IOException {
        plugin = m;
        teamd = TeamDataManager.getInstance();
        m.getCommand("team").setExecutor(this);
        m.getCommand("druzyna").setExecutor(this);
        if ((teamd.getData().getString("czerwony.osoby")) == null) {
            teamd.getData().set("czerwony.osoby", null);
            teamd.getData().set("czerwony.limit", plugin.getConfig().getInt("teamy.darmo_sloty"));
            teamd.saveData();
        }
        if ((teamd.getData().getString("jasnoniebieski.limit")) == null) {
            teamd.getData().set("jasnoniebieski.osoby", null);
            teamd.getData().set("jasnoniebieski.limit", plugin.getConfig().getInt("teamy.darmo_sloty"));
            teamd.saveData();
        }
        if ((teamd.getData().getString("zolty.limit")) == null) {
            teamd.getData().set("zolty.osoby", null);
            teamd.getData().set("zolty.limit", plugin.getConfig().getInt("teamy.darmo_sloty"));
            teamd.saveData();
        }
        if ((teamd.getData().getString("rozowy.limit")) == null) {
            teamd.getData().set("rozowy.osoby", null);
            teamd.getData().set("rozowy.limit", plugin.getConfig().getInt("teamy.darmo_sloty"));
            teamd.saveData();
        }
        if ((teamd.getData().getString("zielony.limit")) == null) {
            teamd.getData().set("zielony.osoby", null);
            teamd.getData().set("zielony.limit", plugin.getConfig().getInt("teamy.darmo_sloty"));
            teamd.saveData();
        }
        if ((teamd.getData().getString("pomaranczowy.limit")) == null) {
            teamd.getData().set("pomaranczowy.osoby", null);
            teamd.getData().set("pomaranczowy.limit", plugin.getConfig().getInt("teamy.darmo_sloty"));
            teamd.saveData();
        }
        if ((teamd.getData().getString("niebieski.limit")) == null) {
            teamd.getData().set("niebieski.osoby", null);
            teamd.getData().set("niebieski.limit", plugin.getConfig().getInt("teamy.darmo_sloty"));
            teamd.saveData();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cTa komenda jest przeznaczona tylko dla graczy");
            return false;
        }
        Player p = (Player) sender;
        try {
            String team = teamd.getData().getString(p.getDisplayName() + ".team");
            if (team == null) {
                team_brak(p);
            } else {
                team(p, team);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static InventoryView team(Player p, String kolor_teamu) throws IOException {
        int ile_osob = teamd.getData().getList(kolor_teamu + ".osoby").size();
        List lista_osob = teamd.getData().getStringList(kolor_teamu + ".osoby");
        int limit_teamu = teamd.getData().getInt(kolor_teamu + ".limit");
        int koszt_slot = plugin.getConfig().getInt("teamy.koszt_slot");

        Inventory inventory = Bukkit.createInventory((InventoryHolder) p, 27, ("§9§lZarzadzaj TEAM"));
        if (kolor_teamu.equals("czerwony")) {
            ItemBuilder slot13 = (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lTEAM CZERWONY").addLore("§7").addLore("§fLimit: §7(§9" + ile_osob + "§7/§9" + limit_teamu + "§7)").addLore("§fOsoby: §9" + lista_osob).addLore("§7");
            inventory.setItem(13, slot13.build());
        }
        if (kolor_teamu.equals("jasnoniebieski")) {
            ItemBuilder slot13 = (new ItemBuilder(Material.LIGHT_BLUE_CONCRETE, 1)).setTitle("§b§lTEAM JASNO NIEBIESKI").addLore("§7").addLore("§fLimit: §7(§9" + ile_osob + "§7/§9" + limit_teamu + "§7)").addLore("§fOsoby: §9" + lista_osob).addLore("§7");
            inventory.setItem(13, slot13.build());
        }
        if (kolor_teamu.equals("zolty")) {
            ItemBuilder slot13 = (new ItemBuilder(Material.YELLOW_CONCRETE, 1)).setTitle("§e§lTEAM ZOLTY").addLore("§7").addLore("§fLimit: §7(§9" + ile_osob + "§7/§9" + limit_teamu + "§7)").addLore("§fOsoby: §9" + lista_osob).addLore("§7");
            inventory.setItem(13, slot13.build());
        }
        if (kolor_teamu.equals("rozowy")) {
            ItemBuilder slot13 = (new ItemBuilder(Material.PINK_CONCRETE, 1)).setTitle("§d§lTEAM ROZOWY").addLore("§7").addLore("§fLimit: §7(§9" + ile_osob + "§7/§9" + limit_teamu + "§7)").addLore("§fOsoby: §9" + lista_osob).addLore("§7");
            inventory.setItem(13, slot13.build());
        }
        if (kolor_teamu.equals("zielony")) {
            ItemBuilder slot13 = (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lTEAM JASNO ZIELONY").addLore("§7").addLore("§fLimit: §7(§9" + ile_osob + "§7/§9" + limit_teamu + "§7)").addLore("§fOsoby: §9" + lista_osob).addLore("§7");
            inventory.setItem(13, slot13.build());
        }
        if (kolor_teamu.equals("pomaranczowy")) {
            ItemBuilder slot13 = (new ItemBuilder(Material.ORANGE_CONCRETE, 1)).setTitle("§6§lTEAM POMARANCZOWY").addLore("§7").addLore("§fLimit: §7(§9" + ile_osob + "§7/§9" + limit_teamu + "§7)").addLore("§fOsoby: §9" + lista_osob).addLore("§7");
            inventory.setItem(13, slot13.build());
        }
        if (kolor_teamu.equals("niebieski")) {
            ItemBuilder slot13 = (new ItemBuilder(Material.BLUE_CONCRETE, 1)).setTitle("§9§lTEAM NIEBIESKI").addLore("§7").addLore("§fLimit: §7(§9" + ile_osob + "§7/§9" + limit_teamu + "§7)").addLore("§fOsoby: §9" + lista_osob).addLore("§7");
            inventory.setItem(13, slot13.build());
        }

        ItemBuilder slot18 = (new ItemBuilder(Material.CHEST, 1)).setTitle("§e§lZwieksz sloty").addLore("§7").addLore("§fKliknij aby kupić slot").addLore("§7").addLore("§fObecny limit: §9" + limit_teamu).addLore("§7").addLore("§cKoszt jednego slotu to " + koszt_slot + " diax").addLore("§7");
        inventory.setItem(18, slot18.build());
        ItemBuilder slot26 = (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lOpuść TEAM");
        inventory.setItem(26, slot26.build());

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
        inventory.setItem(10, backguard.build());
        inventory.setItem(11, backguard.build());
        inventory.setItem(12, backguard.build());
        inventory.setItem(14, backguard.build());
        inventory.setItem(15, backguard.build());
        inventory.setItem(16, backguard.build());
        inventory.setItem(17, backguard.build());
        inventory.setItem(19, backguard.build());
        inventory.setItem(20, backguard.build());
        inventory.setItem(21, backguard.build());
        inventory.setItem(22, backguard.build());
        inventory.setItem(23, backguard.build());
        inventory.setItem(24, backguard.build());
        inventory.setItem(25, backguard.build());
        return p.openInventory(inventory);
    }

    public static InventoryView team_brak(Player p) throws IOException {
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

        Inventory inventory = Bukkit.createInventory((InventoryHolder) p, 27, ("§9§lWybierz TEAM"));
        ItemBuilder slot10 = (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lTEAM CZERWONY").addLore("§7").addLore("§fKliknij aby dolaczyć").addLore("§7").addLore("§fLimit: §7(§9" + czerwoni_ile + "§7/§9" + czerwoni_limit + "§7)").addLore("§fOsoby: §9" + lista_czerwoni).addLore("§7");
        inventory.setItem(10, slot10.build());
        ItemBuilder slot11 = (new ItemBuilder(Material.LIGHT_BLUE_CONCRETE, 1)).setTitle("§b§lTEAM JASNO NIEBIESKI").addLore("§7").addLore("§fKliknij aby dolaczyć").addLore("§7").addLore("§fLimit: §7(§9" + jasnoniebieski_ile + "§7/§9" + jasnoniebieski_limit + "§7)").addLore("§fOsoby: §9" + lista_jasnoniebieski).addLore("§7");
        inventory.setItem(11, slot11.build());
        ItemBuilder slot12 = (new ItemBuilder(Material.YELLOW_CONCRETE, 1)).setTitle("§e§lTEAM ZOLTY").addLore("§7").addLore("§fKliknij aby dolaczyć").addLore("§7").addLore("§fLimit: §7(§9" + zolty_ile + "§7/§9" + zolty_limit + "§7)").addLore("§fOsoby: §9" + lista_zolty).addLore("§7");
        inventory.setItem(12, slot12.build());
        ItemBuilder slot13 = (new ItemBuilder(Material.PINK_CONCRETE, 1)).setTitle("§d§lTEAM ROZOWY").addLore("§7").addLore("§fKliknij aby dolaczyć").addLore("§7").addLore("§fLimit: §7(§9" + rozowy_ile + "§7/§9" + rozowy_limit + "§7)").addLore("§fOsoby: §9" + lista_rozowy).addLore("§7");
        inventory.setItem(13, slot13.build());
        ItemBuilder slot14 = (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lTEAM JASNO ZIELONY").addLore("§7").addLore("§fKliknij aby dolaczyć").addLore("§7").addLore("§fLimit: §7(§9" + zielony_ile + "§7/§9" + zielony_limit + "§7)").addLore("§fOsoby: §9" + lista_zielony).addLore("§7");
        inventory.setItem(14, slot14.build());
        ItemBuilder slot15 = (new ItemBuilder(Material.ORANGE_CONCRETE, 1)).setTitle("§6§lTEAM POMARANCZOWY").addLore("§7").addLore("§fKliknij aby dolaczyć").addLore("§7").addLore("§fLimit: §7(§9" + pomaranczowy_ile + "§7/§9" + pomaranczowy_limit + "§7)").addLore("§fOsoby: §9" + lista_pomaranczowy).addLore("§7");
        inventory.setItem(15, slot15.build());
        ItemBuilder slot16 = (new ItemBuilder(Material.BLUE_CONCRETE, 1)).setTitle("§9§lTEAM NIEBIESKI").addLore("§7").addLore("§fKliknij aby dolaczyć").addLore("§7").addLore("§fLimit: §7(§9" + niebieski_ile + "§7/§9" + niebieski_limit + "§7)").addLore("§fOsoby: §9" + lista_niebieski).addLore("§7");
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
        if (e.getView().getTitle().equals("§9§lWybierz TEAM")) {
            if (e.getRawSlot() == -999) p.closeInventory();
            if (e.getRawSlot() < e.getInventory().getSize()) {
                e.setCancelled(true);
                if (e.getRawSlot() == 10) {
                    p.closeInventory();
                    jointeam(p, "czerwony", "§c§lTEAM CZERWONY");
                }
                if (e.getRawSlot() == 11) {
                    p.closeInventory();
                    jointeam(p, "jasnoniebieski", "§b§lTEAM JASNO NIEBIESKI");
                }
                if (e.getRawSlot() == 12) {
                    p.closeInventory();
                    jointeam(p, "zolty", "§e§lTEAM ZOLTY");
                }
                if (e.getRawSlot() == 13) {
                    p.closeInventory();
                    jointeam(p, "rozowy", "§d§lTEAM ROZOWY");
                }
                if (e.getRawSlot() == 14) {
                    p.closeInventory();
                    jointeam(p, "zielony", "§a§lTEAM JASNO ZIELONY");
                }
                if (e.getRawSlot() == 15) {
                    p.closeInventory();
                    jointeam(p, "pomaranczowy", "§6§lTEAM POMARANCZOWY");
                }
                if (e.getRawSlot() == 16) {
                    p.closeInventory();
                    jointeam(p, "niebieski", "§9§lTEAM NIEBIESKI");
                }
            }
        }
        if (e.getView().getTitle().equals("§9§lZarzadzaj TEAM")) {
            if (e.getRawSlot() == -999) p.closeInventory();
            if (e.getRawSlot() < e.getInventory().getSize()) {
                e.setCancelled(true);
                if (e.getRawSlot() == 26) {
                    p.closeInventory();
                    String team = teamd.getData().getString(p.getDisplayName() + ".team");
                    ArrayList<String> osoby = new ArrayList<>();
                    osoby = new ArrayList<>(teamd.getData().getStringList(team + ".osoby"));
                    osoby.remove(p.getDisplayName());
                    teamd.getData().set(team + ".osoby", osoby);
                    teamd.getData().set(p.getDisplayName() + ".team", null);
                    teamd.saveData();
                    int ile_osob = teamd.getData().getList(team + ".osoby").size();
                    if (ile_osob == 0) {
                        teamd.getData().set(team + ".osoby", null);
                        teamd.saveData();
                    }
                    p.sendMessage("§7Pomyślnie opuszczono team");
                    String nazwa_team = "brak";
                    if (team.equals("czerwony")) {
                        nazwa_team = "§c§lTEAM CZERWONY";
                    }
                    if (team.equals("jasnoniebieski")) {
                        nazwa_team = "§b§lTEAM JASNO NIEBIESKI";
                    }
                    if (team.equals("zolty")) {
                        nazwa_team = "§e§lTEAM ZOLTY";
                    }
                    if (team.equals("rozowy")) {
                        nazwa_team = "§d§lTEAM ROZOWY";
                    }
                    if (team.equals("zielony")) {
                        nazwa_team = "§a§lTEAM JASNO ZIELONY";
                    }
                    if (team.equals("pomaranczowy")) {
                        nazwa_team = "§6§lTEAM POMARANCZOWY";
                    }
                    if (team.equals("niebieski")) {
                        nazwa_team = "§9§lTEAM NIEBIESKI";
                    }
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.sendMessage("§fGracz §9" + p.getName() + " §fopuścil " + nazwa_team);
                    }
                }
                if (e.getRawSlot() == 18) {
                    p.closeInventory();
                    int koszt = plugin.getConfig().getInt("teamy.koszt_slot");
                    if (p.getInventory().contains(Material.DIAMOND, koszt)) {
                        String team = teamd.getData().getString(p.getDisplayName() + ".team");
                        removeItems((Inventory) p.getInventory(), Material.DIAMOND, koszt);
                        teamd.getData().set(team + ".limit", teamd.getData().getInt(team + ".limit") + 1);
                        teamd.saveData();
                        p.sendMessage("§7Pomyślnie dodano slot do teamu");
                    } else {
                        p.sendMessage("§7Aby dodać slot musisz posiadać §9" + koszt + " diax");
                    }
                }
            }
        }
    }

    public static void jointeam(Player p, String team, String nazwa_team) throws IOException {
        if ((teamd.getData().getString(p.getDisplayName() + ".zmiany")) == null) {
            teamd.getData().set(p.getDisplayName() + ".zmiany", 1);
            teamd.getData().set(p.getDisplayName() + ".team", team);
            ArrayList<String> osoby = new ArrayList<>();
            osoby = new ArrayList<>(teamd.getData().getStringList(team + ".osoby"));
            osoby.add(p.getDisplayName());
            teamd.getData().set(team + ".osoby", osoby);
            teamd.saveData();
            p.sendMessage("§7Pomyślnie dolaczono do teamu");
            for (Player players : Bukkit.getOnlinePlayers()) {
                players.sendMessage("§fGracz §9" + p.getName() + " §fdolaczyl do " + nazwa_team);
            }
        } else {
            int zmiany = teamd.getData().getInt(p.getDisplayName() + ".zmiany");
            int darmowe = plugin.getConfig().getInt("teamy.darmo_zmian");
            int koszt = plugin.getConfig().getInt("teamy.koszt_zmiany");
            if (zmiany < darmowe) {
                teamd.getData().set(p.getDisplayName() + ".zmiany", teamd.getData().getInt(p.getDisplayName() + ".zmiany") + 1);
                teamd.getData().set(p.getDisplayName() + ".team", team);
                ArrayList<String> osoby = new ArrayList<>();
                osoby = new ArrayList<>(teamd.getData().getStringList(team + ".osoby"));
                osoby.add(p.getDisplayName());
                teamd.getData().set(team + ".osoby", osoby);
                teamd.saveData();
                p.sendMessage("§7Pomyślnie dolaczono do teamu");
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage("§fGracz §9" + p.getName() + " §fdolaczyl do " + nazwa_team);
                }
            } else {
                if (p.getInventory().contains(Material.DIAMOND, koszt)) {
                    removeItems((Inventory) p.getInventory(), Material.DIAMOND, koszt);
                    teamd.getData().set(p.getDisplayName() + ".zmiany", teamd.getData().getInt(p.getDisplayName() + ".zmiany") + 1);
                    teamd.getData().set(p.getDisplayName() + ".team", team);
                    ArrayList<String> osoby = new ArrayList<>();
                    osoby = new ArrayList<>(teamd.getData().getStringList(team + ".osoby"));
                    osoby.add(p.getDisplayName());
                    teamd.getData().set(team + ".osoby", osoby);
                    teamd.saveData();
                    p.sendMessage("§7Pomyślnie dolaczono do teamu");
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.sendMessage("§fGracz §9" + p.getName() + " §fdolaczyl do " + nazwa_team);
                    }
                } else {
                    p.sendMessage("§7Aby dolaczyć do teamu należy mieć §9" + koszt + " diax");
                }
            }
        }
    }

    public static int removeItems(Inventory inventory, Material type, int amount) {
        if (type == null || inventory == null)
            return -1;
        if (amount <= 0)
            return -1;
        if (amount == Integer.MAX_VALUE) {
            inventory.remove(type);
            return 0;
        }
        HashMap<Integer, ItemStack> retVal = inventory.removeItem(new ItemStack[]{new ItemStack(type, amount)});
        int notRemoved = 0;
        for (ItemStack item : retVal.values())
            notRemoved += item.getAmount();
        return notRemoved;
    }
}