package pl.bartixen.bxcore.Staty;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.bartixen.bxcore.Data.StatyDataManager;
import pl.bartixen.bxcore.Main;

import java.io.IOException;
import java.util.UUID;

public class StatyTime extends BukkitRunnable {

    Main plugin;

    static StatyDataManager statyd;

    public StatyTime(Main m) {
        plugin = m;
        statyd = StatyDataManager.getInstance();
    }

    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            UUID uuid = p.getUniqueId();

            statyd.getData().set(uuid + ".times", statyd.getData().getInt(uuid + ".times") + 1);
            try {
                statyd.saveData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
