package net.poweredbyhate.fisheditor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Lax on 6/5/2017.
 */
public class FishEditorCmd implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player && sender.hasPermission("fishedit.admin")) {
            ((Player) sender).openInventory(FishEditor.instance.fishBucket);
        }
        return false;
    }
}
