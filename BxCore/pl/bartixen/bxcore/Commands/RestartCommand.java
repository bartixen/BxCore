package pl.bartixen.bxcore.Commands;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

public class RestartCommand implements CommandExecutor {

    Main plugin;

    public static boolean restart = false;

    public RestartCommand(Main m) {
        plugin = m;
        m.getCommand("wylaczenie").setExecutor(this);
    }

    public void anulurrestart(BossBar bar) {
        bar.setTitle("§c§lRestart zostal anulowany");
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                bar.removeAll();
            }
        }, 20);
    }

    public void bossbar() {
        if (restart) {
            BossBar bar = Bukkit.createBossBar("§c§lRestart serwera za §c§l60s", BarColor.RED, BarStyle.SEGMENTED_10);
            bar.setVisible(true);
            for (Player players : Bukkit.getOnlinePlayers()) {
                bar.addPlayer(players);
            }
            bar.setProgress(1.0D);

            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                public void run() {
                    if (restart) {
                        bar.setProgress(0.9D);
                        bar.setTitle("§c§lRestart serwera za §c§l50s");
                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                            public void run() {
                                if (restart) {
                                    bar.setProgress(0.8D);
                                    bar.setTitle("§c§lRestart serwera za §c§l40s");
                                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                        public void run() {
                                            if (restart) {
                                                bar.setProgress(0.7D);
                                                bar.setTitle("§c§lRestart serwera za §c§l30s");
                                                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                    public void run() {
                                                        if (restart) {
                                                            bar.setProgress(0.6D);
                                                            bar.setTitle("§c§lRestart serwera za §c§l20s");
                                                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                public void run() {
                                                                    if (restart) {
                                                                        bar.setProgress(0.5D);
                                                                        bar.setTitle("§c§lRestart serwera za §c§l10s");
                                                                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                            public void run() {
                                                                                if (restart) {
                                                                                    bar.setProgress(0.4D);
                                                                                    bar.setTitle("§c§lRestart serwera za §c§l5s");
                                                                                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                                        public void run() {
                                                                                            if (restart) {
                                                                                                bar.setProgress(0.3D);
                                                                                                bar.setTitle("§c§lRestart serwera za §c§l3s");
                                                                                                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                                                    public void run() {
                                                                                                        if (restart) {
                                                                                                            bar.setProgress(0.2D);
                                                                                                            bar.setTitle("§c§lRestart serwera za §c§l2s");
                                                                                                            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                                                                public void run() {
                                                                                                                    if (restart) {
                                                                                                                        bar.setProgress(0.1D);
                                                                                                                        bar.setTitle("§c§lRestart serwera za §c§l1s");
                                                                                                                        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                                                                            public void run() {
                                                                                                                                bar.setProgress(0.0D);
                                                                                                                                bar.removeAll();
                                                                                                                                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "save-all");
                                                                                                                                if (restart) {
                                                                                                                                    for (Player players : Bukkit.getOnlinePlayers()) {
                                                                                                                                        players.kickPlayer("§cRestart serwera");
                                                                                                                                    }
                                                                                                                                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                                                                                                                        public void run() {
                                                                                                                                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "stop");
                                                                                                                                        }
                                                                                                                                    }, 20);
                                                                                                                                } else {
                                                                                                                                    anulurrestart(bar);
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }, 20);

                                                                                                                    } else {
                                                                                                                        anulurrestart(bar);
                                                                                                                    }
                                                                                                                }
                                                                                                            }, 20);
                                                                                                        } else {
                                                                                                            anulurrestart(bar);
                                                                                                        }
                                                                                                    }
                                                                                                }, 20);
                                                                                            } else {
                                                                                                anulurrestart(bar);
                                                                                            }
                                                                                        }
                                                                                    }, 20 * 2);
                                                                                } else {
                                                                                    anulurrestart(bar);
                                                                                }
                                                                            }
                                                                        }, 20 * 5);
                                                                    } else {
                                                                        anulurrestart(bar);
                                                                    }
                                                                }
                                                            }, 20 * 10);
                                                        } else {
                                                            anulurrestart(bar);
                                                        }
                                                    }
                                                }, 20 * 10);
                                            } else {
                                                anulurrestart(bar);
                                            }
                                        }
                                    }, 20 * 10);
                                } else {
                                    anulurrestart(bar);
                                }
                            }
                        }, 20 * 10);
                    } else {
                        anulurrestart(bar);
                    }
                }
            }, 20 * 10);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender.hasPermission("bxcore.commands.restart") || sender.isOp()) {
            if (!restart) {
                sender.sendMessage("§cRestart zostal aktywowany");
                restart = true;
                bossbar();
            } else {
                restart = false;
                sender.sendMessage("§cAnulowano restart serwera");
            }
        } else {
            sender.sendMessage("§7Brak permisji: §9bxcore.commands.restart");
        }
        return false;
    }
}
