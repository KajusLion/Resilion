package me.kajuslion.resilion.block;

import me.kajuslion.resilion.Resilion;
import me.kajuslion.resilion.item.ModItemGroup;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static final Block OAK_TABLE = registerBlock("oak_table",
            new Block(FabricBlockSettings.of(Material.WOOD).strength(4f).requiresTool()), ModItemGroup.RESILION);
    public static final Block OAK_CHAIR = registerBlock("oak_chair",
            new Block(FabricBlockSettings.of(Material.WOOD).strength(4f).requiresTool()), ModItemGroup.RESILION);




    private static Block registerBlock(String name, Block block, ItemGroup tab) {
        registerBlockItem(name, block, tab);
        return Registry.register(Registry.BLOCK, new Identifier(Resilion.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block, ItemGroup tab) {
        Registry.register(Registry.ITEM, new Identifier(Resilion.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(tab)));
    }

    public static void registerModBlocks() {
        Resilion.LOGGER.debug("Registering ModBlocks for " + Resilion.MOD_ID);
    }
}