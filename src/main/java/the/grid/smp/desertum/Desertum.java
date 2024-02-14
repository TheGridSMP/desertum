package the.grid.smp.desertum;

import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

public final class Desertum extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getServer().getWorld("");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
