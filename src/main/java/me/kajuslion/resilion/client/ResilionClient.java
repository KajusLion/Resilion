package me.kajuslion.resilion.client;

import me.kajuslion.resilion.entity.InvisEntityRenderer;
import me.kajuslion.resilion.networking.ModMessages;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

import static me.kajuslion.resilion.Resilion.INVIS;

public class ResilionClient implements ClientModInitializer {
    @Override
    public void onInitializeClient(){
        EntityRendererRegistry.register(INVIS, InvisEntityRenderer::new);
        HudRenderCallback.EVENT.register(new LivesOverlay());
        ModMessages.registerC2SPackets();

    }
}
