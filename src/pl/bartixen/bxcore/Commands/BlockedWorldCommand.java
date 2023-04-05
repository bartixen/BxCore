package pl.bartixen.bxcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

import java.util.logging.Level;

public class BlockedWorldCommand implements CommandExecutor {

    Main plugin;

    public BlockedWorldCommand(Main m) {
        plugin = m;
        m.getCommand("blockworld").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.blockworld") || sender.isOp()) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("nether")) {
                    if (plugin.getConfig().getBoolean("blockworld.nether.blocked")) {
                        plugin.getConfig().set("blockworld.nether.blocked", false);
                        plugin.saveConfig();
                        sender.sendMessage("§7Świat §9NETHER §7został odblokowany");
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.sendTitle("§f§lŚwiat §9§lNETHER §f§lzostał §a§lodblokowany", "", 5, 50, 5);
                        }
                        if (plugin.getConfig().getBoolean("logs")) {
                            plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " odblokowal swiat NETHER");
                        }
                    } else {
                        plugin.getConfig().set("blockworld.nether.blocked", true);
                        plugin.saveConfig();
                        sender.sendMessage("§7Świat §9NETHER §7został zablokowany");
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.sendTitle("§f§lŚwiat §9§lNETHER §f§lzostał §c§lzablokowany", "", 5, 50, 5);
                        }
                        if (plugin.getConfig().getBoolean("logs")) {
                            plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " zablokowal swiat NETHER");
                        }
                    }
                } else {
                    if (args[0].equalsIgnoreCase("end")) {
                        if (plugin.getConfig().getBoolean("blockworld.end.blocked")) {
                            plugin.getConfig().set("blockworld.end.blocked", false);
                            plugin.saveConfig();
                            sender.sendMessage("§7Świat §9END §7zostal odblokowany");
                            for (Player players : Bukkit.getOnlinePlayers()) {
                                players.sendTitle("§f§lŚwiat §9§lEND §f§lzostał §a§lodblokowany", "", 5, 50, 5);
                            }
                            if (plugin.getConfig().getBoolean("logs")) {
                                plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " odblokowal swiat END");
                            }
                        } else {
                            plugin.getConfig().set("blockworld.end.blocked", true);
                            plugin.saveConfig();
                            sender.sendMessage("§7Świat §9END §7zostal zablokowany");
                            for (Player players : Bukkit.getOnlinePlayers()) {
                                players.sendTitle("§f§lŚwiat §9§lEND §f§lzostał §c§lzablokowany", "", 5, 50, 5);
                            }
                            if (plugin.getConfig().getBoolean("logs")) {
                                plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " zablokowal swiat END");
                            }
                        }
                    } else {
                        sender.sendMessage("§7Poprawne użycie: §9/blockworld [nether,end]");
                    }
                }
            } else {
                sender.sendMessage("§7Poprawne użycie: §9/blockworld [nether,end]");
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.blockworld");
        }
        return false;
    }
}
