package yejunho10.magicplugin.etc;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class tpaData {
    Player f, t;
    Location loc;

    public tpaData(Player sender, Player target, Location where) {
        this.f = sender;
        this.t = target;
        this.loc = where;
    }

    public Player getSender() {
        return this.f;
    }

    public Player getTarget() {
        return this.t;
    }

    public Location getLocation() {
        return this.loc;
    }
}
