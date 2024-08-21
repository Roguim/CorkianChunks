package io.github.roguim.corkianchunks;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.fabric.api.client.command.v2.*;

import io.github.roguim.corkianchunks.commands.ChunkTest;

public class Main implements ModInitializer {
    Logger logger = LoggerFactory.getLogger("corkianchunks");

    @Override
    public void onInitialize() {
        ClientCommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("chunktest")
                    .then(ClientCommandManager.argument("chunkCoordX", IntegerArgumentType.integer())
                            .then(ClientCommandManager.argument("chunkCoordZ", IntegerArgumentType.integer())
                                    .executes(ChunkTest::executor))));
        }));
    }
}