package the.grid.smp.desertum.config;

import org.bukkit.configuration.ConfigurationSection;
import the.grid.smp.communis.config.Config;
import the.grid.smp.desertum.Desertum;

public class DesertumConfig extends Config {

    private static final long MINS_TO_TICKS = 60 * 24L;

    private String desertedWorld;
    private String mainWorld;

    private long delay;
    private long maxInactivity;

    public DesertumConfig(Desertum desertum) {
        super(desertum, "config");
    }

    @Override
    public void read(ConfigurationSection section) {
        this.desertedWorld = section.getString("deserted-world");
        this.mainWorld = section.getString("main-world");

        this.delay = section.getInt("delay") * MINS_TO_TICKS;
        this.maxInactivity = section.getInt("max-inactivity") * 24 * 60 * MINS_TO_TICKS;
    }

    @Override
    public void write(ConfigurationSection section) { }

    public String getDesertedWorld() {
        return desertedWorld;
    }

    public String getMainWorld() {
        return mainWorld;
    }

    public long getDelay() {
        return delay;
    }

    public long getMaxInactivity() {
        return maxInactivity;
    }
}
