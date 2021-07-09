package pl.bartixen.bxcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Data.UserDataManager;
import pl.bartixen.bxcore.Main;

import java.net.InetSocketAddress;
import java.util.logging.Level;

public class IPCommand implements CommandExecutor {

    Main plugin;

    static UserDataManager userd;

    public IPCommand(Main m) {
        plugin = m;
        m.getCommand("ip").setExecutor(this);
        m.getCommand("graczip").setExecutor(this);
        userd = UserDataManager.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.ip") || sender.isOp()) {
            if (args.length == 1) {
                Player cel = Bukkit.getPlayerExact(args[0]);
                if (cel != null) {
                    InetSocketAddress IPAdressPlayer = cel.getAddress();
                    String ip = IPAdressPlayer.toString();
                    String name = ip;
                    int entityTypeLenght = name.length() - 6;
                    String ipban = name.substring(0, entityTypeLenght);
                    sender.sendMessage("§7Adres IP gracza §9" + args[0] + " §7to §9" + ipban);
                    if (plugin.getConfig().getBoolean("logs")) {
                        plugin.getLogger().log(Level.WARNING, "Gracz " + sender.getName() + " uzyskal dostep do adresu IP gracza " + args[0]);
                    }
                    return false;
                }
                if ((userd.getData().getString(args[0])) != null) {
                    String ip = userd.getData().getString(args[0] + ".last_ip");
                    sender.sendMessage("§7Adres IP gracza §9" + args[0] + " §7to §9" + ip);
                    if (plugin.getConfig().getBoolean("logs")) {
                        plugin.getLogger().log(Level.WARNING, "Gracz " + sender.getName() + " uzyskal dostep do adresu IP gracza " + args[0]);
                    }
                } else {
                    sender.sendMessage("§7Brak danych o graczu §9" + args[0]);
                }
            } else {
                sender.sendMessage("§7Poprawne użycie: §9/ip [gracz]");
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.ip");
        }
        return false;
    }
}
