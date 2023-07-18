package pl.amon.moretinygates.gates.multiblock;

import com.dannyandson.tinyredstone.api.IPanelCell;
import com.dannyandson.tinyredstone.api.IPanelCellInfoProvider;
import com.dannyandson.tinyredstone.blocks.PanelCellVoxelShape;
import com.dannyandson.tinyredstone.blocks.Side;

import net.minecraft.nbt.CompoundTag;

public abstract class AbstractTinyMultiblock implements IMultiblock, IPanelCell, IPanelCellInfoProvider {
  public PanelCellVoxelShape getShape() {
    return PanelCellVoxelShape.QUARTERCELLSLAB;
  }

  public CompoundTag writeNBT() {
    CompoundTag compoundTag = new CompoundTag();
    return compoundTag;
  }

  public void readNBT(CompoundTag compoundTag) {}

  public Side getBaseSide() {
    return Side.BOTTOM;
  }
  
  public boolean canAttachToBaseOnSide(Side side) {
    return side == Side.BOTTOM;
  }

  public int getStrongRsOutput(Side side) {
    return this.getWeakRsOutput(side);
  }

  public boolean needsSolidBase() {
    return true;
  }
}
