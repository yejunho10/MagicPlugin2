package yejunho10.magicplugin.npc;

import com.mojang.authlib.GameProfile;
import net.minecraft.network.protocol.game.PacketPlayOutNamedEntitySpawn;
import net.minecraft.network.protocol.game.PacketPlayOutPlayerInfo;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.level.WorldServer;
import net.minecraft.server.network.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_18_R2.CraftServer;
import org.bukkit.craftbukkit.v1_18_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class NPCManager {
    public void createNPC(Player player, String npcName) {
        Location location = player.getLocation();
        MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer nmsWorld = ((CraftWorld) player.getWorld()).getHandle();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), npcName);

        EntityPlayer npc = new EntityPlayer(nmsServer, nmsWorld, gameProfile);
        Player npcPlayer = npc.getBukkitEntity().getPlayer();
        npcPlayer.setPlayerListName("");

        npc.a(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());

        PlayerConnection connection = ((CraftPlayer) player).getHandle().b;
        /*
        connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.a, npc));
        connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
        */
    }
}