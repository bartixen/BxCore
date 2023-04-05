package pl.bartixen.bxcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

import java.util.logging.Level;

public class FlyCommand implements CommandExecutor {

    Main plugin;

    public FlyCommand(Main m) {
        plugin = m;
        m.getCommand("fly").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.fly") || sender.isOp()) {
            if (args.length == 0) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§7Poprawne użycie: §9/fly [gracz]");
                    return false;
                }
                Player p = (Player) sender;
                if (p.getAllowFlight()) {
                    p.setAllowFlight(false);
                    p.sendMessage("§7Latanie zostało §cwyłączone");
                    if (plugin.getConfig().getBoolean("logs")) {
                        plugin.getLogger().log(Level.INFO, "Gracz " + p.getName() + " wylaczyl latanie");
                    }
                    return false;
                } else {
                    p.setAllowFlight(true);
                    p.sendMessage("§7Latanie zostało §awłączone");
                    if (plugin.getConfig().getBoolean("logs")) {
                        plugin.getLogger().log(Level.INFO, "Gracz " + p.getName() + " wlaczyl latanie");
                    }
                    return false;
                }
            } else {
                if (args.length == 1) {
                    Player cel = Bukkit.getPlayerExact(args[0]);
                    if (cel == null) {
                        sender.sendMessage("§7Ten gracz jest §coffline");
                        return true;
                    }
                    if (cel.getAllowFlight()) {
                        cel.setAllowFlight(false);
                        cel.sendMessage("§7Latanie zostalo §cwyłączone §7przez gracza §9" + sender.getName());
                        sender.sendMessage("§7Poprawne §cwyłączono §7latanie graczu §9" + cel.getName());
                        plugin.getLogger().log(Level.INFO,"Gracz " + sender.getName() + " wylaczyl latanie dla §9" + cel.getName());
                        return false;
                    } else {
                        cel.setAllowFlight(true);
                        cel.sendMessage("§7Latanie zostalo §awłączone §7przez gracza §9" + sender.getName());
                        sender.sendMessage("§7Poprawne §awłączono §7latanie graczu §9" + cel.getName());
                        plugin.getLogger().log(Level.INFO,"Gracz " + sender.getName() + " wlaczyl latanie dla §9" + cel.getName());
                        return false;
                    }
                } else {
                    sender.sendMessage("§7Poprawne użycie: §9/fly [gracz]");
                }
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.fly");
        }
        return false;
    }
}