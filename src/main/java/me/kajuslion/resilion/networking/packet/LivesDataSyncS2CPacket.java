package me.kajuslion.resilion.networking.packet;

import me.kajuslion.resilion.utility.DataSaver;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;


public class LivesDataSyncS2CPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        ((DataSaver.EntityDataSaver) client.player).getPersistentData().putInt("lives", buf.readInt());
    }
}