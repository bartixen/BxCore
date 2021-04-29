package pl.bartixen.bxcore.Commands;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

public class SpawnCommand implements CommandExecutor {

    Main plugin;

    public SpawnCommand(Main m) {
        plugin = m;
        m.getCommand("spawn").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cTa komenda jest przeznaczona tylko dla graczy");
            return false;
        }
        Player p = (Player) sender;
        double x = plugin.getConfig().getDouble("spawn.x");
        double y = plugin.getConfig().getDouble("spawn.y");
        double z = plugin.getConfig().getDouble("spawn.z");
        float yaw = plugin.getConfig().getInt("spawn.yaw");
        float pitch = plugin.getConfig().getInt("spawn.pitch");
        String world = plugin.getConfig().getString("spawn.world");
        if (p.hasPermission("bxcore.commands.spawn") || p.isOp()) {
            if (!(p.hasPermission("bxhome.user.bypass") || p.isOp())) {
                double tpX = p.getLocation().getX();
                double tpY = p.getLocation().getY();
                double tpZ = p.getLocation().getZ();
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §98s"));
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    public void run() {
                        double tpX1 = p.getLocation().getX();
                        double tpY1 = p.getLocation().getY();
                        double tpZ1 = p.getLocation().getZ();
                        if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                            p.sendMessage("§7Teleportacja anulowana z powodu ruszenia się");
                        }
                        if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §97s"));
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                public void run() {
                                    double tpX1 = p.getLocation().getX();
                                    double tpY1 = p.getLocation().getY();
                                    double tpZ1 = p.getLocation().getZ();
                                    if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                        p.sendMessage("§7Teleportacja anulowana z powodu ruszenia się");
                                    }
                                    if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §96s"));
                                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                            public void run() {
                                                double tpX1 = p.getLocation().getX();
                                                double tpY1 = p.getLocation().getY();
                                                double tpZ1 = p.getLocation().getZ();
                                                if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                    p.sendMessage("§7Teleportacja anulowana z powodu ruszenia się");
                                                }
                                                if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §95s"));
                                                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                        public void run() {
                                                            double tpX1 = p.getLocation().getX();
                                                            double tpY1 = p.getLocation().getY();
                                                            double tpZ1 = p.getLocation().getZ();
                                                            if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                                p.sendMessage("§7Teleportacja anulowana z powodu ruszenia się");
                                                            }
                                                            if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §94s"));
                                                                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                    public void run() {
                                                                        double tpX1 = p.getLocation().getX();
                                                                        double tpY1 = p.getLocation().getY();
                                                                        double tpZ1 = p.getLocation().getZ();
                                                                        if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                                            p.sendMessage("§7Teleportacja anulowana z powodu ruszenia się");
                                                                        }
                                                                        if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §93s"));
                                                                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                                public void run() {
                                                                                    double tpX1 = p.getLocation().getX();
                                                                                    double tpY1 = p.getLocation().getY();
                                                                                    double tpZ1 = p.getLocation().getZ();
                                                                                    if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                                                        p.sendMessage("§7Teleportacja anulowana z powodu ruszenia się");
                                                                                    }
                                                                                    if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                                                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §92s"));
                                                                                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                                            public void run() {
                                                                                                double tpX1 = p.getLocation().getX();
                                                                                                double tpY1 = p.getLocation().getY();
                                                                                                double tpZ1 = p.getLocation().getZ();
                                                                                                if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                                                                    p.sendMessage("§7Teleportacja anulowana z powodu ruszenia się");
                                                                                                }
                                                                                                if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                                                                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §91s"));
                                                                                                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                                                        public void run() {
                                                                                                            if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                                                                                p.teleport(new Location(p.getServer().getWorld(world), x, y, z, yaw, pitch));
                                                                                                                p.sendMessage("§7Pomyślnie przeteleportowano ciebie na spawn");
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
                p.teleport(new Location(p.getServer().getWorld(world), x, y, z, yaw, pitch));
                p.sendMessage("§7Pomyślnie przeteleportowano ciebie na spawn");
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.spawn");
        }
        return false;
    }

}
