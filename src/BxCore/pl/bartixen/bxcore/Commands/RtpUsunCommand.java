package pl.bartixen.bxcore.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.bartixen.bxcore.Data.RtpDataManager;
import pl.bartixen.bxcore.Main;

import java.io.IOException;
import java.util.logging.Level;

public class RtpUsunCommand implements CommandExecutor {

    Main plugin;

    static RtpDataManager rtpd;

    public RtpUsunCommand(Main m) {
        plugin = m;
        m.getCommand("rtpusun").setExecutor(this);
        rtpd = RtpDataManager.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.rtpnadaj") || sender.isOp()) {
            String x = args[0];
            String y = args[1];
            String z = args[2];
            if (RtpNadajCommand.przyciski.contains(x + y + z)) {
                RtpNadajCommand.przyciski.remove(x + y + z);
                rtpd.getData().set("przyciski", RtpNadajCommand.przyciski);
                try {
                    rtpd.saveData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sender.sendMessage("§7Poprawnie usunieto przycisk rtp §9X §b" + x + " §9Y §b" + y + " §9Z §b" + z);
                if (plugin.getConfig().getBoolean("logs")) {
                    plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " usunal przcisk rtp z kordynat: X - " + x + " Y - " + y + " Z - " + z);
                }
            } else {
                sender.sendMessage("§7Nie znaleziono takiego przycisku rtp");
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.rtpnadaj");
        }
        return false;
    }
}
