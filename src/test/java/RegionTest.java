import the.grid.smp.desertum.data.ChunkPos;
import the.grid.smp.desertum.data.Region;

import java.util.List;

public class RegionTest {

    public static void main(String[] args) {
        List<ChunkPos> pos = (List<ChunkPos>) new Region(3, -1).getChunks();

        assert pos.size() == 32;
        assert pos.get(0).equals(new ChunkPos(96, -32));
        assert pos.get(pos.size() - 1).equals(new ChunkPos(127, -1));
    }
}
