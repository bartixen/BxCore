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
        if (plugin.getConfig().getBoolean("automessage.dziala")) {
            List<String> wiadomosci = plugin.getConfig().getStringList("automessage.wiadomosci");

            if (i < wiadomosci.size()) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage(wiadomosci.get(i).replace("&", "§"));
                }
                i++;
            } else {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage(wiadomosci.get(0).replace("&", "§"));
                }
                i = 1;
            }
        }

    }

}
