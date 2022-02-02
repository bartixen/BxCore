package pl.bartixen.bxcore.Tpa;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.bartixen.bxcore.Main;

import java.util.HashMap;
import java.util.Map;

public class TpSystem {
    static Main plugin;

    public TpSystem(Main m) {
        plugin = m;
    }
    public static Map<String, String> request = new HashMap<String, String>();

    public  static void removeRequest(String key) {
       if(request.containsKey(key)){
           Player p = Bukkit.getPlayer(request.get(key));
           if (p != null) {
               p.sendMessage("§7Twoja prośba o teleportacje wygasla");
           }

           request.remove(key);

       }
    }

   public static void sendRequest(Player p, Player p2) {
       p.sendMessage("§7Wysłano prośbe teleportacji do gracza §9" + p2.getName());
       p2.sendMessage("§7Gracz §9" + p.getName() + " §7wysłał prośbe o teleportacje do ciebie");
       p2.sendMessage("§7Wpisz §9/tpaccept §7aby zakceptować teleportacje");
       p2.sendMessage("§7Wpisz §9/tpdeny §7aby odrzucić teleportacje");

       request.put(p2.getName(), p.getName());
   }
}