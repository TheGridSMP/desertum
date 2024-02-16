package the.grid.smp.desertum.data;

import org.bukkit.World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Region {

    private final int x;
    private final int z;

    public Region(int x, int z) {
        this.x = x;
        this.z = z;
    }

    public Collection<ChunkPos> getGeneratedChunks(World world) {
        return this.getChunks().parallelStream().filter(
                chunkPos -> world.isChunkGenerated(chunkPos.x(), chunkPos.z())
        ).toList();
    }

    public Collection<ChunkPos> getChunks() {
        int startX = this.x * 32;
        int startZ = this.z * 32;

        int endX = startX + 31;
        int endZ = startZ + 31;

        return this.getChunks(startX, startZ, endX, endZ);
    }

    protected List<ChunkPos> getChunks(int startX, int startZ, int endX, int endZ) {
        List<ChunkPos> positions = new ArrayList<>();

        for (int x = startX; x < endX + 1; x++) {
            for (int z = startZ; z < endZ + 1; z++) {
                positions.add(new ChunkPos(x, z));
            }
        }

        return positions;
    }
}
