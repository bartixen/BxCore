package pl.bartixen.bxcore.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.bartixen.bxcore.Data.*;
import pl.bartixen.bxcore.Main;

public class BxCoreCommand implements CommandExecutor {

    Main plugin;

    HomeDataManager hd;
    MsgDataManager msgd;
    UserDataManager userd;
    StatyDataManager statyd;
    BanDataManager band;
    WhitelistDataManager wld;
    RtpDataManager rtpd;
    AntyXrayDataManager antyd;

    public BxCoreCommand(Main m) {
        plugin = m;
        m.getCommand("bx").setExecutor(this);
        m.getCommand("bxcore").setExecutor(this);
        hd = HomeDataManager.getInstance();
        msgd = MsgDataManager.getInstance();
        userd = UserDataManager.getInstance();
        statyd = StatyDataManager.getInstance();
        band = BanDataManager.getInstance();
        wld = WhitelistDataManager.getInstance();
        rtpd = RtpDataManager.getInstance();
        antyd = AntyXrayDataManager.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String version = plugin.version;
        if (args.length == 1) {
            if (sender.hasPermission("bxcore.commands.reload") || sender.isOp()) {
                if (args[0].equalsIgnoreCase("reload")) {
                    sender.sendMessage("§8 • — • — • — • ");
                    plugin.reloadConfig();
                    sender.sendMessage("§7Pomyślnie przeładowano §9CONFIG");
                    hd.reloadData();
                    sender.sendMessage("§7Pomyślnie przeładowano §9HOMEDATAMENAGER");
                    msgd.reloadData();
                    sender.sendMessage("§7Pomyślnie przeładowano §9MSGDATAMENAGER");
                    userd.reloadData();
                    sender.sendMessage("§7Pomyślnie przeładowano §9USERDATAMENAGER");
                    statyd.reloadData();
                    sender.sendMessage("§7Pomyślnie przeładowano §9STATYDATAMENAGER");
                    band.reloadData();
                    sender.sendMessage("§7Pomyślnie przeładowano §9BANDATAMENAGER");
                    wld.reloadData();
                    sender.sendMessage("§7Pomyślnie przeładowano §9WHITELISTDATAMENAGER");
                    rtpd.reloadData();
                    sender.sendMessage("§7Pomyślnie przeładowano §9RTPDATAMENAGER");
                    antyd.reloadData();
                    sender.sendMessage("§7Pomyślnie przeładowano §9ANTYXRAYDATAMENAGER");
                    sender.sendMessage("§8 • — • — • — • ");
                } else {
                    WiadomoscPlugin(sender, version);
                }
            } else {
                WiadomoscPlugin(sender, version);
            }
        } else {
            WiadomoscPlugin(sender, version);
        }
        return false;
    }

    public void WiadomoscPlugin(CommandSender sender, String version) {
        sender.sendMessage("§7");
        sender.sendMessage("§7Plugin §eBxCore");
        sender.sendMessage("§7Version: §e" + version);
        sender.sendMessage("§7Author: §eBartixen");
        sender.sendMessage("§7Website: §ehttps://bartixen.pl");
        sender.sendMessage("§7");
    }

}
