package yejunho10.magicplugin.func;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import yejunho10.magicplugin.GUIPlugin;

import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import static org.bukkit.Bukkit.getLogger;
import static yejunho10.magicplugin.cmd.Ticket.ticket;
import static yejunho10.magicplugin.func.Teleport.*;
import static yejunho10.magicplugin.func.Teleport.loc;

public class Functions {
    private static final GUIPlugin plugin = GUIPlugin.getInstance();
    private static FileConfiguration config = plugin.getConfig();
    private static int i;

    public static void sendAnnoucement() {
        if (!(Bukkit.getOnlinePlayers().size() == 0)) {
            i++;
            switch (i) {
                case 1 -> Bukkit.broadcastMessage(ChatColor.YELLOW + "[" + ChatColor.GREEN + "!" + ChatColor.YELLOW + "] " + ChatColor.LIGHT_PURPLE + "핵, 엑스레이는 불법입니다.");
                case 2 -> Bukkit.broadcastMessage(ChatColor.YELLOW + "[" + ChatColor.GREEN + "!" + ChatColor.YELLOW + "] " + ChatColor.LIGHT_PURPLE + "디스코드 서버에 들어와주세요. http://discord.feathers.kro.kr");
                case 3 -> Bukkit.broadcastMessage(ChatColor.YELLOW + "[" + ChatColor.GREEN + "!" + ChatColor.YELLOW + "] " + ChatColor.LIGHT_PURPLE + "우리 서버를 추천해주세요. http://minelist.feathers.kro.kr");
                case 4 -> Bukkit.broadcastMessage(ChatColor.YELLOW + "[" + ChatColor.GREEN + "!" + ChatColor.YELLOW + "] " + ChatColor.LIGHT_PURPLE + "도메인이 mc.feather-s.kr로 변경되었습니다. 기존 도메인은 4/30에 지원 종료됩니다.");
                case 5 -> Bukkit.broadcastMessage(ChatColor.YELLOW + "[" + ChatColor.GREEN + "!" + ChatColor.YELLOW + "] " + ChatColor.LIGHT_PURPLE + "스태프를 모집합니다. http://forms.feathers.kro.kr");
                case 6 -> Bukkit.broadcastMessage(ChatColor.YELLOW + "[" + ChatColor.GREEN + "!" + ChatColor.YELLOW + "] " + ChatColor.LIGHT_PURPLE + "후원을 해주시면 각종 특권을 드립니다. http://thanks.feathers.kro.kr");
            }
            if (i == 6) {
                i = 0;
            }
        }
    }

    public static void saveMaps1() {
        try {
            if (!(map1.size() == 0)) {
                getLogger().info("[map1 저장 시작]");
                for (Map.Entry<UUID, Location> entry : map1.entrySet()) {
                    config.set("maps.map1." + entry.getKey(), entry.getValue());
                    getLogger().info("[map1 저장중 : " + entry.getKey() + "]");
                }
                plugin.saveConfig();
                getLogger().info("[map1 저장완료]");
            } else {
                getLogger().info("[map1 저장 취소]");
            }
        } catch (Exception e) {
            getLogger().info("[map1 저장 실패]");
            e.printStackTrace();
        }
    }

    public static void saveMaps2() {
        try {
            if (!(map2.size() == 0)) {
                getLogger().info("[map2 저장 시작]");
                for (Map.Entry<UUID, Location> entry : map2.entrySet()) {
                    config.set("maps.map2." + entry.getKey(), entry.getValue());
                    getLogger().info("[map2 저장중 : " + entry.getKey() + "]");
                }
                plugin.saveConfig();
                getLogger().info("[map2 저장완료]");
            } else {
                getLogger().info("[map2 저장 취소]");
            }
        } catch (Exception e) {
            getLogger().info("[map2 저장 실패]");
        }
    }

