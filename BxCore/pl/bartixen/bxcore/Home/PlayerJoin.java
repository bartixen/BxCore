package pl.bartixen.bxcore.Home;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.bartixen.bxcore.Data.HomeDataManager;

import java.io.IOException;
import java.util.UUID;

public class PlayerJoin implements Listener {

    static HomeDataManager hd;

    public PlayerJoin() {
        hd = HomeDataManager.getInstance();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws IOException {
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();

        String nick = p.getName();
        hd.getData().set(uuid + ".homes.setname.home1", null);
        hd.getData().set(uuid + ".homes.setname.home2", null);
        hd.getData().set(uuid + ".homes.setname.home3", null);
        hd.getData().set(uuid + ".homes.setname.home4", null);
        hd.getData().set(uuid + ".homes.setname.home5", null);
        hd.saveData();
        if (!p.hasPlayedBefore()) {
            hd.getData().set(uuid + ".nick", nick);
            hd.saveData();
        }
    }

}
