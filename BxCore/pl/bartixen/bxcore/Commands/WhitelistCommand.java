package pl.bartixen.bxcore.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import pl.bartixen.bxcore.Data.WhitelistDataManager;
import pl.bartixen.bxcore.Main;

import java.io.IOException;
import java.util.ArrayList;

public class WhitelistCommand implements CommandExecutor, Listener {

    Main plugin;
    static WhitelistDataManager wld;

    public static ArrayList<String> whitelist = new ArrayList<>();

    public WhitelistCommand(Main m) throws IOException {
        plugin = m;
        m.getCommand("whitelist").setExecutor(this);
        m.getCommand("wl").setExecutor(this);
        wld = WhitelistDataManager.getInstance();
        whitelist = new ArrayList<>(wld.getData().getStringList("lista"));
        if ((wld.getData().getString("ustawienia")) == null) {
            wld.getData().set("ustawienia.status", false);
            wld.getData().set("ustawienia.wiadomosc", "Nie ma ciebie na whitelist");
            wld.saveData();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPreLogin(AsyncPlayerPreLoginEvent e) throws IOException {
        String wlaczona = "true";
        if ((wld.getData().getString("ustawienia.status")) == wlaczona) {
            if (!(whitelist.contains(e.getName()))) {
                String message = wld.getData().getString("ustawienia.wiadomosc");
                e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, message);
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.whitelist") || sender.isOp()) {
            if (args.length > 0) {
                if ((args[0].equalsIgnoreCase("on")) || (args[0].equalsIgnoreCase("wlacz"))) {
                    String wlaczona = "true";
                    if ((wld.getData().getString("ustawienia.status")) != wlaczona) {
                        wld.getData().set("ustawienia.status", true);
                        try {
                            wld.saveData();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        sender.sendMessage("§7Whitelist zostala §9wlaczona");
                    } else {
                        sender.sendMessage("§7Whitelista jest już wlaczona");
                    }
                } else {
                    if ((args[0].equalsIgnoreCase("off")) || (args[0].equalsIgnoreCase("wylacz"))) {
                        String wylaczona = "false";
                        if ((wld.getData().getString("ustawienia.status")) != wylaczona) {
                            wld.getData().set("ustawienia.status", false);
                            try {
                                wld.saveData();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            sender.sendMessage("§7Whitelist zostala §9wylaczona");
                        } else {
                            sender.sendMessage("§7Whitelista jest już wylaczona");
                        }
                    } else {
                        if ((args[0].equalsIgnoreCase("add")) || (args[0].equalsIgnoreCase("dodaj"))) {
                            if (args.length == 2) {
                                if (!(whitelist.contains(args[1]))) {
                                    whitelist.add(args[1]);
                                    wld.getData().set("lista", whitelist);
                                    try {
                                        wld.saveData();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    sender.sendMessage("§7Dodano gracza §9" + args[1] + " §7do whitelisty");
                                } else {
                                    sender.sendMessage("§7Gracz §9" + args[1] + " §7jest już na whitelist");
                                }
                            } else {
                                sender.sendMessage("§7Poprawne użycie: §9/whitelist [on,off,list,add,remove,reset,message]");
                            }
                        } else {
                            if ((args[0].equalsIgnoreCase("remove")) || (args[0].equalsIgnoreCase("usun"))) {
                                if (args.length == 2) {
                                    if (whitelist.contains(args[1])) {
                                        whitelist.remove(args[1]);
                                        wld.getData().set("lista", whitelist);
                                        try {
                                            wld.saveData();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        sender.sendMessage("§7Usunieto gracza §9" + args[1] + " §7z whitelisty");
                                    } else {
                                        sender.sendMessage("§7Gracza §9" + args[1] + " §7nie ma na whitelist");
                                    }
                                } else {
                                    sender.sendMessage("§7Poprawne użycie: §9/whitelist [on,off,list,add,remove,reset,message]");
                                }
                            } else {
                                if ((args[0].equalsIgnoreCase("reset")) || (args[0].equalsIgnoreCase("wyczyszcz"))) {
                                    wld.getData().set("lista", null);
                                    try {
                                        wld.saveData();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    sender.sendMessage("§7Poprawnie §9usunieto wszystkich §7z whitelisty");
                                } else {
                                    if ((args[0].equalsIgnoreCase("message")) || (args[0].equalsIgnoreCase("wiadomosc"))) {
                                        StringBuilder sb = new StringBuilder();
                                        for (int i = 1; i < args.length; ++i) {
                                            sb.append(args[i]).append(" ");
                                        }
                                        String msg = sb.toString().replace("&", "§");
                                        wld.getData().set("ustawienia.wiadomosc", msg);
                                        try {
                                            wld.saveData();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        sender.sendMessage("§7Wiadomość whitelisty zostala ustawiona na: §9" + sb.toString());
                                    } else {
                                        if ((args[0].equalsIgnoreCase("list")) || (args[0].equalsIgnoreCase("lista"))) {
                                            sender.sendMessage("§7Lista graczy: (§9" + whitelist.size() + "§7) §9" + whitelist);
                                        } else {
                                            sender.sendMessage("§7Poprawne użycie: §9/whitelist [on,off,list,add,remove,reset,message]");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                sender.sendMessage("§7Poprawne użycie: §9/whitelist [on,off,list,add,remove,reset,message]");
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.whitelist");
        }
        return false;
    }
}
