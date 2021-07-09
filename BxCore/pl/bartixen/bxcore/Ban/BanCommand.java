package pl.bartixen.bxcore.Ban;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Data.BanDataManager;
import pl.bartixen.bxcore.Data.UserDataManager;
import pl.bartixen.bxcore.Main;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Level;

public class BanCommand implements CommandExecutor {

    Main plugin;

    static BanDataManager band;
    static UserDataManager userd;

    String msg;

    public BanCommand(Main m) {
        plugin = m;
        m.getCommand("ban").setExecutor(this);
        band = BanDataManager.getInstance();
        userd = UserDataManager.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.ban") || sender.isOp()) {
            Date now = new Date();
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss ss:mm:HH dd-MM-yyyy");
            if (args.length > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < args.length; ++i) {
                    sb.append(args[i]).append(" ");
                }
                msg = sb.toString().replace("&", "§");
                if (((band.getData().getString(args[0] + ".permban")) == null) && ((band.getData().getString(args[0] + ".tempban")) == null) && ((band.getData().getString(args[0] + ".banip")) == null)) {
                    if (!(args.length > 1)) { msg = "brak"; }
                    String nazwa = plugin.getConfig().getString("nazwa");
                    Player cel = Bukkit.getPlayerExact(args[0]);
                    if (cel == null) {
                        if (userd.getData().getString(args[0]) != null) {
                            String ip = userd.getData().getString(args[0] + ".last_ip");
                            band.getData().set(args[0] + ".permban.ip", ip);
                        }
                        band.getData().set(args[0] + ".permban.adminstrator", sender.getName());
                        band.getData().set(args[0] + ".permban.powod", msg);
                        band.getData().set(args[0] + ".permban.nick", args[0]);
                        band.getData().set(args[0] + ".permban.data", format.format(now));
                        try {
                            band.saveData();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        sender.sendMessage("§7Gracz §9" + args[0] + " §7został pomyślnie zbanowany permamentnie z powodem §9" + msg);
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            if (players.hasPermission("bxcore.commands.ban") || players.isOp()) {
                                players.sendMessage("§7Gracz §9" + args[0] + " §7został zbanowany permamentnie przez §9" + sender.getName() +  "§7 z powodem §9" + msg);
                            }
                        }
                        if (plugin.getConfig().getBoolean("logs")) {
                            plugin.getLogger().log(Level.INFO,"Gracz " + args[0] + " zostal zbanowany permamentnie przez " + sender.getName() +  " z powodem " + msg);
                        }
                        return false;
                    }
                    UUID uuid = cel.getUniqueId();
                    InetSocketAddress IPAdressPlayer = cel.getAddress();
                    String ip = IPAdressPlayer.toString();
                    String name = ip;
                    int entityTypeLenght = name.length() - 6;
                    String ipban = name.substring(0, entityTypeLenght);
                    band.getData().set(cel.getName() + ".permban.adminstrator", sender.getName());
                    band.getData().set(cel.getName() + ".permban.powod", msg);
                    band.getData().set(cel.getName() + ".permban.nick", cel.getName());
                    band.getData().set(cel.getName() + ".permban.uuid", uuid.toString());
                    band.getData().set(cel.getName() + ".permban.ip", ipban);
                    band.getData().set(cel.getName() + ".permban.data", format.format(now));
                    try {
                        band.saveData();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    sender.sendMessage("§7Gracz §9" + cel.getName() + " §7został pomyślnie zbanowany permamentnie z powodem §9" + msg);
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        if (players.hasPermission("bxcore.commands.ban") || players.isOp()) {
                            players.sendMessage("§7Gracz §9" + cel.getName() + " §7został zbanowany permamentnie przez §9" + sender.getName() +  "§7 z powodem §9" + msg);
                        }
                    }
                    if (plugin.getConfig().getBoolean("logs")) {
                        plugin.getLogger().log(Level.INFO, "Gracz " + cel.getName() + " zostal zbanowany permamentnie przez " + sender.getName() + " z powodem " + msg);
                    }
                    cel.kickPlayer("\n§8• — • — • — • §9§lBAN §8• — • — • — •\n\n§7Nick: §9" + cel.getDisplayName() + "\n§7Powód: §9" + msg + "\n§7Administrator: §9" + sender.getName() + "\n\n§8• — • — • — • §f§l" + nazwa + " §8• — • — • — •\n");
                } else {
                    sender.sendMessage("§7Gracz §9" + args[0] + " §7jest już zbanowany");
                }
            } else {
                sender.sendMessage("§7Poprawne użycie: §9/ban [gracz] [powód]");
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.ban");
        }
        return false;
    }
}
