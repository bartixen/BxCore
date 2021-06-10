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
import java.util.ArrayList;
import java.util.Date;

public class BanIPCommand implements CommandExecutor {

    Main plugin;

    static BanDataManager band;
    static UserDataManager userd;
    public static ArrayList<String> banip = new ArrayList<>();

    String msg;

    public BanIPCommand(Main m) {
        plugin = m;
        m.getCommand("banip").setExecutor(this);
        m.getCommand("ipban").setExecutor(this);
        band = BanDataManager.getInstance();
        userd = UserDataManager.getInstance();
        banip = new ArrayList<>(band.getData().getStringList("banip"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.banip") || sender.isOp()) {
            Date now = new Date();
            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
            if (args.length > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < args.length; ++i) {
                    sb.append(args[i]).append(" ");
                }
                msg = sb.toString().replace("&", "§");
                if (((band.getData().getString(args[0] + ".permban")) == null) && ((band.getData().getString(args[0] + ".tempban")) == null) && ((band.getData().getString(args[0] + ".banip")) == null) && (!(banip.contains(args[0])))) {
                    if (!(args.length > 1)) { msg = "brak"; }
                    String nazwa = plugin.getConfig().getString("nazwa");
                    if (userd.getData().getString(args[0]) != null) {
                        String ip = userd.getData().getString(args[0] + ".last_ip");
                        band.getData().set(args[0] + ".banip.adminstrator", sender.getName());
                        band.getData().set(args[0] + ".banip.powod", msg);
                        band.getData().set(args[0] + ".banip.nick", args[0]);
                        band.getData().set(args[0] + ".banip.ip", ip);
                        band.getData().set(args[0] + ".banip.data", format.format(now));
                        banip.add(ip);
                        band.getData().set("banip", banip);
                        try {
                            band.saveData();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        sender.sendMessage("§7Gracz §9" + args[0] + " §8(§b" + ip + "§8) §7zostal pomyślnie dodany do blacklist z powodem §9" + msg);
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            if (players.hasPermission("bxcore.commands.banip") || players.isOp()) {
                                players.sendMessage("§7Gracz §9" + args[0] + " §7zostal dodany do blacklist przez §9" + sender.getName() +  "§7 z powodem §9" + msg);
                            }
                        }
                        Player cel = Bukkit.getPlayerExact(args[0]);
                        if (cel != null) {
                            cel.kickPlayer("\n§8• — • — • — • §9§lBLACKLIST §8• — • — • — •\n\n§7Nick: §9" + cel.getDisplayName() + "\n§7Powód: §9" + msg + "\n§7Administrator: §9" + sender.getName() + "\n\n§8• — • — • — • §f§l" + nazwa + " §8• — • — • — •\n");
                        }
                        return false;
                    } else {
                        String ip = "/" + args[0];
                        banip.add(ip);
                        band.getData().set("banip", banip);
                        try {
                            band.saveData();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            InetSocketAddress IPAdressPlayer = p.getAddress();
                            String ip1 = IPAdressPlayer.toString();
                            String name = ip1;
                            int entityTypeLenght = name.length() - 6;
                            String ipban = name.substring(0, entityTypeLenght);

                            p.sendMessage(ipban);
                            p.sendMessage(ip);

                            if (ipban.equals(ip)) {
                                p.kickPlayer("\n§8• — • — • — • §9§lBLACKLIST §8• — • — • — •\n\n§7Nick: §9" + p.getName() + "\n\n§cNa ten adres IP §8(§e" + ipban + "§8) §czostala nadana blokada\n\n§8• — • — • — • §f§l" + nazwa + " §8• — • — • — •\n");
                            }
                        }
                        sender.sendMessage("§7Adres IP §9" + ip + " §7zostal pomyślnie dodany do blacklist");
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            if (players.hasPermission("bxcore.commands.banip") || players.isOp()) {
                                players.sendMessage("§7Adres IP §9" + ip + " §7zostal dodany do blacklist przez §9" + sender.getName() +  "§7 z powodem §9" + msg);
                            }
                        }
                        return false;
                    }
                } else {
                    sender.sendMessage("§7Gracz/IP §9" + args[0] + " §7jest już zbanowany");
                }
            } else {
                sender.sendMessage("§7Poprawne użycie: §9/banip [gracz/ip] [powód]");
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.banip");
        }
        return false;
    }
}
