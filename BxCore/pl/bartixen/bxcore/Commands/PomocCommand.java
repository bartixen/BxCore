package pl.bartixen.bxcore.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.bartixen.bxcore.Main;

public class PomocCommand implements CommandExecutor {

    Main plugin;

    public PomocCommand(Main m) {
        plugin = m;
        m.getCommand("pomoc").setExecutor(this);
        m.getCommand("help").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage("§7");
        sender.sendMessage("§8§l[§9§lPOMOC§8§l] §fKomendy §fdostepnie na serwerze");
        if (sender.hasPermission("bxcore.commands.spawn") || sender.isOp()) {
            sender.sendMessage("§7- §9/spawn §7| §fPrzeteleportuje ciebie na spawn");
        }
        sender.sendMessage("§7- §9/helpop §7| §fWysyla wiadomość do administracji");
        if (sender.hasPermission("bxcore.commands.rtp") || sender.isOp()) {
            sender.sendMessage("§7- §9/rtp §7| §fTeleportuje ciebie w losowe miejsce na mapie");
        }
        if (sender.hasPermission("bxcore.commands.tpa") || sender.isOp()) {
            sender.sendMessage("§7- §9/tpa §7| §fWysyla prośbe do danego gracza o teleporacje");
        }
        sender.sendMessage("§7- §9/home §7| §fTeleportuje ciebie do domu");
        sender.sendMessage("§7- §9/msg §7| §fWysyla prywatna wiadomość do gracza");
        sender.sendMessage("§7- §9/staty §7| §fWyświetla statystyki");
        if (sender.hasPermission("bxcore.commands.alert") || sender.isOp()) {
            sender.sendMessage("§7- §9/alert, /bc §7| §fWysyla do wszystkich alert");
            sender.sendMessage("§7- §9/bossbar §7| §fWysyla do wszystkich bossbar z informacja");
        }
        if (sender.hasPermission("bxcore.commands.back") || sender.isOp()) {
            sender.sendMessage("§7- §9/back §7| §fTeleportuje ciebie do ostatniego zapisanego miejsca");
        }
        if (sender.hasPermission("bxcore.commands.chat") || sender.isOp()) {
            sender.sendMessage("§7- §9/czat §7| §fZarzadzanie czatem na serwerze");
        }
        if (sender.hasPermission("bxcore.commands.clear") || sender.isOp()) {
            sender.sendMessage("§7- §9/clear §7| §fWyczyszcza ekwipunek");
        }
        if (sender.hasPermission("bxcore.commands.crafting") || sender.isOp()) {
            sender.sendMessage("§7- §9/wb §7| §fOtwiera crafting");
        }
        if (sender.hasPermission("bxcore.commands.day") || sender.isOp()) {
            sender.sendMessage("§7- §9/day §7| §fUstawia dzień na serwerze");
        }
        if (sender.hasPermission("bxcore.commands.night") || sender.isOp()) {
            sender.sendMessage("§7- §9/night §7| §fUstawia noc na serwerze");
        }
        if (sender.hasPermission("bxcore.commands.enderchest") || sender.isOp()) {
            sender.sendMessage("§7- §9/ec §7| §fOtwiera enderchesta");
        }
        if (sender.hasPermission("bxcore.commands.fly") || sender.isOp()) {
            sender.sendMessage("§7- §9/fly §7| §fZmienia status fly");
        }
        if (sender.hasPermission("bxcore.commands.gamemode") || sender.isOp() || sender.hasPermission("bxcore.commands.pomoc.gamemode")) {
            sender.sendMessage("§7- §9/gm §7| §fZmienia tryb gry");
        }
        if (sender.hasPermission("bxcore.commands.godmode") || sender.isOp()) {
            sender.sendMessage("§7- §9/god §7| §fUstawia nieśmiertelność");
        }
        if (sender.hasPermission("bxcore.commands.heal") || sender.isOp()) {
            sender.sendMessage("§7- §9/heal §7| §fLeczy gracza");
        }
        if (sender.hasPermission("bxcore.commands.invsee") || sender.isOp()) {
            sender.sendMessage("§7- §9/invsee §7| §fOtwiera ekwipunek gracza");
        }
        if (sender.hasPermission("bxcore.commands.list") || sender.isOp()) {
            sender.sendMessage("§7- §9/lista §7| §fPokazuje liczbe graczy na serwerze");
        }
        if (sender.hasPermission("bxcore.commands.repair") || sender.isOp()) {
            sender.sendMessage("§7- §9/repair §7| §fNaprawia przedmioty");
        }
        if (sender.hasPermission("bxcore.commands.speed") || sender.isOp()) {
            sender.sendMessage("§7- §9/speed §7| §fUstawia szybkosc gracza");
        }
        if (sender.hasPermission("bxcore.commands.tp") || sender.isOp()) {
            sender.sendMessage("§7- §9/tp §7| §fTeleportuje do gracza");
        }
        if (sender.hasPermission("bxcore.commands.tp") || sender.isOp()) {
            sender.sendMessage("§7- §9/s §7| §fTeleportuje gracza do ciebie");
        }
        if (sender.hasPermission("bxcore.commands.weather") || sender.isOp()) {
            sender.sendMessage("§7- §9/weather §7| §fUstawia pogode na serwerze");
        }
        if (sender.hasPermission("bxcore.commands.tp") || sender.isOp()) {
            sender.sendMessage("§7- §9/world §7| §fTeleportuje ciebie na dany świat");
        }
        if (sender.hasPermission("bxcore.commands.vanish") || sender.isOp()) {
            sender.sendMessage("§7- §9/v §7| §fUstawia vanish");
        }
        if (sender.hasPermission("bxcore.commands.socialspy") || sender.isOp()) {
            sender.sendMessage("§7- §9/socialspy §7| §fUstawia podglad wiadomosci prywatnych");
        }
        if (sender.hasPermission("bxcore.commands.kick") || sender.isOp()) {
            sender.sendMessage("§7- §9/kick §7| §fWyrzuca gracza z serwera");
        }
        if (sender.hasPermission("bxcore.commands.ban") || sender.isOp()) {
            sender.sendMessage("§7- §9/ban §7| §fBanuje gracza");
        }
        if (sender.hasPermission("bxcore.commands.banip") || sender.isOp()) {
            sender.sendMessage("§7- §9/banip §7| §fBanuje adres ip");
        }
        if (sender.hasPermission("bxcore.commands.tempban") || sender.isOp()) {
            sender.sendMessage("§7- §9/tempban §7| §fBanuje tymczasowo gracza");
        }
        if (sender.hasPermission("bxcore.commands.unban") || sender.isOp()) {
            sender.sendMessage("§7- §9/unban §7| §fOdbanowauje gracza");
        }
        if (sender.hasPermission("bxcore.commands.mute") || sender.isOp()) {
            sender.sendMessage("§7- §9/mute §7| §fWycisza gracza");
        }
        if (sender.hasPermission("bxcore.commands.tempmute") || sender.isOp()) {
            sender.sendMessage("§7- §9/tempmute §7| §fWycisza tymczasowo gracza");
        }
        if (sender.hasPermission("bxcore.commands.ip") || sender.isOp()) {
            sender.sendMessage("§7- §9/ip §7| §fPokazuje ip gracza");
        }
        if (sender.hasPermission("bxcore.commands.gracz") || sender.isOp()) {
            sender.sendMessage("§7- §9/gracz §7| §fPokazuje wszystkie informacje o graczu");
        }
        if (sender.hasPermission("bxcore.commands.whitelist") || sender.isOp()) {
            sender.sendMessage("§7- §9/whitelist §7| §fZarzadzanie whitelista");
        }
        if (sender.hasPermission("bxcore.commands.server") || sender.isOp()) {
            sender.sendMessage("§7- §9/serwer §7| §fPokazuje wszystkie ważne informacje o serwerze");
        }
        if (sender.hasPermission("bxcore.commands.restart") || sender.isOp()) {
            sender.sendMessage("§7- §9/wylaczenie §7| §fWylacza serwer w ciagu 60s");
        }
        sender.sendMessage("§7");
        return false;
    }
}