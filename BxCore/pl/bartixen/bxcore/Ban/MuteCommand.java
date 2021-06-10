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

public class MuteCommand implements CommandExecutor {

    Main plugin;

    static BanDataManager band;

    String msg;

    public MuteCommand(Main m) {
        plugin = m;
        m.getCommand("mute").setExecutor(this);
        band = BanDataManager.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.mute") || sender.isOp()) {
            if (args.length > 0) {
                Date now = new Date();
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
                if ((band.getData().getString(args[0] + ".mute") == null) && (band.getData().getString(args[0] + ".tempmute") == null)) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 1; i < args.length; ++i) {
                        sb.append(args[i]).append(" ");
                    }
                    msg = sb.toString().replace("&", "§");
                    Player cel = Bukkit.getPlayerExact(args[0]);
                    String nazwa = plugin.getConfig().getString("nazwa");
                    if (!(args.length > 1)) {
                        msg = "brak";
                    }
                    if (cel == null) {
                        band.getData().set(args[0] + ".mute.adminstrator", sender.getName());
                        band.getData().set(args[0] + ".mute.powod", msg);
                        band.getData().set(args[0] + ".mute.nick", args[0]);
                        band.getData().set(args[0] + ".mute.data", format.format(now));
                        try {
                            band.saveData();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        sender.sendMessage("§7Gracz §9" + args[0] + " §7zostal pomyślnie wyciszony");
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            if (players.hasPermission("bxcore.commands.mute") || players.isOp()) {
                                players.sendMessage("§7Gracz §9" + args[0] + " §7zostal wyciszony przez §9" + sender.getName() +  "§7 z powodem §9" + msg);
                            }
                        }
                        return false;
                    }
                    band.getData().set(cel.getName() + ".mute.adminstrator", sender.getName());
                    band.getData().set(cel.getName() + ".mute.powod", msg);
                    band.getData().set(cel.getName() + ".mute.nick", cel.getName());
                    band.getData().set(cel.getName() + ".mute.uuid", cel.getUniqueId().toString());
                    band.getData().set(cel.getName() + ".mute.data", format.format(now));
                    try {
                        band.saveData();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    cel.sendMessage("");
                    cel.sendMessage("§8• — • — • — • §9§lWYCISZENIE §8• — • — • — •");
                    cel.sendMessage("");
                    cel.sendMessage("§7Powód: §9" + msg);
                    cel.sendMessage("§7Administrator: §9" + sender.getName());
                    cel.sendMessage("§7Wygasa: §9nigdy");
                    cel.sendMessage("");
                    cel.sendMessage("§8• — • — • — • §f§l" + nazwa + " §8• — • — • — •");
                    cel.sendMessage("");
                    sender.sendMessage("§7Gracz §9" + cel.getName() + " §7zostal pomyślnie wyciszony");
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        if (players.hasPermission("bxcore.commands.mute") || players.isOp()) {
                            players.sendMessage("§7Gracz §9" + cel.getName() + " §7zostal wyciszony przez §9" + sender.getName() +  "§7 z powodem §9" + msg);
                        }
                    }
                } else {
                    sender.sendMessage("§7Gracz §9" + args[0] + " §7jest już wyciszony");
                }
            } else {
                sender.sendMessage("§7Poprawne użycie: §9/mute [gracz] [powód]");
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.mute");
        }
        return false;
    }
}
