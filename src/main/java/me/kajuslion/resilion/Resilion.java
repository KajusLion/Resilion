package me.kajuslion.resilion;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.kajuslion.resilion.item.ModItems;



public class Resilion implements ModInitializer {

    public static final String MOD_ID = "resilion";
    public static final Logger LOGGER = LoggerFactory.getLogger("resilion");

    @Override
    public void onInitialize() {
        LOGGER.info("Resilion has loaded!");
        ModItems.registerModItems();
    }

}

