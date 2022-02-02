package pl.bartixen.bxcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

import java.util.logging.Level;

public class ChatClearCommand implements CommandExecutor {

    Main plugin;

    public ChatClearCommand(Main m) {
        plugin = m;
        m.getCommand("cc").setExecutor(this);
    }

    public static boolean chat = true;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.chat") || sender.isOp()) {
            for (int i = 0; i < 201; i++) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    players.sendMessage(" ");
                }
            }
            for (Player players : Bukkit.getOnlinePlayers()) {
                players.sendMessage("§7Czat wyczyszczono przez §9" + sender.getName());
            }
            if (plugin.getConfig().getBoolean("logs")) {
                plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " wyczyszczyl czat");
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.chat");
        }
        return false;
    }
}