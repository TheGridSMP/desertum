package the.grid.smp.desertum.config;

import org.bukkit.configuration.ConfigurationSection;
import the.grid.smp.communis.config.Config;
import the.grid.smp.desertum.Desertum;

public class DesertumConfig extends Config {

    private String desertedWorld;
    private String mainWorld;

    public DesertumConfig(Desertum desertum) {
        super(desertum, "config");
    }

    @Override
    public void read(ConfigurationSection section) {
        this.desertedWorld = section.getString("deserted-world");
        this.mainWorld = section.getString("main-world");
    }

    @Override
    public void write(ConfigurationSection section) { }

    public String getDesertedWorld() {
        return desertedWorld;
    }

    public String getMainWorld() {
        return mainWorld;
    }
}
