package pl.bartixen.bxcore.Listeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import pl.bartixen.bxcore.Commands.RestartCommand;
import pl.bartixen.bxcore.Data.AntyLogutDataManager;
import pl.bartixen.bxcore.Main;

import java.io.IOException;

public class AntyLogut extends BukkitRunnable implements Listener {

    Main plugin;

    AntyLogutDataManager antylogutd;

    public AntyLogut(Main m) {
        plugin = m;
        antylogutd = AntyLogutDataManager.getInstance();
    }

    //W PvpCommand.java jest kod do tego//

    @EventHandler
    public void onjoin(PlayerJoinEvent e) throws IOException {
        Player p = e.getPlayer();
        antylogutd.getData().set(p.getDisplayName() + ".czas", null);
        antylogutd.saveData();
    }

    @SuppressWarnings("unused")
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onquit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if ((antylogutd.getData().getString(p.getDisplayName() + ".czas")) != null) {
            if (!RestartCommand.restart) {
                p.setHealth(0);
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage("§fGracz §9" + p.getName() + " §fwylogowal sie podczas PVP");
                }
            }
        }
    }

    @Override
    public void run() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            if ((antylogutd.getData().getInt(p.getDisplayName() + ".czas")) > 0) {
                int ile = antylogutd.getData().getInt(p.getDisplayName() + ".czas");
                antylogutd.getData().set(p.getDisplayName() + ".czas", ile - 1);
                try {
                    antylogutd.saveData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§8§l» §c§lJESTES PODCZAS PVP §7§l(§e§l" + ile + "§7§l) §8§l«"));
            } else {
                antylogutd.getData().set(p.getDisplayName() + ".czas", null);
                try {
                    antylogutd.saveData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
