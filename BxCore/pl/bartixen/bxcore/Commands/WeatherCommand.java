package pl.bartixen.bxcore.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

public class WeatherCommand implements CommandExecutor {

    Main plugin;

    public WeatherCommand(Main m) {
        plugin = m;
        m.getCommand("weather").setExecutor(this);
        m.getCommand("pogoda").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cTa komenda jest przeznaczona tylko dla graczy");
            return false;
        }
        if (sender.hasPermission("bxcore.commands.weather") || sender.isOp()) {
            if (args.length == 1) {
                Player p = (Player) sender;
                if (args[0].equalsIgnoreCase("sun")) {
                    p.getWorld().setWeatherDuration(1);
                    p.getWorld().setThundering(false);
                    p.sendMessage("§7Ustawiono pogodę sloneczną na danym świecie");
                } else {
                    if (args[0].equalsIgnoreCase("storm")) {
                        p.getWorld().setWeatherDuration(300);
                        p.getWorld().setThundering(true);
                        p.sendMessage("§7Ustawiono pogodę burzową na danym świecie");
                    } else {
                        sender.sendMessage("§7Poprawne użycie: §9/weather [sun,storm]");
                    }
                }
            } else {
                sender.sendMessage("§7Poprawne użycie: §9/weather [sun,storm]");
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.weather");
        }
        return false;
    }

}
