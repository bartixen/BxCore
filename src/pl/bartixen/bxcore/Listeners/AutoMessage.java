package pl.bartixen.bxcore.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.bartixen.bxcore.Main;

import java.util.List;

public class AutoMessage extends BukkitRunnable {

    Main plugin;

    int i = 0;

    public AutoMessage(Main m) {
        plugin = m;
    }

    @Override
    public void run() {
        if (plugin.getConfig().getBoolean("automessage.works")) {
            List<String> tidings = plugin.getConfig().getStringList("automessage.tidings");

            if (i < tidings.size()) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage(tidings.get(i).replace("&", "§"));
                }
                i++;
            } else {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage(tidings.get(0).replace("&", "§"));
                }
                i = 1;
            }
        }

    }

}
