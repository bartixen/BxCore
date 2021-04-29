package pl.bartixen.bxcore.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.bartixen.bxcore.Main;

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
                        sender.sendMessage("§7Świat §9NETHER §7zostal odblokowany");
                    } else {
                        plugin.getConfig().set("blockworld.nether.blocked", true);
                        plugin.saveConfig();
                        sender.sendMessage("§7Świat §9NETHER §7zostal zablokowany");
                    }
                } else {
                    if (args[0].equalsIgnoreCase("end")) {
                        if (plugin.getConfig().getBoolean("blockworld.end.blocked")) {
                            plugin.getConfig().set("blockworld.end.blocked", false);
                            plugin.saveConfig();
                            sender.sendMessage("§7Świat §9END §7zostal odblokowany");
                        } else {
                            plugin.getConfig().set("blockworld.end.blocked", true);
                            plugin.saveConfig();
                            sender.sendMessage("§7Świat §9END §7zostal zablokowany");
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
