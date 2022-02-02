package pl.bartixen.bxcore.Listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.bartixen.bxcore.Data.UserDataManager;
import pl.bartixen.bxcore.Main;

import java.io.IOException;
import java.net.InetSocketAddress;

public class PlayerData implements Listener {

    Main plugin;

    static UserDataManager userd;

    public PlayerData(Main m) {
        plugin = m;
        userd = UserDataManager.getInstance();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws IOException {
        Player p = e.getPlayer();
        p.sendTitle("", "", 1, 1, 1);
        String uuid = p.getUniqueId().toString();
        String nick = p.getName();
        InetSocketAddress IPAdressPlayer = p.getAddress();
        String ip = IPAdressPlayer.toString();
        int entityTypeLenght = ip.length() - 6;
        String ipban = ip.substring(0, entityTypeLenght);
        if (userd.getData().getConfigurationSection(nick) == null) {
            userd.getData().set(nick + ".uuid", uuid);
            userd.getData().set(nick + ".first_ip", ipban);
            userd.getData().set(nick + ".last_ip", ipban);
            userd.saveData();
            double x = plugin.getConfig().getDouble("spawn.x");
            double y = plugin.getConfig().getDouble("spawn.y");
            double z = plugin.getConfig().getDouble("spawn.z");
            float yaw = plugin.getConfig().getInt("spawn.yaw");
            float pitch = plugin.getConfig().getInt("spawn.pitch");
            String world = plugin.getConfig().getString("spawn.world");
            p.teleport(new Location(p.getServer().getWorld(world), x, y, z, yaw, pitch));
        }
        userd.getData().set(nick + ".uuid", uuid);
        userd.getData().set(nick + ".last_ip", ipban);
        userd.saveData();
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) throws IOException {
        Player p = e.getPlayer();
        String nick = p.getName();
        InetSocketAddress IPAdressPlayer = p.getAddress();
        String ip1 = IPAdressPlayer.toString();
        String name = ip1;
        int entityTypeLenght = name.length() - 6;
        String ipban = name.substring(0, entityTypeLenght);
        userd.getData().set(nick + ".last_ip", ipban);
        userd.saveData();
    }
}
