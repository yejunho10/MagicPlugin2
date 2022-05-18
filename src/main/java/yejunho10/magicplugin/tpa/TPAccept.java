package yejunho10.magicplugin.tpa;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static yejunho10.magicplugin.GUIPlugin.tpaMap;

@SuppressWarnings("all")
public class TPAccept implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "명령어는 플레이어만 사용 가능합니다.");
            return false;
        }
        Player p = (Player) sender;

        if (args.length > 0) {
            p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "아규먼트가 많습니다.");
            return false;
        }

        if (!tpaMap.containsKey(p)){
            p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "초대를 받은 기록이 없습니다.");
        }

        Player sendPlayer = tpaMap.get(p).getSender();
        Player recievePlayer = tpaMap.get(p).getTarget();

        p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + "요청을 수락하였습니다.");
        sendPlayer.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + recievePlayer.getName() + "님이 요청을 수락하였습니다.");

        Location loc = tpaMap.get(p).getLocation();

        sendPlayer.teleport(loc);
        sendPlayer.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + "성공적으로 텔레포트 되었습니다!");

        tpaMap.remove(p);
        return false;
    }
}