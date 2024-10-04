package com.shanebeestudios.coreapi.event;

import net.minecraft.network.protocol.Packet;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

/**
 * Abstract class for packet events
 */
@SuppressWarnings("unused")
public abstract class PacketEvent extends Event implements Cancellable {

    private final Packet<?> packet;
    private final Player player;
    private boolean cancelled = false;

    PacketEvent(Packet<?> packet, Player player) {
        super(true);
        this.packet = packet;
        this.player = player;
    }

    /**
     * Get the packet from the event
     *
     * @return Packet from event
     */
    public Packet<?> getPacket() {
        return this.packet;
    }

    /**
     * Get the player that sent/received the packet
     *
     * @return Player that sent/received packet
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Check if event was cancelled
     *
     * @return True if cancelled
     */
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * Cancel the event
     *
     * @param cancel Whether to cancel this event
     */
    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

}
