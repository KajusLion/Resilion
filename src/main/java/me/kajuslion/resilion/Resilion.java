package me.kajuslion.resilion;

import me.kajuslion.resilion.entity.InvisEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
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


    public static final EntityType<InvisEntity> CUBE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier("resilion", "cube"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, InvisEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );

    @Override
    public void onInitialize() {
        LOGGER.info("Resilion has loaded!");
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        FabricDefaultAttributeRegistry.register(CUBE, InvisEntity.createMobAttributes());

    }

}

