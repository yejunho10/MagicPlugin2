package yejunho10.magicplugin.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yejunho10.magicplugin.func.Teleport;

public class Home implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(ChatColor.RED + "해당 명령어는 플레이어만 사용 가능합니다.");
            return false;
        } //콘솔등에서 입력시

        if (!Teleport.home.containsKey(p.getUniqueId())) {
            p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "등록된 집 정보가 없습니다.");
        } else {
            p.teleport(Teleport.home.get(p.getUniqueId()));
            p.sendMessage(ChatColor.GREEN + "성공적으로 텔레포트 되었습니다!");
        }

        return false;
    }
}
