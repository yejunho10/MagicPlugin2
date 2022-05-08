package yejunho10.magicplugin.cmd;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;
import yejunho10.magicplugin.event.OpenItemInventoryEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("all")
public class ItemCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            OpenItemInventoryEvent event = new OpenItemInventoryEvent((Player)sender);
            Bukkit.getPluginManager().callEvent(event);
            return true;
        }
        sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "명령어는 플레이어만 사용 가능합니다.");
        return false;
    }
}
