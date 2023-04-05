package pl.bartixen.bxcore.Listeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.scheduler.BukkitRunnable;
import pl.bartixen.bxcore.Main;
import pl.bartixen.bxcore.Permission.PermAddition;
import pl.bartixen.bxcore.Permission.PermissionConfig;

import java.util.ArrayList;
import java.util.UUID;

public class RefreshAction extends BukkitRunnable {

    Main plugin;

    PermissionConfig permd;

    public RefreshAction(Main m) {
        plugin = m;
        permd = PermissionConfig.getInstance();
    }

    @Override
    public void run() {
        for (Player players : Bukkit.getOnlinePlayers()) {
            if (plugin.invisible.contains(players)) {
                players.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§aJestes obecnie niewidoczny"));
            }
            TabList.RefreshPlayerName(players, TabList.TeamAction.CREATE);
            String header = plugin.getConfig().getString("header").replace("&", "§").replace("%ping%", getPing(players) + "").replace("%tps%", getTps()).replace("%name%", plugin.getConfig().getString("nazwa"));
            String footer = plugin.getConfig().getString("footer").replace("&", "§").replace("%ping%", getPing(players) + "").replace("%tps%", getTps()).replace("%name%", plugin.getConfig().getString("nazwa"));
            players.setPlayerListHeaderFooter(header, footer);
            refreshPermission(players);
        }
    }

    public void refreshPermission(Player p) {
        UUID uuid = p.getUniqueId();
        String rank = permd.getData().getString("users." + uuid);
        ArrayList<String> listPermission = new ArrayList<>(permd.getData().getStringList("groups." + rank + ".permissions"));
        for (String permission : listPermission) {
            PermissionAttachment at = p.addAttachment(plugin);
            at.setPermission(permission, true);
        }
    }

    private String getTps() {
        StringBuilder sb = new StringBuilder("");
        //for (double tps : plugin.getServer().get) {
        //    sb.append(format(tps));
        //}
        //return sb.toString().substring(0, 4);
        return "%tps%";
    }

    public static int getPing(Player p) {
        String v = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        if (!p.getClass().getName().equals("org.bukkit.craftbukkit." + v + ".entity.CraftPlayer")) {
            p = Bukkit.getPlayer(p.getUniqueId());
        }
        try {
            int ping = p.getPing();
            return ping;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private String format(double tps) {
        return String.valueOf(Math.min( Math.round( tps * 100.0 ) / 100.0, 20.0 ));
    }
}
