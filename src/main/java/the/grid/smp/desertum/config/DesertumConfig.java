package the.grid.smp.desertum.config;

import org.bukkit.configuration.ConfigurationSection;
import the.grid.smp.communis.config.Config;
import the.grid.smp.desertum.Desertum;

public class DesertumConfig extends Config {

    private String desertedWorld;

    private final Desertum desertum;

    public DesertumConfig(Desertum desertum) {
        super(desertum, "config");
        this.desertum = desertum;
    }

    @Override
    public void read(ConfigurationSection section) {
        this.desertedWorld = section.getString("deserted-world");
    }

    @Override
    public void write(ConfigurationSection section) { }
}
