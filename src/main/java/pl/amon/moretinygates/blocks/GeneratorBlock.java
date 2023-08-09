package pl.amon.moretinygates.blocks;

import com.dannyandson.tinygates.blocks.AbstractGateBlock;
import com.dannyandson.tinygates.blocks.AbstractGateBlockEntity;
import com.dannyandson.tinygates.blocks.Side;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class GeneratorBlock extends AbstractGateBlock {
  public GeneratorBlock() {
    super();
  }

  @Override
  protected AbstractGateBlockEntity newAbstractGateBlockEntity(BlockPos pos, BlockState state) {
      return new GeneratorBlockEntity(pos, state);
  }

  @Override
  public boolean canConnectRedstone(Side side) {
    return side == Side.BACK || side == Side.FRONT;
  }

  /** @deprecated */
  @Deprecated
  public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
    if (level.isClientSide) {
      return InteractionResult.SUCCESS;
    }
    
    BlockEntity entity = level.getBlockEntity(blockPos);
    
    if (entity instanceof GeneratorBlockEntity generatorEntity) {
      generatorEntity.onActivated();
    }

    return InteractionResult.CONSUME;
  }
}