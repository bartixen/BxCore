package pl.bartixen.bxcore;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import pl.bartixen.bxcore.Ban.*;
import pl.bartixen.bxcore.Commands.*;
import pl.bartixen.bxcore.Data.*;
import pl.bartixen.bxcore.Home.HomeCommand;
import pl.bartixen.bxcore.Home.HomeListener;
import pl.bartixen.bxcore.Home.PlayerJoin;
import pl.bartixen.bxcore.Listeners.*;
import pl.bartixen.bxcore.Msg.Ignore;
import pl.bartixen.bxcore.Msg.Msg;
import pl.bartixen.bxcore.Msg.Replay;
import pl.bartixen.bxcore.Msg.Socialspy;
import pl.bartixen.bxcore.Staty.StatyCommand;
import pl.bartixen.bxcore.Staty.StatyListeners;
import pl.bartixen.bxcore.Staty.StatyTime;
import pl.bartixen.bxcore.Tpa.TpAccept;
import pl.bartixen.bxcore.Tpa.TpDeny;
import pl.bartixen.bxcore.Tpa.TpSystem;
import pl.bartixen.bxcore.Tpa.Tpa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

public class Main extends JavaPlugin implements Listener {

    public int tps = 0;

    public static Main main;
    public static ArrayList<Player> invisible = new ArrayList<>();

    public String version = getDescription().getVersion();
    public String serwer = getConfig().getString("nazwa");

    HomeDataManager hd;
    MsgDataManager msgd;
    UserDataManager userd;
    StatyDataManager statyd;
    BanDataManager band;
    WhitelistDataManager wld;
    RtpDataManager rtpd;
    AntyXrayDataManager antyd;
    TeamDataManager teamd;
    AntyLogutDataManager antylogutd;

    public Main() {
        hd = HomeDataManager.getInstance();
        msgd = MsgDataManager.getInstance();
        userd = UserDataManager.getInstance();
        statyd = StatyDataManager.getInstance();
        band = BanDataManager.getInstance();
        wld = WhitelistDataManager.getInstance();
        rtpd = RtpDataManager.getInstance();
        antyd = AntyXrayDataManager.getInstance();
        teamd = TeamDataManager.getInstance();
        antylogutd = AntyLogutDataManager.getInstance();
    }

