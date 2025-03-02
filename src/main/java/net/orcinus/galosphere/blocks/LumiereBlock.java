package net.orcinus.galosphere.blocks;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AmethystBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.orcinus.galosphere.init.GBlocks;

import java.util.Map;
import java.util.Random;

public class LumiereBlock extends AmethystBlock {
    private static final Map<Block, Block> BLOCK_REFORMING_MAP = Util.make(Maps.newHashMap(), map -> {
        map.put(Blocks.GRANITE, Blocks.RED_SAND);
        map.put(Blocks.RED_SAND, Blocks.SAND);
        map.put(Blocks.COBBLESTONE, Blocks.GRAVEL);
    });
    private final boolean charged;

    public LumiereBlock(boolean charged, Properties properties) {
        super(properties);
        this.charged = charged;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return this.charged;
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter level, BlockPos pos) {
        return this.charged ? 6 : 0;
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, Random random) {
        BlockPos abovePos = pos.above();
        if (this.charged) {
            for (Block block : BLOCK_REFORMING_MAP.keySet()) {
                if (world.getBlockState(abovePos).is(block)) {
                    world.playSound(null, pos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.75F, 1.4F);
                    world.setBlock(pos, GBlocks.LUMIERE_BLOCK.get().defaultBlockState(), 2);
                    if (world.getBlockState(abovePos).isCollisionShapeFullBlock(world, abovePos)) {
                        world.levelEvent(2009, abovePos, 0);
                    }
                    BlockState convertedState = BLOCK_REFORMING_MAP.get(block).defaultBlockState();
                    for (Direction direction : Direction.Plane.HORIZONTAL) {
                        BlockPos dirPos = pos.below().relative(direction);
                        if (world.getBlockState(dirPos).isCollisionShapeFullBlock(world, dirPos)) {
                            world.levelEvent(2009, dirPos, 0);
                        }
                    }
                    world.setBlock(abovePos, convertedState, 2);
                    break;
                }
            }
        }
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (this.charged) {
            world.scheduleTick(pos, this, 120);
        }
    }
}
