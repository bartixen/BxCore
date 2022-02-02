package pl.bartixen.bxcore.Commands;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

import java.util.Random;
import java.util.logging.Level;

public class RtpCommand implements CommandExecutor {

    Main plugin;

    public RtpCommand(Main m) {
        plugin = m;
        m.getCommand("rtp").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cTa komenda jest przeznaczona tylko dla graczy");
            return false;
        }
        Player p = (Player) sender;
        if (sender.hasPermission("bxcore.commands.rtp") || sender.isOp()) {
            World w = p.getServer().getWorld("world");
            int border = (int) p.getLocation().getWorld().getWorldBorder().getSize() / 2;

            int coordsX = border;
            int coordsZ = border;

            Random rand = new Random();

            double x = -1 ^ rand.nextInt(coordsX);
            double z = rand.nextInt(coordsZ);
            double y = 63.0;

            Location loc = new Location(w, x, y, z);
            loc.setY((double) (loc.getWorld().getHighestBlockYAt(loc) + 3));
            if (!(p.hasPermission("bxhome.user.bypass") || p.isOp())) {
                double tpX = p.getLocation().getX();
                double tpY = p.getLocation().getY();
                double tpZ = p.getLocation().getZ();
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §98s"));
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    public void run(){
                        double tpX1 = p.getLocation().getX();
                        double tpY1 = p.getLocation().getY();
                        double tpZ1 = p.getLocation().getZ();
                        if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                            p.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                        }
                        if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §97s"));
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                public void run() {
                                    double tpX1 = p.getLocation().getX();
                                    double tpY1 = p.getLocation().getY();
                                    double tpZ1 = p.getLocation().getZ();
                                    if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                        p.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                                    }
                                    if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §96s"));
                                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                            public void run() {
                                                double tpX1 = p.getLocation().getX();
                                                double tpY1 = p.getLocation().getY();
                                                double tpZ1 = p.getLocation().getZ();
                                                if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                    p.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                                                }
                                                if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §95s"));
                                                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                        public void run() {
                                                            double tpX1 = p.getLocation().getX();
                                                            double tpY1 = p.getLocation().getY();
                                                            double tpZ1 = p.getLocation().getZ();
                                                            if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                                p.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                                                            }
                                                            if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §94s"));
                                                                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                    public void run() {
                                                                        double tpX1 = p.getLocation().getX();
                                                                        double tpY1 = p.getLocation().getY();
                                                                        double tpZ1 = p.getLocation().getZ();
                                                                        if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                                            p.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                                                                        }
                                                                        if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §93s"));
                                                                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                                public void run() {
                                                                                    double tpX1 = p.getLocation().getX();
                                                                                    double tpY1 = p.getLocation().getY();
                                                                                    double tpZ1 = p.getLocation().getZ();
                                                                                    if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                                                        p.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                                                                                    }
                                                                                    if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                                                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §92s"));
                                                                                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                                            public void run() {
                                                                                                double tpX1 = p.getLocation().getX();
                                                                                                double tpY1 = p.getLocation().getY();
                                                                                                double tpZ1 = p.getLocation().getZ();
                                                                                                if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                                                                    p.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                                                                                                }
                                                                                                if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                                                                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §91s"));
                                                                                                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                                                        public void run() {
                                                                                                            if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                                                                                p.teleport(loc);
                                                                                                                if (plugin.getConfig().getBoolean("logs")) {
                                                                                                                    plugin.getLogger().log(Level.INFO, "Gracz " + p.getName() + " teleportowal sie w losowe kordynaty: X - " + x + " Z - " + z);
                                                                                                                }
                                                                                                                p.sendMessage("§7Zostales przeteleportowany na kordy §9X §b" + x + " §9Z §b" + z);
                                                                                                            }
                                                                                                        }
                                                                                                    }, 20);
                                                                                                }
                                                                                            }
                                                                                        }, 20);
                                                                                    }
                                                                                }
                                                                            }, 20);
                                                                        }
                                                                    }
                                                                }, 20);
                                                            }
                                                        }
                                                    }, 20);
                                                }
                                            }
                                        }, 20);
                                    }
                                }
                            }, 20);
                        }
                    }
                }, 20);
            } else {
                p.teleport(loc);
                p.sendMessage("§7Zostales przeteleportowany na kordy §9X §b" + x + " §9Z §b" + z);
                if (plugin.getConfig().getBoolean("logs")) {
                    plugin.getLogger().log(Level.INFO, "Gracz " + p.getName() + " teleportowal sie w losowe kordynaty: X - " + x + " Z - " + z);
                }
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.rtp");
        }
        return false;
    }

}
