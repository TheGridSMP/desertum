package the.grid.smp.desertum.util;

import org.bukkit.Chunk;
import org.bukkit.block.Block;

public class WorldUtil {

    public static void copy(Chunk from, Chunk to) {
        for (int x = 0; x < 16; x++) {
            for (int y = -64; y < 320; y++) {
                for (int z = 0; z < 16; z++) {
                    Block replacement = from.getBlock(x, y, z);
                    Block replacing = to.getBlock(x, y, z);

                    replacing.setType(replacement.getType());
                    replacing.setBlockData(replacement.getBlockData());
                }
            }
        }
    }
}
