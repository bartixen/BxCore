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

public class TempMuteCommand implements CommandExecutor {

    Main plugin;

    static BanDataManager band;

    String msg;

    public TempMuteCommand(Main m) {
        plugin = m;
        m.getCommand("tempmute").setExecutor(this);
        band = BanDataManager.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.tempmute") || sender.isOp()) {
            if (args.length > 1) {
                if ((band.getData().getString(args[0] + ".mute") == null) && (band.getData().getString(args[0] + ".tempmute") == null)) {
                    String timeVariable = args[1].replaceAll("[0-9]", "");
                    if (!args[1].matches(".*\\d.*")) {
                        sender.sendMessage("§7Poprawne użycie: §9/tempmute [gracz] [1s|1m|1h|1d] [powód]");
                        return false;
                    }
                    if (!timeVariable.equalsIgnoreCase("s") && !timeVariable.equalsIgnoreCase("m") && !timeVariable.equalsIgnoreCase("h") && !timeVariable.equalsIgnoreCase("d")) {
                        sender.sendMessage("§7Poprawne użycie: §9/tempmute [gracz] [1s|1m|1h|1d] [powód]");
                        return false;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (int i = 2; i < args.length; ++i) {
                        sb.append(args[i]).append(" ");
                    }
                    msg = sb.toString().replace("&", "§");
                    if (!(args.length > 2)) {
                        msg = "brak";
                    }
                    String czas = args[1];
                    Date now = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
                    Player cel = Bukkit.getPlayerExact(args[0]);
                    String nazwa = plugin.getConfig().getString("nazwa");
                    if (cel == null) {
                        band.getData().set(args[0] + ".tempmute.adminstrator", sender.getName());
                        band.getData().set(args[0] + ".tempmute.powod", msg);
                        band.getData().set(args[0] + ".tempmute.nick", args[0]);
                        band.getData().set(args[0] + ".tempmute.data", format.format(now));
                        band.getData().set(args[0] + ".tempmute.czas", czas);
                        try {
                            band.saveData();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        sender.sendMessage("§7Gracz §9" + args[0] + " §7zostal pomyślnie wyciszony");
                        return false;
                    }
                    band.getData().set(cel.getName() + ".tempmute.adminstrator", sender.getName());
                    band.getData().set(cel.getName() + ".tempmute.powod", msg);
                    band.getData().set(cel.getName() + ".tempmute.nick", cel.getName());
                    band.getData().set(cel.getName() + ".tempmute.uuid", cel.getUniqueId().toString());
                    band.getData().set(cel.getName() + ".tempmute.data", format.format(now));
                    band.getData().set(cel.getName() + ".tempmute.czas", czas);
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
                    cel.sendMessage("");
                    cel.sendMessage("§8• — • — • — • §f§l" + nazwa + " §8• — • — • — •");
                    cel.sendMessage("");
                    sender.sendMessage("§7Gracz §9" + cel.getName() + " §7zostal pomyślnie wyciszony");

                } else {
                    sender.sendMessage("§7Gracz §9" + args[0] + " §7jest już wyciszony");
                }
            } else {
                sender.sendMessage("§7Poprawne użycie: §9/tempmute [gracz] [1s|1m|1h|1d] [powód]");
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.tempmute");
        }
        return false;
    }

}
