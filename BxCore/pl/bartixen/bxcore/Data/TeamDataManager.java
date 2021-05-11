package pl.bartixen.bxcore.Data;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class TeamDataManager {

    static TeamDataManager instance;
    static TeamDataManager teamd;
    FileConfiguration data;
    public static File teamfile;

    static {
        instance = new TeamDataManager();
        teamd = TeamDataManager.getInstance();
    }

    public static TeamDataManager getInstance() {
        return instance;
    }

    public void setup(Plugin p) throws IOException {
        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdirs();
        }
        File path = new File(p.getDataFolder() + File.separator + "/data");
        teamfile = new File(path, String.valueOf(File.separator + "team.yml"));
        if (!teamfile.exists()) {
            try {
                path.mkdirs();
                teamfile.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getLogger().log(Level.WARNING, "§cNie udalo sie utworzyc pliku §eteam.yml");
            }
        }
        data = YamlConfiguration.loadConfiguration(teamfile);
    }

    public FileConfiguration getData() {
        return data;
    }

    public void saveData() throws IOException {
        try {
            data.save(teamfile);
        } catch (IIOException e) {
            Bukkit.getServer().getLogger().log(Level.WARNING, "§cNie udalo sie zapisac pliku §eteam.yml");
        }
    }

    public void reloadData() {
        data = YamlConfiguration.loadConfiguration(teamfile);
    }
}
