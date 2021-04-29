package pl.bartixen.bxcore.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.bartixen.bxcore.Data.RtpDataManager;
import pl.bartixen.bxcore.Main;

import java.io.IOException;
import java.util.ArrayList;

public class RtpNadajCommand implements CommandExecutor {

    Main plugin;

    static RtpDataManager rtpd;

    public static ArrayList<String> przyciski = new ArrayList<>();

    public RtpNadajCommand(Main m) {
        plugin = m;
        m.getCommand("rtpnadaj").setExecutor(this);
        rtpd = RtpDataManager.getInstance();
        przyciski = new ArrayList<>(rtpd.getData().getStringList("przyciski"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.rtpnadaj") || sender.isOp()) {
            if (args.length == 3) {
                String x = args[0];
                String y = args[1];
                String z = args[2];
                if (!(przyciski.contains(x + y + z))) {
                    przyciski.add(x + y + z + "");
                    rtpd.getData().set("przyciski", przyciski);
                    try {
                        rtpd.saveData();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    sender.sendMessage("§7Poprawnie dodano nowy przycisk rtp §9X §b" + x + " §9Y §b" + y + " §9Z §b" + z);
                } else {
                    sender.sendMessage("§Taki przycisk już istnieje");
                }
            } else {
                sender.sendMessage("§7Poprawne użycie: §9/rtpnadaj [x] [y] [z]");
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.rtpnadaj");
        }
        return false;
    }
}
