package pl.amon.moretinygates.blocks;

import java.util.function.Supplier;
import javax.annotation.Nullable;

import com.dannyandson.tinygates.blocks.AbstractGateBlock;
import com.dannyandson.tinygates.blocks.AbstractGateBlockEntity;
import com.dannyandson.tinygates.blocks.GateBlockRenderer;
import com.dannyandson.tinygates.blocks.Side;
import com.dannyandson.tinygates.items.GateBlockItem;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import pl.amon.moretinygates.MoreTinyGates;


public abstract class GateBlock {
  public abstract int logic(int a, int b);
  
  public RegistryObject<VariantBlock> block;
  public RegistryObject<Item> item;
  public RegistryObject<BlockEntityType<VariantBlockEntity>> entity;

  public GateBlock(String name, DeferredRegister<Item> ITEMS, DeferredRegister<Block> BLOCKS, DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES) {
    block = BLOCKS.register(name + "_block", () -> new VariantBlock(name));
    item = ITEMS.register(name + "_item", () -> new GateBlockItem(block.get()));
    entity = BLOCK_ENTITIES.register(name + "_block", this.createSupplier(name, block));
  }
  
  public int runLogicOnSites(int left, int right, int back, int front) {
    return logic(left, right);
  }

  public void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
    event.registerBlockEntityRenderer(entity.get(), GateBlockRenderer::new);
  }

  public Supplier<? extends BlockEntityType<VariantBlockEntity>> createSupplier(String name, RegistryObject<VariantBlock> block) {
    return () -> BlockEntityType.Builder.of((BlockPos pos, BlockState state) -> new VariantBlockEntity(pos, state, name), block.get()).build(null);
  }

  public static ResourceLocation getOnTexture(String name) {
    return new ResourceLocation(MoreTinyGates.MODID, "block/" + name + "_on");
  } 

  public static ResourceLocation getOffTexture(String name) {
    return new ResourceLocation(MoreTinyGates.MODID, "block/" + name + "_off");
  }

  public class VariantBlock extends AbstractGateBlock {
    String name;

    public VariantBlock(String name) {
      super();
      this.name = name;
    }

    @Override
    protected AbstractGateBlockEntity newAbstractGateBlockEntity(BlockPos pos, BlockState state) {
      return new VariantBlockEntity(pos, state, name);
    }

    @Override
    public boolean canConnectRedstone(Side side) {
      return side == Side.LEFT || side == Side.RIGHT || side == Side.FRONT;
    }
  }

  public class VariantBlockEntity extends AbstractGateBlockEntity {
    String name;

    public VariantBlockEntity(BlockPos pos, BlockState state, String name) {
      super(entity.get(), pos, state);
      this.name = name;
    }

    @Override
    public ResourceLocation getTexture() {
      if (this.output > 0)
        return getOnTexture(name);
      return getOffTexture(name);
    }

    /**
     * Respond to neighbor change
     *
     * @param neighbor The block position of the neighbor that changed
     * @return true if the output changed
     */
    @Override
    public boolean onNeighborChange(@Nullable BlockPos neighbor) {
      Direction leftDirection = getDirectionFromSide(com.dannyandson.tinygates.blocks.Side.LEFT);
      Direction rightDirection = getDirectionFromSide(com.dannyandson.tinygates.blocks.Side.RIGHT);
      Direction backDirection = getDirectionFromSide(com.dannyandson.tinygates.blocks.Side.BACK);
      Direction frontDirection = getDirectionFromSide(com.dannyandson.tinygates.blocks.Side.FRONT);

      int left = getLevel().getSignal(getBlockPos().relative(leftDirection), leftDirection);
      int right = getLevel().getSignal(getBlockPos().relative(rightDirection), rightDirection);
      int back = getLevel().getSignal(getBlockPos().relative(backDirection), backDirection);
      int front = getLevel().getSignal(getBlockPos().relative(frontDirection), frontDirection);

      int output = runLogicOnSites(left, right, back, front);

      if (output != this.output) {
        this.output = output;
        return true;
      }

      return false;
    }
  }
}