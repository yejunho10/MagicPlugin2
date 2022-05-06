package yejunho10.magicplugin.party;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import yejunho10.magicplugin.GUIPlugin;

import java.util.Arrays;
import java.util.List;

import static yejunho10.magicplugin.GUIPlugin.inviteMap;

public class PartyCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "명령어는 플레이어만 사용 가능합니다.");
            return false;
        } //콘솔등에서 입력시
        Player p = (Player) sender;

        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "사용법이 잘못되었습니다. /party help를 참고하세요.");
            return false;
        }

        PartyPlayer pp = PartyPlayer.getPartyPlayer(p);
        Party party;

        switch (args[0]) {
            case "create":
                if (pp.hasParty()) {
                    p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "이미 가입된 파티가 있습니다. /party leave를 사용하세요.");
                    return false;
                } //이미 가입된 파티가 있음

                party = new Party(p);
                pp.joinParty(party.getId());
                p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.LIGHT_PURPLE + "파티를 생성하였습니다.");
                break;
            case "list":
                if (!pp.hasParty()) {
                    p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "가입된 파티가 없습니다.");
                    return false;
                } //가입된 파티가 없음 (찐따)

                party = Party.getParty(pp.getPartyId());
                for (Player partyPlayer : party.getPlayers()) {
                    p.sendMessage(partyPlayer.getName() + (party.isMaster(partyPlayer) ? " §e파티장" : " §7파티원"));
                }
                break;
            case "remove":
                if (!pp.hasParty()) {
                    p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "가입된 파티가 없습니다.");
                    return false;
                } //가입된 파티가 없음 (찐따)
                party = Party.getParty(pp.getPartyId());
                if (!party.isMaster(p)) {
                    p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "파티장만 파티를 삭제할 수 있습니다.");
                    return false;
                } //파티장만 파티를 삭제할수 있음

                p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.LIGHT_PURPLE + "파티를 삭제하였습니다.");
                for (Player partyPlayer : party.getPlayers()) {
                    PartyPlayer.getPartyPlayer(partyPlayer).leaveParty();
                    partyPlayer.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.LIGHT_PURPLE + "파티가 삭제되어, 탈퇴 되었습니다.");
                }
                party.removeParty();
                break;
            case "leave":
                if (!pp.hasParty()) {
                    p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "가입된 파티가 없습니다.");
                    return false;
                } //가입된 파티가 없음 (찐따)
                party = Party.getParty(pp.getPartyId());
                if (party.isMaster(p)) {
                    p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "파티장은 파티를 떠날 수 없습니다. /party remove를 사용해주세요.");
                    return false;
                } //파티장은 파티를 떠날수 없음

                party.removePlayer(p);
                party.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.LIGHT_PURPLE + p.getName() + "님이 파티를 떠났습니다.");
                p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.LIGHT_PURPLE + "파티를 떠났습니다.");
                break;
            case "invite":
                if (args.length < 2) {
                    p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "초대할 플레이어를 입력해주세요.");
                } //초대할 플레이어 입력 X
                if (!pp.hasParty()) {
                    p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "가입된 파티가 없습니다.");
                    return false;
                } //가입된 파티가 없음 (찐따)
                party = Party.getParty(pp.getPartyId());
                if (!party.isMaster(p)) {
                    p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "파티장만 초대기능을 사용할수 있습니다.");
                    return false;
                } //파티장이 아니면 초대할 수 없음
                Player target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "초대할 플레이어가 존재하지 않습니다.");
                    return false;
                } //타겟이 존재하지 않음/온라인이 아님
                PartyPlayer pptarget = PartyPlayer.getPartyPlayer(target);
                if (pptarget.hasParty()) {
                    p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "해당 플레이어는 이미 다른 파티에 속해있습니다.");
                    return false;
                } //플레이어가 이미 다른 파티에 속해있음
                if (inviteMap.containsKey(target.getName())) {
                    p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "해당 플레이어는 이미 초대를 받았습니다. 잠시후 다시 시도해주세요.");
                    return false;
                } //이미 초대를 받은 플레이어임
                if (target.getName() == p.getName()) {
                    p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "자신을 초대할 수 없습니다.");
                    return false;
                } //자신을 초대할 수 없음

                p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.LIGHT_PURPLE + target.getName() + "님을 초대하였습니다.");
                target.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.LIGHT_PURPLE + p.getName() + "님이 파티에 초대되었습니다. 수락하려면 /party accept, 거절하려면 /party deny를 15초 이내에 사용해주세요.");

                inviteMap.put(target.getName(), party.getId());

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (inviteMap.containsKey(target.getName()) && inviteMap.get(target.getName()) == (party.getId())) {
                            inviteMap.remove(target.getName());
                        }
                    }
                }.runTaskLaterAsynchronously(GUIPlugin.getInstance(), 20 * 15);
                break;
            case "kick":
                if (args.length < 2) {
                    p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "추방할 플레이어를 입력해주세요.");
                } //추방할 플레이어 입력 X
                if (!pp.hasParty()) {
                    p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "가입된 파티가 없습니다.");
                    return false;
                } //가입된 파티가 없음
                party = Party.getParty(pp.getPartyId());
                if (!party.isMaster(p)) {
                    p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "파티장만 추방할수 있습니다.");
                    return false;
                } //파티장이 아님
                target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "추방할 플레이어가 존재하지 않습니다.");
                    return false;
                } //타겟이 존재하지 않음/온라인이 아님
                pptarget = PartyPlayer.getPartyPlayer(target);
                if (!pptarget.hasParty() || pptarget.getPartyId() != pp.getPartyId()) {
                    p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "해당 플레이어는 이 파티에 속해있지 않습니다.");
                    return false;
                } //타겟이 이 파티에 속하지 않음
                if (party.isMaster(target)) {
                    p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "파티장은 추방할수 없습니다.");
                } //추방하려는 플레이어가 파티장임
                if (target.getName() == p.getName()) {
                    p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "자신을 추방할 수 없습니다.");
                    return false;
                } //자신을 추방하려고 함

                party.removePlayer(target);
                target.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.LIGHT_PURPLE + p.getName() + "님은 파티에서 추방되었습니다.");
                party.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.LIGHT_PURPLE + target.getName() + "님은 파티에서 추방되었습니다.");
                pptarget.leaveParty();
                break;
            case "accept":
                if (!inviteMap.containsKey(p.getName())) {
                    p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "초대를 받은 기록이 없습니다.");
                    return false;
                } //초대 받은적 없음 (찐따)
                if (pp.hasParty()) {
                    p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "이미 파티에 속해있습니다.");
                    return false;
                } //이미 다른 파티에 속해 있음
                party = Party.getParty(inviteMap.get(p.getName()));
                if (party == null) {
                    p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "초대를 받은 파티가 존재하지 않습니다. (이미 삭제되었을수 있습니다)");
                    return false;
                } //존재하지 않는 파티에서 초대를 받음

                party.addPlayer(p);
                party.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.LIGHT_PURPLE + p.getName() + "님이 파티에 참여했습니다.");
                p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.LIGHT_PURPLE + "초대를 수락했습니다.");
                pp.joinParty(party.getId());
                inviteMap.remove(p.getName());
                break;
            case "deny":
                if (!inviteMap.containsKey(p.getName())) {
                    p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "초대를 받은 기록이 없습니다.");
                    return false;
                } //초대 받은적 없음 (찐따)
                party = Party.getParty(inviteMap.get(p.getName()));
                if (party == null) {
                    p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "초대를 받은 파티가 존재하지 않습니다. (이미 삭제되었을수 있습니다)");
                    return false;
                } //존재하지 않는 파티에서 초대를 받음

                party.getMasterPlayer().sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.LIGHT_PURPLE + p.getName() + "님이 초대를 거절했습니다.");
                inviteMap.remove(p.getName());
                p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.LIGHT_PURPLE + "초대를 거절했습니다.");
                break;
            case "help":
                p.sendMessage(ChatColor.YELLOW + "========== " + ChatColor.LIGHT_PURPLE + "/party 명령어 도움말" + ChatColor.YELLOW + " ==========");
                p.sendMessage(ChatColor.YELLOW + "/party create : 파티를 생성합니다.");
                p.sendMessage(ChatColor.YELLOW + "/party list : 파티원 목록을 확인합니다.");
                p.sendMessage(ChatColor.YELLOW + "/party remove : 파티를 삭제합니다.");
                p.sendMessage(ChatColor.YELLOW + "/party leave : 파티를 떠납니다.");
                p.sendMessage(ChatColor.YELLOW + "/party invite <플레이어> : <플레이어>를 파티에 초대합니다.");
                p.sendMessage(ChatColor.YELLOW + "/party kick <플레이어> : <플레이어>를 파티에서 추방합니다.");
                p.sendMessage(ChatColor.YELLOW + "/party accept : 초대를 수락합니다.");
                p.sendMessage(ChatColor.YELLOW + "/party deny : 초대를 거절합니다.");
                break;
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (args.length == 0) {
            return inviteMap.containsKey(sender.getName()) ? Arrays.asList("create", "list", "remove", "leave", "invite", "kick", "accept", "deny") : Arrays.asList("create", "list", "remove", "leave", "invite", "kick");
        }
        else if (args[0].equalsIgnoreCase("invite") || args[0].equalsIgnoreCase("kick")) {
            return null;
        } else if (args[0].equalsIgnoreCase("create") || args[0].equalsIgnoreCase("list") || args[0].equalsIgnoreCase("remove") ||
                args[0].equalsIgnoreCase("leave") || args[0].equalsIgnoreCase("accept") || args[0].equalsIgnoreCase("deny")) {
            return List.of("");
        }
        return null;
    }
}