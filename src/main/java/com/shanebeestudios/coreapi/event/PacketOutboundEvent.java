package com.shanebeestudios.coreapi.event;

import com.shanebeestudios.coreapi.listener.PlayerPacketListener;
import net.minecraft.network.protocol.Packet;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * Called when a packet is sent to a client
 * <p>To listen to this event you will first need to execute {@link PlayerPacketListener#registerListener(Plugin)}</p>
 */
@SuppressWarnings("unused")
public class PacketOutboundEvent extends PacketEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    /**
     * Why do I need to put a description? It's hidden
     *
     * @param packet packet
     * @param player player
     * @hidden no
     */
    public PacketOutboundEvent(Packet<?> packet, Player player) {
        super(packet, player);
    }

    /**
     * Get the handler list
     *
     * @return Handler list
     */
    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    /**
     * Get the handler list
     *
     * @return Handler list
     */
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}
