package me.kajuslion.resilion.item;

import me.kajuslion.resilion.Resilion;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup RESILION = FabricItemGroupBuilder.build(new Identifier(Resilion.MOD_ID, "resilion"),
            () -> new ItemStack(ModItems.KAJUS_TOTEM));
}