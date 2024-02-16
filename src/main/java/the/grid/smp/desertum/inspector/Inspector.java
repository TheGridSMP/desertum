package the.grid.smp.desertum.inspector;

import org.bukkit.*;
import the.grid.smp.desertum.Desertum;
import the.grid.smp.desertum.data.ChunkPos;
import the.grid.smp.desertum.data.Region;
import the.grid.smp.desertum.util.WorldUtil;

import java.util.Collection;

public class Inspector {

    private final Desertum desertum;

    public Inspector(Desertum desertum) {
        this.desertum = desertum;
        this.desertum.getServer().getScheduler().runTaskLater(desertum, this::start, 1L);
    }

    public void start() {
        World world = this.desertum.getWorldManager().getMainWorld();
        Collection<Region> regions = WorldUtil.getRegions(this.desertum, world);

        for (Region region : regions) {
            for (ChunkPos pos : region.getGeneratedChunks(world)) {
                this.inspectChunk(world, pos);
            }
        }
    }

    private void inspectChunk(World world, ChunkPos pos) {
        this.inspectChunk(world.getChunkAt(pos.x(), pos.z()));
    }

    private void inspectChunk(Chunk chunk) {
        this.inspectSnapshot(chunk.getChunkSnapshot(false, false, false));
    }

    private void inspectSnapshot(ChunkSnapshot snapshot) {
        int startX = snapshot.getX() * 16;
        int startZ = snapshot.getZ() * 16;

        int endX = startX+15;
        int endZ = startZ+15;

        for (int x = startX; x < endX; x++) {
            for (int y = 0; y < 320; y++) {
                for (int z = 0; z < endZ; z++) {
                    if (Inspector.playerMade(snapshot.getBlockType(x, y, z))) {
                        this.desertum.getLogger().severe("Chunk at " + snapshot.getX() + ":" + snapshot.getZ() + " has player stuff! Leaving untouched...");
                        break;
                    }
                }
            }
        }

        this.desertum.getLogger().severe("Chunk at " + snapshot.getX() + ":" + snapshot.getZ() + " doesnt have player stuff! Can be purged!");
    }

    private static boolean playerMade(Material material) {
        return Tag.BEDS.isTagged(material) || material == Material.CHEST || material == Material.ENDER_CHEST;
    }
}
