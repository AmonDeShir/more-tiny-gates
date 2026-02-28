package pl.amon.moretinygates.blocks;

import com.dannyandson.tinygates.blocks.AbstractGateBlock;
import com.dannyandson.tinygates.blocks.AbstractGateBlockEntity;
import com.dannyandson.tinygates.blocks.Side;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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

  @Override
  protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
    if (level.isClientSide) {
      return InteractionResult.SUCCESS;
    }

    BlockEntity entity = level.getBlockEntity(pos);

    if (entity instanceof GeneratorBlockEntity generatorEntity) {
      generatorEntity.onActivated();
    }

    return InteractionResult.CONSUME;
  }

  @Override
  protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
    if (level.isClientSide) {
      return ItemInteractionResult.SUCCESS;
    }

    BlockEntity entity = level.getBlockEntity(pos);

    if (entity instanceof GeneratorBlockEntity generatorEntity) {
      generatorEntity.onActivated();
    }

    return ItemInteractionResult.CONSUME;
  }
}
