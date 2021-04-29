package pl.bartixen.bxcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import pl.bartixen.bxcore.Main;

import java.util.ArrayList;

public class GodCommand implements CommandExecutor, Listener {

    Main plugin;

    public GodCommand(Main m) {
        plugin = m;
        m.getCommand("god").setExecutor(this);
        m.getCommand("godmode").setExecutor(this);
    }

    public static ArrayList<String> gods = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.godmode") || sender.isOp()) {
            if (args.length == 0) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§7Poprawne użycie: §9/god [gracz]");
                    return false;
                }
                Player p = (Player) sender;
                if (gods.contains(p.getName())) {
                    gods.remove(p.getName());
                    p.sendMessage("§7Zmieniono nieśmiertelność na §cOFF");
                } else {
                    gods.add(p.getName());
                    p.sendMessage("§7Zmieniono nieśmiertelność na §aON");
                }
                return false;
            } else {
                if (args.length == 1) {
                    Player cel = Bukkit.getPlayerExact(args[0]);
                    if (cel == null) {
                        sender.sendMessage("§7Ten gracz jest §coffline");
                        return true;
                    }
                    if (gods.contains(cel.getName())) {
                        gods.remove(cel.getName());
                        cel.sendMessage("§7Zmieniono nieśmiertelność na §cOFF §7przez gracza §9" + sender.getName());
                        sender.sendMessage("§7Poprawne ustawiono graczu §9" + cel.getName() + " §7nieśmiertelność na §cOFF");
                    } else {
                        gods.add(cel.getName());
                        cel.sendMessage("§7Zmieniono nieśmiertelność na §aON §7przez gracza §9" + sender.getName());
                        sender.sendMessage("§7Poprawne ustawiono graczu §9" + cel.getName() + " §7nieśmiertelność na §aON");
                    }
                } else {
                    sender.sendMessage("§7Poprawne użycie: §9/god [gracz]");
                }
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.godmode");
        }
        return false;
    }

    @EventHandler
    public void onGod(EntityDamageEvent e) {
        if (e.getEntityType() != EntityType.PLAYER)
            return;
        if (!GodCommand.gods.contains(e.getEntity().getName()))
            return;
        e.setCancelled(true);
    }

}