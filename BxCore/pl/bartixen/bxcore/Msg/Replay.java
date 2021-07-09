package pl.bartixen.bxcore.Msg;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Data.MsgDataManager;
import pl.bartixen.bxcore.Main;

import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

public class Replay implements CommandExecutor {

    Main plugin;

    static MsgDataManager msgd;

    public Replay(Main m) {
        plugin = m;
        msgd = MsgDataManager.getInstance();
        m.getCommand("r").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cTa komenda jest przeznaczona tylko dla graczy");
            return false;
        }

        Player p = (Player) sender;
        UUID puuid = p.getUniqueId();

        if (args.length >= 1) {
            Player cel = Bukkit.getPlayer(msgd.getData().getString(puuid + ".lastPlayer"));
            if (cel == null) {
                p.sendMessage("§7Gracz do którego ostatnio pisałeś jest §coffline");
                return true;
            }

            UUID celuuid = cel.getUniqueId();

            List<String> celIgnored = msgd.getData().getStringList(celuuid + ".ignored");

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < args.length; ++i) {
                sb.append(args[i]).append(" ");
            }

            String msg = sb.toString();

            if (!celIgnored.contains(p.getDisplayName())) {
                p.sendMessage("§8[§2Ja §8-> §9" + cel.getName() + "§8] §a" + msg);
                cel.sendMessage("§8[§9" + p.getName() + " §8-> §2Ja§8] §a" + msg);
            } else {
                p.sendMessage("§7Ten gracz ciebie ignoruje");
            }
            for (Player players : Bukkit.getOnlinePlayers()) {
                UUID uuid = players.getUniqueId();
                if (msgd.getData().getConfigurationSection(uuid + ".socialspy") !=null) {
                    players.sendMessage("§8[§9" + p.getName() + " §8-> §9" + cel.getName() + "§8] §a" + msg);
                }
                if (plugin.getConfig().getBoolean("logs")) {
                    plugin.getLogger().log(Level.INFO, "[" + p.getName() + " -> " + cel.getName() + "] " + msg);
                }
            }
        } else {
            p.sendMessage("§7Poprawne użycie: §9/msg [gracz] [wiadomość]");
        }
        return false;
    }
}
