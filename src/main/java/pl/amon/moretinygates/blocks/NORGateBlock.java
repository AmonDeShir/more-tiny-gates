package pl.amon.moretinygates.blocks;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;

public class NORGateBlock extends GateBlock {
  public NORGateBlock(DeferredRegister<Item> ITEMS, DeferredRegister<Block> BLOCKS, DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES) {
    super("nor_gate", ITEMS, BLOCKS, BLOCK_ENTITIES);
  }

  @Override
  public int logic(int a, int b) {
    return a == 0 && b == 0 ? 15 : 0;
  }
}