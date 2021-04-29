package pl.bartixen.bxcore.Data;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class MsgDataManager {

    static MsgDataManager instance;
    Plugin p;
    FileConfiguration data;
    public static File msgfile;

    static {
        instance = new MsgDataManager();
    }

    public static MsgDataManager getInstance() {
        return instance;
    }

    public void setup(Plugin p) throws IOException {
        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdirs();
        }
        File path = new File(p.getDataFolder() + File.separator + "/data");
        msgfile = new File(path, String.valueOf(File.separator + "msg.yml"));
        if (!msgfile.exists()) {
            try {
                path.mkdirs();
                msgfile.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getLogger().log(Level.WARNING, "§cNie udalo sie utworzyc pliku §ehome.yml");
            }
        }
        data = YamlConfiguration.loadConfiguration(msgfile);
    }

    public FileConfiguration getData() {
        return data;
    }

    public void saveData() throws IOException {
        try {
            data.save(msgfile);
        } catch (IIOException e) {
            Bukkit.getServer().getLogger().log(Level.WARNING, "§cNie udalo sie zapisac pliku §ehome.yml");
        }
    }

    public void reloadData() {
        data = YamlConfiguration.loadConfiguration(msgfile);
    }

}
