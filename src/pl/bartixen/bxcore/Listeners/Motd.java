package pl.bartixen.bxcore.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import pl.bartixen.bxcore.Main;

public class Motd implements Listener {

    Main plugin;

    public Motd(Main m) {
        plugin = m;
    }

    @EventHandler
    public void Motd(ServerListPingEvent e) {
        String original1 = plugin.getConfig().getString("motd.line-1");
        String original2 = plugin.getConfig().getString("motd.line-2");
        String dodacjeden = plugin.getConfig().getString("motd.max-add-one");
        int max = plugin.getConfig().getInt("motd.max");
        int playercount = Bukkit.getServer().getOnlinePlayers().size();

        String motd1 = original1.replace("&", "§");
        String motd2 = original2.replace("&", "§");

        e.setMotd(motd1 + "\n" + motd2);
        e.setMaxPlayers(max);

        if (dodacjeden == "true") {
            e.setMaxPlayers(playercount + 1);
        }

    }

}
