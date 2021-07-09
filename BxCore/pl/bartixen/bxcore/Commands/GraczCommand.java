package pl.bartixen.bxcore.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.bartixen.bxauth.Data.DataManager;
import pl.bartixen.bxcore.Ban.BanIPCommand;
import pl.bartixen.bxcore.Ban.BanTimeChecker;
import pl.bartixen.bxcore.Ban.MuteTimeChecker;
import pl.bartixen.bxcore.Data.BanDataManager;
import pl.bartixen.bxcore.Data.UserDataManager;
import pl.bartixen.bxcore.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

public class GraczCommand implements CommandExecutor {

    Main plugin;
    static BanDataManager band;
    static UserDataManager userd;
    static DataManager data;

    public ArrayList<String> kontaregister = new ArrayList<>();

    public GraczCommand(Main m) {
        plugin = m;
        m.getCommand("gracz").setExecutor(this);
        m.getCommand("player").setExecutor(this);
        band = BanDataManager.getInstance();
        userd = UserDataManager.getInstance();
        data = DataManager.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.gracz") || sender.isOp()) {
            if (args.length == 1) {
                sender.sendMessage("§7Wyszukiwanie gracza: §9" + args[0]);
                sender.sendMessage("§8 • — • — • — • ");

                String nick = "brak";
                String firstip = "brak";
                String lastip = "brak";
                String uuid = "brak";
                String world = "brak";
                int x = 0;
                int y = 0;
                int z = 0;
                int yaw = 0;
                int pitch = 0;
                String register = "brak";
                String dataregister = "brak";
                String timelogin = "brak";
                String lastlogin = "brak";
                String lastplay = "brak";
                String session = "brak";
                String multikonta = "brak";
                String pierwszewijsie = "brak";
                String premium = "brak";

                if (((band.getData().getString(args[0])) == null) && ((userd.getData().getString(args[0])) == null)) {
                    sender.sendMessage("§7Brak danych o graczu §9" + args[0]);
                    sender.sendMessage("§8 • — • — • — • ");
                    return false;
                }
                if ((userd.getData().getString(args[0])) != null) {
                    nick = args[0];
                    String name = userd.getData().getString(args[0] + ".first_ip");
                    int entityTypeLenght = name.length() - 4;
                    firstip = name.substring(0, entityTypeLenght) + "****";
                    String name1 = userd.getData().getString(args[0] + ".last_ip");
                    int entityTypeLenght1 = name.length() - 4;
                    lastip = name1.substring(0, entityTypeLenght1) + "****";
                    uuid = userd.getData().getString(args[0] + ".uuid");
                    world = data.getData().getString(uuid + ".lastlocation.world");
                    x = data.getData().getInt(uuid + ".lastlocation.x");
                    y = data.getData().getInt(uuid + ".lastlocation.y");
                    z = data.getData().getInt(uuid + ".lastlocation.z");
                    yaw = data.getData().getInt(uuid + ".lastlocation.yaw");
                    pitch = data.getData().getInt(uuid + ".lastlocation.pitch");
                    if (data.getData().getBoolean(uuid + ".register")) {
                        register = "zarejestrowany";
                    } else {
                        register = "nie zarejestrowany";
                    }
                    if ((data.getData().getString(uuid + ".data_register")) != null) {
                        dataregister = data.getData().getString(uuid + ".data_register");
                    }
                    timelogin = data.getData().getString(uuid + ".logintime");
                    lastlogin = data.getData().getString(uuid + ".lastlogin");
                    lastplay = data.getData().getString(uuid + ".lastplay");
                    if ((data.getData().getString("sessions." + nick)) != null) {
                        session = "aktywna";
                    } else {
                        session = "nieaktywna";
                    }
                    if (((data.getData().getString(uuid + ".first_ip").equals((data.getData().getString(uuid + ".last_ip")))))) {
                        String ip = (data.getData().getString(uuid + ".first_ip")).replace("/", "").replace(".", "");
                        if (data.getData().getBoolean("useripregister." + ip + ".multikonta")) {
                            multikonta = "może tworzyć multikonta";
                        } else {
                            multikonta = "nie może tworzyć multikont";
                        }
                    } else {
                        multikonta = "gracz ma zmienne IP";
                    }
                    String ipdospr = name.replace("/", "").replace(".", "");
                    kontaregister = new ArrayList<>(data.getData().getStringList("useripregister." + ipdospr + ".gracze"));
                    pierwszewijsie = data.getData().getString(uuid + ".data_first_join");
                    if (data.getData().getBoolean(uuid + ".premium")) {
                        premium = "platne";
                    } else {
                        premium = "darmowe";
                    }
                }

                sender.sendMessage("§7Nick: §9" + nick);
                sender.sendMessage("§7UUID: §9" + uuid);
                sender.sendMessage("§7Konto: §9" + premium);
                sender.sendMessage("§7Pierwszy raz weszedl na serwer: §9" + pierwszewijsie);
                sender.sendMessage("§7Ostatni raz na serwerze: §9" + lastplay);
                sender.sendMessage("§7IP (pierwsze): §9" + firstip);
                sender.sendMessage("§7IP (ostatnie): §9" + lastip);
                sender.sendMessage("§7Ostatnia lokalizacja:");
                sender.sendMessage("  §7Świat: §9" + world);
                sender.sendMessage("  §7X: §9" + x + " §7Y: §9" + y + " §7Z: §9" + z);
                sender.sendMessage("  §7Yaw: §9" + yaw + " §7Pitch: §9" + pitch);
                sender.sendMessage("§7Kary:");

                if ((band.getData().getString(args[0] + ".tempban")) != null) {
                    try {
                        if (BanTimeChecker.check(args[0])) {
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if ((band.getData().getString(args[0] + ".tempmute")) != null) {
                    try {
                        if (MuteTimeChecker.check(args[0])) {
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if ((band.getData().getString(args[0] + ".permban")) != null) {
                    String msg = band.getData().getString(args[0] + ".permban.powod");
                    String admin = band.getData().getString(args[0] + ".permban.adminstrator");
                    sender.sendMessage("  §7Blokada: §9aktywna");
                    sender.sendMessage("    §7Powód: §9" + msg);
                    sender.sendMessage("    §7Administrator: §9" + admin);
                    sender.sendMessage("    §7Wygasa: §9nigdy");
                } else {
                    if ((band.getData().getString(args[0] + ".tempban")) != null) {
                        String msg = band.getData().getString(args[0] + ".tempban.powod");
                        String admin = band.getData().getString(args[0] + ".tempban.adminstrator");
                        String czas = BanTimeChecker.getTime();
                        sender.sendMessage("  §7Blokada: §9aktywna");
                        sender.sendMessage("    §7Powód: §9" + msg);
                        sender.sendMessage("    §7Administrator: §9" + admin);
                        sender.sendMessage("    §7Wygasa: §9" + czas);
                    } else {
                        if ((band.getData().getString(args[0] + ".banip")) != null) {
                            String msg = band.getData().getString(args[0] + ".banip.powod");
                            String admin = band.getData().getString(args[0] + ".banip.adminstrator");
                            sender.sendMessage("  §7Blokada: §9aktywna");
                            sender.sendMessage("    §7Powód: §9" + msg);
                            sender.sendMessage("    §7Administrator: §9" + admin);
                        } else {
                            if ((userd.getData().getString(args[0])) != null) {
                                String adresip = userd.getData().getString(args[0] + ".last_ip");
                                if (BanIPCommand.banip.contains(adresip)) {
                                    sender.sendMessage("  §7Blokada: §9aktywna");
                                    sender.sendMessage("    §9Adres IP gracza zapisany jest na blacklist");
                                } else {
                                    sender.sendMessage("  §7Blokada: §9brak");
                                }
                            } else {
                                sender.sendMessage("  §7Blokada: §9brak");
                            }
                        }
                    }
                }
                if ((band.getData().getString(args[0] + ".mute")) != null) {
                    String msg = band.getData().getString(args[0] + ".mute.powod");
                    String admin = band.getData().getString(args[0] + ".mute.adminstrator");
                    sender.sendMessage("  §7Wyciszenie: §9aktywne");
                    sender.sendMessage("    §7Powód: §9" + msg);
                    sender.sendMessage("    §7Administrator: §9" + admin);
                    sender.sendMessage("    §7Wygasa: §9nigdy");
                } else {
                    if ((band.getData().getString(args[0] + ".tempmute")) != null) {
                        String msg = band.getData().getString(args[0] + ".tempmute.powod");
                        String admin = band.getData().getString(args[0] + ".tempmute.adminstrator");
                        String czas = MuteTimeChecker.getTime();
                        sender.sendMessage("  §7Wyciszenie: §9aktywne");
                        sender.sendMessage("    §7Powód: §9" + msg);
                        sender.sendMessage("    §7Administrator: §9" + admin);
                        sender.sendMessage("    §7Wygasa: §9" + czas);
                    } else {
                        sender.sendMessage("  §7Wyciszenie: §9brak");
                    }
                }
                if (WhitelistCommand.whitelist.contains(args[0])) {
                    sender.sendMessage("§7Whitelist: §9dodany");
                } else {
                    sender.sendMessage("§7Whitelist: §9nie dodany");
                }
                sender.sendMessage("§7Uwierzytelnianie gracza:");
                sender.sendMessage("  §7Status: §9" + register);
                sender.sendMessage("  §7Data rejestracji: §9" + dataregister);
                sender.sendMessage("  §7Ostani czas logowania: §9" + timelogin + "s");
                sender.sendMessage("  §7Ostanie logowanie na serwerze: §9" + lastlogin);
                sender.sendMessage("  §7Sesja uwierzytelniania: §9" + session);
                sender.sendMessage("  §7Multikonta: §9" + multikonta);
                sender.sendMessage("  §7Konta zarejestrowane na adresie IP gracza: §9" + kontaregister);
                sender.sendMessage("§8 • — • — • — • ");
                if (plugin.getConfig().getBoolean("logs")) {
                    plugin.getLogger().log(Level.WARNING, "Gracz " + sender.getName() + " uzyskal dostep do wszystkich danych gracza " + args[0]);
                }
            } else {
                sender.sendMessage("§7Poprawne użycie: §9/gracz [gracz]");
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.gracz");
        }
        return false;
    }

}
