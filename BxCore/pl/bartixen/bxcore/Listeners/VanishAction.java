package pl.bartixen.bxcore.Listeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.bartixen.bxcore.Main;

public class VanishAction extends BukkitRunnable {

    Main plugin;

    public VanishAction(Main m) {
        plugin = m;
    }

    @Override
    public void run() {
        for (Player players : Bukkit.getOnlinePlayers()) {
            if (plugin.invisible.contains(players)) {
                players.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§aJestes obecnie niewidoczny"));
            }
        }
    }
}
