package pl.amon.moretinygates.blocks;

import javax.annotation.Nullable;

import com.dannyandson.tinygates.blocks.AbstractGateBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import pl.amon.moretinygates.gates.Generator;
import pl.amon.moretinygates.setup.Registration;

public class GeneratorBlockEntity extends AbstractGateBlockEntity {
  boolean powerOn = true;
  int level = 0;

  public GeneratorBlockEntity(BlockPos pos, BlockState state) {
    super(Registration.GENERATOR_BLOCK_ENTITY.get(), pos, state);
  }

  @Override
  public ResourceLocation getTexture() {
    if (this.powerOn) {
      return Generator.TEXTURE_ON[level];
    }
    
    return Generator.TEXTURE_OFF[level];
  }

  /**
   * Respond to neighbor change
   *
   * @param neighbor The block position of the neighbor that changed
   * @return true if the output changed
   */
  @Override
  public boolean onNeighborChange(@Nullable BlockPos neighbor) {
    Direction backDirection = getDirectionFromSide(com.dannyandson.tinygates.blocks.Side.BACK);
    int back = getLevel().getSignal(getBlockPos().relative(backDirection), backDirection);

    boolean powerOn = back == 0;

    if (powerOn != this.powerOn) {
      this.powerOn = powerOn;
      this.output = this.powerOn ? this.level : 0;
      
      return true;
    }

    return false;
  }

  @Override
  protected void saveAdditional(CompoundTag nbt) {
    super.saveAdditional(nbt);
    
    nbt.putBoolean("powerOn", this.powerOn);
    nbt.putInt("level", this.level);
  }
  
  @Override
  public void load(CompoundTag compoundTag) {
    super.load(compoundTag);

    this.powerOn = compoundTag.getBoolean("powerOn");
    this.level = compoundTag.getInt("level");
  }

  public void onActivated() {
    this.level += 1;
    
    if (this.level >= 16) {
      this.level = 0;
    }

    if (this.powerOn) {
      this.output = this.level;
    }

    this.outputChange();
  }
}