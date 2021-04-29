package pl.bartixen.bxcore.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import pl.bartixen.bxcore.Data.AntyXrayDataManager;
import pl.bartixen.bxcore.Main;

import java.io.IOException;
import java.util.UUID;

public class AntyXray implements Listener {

    Main plugin;

    AntyXrayDataManager antyd;

    public AntyXray(Main m) {
        plugin = m;
        antyd = AntyXrayDataManager.getInstance();
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) throws IOException {
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        if (e.getBlock().getType() == Material.DIAMOND_ORE && (plugin.getConfig().getString("antyxray") == "true")) {
            if ((antyd.getData().getString("diamond." + uuid)) == null) {
                antyd.getData().set("diamond." + uuid, 0);
                antyd.saveData();
            }
            antyd.getData().set("diamond." + uuid, antyd.getData().getInt("diamond." + uuid) + 1);
            antyd.saveData();
            int ile = antyd.getData().getInt("diamond." + uuid);
            for (Player players : Bukkit.getOnlinePlayers()) {
                if (players.hasPermission("bxcore.alert.antyxray") || players.isOp()) {
                    players.sendMessage("§8[§cANTYXRAY§8] §eGracz §c" + p.getName() + " §ewykopal rude diamentu §7(w ciagu 1h wykopal §f" + ile + "§7)");
                }
            }
        }
        if (e.getBlock().getType() == Material.ANCIENT_DEBRIS && (plugin.getConfig().getString("antyxray") == "true")) {
            if ((antyd.getData().getString("netherite." + uuid)) == null) {
                antyd.getData().set("netherite." + uuid, 0);
                antyd.saveData();
            }
            antyd.getData().set("netherite." + uuid, antyd.getData().getInt("netherite." + uuid) + 1);
            antyd.saveData();
            int ile = antyd.getData().getInt("netherite." + uuid);
            for (Player players : Bukkit.getOnlinePlayers()) {
                if (players.hasPermission("bxcore.alert.antyxray") || players.isOp()) {
                    players.sendMessage("§8[§cANTYXRAY§8] §eGracz §c" + p.getName() + " §ewykopal ancient debris §7(w ciagu 1h wykopal §f" + ile + "§7)");
                }
            }
        }
    }
}