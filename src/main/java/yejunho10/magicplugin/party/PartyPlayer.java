package yejunho10.magicplugin.party;

import org.bukkit.entity.Player;

import static yejunho10.magicplugin.GUIPlugin.ppMap;

public class PartyPlayer {
    private int partyId;

    private PartyPlayer(Player p) {
        partyId = -1;
        ppMap.put(p.getName(), this);
    }

    public static void registerPartyPlayer(Player p) {
        if (!ppMap.containsKey(p.getName()))
            new PartyPlayer(p);
    }

    public static PartyPlayer getPartyPlayer(Player p) {
        return ppMap.containsKey(p.getName()) ? ppMap.get(p.getName()) : new PartyPlayer(p);
    }

    public int getPartyId() {
        return partyId;
    }

    public boolean hasParty() {
        return partyId != -1;
    }

    public void joinParty(int partyId) {
        this.partyId = partyId;
    }

    public void leaveParty() {
        partyId = -1;
    }
}
