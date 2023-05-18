package me.kajuslion.resilion.client;

import com.mojang.blaze3d.systems.RenderSystem;
import me.kajuslion.resilion.Resilion;
import me.kajuslion.resilion.utility.DataSaver;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class LivesOverlay implements HudRenderCallback {
    private static final Identifier FULL = new Identifier(Resilion.MOD_ID,
            "textures/ui/skull_full.png");
    private static final Identifier EMPTY = new Identifier(Resilion.MOD_ID,
            "textures/ui/skull_empty.png");

    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta){
        int x = 0;
        int y = 0;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            x = width / 2;
            y = height;
        }

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, EMPTY);
        for(int i = 0; i < 10; i++) {                  // 94
            DrawableHelper.drawTexture(matrixStack,x + 10 + (i * 8),y - 49,0,0,9,9,
                    9,9);
        }

        RenderSystem.setShaderTexture(0, FULL);
        for(int i = 0; i < 10; i++) {
            if (8 > i) {
                DrawableHelper.drawTexture(matrixStack, x + 82 + (i * -8), y - 49, 0, 0, 9, 9,
                        9, 9);
            } else {
                break;
            }
        }

    }
}
