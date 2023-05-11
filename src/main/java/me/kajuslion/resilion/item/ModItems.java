package me.kajuslion.resilion.item;

import me.kajuslion.resilion.Resilion;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item KAJUS_TOTEM = registerItem("kajus_totem",
            new Item(new FabricItemSettings().maxCount(1).group(ModItemGroup.RESILION).rarity(Rarity.RARE)));



    //public static final Item KAJUS_TOTEM = registerItem("totem_of_undying",
    //        new Item(new FabricItemSettings().maxCount(1).group(ItemGroup.COMBAT).rarity(Rarity.RARE)));

    //public static final Item MYTHRIL_NUGGET = registerItem("mythril_nugget",
    //        new Item(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1)));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Resilion.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Resilion.LOGGER.info("Registering Mod Items for " + Resilion.MOD_ID);
    }
}