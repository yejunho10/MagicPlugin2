package yejunho10.magicplugin.cmd;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import yejunho10.magicplugin.GUIPlugin;
import yejunho10.magicplugin.func.Teleport;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;

import static yejunho10.magicplugin.func.Functions.*;
import static yejunho10.magicplugin.func.Teleport.*;
import static yejunho10.magicplugin.event.Event.terror;

@SuppressWarnings("all")
public class MPCommand implements CommandExecutor, TabCompleter {
    /*
    surv1, 144, 63, 118
    surv2, 128, 71, 16
    surv3, -224, 105, -160
    world, 0, 70, 0
    casino, 3, -49, 26
    minigame1, 403, 116, -320
    co_01, 46, 52, -19
    minigame1, 0, -60, 0
    */

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "사용법이 잘못되었습니다. /mp help를 참고하세요.");
        } //mp
        else if (args[0].equalsIgnoreCase("about")) {
            if (sender.hasPermission("myplugin.about")) {
                if (args.length > 1) {
                    sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "아규먼트가 많습니다.");
                    return false;
                }

                sender.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + "이 기능은 GUI메뉴의 커맨드 기능입니다.");
                sender.sendMessage(ChatColor.YELLOW + "[문의] - " + ChatColor.WHITE + "디스코드 | 호예준#3840");
                return true;
            }
            no_permissions((Player) sender, "myplugin.about");
            return false;
        } //mp about
        else if (args[0].equalsIgnoreCase("tp")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "명령어는 플레이어만 사용 가능합니다.");
                return false;
            } //콘솔등에서 입력시
            Player p = (Player) sender;

            if (p.hasPermission("myplugin.teleport")) {
                if (args.length < 2) {
                    sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "아큐먼트가 부족합니다.");
                    return false;
                }

                if (args[1].equalsIgnoreCase("zb")) {
                    if (args.length < 3) {
                        sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "반성의 방으로 텔레포트할 플레이어를 입력해주세요.");
                        return false;
                    }

                    if (p.isOp()) {
                        Location Dest_zb = new Location(loc.get("zb").getWorld(), loc.get("zb").getX(), loc.get("zb").getY(), loc.get("zb").getZ()); //ZB
                        Player target = Bukkit.getPlayer(args[2]);

                        if (target == null) {
                            sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "입력한 플레이어는 온라인 상태가 아닙니다.");
                            return false;
                        }

                        target.teleport(Dest_zb);
                        sender.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + "반성의 방으로 성공적으로 텔레포트 하였습니다!");
                        target.setOp(false);
                        return true;
                    }
                    no_ops(p);
                    return false;
                }

                if (args.length > 2) {
                    sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "아규먼트가 많습니다.");
                }
                switch (args[1]) {
                    case "survival1" -> Teleport.teleportSurvival1(p);
                    case "survival2" -> Teleport.teleportSurvival2(p);
                    case "survival3" -> Teleport.teleportSurvival3(p);
                    case "lobby" -> Teleport.teleportLobby(p);
                    case "spawn" -> Teleport.teleportSpawn(p);
                    case "casino" -> Teleport.teleportCasino(p);
                    case "minigame1" -> Teleport.teleportMinigame1(p);
                    case "zw" -> {
                        if (p.isOp()) {
                            Location Dest_zw = new Location(loc.get("zw").getWorld(), loc.get("zw").getX(), loc.get("zw").getY(), loc.get("zw").getZ()); //ZW
                            p.teleport(Dest_zw);
                            sender.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + "성공적으로 텔레포트 되었습니다!");
                            return true;
                        }
                        no_ops(p);
                        return false;
                    }
                    default -> {
                        sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "존재하지 않는 텔레포트입니다.");
                        return false;
                    }
                }
                return true;
            }
            no_permissions(p, "myplugin.teleport");
            return false;
        } //mp tp
        else if (args[0].equalsIgnoreCase("lobby")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "해당 명령어는 플레이어만 사용 가능합니다.");
                return false;
            } //콘솔등에서 입력시
            Teleport.teleportLobby((Player) sender);
            return true;
        } //mp lobby
        else if (args[0].equalsIgnoreCase("reload")) {
            if (sender.isOp()) {
                if (args.length > 2) {
                    sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "아규먼트가 많습니다.");
                    return false;
                }
                else if (args.length < 2) {
                    sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "아큐먼트가 부족합니다.");
                    return false;
                }

                if (args[1].equalsIgnoreCase("server")) {
                    Bukkit.broadcastMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + "서버가 재로드됩니다! 안전한곳에서 움직이지 말아주세요.");
                    Bukkit.reload();
                    return true;
                }
                else if (args[1].equalsIgnoreCase("config")) {
                    sender.sendMessage(ChatColor.GREEN + "[설정 재로드] - 저장 시작");
                    saveMaps();
                    sender.sendMessage(ChatColor.GREEN + "[설정 재로드] - 저장 완료");
                    sender.sendMessage(ChatColor.GREEN + "[설정 재로드] - 불러오기 시작");
                    restoreMaps();
                    sender.sendMessage(ChatColor.GREEN + "[설정 재로드] - 불러오기 완료");
                    return true;
                }

                sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "아규먼트가 잘못되었습니다.");
                return false;
            }
            no_ops((Player) sender);
            return false;
        } //mp reload <server|config>
        else if (args[0].equalsIgnoreCase("reset")) {
            if (sender.isOp()) {
                if (args.length > 1) {
                    sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "아규먼트가 많습니다.");
                    return false;
                }

                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!player.isOp()) {
                        switch (player.getWorld().getName()) {
                            case "world", "casino", "co_01", "minigame1" -> resetMessage(player, GameMode.ADVENTURE);
                            case "surv1", "surv2", "surv3", "surv1_nehter", "surv2_nehter", "surv3_nehter", "world_the_end" -> resetMessage(player, GameMode.SURVIVAL);
                            default -> player.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.GREEN + player.getName() + ChatColor.WHITE + "님은 현재, " + ChatColor.GREEN + player.getWorld().getName() + ChatColor.WHITE + "월드에 있으며, 해당월드는 인식되지 않았으므로 게임모드를 변경하지 못했음을 알려드립니다.");
                        }
                    } else {
                        player.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.GREEN + player.getName() + ChatColor.WHITE + "님은 현재, " + ChatColor.GREEN + player.getWorld().getName() + ChatColor.WHITE + "월드에 있으며, OP권한이 있어 게임모드가 변경되지 않았음을 알려드립니다.");
                    }
                }
                return true;
            }
            no_ops((Player) sender);
            return false;
        } //mp reset
        else if (args[0].equalsIgnoreCase("stop")) {
            if (sender.isOp()) {
                if (args.length < 1) {
                    sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "아규먼트가 많습니다.");
                    return false;
                }

                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.kickPlayer("서버가 종료됩니다.");
                }
                Bukkit.shutdown();
                return true;
            }
            no_ops((Player) sender);
            return false;
        } //mp stop
        else if (args[0].equalsIgnoreCase("terror")) {
                if (sender.isOp()) {
                    if (args.length < 2) {
                        sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "아규먼트가 많습니다.");
                        return false;
                    }
                    else if (args.length > 2) {
                        sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "아큐먼트가 부족합니다.");
                        return false;
                    }

                    if (args[1].equalsIgnoreCase("on")) {
                        if (terror) {
                            sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "테러 대응모드가 이미 켜져있습니다.");
                            return false;
                        }
                        terror = true;
                        sender.sendMessage(ChatColor.GREEN + "테러 대응모드를 켰습니다.");
                        Bukkit.broadcastMessage(ChatColor.RED + "### 테러 대응모드가 켜졌습니다. ###");
                        return true;
                    }
                    else if (args[1].equalsIgnoreCase("off")) {
                        if (!terror) {
                            sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "테러 대응모드가 이미 꺼져있습니다.");
                            return false;
                        }
                        terror = false;
                        sender.sendMessage(ChatColor.GREEN + "테러 대응모드를 껐습니다.");
                        Bukkit.broadcastMessage(ChatColor.RED + "### 테러 대응모드가 꺼졌습니다. ###");
                        return true;
                    }

                    sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "아규먼트가 잘못되었습니다.");
                    return false;
                }
                no_ops((Player) sender);
                return false;
        } //mp terror <on|off>
        else if (args[0].equalsIgnoreCase("help")) {
            if (args.length > 1) {
                    sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "아규먼트가 많습니다.");
                    return false;
                }

            sender.sendMessage(ChatColor.YELLOW + "========== " + ChatColor.LIGHT_PURPLE + "/mp 명령어 도움말" + ChatColor.YELLOW + " ==========");
            sender.sendMessage(ChatColor.RED + "/mp about : 플러그인의 정보를 출력합니다.");
            sender.sendMessage(ChatColor.RED + "/mp tp <위치> : 지정된 위치로 텔레포트합니다.");
            sender.sendMessage(ChatColor.RED + "/mp lobby : 로비로 텔레포트합니다.");
        } //mp help
        return false;
    }

    @Nullable
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            return sender.isOp() ? Arrays.asList("about", "tp", "lobby", "reload", "reset", "stop", "terror", "help") : Arrays.asList("about", "tp", "lobby", "help");
        }
        if (args[0].equalsIgnoreCase("tp")) {
            return sender.isOp() ? Arrays.asList("spawn", "lobby", "casino", "minigame1", "survival1", "survival2", "survival3", "zw", "zb") : Arrays.asList("spawn", "lobby", "casino", "minigame1", "survival1", "survival2", "survival3");
        }
        else if (args[0].equalsIgnoreCase("terror")) {
            return terror ? List.of("off") : List.of("on");
        }
        else if (args[0].equalsIgnoreCase("reload")) {
            return sender.isOp() ? Arrays.asList("server", "config") : List.of("");
        }
        return List.of("");
    }

    private void resetMessage(Player p, GameMode gm) {
        switch (gm) {
            case SURVIVAL -> p.setGameMode(GameMode.SURVIVAL);
            case ADVENTURE -> p.setGameMode(GameMode.ADVENTURE);
        }
        p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + ChatColor.GREEN + p.getName() + ChatColor.LIGHT_PURPLE + "님은 현재, " + ChatColor.GREEN + p.getWorld().getName() + ChatColor.LIGHT_PURPLE + "월드에 있으므로, " + ChatColor.GREEN + p.getGameMode() + ChatColor.LIGHT_PURPLE + "모드로 변경되었음을 알려드립니다.");
    }

    private void no_permissions(Player p, String permissions) {
        p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "당신은 이 명령어를 사용할 권한이 없습니다!");
        p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + "관계자에게 아래 정보와 함께 권한을 요청해보세요.");
        p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + "플레이어: " + ChatColor.WHITE + p.getName() + ChatColor.LIGHT_PURPLE + " 퍼미션: " + ChatColor.WHITE + permissions);
    }

    private void no_ops(Player p) {
        p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "당신은 이 명령어를 사용할 권한이 없습니다!");
        p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + "관계자에게 아래 정보와 함께 권한을 요청해보세요.");
        p.sendMessage(ChatColor.LIGHT_PURPLE + "플레이어: " + ChatColor.WHITE + p.getName());
    }
}