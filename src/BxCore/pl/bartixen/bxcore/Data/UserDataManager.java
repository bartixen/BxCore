package pl.bartixen.bxcore.Data;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class UserDataManager {

    static UserDataManager instance;
    Plugin p;
    FileConfiguration data;
    public static File userfile;

    static {
        instance = new UserDataManager();
    }

    public static UserDataManager getInstance() {
        return instance;
    }

    public void setup(Plugin p) throws IOException {
        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdirs();
        }
        File path = new File(p.getDataFolder() + File.separator + "/data");
        userfile = new File(path, String.valueOf(File.separator + "user.yml"));
        if (!userfile.exists()) {
            try {
                path.mkdirs();
                userfile.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getLogger().log(Level.WARNING, "§cNie udalo sie utworzyc pliku §euser.yml");
            }
        }
        data = YamlConfiguration.loadConfiguration(userfile);
    }

    public FileConfiguration getData() {
        return data;
    }

    public void saveData() throws IOException {
        try {
            data.save(userfile);
        } catch (IIOException e) {
            Bukkit.getServer().getLogger().log(Level.WARNING, "§cNie udalo sie zapisac pliku §euser.yml");
        }
    }

    public void reloadData() {
        data = YamlConfiguration.loadConfiguration(userfile);
    }

}
