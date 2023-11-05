package pl.bartixen.bxcore.Commands;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import pl.bartixen.bxcore.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class VanishCommand implements CommandExecutor {

    Main plugin;

    public VanishCommand(Main m) {
        plugin = m;
        m.getCommand("v").setExecutor(this);
        m.getCommand("vanish").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
            if (args.length == 1) {
                Player cel = Bukkit.getPlayerExact(args[0]);
                if (cel == null) {
                    sender.sendMessage("§7Ten gracz jest §coffline");
                    return true;
                }
                if (plugin.invisible.contains(cel)) {
                    plugin.invisible.remove(cel);
                    lightning(cel);
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.showPlayer(cel);
                    }
                    cel.sendMessage("§7Jesteś teraz §cwidoczny §7przez §9" + sender.getName());
                    sender.sendMessage("§7Gracz §9" + cel.getName() + " §7jest teraz §cwidoczny");
                    if (plugin.getConfig().getBoolean("logs")) {
                        plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " wylaczyl vanish dla " + cel.getName());
                    }
                } else {
                    plugin.invisible.add(cel);
                    lightning(cel);
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        if (!(players.hasPermission("bxcore.commands.vanish"))) {
                            players.hidePlayer(cel);
                        }
                    }
                    cel.sendMessage("§7Jesteś teraz §aniewidoczny §7przez §9" + sender.getName());
                    sender.sendMessage("§7Gracz §9" + cel.getName() + " §7jest teraz §aniewidoczny");
                        if (plugin.getConfig().getBoolean("logs")) {
                            plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " wlaczyl vanish dla " + cel.getName());
                        }
                }
            } else {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§7Poprawne użycie: §9/v [gracz]");
                    return false;
                }
                Player p = (Player) sender;
                if (p.hasPermission("bxcore.commands.vanish") || p.isOp()) {
                    if (plugin.invisible.contains(p)) {
                        plugin.invisible.remove(p);
                        lightning(p);
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            players.showPlayer(p);
                        }
                        p.sendMessage("§7Jesteś teraz §cwidoczny");
                        if (plugin.getConfig().getBoolean("logs")) {
                            plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " wylaczyl vanish");
                        }
                    } else {
                        plugin.invisible.add(p);
                        lightning(p);
                        for (Player players : Bukkit.getOnlinePlayers()) {
                            if (!(players.hasPermission("bxcore.commands.vanish"))) {
                                players.hidePlayer(p);
                            }
                        }
                        p.sendMessage("§7Jesteś teraz §aniewidoczny");
                        if (plugin.getConfig().getBoolean("logs")) {
                            plugin.getLogger().log(Level.INFO, "Gracz " + sender.getName() + " wlaczyl vanish");
                        }
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§aJestes obecnie niewidoczny"));
                    }
                } else {
                    p.sendMessage("§7Brak permisji: §9bxcore.commands.vanish");
                }
        }

        return false;
    }

    List<Entity> spawnedBats = new ArrayList<>();

    public void lightning(Player player) {
        Location strikeLocation = player.getLocation();
        player.getWorld().strikeLightningEffect(strikeLocation);
        for (int j = 0; j < 20; j++) {
            player.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, player.getLocation().add(Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5), 0, 0, 0, 0, 1);
            player.getWorld().spawnParticle(Particle.LANDING_LAVA, player.getLocation().add(Math.random() - 0.5, Math.random() - 0.5, Math.random() - 0.5), 0, 0, 0, 0, 1);
        }
        for (int i = 0; i < 5; i++) {
            Bukkit.getServer().getScheduler().runTaskLater(plugin, () -> {
                Entity bat = player.getWorld().spawnEntity(player.getLocation(), EntityType.BAT);
                spawnedBats.add(bat);
            }, i * 20L);
        }
        Bukkit.getServer().getScheduler().runTaskLater(plugin, () -> {
            for (Entity bat : spawnedBats) {
                bat.remove();
            }
            spawnedBats.clear();
        }, 60L);
    }

}
