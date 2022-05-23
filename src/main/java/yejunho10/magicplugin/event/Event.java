package yejunho10.magicplugin.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import yejunho10.magicplugin.GUIPlugin;

import java.util.Collections;
import java.util.Random;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Bukkit.getWorld;
import static yejunho10.magicplugin.cmd.Ticket.ticket;
import static yejunho10.magicplugin.func.Teleport.loc;

public class Event implements Listener {
    public static Boolean terror = false;

    @EventHandler
    private void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if (terror) {
            p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "테러 대응모드 활성화중이므로 블럭을 파괴할 수 없습니다! 이용에 불편을 드려 죄송합니다.");
            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_FALL, 1, 1);
            e.setCancelled(true);
        } else if (p.getWorld() == getWorld("world") || p.getWorld() == getWorld("co_01") || p.getWorld() == getWorld("casino") || p.getWorld() == getWorld("minigame1")) {
            if (!p.isOp()) {
                p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "해당 월드에서는 블럭 파괴가 불가능합니다! 이용에 불편을 드려 죄송합니다.");
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_FALL, 1, 1);
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();

        if (terror) {
            p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "테러 대응모드 활성화중이므로 블럭을 설치할 수 없습니다! 이용에 불편을 드려 죄송합니다.");
            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_FALL, 1, 1);
            e.setCancelled(true);
        } else if (p.getWorld() == getWorld("world") || p.getWorld() == getWorld("co_01") || p.getWorld() == getWorld("casino") || p.getWorld() == getWorld("minigame1")) {
            if (!p.isOp()) {
                p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "해당 월드에서는 블럭 설치가 불가능합니다!");
                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_FALL, 1, 1);
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    private void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if (terror) {
            p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "테러 대응모드 활성화중이므로 움직일 수 없습니다! 이용에 불편을 드려 죄송합니다.");
            e.setCancelled(true);
        }
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.setJoinMessage(ChatColor.YELLOW + "[+]" + ChatColor.GREEN + e.getPlayer().getName() + "님이 서버에 접속하셨습니다.");
        p.sendMessage(ChatColor.YELLOW + "[공지]");
        p.sendMessage(ChatColor.YELLOW + "저희 서버에 오신것을 환영합니다.");
        p.sendMessage(ChatColor.YELLOW + "저희 서버는 아래와 같은 명령어를 지원합니다.");
        p.sendMessage(ChatColor.GREEN + "/sethome" + ChatColor.YELLOW + " - 집 설정");
        p.sendMessage(ChatColor.GREEN + "/home" + ChatColor.YELLOW + " - 집 이동");
        p.sendMessage(ChatColor.GREEN + "/menu | Shift + f" + ChatColor.YELLOW + " - 메뉴 열기");
        p.sendMessage(ChatColor.YELLOW + "궁금한 명령어가 있다면, " + ChatColor.GREEN + "/mp help | /tk help" + ChatColor.YELLOW + "를 입력해주세요.");

        if (!(ticket.containsKey(e.getPlayer().getUniqueId()))) {
            ticket.put(e.getPlayer().getUniqueId(), 0);
            Bukkit.getLogger().info("[플레이어 기초 티켓 값 저장]");
        }
    }

    @EventHandler
    private void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(ChatColor.YELLOW + "[-]" + ChatColor.GREEN + e.getPlayer().getName() + "님이 서버에서 퇴장하셨습니다.");
        if (Bukkit.getOnlinePlayers().size() == 0) {
            Bukkit.getWorld("mingiame2").getBlockAt(GUIPlugin.gungameEnable).setType(Material.AIR);
            Bukkit.broadcastMessage(ChatColor.RED + "### 남은플레이어가 없어 TNT건 게임이 꺼졌습니다. ###");
        }
    }

    @EventHandler
    private void onPlayerChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (p.getName().equals("yejunho10") || p.getName().equals("ikevin0920")) {
            e.setFormat(ChatColor.WHITE + "[" + ChatColor.BLUE + "MASTER" + ChatColor.WHITE + " - " + ChatColor.AQUA + e.getPlayer().getName() + ChatColor.WHITE + "] " + e.getMessage());
        } else if (p.getName().equals("KRHSJ")) {
            e.setFormat(ChatColor.WHITE + "[" + ChatColor.YELLOW + "STAFF" + ChatColor.WHITE + " - " + ChatColor.AQUA + e.getPlayer().getName() + ChatColor.WHITE + "] " + e.getMessage());
        } else if (p.getName().equals("billy0224")) {
            e.setFormat(ChatColor.WHITE + "[" + ChatColor.GREEN + "BUILDER" + ChatColor.WHITE + " - " + ChatColor.AQUA + e.getPlayer().getName() + ChatColor.WHITE + "] " + e.getMessage());
        } else if (p.getName().equals("seaduck138") || p.getName().equals("ROJA4515")) {
            e.setFormat(ChatColor.WHITE + "[" + ChatColor.RED + "TOP ADMIN" + ChatColor.WHITE + " - " + ChatColor.AQUA + e.getPlayer().getName() + ChatColor.WHITE + "] " + e.getMessage());
        } else if (p.getName().equals("Gigammas")) {
            e.setFormat(ChatColor.WHITE + "[" + ChatColor.RED + "ADMIN" + ChatColor.WHITE + " - " + ChatColor.AQUA + e.getPlayer().getName() + ChatColor.WHITE + "] " + e.getMessage());
        } else if (p.isOp()) {
            e.setFormat(ChatColor.WHITE + "[" + ChatColor.GOLD + "임시 OP" + ChatColor.WHITE + " - " + ChatColor.AQUA + e.getPlayer().getName() + ChatColor.WHITE + "] " + e.getMessage());
        } else {
            e.setFormat(ChatColor.WHITE + "[" + ChatColor.GOLD + "USER" + ChatColor.WHITE + " - " + ChatColor.AQUA + e.getPlayer().getName() + ChatColor.WHITE + "] " + e.getMessage());
        }
    }

    @EventHandler
    private void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = ((Player) e.getEntity()).getPlayer();
            switch (e.getEntity().getWorld().getName()) {
                case "world", "casino", "co_01", "minigame1" -> {
                    e.setCancelled(true);
                    p.sendMessage(ChatColor.GREEN + p.getName() + ChatColor.LIGHT_PURPLE + "님은 현재, " + ChatColor.GREEN + p.getWorld().getName() + ChatColor.LIGHT_PURPLE + "월드에 있으므로 " + ChatColor.GREEN + e.getDamager().getType() + ChatColor.LIGHT_PURPLE + "에게 받은 데미지를 취소합니다.");
                }
            }
        }
    }

    @EventHandler
    private void onPlayerDeath(PlayerDeathEvent e) {
        ItemStack item = new ItemStack(Material.ROTTEN_FLESH, 3);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Human Flesh");
        meta.setLore(Collections.singletonList(ChatColor.GREEN + "별로 도움되지는 않아보인다."));
        item.setItemMeta(meta);

        switch (e.getEntity().getWorld().getName()) {
            case "world", "casino", "co_01", "minigame1" -> {
                e.setKeepInventory(true);
                e.getDrops().clear();
            }
            default -> e.setKeepInventory(false);
        }
        e.getDrops().add(item);
    }

    @EventHandler
    private void onPlayerItemConsume(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        if (e.getItem().getType() == Material.ROTTEN_FLESH && e.getItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Human Flesh")) {
            int temp = (new Random()).nextInt(100);
            if (temp <= 30) {
                p.getWorld().dropItemNaturally(p.getLocation(), new ItemStack(Material.DIAMOND, 1));
                p.sendMessage(ChatColor.GREEN + "고기에서 보물을 찾았다!");
            } //30% 확률로 보물을 찾을 수 있다.
            else {
                p.sendMessage(ChatColor.RED + "별로 도움되지는 않아보인다...");
            } //70% 확률로 보물을 찾을 수 없다.

            for (ItemStack item : p.getInventory().getContents()) {
                if (!(item == null)) {
                    if (item.getType() == Material.ROTTEN_FLESH && p.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Human Flesh")) {
                        if (item.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Human Flesh")) {
                            item.setAmount(item.getAmount() - 1);
                            break;
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    private void onPlayerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        switch (p.getWorld().getName()) {
            case "world" -> e.setRespawnLocation(loc.get("spawn"));
            case "casino" -> e.setRespawnLocation(loc.get("casino"));
            case "co_01" -> e.setRespawnLocation(loc.get("lobby"));
            case "minigame1" -> e.setRespawnLocation(loc.get("minigame1"));
        }
    }

    @EventHandler
    private void onPlayerSwapHandItem(PlayerSwapHandItemsEvent e) {
        Player p = e.getPlayer();

        if (p.isSneaking()) {
            Bukkit.getPluginManager().callEvent(new OpenItemInventoryEvent(p));
            p.sendMessage(ChatColor.GREEN + "메뉴창을 열고 있습니다..");
            getLogger().info(p.getName() + "님이 메뉴창을 열고 있습니다..");
        }
    } //Shift + f  ->  메뉴창 열기
}