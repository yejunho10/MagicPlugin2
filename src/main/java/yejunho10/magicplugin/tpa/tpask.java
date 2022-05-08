package yejunho10.magicplugin.tpa;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

@SuppressWarnings("all")
public class tpask implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
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


-> 15초후에 실행한다. <-
-> tpa 신청 내역 삭제에 이용된다 <-
*/