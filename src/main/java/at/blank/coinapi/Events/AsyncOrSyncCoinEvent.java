package at.blank.coinapi.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AsyncOrSyncCoinEvent extends Event {

    private final Player player;
    private final double newAmount;

    public AsyncOrSyncCoinEvent(Player player, double newAmount, boolean async) {
        super(async);
        this.player = player;
        this.newAmount = newAmount;
    }

    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getTarget() {
        return player;
    }

    public double getNewAmount() {
        return newAmount;
    }
}
