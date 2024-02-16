package the.grid.smp.desertum;

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
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public DesertumConfig config() {
        return this.config;
    }

    public WorldManager getWorldManager() {
        return this.worldManager;
    }

    public void reload() {
        this.config.reload();
        this.worldManager.reload();
    }
}
