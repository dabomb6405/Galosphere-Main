package net.orcinus.galosphere.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public class PollinatedClusterBlock extends AmethystClusterBlock {
    public static final BooleanProperty POLLINATED = BooleanProperty.create("pollinated");

    public PollinatedClusterBlock(Properties properties) {
        super(7, 3, properties);
        this.registerDefaultState(this.defaultBlockState().setValue(POLLINATED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(POLLINATED);
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, Random random) {
        if (state.getValue(POLLINATED)) {
            Direction direction = state.getValue(FACING);
            double i = pos.getX();
            double j = pos.getY();
            double k = pos.getZ();
            BlockPos.MutableBlockPos mut = new BlockPos.MutableBlockPos();
            double velX = random.nextBoolean() ? -(random.nextFloat() / 10.0F) : random.nextFloat() / 10.0F;
            double velY = random.nextBoolean() ? -(random.nextFloat() / 10.0F) : random.nextFloat() / 10.0F;
            double velZ = random.nextBoolean() ? -(random.nextFloat() / 10.0F) : random.nextFloat() / 10.0F;
            double x = i + 0.5D;
            double y = j + 0.9D;
            double z = k + 0.5D;
            mut.set(i + Mth.nextInt(random, -10, 10), j - random.nextInt(10), k + Mth.nextInt(random, -10, 10));
            BlockState blockstate = world.getBlockState(mut);
            if (!blockstate.isCollisionShapeFullBlock(world, mut)) {
                world.addParticle(ParticleTypes.END_ROD, (double) mut.getX() + direction.getStepX() + random.nextDouble(), (double) mut.getY() + direction.getStepY() + random.nextDouble(), (double) mut.getZ() + direction.getStepZ() + random.nextDouble(), velX, velY, velZ);
            }
            if (random.nextInt(5) == 0) {
                world.addParticle(ParticleTypes.END_ROD, x + direction.getStepX(), y + direction.getStepY(), z + direction.getStepZ(), velX, velY, velZ);
            }
        }
    }
}
