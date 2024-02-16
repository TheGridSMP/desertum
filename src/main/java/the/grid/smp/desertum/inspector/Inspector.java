package the.grid.smp.desertum.inspector;

import org.bukkit.World;
import the.grid.smp.desertum.Desertum;
import the.grid.smp.desertum.data.Region;
import the.grid.smp.desertum.util.WorldUtil;

import java.util.Collection;

public class Inspector {

    private final Desertum desertum;

    public Inspector(Desertum desertum) {
        this.desertum = desertum;
        this.start();
    }

    public void start() {
        World world = this.desertum.getWorldManager().getMainWorld();
        Collection<Region> regions = WorldUtil.getRegions(this.desertum, world);

        for (Region region : regions) {
            region.getGeneratedChunks(world);
        }
    }
}
