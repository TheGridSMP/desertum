package the.grid.smp.desertum.world;

import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.generator.ChunkGenerator;
import the.grid.smp.desertum.Desertum;

public class WorldManager {

    private final Desertum desertum;

    private World world;

    public WorldManager(Desertum desertum) {
        this.desertum = desertum;
        this.reload();
    }

    public void reload() {
        String deserted = this.desertum.config().getDesertedWorld();

        World main = this.desertum.getServer().getWorld(
                this.desertum.config().getMainWorld()
        );

        if (main == null) {
            this.desertum.getLogger().severe("Couldn't find main world!");
            return;
        }

        this.world = this.desertum.getServer().createWorld(
                new WorldCreator(deserted).copy(main)
                        .keepSpawnInMemory(false)
        );

        this.world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        this.world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
        this.world.setGameRule(GameRule.DO_FIRE_TICK, false);
        this.world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
    }

    public World getDesertedWorld() {
        return world;
    }
}
