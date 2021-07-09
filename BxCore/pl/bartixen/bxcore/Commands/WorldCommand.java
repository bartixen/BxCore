package pl.bartixen.bxcore.Commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

import java.util.logging.Level;

public class WorldCommand implements CommandExecutor {

    Main plugin;

    public WorldCommand(Main m) {
        plugin = m;
        m.getCommand("world").setExecutor(this);
        m.getCommand("swiat").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cTa komenda jest przeznaczona tylko dla graczy");
            return false;
        }
        Player p = (Player) sender;
        if (p.hasPermission("bxcore.commands.tp") || p.isOp()) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("world")) {
                    p.teleport(new Location(p.getServer().getWorld("world"), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch()));
                    p.sendMessage("§7Pomyślnie przeteleportowano ciebie do świata §9world");
                    if (plugin.getConfig().getBoolean("logs")) {
                        plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " teleportowal sie do swiata WORLD");
                    }
                } else {
                    if (args[0].equalsIgnoreCase("nether")) {
                        p.teleport(new Location(p.getServer().getWorld("world_nether"), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch()));
                        p.sendMessage("§7Pomyślnie przeteleportowano ciebie do świata §9world_nether");
                        if (plugin.getConfig().getBoolean("logs")) {
                            plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " teleportowal sie do swiata WORLD_NETHER");
                        }
                    } else {
                        if (args[0].equalsIgnoreCase("end")) {
                            p.teleport(new Location(p.getServer().getWorld("world_the_end"), p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch()));
                            p.sendMessage("§7Pomyślnie przeteleportowano ciebie do świata §9world_the_end");
                            if (plugin.getConfig().getBoolean("logs")) {
                                plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " teleportowal sie do swiata WORLD_THE_END");
                            }
                        } else {
                            sender.sendMessage("§7Poprawne użycie: §9/world [world,nether,end]");
                        }
                    }
                }
            } else {
                sender.sendMessage("§7Poprawne użycie: §9/world [world,nether,end]");
            }
        } else {
            p.sendMessage("§7Brak permisji: §9bxcore.commands.tp");
        }
        return false;
    }
}