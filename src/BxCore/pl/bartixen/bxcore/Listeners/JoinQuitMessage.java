package pl.bartixen.bxcore.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.bartixen.bxcore.Ban.BanIPCommand;
import pl.bartixen.bxcore.Main;

import java.net.InetSocketAddress;

public class JoinQuitMessage implements Listener {

    Main plugin;

    public JoinQuitMessage(Main m) {
        plugin = m;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (plugin.getConfig().getBoolean("joinquitmessage", true)) {
            Player p = e.getPlayer();
            e.setJoinMessage("§8[§a+§8] §7" + p.getName());
        } else {
            e.setJoinMessage("");
        }
    }

    @EventHandler
    public void onQuti(PlayerQuitEvent e) {
        if (plugin.getConfig().getBoolean("joinquitmessage", true)) {
            Player p = e.getPlayer();
            InetSocketAddress IPAdressPlayer = p.getAddress();
            String ip = IPAdressPlayer.toString();
            String name = ip;
            int entityTypeLenght = name.length() - 6;
            String ipban = name.substring(0, entityTypeLenght);
            if (BanIPCommand.banip.contains(ipban)) {
                e.setQuitMessage("");
            } else {
                e.setQuitMessage("§8[§c-§8] §7" + p.getName());
            }
        } else {
            e.setQuitMessage("");
        }
    }

}
