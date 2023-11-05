package pl.bartixen.bxcore.Data;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class AntyLogutDataManager {

    static AntyLogutDataManager instance;
    Plugin p;
    FileConfiguration data;
    public static File antylogutfile;

    static {
        instance = new AntyLogutDataManager();
    }

    public static AntyLogutDataManager getInstance() {
        return instance;
    }

    public void setup(Plugin p) throws IOException {
        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdirs();
        }
        File path = new File(p.getDataFolder() + File.separator + "/data");
        antylogutfile = new File(path, String.valueOf(File.separator + "antylogut.yml"));
        if (!antylogutfile.exists()) {
            try {
                path.mkdirs();
                antylogutfile.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getLogger().log(Level.WARNING, "Failed to create file antylogut.yml");
            }
        }
        data = YamlConfiguration.loadConfiguration(antylogutfile);
    }

    public FileConfiguration getData() {
        return data;
    }

    public void saveData() throws IOException {
        try {
            data.save(antylogutfile);
        } catch (IIOException e) {
            Bukkit.getServer().getLogger().log(Level.WARNING, "Failed to save the file antylogut.yml");
        }
    }

    public void reloadData() {
        data = YamlConfiguration.loadConfiguration(antylogutfile);
    }
}
