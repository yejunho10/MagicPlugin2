package yejunho10.magicplugin.tpa;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import yejunho10.magicplugin.GUIPlugin;
import yejunho10.magicplugin.etc.tpaData;

import static yejunho10.magicplugin.GUIPlugin.tpaMap;

@SuppressWarnings("all")
public class TPAsk implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "명령어는 플레이어만 사용 가능합니다.");
            return false;
        }
        Player p = (Player) sender;

        if (args.length > 1) {
            sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "아규먼트가 많습니다.");
            return false;
        }
        else if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "아규먼트가 부족합니다.");
            return false;
        }

        if (args.length == 0) {
            p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "누구에게 텔레포트를 요청할지 입력해주세요.");
            return false;
        }
        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "초대할 플레이어가 존재하지 않습니다.");
            return false;
        }

        if (tpaMap.containsKey(target)) {
            p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "이미 다른 이에게 초대 받은 플레이어입니다.");
            return false;
        }

        if (p.getName() == target.getName()) {
            p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "자기 자신을 초대할수 없습니다.");
            return false;
        }

        tpaData tpadata = new tpaData(p, target, target.getLocation());

        tpaMap.put(target, tpadata);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (tpaMap.containsKey(target)) {
                    tpaMap.remove(target);
                    p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "텔레포트 신청 시간이 초과되었습니다.");
                }
            }
        }.runTaskLaterAsynchronously(GUIPlugin.getInstance(), 20 * 15);

        p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + target.getName() + "님에게 텔레포트를 요청했습니다.");
        p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + p.getName() + "님이 이곳으로 텔레포트를 요청했습니다.");
        target.sendMessage("요청을 수락하려면 /tpaccept를, 요청을 거절하려면 /tpadeny를 사용해주세요.");
        target.sendMessage("15초 뒤에 신청이 초기화됩니다.");

        //임시다!
        tpaData a = tpaMap.get(target);
        p.sendMessage("요청자 : " + a.getSender() +
                "\n타겟 : " + a.getTarget().getName() +
                "\n위치 : " + a.getLocation().getWorld().getName() + ", " + a.getLocation().getX() + ", " + a.getLocation().getY() + ", " + a.getLocation().getZ());
        target.sendMessage("요청자 : " + a.getSender() +
                "\n타겟 : " + a.getTarget().getName() +
                "\n위치 : " + a.getLocation().getWorld().getName() + ", " + a.getLocation().getX() + ", " + a.getLocation().getY() + ", " + a.getLocation().getZ());
        return false;
    }
}

/*
new BukkitRunnable() {
    @Override
        public void run() {
            코드 내용!
        }
}.runTaskLaterAsynchronously(GUIPlugin.getInstance(), 20 * 15);
= 15초후에 실행한다.

-> Asynchronously = 비동기 였나 <-
*/