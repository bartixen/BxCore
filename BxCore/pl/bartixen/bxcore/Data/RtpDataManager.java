package pl.bartixen.bxcore.Data;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class RtpDataManager {

    static RtpDataManager instance;
    Plugin p;
    FileConfiguration data;
    public static File rtpfile;

    static {
        instance = new RtpDataManager();
    }

    public static RtpDataManager getInstance() {
        return instance;
    }

    public void setup(Plugin p) throws IOException {
        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdirs();
        }
        File path = new File(p.getDataFolder() + File.separator + "/data");
        rtpfile = new File(path, String.valueOf(File.separator + "rtp.yml"));
        if (!rtpfile.exists()) {
            try {
                path.mkdirs();
                rtpfile.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getLogger().log(Level.WARNING, "§cNie udalo sie utworzyc pliku §ertp.yml");
            }
        }
        data = YamlConfiguration.loadConfiguration(rtpfile);
    }

    public FileConfiguration getData() {
        return data;
    }

    public void saveData() throws IOException {
        try {
            data.save(rtpfile);
        } catch (IIOException e) {
            Bukkit.getServer().getLogger().log(Level.WARNING, "§cNie udalo sie zapisac pliku §ertp.yml");
        }
    }

    public void reloadData() {
        data = YamlConfiguration.loadConfiguration(rtpfile);
    }
}
