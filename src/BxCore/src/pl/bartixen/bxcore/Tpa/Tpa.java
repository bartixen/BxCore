package pl.bartixen.bxcore.Tpa;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

public class Tpa implements CommandExecutor {

    Main plugin;

    public Tpa(Main m) {
        plugin = m;
        m.getCommand("tpa").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cTa komenda jest przeznaczona tylko dla graczy");
            return false;
        }
        Player p = (Player) sender;
        if (sender.hasPermission("bxcore.commands.tpa")) {
            if (args.length == 1) {
                Player cel = Bukkit.getPlayer(args[0]);
                if (cel == null) {
                    p.sendMessage("§7Ten gracz jest §coffline");
                    return false;
                } else {
                    if (cel == p) {
                        p.sendMessage("§7Nie możesz się teleportować sam do siebie");
                        return false;
                    } else {
                        TpSystem.sendRequest(p, cel);
                        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                            @Override
                            public void run() {
                                TpSystem.removeRequest(cel.getName());
                            }
                        }, 600L);
                    }
                }
            } else {
                p.sendMessage("§7Poprawne użycie: §9/tpa [gracz]");
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.tpa");
        }
        return true;
    }
}