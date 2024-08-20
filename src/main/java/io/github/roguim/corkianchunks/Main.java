package io.github.roguim.corkianchunks;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.roguim.corkianchunks.commands.ChunkTest;

public class Main implements ModInitializer {
    Logger logger = LoggerFactory.getLogger("corkianchunks");

    @Override
    public void onInitialize() {
        CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("chunktest")
                    .then(CommandManager.argument("chunkCoordX", IntegerArgumentType.integer()))
                    .then(CommandManager.argument("chunkCoordZ", IntegerArgumentType.integer()))
                    .executes(ChunkTest::executor));
        }));
    }
}