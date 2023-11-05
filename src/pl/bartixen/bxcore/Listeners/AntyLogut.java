package pl.bartixen.bxcore.Listeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import pl.bartixen.bxcore.Commands.RestartCommand;
import pl.bartixen.bxcore.Data.AntyLogutDataManager;
import pl.bartixen.bxcore.Main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import static io.netty.handler.codec.http.multipart.DiskFileUpload.prefix;

public class AntyLogut extends BukkitRunnable implements Listener {

    Main plugin;

    AntyLogutDataManager antylogutd;

    public AntyLogut(Main m) {
        plugin = m;
        antylogutd = AntyLogutDataManager.getInstance();
    }

    //W PvpCommand.java there is code for this//

    @EventHandler
    public void onjoin(PlayerJoinEvent e) throws IOException {
        Player p = e.getPlayer();
        antylogutd.getData().set(p.getDisplayName() + ".time", null);
        antylogutd.saveData();
        p.setInvulnerable(true);
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§aJestes podczas ochrony"));

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            p.setInvulnerable(false);
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§cTwoja ochrona wygasla"));
        }, 200L);
    }

    @SuppressWarnings("unused")
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onquit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if ((antylogutd.getData().getString(p.getDisplayName() + ".time")) != null) {
            if (!RestartCommand.restart) {
                p.setHealth(0);
                String nick = antylogutd.getData().getString(p.getDisplayName() + ".who");
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage("§fGracz §9" + p.getName() + " §fwylogowal sie podczas walki z §9" + nick);
                }
                if (plugin.getConfig().getBoolean("logs")) {
                    plugin.getLogger().log(Level.INFO, "Gracz " + p.getName() + " wylogowal sie podczas walki z " + nick);
                }
            }
        }
    }

    @EventHandler
    public void OnDeath(PlayerDeathEvent e) throws IOException {
        Player p = e.getEntity();
        int x = (int) p.getLocation().getX();
        int y = (int) p.getLocation().getY();
        int z = (int) p.getLocation().getZ();
        p.sendMessage("§fTwoje kordy śmierci to: §9X: " + x + " Y: " + y + " Z: " + z);
        antylogutd.getData().set(p.getDisplayName() + ".time", null);
        antylogutd.saveData();
    }



    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if ((antylogutd.getData().getInt(p.getDisplayName() + ".time")) > 0) {
                int ile = antylogutd.getData().getInt(p.getDisplayName() + ".time");
                antylogutd.getData().set(p.getDisplayName() + ".time", ile - 1);
                try {
                    antylogutd.saveData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§8§l» §c§lJESTES PODCZAS PVP §7§l(§e§l" + ile + "§7§l) §8§l«"));
            } else {
                if (((antylogutd.getData().getInt(p.getDisplayName() + ".time")) < 0) && ((antylogutd.getData().getString(p.getDisplayName() + ".time")) != null)) {
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§8§l» §a§lMOZESZ SIE WYLOGOWAC §8§l«"));
                }
                antylogutd.getData().set(p.getDisplayName() + ".time", null);
                try {
                    antylogutd.saveData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
