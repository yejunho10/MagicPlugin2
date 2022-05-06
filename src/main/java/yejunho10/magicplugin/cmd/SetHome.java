package yejunho10.magicplugin.cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import yejunho10.magicplugin.func.Teleport;

@SuppressWarnings("all")
public class SetHome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage(ChatColor.RED + "해당 명령어는 플레이어만 사용 가능합니다.");
            return false;
        } //콘솔등에서 입력시

        if (p.getWorld().equals(Bukkit.getWorld("world")) || p.getWorld().equals(Bukkit.getWorld("co_01")) || p.getWorld().equals(Bukkit.getWorld("casino")) || p.getWorld().equals(Bukkit.getWorld("minigame1"))) {
            p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "해당월드에서는 이 명령어 사용이 불가합니다.");
        } else {
            Teleport.home.put(p.getUniqueId(), p.getLocation());
            p.sendMessage(ChatColor.GREEN + "집 정보가 성공적으로 등록 되었습니다!");
        }

        return false;
    }
}
