package me.kajuslion.resilion;

import me.kajuslion.resilion.entity.InvisEntity;
import me.kajuslion.resilion.entity.InvisEntityRenderer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.kajuslion.resilion.item.ModItems;
import me.kajuslion.resilion.block.ModBlocks;



public class Resilion implements ModInitializer {

    public static final String MOD_ID = "resilion";
    public static final Logger LOGGER = LoggerFactory.getLogger("resilion");


    public static final EntityType<InvisEntity> INVIS = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("resilion", "sit_entity"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, InvisEntity::new).dimensions(EntityDimensions.fixed(0f, 0f)).build()
    );

    public static final EntityModelLayer MODEL_CUBE_LAYER = new EntityModelLayer(new Identifier("entitytesting", "cube"), "main");

    @Override
    public void onInitialize() {
        LOGGER.info("Resilion has loaded!");
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        //Put this on client. I only put this here for demonstration. Will crash on server otherwise :)
        EntityRendererRegistry.register(INVIS, InvisEntityRenderer::new);


        //This is not needed.
        //FabricDefaultAttributeRegistry.register(CUBE, InvisEntity.createMobAttributes());

    }

}

