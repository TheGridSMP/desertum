package the.grid.smp.desertum.command;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import the.grid.smp.desertum.Desertum;
import the.grid.smp.desertum.data.ChunkPos;
import the.grid.smp.desertum.util.WorldUtil;

import java.util.List;

public class DesertumCommand implements TabExecutor {

    private final Desertum desertum;

    public DesertumCommand(Desertum desertum) {
        this.desertum = desertum;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("desertum.command"))
            return false;

        switch (args[0]) {
            case "purge", "reset" -> {
                if (!sender.hasPermission("desertum.command.purge"))
                    return false;

                int x = Integer.parseInt(args[1]);
                int z = Integer.parseInt(args[2]);

                World main = this.desertum.getWorldManager().getMainWorld();
                World deserted = this.desertum.getWorldManager().getDesertedWorld();

                WorldUtil.copy(this.desertum, deserted.getChunkAt(x, z), main.getChunkAt(x, z));
            }

            case "inspect" -> {
                if (!sender.hasPermission("desertum.command.inspect"))
                    return false;

                if (args.length == 3) {
                    int x = Integer.parseInt(args[1]);
                    int z = Integer.parseInt(args[2]);

                    this.desertum.getInspector().inspect(new ChunkPos(x, z));
                    return true;
                }

                this.desertum.getInspector().inspect();
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return List.of();
    }
}
