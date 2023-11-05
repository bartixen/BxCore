package pl.bartixen.bxcore.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import pl.bartixen.bxcore.Main;

import java.util.HashMap;

public class AntySpam implements Listener {

    HashMap<Player, Long> spam = new HashMap<Player, Long>();
    HashMap<Player, Long> spamc = new HashMap<Player, Long>();
    HashMap<Player, Long> spamhelp = new HashMap<Player, Long>();

    Main plugin;

    public AntySpam(Main m) {
        plugin = m;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        int time = plugin.getConfig().getInt("antyspam.time");
        if (plugin.getConfig().getBoolean("antyspam.works", true)) {
            if (!(p.hasPermission("bxcore.user.bypass") || p.isOp())) {
                if (spam.containsKey(p)) {
                    if (spam.get(p) > System.currentTimeMillis()) {
                        p.sendMessage("§cPoczekaj przed ponownym wyslaniem wiadomości");
                        e.setCancelled(true);
                    } else {
                        spam.put(p, System.currentTimeMillis() + time * 1000);
                    }
                } else {
                    spam.put(p, System.currentTimeMillis() + time * 1000);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        int czas = plugin.getConfig().getInt("antyspam.czas");
        if (plugin.getConfig().getBoolean("antyspam.dzialanie", true)) {
            if (!(p.hasPermission("bxcore.user.bypass") || p.isOp())) {
                String helpop = "helpop";
                if (e.getMessage().toLowerCase().startsWith("/" + helpop.toLowerCase())) {
                    if (spamhelp.containsKey(p)) {
                        if (spamhelp.get(p) > System.currentTimeMillis()) {
                            p.sendMessage("§cPoczekaj przed ponownym wyslaniem wiadomości do administracji");
                            e.setCancelled(true);
                        } else {
                            spamhelp.put(p, System.currentTimeMillis() + 30 * 1000);
                        }
                    } else {
                        spamhelp.put(p, System.currentTimeMillis() + 30 * 1000);
                    }
                } else {
                    if (spamc.containsKey(p)) {
                        if (spamc.get(p) > System.currentTimeMillis()) {
                            p.sendMessage("§cPoczekaj przed ponownym wyslaniem komendy");
                            e.setCancelled(true);
                        } else {
                            spamc.put(p, System.currentTimeMillis() + czas * 1000);
                        }
                    } else {
                        spamc.put(p, System.currentTimeMillis() + czas * 1000);
                    }
                }
            }
        }
    }
}
