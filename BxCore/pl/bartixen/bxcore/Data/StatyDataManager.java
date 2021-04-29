package pl.bartixen.bxcore.Data;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class StatyDataManager {

    static StatyDataManager instance;
    Plugin p;
    FileConfiguration data;
    public static File statyfile;

    static {
        instance = new StatyDataManager();
    }

    public static StatyDataManager getInstance() {
        return instance;
    }

    public void setup(Plugin p) throws IOException {
        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdirs();
        }
        File path = new File(p.getDataFolder() + File.separator + "/data");
        statyfile = new File(path, String.valueOf(File.separator + "staty.yml"));
        if (!statyfile.exists()) {
            try {
                path.mkdirs();
                statyfile.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getLogger().log(Level.WARNING, "§cNie udalo sie utworzyc pliku §estaty.yml");
            }
        }
        data = YamlConfiguration.loadConfiguration(statyfile);
    }

    public FileConfiguration getData() {
        return data;
    }

    public void saveData() throws IOException {
        try {
            data.save(statyfile);
        } catch (IIOException e) {
            Bukkit.getServer().getLogger().log(Level.WARNING, "§cNie udalo sie zapisac pliku §estaty.yml");
        }
    }

    public void reloadData() {
        data = YamlConfiguration.loadConfiguration(statyfile);
    }

}
