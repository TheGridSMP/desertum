package the.grid.smp.desertum.command;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import the.grid.smp.desertum.Desertum;
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
            case "purge" -> {
                if (!sender.hasPermission("desertum.command.purge"))
                    return false;

                int x = Integer.parseInt(args[1]);
                int z = Integer.parseInt(args[2]);

                World main = this.desertum.getWorldManager().getMainWorld();
                World deserted = this.desertum.getWorldManager().getDesertedWorld();

                WorldUtil.copy(deserted.getChunkAt(x, z), main.getChunkAt(x, z));
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return List.of();
    }
}
