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

public class UnBanCommand implements CommandExecutor {

    Main plugin;

    static BanDataManager band;

    public UnBanCommand(Main m) {
        plugin = m;
        m.getCommand("unban").setExecutor(this);
        band = BanDataManager.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.unban") || sender.isOp()) {
            if (args.length == 1) {
                Date now = new Date();
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
                if ((band.getData().getString(args[0] + ".permban")) != null) {
                    band.getData().set(args[0] + ".permban", null);
                    band.getData().set(args[0] + ".history.lastunban.administrator", sender.getName());
                    band.getData().set(args[0] + ".history.lastunban.data", format.format(now));
                    try {
                        band.saveData();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    sender.sendMessage("§7Gracz §9" + args[0] + " §7został pomyślnie odbanowany");
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        if (players.hasPermission("bxcore.commands.unban") || players.isOp()) {
                            players.sendMessage("§7Gracz §9" + args[0] + " §7został odbanowany przez §9" + sender.getName());
                        }
                    }
                    if (plugin.getConfig().getBoolean("logs")) {
                        plugin.getLogger().log(Level.INFO, "Gracz " + args[0] + " zostal odbanowany przez " + sender.getName());
                    }
                } else {
                    if ((band.getData().getString(args[0] + ".tempban")) != null) {
                        band.getData().set(args[0] + ".tempban", null);
                        band.getData().set(args[0] + ".history.lastunban.administrator", sender.getName());
                        band.getData().set(args[0] + ".history.lastunban.data", format.format(now));
                        try {
                            band.saveData();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        sender.sendMessage("§7Gracz §9" + args[0] + " §7został pomyślnie odbanowany");
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            if (players.hasPermission("bxcore.commands.unban") || players.isOp()) {
                                players.sendMessage("§7Gracz §9" + args[0] + " §7został odbanowany przez §9" + sender.getName());
                            }
                        }
                        if (plugin.getConfig().getBoolean("logs")) {
                            plugin.getLogger().log(Level.INFO, "Gracz " + args[0] + " zostal odbanowany przez " + sender.getName());
                        }
                    } else {
                        if ((band.getData().getString(args[0] + ".banip")) != null) {
                            String adres = band.getData().getString(args[0] + ".banip.ip");
                            band.getData().set(args[0] + ".banip", null);
                            band.getData().set(args[0] + ".history.lastunblacklist.administrator", sender.getName());
                            band.getData().set(args[0] + ".history.lastunblacklist.data", format.format(now));
                            BanIPCommand.banip.remove(adres);
                            band.getData().set("banip", BanIPCommand.banip);
                            try {
                                band.saveData();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            sender.sendMessage("§7Gracz §9" + args[0] + " §7został pomyślnie usuniety z blacklisty");
                            for (Player players : Bukkit.getOnlinePlayers()) {
                                if (players.hasPermission("bxcore.commands.unban") || players.isOp()) {
                                    players.sendMessage("§7Gracz §9" + args[0] + " §7został usuniety z blacklisty przez §9" + sender.getName());
                                }
                            }
                            if (plugin.getConfig().getBoolean("logs")) {
                                plugin.getLogger().log(Level.INFO, "Gracz " + args[0] + " zostal usuniety z blacklisty przez " + sender.getName());
                            }
                        } else {
                            if (BanIPCommand.banip.contains(args[0])) {
                                BanIPCommand.banip.remove(args[0]);
                                band.getData().set("banip", BanIPCommand.banip);
                                try {
                                    band.saveData();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                sender.sendMessage("§7Adres IP §9" + args[0] + " §7został pomyślnie usuniety z blacklisty");
                                for (Player players : Bukkit.getOnlinePlayers()) {
                                    if (players.hasPermission("bxcore.commands.banip") || players.isOp()) {
                                        players.sendMessage("§7Adres IP §9" + args[0] + " §7został usuniety z blacklisty przez §9" + sender.getName());
                                    }
                                }
                                if (plugin.getConfig().getBoolean("logs")) {
                                    plugin.getLogger().log(Level.INFO, "Adres IP " + args[0] + " zostal usuniety z blacklisty przez " + sender.getName());
                                }
                            } else {
                                if (BanIPCommand.banip.contains("/" + args[0])) {
                                    BanIPCommand.banip.remove("/" + args[0]);
                                    band.getData().set("banip", BanIPCommand.banip);
                                    try {
                                        band.saveData();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    sender.sendMessage("§7Adres IP §9/" + args[0] + " §7został pomyślnie usuniety z blacklisty");
                                    for (Player players : Bukkit.getOnlinePlayers()) {
                                        if (players.hasPermission("bxcore.commands.banip") || players.isOp()) {
                                            players.sendMessage("§7Adres IP §9/" + args[0] + " §7został usuniety z blacklisty przez §9" + sender.getName());
                                        }
                                    }
                                    if (plugin.getConfig().getBoolean("logs")) {
                                        plugin.getLogger().log(Level.INFO, "Adres IP /" + args[0] + " zostal usuniety z blacklisty przez " + sender.getName());
                                    }
                                } else {
                                    sender.sendMessage("§7Gracz/IP §9" + args[0] + " §7nie jest zbanowany");
                                }
                            }
                        }
                    }
                }
            } else {
                sender.sendMessage("§7Poprawne użycie: §9/unban [gracz]");
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.unban");
        }
        return false;
    }
}
