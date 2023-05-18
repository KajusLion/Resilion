package me.kajuslion.resilion;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import me.kajuslion.resilion.entity.InvisEntity;
import me.kajuslion.resilion.networking.ModMessages;
import me.kajuslion.resilion.utility.DataSaver;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.kajuslion.resilion.item.ModItems;
import me.kajuslion.resilion.block.ModBlocks;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static me.kajuslion.resilion.utility.LivesData.setLives;
import static net.minecraft.command.argument.EntityArgumentType.getPlayer;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

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
        ModMessages.registerC2SPackets();
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("setlives")
                .requires(source -> source.hasPermissionLevel(4))

                .then(argument("player", EntityArgumentType.player())
                .then(argument("lives", IntegerArgumentType.integer())


                .executes(ctx -> {
                    try {
                        setLives((DataSaver.EntityDataSaver) getPlayer(ctx, "player"), getInteger(ctx,"lives"));
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }


                    ctx.getSource().sendMessage(Text.literal(
                            "Called /setlives for " + getPlayer(ctx, "player") + " to give " + getInteger(ctx,"lives") + " lives!"
                    ));
                    return 1;
                })))));
                                //setLives((DataSaver.EntityDataSaver) player, lives);

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("foo")
                .executes(context -> {
                    // For versions below 1.19, replace "Text.literal" with "new LiteralText".
                    context.getSource().sendMessage(Text.literal("Called /foo with no arguments"));

                    return 1;
                })));



    }


}


