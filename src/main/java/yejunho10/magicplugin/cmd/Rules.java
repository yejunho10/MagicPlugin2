package yejunho10.magicplugin.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Rules implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "해당 명령어는 플레이어만 사용 가능합니다.");
            return false;
        } //콘솔등에서 입력시

        p.sendMessage(ChatColor.LIGHT_PURPLE + "규칙은 제작중입니다!");
        return false;
    }
}
