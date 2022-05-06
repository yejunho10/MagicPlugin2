package yejunho10.magicplugin.func;

import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Teleport {
    public static Map<UUID, Location> map1 = new HashMap<>();
    public static Map<UUID, Location> map2 = new HashMap<>();
    public static Map<UUID, Location> map3 = new HashMap<>();
    public static Map<String, Location> loc = new HashMap<>();

    public static Map<UUID, Location> home = new HashMap<>();
    

    public static void teleportSpawn(Player p) {
        if (p.hasPermission("myplugin.teleport.lobby")) {
            if (p.getWorld() == Bukkit.getWorld("surv1")) {
                UUID playerUUID = p.getUniqueId();
                Location survivalLocation = p.getLocation();
                map1.put(playerUUID, survivalLocation);
            } else if (p.getWorld() == Bukkit.getWorld("surv2")) {
                UUID playerUUID = p.getUniqueId();
                Location survivalLocation = p.getLocation();
                map2.put(playerUUID, survivalLocation);
            } else if (p.getWorld() == Bukkit.getWorld("surv3")) {
                UUID playerUUID = p.getUniqueId();
                Location survivalLocation = p.getLocation();
                map3.put(playerUUID, survivalLocation);
            }

            Location Dest_Spawn = new Location(loc.get("spawn").getWorld(), loc.get("spawn").getX(), loc.get("spawn").getY(), loc.get("spawn").getZ()); //스폰
            p.teleport(Dest_Spawn);
            p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + "성공적으로 텔레포트 되었습니다!");

            gamemode_adventure(p);
        } else {
            no_permissions(p, "myplugin.teleport.lobby");
        }
    }

    public static void teleportLobby(Player p) {
        if (p.hasPermission("myplugin.teleport.lobby")) {
            if (p.getWorld() == Bukkit.getWorld("surv1")) {
                UUID playerUUID = p.getUniqueId();
                Location survivalLocation = p.getLocation();
                map1.put(playerUUID, survivalLocation);
            } else if (p.getWorld() == Bukkit.getWorld("surv2")) {
                UUID playerUUID = p.getUniqueId();
                Location survivalLocation = p.getLocation();
                map2.put(playerUUID, survivalLocation);
            } else if (p.getWorld() == Bukkit.getWorld("surv3")) {
                UUID playerUUID = p.getUniqueId();
                Location survivalLocation = p.getLocation();
                map3.put(playerUUID, survivalLocation);
            }

            Location Dest_Lobby = new Location(loc.get("lobby").getWorld(), loc.get("lobby").getX(), loc.get("lobby").getY(), loc.get("lobby").getZ()); //로비
            p.teleport(Dest_Lobby);
            p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + "성공적으로 텔레포트 되었습니다!");

            gamemode_adventure(p);
        } else {
            no_permissions(p, "myplugin.teleport.lobby");
        }
    }

    public static void teleportCasino(Player p) {
        if (p.hasPermission("myplugin.teleport.casino")) {
            if (p.getWorld() == Bukkit.getWorld("surv1")) {
                UUID playerUUID = p.getUniqueId();
                Location survivalLocation = p.getLocation();
                map1.put(playerUUID, survivalLocation);
            } else if (p.getWorld() == Bukkit.getWorld("surv2")) {
                UUID playerUUID = p.getUniqueId();
                Location survivalLocation = p.getLocation();
                map2.put(playerUUID, survivalLocation);
            } else if (p.getWorld() == Bukkit.getWorld("surv3")) {
                UUID playerUUID = p.getUniqueId();
                Location survivalLocation = p.getLocation();
                map3.put(playerUUID, survivalLocation);
            }

            Location Dest_Casino = new Location(loc.get("casino").getWorld(), loc.get("casino").getX(), loc.get("casino").getY(), loc.get("casino").getZ()); //카지노
            p.teleport(Dest_Casino);
            p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + "성공적으로 텔레포트 되었습니다!");

            gamemode_adventure(p);
        } else {
            no_permissions(p, "myplugin.teleport.casino");
        }
    }

    public static void teleportMinigame1(Player p) {
        if (p.hasPermission("myplugin.teleport.minigame1")) {
            if (p.getWorld() == Bukkit.getWorld("surv1")) {
                UUID playerUUID = p.getUniqueId();
                Location survivalLocation = p.getLocation();
                map1.put(playerUUID, survivalLocation);
            } else if (p.getWorld() == Bukkit.getWorld("surv2")) {
                UUID playerUUID = p.getUniqueId();
                Location survivalLocation = p.getLocation();
                map2.put(playerUUID, survivalLocation);
            } else if (p.getWorld() == Bukkit.getWorld("surv3")) {
                UUID playerUUID = p.getUniqueId();
                Location survivalLocation = p.getLocation();
                map3.put(playerUUID, survivalLocation);
            }

            Location Dest_Minigame1 = new Location(loc.get("minigame1").getWorld(), loc.get("minigame1").getX(), loc.get("minigame1").getY(), loc.get("minigame1").getZ()); //미니게임1
            p.teleport(Dest_Minigame1);
            p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + "성공적으로 텔레포트 되었습니다!");

            gamemode_adventure(p);

        } else {
            no_permissions(p, "myplugin.teleport.minigame1");
        }
    }

    public static void teleportSurvival1(Player p) {
        if (p.hasPermission("myplugin.teleport.survival")) {
            if (p.getWorld() == Bukkit.getWorld("surv1")) {
                p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "현재 " + ChatColor.GREEN + p.getName() + ChatColor.RED + "님은 이동하려는 월드와 같은 월드에 있으므로 이동을 취소했습니다.");
            } else {
                if (p.getWorld() == Bukkit.getWorld("surv2")) {
                    UUID playerUUID = p.getUniqueId();
                    map2.remove(playerUUID);
                    map2.put(playerUUID, p.getLocation());
                } else if (p.getWorld() == Bukkit.getWorld("surv3")) {
                    UUID playerUUID = p.getUniqueId();
                    map3.remove(playerUUID);
                    map3.put(playerUUID, p.getLocation());
                }

                Location Dest_surv1 = new Location(loc.get("surv1").getWorld(), loc.get("surv1").getX(), loc.get("surv1").getY(), loc.get("surv1").getZ()); //야생1
                p.teleport(Dest_surv1);
                p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + "성공적으로 텔레포트 되었습니다!");

                gamemode_survival(p);

                UUID playerUUID = p.getUniqueId();
                if (map1.containsKey(playerUUID)) {
                    p.teleport(map1.get(playerUUID));
                    map1.remove(playerUUID);
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "이전 기록된 위치로 텔레포트 됩니다.");
                } else {
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "이전 기록된 위치가 없습니다.");
                }
            }
        } else {
            no_permissions(p, "myplugin.teleport.survival");
        }
    }

    public static void teleportSurvival2(Player p) {
        if (p.hasPermission("myplugin.teleport.survival")) {
            if (p.getWorld() == Bukkit.getWorld("surv2")) {
                p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "현재 " + ChatColor.GREEN + p.getName() + ChatColor.RED + "님은 이동하려는 월드와 같은 월드에 있으므로 이동을 취소했습니다.");
            } else {
                if (p.getWorld() == Bukkit.getWorld("surv1")) {
                    UUID playerUUID = p.getUniqueId();
                    map1.remove(playerUUID);
                    map1.put(playerUUID, p.getLocation());
                } else if (p.getWorld() == Bukkit.getWorld("surv3")) {
                    UUID playerUUID = p.getUniqueId();
                    map3.remove(playerUUID);
                    map3.put(playerUUID, p.getLocation());
                }

                Location Dest_surv2 = new Location(loc.get("surv2").getWorld(), loc.get("surv2").getX(), loc.get("surv2").getY(), loc.get("surv2").getZ()); //야생2
                p.teleport(Dest_surv2);
                p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + "성공적으로 텔레포트 되었습니다!");

                gamemode_survival(p);

                UUID playerUUID = p.getUniqueId();
                if (map2.containsKey(playerUUID)) {
                    p.teleport(map2.get(playerUUID));
                    map2.remove(playerUUID);
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "이전 기록된 위치로 텔레포트 됩니다.");
                } else {
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "이전 기록된 위치가 없습니다.");
                }
            }
        } else {
            no_permissions(p, "myplugin.teleport.survival");
        }
    }

    public static void teleportSurvival3(Player p) {
        if (p.hasPermission("myplugin.teleport.survival")) {
            if (p.getWorld() == Bukkit.getWorld("surv3")) {
                p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "현재 " + ChatColor.GREEN + p.getName() + ChatColor.RED + "님은 이동하려는 월드와 같은 월드에 있으므로 이동을 취소했습니다.");
            } else {
                if (p.getWorld() == Bukkit.getWorld("surv1")) {
                    UUID playerUUID = p.getUniqueId();
                    map1.remove(playerUUID);
                    map1.put(playerUUID, p.getLocation());
                } else if (p.getWorld() == Bukkit.getWorld("surv2")) {
                    UUID playerUUID = p.getUniqueId();
                    map2.remove(playerUUID);
                    map2.put(playerUUID, p.getLocation());
                }

                Location Dest_surv3 = new Location(loc.get("surv3").getWorld(), loc.get("surv3").getX(), loc.get("surv3").getY(), loc.get("surv3").getZ()); //야생3
                p.teleport(Dest_surv3);
                p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + "성공적으로 텔레포트 되었습니다!");

                gamemode_survival(p);

                UUID playerUUID = p.getUniqueId();
                if (map3.containsKey(playerUUID)) {
                    p.teleport(map3.get(playerUUID));
                    map3.remove(playerUUID);
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "이전 기록된 위치로 텔레포트 됩니다.");
                } else {
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "이전 기록된 위치가 없습니다.");
                }
            }
        } else {
            no_permissions(p, "myplugin.teleport.survival");
        }
    }

    public static void resetRecordLocatins(Player p) {
        UUID playerUUID = p.getUniqueId();

        if (map1.containsKey(playerUUID)) {
            map1.remove(playerUUID);
            p.sendMessage(ChatColor.GREEN + "야생1 | 좌표 정보를 성공적으로 삭제하였습니다!");
        } else {
            p.sendMessage(ChatColor.RED + "야생1 | 좌표 정보를 삭제하지 못했습니다!");
        }
        if (map2.containsKey(playerUUID)) {
            map2.remove(playerUUID);
            p.sendMessage(ChatColor.GREEN + "야생2 | 좌표 정보를 성공적으로 삭제하였습니다!");
        } else {
            p.sendMessage(ChatColor.RED + "야생2 | 좌표 정보를 삭제하지 못했습니다!");
        }
        if (map3.containsKey(playerUUID)) {
            map3.remove(playerUUID);
            p.sendMessage(ChatColor.GREEN + "야생3 | 좌표 정보를 성공적으로 삭제하였습니다!");
        } else {
            p.sendMessage(ChatColor.RED + "야생3 | 좌표 정보를 삭제하지 못했습니다!");
        }
    }

    public static void gamemode_adventure(Player p) {
        if (!p.isOp()) {
            p.setGameMode(GameMode.ADVENTURE);
            p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + "어드벤쳐 모드로 변경되었습니다.");
            p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + "현재 게임모드: " + ChatColor.WHITE + p.getGameMode());
        } else if (p.isOp()) {
            p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + "게임모드가 변경되지 않았습니다. 사유: 해당 플레이어는 OP를 소유하고 있습니다.");
        }
    }

    public static void gamemode_survival(Player p) {
        if (!p.isOp()) {
            p.setGameMode(GameMode.SURVIVAL);
            p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + "서바이벌 모드로 변경되었습니다.");
            p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + "현재 게임모드: " + ChatColor.WHITE + p.getGameMode());
        } else if (p.isOp()) {
            p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + "게임모드가 변경되지 않았습니다. 사유: 해당 플레이어는 OP를 소유하고 있습니다.");
        }
    }

    public static void no_permissions(Player p, String permissions) {
        p.sendMessage(ChatColor.RED + "[오류] - " + ChatColor.WHITE + "당신은 이 명령어를 사용할 권한이 없습니다!");
        p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + "어드민에게 아래 정보와 함께 권한을 요청해보세요.");
        p.sendMessage(ChatColor.YELLOW + "[안내] - " + ChatColor.WHITE + "플레이어: " + ChatColor.WHITE + p.getName() + ChatColor.LIGHT_PURPLE + " 퍼미션: " + ChatColor.WHITE + permissions);
    }
}
