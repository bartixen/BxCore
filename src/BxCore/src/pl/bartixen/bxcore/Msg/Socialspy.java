package pl.bartixen.bxcore.Msg;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Data.MsgDataManager;
import pl.bartixen.bxcore.Main;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;

public class Socialspy implements CommandExecutor {

    Main plugin;

    static MsgDataManager msgd;

    public Socialspy(Main m) {
        plugin = m;
        msgd = MsgDataManager.getInstance();
        m.getCommand("socialspy").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cTa komenda jest przeznaczona tylko dla graczy");
            return false;
        }

        Player p = (Player) sender;
        UUID uuid = p.getUniqueId();
        if (p.hasPermission("bxcore.commands.socialspy") || p.isOp()) {
            if (msgd.getData().getConfigurationSection(uuid + ".socialspy") != null) {
                msgd.getData().set(uuid + ".socialspy", null);
                try {
                    msgd.saveData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                p.sendMessage("§7Podglad wiadomości prywatnych zostal §cwylaczony");
                if (plugin.getConfig().getBoolean("logs")) {
                    plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " wylaczyl podglad prywatnych wiadomosci");
                }
            } else {
                msgd.getData().set(uuid + ".socialspy.use", true);
                try {
                    msgd.saveData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                p.sendMessage("§7Podglad wiadomości prywatnych zostal §awlaczony");
                if (plugin.getConfig().getBoolean("logs")) {
                    plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " wlaczyl podglad prywatnych wiadomosci");
                }
            }
        } else {
            p.sendMessage("§7Brak permisji: §9bxcore.commands.socialspy");
        }
        return false;
    }


}