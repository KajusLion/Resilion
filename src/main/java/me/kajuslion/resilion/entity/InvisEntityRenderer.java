package me.kajuslion.resilion.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

public class InvisEntityRenderer extends MobEntityRenderer<InvisEntity, InvisEntityModel>  {
    public CubeEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new InvisEntityEntityModel(context.getPart(EntityTestingClient.MODEL_CUBE_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(CubeEntity entity) {
        return new Identifier("entitytesting", "textures/entity/cube/cube.png");
    }
}
