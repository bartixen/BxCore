package pl.bartixen.bxcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

import java.util.logging.Level;

public class SpeedCommand implements CommandExecutor {

    Main plugin;

    public SpeedCommand(Main m) {
        plugin = m;
        m.getCommand("speed").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.speed") || sender.isOp()) {
            if (args.length == 1) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§7Poprawne użycie: §9/speed [liczba] [gracz]");
                    return false;
                }
                double liczba = Integer.parseInt(args[0]);
                Player p = (Player) sender;
                if (liczba == 1) {
                    p.setFlySpeed((float) 0.2);
                    p.setWalkSpeed((float) 0.2);
                    p.sendMessage("§7Twoja predkość została ustawiona na §9" + liczba);
                    if (plugin.getConfig().getBoolean("logs")) {
                        plugin.getLogger().log(Level.INFO, "Gracz " + p.getName() + " ustawil swoja predkosc na 1");
                    }
                } else {
                    if (liczba == 2) {
                        p.setFlySpeed((float) 0.3);
                        p.setWalkSpeed((float) 0.3);
                        p.sendMessage("§7Twoja predkość została ustawiona na §9" + liczba);
                        if (plugin.getConfig().getBoolean("logs")) {
                            plugin.getLogger().log(Level.INFO, "Gracz " + p.getName() + " ustawil swoja predkosc na 2");
                        }
                    } else {
                        if (liczba == 3) {
                            p.setFlySpeed((float) 0.4);
                            p.setWalkSpeed((float) 0.4);
                            p.sendMessage("§7Twoja predkość została ustawiona na §9" + liczba);
                            if (plugin.getConfig().getBoolean("logs")) {
                                plugin.getLogger().log(Level.INFO, "Gracz " + p.getName() + " ustawil swoja predkosc na 3");
                            }
                        } else {
                            if (liczba == 4) {
                                p.setFlySpeed((float) 0.5);
                                p.setWalkSpeed((float) 0.5);
                                p.sendMessage("§7Twoja predkość została ustawiona na §9" + liczba);
                                if (plugin.getConfig().getBoolean("logs")) {
                                    plugin.getLogger().log(Level.INFO, "Gracz " + p.getName() + " ustawil swoja predkosc na 4");
                                }
                            } else {
                                if (liczba == 5) {
                                    p.setFlySpeed((float) 0.6);
                                    p.setWalkSpeed((float) 0.6);
                                    p.sendMessage("§7Twoja predkość została ustawiona na §9" + liczba);
                                    if (plugin.getConfig().getBoolean("logs")) {
                                        plugin.getLogger().log(Level.INFO, "Gracz " + p.getName() + " ustawil swoja predkosc na 5");
                                    }
                                } else {
                                    if (liczba == 6) {
                                        p.setFlySpeed((float) 0.7);
                                        p.setWalkSpeed((float) 0.7);
                                        p.sendMessage("§7Twoja predkość została ustawiona na §9" + liczba);
                                        if (plugin.getConfig().getBoolean("logs")) {
                                            plugin.getLogger().log(Level.INFO, "Gracz " + p.getName() + " ustawil swoja predkosc na 6");
                                        }
                                    } else {
                                        if (liczba == 7) {
                                            p.setFlySpeed((float) 0.8);
                                            p.setWalkSpeed((float) 0.8);
                                            p.sendMessage("§7Twoja predkość została ustawiona na §9" + liczba);
                                            if (plugin.getConfig().getBoolean("logs")) {
                                                plugin.getLogger().log(Level.INFO, "Gracz " + p.getName() + " ustawil swoja predkosc na 7");
                                            }
                                        } else {
                                            if (liczba == 8) {
                                                p.setFlySpeed((float) 0.9);
                                                p.setWalkSpeed((float) 0.9);
                                                p.sendMessage("§7Twoja predkość została ustawiona na §9" + liczba);
                                                if (plugin.getConfig().getBoolean("logs")) {
                                                    plugin.getLogger().log(Level.INFO, "Gracz " + p.getName() + " ustawil swoja predkosc na 8");
                                                }
                                            } else {
                                                if (liczba == 9) {
                                                    p.setFlySpeed((float) 1.0);
                                                    p.setWalkSpeed((float) 1.0);
                                                    p.sendMessage("§7Twoja predkość została ustawiona na §9" + liczba);
                                                    if (plugin.getConfig().getBoolean("logs")) {
                                                        plugin.getLogger().log(Level.INFO, "Gracz " + p.getName() + " ustawil swoja predkosc na 9");
                                                    }
                                                } else {
                                                    if (liczba == 10) {
                                                        p.setFlySpeed((float) 1.0);
                                                        p.setWalkSpeed((float) 1.0);
                                                        p.sendMessage("§7Twoja predkość została ustawiona na §9" + liczba);
                                                        if (plugin.getConfig().getBoolean("logs")) {
                                                            plugin.getLogger().log(Level.INFO, "Gracz " + p.getName() + " ustawil swoja predkosc na 10");
                                                        }
                                                    } else {
                                                        sender.sendMessage("§7Podana liczba jest nieprawidłowa możesz podać od §91 §7do §910");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return false;
            } else {
                if (args.length == 2) {
                    Player cel = Bukkit.getPlayerExact(args[1]);
                    if (cel == null) {
                        sender.sendMessage("§7Ten gracz jest §coffline");
                        return true;
                    }
                    double liczba = Integer.parseInt(args[0]);
                    if (liczba == 1) {
                        cel.setFlySpeed((float) 0.1);
                        cel.setWalkSpeed((float) 0.2);
                        cel.sendMessage("§7Twoja predkość została ustawiona na §9" + liczba + " §7przez gracza §9" + sender.getName());
                        sender.sendMessage("§7Poprawne ustawiono predkość graczu §9" + cel.getName() + " §7na §9" + liczba);
                        if (plugin.getConfig().getBoolean("logs")) {
                            plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " ustawil swoja predkosc na 1 dla " + cel.getName());
                        }
                    } else {
                        if (liczba == 2) {
                            cel.setFlySpeed((float) 0.2);
                            cel.setWalkSpeed((float) 0.3);
                            cel.sendMessage("§7Twoja predkość została ustawiona na §9" + liczba + " §7przez gracza §9" + sender.getName());
                            sender.sendMessage("§7Poprawne ustawiono predkość graczu §9" + cel.getName() + " §7na §9" + liczba);
                            if (plugin.getConfig().getBoolean("logs")) {
                                plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " ustawil swoja predkosc na 2 dla " + cel.getName());
                            }
                        } else {
                            if (liczba == 3) {
                                cel.setFlySpeed((float) 0.3);
                                cel.setWalkSpeed((float) 0.4);
                                cel.sendMessage("§7Twoja predkość została ustawiona na §9" + liczba + " §7przez gracza §9" + sender.getName());
                                sender.sendMessage("§7Poprawne ustawiono predkość graczu §9" + cel.getName() + " §7na §9" + liczba);
                                if (plugin.getConfig().getBoolean("logs")) {
                                    plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " ustawil swoja predkosc na 3 dla " + cel.getName());
                                }
                            } else {
                                if (liczba == 4) {
                                    cel.setFlySpeed((float) 0.4);
                                    cel.setWalkSpeed((float) 0.5);
                                    cel.sendMessage("§7Twoja predkość została ustawiona na §9" + liczba + " §7przez gracza §9" + sender.getName());
                                    sender.sendMessage("§7Poprawne ustawiono predkość graczu §9" + cel.getName() + " §7na §9" + liczba);
                                    if (plugin.getConfig().getBoolean("logs")) {
                                        plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " ustawil swoja predkosc na 4 dla " + cel.getName());
                                    }
                                } else {
                                    if (liczba == 5) {
                                        cel.setFlySpeed((float) 0.5);
                                        cel.setWalkSpeed((float) 0.6);
                                        cel.sendMessage("§7Twoja predkość została ustawiona na §9" + liczba + " §7przez gracza §9" + sender.getName());
                                        sender.sendMessage("§7Poprawne ustawiono predkość graczu §9" + cel.getName() + " §7na §9" + liczba);
                                        if (plugin.getConfig().getBoolean("logs")) {
                                            plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " ustawil swoja predkosc na 5 dla " + cel.getName());
                                        }
                                    } else {
                                        if (liczba == 6) {
                                            cel.setFlySpeed((float) 0.6);
                                            cel.setWalkSpeed((float) 0.7);
                                            cel.sendMessage("§7Twoja predkość została ustawiona na §9" + liczba + " §7przez gracza §9" + sender.getName());
                                            sender.sendMessage("§7Poprawne ustawiono predkość graczu §9" + cel.getName() + " §7na §9" + liczba);
                                            if (plugin.getConfig().getBoolean("logs")) {
                                                plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " ustawil swoja predkosc na 6 dla " + cel.getName());
                                            }
                                        } else {
                                            if (liczba == 7) {
                                                cel.setFlySpeed((float) 0.7);
                                                cel.setWalkSpeed((float) 0.8);
                                                cel.sendMessage("§7Twoja predkość została ustawiona na §9" + liczba + " §7przez gracza §9" + sender.getName());
                                                sender.sendMessage("§7Poprawne ustawiono predkość graczu §9" + cel.getName() + " §7na §9" + liczba);
                                                if (plugin.getConfig().getBoolean("logs")) {
                                                    plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " ustawil swoja predkosc na 7 dla " + cel.getName());
                                                }
                                            } else {
                                                if (liczba == 8) {
                                                    cel.setFlySpeed((float) 0.8);
                                                    cel.setWalkSpeed((float) 0.9);
                                                    cel.sendMessage("§7Twoja predkość została ustawiona na §9" + liczba + " §7przez gracza §9" + sender.getName());
                                                    sender.sendMessage("§7Poprawne ustawiono predkość graczu §9" + cel.getName() + " §7na §9" + liczba);
                                                    if (plugin.getConfig().getBoolean("logs")) {
                                                        plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " ustawil swoja predkosc na 8 dla " + cel.getName());
                                                    }
                                                } else {
                                                    if (liczba == 9) {
                                                        cel.setFlySpeed((float) 0.9);
                                                        cel.setWalkSpeed((float) 1.0);
                                                        cel.sendMessage("§7Twoja predkość została ustawiona na §9" + liczba + " §7przez gracza §9" + sender.getName());
                                                        sender.sendMessage("§7Poprawne ustawiono predkość graczu §9" + cel.getName() + " §7na §9" + liczba);
                                                        if (plugin.getConfig().getBoolean("logs")) {
                                                            plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " ustawil swoja predkosc na 9 dla " + cel.getName());
                                                        }
                                                    } else {
                                                        if (liczba == 10) {
                                                            cel.setFlySpeed((float) 1.0);
                                                            cel.setWalkSpeed((float) 1.0);
                                                            cel.sendMessage("§7Twoja predkość została ustawiona na §9" + liczba + " §7przez gracza §9" + sender.getName());
                                                            sender.sendMessage("§7Poprawne ustawiono predkość graczu §9" + cel.getName() + " §7na §9" + liczba);
                                                            if (plugin.getConfig().getBoolean("logs")) {
                                                                plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " ustawil swoja predkosc na 10 dla " + cel.getName());
                                                            }
                                                        } else {
                                                            sender.sendMessage("§7Podana liczba jest nieprawidłowa możesz podać od §91 §7do §910");
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    sender.sendMessage("§7Poprawne użycie: §9/speed [liczba]");
                }
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.speed");
        }
        return false;
    }
}