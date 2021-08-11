package pl.bartixen.bxcore.Commands;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

import java.util.logging.Level;

public class VanishCommand implements CommandExecutor {

    Main plugin;

    public VanishCommand(Main m) {
        plugin = m;
        m.getCommand("v").setExecutor(this);
        m.getCommand("vanish").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (args.length == 1) {
                Player cel = Bukkit.getPlayerExact(args[0]);
                if (cel == null) {
                    sender.sendMessage("§7Ten gracz jest §coffline");
                    return true;
                }
                if (plugin.invisible.contains(cel)) {
                    plugin.invisible.remove(cel);
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.showPlayer(cel);
                    }
                    cel.sendMessage("§7Jesteś teraz §cwidoczny §7przez §9" + sender.getName());
                    sender.sendMessage("§7Gracz §9" + cel.getName() + " §7jest teraz §cwidoczny");
                    if (plugin.getConfig().getBoolean("logs")) {
                        plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " wylaczyl vanish dla " + cel.getName());
                    }
                } else {
                    plugin.invisible.add(cel);
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        if (!(players.hasPermission("bxcore.commands.vanish"))) {
                            players.hidePlayer(cel);
                        }
                    }
                    cel.sendMessage("§7Jesteś teraz §aniewidoczny §7przez §9" + sender.getName());
                    sender.sendMessage("§7Gracz §9" + cel.getName() + " §7jest teraz §aniewidoczny");
                        if (plugin.getConfig().getBoolean("logs")) {
                            plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " wlaczyl vanish dla " + cel.getName());
                        }
                }
            } else {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§7Poprawne użycie: §9/v [gracz]");
                    return false;
                }
                Player p = (Player) sender;
                if (p.hasPermission("bxcore.commands.vanish") || p.isOp()) {
                    if (plugin.invisible.contains(p)) {
                        plugin.invisible.remove(p);
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.showPlayer(p);
                        }
                        p.sendMessage("§7Jesteś teraz §cwidoczny");
                        if (plugin.getConfig().getBoolean("logs")) {
                            plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " wylaczyl vanish");
                        }
                    } else {
                        plugin.invisible.add(p);
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            if (!(players.hasPermission("bxcore.commands.vanish"))) {
                                players.hidePlayer(p);
                            }
                        }
                        p.sendMessage("§7Jesteś teraz §aniewidoczny");
                        if (plugin.getConfig().getBoolean("logs")) {
                            plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " wlaczyl vanish");
                        }
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§aJestes obecnie niewidoczny"));
                    }
                } else {
                    p.sendMessage("§7Brak permisji: §9bxcore.commands.vanish");
                }
        }

        return false;
    }

}
