package pl.bartixen.bxcore.Ban;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.bartixen.bxcore.Data.BanDataManager;
import pl.bartixen.bxcore.Main;

import java.io.IOException;
import java.net.InetSocketAddress;

public class BanListener implements Listener {

    Main plugin;

    static BanDataManager band;

    public BanListener(Main m) {
        plugin = m;
        band = BanDataManager.getInstance();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPreLogin(AsyncPlayerPreLoginEvent e) throws IOException {
        String name = plugin.getConfig().getString("name");

        if ((band.getData().getString(e.getName() + ".permban")) != null) {
            String msg = band.getData().getString(e.getName() + ".permban.powod");
            String admin = band.getData().getString(e.getName() + ".permban.adminstrator");
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "\n§8• — • — • — • §9§lBAN §8• — • — • — •\n\n§7Nick: §9" + e.getName() + "\n§7Powód: §9" + msg + "\n§7Administrator: §9" + admin + "\n§7Wygasa: §9nigdy\n\n§8• — • — • — • §f§l" + name + " §8• — • — • — •\n");
        }

        if ((band.getData().getString(e.getName() + ".tempban")) != null) {
            if (BanTimeChecker.check(e.getName())) {
                String msg = band.getData().getString(e.getName() + ".tempban.powod");
                String admin = band.getData().getString(e.getName() + ".tempban.adminstrator");
                String czas = BanTimeChecker.getTime();
                e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "\n§8• — • — • — • §9§lBAN §8• — • — • — •\n\n§7Nick: §9" + e.getName() + "\n§7Powód: §9" + msg + "\n§7Administrator: §9" + admin + "\n§7Wygasa: §9" + czas + "\n\n§8• — • — • — • §f§l" + name + " §8• — • — • — •\n");
            }
        }

        if ((band.getData().getString(e.getName() + ".banip")) != null) {
            String msg = band.getData().getString(e.getName() + ".banip.powod");
            String admin = band.getData().getString(e.getName() + ".banip.adminstrator");
            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "\n§8• — • — • — • §9§lBLACKLIST §8• — • — • — •\n\n§7Nick: §9" + e.getName() + "\n§7Powód: §9" + msg + "\n§7Administrator: §9" + admin + "\n\n§8• — • — • — • §f§l" + name + " §8• — • — • — •\n");
        }
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String nazwa = plugin.getConfig().getString("nazwa");

        InetSocketAddress IPAdressPlayer = p.getAddress();
        String ip = IPAdressPlayer.toString();
        String name = ip;
        int entityTypeLenght = name.length() - 6;
        String ipban = name.substring(0, entityTypeLenght);

        if ((BanIPCommand.banip.contains(ipban)) && !p.isOp()) {
            p.kickPlayer("\n§8• — • — • — • §9§lBLACKLIST §8• — • — • — •\n\n§7Nick: §9" + p.getName() + "\n\n§cNa ten adres IP §8(§e" + ipban + "§8) §czostała nadana blokada\n\n§8• — • — • — • §f§l" + nazwa + " §8• — • — • — •\n");
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) throws IOException {
        Player p = e.getPlayer();
        if (!p.isOp()) {
            if (band.getData().getString(p.getName() + ".mute") != null) {
                e.setCancelled(true);
                String nazwa = plugin.getConfig().getString("nazwa");
                String msg = band.getData().getString(p.getName() + ".mute.powod");
                String admin = band.getData().getString(p.getName() + ".mute.adminstrator");
                p.sendMessage("");
                p.sendMessage("§8• — • — • — • §9§lWYCISZENIE §8• — • — • — •");
                p.sendMessage("");
                p.sendMessage("§7Powód: §9" + msg);
                p.sendMessage("§7Administrator: §9" + admin);
                p.sendMessage("§7Wygasa: §9nigdy");
                p.sendMessage("");
                p.sendMessage("§8• — • — • — • §f§l" + nazwa + " §8• — • — • — •");
                p.sendMessage("");
            }
            if (band.getData().getString(p.getName() + ".tempmute") != null) {
                if (MuteTimeChecker.check(p.getName())) {
                    e.setCancelled(true);
                    String nazwa = plugin.getConfig().getString("nazwa");
                    String msg = band.getData().getString(p.getName() + ".tempmute.powod");
                    String admin = band.getData().getString(p.getName() + ".tempmute.adminstrator");
                    String czas = MuteTimeChecker.getTime();
                    p.sendMessage("");
                    p.sendMessage("§8• — • — • — • §9§lWYCISZENIE §8• — • — • — •");
                    p.sendMessage("");
                    p.sendMessage("§7Powód: §9" + msg);
                    p.sendMessage("§7Administrator: §9" + admin);
                    p.sendMessage("§7Wygasa: §9" + czas);
                    p.sendMessage("");
                    p.sendMessage("§8• — • — • — • §f§l" + nazwa + " §8• — • — • — •");
                    p.sendMessage("");
                }
            }
        }
    }
}
