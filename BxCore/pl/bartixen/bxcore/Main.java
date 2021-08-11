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
import pl.bartixen.bxcore.Permission.PermAddition;
import pl.bartixen.bxcore.Permission.PermCommand;
import pl.bartixen.bxcore.Permission.PermissionConfig;
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
    AntyLogutDataManager antylogutd;
    PermissionConfig permd;

    public Main() {
        hd = HomeDataManager.getInstance();
        msgd = MsgDataManager.getInstance();
        userd = UserDataManager.getInstance();
        statyd = StatyDataManager.getInstance();
        band = BanDataManager.getInstance();
        wld = WhitelistDataManager.getInstance();
        rtpd = RtpDataManager.getInstance();
        antyd = AntyXrayDataManager.getInstance();
        antylogutd = AntyLogutDataManager.getInstance();
        permd = PermissionConfig.getInstance();
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

            getLogger().log(Level.INFO, "§fChecking:");

            getConfig().options().copyDefaults(true);
            saveConfig();

            getLogger().log(Level.INFO, "§7- §fChecking for updates §7| §cERROR");

            try {
                hd.setup(this);
                getLogger().log(Level.INFO, "§7- §fHomeDataManager §7| §aOK");
            } catch (IOException e) {
                e.printStackTrace();
                getLogger().log(Level.INFO, "§7- §fHomeDataManager §7| §cERROR");
            }

            try {
                msgd.setup(this);
                getLogger().log(Level.INFO, "§7- §fMsgDataManager §7| §aOK");
            } catch (IOException e) {
                e.printStackTrace();
                getLogger().log(Level.INFO, "§7- §fMsgDataManager §7| §cERROR");
            }

            try {
                userd.setup(this);
                getLogger().log(Level.INFO, "§7- §fUserDataManager §7| §aOK");
            } catch (IOException e) {
                e.printStackTrace();
                getLogger().log(Level.INFO, "§7- §fUserDataManager §7| §cERROR");
            }

            try {
                statyd.setup(this);
                getLogger().log(Level.INFO, "§7- §fStatyDataManager §7| §aOK");
            } catch (IOException e) {
                e.printStackTrace();
                getLogger().log(Level.INFO, "§7- §fStatyDataManager §7| §cERROR");
            }

            try {
                antyd.setup(this);
                getLogger().log(Level.INFO, "§7- §fAntyXrayDataManager §7| §aOK");
            } catch (IOException e) {
                e.printStackTrace();
                getLogger().log(Level.INFO, "§7- §fAntyXrayDataManager §7| §cERROR");
            }

            try {
                band.setup(this);
                getLogger().log(Level.INFO, "§7- §fBanDataManager §7| §aOK");
            } catch (IOException e) {
                e.printStackTrace();
                getLogger().log(Level.INFO, "§7- §fBanDataManager §7| §cERROR");
            }

            try {
                wld.setup(this);
                getLogger().log(Level.INFO, "§7- §fWhitelistDataManager §7| §aOK");
            } catch (IOException e) {
                e.printStackTrace();
                getLogger().log(Level.INFO, "§7- §fWhitelistDataManager §7| §cERROR");
            }

            try {
                antylogutd.setup(this);
                getLogger().log(Level.INFO, "§7- §fAntyXrayDataManager §7| §aOK");
            } catch (IOException e) {
                e.printStackTrace();
                getLogger().log(Level.INFO, "§7- §fAntyXrayDataManager §7| §cERROR");
            }

            try {
                rtpd.setup(this);
                getLogger().log(Level.INFO, "§7- §fRtpDataManager §7| §aOK");
            } catch (IOException e) {
                e.printStackTrace();
                getLogger().log(Level.INFO, "§7- §fRtpDataManager §7| §cERROR");
            }

            try {
                permd.setup(this);
                getLogger().log(Level.INFO, "§7- §fPermissionConfig §7| §aOK");
            } catch (IOException e) {
                e.printStackTrace();
                getLogger().log(Level.INFO, "§7- §fPermissionConfig §7| §cERROR");
            }

            getLogger().log(Level.INFO, "§fServer status:");

            Runtime runtime = Runtime.getRuntime();
            String name = getConfig().getString("nazwa");
            String version = getServer().getVersion();
            int ram = (int) (runtime.totalMemory() / 1048576L);
            int port = getServer().getPort();
            String ip = getServer().getIp();
            int viewdistance = getServer().getViewDistance();
            String onlinemode = String.valueOf(getServer().getOnlineMode());

            getLogger().log(Level.INFO, "§7- §fName §7| §9" + name);

            getLogger().log(Level.INFO, "§7- §fVersion §7| §9" + version);

            getLogger().log(Level.INFO, "§7- §fRam §7| §9" + ram + " MB");

            getLogger().log(Level.INFO, "§7- §fPort §7| §9" + port);

            getLogger().log(Level.INFO, "§7- §fIP §7| §9" + ip);

            getLogger().log(Level.INFO, "§7- §fView Distance §7| §9" + viewdistance);

            getLogger().log(Level.INFO, "§7- §fOnline Mode §7| §9" + onlinemode);

            String autodziala = getConfig().getString("automessage.dziala");
            int autoczas = getConfig().getInt("automessage.czas");

            if (autodziala.equals("true")) {
                new AutoMessage(this).runTaskTimer(this, 0, autoczas * 20);
            }

            main = this;

            if (permd.getData().getString("default") == null) {
                permd.getData().set("default", "gracz");
                permd.getData().set("groups.admin.prefix", "§8[§cADMIN§8]");
                ArrayList<String> listPermission = new ArrayList<>();
                listPermission.add("bxcore.commands.gamemode");
                listPermission.add("bxcore.commands.whitelist");
                permd.getData().set("groups.admin.permissions", listPermission);
                permd.getData().set("groups.gracz.prefix", "§8[§aGRACZ§8]");
                ArrayList<String> listPermission1 = new ArrayList<>();
                listPermission1.add("bxcore.commands.spawn");
                permd.getData().set("groups.gracz.permissions", listPermission1);
                permd.getData().set("users.f41908b0-e496-42a1-8562-9cbfc2265ca3", "admin");
                try {
                    permd.saveData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

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
            new BlockedWorldCommand(this);
            new TpDeny(this);
            new TpSystem(this);
            new AlertCommand(this);
            new BackCommand(this);
            new PermCommand(this);
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
            new RepairCommand(this);
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
            getServer().getPluginManager().registerEvents(new Mending(this), this);
            getServer().getPluginManager().registerEvents(new BlockedWorld(this), this);
            getServer().getPluginManager().registerEvents(new PvpCommand(this), this);
            getServer().getPluginManager().registerEvents(new LvLDragon(this), this);
            getServer().getPluginManager().registerEvents(new AntyLogut(this), this);
            getServer().getPluginManager().registerEvents(new PvpCommand(this), this);
            getServer().getPluginManager().registerEvents(new PermAddition(this), this);
            new RefreshAction(this).runTaskTimer(this, 0, 20 * 2);
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

        }
    }

    public static Main getMain() {
        return main;
    }

}