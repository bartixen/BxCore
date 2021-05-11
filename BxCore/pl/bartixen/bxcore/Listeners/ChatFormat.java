package pl.bartixen.bxcore.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.bartixen.bxcore.Data.TeamDataManager;
import pl.bartixen.bxcore.Data.UserDataManager;
import pl.bartixen.bxcore.Main;

public class ChatFormat implements Listener {

    Main plugin;

    static UserDataManager userd;
    static TeamDataManager teamd;

    public ChatFormat(Main m) {
        plugin = m;
        userd = UserDataManager.getInstance();
        teamd = TeamDataManager.getInstance();
    }

    @EventHandler
    public void onPrefix(AsyncPlayerChatEvent e) {
        String original = e.getMessage();
        String formatted = ChatColor.translateAlternateColorCodes('&', original);
        Player p = e.getPlayer();

        String team = "";
        String druzyna = "brak";
        if (teamd.getData().getString(p.getDisplayName() + ".team") != null) {
            druzyna = teamd.getData().getString(p.getDisplayName() + ".team");
            if (druzyna.equals("czerwony")) {
                team = "§8§l[§c§l●§8§l] ";
            }
            if (druzyna.equals("jasnoniebieski")) {
                team = "§8§l[§b§l●§8§l] ";
            }
            if (druzyna.equals("zolty")) {
                team = "§8§l[§e§l●§8§l] ";
            }
            if (druzyna.equals("rozowy")) {
                team = "§8§l[§d§l●§8§l] ";
            }
            if (druzyna.equals("zielony")) {
                team = "§8§l[§a§l●§8§l] ";
            }
            if (druzyna.equals("pomaranczowy")) {
                team = "§8§l[§6§l●§8§l] ";
            }
            if (druzyna.equals("niebieski")) {
                team = "§8§l[§9§l●§8§l] ";
            }
        }

        if (p.hasPermission("wlasciciel")) {
            e.setFormat(team + "§8§l[§c§l%s§8§l] §7-> §c" + formatted);
        } else {
            if (p.hasPermission("admin")) {
                e.setFormat(team + "§8§l[§b§l%s§8§l] §7-> §b" + formatted);
            } else {
                if (p.hasPermission("pomocnik")) {
                    e.setFormat(team + "§8§l[§d§l%s§8§l] §7-> §d" + formatted);
                } else {
                    e.setFormat(team + "§8§l[§9§l%s§8§l] §7-> §f%s");
                }
            }
        }
    }
}
