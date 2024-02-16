package the.grid.smp.desertum.util;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import the.grid.smp.desertum.data.Region;

import java.io.File;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WorldUtil {

    public static Collection<Region> getRegions(Plugin plugin, World world) {
        return WorldUtil.getRegions(plugin.getServer(), world);
    }

    public static Collection<Region> getRegions(Server server, World world) {
        return WorldUtil.getRegions(new File(server.getWorldContainer(), world.getName()));
    }

    public static Collection<Region> getRegions(File world) {
        List<Region> list = new ArrayList<>();
        File regions = new File(world, "region");

        for (File region : regions.listFiles()) {
            list.add(WorldUtil.getRegion(region));
        }

        return list;
    }

    private static Region getRegion(File region) {
        return WorldUtil.getRegion(region.getName());
    }

    private static Region getRegion(String name) {
        // r.x.z.mca
        String[] parts = name.split("\\.");

        int x = Integer.parseInt(parts[1]);
        int z = Integer.parseInt(parts[2]);
        return new Region(x, z);
    }
}
