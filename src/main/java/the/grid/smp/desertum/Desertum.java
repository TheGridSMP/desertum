package the.grid.smp.desertum;

import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import the.grid.smp.desertum.command.DesertumCommand;
import the.grid.smp.desertum.config.DesertumConfig;
import the.grid.smp.desertum.inspector.Inspector;
import the.grid.smp.desertum.listener.WorldListener;
import the.grid.smp.desertum.world.WorldManager;

public final class Desertum extends JavaPlugin {

    private DesertumConfig config;
    private WorldManager worldManager;
    private Inspector inspector;

    @Override
    public void onEnable() {
        this.config = new DesertumConfig(this);
        this.worldManager = new WorldManager(this);
        this.inspector = new Inspector(this);

        this.getServer().getPluginManager().registerEvents(new WorldListener(this), this);
        this.command("desertum", new DesertumCommand(this));
    }

    @Override
    public void onDisable() {
        this.inspector.reset();
    }

    private void command(String name, TabExecutor executor) {
        PluginCommand command = this.getServer().getPluginCommand(name);

        command.setExecutor(executor);
        command.setTabCompleter(executor);
    }

    public DesertumConfig config() {
        return this.config;
    }

    public WorldManager getWorldManager() {
        return this.worldManager;
    }

    public Inspector getInspector() {
        return inspector;
    }

    public void reload() {
        this.config.reload();
        this.worldManager.reload();
    }
}
