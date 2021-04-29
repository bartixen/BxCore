package pl.bartixen.bxcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import pl.bartixen.bxcore.Main;

import java.util.HashMap;

public class BackCommand implements CommandExecutor, Listener {

    Main plugin;

    public BackCommand(Main m) {
        plugin = m;
        m.getCommand("back").setExecutor(this);
    }

    static HashMap<Player, Location> back = new HashMap<Player, Location>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.back") || sender.isOp()) {
            if (args.length == 0) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§7Poprawne użycie: §9/back [gracz]");
                    return false;
                }
                Player p = (Player) sender;
                if (this.back.containsKey(p)) {
                    p.teleport(this.back.get(p));
                    this.back.remove(p);
                    p.sendMessage("§7Pomyślnie przeteleportowano ciebie do ostatniej lokalizacji");
                } else {
                    p.sendMessage("§7Nie znaleziono poprzedniej lokalizacji");
                }
            } else {
                if (args.length == 1) {
                    Player p = Bukkit.getPlayerExact(args[0]);
                    if (p == null) {
                        sender.sendMessage("§7Ten gracz jest §coffline");
                        return true;
                    }
                    if (this.back.containsKey(p)) {
                        p.teleport(this.back.get(p));
                        p.sendMessage("§7Pomyślnie przeteleportowano ciebie do ostatniej lokalizacji przez §9" + sender.getName());
                    } else {
                        sender.sendMessage("§7Nie znaleziono poprzedniej lokalizacji");
                    }
                } else {
                    sender.sendMessage("§7Poprawne użycie: §9/back [gracz]");
                }
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.back");
        }
        return false;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player p = event.getEntity();
        back.put(p, p.getLocation());
    }
    
    @EventHandler
    public void onPlayerTeleportEvent(PlayerTeleportEvent event){
        Player p = event.getPlayer();
        back.put(p, p.getLocation());
    }
}
