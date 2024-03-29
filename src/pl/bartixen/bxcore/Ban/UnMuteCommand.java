package pl.bartixen.bxcore.Ban;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Data.BanDataManager;
import pl.bartixen.bxcore.Main;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

public class UnMuteCommand implements CommandExecutor {

    Main plugin;

    static BanDataManager band;

    public UnMuteCommand(Main m) {
        plugin = m;
        m.getCommand("unmute").setExecutor(this);
        band = BanDataManager.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.unmute") || sender.isOp()) {
            if (args.length == 1) {
                Date now = new Date();
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
                if ((band.getData().getString(args[0] + ".mute")) != null) {
                    band.getData().set(args[0] + ".mute", null);
                    band.getData().set(args[0] + ".history.lastunmute.administrator", sender.getName());
                    band.getData().set(args[0] + ".history.lastunmute.data", format.format(now));
                    try {
                        band.saveData();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    sender.sendMessage("§7Gracz §9" + args[0] + " §7został pomyślnie odciszony");
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        if (players.hasPermission("bxcore.commands.unmute") || players.isOp()) {
                            players.sendMessage("§7Gracz §9" + args[0] + " §7zostal odciszony przez §9" + sender.getName());
                        }
                    }
                    if (plugin.getConfig().getBoolean("logs")) {
                        plugin.getLogger().log(Level.INFO, "Gracz " + args[0] + " zostal odciszony przez " + sender.getName());
                    }
                } else {
                    if ((band.getData().getString(args[0] + ".tempmute")) != null) {
                        band.getData().set(args[0] + ".tempmute", null);
                        band.getData().set(args[0] + ".history.lastunmute.administrator", sender.getName());
                        band.getData().set(args[0] + ".history.lastunmute.data", format.format(now));
                        try {
                            band.saveData();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        sender.sendMessage("§7Gracz §9" + args[0] + " §7został pomyślnie odciszony");
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            if (players.hasPermission("bxcore.commands.unmute") || players.isOp()) {
                                players.sendMessage("§7Gracz §9" + args[0] + " §7zostal odciszony przez §9" + sender.getName());
                            }
                        }
                        if (plugin.getConfig().getBoolean("logs")) {
                            plugin.getLogger().log(Level.INFO, "Gracz " + args[0] + " zostal odciszony przez " + sender.getName());
                        }
                    } else {
                        sender.sendMessage("§7Gracz §9" + args[0] + " §7nie jest wyciszony");
                    }
                }
            } else {
                sender.sendMessage("§7Poprawne użycie: §9/unmute [gracz]");
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.unmute");
        }
        return false;
    }
}
