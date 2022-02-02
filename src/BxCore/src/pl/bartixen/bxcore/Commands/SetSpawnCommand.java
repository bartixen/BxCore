package pl.bartixen.bxcore.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

import java.util.logging.Level;

public class SetSpawnCommand implements CommandExecutor {

    Main plugin;

    public SetSpawnCommand(Main m) {
        plugin = m;
        m.getCommand("setspawn").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cTa komenda jest przeznaczona tylko dla graczy");
            return false;
        }
        Player p = (Player) sender;
        if (p.hasPermission("bxcore.commands.setspawn") || p.isOp()) {
            double x = p.getLocation().getX();
            double y = p.getLocation().getY();
            double z = p.getLocation().getZ();
            double yaw = p.getLocation().getYaw();
            double pitch = p.getLocation().getPitch();
            String world = p.getLocation().getWorld().getName();
            plugin.getConfig().set("spawn.x", x);
            plugin.getConfig().set("spawn.y", y);
            plugin.getConfig().set("spawn.z", z);
            plugin.getConfig().set("spawn.yaw", yaw);
            plugin.getConfig().set("spawn.pitch", pitch);
            plugin.getConfig().set("spawn.world", world);
            plugin.saveConfig();
            if (plugin.getConfig().getBoolean("logs")) {
                plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " ustawil nowe kordynaty spawnu: X - " + x + " Y - " + y + " Z - " + z + " YAW - " + yaw + " PITCH - " + pitch + " WORLD - " + world);
            }
            p.sendMessage("§7Ustawiono spawn na kordynatach §9X - §b" + x + " §9Y - §b" + y + " §9Z - §b" + z + " §9YAW - §b" + yaw + " §9PITCH - §b" + pitch + " §9WORLD - §b" + world);
        } else {
            p.sendMessage("§7Brak permisji: §9bxcore.commands.setspawn");
        }
        return false;
    }
}