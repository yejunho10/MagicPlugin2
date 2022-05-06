package yejunho10.magicplugin.party;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        PartyPlayer.registerPartyPlayer(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        PartyPlayer pp = PartyPlayer.getPartyPlayer(p);

        if (pp.hasParty()) {
            Party party = Party.getParty(pp.getPartyId());
            if (party.getPlayers().size() == 1) {
                party.removeParty();
                pp.leaveParty();
            } else {
                if (party.isMaster(p)){
                    party.removePlayer(p);
                    party.changeMaster();
                    party.sendMessage(p.getName() + " 님이 파티를 떠나 파티장이 " + party.getMasterPlayer().getName() + "님으로 변경되었습니다.");
                } else {
                    party.sendMessage(p.getName() + " 님이 접속을 종료해, 파티에서 떠났습니다.");
                }
                pp.leaveParty();
            }
        }
    }
}
