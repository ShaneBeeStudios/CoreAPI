package com.shanebeestudios.coreapi.event;

import net.minecraft.network.protocol.Packet;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

@SuppressWarnings("unused")
public abstract class PacketEvent extends Event implements Cancellable {

    private final Packet<?> packet;
    private final Player player;
    private boolean cancelled = false;

    public PacketEvent(Packet<?> packet, Player player) {
        super(true);
        this.packet = packet;
        this.player = player;
    }

    public Packet<?> getPacket() {
        return this.packet;
    }

    public Player getPlayer() {
        return this.player;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

}
