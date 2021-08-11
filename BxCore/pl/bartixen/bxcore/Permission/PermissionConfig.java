package pl.bartixen.bxcore.Permission;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import javax.imageio.IIOException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class PermissionConfig {

    static PermissionConfig instance;
    Plugin p;
    FileConfiguration data;
    public static File permissionfile;

    static {
        instance = new PermissionConfig();
    }

    public static PermissionConfig getInstance() {
        return instance;
    }

    public void setup(Plugin p) throws IOException {
        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdirs();
        }
        File path = new File(p.getDataFolder() + File.separator);
        permissionfile = new File(path, String.valueOf(File.separator + "permission.yml"));
        if (!permissionfile.exists()) {
            try {
                path.mkdirs();
                permissionfile.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getLogger().log(Level.WARNING, "§cNie udalo sie utworzyc pliku §epermission.yml");
            }
        }
        data = YamlConfiguration.loadConfiguration(permissionfile);
    }

    public FileConfiguration getData() {
        return data;
    }

    public void saveData() throws IOException {
        try {
            data.save(permissionfile);
        } catch (IIOException e) {
            Bukkit.getServer().getLogger().log(Level.WARNING, "§cNie udalo sie zapisac pliku §epermission.yml");
        }
    }

    public void reloadData() {
        data = YamlConfiguration.loadConfiguration(permissionfile);
    }
}
