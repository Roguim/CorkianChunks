package io.github.roguim.corkianchunks.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.World;

public class ChunkTest {
    /**
     * @param context:
     *               Arguments -
     *                  chunkCoordX - Int
     *                  chunkCoordZ - int
     * @return int
     */
    public static int executor(CommandContext<ServerCommandSource> context) {
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
        world.addParticle(ParticleTypes.FALLING_OBSIDIAN_TEAR, xEastBound, y, zNthBound, 0, 16 * multiplier, 16 * multiplier);
        return 1;
    }
}
