package pl.bartixen.bxcore.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import pl.bartixen.bxcore.Main;

public class Weather implements Listener {

    Main plugin;

    public Weather(Main m) {
        plugin = m;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void WeatherChangeEvent(WeatherChangeEvent e) {
        if (plugin.getConfig().getBoolean("weather", true)) {
            if (!e.toWeatherState())
                return;
            e.setCancelled(true);
            e.getWorld().setWeatherDuration(1);
            e.getWorld().setThundering(false);
        }
    }
}
