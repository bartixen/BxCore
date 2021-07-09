package pl.bartixen.bxcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

import java.util.logging.Level;

public class TpCommand implements CommandExecutor {

    Main plugin;

    public TpCommand(Main m) {
        plugin = m;
        m.getCommand("tp").setExecutor(this);
        m.getCommand("teleport").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.tp") || sender.isOp()) {
            if (args.length == 1) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§cTa komenda jest przeznaczona tylko dla graczy");
                    return false;
                }
                Player p = (Player) sender;
                Player cel = Bukkit.getPlayerExact(args[0]);
                if (cel == null) {
                    p.sendMessage("§7Ten gracz jest §coffline");
                    return true;
                }
                p.teleport(cel.getLocation());
                p.sendMessage("§7Przeteleportowano do gracza §9" + cel.getName());
                if (plugin.getConfig().getBoolean("logs")) {
                    plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " teleportowal sie do " + cel.getName());
                }
            } else {
                if (args.length == 2) {
                    Player cel1 = Bukkit.getPlayerExact(args[0]);
                    Player cel2 = Bukkit.getPlayerExact(args[1]);
                    if (cel1 == null) {
                        sender.sendMessage("§7Jeden z graczy jest §coffline");
                        return true;
                    }
                    if (cel2 == null) {
                        sender.sendMessage("§7Jeden z graczy jest §coffline");
                        return true;
                    }
                    cel1.teleport(cel2.getLocation());
                    sender.sendMessage("§7Przeteleportowano gracza §9" + cel1.getName() + " §7do gracza §9" + cel2.getName());
                    cel1.sendMessage("§7Przeteleportowano ciebie do gracza §9" + cel2.getName() + " §7przez §9" + sender.getName());
                    if (plugin.getConfig().getBoolean("logs")) {
                        plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " teleportowal gracza " + cel1.getName() + " do " + cel2.getName());
                    }
                } else {
                    if (args.length == 3) {
                        if (!(sender instanceof Player)) {
                            sender.sendMessage("§cTa komenda jest przeznaczona tylko dla graczy");
                            return false;
                        }
                        Player p = (Player) sender;
                        final double x = args[0].startsWith("~") ? p.getLocation().getX() + (args[0].length() > 1 ? Double.parseDouble(args[0].substring(1)) : 0) : Double.parseDouble(args[0]);
                        final double y = args[1].startsWith("~") ? p.getLocation().getY() + (args[1].length() > 1 ? Double.parseDouble(args[1].substring(1)) : 0) : Double.parseDouble(args[1]);
                        final double z = args[2].startsWith("~") ? p.getLocation().getZ() + (args[2].length() > 1 ? Double.parseDouble(args[2].substring(1)) : 0) : Double.parseDouble(args[2]);
                        if (!(x > 30000000 || y > 30000000 || z > 30000000 || x < -30000000 || y < -30000000 || z < -30000000)) {
                            p.teleport(new Location(p.getWorld(), x, y, z, p.getLocation().getYaw(), p.getLocation().getPitch()));
                            p.sendMessage("§7Przeteleportowano ciebie na koordynaty: §9X " + x + " §9Y " + y + " §9Z " + z);
                            if (plugin.getConfig().getBoolean("logs")) {
                                plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " teleportowal sie na kordy X - " + x + " Y - " + y + " Z - " + z);
                            }
                        } else {
                            sender.sendMessage("§7Podano nieprawidlowe koordynaty");
                        }
                    } else {
                        sender.sendMessage("§7Poprawne użycie: §9/tp [gracz] | /tp [gracz] [gracz] | /tp [X] [Y] [Z]");
                    }
                }
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.tp");
        }
        return false;
    }
}