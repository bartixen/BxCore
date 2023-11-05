package pl.bartixen.bxcore.Reward;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import pl.bartixen.bxcore.Data.StatyDataManager;
import pl.bartixen.bxcore.Data.UserDataManager;
import pl.bartixen.bxcore.Main;
import pl.bartixen.bxcore.Utils.ItemBuilder;

public class RewardCommand implements CommandExecutor {

    Main plugin;

    static StatyDataManager statyd;

    static UserDataManager userd;

    public RewardCommand(Main m) {
        plugin = m;
        m.getCommand("nagrody").setExecutor(this);
        m.getCommand("nagroda").setExecutor(this);
        m.getCommand("reward").setExecutor(this);
        statyd = StatyDataManager.getInstance();
        userd = UserDataManager.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player)sender;
        String uuid = String.valueOf(p.getUniqueId());
        reward(uuid, p);
        return false;
    }

    public static InventoryView reward(String uuid, Player sender) {
        Inventory inventory = Bukkit.createInventory(sender, 54, ("§9§lNagrody dla ciebie"));

        int postawione = statyd.getData().getInt(uuid + ".places");
        int wykopane = statyd.getData().getInt(uuid + ".breaks");
        int diamenty = statyd.getData().getInt(uuid + ".diamonds");
        int swierci = statyd.getData().getInt(uuid + ".deaths");
        int czas = statyd.getData().getInt(uuid + ".times");
        int noce = statyd.getData().getInt(uuid + ".sleeps");
        int smoki = statyd.getData().getInt(uuid + ".dragons");

        int godziny = czas / 60;
        int minuty = czas % 60;

        inventory.setItem(1, (new ItemBuilder(Material.STONE, 1)).setTitle("§f§lPostawione bloki: §9§l" + postawione).addLore(" ").addLore("§7To są twoje statystyki").build());
        inventory.setItem(2, (new ItemBuilder(Material.GOLDEN_PICKAXE, 1)).setTitle("§f§lWykopane bloki: §9§l" + wykopane).addLore(" ").addLore("§7To są twoje statystyki").build());
        inventory.setItem(3, (new ItemBuilder(Material.DIAMOND, 1)).setTitle("§f§lWykopane diamenty: §9§l" + diamenty).addLore(" ").addLore("§7To są twoje statystyki").build());
        inventory.setItem(4, (new ItemBuilder(Material.SKELETON_SKULL, 1)).setTitle("§f§lŚmierci: §9§l" + swierci).addLore(" ").addLore("§7To są twoje statystyki").build());
        inventory.setItem(5, (new ItemBuilder(Material.CLOCK, 1)).setTitle("§f§lSpędzony czas: §9§l" + godziny + "h " + minuty + "min").addLore(" ").addLore("§7To są twoje statystyki").build());
        inventory.setItem(6, (new ItemBuilder(Material.RED_BED, 1)).setTitle("§f§lPrzespane noce: §9§l" + noce).addLore(" ").addLore("§7To są twoje statystyki").build());
        inventory.setItem(7, (new ItemBuilder(Material.DRAGON_HEAD, 1)).setTitle("§f§lZabitych smoków: §9§l" + smoki).addLore(" ").addLore("§7To są twoje statystyki").build());

        inventory.setItem(46, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 16x Diament").addLore(" ").addLore("§fAby to odebrać musisz postawić §925 000 §fbloków").build());
        inventory.setItem(37, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 13x Obsydian").addLore(" ").addLore("§fAby to odebrać musisz postawić §915 000 §fbloków").build());
        inventory.setItem(28, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Diamentowa Siekiera").addLore(" ").addLore("§fAby to odebrać musisz postawić §910 000 §fbloków").build());
        inventory.setItem(19, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 6x Skrzynia").addLore(" ").addLore("§fAby to odebrać musisz postawić §95 000 §fbloków").build());
        inventory.setItem(10, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Zestaw Treakota").addLore(" ").addLore("§fAby to odebrać musisz postawić §92 500 §fbloków").build());

        if (postawione >= 2500) {
            if (statyd.getData().getBoolean(uuid + ".reward.place.first")) {
                inventory.setItem(10, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Zestaw Treakota").addLore(" ").build());
            } else {
                inventory.setItem(10, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Zestaw Treakota").addLore(" ").addLore("§7Kliknij aby odebrać").build());
            }
            if (postawione >= 5000) {
                if (statyd.getData().getBoolean(uuid + ".reward.place.second")) {
                    inventory.setItem(19, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 6x Skrzynia").addLore(" ").build());
                } else {
                    inventory.setItem(19, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 6x Skrzynia").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                }
                if (postawione >= 10000) {
                    if (statyd.getData().getBoolean(uuid + ".reward.place.third")) {
                        inventory.setItem(28, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Diamentowa Siekiera").addLore(" ").build());
                    } else {
                        inventory.setItem(28, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Diamentowa Siekiera").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                    }
                    if (postawione >= 15000) {
                        if (statyd.getData().getBoolean(uuid + ".reward.place.fourth")) {
                            inventory.setItem(37, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 13x Obsydian").addLore(" ").build());
                        } else {
                            inventory.setItem(37, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 13x Obsydian").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                        }
                        if (postawione >= 25000) {
                            if (statyd.getData().getBoolean(uuid + ".reward.place.fifth")) {
                                inventory.setItem(46, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 16x Diament").addLore(" ").build());
                            } else {
                                inventory.setItem(46, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 16x Diament").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                            }
                        }
                    }
                }
            }
        }

        inventory.setItem(47, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Łuk z Skinem AK-47").addLore(" ").addLore("§fAby to odebrać musisz wykopać §9100 000 §fbloków").build());
        inventory.setItem(38, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Efekt Haste II na 15 min").addLore(" ").addLore("§fAby to odebrać musisz wykopać §950 000 §fbloków").build());
        inventory.setItem(29, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Książka Mending").addLore(" ").addLore("§fAby to odebrać musisz wykopać §925 000 §fbloków").build());
        inventory.setItem(20, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Diamentowy Kilof").addLore(" ").addLore("§fAby to odebrać musisz wykopać §915 000 §fbloków").build());
        inventory.setItem(11, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Kowadło").addLore(" ").addLore("§fAby to odebrać musisz wykopać §95 000 §fbloków").build());

        if (wykopane >= 5000) {
            if (statyd.getData().getBoolean(uuid + ".reward.break.first")) {
                inventory.setItem(11, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Kowadło").addLore(" ").build());
            } else {
                inventory.setItem(11, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Kowadło").addLore(" ").addLore("§7Kliknij aby odebrać").build());
            }
            if (wykopane >= 15000) {
                if (statyd.getData().getBoolean(uuid + ".reward.break.second")) {
                    inventory.setItem(20, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Diamentowy Kilof").addLore(" ").build());
                } else {
                    inventory.setItem(20, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Diamentowy Kilof").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                }
                if (wykopane >= 25000) {
                    if (statyd.getData().getBoolean(uuid + ".reward.break.third")) {
                        inventory.setItem(29, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Książka Mending").addLore(" ").build());
                    } else {
                        inventory.setItem(29, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Książka Mending").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                    }
                    if (wykopane >= 50000) {
                        if (statyd.getData().getBoolean(uuid + ".reward.break.fourth")) {
                            inventory.setItem(38, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Efekt Haste II na 15 min").addLore(" ").build());
                        } else {
                            inventory.setItem(38, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Efekt Haste II na 15 min").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                        }
                        if (wykopane >= 100000) {
                            if (statyd.getData().getBoolean(uuid + ".reward.break.fifth")) {
                                inventory.setItem(47, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Łuk z Skinem AK-47").addLore(" ").build());
                            } else {
                                inventory.setItem(47, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Łuk z Skinem AK-47").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                            }
                        }
                    }
                }
            }
        }

        inventory.setItem(48, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Miecz z Netherytu z Skinem Skull Cutlass").addLore(" ").addLore("§fAby to odebrać musisz wykopać §9256 §fdiamentów").build());
        inventory.setItem(39, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Totem Nieśmiertelności").addLore(" ").addLore("§fAby to odebrać musisz wykopać §9128 §fdiamentów").build());
        inventory.setItem(30, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Sztabka Netherytu").addLore(" ").addLore("§fAby to odebrać musisz wykopać §964 §fdiamentów").build());
        inventory.setItem(21, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 3x Oko Endu").addLore(" ").addLore("§fAby to odebrać musisz wykopać §932 §fdiamentów").build());
        inventory.setItem(12, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Diamentowy Miecz").addLore(" ").addLore("§fAby to odebrać musisz wykopać §916 §fdiamentów").build());

        if (diamenty >= 16) {
            if (statyd.getData().getBoolean(uuid + ".reward.diamond.first")) {
                inventory.setItem(12, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Diamentowy Miecz").addLore(" ").build());
            } else {
                inventory.setItem(12, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Diamentowy Miecz").addLore(" ").addLore("§7Kliknij aby odebrać").build());
            }
            if (diamenty >= 32) {
                if (statyd.getData().getBoolean(uuid + ".reward.diamond.second")) {
                    inventory.setItem(21, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 3x Oko Endu").addLore(" ").build());
                } else {
                    inventory.setItem(21, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 3x Oko Endu").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                }
                if (diamenty >= 64) {
                    if (statyd.getData().getBoolean(uuid + ".reward.diamond.third")) {
                        inventory.setItem(30, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Sztabka Netherytu").addLore(" ").build());
                    } else {
                        inventory.setItem(30, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Sztabka Netherytu").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                    }
                    if (diamenty >= 128) {
                        if (statyd.getData().getBoolean(uuid + ".reward.diamond.fourth")) {
                            inventory.setItem(39, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Totem Nieśmiertelności").addLore(" ").build());
                        } else {
                            inventory.setItem(39, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Totem Nieśmiertelności").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                        }
                        if (diamenty >= 256) {
                            if (statyd.getData().getBoolean(uuid + ".reward.diamond.fifth")) {
                                inventory.setItem(48, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Miecz z Netherytu z Skinem Skull Cutlass").addLore(" ").build());
                            } else {
                                inventory.setItem(48, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Miecz z Netherytu z Skinem Skull Cutlass").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                            }
                        }
                    }
                }
            }
        }

        inventory.setItem(49, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Twoja Główka").addLore(" ").addLore("§fAby to odebrać musisz uzyskać §9250 §fśmierci").build());
        inventory.setItem(40, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Kamienny Miecz").addLore(" ").addLore("§fAby to odebrać musisz uzyskać §9100 §fśmierci").build());
        inventory.setItem(31, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Łuk").addLore(" ").addLore("§fAby to odebrać musisz uzyskać §950 §fśmierci").build());
        inventory.setItem(22, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Skórzana Zbroja").addLore(" ").addLore("§fAby to odebrać musisz uzyskać §925 §fśmierci").build());
        inventory.setItem(13, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Tarcza").addLore(" ").addLore("§fAby to odebrać musisz uzyskać §910 §fśmierci").build());

        if (swierci >= 10) {
            if (statyd.getData().getBoolean(uuid + ".reward.death.first")) {
                inventory.setItem(13, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Tarcza").addLore(" ").build());
            } else {
                inventory.setItem(13, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Tarcza").addLore(" ").addLore("§7Kliknij aby odebrać").build());
            }
            if (swierci >= 25) {
                if (statyd.getData().getBoolean(uuid + ".reward.death.second")) {
                    inventory.setItem(22, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Skórzana Zbroja").addLore(" ").build());
                } else {
                    inventory.setItem(22, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Skórzana Zbroja").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                }
                if (swierci >= 50) {
                    if (statyd.getData().getBoolean(uuid + ".reward.death.third")) {
                        inventory.setItem(31, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Łuk").addLore(" ").build());
                    } else {
                        inventory.setItem(31, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Łuk").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                    }
                    if (swierci >= 100) {
                        if (statyd.getData().getBoolean(uuid + ".reward.death.fourth")) {
                            inventory.setItem(40, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Kamienny Miecz").addLore(" ").build());
                        } else {
                            inventory.setItem(40, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Kamienny Miecz").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                        }
                        if (swierci >= 250) {
                            if (statyd.getData().getBoolean(uuid + ".reward.death.fifth")) {
                                inventory.setItem(49, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Twoja Główka").addLore(" ").build());
                            } else {
                                inventory.setItem(49, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Twoja Główka").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                            }
                        }
                    }
                }
            }
        }

        inventory.setItem(50, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Jajko Ocelota").addLore(" ").addLore("§fAby to odebrać musisz przegrane §9100h §fna serwerze").build());
        inventory.setItem(41, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 Dostęp do /tpa").addLore(" ").addLore("§fAby to odebrać musisz uzyskać §950h §fna serwerze").build());
        inventory.setItem(32, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 Dostęp do 1 /home").addLore(" ").addLore("§fAby to odebrać musisz uzyskać §925h §fna serwerze").build());
        inventory.setItem(23, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Mikstura Szybkość").addLore(" ").addLore("§fAby to odebrać musisz uzyskać §910h §fna serwerze").build());
        inventory.setItem(14, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Łóżko").addLore(" ").addLore("§fAby to odebrać musisz uzyskać §92h §fna serwerze").build());

        if (czas >= 120) {
            if (statyd.getData().getBoolean(uuid + ".reward.time.first")) {
                inventory.setItem(14, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Łóżko").addLore(" ").build());
            } else {
                inventory.setItem(14, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Łóżko").addLore(" ").addLore("§7Kliknij aby odebrać").build());
            }
            if (czas >= 600) {
                if (statyd.getData().getBoolean(uuid + ".reward.time.second")) {
                    inventory.setItem(23, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Mikstura Szybkość").addLore(" ").build());
                } else {
                    inventory.setItem(23, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Mikstura Szybkość").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                }
                if (czas >= 1500) {
                    if (statyd.getData().getBoolean(uuid + ".reward.time.third")) {
                        inventory.setItem(32, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 Dostęp do 1 /home").addLore(" ").build());
                    } else {
                        inventory.setItem(32, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 Dostęp do 1 /home").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                    }
                    if (czas >= 3000) {
                        if (statyd.getData().getBoolean(uuid + ".reward.time.fourth")) {
                            inventory.setItem(41, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 Dostęp do /tpa").addLore(" ").build());
                        } else {
                            inventory.setItem(41, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 Dostęp do /tpa").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                        }
                        if (czas >= 6000) {
                            if (statyd.getData().getBoolean(uuid + ".reward.time.fifth")) {
                                inventory.setItem(50, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Jajko Ocelota").addLore(" ").build());
                            } else {
                                inventory.setItem(50, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Jajko Ocelota").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                            }
                        }
                    }
                }
            }
        }

        inventory.setItem(51, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Głowa Creepera").addLore(" ").addLore("§fAby to odebrać musisz mieć przespane §9250 §fnocy na serwerze").build());
        inventory.setItem(42, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 32x Butelka Expa").addLore(" ").addLore("§fAby to odebrać musisz mieć przespane §9150 §fnocy na serwerze").build());
        inventory.setItem(33, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Siodło").addLore(" ").addLore("§fAby to odebrać musisz mieć przespane §9100 §fnocy na serwerze").build());
        inventory.setItem(24, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Znacznik").addLore(" ").addLore("§fAby to odebrać musisz mieć przespane §950 §fnocy na serwerze").build());
        inventory.setItem(15, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 32x Magiczna Lampa Oświetleniowa").addLore(" ").addLore("§fAby to odebrać musisz mieć przespane §925 §fnocy na serwerze").build());

        if (noce >= 25) {
            if (statyd.getData().getBoolean(uuid + ".reward.sleep.first")) {
                inventory.setItem(15, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 32x Magiczna Lampa Oświetleniowa").addLore(" ").build());
            } else {
                inventory.setItem(15, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 32x Magiczna Lampa Oświetleniowa").addLore(" ").addLore("§7Kliknij aby odebrać").build());
            }
            if (noce >= 50) {
                if (statyd.getData().getBoolean(uuid + ".reward.sleep.second")) {
                    inventory.setItem(24, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Znacznik").addLore(" ").build());
                } else {
                    inventory.setItem(24, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Znacznik").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                }
                if (noce >= 100) {
                    if (statyd.getData().getBoolean(uuid + ".reward.sleep.third")) {
                        inventory.setItem(33, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Siodło").addLore(" ").build());
                    } else {
                        inventory.setItem(33, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Siodło").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                    }
                    if (noce >= 150) {
                        if (statyd.getData().getBoolean(uuid + ".reward.sleep.fourth")) {
                            inventory.setItem(42, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 32x Butelka Expa").addLore(" ").build());
                        } else {
                            inventory.setItem(42, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 32x Butelka Expa").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                        }
                        if (noce >= 250) {
                            if (statyd.getData().getBoolean(uuid + ".reward.sleep.fifth")) {
                                inventory.setItem(51, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Głowa Creepera").addLore(" ").build());
                            } else {
                                inventory.setItem(51, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Głowa Creepera").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                            }
                        }
                    }
                }
            }
        }

        inventory.setItem(52, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Elytra z Skinem Enderman Minecon").addLore(" ").addLore("§fAby to odebrać musisz zabić §925 §fsmoków").build());
        inventory.setItem(43, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Diamentowa Zbroja Końska").addLore(" ").addLore("§fAby to odebrać musisz zabić §915 §fsmoków").build());
        inventory.setItem(34, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 128x Fajerwerek (moc 3)").addLore(" ").addLore("§fAby to odebrać musisz zabić §910 §fsmoków").build());
        inventory.setItem(25, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Skulkerowa Skrzynia").addLore(" ").addLore("§fAby to odebrać musisz zabić §95 §fsmoków").build());
        inventory.setItem(16, (new ItemBuilder(Material.RED_CONCRETE, 1)).setTitle("§c§lZABLOKOWANE").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Totem Nieśmiertelności").addLore(" ").addLore("§fAby to odebrać musisz zabić §92 §fsmoki").build());

        if (smoki >= 2) {
            if (statyd.getData().getBoolean(uuid + ".reward.dragon.first")) {
                inventory.setItem(16, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Totem Nieśmiertelności").addLore(" ").build());
            } else {
                inventory.setItem(16, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Totem Nieśmiertelności").addLore(" ").addLore("§7Kliknij aby odebrać").build());
            }
            if (smoki >= 5) {
                if (statyd.getData().getBoolean(uuid + ".reward.dragon.second")) {
                    inventory.setItem(25, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Skulkerowa Skrzynia").addLore(" ").build());
                } else {
                    inventory.setItem(25, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Skulkerowa Skrzynia").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                }
                if (smoki >= 10) {
                    if (statyd.getData().getBoolean(uuid + ".reward.dragon.third")) {
                        inventory.setItem(34, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 128x Fajerwerek (moc 3)").addLore(" ").build());
                    } else {
                        inventory.setItem(34, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 128x Fajerwerek (moc 3)").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                    }
                    if (smoki >= 15) {
                        if (statyd.getData().getBoolean(uuid + ".reward.dragon.fourth")) {
                            inventory.setItem(43, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Diamentowa Zbroja Końska").addLore(" ").build());
                        } else {
                            inventory.setItem(43, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Diamentowa Zbroja Końska").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                        }
                        if (smoki >= 25) {
                            if (statyd.getData().getBoolean(uuid + ".reward.dragon.fifth")) {
                                inventory.setItem(52, (new ItemBuilder(Material.LIGHT_GRAY_CONCRETE, 1)).setTitle("§7§lODEBRANO").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Elytra z Skinem Enderman Minecon").addLore(" ").build());
                            } else {
                                inventory.setItem(52, (new ItemBuilder(Material.LIME_CONCRETE, 1)).setTitle("§a§lODBIERZ").addLore(" ").addLore("§e§l\uD83C\uDF81 1x Elytra z Skinem Enderman Minecon").addLore(" ").addLore("§7Kliknij aby odebrać").build());
                            }
                        }
                    }
                }
            }
        }

        ItemBuilder backguard = (new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, 1)).setTitle("§7");
        inventory.setItem(0, backguard.build());
        inventory.setItem(8, backguard.build());
        inventory.setItem(9, backguard.build());
        inventory.setItem(17, backguard.build());
        inventory.setItem(18, backguard.build());
        inventory.setItem(26, backguard.build());
        inventory.setItem(27, backguard.build());
        inventory.setItem(35, backguard.build());
        inventory.setItem(36, backguard.build());
        inventory.setItem(44, backguard.build());
        inventory.setItem(45, backguard.build());
        inventory.setItem(53, backguard.build());

        return sender.openInventory(inventory);
    }

}
