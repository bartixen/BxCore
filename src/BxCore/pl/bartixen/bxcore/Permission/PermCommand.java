package pl.bartixen.bxcore.Permission;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Data.UserDataManager;
import pl.bartixen.bxcore.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class PermCommand implements CommandExecutor {

    Main plugin;

    PermissionConfig permd;
    UserDataManager userd;

    public PermCommand(Main m) {
        plugin = m;
        m.getCommand("perm").setExecutor(this);
        m.getCommand("permission").setExecutor(this);
        m.getCommand("pex").setExecutor(this);
        permd = PermissionConfig.getInstance();
        userd = UserDataManager.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.perm") || sender.isOp()) {
            if (args.length > 1) {
                if (args[0].equalsIgnoreCase("ustaw") || args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("set")) {
                    if (args.length == 3) {
                        Player cel = Bukkit.getPlayerExact(args[1]);
                        if (cel != null) {
                            if (permd.getData().getString("groups." + args[2]) != null) {
                                UUID uuid = cel.getUniqueId();
                                permd.getData().set("users." + uuid, args[2]);
                                try {
                                    permd.saveData();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                sender.sendMessage("§7Gracz §9" + cel.getDisplayName() + " §7dostal range §9" + args[2]);
                            } else {
                                sender.sendMessage("§cNie znaleziono rangi");
                                return false;
                            }
                        } else {
                            if (userd.getData().getString(args[1]) != null) {
                                if (permd.getData().getString("groups." + args[2]) != null) {
                                    String uuid = userd.getData().getString(args[1] + ".uuid");
                                    permd.getData().set("users." + uuid, args[2]);
                                    try {
                                        permd.saveData();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    sender.sendMessage("§7Gracz §9" + args[1] + " §7dostal range §9" + args[2]);
                                } else {
                                    sender.sendMessage("§cNie znaleziono rangi");
                                    return false;
                                }
                            } else {
                                if (permd.getData().getString("groups." + args[2]) != null) {
                                    permd.getData().set("expectancy." + args[1], args[2]);
                                    try {
                                        permd.saveData();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    sender.sendMessage("§7Gracza §9" + args[1] + " §7nigdy jeszcze nie bylo na serwerze wiec jak wejdzie ranga §9" + args[2] + " §7zostanie mu nadana automatycznie");
                                } else {
                                    sender.sendMessage("§cNie znaleziono rangi");
                                    return false;
                                }
                            }
                        }
                    } else {
                        sender.sendMessage("§7Poprawne użycie: §9/perm [ustaw,usun,info] [gracz] (ranga)");
                        return false;
                    }
                } else {
                    if (args[0].equalsIgnoreCase("usun") || args[0].equalsIgnoreCase("remove")) {
                        if (args.length == 2) {
                            Player cel = Bukkit.getPlayerExact(args[1]);
                            if (cel != null) {
                                UUID uuid = cel.getUniqueId();
                                permd.getData().set("users." + uuid, permd.getData().getString("default"));
                                try {
                                    permd.saveData();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                sender.sendMessage("§7Gracz §9" + cel.getDisplayName() + " §7zostal usuniety z rangi");
                            } else {
                                if (userd.getData().getString(args[1]) != null) {
                                    String uuid = userd.getData().getString(args[1] + ".uuid");
                                    permd.getData().set("users." + uuid, permd.getData().getString("default"));
                                    try {
                                        permd.saveData();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    sender.sendMessage("§7Gracz §9" + args[1] + " §7zostal usuniety z rangi");
                                } else {
                                    if (permd.getData().getString("expectancy." + args[1]) != null) {
                                        permd.getData().set("expectancy." + args[1], null);
                                        try {
                                            permd.saveData();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        sender.sendMessage("§7Gracz §9" + args[1] + " §7zostal usuniety z zaplanowanej rangi");
                                    } else {
                                        sender.sendMessage("§7Gracza §9" + args[1] + " §7nigdy jeszcze nie bylo na serwerze i nie ma zaplanowanych rang");
                                    }
                                }
                            }
                        } else {
                            sender.sendMessage("§7Poprawne użycie: §9/perm [ustaw,usun,info] [gracz] (ranga)");
                            return false;
                        }
                    } else {
                        if (args[0].equalsIgnoreCase("info")) {
                            Player cel = Bukkit.getPlayerExact(args[1]);
                            if (cel != null) {
                                UUID uuid = cel.getUniqueId();
                                String rank = permd.getData().getString("users." + uuid);
                                String prefix = permd.getData().getString("groups." + rank + ".prefix");
                                ArrayList<String> listPermission = new ArrayList<>(permd.getData().getStringList("groups." + rank + ".permissions"));
                                sender.sendMessage("§7Gracz §9" + cel.getDisplayName() + " §7posiada range §9" + rank);
                                sender.sendMessage("§7W tym permisje: §9" + listPermission);
                            } else {
                                if (userd.getData().getString(args[1]) != null) {
                                    String uuid = userd.getData().getString(args[1] + ".uuid");
                                    String rank = permd.getData().getString("users." + uuid);
                                    String prefix = permd.getData().getString("groups." + rank + ".prefix");
                                    ArrayList<String> listPermission = new ArrayList<>(permd.getData().getStringList("groups." + rank + ".permissions"));
                                    sender.sendMessage("§7Gracz §9" + args[1] + " §7posiada range §9" + rank);
                                    sender.sendMessage("§7W tym permisje: §9" + listPermission);
                                } else {
                                    if (permd.getData().getString("expectancy." + args[1]) != null) {
                                        String rank = permd.getData().getString("expectancy." + args[1]);
                                        String prefix = permd.getData().getString("groups." + rank + ".prefix");
                                        ArrayList<String> listPermission = new ArrayList<>(permd.getData().getStringList("groups." + rank + ".permissions"));
                                        sender.sendMessage("§7Gracz §9" + args[1] + " §7posiada zaplanowana range §9" + rank);
                                        sender.sendMessage("§7W tym permisje: §9" + listPermission);
                                    } else {
                                        sender.sendMessage("§7Gracza §9" + args[1] + " §7nigdy jeszcze nie bylo na serwerze i nie ma zaplanowanych rang");
                                    }
                                }
                            }
                        } else {
                            sender.sendMessage("§7Poprawne użycie: §9/perm [ustaw,usun,info] [gracz] (ranga)");
                            return false;
                        }
                    }
                }
            } else {
                sender.sendMessage("§7Poprawne użycie: §9/perm [ustaw,usun,info] [gracz] (ranga)");
                return false;
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.perm");
        }
        return false;
    }

}