    public static void saveMaps3() {
        try {
            if (!(map2.size() == 0)) {
                getLogger().info("[map3 저장 시작]");
                for (Map.Entry<UUID, Location> entry : map3.entrySet()) {
                    config.set("maps.map3." + entry.getKey(), entry.getValue());
                    getLogger().info("[map3 저장중 : " + entry.getKey() + "]");
                }
                plugin.saveConfig();
                getLogger().info("[map3 저장완료]");
            } else {
                getLogger().info("[map3 저장 취소]");
            }
        } catch (Exception e) {
            getLogger().info("[map3 저장 실패]");
        }
    }

    public static void saveHome() {
        try {
            if (!(home.size() == 0)) {
                getLogger().info("[home 저장 시작]");
                for (Map.Entry<UUID, Location> entry : home.entrySet()) {
                    config.set("maps.home." + entry.getKey(), entry.getValue());
                    getLogger().info("[home 저장중 : " + entry.getKey() + "]");
                }
                plugin.saveConfig();
                getLogger().info("[home 저장완료]");
            } else {
                getLogger().info("[home 저장 취소]");
            }
        } catch (Exception e) {
            getLogger().info("[home 저장 실패]");
        }
    }

    public static void saveTicket() {
        try {
            if (!(ticket.size() == 0)) {
                getLogger().info("[ticket 저장 시작]");
                for (Map.Entry<UUID, Integer> entry : ticket.entrySet()) {
                    if (entry.getValue() == 0) {
                        if (config.contains("maps.ticket." + entry.getKey())) {
                            config.set("maps.ticket." + entry.getKey(), null);
                        }
                    } else {
                        config.set("maps.ticket." + entry.getKey(), entry.getValue());
                        getLogger().info("[ticket 저장중 : " + entry.getKey() + "]");
                    }
                }
                plugin.saveConfig();
                getLogger().info("[ticket 저장완료]");
            } else {
                getLogger().info("[ticket 저장 취소]");
            }
        } catch (Exception e) {
            getLogger().info("[ticket 저장 실패]");
        }
    }

    public static void saveMaps() {
        saveMaps1();
        saveMaps2();
        saveMaps3();
        saveHome();
        saveTicket();
    }

