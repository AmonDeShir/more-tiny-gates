package pl.amon.moretinygates.blocks;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;

public class DiodeBlock extends GateBlock {
  public DiodeBlock(DeferredRegister<Item> ITEMS, DeferredRegister<Block> BLOCKS, DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES) {
    super("diode", ITEMS, BLOCKS, BLOCK_ENTITIES);
  }

  @Override
  public int runLogicOnSites(int left, int right, int back, int front) {
    return logic(back, right);
  }
  
  @Override
  public int logic(int a, int b) {
    return Math.max(0, a - 1);
  }
}