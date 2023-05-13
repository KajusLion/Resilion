package me.kajuslion.resilion;

import me.kajuslion.resilion.entity.InvisEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
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

    @Override
    public void onInitialize() {
        LOGGER.info("Resilion has loaded!");
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();

    }

}

