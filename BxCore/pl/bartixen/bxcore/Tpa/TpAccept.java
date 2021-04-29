package pl.bartixen.bxcore.Tpa;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

public class TpAccept implements CommandExecutor {

    Main plugin;

    public TpAccept(Main m) {
        plugin = m;
        m.getCommand("tpaccept").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cTa komenda jest przeznaczona tylko dla graczy");
            return false;
        }
        Player p = (Player) sender;
        if (!TpSystem.request.containsKey(p.getName())) {
            p.sendMessage("§7Nie masz żadnej prośby o teleportacje");
            return false;
        }
        Player p2 = Bukkit.getPlayer(TpSystem.request.get(p.getName()));
        TpSystem.removeRequest(p.getName());

        if (p2 !=null){
            if (!(p.hasPermission("bxhome.user.bypass") || p.isOp())) {
                double tpX = p2.getLocation().getX();
                double tpY = p2.getLocation().getY();
                double tpZ = p2.getLocation().getZ();
                p2.sendMessage("§7Gracz §9" + p.getName() + " §7zakceptowal twoja prośbe");
                p2.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §98s"));
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    public void run(){
                        double tpX1 = p2.getLocation().getX();
                        double tpY1 = p2.getLocation().getY();
                        double tpZ1 = p2.getLocation().getZ();
                        if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                            p2.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                        }
                        if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                            p2.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §97s"));
                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                public void run() {
                                    double tpX1 = p2.getLocation().getX();
                                    double tpY1 = p2.getLocation().getY();
                                    double tpZ1 = p2.getLocation().getZ();
                                    if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                        p2.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                                    }
                                    if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                        p2.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §96s"));
                                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                            public void run() {
                                                double tpX1 = p2.getLocation().getX();
                                                double tpY1 = p2.getLocation().getY();
                                                double tpZ1 = p2.getLocation().getZ();
                                                if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                    p2.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                                                }
                                                if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                    p2.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §95s"));
                                                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                        public void run() {
                                                            double tpX1 = p2.getLocation().getX();
                                                            double tpY1 = p2.getLocation().getY();
                                                            double tpZ1 = p2.getLocation().getZ();
                                                            if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                                p2.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                                                            }
                                                            if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                                p2.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §94s"));
                                                                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                    public void run() {
                                                                        double tpX1 = p2.getLocation().getX();
                                                                        double tpY1 = p2.getLocation().getY();
                                                                        double tpZ1 = p2.getLocation().getZ();
                                                                        if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                                            p2.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                                                                        }
                                                                        if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                                            p2.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §93s"));
                                                                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                                public void run() {
                                                                                    double tpX1 = p2.getLocation().getX();
                                                                                    double tpY1 = p2.getLocation().getY();
                                                                                    double tpZ1 = p2.getLocation().getZ();
                                                                                    if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                                                        p2.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                                                                                    }
                                                                                    if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                                                        p2.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §92s"));
                                                                                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                                            public void run() {
                                                                                                double tpX1 = p2.getLocation().getX();
                                                                                                double tpY1 = p2.getLocation().getY();
                                                                                                double tpZ1 = p2.getLocation().getZ();
                                                                                                if (!(tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1)) {
                                                                                                    p2.sendMessage("§cTeleportacja anulowana z powodu ruszenia się");
                                                                                                }
                                                                                                if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                                                                    p2.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§7Teleportacja nastapi za §91s"));
                                                                                                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                                                        public void run() {
                                                                                                            if (tpX == tpX1 && tpY == tpY1 && tpZ == tpZ1) {
                                                                                                                p2.teleport(p);
                                                                                                                p2.sendMessage("§7Pomyślnie przeteleportowano ciebie do gracza §9" + p.getName());
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
                p2.teleport(p);
                p2.sendMessage("§7Pomyślnie przeteleportowano ciebie do gracza §9" + p.getName());
            }
        } else {
            p.sendMessage("§7Gracz który prosil o teleportacje jest §coffline");
        }
        return false;
    }
}