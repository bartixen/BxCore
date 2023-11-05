package pl.bartixen.bxcore.Msg;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Data.MsgDataManager;
import pl.bartixen.bxcore.Main;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

public class Msg implements CommandExecutor {

    Main plugin;

    static MsgDataManager msgd;

    public Msg(Main m) {
        plugin = m;
        msgd = MsgDataManager.getInstance();
        m.getCommand("msg").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cTa komenda jest przeznaczona tylko dla graczy");
            return false;
        }

        Player p = (Player) sender;
        UUID puuid = p.getUniqueId();

        if (args.length >= 2) {
            Player cel = Bukkit.getPlayerExact(args[0]);
            if (cel == null) {
                p.sendMessage("§7Ten gracz jest §coffline");
                return true;
            }

            UUID celuuid = cel.getUniqueId();

            StringBuilder sb = new StringBuilder();

            for (int i = 1; i < args.length; ++i) {
                sb.append(args[i]).append(" ");
            }

            String msg = sb.toString();

            List<String> celIgnored = msgd.getData().getStringList(celuuid + ".ignored");

            if (!celIgnored.contains(p.getDisplayName())) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    UUID uuid = players.getUniqueId();
                    if (msgd.getData().getConfigurationSection(uuid + ".socialspy") !=null) {
                        players.sendMessage("§8[§9" + p.getName() + " §8-> §9" + cel.getName() + "§8] §a" + msg);
                    }
                }
                if (plugin.getConfig().getBoolean("logs")) {
                    plugin.getLogger().log(Level.INFO, "[" + p.getName() + " -> " + cel.getName() + "] " + msg);
                }
                p.sendMessage("§8[§2Ja §8-> §9" + cel.getName() + "§8] §a" + msg);
                cel.sendMessage("§8[§9" + p.getName() + " §8-> §2Ja§8] §a" + msg);

                msgd.getData().set(puuid + ".lastPlayer", cel.getDisplayName());
                msgd.getData().set(celuuid + ".lastPlayer", p.getDisplayName());
                try {
                    msgd.saveData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                p.sendMessage("§7Ten gracz ciebie ignoruje");
            }
        } else {
            p.sendMessage("§7Poprawne użycie: §9/msg [gracz] [wiadomość]");
        }

        return false;
    }
}
