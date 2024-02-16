package the.grid.smp.desertum;

import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;
import the.grid.smp.desertum.config.DesertumConfig;
import the.grid.smp.desertum.world.WorldManager;

public final class Desertum extends JavaPlugin {

    private DesertumConfig config;
    private WorldManager worldManager;

    @Override
    public void onEnable() {
        this.config = new DesertumConfig(this);
        this.worldManager = new WorldManager(this);

        // Plugin startup logic
        this.getServer().getWorld("");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public DesertumConfig config() {
        return this.config;
    }

    public void reload() {
        this.config.reload();
        this.worldManager.reload();
    }
}
