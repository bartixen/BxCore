package pl.bartixen.bxcore.Listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import pl.bartixen.bxcore.Commands.RtpNadajCommand;
import pl.bartixen.bxcore.Data.RtpDataManager;
import pl.bartixen.bxcore.Main;

import java.util.Random;

public class Rtp implements Listener {

    Main plugin;

    static RtpDataManager rtpd;

    public Rtp(Main m) {
        plugin = m;
        rtpd = RtpDataManager.getInstance();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_BLOCK && event.getClickedBlock().getLocation().getBlock().getType() == Material.OAK_BUTTON) {
            Location lokacja = event.getClickedBlock().getLocation();
            int x1 = lokacja.getBlockX();
            int y1 = lokacja.getBlockY();
            int z1 = lokacja.getBlockZ();
            if (RtpNadajCommand.przyciski.contains(x1 + "" + y1 + "" + z1)) {
                World w = p.getServer().getWorld("world");
                int border = (int) p.getLocation().getWorld().getWorldBorder().getSize() / 2;

                int coordsX = border;
                int coordsZ = border;

                Random rand = new Random();

                double x = -1 ^ rand.nextInt(coordsX);
                double z = rand.nextInt(coordsZ);
                double y = 63.0;

                Location loc = new Location(w, x, y, z);
                loc.setY((double) (loc.getWorld().getHighestBlockYAt(loc) + 3));
                p.teleport(loc);
                p.sendMessage("§7Zostales przeteleportowany na kordy §9X §b" + x + " §9Z §b" + z);
            }
        }
    }
}
