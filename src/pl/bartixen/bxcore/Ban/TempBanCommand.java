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

public class TempBanCommand implements CommandExecutor {

    Main plugin;

    static BanDataManager band;
    static UserDataManager userd;

    String msg;

    public TempBanCommand(Main m) {
        plugin = m;
        m.getCommand("tempban").setExecutor(this);
        band = BanDataManager.getInstance();
        userd = UserDataManager.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.tempban") || sender.isOp()) {
            if (args.length > 1) {
                if (((band.getData().getString(args[0] + ".permban")) == null) && ((band.getData().getString(args[0] + ".tempban")) == null) && ((band.getData().getString(args[0] + ".banip")) == null)) {
                    String timeVariable = args[1].replaceAll("[0-9]", "");
                    if (!args[1].matches(".*\\d.*")) {
                        sender.sendMessage("§7Poprawne użycie: §9/tempban [gracz] [1s|1m|1h|1d] [powód]");
                        return false;
                    }
                    if (!timeVariable.equalsIgnoreCase("s") && !timeVariable.equalsIgnoreCase("m") && !timeVariable.equalsIgnoreCase("h") && !timeVariable.equalsIgnoreCase("d")) {
                        sender.sendMessage("§7Poprawne użycie: §9/tempban [gracz] [1s|1m|1h|1d] [powód]");
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
                    String name1 = plugin.getConfig().getString("name");
                    if (cel == null) {
                        if (userd.getData().getString(args[0]) != null) {
                            String ip = userd.getData().getString(args[0] + ".last_ip");
                            band.getData().set(args[0] + ".tempban.ip", ip);
                        }
                        band.getData().set(args[0] + ".tempban.adminstrator", sender.getName());
                        band.getData().set(args[0] + ".tempban.powod", msg);
                        band.getData().set(args[0] + ".tempban.nick", args[0]);
                        band.getData().set(args[0] + ".tempban.data", format.format(now));
                        band.getData().set(args[0] + ".tempban.czas", czas);
                        try {
                            band.saveData();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        sender.sendMessage("§7Gracz §9" + args[0] + " §7został pomyślnie zbanowany");
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            if (players.hasPermission("bxcore.commands.tempban") || players.isOp()) {
                                players.sendMessage("§7Gracz §9" + args[0] + " §7został zbanowany tymczasowo przez §9" + sender.getName() +  "§7 z powodem §9" + msg);
                            }
                        }
                        if (plugin.getConfig().getBoolean("logs")) {
                            plugin.getLogger().log(Level.INFO, "Gracz " + args[0] + " zostal zbanowany tymczasowo przez " + sender.getName() + " z powodem " + msg);
                        }
                        return false;
                    }
                    UUID uuid = cel.getUniqueId();
                    InetSocketAddress IPAdressPlayer = cel.getAddress();
                    String ip = IPAdressPlayer.toString();
                    String name = ip;
                    int entityTypeLenght = name.length() - 6;
                    String ipban = name.substring(0, entityTypeLenght);
                    band.getData().set(cel.getName() + ".tempban.adminstrator", sender.getName());
                    band.getData().set(cel.getName() + ".tempban.powod", msg);
                    band.getData().set(cel.getName() + ".tempban.nick", cel.getName());
                    band.getData().set(cel.getName() + ".tempban.uuid", uuid.toString());
                    band.getData().set(cel.getName() + ".tempban.ip", ipban);
                    band.getData().set(cel.getName() + ".tempban.data", format.format(now));
                    band.getData().set(cel.getName() + ".tempban.czas", czas);
                    try {
                        band.saveData();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    sender.sendMessage("§7Gracz §9" + cel.getName() + " §7został pomyślnie zbanowany");
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        if (players.hasPermission("bxcore.commands.tempban") || players.isOp()) {
                            players.sendMessage("§7Gracz §9" + cel.getName() + " §7został zbanowany tymczasowo przez §9" + sender.getName() +  "§7 z powodem §9" + msg);
                        }
                    }
                    if (plugin.getConfig().getBoolean("logs")) {
                        plugin.getLogger().log(Level.INFO, "Gracz " + cel.getName() + " zostal zbanowany tymczasowo przez " + sender.getName() + " z powodem " + msg);
                    }
                    cel.kickPlayer("\n§8• — • — • — • §9§lBAN §8• — • — • — •\n\n§7Nick: §9" + cel.getDisplayName() + "\n§7Powód: §9" + msg + "\n§7Administrator: §9" + sender.getName() + "\n\n§8• — • — • — • §f§l" + name1 + " §8• — • — • — •\n");
                } else {
                    sender.sendMessage("§7Gracz §9" + args[0] + " §7jest już zbanowany");
                }
            } else {
                sender.sendMessage("§7Poprawne użycie: §9/tempban [gracz] [czas] [powód]");
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.tempban");
        }
        return false;
    }
}