    @Override
    public void onEnable() {
        if ((!getDescription().getName().contains("BxCore")) || (!getDescription().getAuthors().contains("Bartixen"))) {
            getLogger().log(Level.WARNING, "§8[========== §9BxCore §8==========]");
            getLogger().log(Level.WARNING, "§cPlugin zostal wylaczony z powodu edytowania pliku §eplugin.yml");
            getLogger().log(Level.WARNING, "§8[========== §9BxCore §8==========]");
            Bukkit.getServer().getPluginManager().disablePlugin(this);
        } else {
            getLogger().log(Level.INFO, "§8[========== §9BxCore §8==========]");
            getLogger().log(Level.INFO, "§fVersion: §b{0}", getDescription().getVersion());
            getLogger().log(Level.INFO, "§fAuthor: §bBartixen");
            getLogger().log(Level.INFO, "§fWebsite: §bhttps://bartixen.pl");
            getLogger().log(Level.INFO, "§8[========== §9BxCore §8==========]");

            getConfig().options().copyDefaults(true);
            saveConfig();

            try {
                hd.setup(this);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                msgd.setup(this);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                userd.setup(this);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                statyd.setup(this);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                antyd.setup(this);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                band.setup(this);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                wld.setup(this);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                antylogutd.setup(this);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                rtpd.setup(this);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                teamd.setup(this);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String autodziala = getConfig().getString("automessage.dziala");
            int autoczas = getConfig().getInt("automessage.czas");

            if (autodziala.equals("true")) {
                new AutoMessage(this).runTaskTimer(this, 0, autoczas * 20);
            }

            main = this;
            new StatyTime(this).runTaskTimer(this, 0, 20 * 60);
            new StatyListeners(this);
            new MuteTimeChecker(this);
            new BanCommand(this);
            new BanIPCommand(this);
            new KickCommand(this);
            new BroadCastCommand(this);
            new BanTimeChecker(this);
            new TempMuteCommand(this);
            new TempBanCommand(this);
            new MuteCommand(this);
            new ServerStatsCommand(this);
            new RtpNadajCommand(this);
            new UnBanCommand(this);
            new UnMuteCommand(this);
            new ListCommand(this);
            new SpawnCommand(this);
            new Tpa(this);
            new TpAccept(this);
            new PvpCommand(this);
            try {
                new TeamCommand(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            new BlockedWorldCommand(this);
            new TpDeny(this);
            new TpSystem(this);
            new AlertCommand(this);
            new BackCommand(this);
            new BxCoreCommand(this);
            new ChatClearCommand(this);
            new ChatCommand(this);
            new RtpUsunCommand(this);
            new ClearCommand(this);
            new CraftingCommand(this);
            new DayCommand(this);
            new EnderchestCommand(this);
            new FlyCommand(this);
            new GamemodeCommand(this);
            new HealCommand(this);
            new HelpopCommand(this);
            new RtpCommand(this);
            new InvseeCommand(this);
            new GraczCommand(this);
            new NightCommand(this);
            new PluginsCommand(this);
            new PomocCommand(this);
            new PvpCommand(this);
            new SetSpawnCommand(this);
            new SpeedCommand(this);
            new TpCommand(this);
            try {
                new WhitelistCommand(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            new TphereCommand(this);
            new IPCommand(this);
            new WeatherCommand(this);
            new AnvilCommand(this);
            new WorldCommand(this);
            new VanishCommand(this);
            try {
                new TeamyCommand(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            new VoucherCommand(this);
            new HomeCommand(this);
            new Ignore(this);
            new RestartCommand(this);
            new Msg(this);
            new Replay(this);
            new Socialspy(this);
            new StatyCommand(this);

            getServer().getPluginManager().registerEvents(new AntyXray(this), this);
            getServer().getPluginManager().registerEvents(new GodCommand(this), this);
            getServer().getPluginManager().registerEvents(new UnknownCommand(this), this);
            getServer().getPluginManager().registerEvents(new BanListener(this), this);
            getServer().getPluginManager().registerEvents(new ChatCommand(this), this);
            getServer().getPluginManager().registerEvents(new BackCommand(this), this);
            getServer().getPluginManager().registerEvents(new CommandBlocker(this), this);
            getServer().getPluginManager().registerEvents(new AntySpam(this), this);
            getServer().getPluginManager().registerEvents(new JoinQuitMessage(this), this);
            getServer().getPluginManager().registerEvents(new Weather(this), this);
            getServer().getPluginManager().registerEvents(new ChatFormat(this), this);
            getServer().getPluginManager().registerEvents(new BedSleep(this), this);
            getServer().getPluginManager().registerEvents(new VanishEvents(this), this);
            getServer().getPluginManager().registerEvents(new HomeListener(this), this);
            getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
            getServer().getPluginManager().registerEvents(new PlayerData(this), this);
            getServer().getPluginManager().registerEvents(new StatyListeners(this), this);
            getServer().getPluginManager().registerEvents(new TabList(this), this);
            getServer().getPluginManager().registerEvents(new Motd(this), this);
            getServer().getPluginManager().registerEvents(new SilkSpawners(this), this);
            getServer().getPluginManager().registerEvents(new Rtp(this), this);
            getServer().getPluginManager().registerEvents(new VoucherHome(), this);
            getServer().getPluginManager().registerEvents(new Mending(this), this);
            getServer().getPluginManager().registerEvents(new BlockedWorld(this), this);
            getServer().getPluginManager().registerEvents(new PvpCommand(this), this);
            getServer().getPluginManager().registerEvents(new LvLDragon(this), this);
            getServer().getPluginManager().registerEvents(new AntyLogut(this), this);
            getServer().getPluginManager().registerEvents(new PvpCommand(this), this);
            try {
                getServer().getPluginManager().registerEvents(new TeamyCommand(this), this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                getServer().getPluginManager().registerEvents(new TeamCommand(this), this);
            } catch (IOException e) {
                e.printStackTrace();
            }
            new VanishAction(this).runTaskTimer(this, 0, 20 * 2);
            new ClearXray(this).runTaskTimer(this, 0, 3600 * 20);
            new AntyLogut(this).runTaskTimer(this, 0, 20);
            try {
                getServer().getPluginManager().registerEvents(new WhitelistCommand(this), this);
            } catch (IOException e) {
                e.printStackTrace();
            }

            antyd.getData().set("diamond", null);
            antyd.getData().set("netherite", null);
            try {
                antyd.saveData();
            } catch (IOException e) {
                e.printStackTrace();
            }

            getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
            {
                long sec;
                long currentSec;
                int ticks;

                @Override
                public void run() {
                    sec = (System.currentTimeMillis() / 1000);
                    if(currentSec == sec) {
                        ticks++;
                    } else {
                        currentSec = sec;
                        tps = (tps == 0 ? ticks : ((tps + ticks) / 2));
                        ticks = 0;
                    }
                }
            }, 0, 1);
        }
    }

    public static Main getMain() {
        return main;
    }

}