package the.grid.smp.desertum.world;

import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import the.grid.smp.desertum.Desertum;

public class WorldManager {

    private final Desertum desertum;

    private World deserted;
    private World main;

    public WorldManager(Desertum desertum) {
        this.desertum = desertum;
        this.reload();
    }

    public void reload() {
        String deserted = this.desertum.config().getDesertedWorld();

        this.main = this.desertum.getServer().getWorld(
                this.desertum.config().getMainWorld()
        );

        if (this.main == null) {
            this.desertum.getLogger().severe("Couldn't find main world!");
            return;
        }

        this.deserted = this.desertum.getServer().createWorld(
                new WorldCreator(deserted).copy(this.main)
                        .keepSpawnInMemory(false)
        );

        this.deserted.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        this.deserted.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        this.deserted.setGameRule(GameRule.DO_FIRE_TICK, false);
        this.deserted.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
    }

    public World getDesertedWorld() {
        return deserted;
    }

    public World getMainWorld() {
        return main;
    }
}
