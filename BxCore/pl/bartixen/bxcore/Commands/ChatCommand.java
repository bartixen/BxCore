package pl.bartixen.bxcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.bartixen.bxcore.Main;

public class ChatCommand implements CommandExecutor, Listener {

    Main plugin;

    public ChatCommand(Main m) {
        plugin = m;
        m.getCommand("chat").setExecutor(this);
        m.getCommand("czat").setExecutor(this);
    }

    public static boolean chat = true;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.chat") || sender.isOp()) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("on")) {
                    if (chat) {
                        sender.sendMessage("§7Czat jest już wlaczony");
                    } else {
                        chat = true;
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.sendMessage("§7Czat zostal §awlaczony §7przez §9" + sender.getName());
                        }
                    }
                } else {
                    if (args[0].equalsIgnoreCase("off")) {
                        if (!chat) {
                            sender.sendMessage("§7Czat jest już wylaczony");
                        } else {
                            chat = false;
                            for (Player players : Bukkit.getOnlinePlayers()) {
                                players.sendMessage("§7Czat zostal §cwylaczony §7przez §9" + sender.getName());
                            }
                        }
                    } else {
                        if (args[0].equalsIgnoreCase("clear")) {
                            for (int i = 0; i < 201; i++) {
                                for (Player players : Bukkit.getOnlinePlayers()) {
                                    players.sendMessage(" ");
                                }
                            }
                            for (Player players : Bukkit.getOnlinePlayers()) {
                                players.sendMessage("§7Czat wyczyszczono przez §9" + sender.getName());
                            }
                        } else {
                            sender.sendMessage("§7Poprawne użycie: §9/chat [on,off,clear]");
                        }
                    }
                }
            } else {
                sender.sendMessage("§7Poprawne użycie: §9/chat [on,off,clear]");
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.chat");
        }
        return false;
    }

    @EventHandler
    public void OnChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if(!(p.hasPermission("bxcore.user.bypass") || p.isOp())) {
            if(!chat) {
                p.sendMessage("§cCzat jest obecnie wylaczony");
                e.setCancelled(true);
            }
        }
    }

}