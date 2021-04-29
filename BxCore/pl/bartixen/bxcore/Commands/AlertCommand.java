package pl.bartixen.bxcore.Commands;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

public class AlertCommand implements CommandExecutor {

    Main plugin;

    public AlertCommand(Main m) {
        plugin = m;
        m.getCommand("alert").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.alert") || sender.isOp()) {
            if (args.length > 1) {
                StringBuilder sb = new StringBuilder();

                for (int i = 1; i < args.length; ++i) {
                    sb.append(args[i]).append(" ");
                }

                String msg = sb.toString().replace("&", "§");

                if (args[0].equalsIgnoreCase("title")) {
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.sendTitle("§c§l" + msg, "", 5, 50, 5);
                    }
                    sender.sendMessage("§7Wyslano pomyślnie alert");
                } else {
                    if (args[0].equalsIgnoreCase("subtitle")) {
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.sendTitle("", "§c§l" + msg, 5, 50, 5);
                        }
                        sender.sendMessage("§7Wyslano pomyślnie alert");
                    } else {
                        if (args[0].equalsIgnoreCase("broadcast")) {
                            for (Player players : Bukkit.getOnlinePlayers()) {
                                players.sendMessage("§c§l" + msg);
                            }
                            sender.sendMessage("§7Wyslano pomyślnie alert");
                        } else {
                            if (args[0].equalsIgnoreCase("action")) {
                                for (Player players : Bukkit.getOnlinePlayers()) {
                                    players.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§c§l" + msg));
                                }
                                sender.sendMessage("§7Wyslano pomyślnie alert");
                            } else {
                                sender.sendMessage("§7Poprawne użycie: §9/alert [title,subtitle,action,broadcast] [wiadomosc]");
                            }
                        }
                    }
                }
            } else {
                sender.sendMessage("§7Poprawne użycie: §9/alert [title,subtitle,action,broadcast] [wiadomosc]");
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.alert");
        }
        return false;
    }

}