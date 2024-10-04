package com.shanebeestudios.coreapi.listener;

import com.shanebeestudios.coreapi.event.PacketEvent;
import com.shanebeestudios.coreapi.event.PacketInboundEvent;
import com.shanebeestudios.coreapi.event.PacketOutboundEvent;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerPacketListener implements Listener {

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        Player bukkitPlayer = event.getPlayer();
        ServerPlayer serverPlayer = ((CraftPlayer) bukkitPlayer).getHandle();

        ChannelDuplexHandler handler = new ChannelDuplexHandler() {
            @Override
            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                if (msg instanceof Packet<?> packet) {
                    PacketEvent packetEvent = new PacketOutboundEvent(packet, bukkitPlayer);
                    packetEvent.callEvent();
                    if (packetEvent.isCancelled()) return;
                }
                super.write(ctx, msg, promise);
            }

            @Override
            public void channelRead(@NotNull ChannelHandlerContext ctx, @NotNull Object msg) throws Exception {
                if (msg instanceof Packet<?> packet) {
                    PacketEvent packetEvent = new PacketInboundEvent(packet, bukkitPlayer);
                    packetEvent.callEvent();
                    if (packetEvent.isCancelled()) return;
                }
                super.channelRead(ctx, msg);
            }
        };
        serverPlayer.connection.connection.channel.pipeline().addBefore("packet_handler", bukkitPlayer.getName(), handler);
    }

}
