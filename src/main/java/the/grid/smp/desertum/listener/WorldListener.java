package the.grid.smp.desertum.listener;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import the.grid.smp.desertum.Desertum;

public class WorldListener implements Listener {

    private final Desertum desertum;

    public WorldListener(Desertum desertum) {
        this.desertum = desertum;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        World world = event.getBlock().getLocation().getWorld();
        World mainWorld = this.desertum.getWorldManager().getMainWorld();

        if (world == null || !world.getName().equals(mainWorld.getName()))
            return;

        this.desertum.getInspector().interact(event.getBlock());
    }

    @EventHandler
    public void onBlockInteract(PlayerInteractEvent event) {
        /*if (event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;

        Block block = event.getClickedBlock();

        if (block == null)
            return;

        Location location = block.getLocation();

        World world = location.getWorld();
        World mainWorld = this.desertum.getWorldManager().getMainWorld();

        if (world == null || !world.getName().equals(mainWorld.getName()))
            return;

        this.desertum.getInspector().interact(location);*/
    }
}
