/*package me.kajuslion.resilion.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import me.kajuslion.resilion.utility.DataSaver;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static net.minecraft.command.argument.EntityArgumentType.getPlayer;
import static net.minecraft.server.command.CommandManager.*;
import static me.kajuslion.resilion.utility.LivesData.*;
public class LivesCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher){
        dispatcher.register(literal("setlives")
                .requires(source -> source.hasPermissionLevel(4))

                                .then(argument("player", EntityArgumentType.player())
                                .then(argument("lives", IntegerArgumentType.integer())

                .executes(ctx -> setlives(ctx.getSource(), getPlayer(ctx, "player"), getInteger(ctx,"lives") )))));
    }


    public static int setlives(ServerCommandSource source, ServerPlayerEntity player, Integer lives) {

        setLives((DataSaver.EntityDataSaver) player, lives);

        return Command.SINGLE_SUCCESS;
    }
    }
*/