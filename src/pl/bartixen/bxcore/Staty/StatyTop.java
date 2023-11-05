package pl.bartixen.bxcore.Staty;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import pl.bartixen.bxcore.Data.StatyDataManager;
import pl.bartixen.bxcore.Main;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StatyTop extends BukkitRunnable {

    Main plugin;

    static StatyDataManager statyd;

    public StatyTop(Main m) {
        plugin = m;
        statyd = StatyDataManager.getInstance();
    }

    @Override
    public void run() {

        String death_top1 = "Brak", death_top2 = "Brak", death_top3 = "Brak", death_top4 = "Brak", death_top5 = "Brak";
        String time_top1 = "Brak", time_top2 = "Brak", time_top3 = "Brak", time_top4 = "Brak", time_top5 = "Brak";
        String break_top1 = "Brak", break_top2 = "Brak", break_top3 = "Brak", break_top4 = "Brak", break_top5 = "Brak";
        String place_top1 = "Brak", place_top2 = "Brak", place_top3 = "Brak", place_top4 = "Brak", place_top5 = "Brak";
        String sleep_top1 = "Brak", sleep_top2 = "Brak", sleep_top3 = "Brak", sleep_top4 = "Brak", sleep_top5 = "Brak";
        String diamond_top1 = "Brak", diamond_top2 = "Brak", diamond_top3 = "Brak", diamond_top4 = "Brak", diamond_top5 = "Brak";
        String dragon_top1 = "Brak", dragon_top2 = "Brak", dragon_top3 = "Brak", dragon_top4 = "Brak", dragon_top5 = "Brak";

        List<String> uuidList = new ArrayList<>();
        ConfigurationSection dataSection = statyd.getData();

        if (dataSection.isList("list.uuid")) {
            List<String> uuids = dataSection.getStringList("list.uuid");
            uuidList.addAll(uuids);
        }

        Map<String, Integer> deathsMap = new HashMap<>();
        for (String key : uuidList) {
            int count = statyd.getData().getInt(key + ".deaths");
            String nick = statyd.getData().getString(key + ".nick");
            deathsMap.put(nick, count);
        }

        Map<String, Integer> timesMap = new HashMap<>();
        for (String key : uuidList) {
            int count = statyd.getData().getInt(key + ".times");
            String nick = statyd.getData().getString(key + ".nick");
            timesMap.put(nick, count);
        }

        Map<String, Integer> breaksMap = new HashMap<>();
        for (String key : uuidList) {
            int count = statyd.getData().getInt(key + ".breaks");
            String nick = statyd.getData().getString(key + ".nick");
            breaksMap.put(nick, count);
        }

        Map<String, Integer> sleepsMap = new HashMap<>();
        for (String key : uuidList) {
            int count = statyd.getData().getInt(key + ".sleeps");
            String nick = statyd.getData().getString(key + ".nick");
            sleepsMap.put(nick, count);
        }

        Map<String, Integer> placesMap = new HashMap<>();
        for (String key : uuidList) {
            int count = statyd.getData().getInt(key + ".places");
            String nick = statyd.getData().getString(key + ".nick");
            placesMap.put(nick, count);
        }

        Map<String, Integer> diamondMap = new HashMap<>();
        for (String key : uuidList) {
            int count = statyd.getData().getInt(key + ".diamonds");
            String nick = statyd.getData().getString(key + ".nick");
            diamondMap.put(nick, count);
        }

        Map<String, Integer> dragonsMap = new HashMap<>();
        for (String key : uuidList) {
            int count = statyd.getData().getInt(key + ".dragons");
            String nick = statyd.getData().getString(key + ".nick");
            dragonsMap.put(nick, count);
        }

        List<Map.Entry<String, Integer>> listDeath = new LinkedList<>(deathsMap.entrySet());
        listDeath.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        List<Map.Entry<String, Integer>> listSleep = new LinkedList<>(sleepsMap.entrySet());
        listSleep.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        List<Map.Entry<String, Integer>> listPlace = new LinkedList<>(placesMap.entrySet());
        listPlace.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        List<Map.Entry<String, Integer>> listBreak = new LinkedList<>(breaksMap.entrySet());
        listBreak.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        List<Map.Entry<String, Integer>> listTime = new LinkedList<>(timesMap.entrySet());
        listTime.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        List<Map.Entry<String, Integer>> listDiamonds = new LinkedList<>(diamondMap.entrySet());
        listDiamonds.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        List<Map.Entry<String, Integer>> listDragons = new LinkedList<>(dragonsMap.entrySet());
        listDragons.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for (int i = 0; i < 5 && i < listDragons.size(); i++) {
            switch (i) {
                case 0:
                    dragon_top1 = listDragons.get(i).getKey() + "§7§l -> §e§l" + listDragons.get(i).getValue();
                    break;
                case 1:
                    dragon_top2 = listDragons.get(i).getKey() + "§7 -> §9" + listDragons.get(i).getValue();
                    break;
                case 2:
                    dragon_top3 = listDragons.get(i).getKey() + "§7 -> §9" + listDragons.get(i).getValue();
                    break;
                case 3:
                    dragon_top4 = listDragons.get(i).getKey() + "§7 -> §9" + listDragons.get(i).getValue();
                    break;
                case 4:
                    dragon_top5 = listDragons.get(i).getKey() + "§7 -> §9" + listDragons.get(i).getValue();
                    break;
                default:
                    break;
            }
        }

        for (int i = 0; i < 5 && i < listDeath.size(); i++) {
            switch (i) {
                case 0:
                    death_top1 = listDeath.get(i).getKey() + "§7§l -> §e§l" + listDeath.get(i).getValue();
                    break;
                case 1:
                    death_top2 = listDeath.get(i).getKey() + "§7 -> §9" + listDeath.get(i).getValue();
                    break;
                case 2:
                    death_top3 = listDeath.get(i).getKey() + "§7 -> §9" + listDeath.get(i).getValue();
                    break;
                case 3:
                    death_top4 = listDeath.get(i).getKey() + "§7 -> §9" + listDeath.get(i).getValue();
                    break;
                case 4:
                    death_top5 = listDeath.get(i).getKey() + "§7 -> §9" + listDeath.get(i).getValue();
                    break;
                default:
                    break;
            }
        }

        for (int i = 0; i < 5 && i < listSleep.size(); i++) {
            switch (i) {
                case 0:
                    sleep_top1 = listSleep.get(i).getKey() + "§7§l -> §e§l" + listSleep.get(i).getValue();
                    break;
                case 1:
                    sleep_top2 = listSleep.get(i).getKey() + "§7 -> §9" + listSleep.get(i).getValue();
                    break;
                case 2:
                    sleep_top3 = listSleep.get(i).getKey() + "§7 -> §9" + listSleep.get(i).getValue();
                    break;
                case 3:
                    sleep_top4 = listSleep.get(i).getKey() + "§7 -> §9" + listSleep.get(i).getValue();
                    break;
                case 4:
                    sleep_top5 = listSleep.get(i).getKey() + "§7 -> §9" + listSleep.get(i).getValue();
                    break;
                default:
                    break;
            }
        }

        for (int i = 0; i < 5 && i < listPlace.size(); i++) {
            switch (i) {
                case 0:
                    place_top1 = listPlace.get(i).getKey() + "§7§l -> §e§l" + listPlace.get(i).getValue();
                    break;
                case 1:
                    place_top2 = listPlace.get(i).getKey() + "§7 -> §9" + listPlace.get(i).getValue();
                    break;
                case 2:
                    place_top3 = listPlace.get(i).getKey() + "§7 -> §9" + listPlace.get(i).getValue();
                    break;
                case 3:
                    place_top4 = listPlace.get(i).getKey() + "§7 -> §9" + listPlace.get(i).getValue();
                    break;
                case 4:
                    place_top5 = listPlace.get(i).getKey() + "§7 -> §9" + listPlace.get(i).getValue();
                    break;
                default:
                    break;
            }
        }

        for (int i = 0; i < 5 && i < listBreak.size(); i++) {
            switch (i) {
                case 0:
                    break_top1 = listBreak.get(i).getKey() + "§7§l -> §e§l" + listBreak.get(i).getValue();
                    break;
                case 1:
                    break_top2 = listBreak.get(i).getKey() + "§7 -> §9" + listBreak.get(i).getValue();
                    break;
                case 2:
                    break_top3 = listBreak.get(i).getKey() + "§7 -> §9" + listBreak.get(i).getValue();
                    break;
                case 3:
                    break_top4 = listBreak.get(i).getKey() + "§7 -> §9" + listBreak.get(i).getValue();
                    break;
                case 4:
                    break_top5 = listBreak.get(i).getKey() + "§7 -> §9" + listBreak.get(i).getValue();
                    break;
                default:
                    break;
            }
        }

        for (int i = 0; i < 5 && i < listDiamonds.size(); i++) {
            switch (i) {
                case 0:
                    diamond_top1 = listDiamonds.get(i).getKey() + "§7§l -> §e§l" + listDiamonds.get(i).getValue();
                    break;
                case 1:
                    diamond_top2 = listDiamonds.get(i).getKey() + "§7 -> §9" + listDiamonds.get(i).getValue();
                    break;
                case 2:
                    diamond_top3 = listDiamonds.get(i).getKey() + "§7 -> §9" + listDiamonds.get(i).getValue();
                    break;
                case 3:
                    diamond_top4 = listDiamonds.get(i).getKey() + "§7 -> §9" + listDiamonds.get(i).getValue();
                    break;
                case 4:
                    diamond_top5 = listDiamonds.get(i).getKey() + "§7 -> §9" + listDiamonds.get(i).getValue();
                    break;
                default:
                    break;
            }
        }

        for (int i = 0; i < 5 && i < listTime.size(); i++) {
            switch (i) {
                case 0:
                    int godziny_top1 = listTime.get(i).getValue() / 60;
                    int minuty_top1 = listTime.get(i).getValue() % 60;
                    time_top1 = listTime.get(i).getKey() + "§7§l -> §e§l" + godziny_top1 + "h " + minuty_top1 + "min";
                    break;
                case 1:
                    int godziny_top2 = listTime.get(i).getValue() / 60;
                    int minuty_top2 = listTime.get(i).getValue() % 60;
                    time_top2 = listTime.get(i).getKey() + "§7 -> §9" + godziny_top2 + "h " + minuty_top2 + "min";
                    break;
                case 2:
                    int godziny_top3 = listTime.get(i).getValue() / 60;
                    int minuty_top3 = listTime.get(i).getValue() % 60;
                    time_top3 = listTime.get(i).getKey() + "§7 -> §9" + godziny_top3 + "h " + minuty_top3 + "min";
                    break;
                case 3:
                    int godziny_top4 = listTime.get(i).getValue() / 60;
                    int minuty_top4 = listTime.get(i).getValue() % 60;
                    time_top4 = listTime.get(i).getKey() + "§7 -> §9" + godziny_top4 + "h " + minuty_top4 + "min";
                    break;
                case 4:
                    int godziny_top5 = listTime.get(i).getValue() / 60;
                    int minuty_top5 = listTime.get(i).getValue() % 60;
                    time_top5 = listTime.get(i).getKey() + "§7 -> §9" + godziny_top5 + "h " + minuty_top5 + "min";
                    break;
                default:
                    break;
            }
        }

        statyd.getData().set("top.death.top1", death_top1);
        statyd.getData().set("top.death.top2", death_top2);
        statyd.getData().set("top.death.top3", death_top3);
        statyd.getData().set("top.death.top4", death_top4);
        statyd.getData().set("top.death.top5", death_top5);

        statyd.getData().set("top.sleep.top1", sleep_top1);
        statyd.getData().set("top.sleep.top2", sleep_top2);
        statyd.getData().set("top.sleep.top3", sleep_top3);
        statyd.getData().set("top.sleep.top4", sleep_top4);
        statyd.getData().set("top.sleep.top5", sleep_top5);

        statyd.getData().set("top.diamond.top1", diamond_top1);
        statyd.getData().set("top.diamond.top2", diamond_top2);
        statyd.getData().set("top.diamond.top3", diamond_top3);
        statyd.getData().set("top.diamond.top4", diamond_top4);
        statyd.getData().set("top.diamond.top5", diamond_top5);

        statyd.getData().set("top.time.top1", time_top1);
        statyd.getData().set("top.time.top2", time_top2);
        statyd.getData().set("top.time.top3", time_top3);
        statyd.getData().set("top.time.top4", time_top4);
        statyd.getData().set("top.time.top5", time_top5);

        statyd.getData().set("top.dragon.top1", dragon_top1);
        statyd.getData().set("top.dragon.top2", dragon_top2);
        statyd.getData().set("top.dragon.top3", dragon_top3);
        statyd.getData().set("top.dragon.top4", dragon_top4);
        statyd.getData().set("top.dragon.top5", dragon_top5);

        statyd.getData().set("top.break.top1", break_top1);
        statyd.getData().set("top.break.top2", break_top2);
        statyd.getData().set("top.break.top3", break_top3);
        statyd.getData().set("top.break.top4", break_top4);
        statyd.getData().set("top.break.top5", break_top5);

        statyd.getData().set("top.place.top1", place_top1);
        statyd.getData().set("top.place.top2", place_top2);
        statyd.getData().set("top.place.top3", place_top3);
        statyd.getData().set("top.place.top4", place_top4);
        statyd.getData().set("top.place.top5", place_top5);

        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
        statyd.getData().set("top.update", format.format(now));

        try {
            statyd.saveData();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
