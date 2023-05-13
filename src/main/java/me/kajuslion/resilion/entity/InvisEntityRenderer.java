package me.kajuslion.resilion.entity;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class InvisEntityRenderer extends EntityRenderer<InvisEntity> {
    public InvisEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    //No texture; We dont need one.
    @Override
    public Identifier getTexture(InvisEntity entity) {
        return null;
    }

    //Override this for rendering. But we are not rendering anything so leave it empty
    @Override
    public void render(InvisEntity entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {

    }
}
