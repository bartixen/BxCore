package pl.bartixen.bxcore.Data;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class HomeDataManager {

    static HomeDataManager instance;
    Plugin p;
    FileConfiguration data;
    public static File homefile;

    static {
        instance = new HomeDataManager();
    }

    public static HomeDataManager getInstance() {
        return instance;
    }

    public void setup(Plugin p) throws IOException {
        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdirs();
        }
        File path = new File(p.getDataFolder() + File.separator + "/data");
        homefile = new File(path, String.valueOf(File.separator + "home.yml"));
        if (!homefile.exists()) {
            try {
                path.mkdirs();
                homefile.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getLogger().log(Level.WARNING, "Failed to create file home.yml");
            }
        }
        data = YamlConfiguration.loadConfiguration(homefile);
    }

    public FileConfiguration getData() {
        return data;
    }

    public void saveData() throws IOException {
        try {
            data.save(homefile);
        } catch (IIOException e) {
            Bukkit.getServer().getLogger().log(Level.WARNING, "Failed to save the file home.yml");
        }
    }

    public void reloadData() {
        data = YamlConfiguration.loadConfiguration(homefile);
    }

}
