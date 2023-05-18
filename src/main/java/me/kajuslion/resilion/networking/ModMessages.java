package me.kajuslion.resilion.networking;


import me.kajuslion.resilion.Resilion;
import me.kajuslion.resilion.networking.packet.LivesDataSyncS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {
    //public static final Identifier DRINKING_ID = new Identifier(TutorialMod.MOD_ID, "drinking");
    public static final Identifier LIVES_SYNC_ID = new Identifier(Resilion.MOD_ID, "lives");
    //public static final Identifier EXAMPLE_ID = new Identifier(TutorialMod.MOD_ID, "example");

    public static void registerC2SPackets() {
        //ServerPlayNetworking.registerGlobalReceiver(EXAMPLE_ID, ExampleC2SPacket::receive);
        //ServerPlayNetworking.registerGlobalReceiver(DRINKING_ID, DrinkingC2SPacket::receive);
    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(LIVES_SYNC_ID, LivesDataSyncS2CPacket::receive);
    }
}