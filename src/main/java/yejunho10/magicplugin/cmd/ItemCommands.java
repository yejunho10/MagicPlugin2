package yejunho10.magicplugin.cmd;

import yejunho10.magicplugin.event.OpenItemInventoryEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ItemCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            OpenItemInventoryEvent event = new OpenItemInventoryEvent((Player)sender);
            Bukkit.getPluginManager().callEvent(event);
            return true;
        }
        return false;
    }
}
