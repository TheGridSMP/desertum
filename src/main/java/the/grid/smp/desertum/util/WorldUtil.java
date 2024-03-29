package the.grid.smp.desertum.util;

import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;

public class WorldUtil {

    private static final int MIN_HEIGHT = -64;
    private static final int MAX_HEIGHT = 320;

    private static final int STEP = 2;

    public static void copy(Plugin plugin, Chunk from, Chunk to) {
        step(plugin, from, to, MIN_HEIGHT / STEP, System.currentTimeMillis());
    }

    private static void step(Plugin plugin, Chunk from, Chunk to, int step, long time) {
        plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
            int startY = step * STEP;
            int endY = startY + STEP;

            if (startY == MAX_HEIGHT) {
                plugin.getLogger().info("Purged chunk at: " + from.getX() + ":" + from.getZ() + " in: " + (System.currentTimeMillis() - time));
                return;
            }

            copy(from, to, startY, endY);
            step(plugin, from, to, step + 1, time);
        }, 1);
    }

    public static void copy(Chunk from, Chunk to) {
        copy(from, to, MIN_HEIGHT, MAX_HEIGHT);
    }

    public static void copy(Chunk from, Chunk to, int startY, int endY) {
        for (int x = 0; x < 16; x++) {
            for (int y = startY; y < endY; y++) {
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
