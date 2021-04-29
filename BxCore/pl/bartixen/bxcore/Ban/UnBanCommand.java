package pl.bartixen.bxcore.Ban;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.bartixen.bxcore.Data.BanDataManager;
import pl.bartixen.bxcore.Main;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
                    sender.sendMessage("§7Gracz §9" + args[0] + " §7zostal pomyślnie odbanowany");
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
                        sender.sendMessage("§7Gracz §9" + args[0] + " §7zostal pomyślnie odbanowany");
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
                            sender.sendMessage("§7Gracz §9" + args[0] + " §7zostal pomyślnie usuniety z blacklisty");
                        } else {
                            if (BanIPCommand.banip.contains(args[0])) {
                                BanIPCommand.banip.remove(args[0]);
                                band.getData().set("banip", BanIPCommand.banip);
                                try {
                                    band.saveData();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                sender.sendMessage("§7Adres IP §9" + args[0] + " §7zostal pomyślnie usuniety z blacklisty");
                            } else {
                                if (BanIPCommand.banip.contains("/" + args[0])) {
                                    BanIPCommand.banip.remove("/" + args[0]);
                                    band.getData().set("banip", BanIPCommand.banip);
                                    try {
                                        band.saveData();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    sender.sendMessage("§7Adres IP §9/" + args[0] + " §7zostal pomyślnie usuniety z blacklisty");
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
