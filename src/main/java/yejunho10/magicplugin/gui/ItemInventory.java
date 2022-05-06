package yejunho10.magicplugin.gui;

import yejunho10.magicplugin.event.OpenItemInventoryEvent;
import yejunho10.magicplugin.func.Teleport;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;

@SuppressWarnings("all")
public class ItemInventory implements Listener {
    final Inventory inv = Bukkit.createInventory(null, 54, "메뉴");

    public ItemInventory() {
        Init();
    }

    public void Init() {
        ItemStack empty = new ItemStack(Material.BLUE_STAINED_GLASS_PANE, 1);
        ItemMeta empty_meta = empty.getItemMeta();
        empty_meta.setDisplayName(".");
        empty.setItemMeta(empty_meta);
        
        for(int i = 0; i < 10; i++) {
            inv.setItem(i, empty);
        }
        for(int i = 17; i < 28; i++) {
            inv.setItem(i, empty);
        }
        inv.setItem(35, empty);
        inv.setItem(36, empty);
        inv.setItem(44, empty);
        inv.setItem(45, empty);
        inv.setItem(46, empty);
        inv.setItem(47, empty);
        inv.setItem(48, empty);
        inv.setItem(50, empty);
        inv.setItem(51, empty);
        inv.setItem(52, empty);
        inv.setItem(53, empty);

        ItemStack teleport1 = new ItemStack(Material.OAK_LOG, 1);
        ItemMeta teleport1Meta = teleport1.getItemMeta();
        teleport1Meta.setDisplayName("스폰 이동");
        teleport1Meta.setLore(Collections.singletonList(ChatColor.GREEN + "클릭시 스폰으로 텔레포트 됩니다."));
        teleport1.setItemMeta(teleport1Meta);
        inv.setItem(11, teleport1);

        ItemStack teleport2 = new ItemStack(Material.BRICKS, 1);
        ItemMeta teleport2Meta = teleport2.getItemMeta();
        teleport2Meta.setDisplayName("로비 이동");
        teleport2Meta.setLore(Collections.singletonList(ChatColor.GREEN + "클릭시 로비로 텔레포트 됩니다."));
        teleport2.setItemMeta(teleport2Meta);
        inv.setItem(12, teleport2);

        ItemStack minigame1 = new ItemStack(Material.ORANGE_CONCRETE, 1);
        ItemMeta minigame1Meta = minigame1.getItemMeta();
        minigame1Meta.setDisplayName("미니게임 이동");
        minigame1Meta.setLore(Collections.singletonList(ChatColor.GREEN + "클릭시 미니게임으로 텔레포트 됩니다."));
        minigame1.setItemMeta(minigame1Meta);
        inv.setItem(13, minigame1);

        ItemStack teleport3 = new ItemStack(Material.LIGHT, 1);
        ItemMeta teleport3Meta = teleport3.getItemMeta();
        teleport3Meta.setDisplayName("기록된 위치 삭제");
        teleport3Meta.setLore(Arrays.asList(ChatColor.GREEN + "클릭시 야생 기록 위치가 모두 삭제됩니다.", ChatColor.RED + "사용에 주의해주세요."));
        teleport3.setItemMeta(teleport3Meta);
        inv.setItem(14, teleport3);

        ItemStack teleport4 = new ItemStack(Material.DIAMOND, 1);
        ItemMeta teleport4Meta = teleport4.getItemMeta();
        teleport4Meta.setDisplayName("도박장 이동");
        teleport4Meta.setLore(Collections.singletonList(ChatColor.GREEN + "클릭시 도박장으로 텔레포트 됩니다."));
        teleport4.setItemMeta(teleport4Meta);
        inv.setItem(15, teleport4);

        ItemStack survival1 = new ItemStack(Material.GRASS_BLOCK, 1);
        ItemMeta survival1Meta = survival1.getItemMeta();
        survival1Meta.setDisplayName("야생1 이동");
        survival1Meta.setLore(Arrays.asList(ChatColor.GREEN + "클릭시 야생1로 텔레포트 됩니다.", ChatColor.GREEN + "난이도 : 쉬움"));
        survival1.setItemMeta(survival1Meta);
        inv.setItem(30, survival1);

        ItemStack survival2 = new ItemStack(Material.GRASS_BLOCK, 1);
        ItemMeta survival2Meta = survival2.getItemMeta();
        survival2Meta.setDisplayName("야생2 이동");
        survival2Meta.setLore(Arrays.asList(ChatColor.GREEN + "클릭시 야생2로 텔레포트 됩니다.", ChatColor.GREEN + "난이도 : 보통"));
        survival2.setItemMeta(survival2Meta);
        inv.setItem(31, survival2);

        ItemStack survival3 = new ItemStack(Material.GRASS_BLOCK, 1);
        ItemMeta survival3Meta = survival3.getItemMeta();
        survival3Meta.setDisplayName("야생3 이동");
        survival3Meta.setLore(Arrays.asList(ChatColor.GREEN + "클릭시 야생3으로 텔레포트 됩니다.", ChatColor.GREEN + "난이도 : 어려움"));
        survival3.setItemMeta(survival3Meta);
        inv.setItem(32, survival3);

        ItemStack close = new ItemStack(Material.RED_DYE, 1);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.setDisplayName("메뉴 닫기");
        closeMeta.setLore(Collections.singletonList(ChatColor.GREEN + "클릭시 메뉴가 닫힙니다."));
        close.setItemMeta(closeMeta);
        inv.setItem(49, close);
    }

    @EventHandler
    private void onOpenItemInventory(OpenItemInventoryEvent e) {
        Init();
        e.player.openInventory(inv);
    }

    @EventHandler
    private void onInventoryClick(InventoryClickEvent e) {
        if (e.getInventory() != inv) return;
        ItemStack clickedItem = e.getCurrentItem();
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        Player p = (Player) e.getWhoClicked();

        switch (clickedItem.getItemMeta().getDisplayName()) {
            case "스폰 이동":
                e.setCancelled(true);
                p.closeInventory();
                Teleport.teleportSpawn(p);
                break;
            case "로비 이동":
                e.setCancelled(true);
                p.closeInventory();
                Teleport.teleportLobby(p);
                break;
            case "도박장 이동":
                e.setCancelled(true);
                p.closeInventory();
                Teleport.teleportCasino(p);
                break;
            case "미니게임 이동":
                e.setCancelled(true);
                p.closeInventory();
                Teleport.teleportMinigame1(p);
                break;
            case "야생1 이동":
                e.setCancelled(true);
                p.closeInventory();
                Teleport.teleportSurvival1(p);
                break; //야생1 이동
            case "야생2 이동":
                e.setCancelled(true);
                p.closeInventory();
                Teleport.teleportSurvival2(p);
                break; //야생2 이동
            case "야생3 이동":
                e.setCancelled(true);
                p.closeInventory();
                Teleport.teleportSurvival3(p);
                break; //야생3 이동
            case "메뉴 닫기":
                e.setCancelled(true);
                p.closeInventory();
                break; //GUI 닫기
            case "기록된 위치 삭제":
                e.setCancelled(true);
                p.closeInventory();
                Teleport.resetRecordLocatins(p);
                break;
            default:
                e.setCancelled(true);
                break; //테두리 클릭
        }
    }

    @EventHandler
    private void onInventoryDrag(InventoryDragEvent e) {
        if (e.getInventory() == inv) {
            e.setCancelled(true);
        }
    }
}