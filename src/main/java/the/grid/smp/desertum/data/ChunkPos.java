package the.grid.smp.desertum.data;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

public record ChunkPos(int x, int z) {

    public ChunkPos(Chunk chunk) {
        this(chunk.getX(), chunk.getZ());
    }

    public Location asLocation(World world) {
        return new Location(world, this.x * 16, 16, this.z * 16);
    }
}
