package me.kajuslion.resilion.client;

import net.minecraft.entity.player.PlayerEntity;

public class LivesData {
    private static int playerLives;

    public static void set(int Lives){
        LivesData.playerLives = Lives;
    }
    public static int getPlayerLives(PlayerEntity player){
        return playerLives;
    }
}
