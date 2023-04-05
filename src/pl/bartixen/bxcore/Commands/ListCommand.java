package pl.bartixen.bxcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

import java.util.ArrayList;

public class ListCommand implements CommandExecutor {

    Main plugin;

    public ArrayList<String> listagraczy = new ArrayList<>();
    public ArrayList<String> listaadminow = new ArrayList<>();

    public ListCommand(Main m) {
        plugin = m;
        m.getCommand("list").setExecutor(this);
        m.getCommand("lista").setExecutor(this);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.list") || sender.isOp()) {
            int max = plugin.getServer().getMaxPlayers();
            listagraczy.clear();
            listaadminow.clear();
            for (Player player : Bukkit.getOnlinePlayers()) {
                listagraczy.add(player.getName());
                if (player.hasPermission("bxcore.commands.list") || player.isOp()) {
                    listaadminow.add(player.getName());
                }
            }
            sender.sendMessage("§7Graczy na serwerze (§9" + listagraczy.size() + "§7/§9" + max + "§7) w tym administratorów §9" + listaadminow.size());
            sender.sendMessage("§7Graczy: §9" + listagraczy);
            sender.sendMessage("§7Administratorów: §9" + listaadminow);
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.list");
        }
        return false;
    }
}
