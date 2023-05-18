package me.kajuslion.resilion.utility;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
//import net.kaupenjoe.tutorialmod.networking.ModMessages;
import me.kajuslion.resilion.networking.ModMessages;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class LivesData {
    public static int setLives(DataSaver.EntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int lives = nbt.getInt("lives");
        if(amount >= 10) {
            lives = amount;
        }

        nbt.putInt("lives", lives);
        syncLives(lives, (ServerPlayerEntity) player);
        return lives;
    }



    public static void syncLives(int lives, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(lives);
        ServerPlayNetworking.send(player, ModMessages.LIVES_SYNC_ID, buffer);
    }
}


/*
    public static int removeLives(DataSaver.EntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int lives = nbt.getInt("lives");
        if(lives - amount < 0) {
            lives = 0;
        } else {
            lives -= amount;
        }

        nbt.putInt("lives", lives);
        syncLives(lives, (ServerPlayerEntity) player);
        return lives;
    }



    public static int addLives(DataSaver.EntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int lives = nbt.getInt("lives");
        if(lives + amount >= 10) {
            lives = 10;
        } else {
            lives += amount;
        }

        nbt.putInt("lives", lives);
        syncLives(lives, (ServerPlayerEntity) player);
        return lives;
    }
 */