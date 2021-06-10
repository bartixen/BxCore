package pl.bartixen.bxcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

import java.lang.reflect.InvocationTargetException;

public class ServerStatsCommand implements CommandExecutor {

    Main plugin;

    public ServerStatsCommand(Main m) {
        plugin = m;
        m.getCommand("server").setExecutor(this);
        m.getCommand("serverstats").setExecutor(this);
        m.getCommand("serwer").setExecutor(this);
        m.getCommand("lag").setExecutor(this);
        m.getCommand("lagi").setExecutor(this);
        m.getCommand("tps").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.server") || sender.isOp()) {
            Runtime runtime = Runtime.getRuntime();
            String nazwa = plugin.getConfig().getString("nazwa");
            String wersja = plugin.getServer().getVersion();
            int online = plugin.getServer().getOnlinePlayers().size();
            int maxonline = plugin.getServer().getMaxPlayers();
            int tps = plugin.tps;
            int world = plugin.getServer().getWorlds().size();
            int chunks = plugin.getServer().getWorld("world").getLoadedChunks().length + plugin.getServer().getWorld("world_nether").getLoadedChunks().length + plugin.getServer().getWorld("world_the_end").getLoadedChunks().length;
            int entities = plugin.getServer().getWorld("world").getEntities().size() + plugin.getServer().getWorld("world_nether").getEntities().size() + plugin.getServer().getWorld("world_the_end").getEntities().size();
            int useram = (int) ((runtime.totalMemory() - runtime.freeMemory()) / 1048576L);
            int maxram = (int) (runtime.totalMemory() / 1048576L);
            int sredniping = 0;
            for (Player p : Bukkit.getOnlinePlayers()) {
                int ping = 0;
                Object entityPlayer = null;
                try {
                    entityPlayer = p.getClass().getMethod("getHandle").invoke(p);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                try {
                    ping = (int) entityPlayer.getClass().getField("ping").get(entityPlayer);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                sredniping = sredniping + ping;
            }
            sredniping = sredniping % online;

            sender.sendMessage("§8 • — • — • — • ");
            sender.sendMessage("§7Nazwa serwera: §9" + nazwa);
            sender.sendMessage("§7Silnik: §9" + wersja);
            sender.sendMessage("§7Gracze: §9" + online + "/" + maxonline);
            sender.sendMessage("§7TPS: §9error" + " §8(§cTe dane moga być bledne, użyj /tps§8)");
            sender.sendMessage("§7Liczba światów: §9" + world);
            sender.sendMessage("§7Liczba zaladowanych chunks: §9" + chunks);
            sender.sendMessage("§7Liczba entities: §9" + entities);
            sender.sendMessage("§7Ram: §9" + useram + "/" + maxram + " MB");
            sender.sendMessage("§7Średni ping graczy: §9" + sredniping + " ms §8(§cTe dane moga być bledne§8)");
            sender.sendMessage("§8 • — • — • — • ");
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.server");
        }
        return false;
    }
}
