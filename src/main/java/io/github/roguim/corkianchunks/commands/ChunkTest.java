package io.github.roguim.corkianchunks.commands;

import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public class ChunkTest {
    /**
     * @param context:
     *               Arguments -
     *                  chunkCoordX - Int
     *                  chunkCoordZ - int
     * @return int
     */
    public static int executor(CommandContext<FabricClientCommandSource> context) {
        int multiplier = 3;

        int x = IntegerArgumentType.getInteger(context, "chunkCoordX");
        int z = IntegerArgumentType.getInteger(context, "chunkCoordZ");
        World world = context.getSource().getWorld();

        // chunk = 0,0 xNthBound = 0, xSthBound = 47
        // chunk = 1,1 xNthBound = 48, xSthBound = 95
        // chunk = -1,-1 xEastBound = -48, xWestBound = -1
        double xEastBound = ((x + 1) * 16 * multiplier) - 1;
        double xWestBound = ((x + 1) * 16 * multiplier) - 48;
        double zNthBound = ((x + 1) * 16 * multiplier) - 48;
        double zSthBound = ((x + 1) * 16 * multiplier) - 1;

        double y = context.getSource().getPosition().y;

        // East Wall
        Logger logger = LoggerFactory.getLogger("corkianchunks");
        world.addParticle(ParticleTypes.FALLING_OBSIDIAN_TEAR, true, xEastBound, y, zNthBound, 0, -6 * multiplier, 16 * multiplier);
        context.getSource().sendFeedback(Text.literal("%s %s %s input. Calc: %s %s".formatted(x,y,z,xEastBound,zNthBound)));
        return 1;
    }
}