    public static void restoreMaps() {
        try {
            getLogger().info("[map1 불러오기 시작]");
            config.getConfigurationSection("maps.map1").getKeys(false).forEach(key -> {
                Location content = config.getLocation("maps.map1." + key);
                getLogger().info("[map1 불러오기 : " + key + "]");
                map1.put(UUID.fromString(key), content);
            });
            getLogger().info("[map1 불러오기 완료]");
        } catch (Exception e) {
            getLogger().info("[map1 불러오기 실패]");
            e.printStackTrace();
        }


        try {
            getLogger().info("[map2 불러오기 시작]");
            config.getConfigurationSection("maps.map2").getKeys(false).forEach(key -> {
                Location content = config.getLocation("maps.map2." + key);
                getLogger().info("[map2 불러오기 : " + key + "]");
                map2.put(UUID.fromString(key), content);
            });
            getLogger().info("[map2 불러오기 완료]");
        } catch (Exception e) {
            getLogger().info("[map2 불러오기 실패]");
            e.printStackTrace();
        }


        try {
            getLogger().info("[map3 불러오기 시작]");
            config.getConfigurationSection("maps.map3").getKeys(false).forEach(key -> {
                Location content = ((Location) config.get("maps.map3." + key));
                getLogger().info("[map3 불러오기 : " + key + "]");
                map3.put(UUID.fromString(key), content);
            });
            getLogger().info("[map3 불러오기 완료]");
        } catch (Exception e) {
            getLogger().info("[map3 불러오기 실패]");
            e.printStackTrace();
        }


        try {
            getLogger().info("[home 불러오기 시작]");
            config.getConfigurationSection("maps.home").getKeys(false).forEach(key -> {
                Location content = config.getLocation("maps.home." + key);
                getLogger().info("[home 불러오기 : " + key + "]");
                home.put(UUID.fromString(key), content);
            });
            getLogger().info("[home 불러오기 완료]");
        } catch (Exception e) {
            getLogger().info("[home 불러오기 실패]");
            e.printStackTrace();
        }


        if (config.getConfigurationSection("maps.ticket").getKeys(false).isEmpty()) {
            getLogger().info("[ticket 불러오기중, 경로에 값이 존재하지 않습니다]");
        } else {
            getLogger().info("[ticket 불러오기 시작]");
            config.getConfigurationSection("maps.ticket").getKeys(false).forEach(key -> {
                int content = config.getInt("maps.ticket." + key);
                if (!(content == 0)) {
                    ticket.put(UUID.fromString(key), content);
                    getLogger().info("[ticket 불러오기 : " + key + "]");
                }
            });
            getLogger().info("[ticket 불러오기 완료]");
        }


        if (config.getConfigurationSection("worlds.spawn").getKeys(false).isEmpty()) {
            getLogger().info("[spawn 불러오기중, 경로에 값이 존재하지 않습니다]");
        } else {
            getLogger().info("[spawn 불러오기 시작]");
            String world = config.getString("worlds.spawn.world");
            int x = config.getInt("worlds.spawn.x");
            int y = config.getInt("worlds.spawn.y");
            int z = config.getInt("worlds.spawn.z");
            Location loc_spawn = new Location(Bukkit.getServer().getWorld(world), x, y, z);
            loc.put("spawn", loc_spawn);
            getLogger().info("[spawn 불러오기 : " + loc_spawn + "]");
            getLogger().info("[spawn 불러오기 완료]");
        }


        if (config.getConfigurationSection("worlds.lobby").getKeys(false).isEmpty()) {
            getLogger().info("[lobby 불러오기중, 경로에 값이 존재하지 않습니다]");
        } else {
            getLogger().info("[lobby 불러오기 시작]");
            String world = config.getString("worlds.lobby.world");
            int x = config.getInt("worlds.lobby.x");
            int y = config.getInt("worlds.lobby.y");
            int z = config.getInt("worlds.lobby.z");
            Location loc_lobby = new Location(Bukkit.getServer().getWorld(world), x, y, z);
            loc.put("lobby", loc_lobby);
            getLogger().info("[lobby 불러오기 : " + loc_lobby + "]");
            getLogger().info("[lobby 불러오기 완료]");
        }


        if (config.getConfigurationSection("worlds.surv1").getKeys(false).isEmpty()) {
            getLogger().info("[surv1 불러오기중, 경로에 값이 존재하지 않습니다]");
        } else {
            getLogger().info("[surv1 불러오기 시작]");
            String world = config.getString("worlds.surv1.world");
            int x = config.getInt("worlds.surv1.x");
            int y = config.getInt("worlds.surv1.y");
            int z = config.getInt("worlds.surv1.z");
            Location loc_surv1 = new Location(Bukkit.getServer().getWorld(world), x, y, z);
            loc.put("surv1", loc_surv1);
            getLogger().info("[surv1 불러오기 : " + loc_surv1 + "]");
            getLogger().info("[surv1 불러오기 완료]");
        }


        if (config.getConfigurationSection("worlds.surv2").getKeys(false).isEmpty()) {
            getLogger().info("[surv2 불러오기중, 경로에 값이 존재하지 않습니다]");
        } else {
            getLogger().info("[surv2 불러오기 시작]");
            String world = config.getString("worlds.surv2.world");
            int x = config.getInt("worlds.surv2.x");
            int y = config.getInt("worlds.surv2.y");
            int z = config.getInt("worlds.surv2.z");
            Location loc_surv2 = new Location(Bukkit.getServer().getWorld(world), x, y, z);
            loc.put("surv2", loc_surv2);
            getLogger().info("[surv2 불러오기 : " + loc_surv2 + "]");
            getLogger().info("[surv2 불러오기 완료]");
        }


        if (config.getConfigurationSection("worlds.surv3").getKeys(false).isEmpty()) {
            getLogger().info("[surv3 불러오기중, 경로에 값이 존재하지 않습니다]");
        } else {
            getLogger().info("[surv3 불러오기 시작]");
            String world = config.getString("worlds.surv3.world");
            int x = config.getInt("worlds.surv3.x");
            int y = config.getInt("worlds.surv3.y");
            int z = config.getInt("worlds.surv3.z");
            Location loc_surv3 = new Location(Bukkit.getServer().getWorld(world), x, y, z);
            loc.put("surv3", loc_surv3);
            getLogger().info("[surv3 불러오기 : " + loc_surv3 + "]");
            getLogger().info("[surv3 불러오기 완료]");
        }


        if (config.getConfigurationSection("worlds.casino").getKeys(false).isEmpty()) {
            getLogger().info("[casino 불러오기중, 경로에 값이 존재하지 않습니다]");
        } else {
            getLogger().info("[casino 불러오기 시작]");
            String world = config.getString("worlds.casino.world");
            int x = config.getInt("worlds.casino.x");
            int y = config.getInt("worlds.casino.y");
            int z = config.getInt("worlds.casino.z");
            Location loc_casino = new Location(Bukkit.getServer().getWorld(world), x, y, z);
            loc.put("casino", loc_casino);
            getLogger().info("[casino 불러오기 : " + loc_casino + "]");
            getLogger().info("[casino 불러오기 완료]");
        }


        if (config.getConfigurationSection("worlds.minigame1").getKeys(false).isEmpty()) {
            getLogger().info("[minigame1 불러오기중, 경로에 값이 존재하지 않습니다]");
        } else {
            getLogger().info("[minigame1 불러오기 시작]");
            String world = config.getString("worlds.minigame1.world");
            int x = config.getInt("worlds.minigame1.x");
            int y = config.getInt("worlds.minigame1.y");
            int z = config.getInt("worlds.minigame1.z");
            Location loc_minigame1 = new Location(Bukkit.getWorld(world), x, y, z);
            loc.put("minigame1", loc_minigame1);
            getLogger().info("[minigame1 불러오기 : " + loc_minigame1 + "]");
            getLogger().info("[minigame1 불러오기 완료]");
        }


        if (config.getConfigurationSection("worlds.zw").getKeys(false).isEmpty()) {
            getLogger().info("[zw 불러오기중, 경로에 값이 존재하지 않습니다]");
        } else {
            getLogger().info("[zw 불러오기 시작]");
            String world = config.get("worlds.zw.world").toString();
            int x = Integer.parseInt(config.get("worlds.zw.x").toString());
            int y = Integer.parseInt(config.get("worlds.zw.y").toString());
            int z = Integer.parseInt(config.get("worlds.zw.z").toString());
            Location loc_zw = new Location(Bukkit.getServer().getWorld(world), x, y, z);
            loc.put("zw", loc_zw);
            getLogger().info("[zw 불러오기 : " + loc_zw + "]");
            getLogger().info("[zw 불러오기 완료]");
        }


        if (config.getConfigurationSection("worlds.zb").getKeys(false).isEmpty()) {
            getLogger().info("[zb 불러오기중, 경로에 값이 존재하지 않습니다]");
        } else {
            getLogger().info("[zb 불러오기 시작]");
            String world = config.get("worlds.zb.world").toString();
            int x = Integer.parseInt(config.get("worlds.zb.x").toString());
            int y = Integer.parseInt(config.get("worlds.zb.y").toString());
            int z = Integer.parseInt(config.get("worlds.zb.z").toString());
            Location loc_zb = new Location(Bukkit.getServer().getWorld(world), x, y, z);
            loc.put("zb", loc_zb);
            getLogger().info("[zb 불러오기 : " + loc_zb + "]");
            getLogger().info("[zb 불러오기 완료]");
        }
    }
}
