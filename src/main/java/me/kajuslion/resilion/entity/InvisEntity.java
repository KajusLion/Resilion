package me.kajuslion.resilion.entity;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class InvisEntity extends LivingEntity {
    public InvisEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }
}
