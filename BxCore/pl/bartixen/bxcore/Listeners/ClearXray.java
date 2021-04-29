package pl.bartixen.bxcore.Listeners;

import org.bukkit.scheduler.BukkitRunnable;
import pl.bartixen.bxcore.Data.AntyXrayDataManager;
import pl.bartixen.bxcore.Main;

import java.io.IOException;

public class ClearXray extends BukkitRunnable {

    Main plugin;

    AntyXrayDataManager antyd;

    public ClearXray(Main m) {
        plugin = m;
        antyd = AntyXrayDataManager.getInstance();
    }

    @Override
    public void run() {
        antyd.getData().set("diamond", null);
        antyd.getData().set("netherite", null);
        try {
            antyd.saveData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
