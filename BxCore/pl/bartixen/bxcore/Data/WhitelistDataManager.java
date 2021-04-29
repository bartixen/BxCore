package pl.bartixen.bxcore.Data;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class WhitelistDataManager {

    static WhitelistDataManager instance;
    Plugin p;
    FileConfiguration data;
    public static File wlfile;

    static {
        instance = new WhitelistDataManager();
    }

    public static WhitelistDataManager getInstance() {
        return instance;
    }

    public void setup(Plugin p) throws IOException {
        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdirs();
        }
        File path = new File(p.getDataFolder() + File.separator + "/data");
        wlfile = new File(path, String.valueOf(File.separator + "whitelist.yml"));
        if (!wlfile.exists()) {
            try {
                path.mkdirs();
                wlfile.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getLogger().log(Level.WARNING, "§cNie udalo sie utworzyc pliku §ewhitelist.yml");
            }
        }
        data = YamlConfiguration.loadConfiguration(wlfile);
    }

    public FileConfiguration getData() {
        return data;
    }

    public void saveData() throws IOException {
        try {
            data.save(wlfile);
        } catch (IIOException e) {
            Bukkit.getServer().getLogger().log(Level.WARNING, "§cNie udalo sie zapisac pliku §ewhitelist.yml");
        }
    }

    public void reloadData() {
        data = YamlConfiguration.loadConfiguration(wlfile);
    }

}
