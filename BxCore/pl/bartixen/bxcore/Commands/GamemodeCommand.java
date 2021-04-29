package pl.bartixen.bxcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

public class GamemodeCommand implements CommandExecutor {

    Main plugin;

    public GamemodeCommand(Main m) {
        plugin = m;
        m.getCommand("gm").setExecutor(this);
        m.getCommand("gamemode").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.gamemode") || sender.isOp()) {
            if (args.length == 1) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§7Poprawne użycie: §9/gm [0,1,2,3] [gracz]");
                    return false;
                }
                GameMode mode = getGameMode(args[0]);
                if (mode == null) {
                    sender.sendMessage("§7Poprawne użycie: §9/gm [0,1,2,3]");
                    return false;
                }
                Player p = (Player) sender;
                p.setGameMode(mode);
                p.sendMessage("§7Zmieniono tryb gry na §9" + mode);
                return false;
            } else {
                if (args.length == 2) {
                    GameMode mode = getGameMode(args[0]);
                    if (mode == null) {
                        sender.sendMessage("§7Poprawne użycie: §9/gm [0,1,2,3]");
                        return false;
                    }
                    Player cel = Bukkit.getPlayerExact(args[1]);
                    if (cel == null) {
                        sender.sendMessage("§7Ten gracz jest §coffline");
                        return true;
                    }
                    cel.setGameMode(mode);
                    cel.sendMessage("§7Zmieniono tryb gry na §9" + mode + " §7przez gracza " + sender.getName());
                    sender.sendMessage("§7Poprawne ustawiono graczu §9" + cel.getName() + " §7tryb gry na §9" + mode);
                } else {
                    sender.sendMessage("§7Poprawne użycie: §9/gm [0,1,2,3]");
                    return false;
                }
            }
        } else {
            if (sender.hasPermission("bxcore.commands.pomoc.gamemode") || sender.isOp()) {
                GameMode mode = getGameModepomoc(args[0]);
                if (mode == null) {
                    sender.sendMessage("§7Poprawne użycie: §9/gm [0,3]");
                    return false;
                }
                Player p = (Player) sender;
                p.setGameMode(mode);
                p.sendMessage("§7Zmieniono tryb gry na §9" + mode);
                return false;
            } else {
                sender.sendMessage("§7Brak permisji: §9bxcore.commands.gamemode");
                return false;
            }
        }
        return false;
    }

    public GameMode getGameModepomoc(String s) {
        if (s.equalsIgnoreCase("s") || s.equalsIgnoreCase("0") || s.equalsIgnoreCase("survival"))
            return GameMode.SURVIVAL;
        if (s.equalsIgnoreCase("3") || s.equalsIgnoreCase("spectator"))
            return GameMode.SPECTATOR;
        return null;
    }

    public GameMode getGameMode(String s) {
        if (s.equalsIgnoreCase("s") || s.equalsIgnoreCase("0") || s.equalsIgnoreCase("survival"))
            return GameMode.SURVIVAL;
        if (s.equalsIgnoreCase("c") || s.equalsIgnoreCase("1") || s.equalsIgnoreCase("creative"))
            return GameMode.CREATIVE;
        if (s.equalsIgnoreCase("a") || s.equalsIgnoreCase("2") || s.equalsIgnoreCase("adventure"))
            return GameMode.ADVENTURE;
        if (s.equalsIgnoreCase("3") || s.equalsIgnoreCase("spectator"))
            return GameMode.SPECTATOR;
        return null;
    }
}