package com.shanebeestudios.coreapi.event;

import net.minecraft.network.protocol.Packet;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PacketInboundEvent extends PacketEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    public PacketInboundEvent(Packet<?> packet, Player player) {
        super(packet, player);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}
