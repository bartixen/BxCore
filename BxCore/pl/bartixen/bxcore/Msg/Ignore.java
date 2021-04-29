package pl.bartixen.bxcore.Msg;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Data.MsgDataManager;
import pl.bartixen.bxcore.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Ignore implements CommandExecutor {

    Main plugin;

    static MsgDataManager msgd;

    public Ignore(Main m) {
        plugin = m;
        msgd = MsgDataManager.getInstance();
        m.getCommand("ignore").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cTa komenda jest przeznaczona tylko dla graczy");
            return false;
        }

        Player p = (Player) sender;
        UUID puuid = p.getUniqueId();

        if (args.length == 1) {
            Player cel = Bukkit.getPlayerExact(args[0]);

            if (cel == p) {
                p.sendMessage("§7Nie możesz ignorować samego siebie");
                return false;
            }

            List<String> ignored = msgd.getData().getStringList(puuid + ".ignored");

            if (ignored == null) {
                ignored = new ArrayList<String>();
            }

            if (!ignored.contains(cel.getDisplayName())) {
                ignored.add(cel.getDisplayName());
                msgd.getData().set(puuid + ".ignored", ignored);
                try {
                    msgd.saveData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                p.sendMessage("§7Gracz §9" + cel.getDisplayName() + " §7zostal dodany do listy ignorowanych");
            } else {
                ignored.remove(cel.getDisplayName());
                msgd.getData().set(puuid + ".ignored", ignored);
                try {
                    msgd.saveData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                p.sendMessage("§7Gracz §9" + cel.getDisplayName() + " §7zostal usuniety do listy ignorowanych");
            }
        } else {
            p.sendMessage("§7Poprawne użycie: §9/ignore [gracz]");
        }
        return false;
    }
}
