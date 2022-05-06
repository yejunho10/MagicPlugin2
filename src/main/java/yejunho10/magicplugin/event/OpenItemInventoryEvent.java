package yejunho10.magicplugin.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class OpenItemInventoryEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    public Player player;
    public OpenItemInventoryEvent(Player player) {
        this.player = player;
    }

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
