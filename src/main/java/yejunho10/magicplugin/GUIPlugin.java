package yejunho10.magicplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import yejunho10.magicplugin.cmd.*;
import yejunho10.magicplugin.event.Event;
import yejunho10.magicplugin.gui.ItemInventory;
import yejunho10.magicplugin.party.Party;
import yejunho10.magicplugin.party.PartyCommand;
import yejunho10.magicplugin.party.PartyPlayer;
import yejunho10.magicplugin.tpa.TPAccept;
import yejunho10.magicplugin.tpa.TPADeny;
import yejunho10.magicplugin.tpa.TPAsk;
import yejunho10.magicplugin.etc.tpaData;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static yejunho10.magicplugin.func.Functions.*;

@SuppressWarnings("all")
public class GUIPlugin extends JavaPlugin implements CommandExecutor {
    private static GUIPlugin instance;
    public static Map<String, PartyPlayer> ppMap = new HashMap<>();
    public static Map<Integer, Party> partyMap = new HashMap<>();
    public static Map<String, Integer> inviteMap = new HashMap<>();

    public static Map<Player, tpaData> tpaMap = new HashMap<>();

    Timer timerForAnnouncement = new Timer();

    public static Location gungameEnable = new Location(Bukkit.getWorld("minigame2"), 82, -36, 130);

    @Override
    public void onEnable() {
        getLogger().info("[플러그인이 활성화됩니다]");

        getServer().getPluginManager().registerEvents(new Event(), this);
        getServer().getPluginManager().registerEvents(new ItemInventory(), this);
        getCommand("mp").setExecutor(new MPCommand());
        getCommand("tk").setExecutor(new Ticket());
        getCommand("menu").setExecutor(new ItemCommands());
        getCommand("sethome").setExecutor(new SetHome());
        getCommand("home").setExecutor(new Home());
        getCommand("party").setExecutor(new PartyCommand());

        getCommand("tpask").setExecutor(new TPAsk());
        getCommand("tpaccept").setExecutor(new TPAccept());
        getCommand("tpadeny").setExecutor(new TPADeny());

        instance = this;

        timerForAnnouncement.schedule(new TimerTask() {
            @Override
            public void run() {
                sendAnnoucement();
            }
        }, 5000, 120000);

        restoreMaps();
    }

    @Override
    public void onDisable() {
        getLogger().info("[플러그인이 비활성화됩니다]");

        timerForAnnouncement.cancel();

        saveMaps();
    }

    public static GUIPlugin getInstance() {
        return instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("mpr")) {
            if (!sender.isOp()) {
                sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "이 기능은 OP 권한만 사용가능합니다.");
                return false;
            }

            restoreMaps();
            sender.sendMessage(ChatColor.GREEN + "[콘피그 파일 불러오기 완료]");
            return true;
        }
        else if (label.equalsIgnoreCase("mps")) {
            if (!sender.isOp()) {
                sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "이 기능은 OP 권한만 사용가능합니다.");
                return false;
            }

            saveMaps();
            sender.sendMessage(ChatColor.GREEN + "[콘피그 파일 저장 완료]");
            return true;
        }
        else if (label.equalsIgnoreCase("heal")) {
            if (!sender.isOp()) {
                sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "이 기능은 OP 권한만 사용가능합니다.");
                return false;
            }

            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "존재하지 않는 플레이어입니다.");
                    return false;
                }
                if (target.isOnline()) {
                    sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "온라인 상태인 플레이어만 사용가능합니다.");
                    return false;
                }

                target.setHealth(target.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
                target.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + sender.getName() + ChatColor.LIGHT_PURPLE + "님에 의하여 체력이 회복되었습니다.");
                return true;
            }
            else if (args.length > 1) {
                sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "입력 값이 많습니다.");
            }

            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "명령어는 플레이어만 사용 가능합니다.");
                return false;
            } //콘솔등에서 입력시
            Player p = (Player) sender;

            p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue());
            p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.LIGHT_PURPLE + "체력이 회복되었습니다.");
            return true;
        }
        else if (label.equalsIgnoreCase("feed")) {
            if (!sender.isOp()) {
                sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "이 기능은 OP 권한만 사용가능합니다.");
                return false;
            }

            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "존재하지 않는 플레이어입니다.");
                    return false;
                }
                if (!target.isOnline()) {
                    sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "온라인 상태인 플레이어만 사용가능합니다.");
                    return false;
                }

                target.setFoodLevel(20);
                target.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + sender.getName() + ChatColor.LIGHT_PURPLE + "님에 의하여 배고픔이 회복되었습니다.");
                return true;
            }
            else if (args.length > 1) {
                sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "입력 값이 많습니다.");
            }

            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "명령어는 플레이어만 사용 가능합니다.");
                return false;
            } //콘솔등에서 입력시
            Player p = (Player) sender;

            p.setFoodLevel(20);
            p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.LIGHT_PURPLE + "배고픔이 회복되었습니다.");
            return true;
        }
        return false;
    }
}

