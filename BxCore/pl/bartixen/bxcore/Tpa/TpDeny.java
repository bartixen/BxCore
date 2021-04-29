package pl.bartixen.bxcore.Tpa;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

public class TpDeny implements CommandExecutor {

    Main plugin;

    public TpDeny(Main m) {
        plugin = m;
        m.getCommand("tpdeny").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cTa komenda jest przeznaczona tylko dla graczy");
            return false;
        }
        Player p = (Player) sender;

        if (!TpSystem.request.containsKey(p.getName())) {
            p.sendMessage("§7Nie masz prośb do odrzucenia");
            return false;
        }
        Player p2 = Bukkit.getPlayer(TpSystem.request.get(p.getName()));
        TpSystem.request.remove(p.getName());
        if (p2 != null) {
            p2.sendMessage("§7Gracz §9" + p.getName() + " §7odrzucil twoja prośbe o teleportacje");
            p.sendMessage("§7Prośba gracza §9" + p2.getName() + " §7zostala odrzucona");
        } else {
            p.sendMessage("§7Gracz który prosil o teleportacje jest §coffline");
        }
        return false;
    }
}