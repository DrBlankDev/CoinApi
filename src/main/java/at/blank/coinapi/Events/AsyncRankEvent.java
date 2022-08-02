package at.blank.coinapi.Events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AsyncRankEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public AsyncRankEvent() {
        super(true);
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
