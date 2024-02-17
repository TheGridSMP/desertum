package the.grid.smp.desertum.inspector;

import me.angeschossen.lands.api.LandsIntegration;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import the.grid.smp.desertum.Desertum;
import the.grid.smp.desertum.config.DesertumConfig;
import the.grid.smp.desertum.data.ChunkPos;
import the.grid.smp.desertum.db.ChunkDatabase;

public class Inspector {
    private final Desertum desertum;
    private final ChunkDatabase db;

    public Inspector(Desertum desertum) {
        this.desertum = desertum;
        this.db = new ChunkDatabase(desertum);

        DesertumConfig config = this.desertum.config();
        long delay = config.getDelay();

        this.desertum.getServer().getScheduler().runTaskTimerAsynchronously(this.desertum, () -> {
            for (ChunkPos pos : this.db.getChunks(config.getMaxInactivity())) {
                if (this.isProtected(pos)) {
                    this.db.remove(pos);
                    continue;
                }

                this.desertum.getLogger().warning("Chunk at " + pos + " can be purged!");
            }
        }, delay, delay);
    }

    public void reset() {
        this.db.close();
    }

    public void interact(Block block) {
        this.interact(block.getChunk());
    }

    public void interact(Location location) {
        this.interact(location.getChunk());
    }

    public void interact(Chunk chunk) {
        this.db.setChunk(new ChunkPos(chunk), chunk.getWorld().getFullTime());
    }

    private boolean isProtected(ChunkPos pos) {
        if (!this.desertum.getServer().getPluginManager().isPluginEnabled("Lands"))
            return false;

        World mainWorld = this.desertum.getWorldManager().getMainWorld();

        LandsIntegration lands = LandsIntegration.of(this.desertum);
        return lands.getLandByUnloadedChunk(mainWorld, pos.x(), pos.z()) == null;
    }
}
