package pl.amon.moretinygates.gates;

import com.dannyandson.tinyredstone.blocks.PanelCellVoxelShape;
import com.dannyandson.tinyredstone.blocks.PanelTile;
import com.dannyandson.tinyredstone.blocks.PosInPanelCell;
import com.dannyandson.tinyredstone.blocks.Side;

import net.minecraft.nbt.CompoundTag;

import com.dannyandson.tinyredstone.api.IOverlayBlockInfo;
import com.dannyandson.tinyredstone.api.IPanelCell;
import com.dannyandson.tinyredstone.api.IPanelCellInfoProvider;

public abstract class AbstractAnalogElement implements IPanelCell, IPanelCellInfoProvider {
  protected int output = 0;

  public AbstractAnalogElement() {
  }

  public int getWeakRsOutput(Side side) {
    return side == Side.FRONT ? output : 0;
  }

  public int getStrongRsOutput(Side side) {
    return this.getWeakRsOutput(side);
  }

  public boolean needsSolidBase() {
    return true;
  }

  public boolean canAttachToBaseOnSide(Side side) {
    return side == Side.BOTTOM;
  }

  public Side getBaseSide() {
    return Side.BOTTOM;
  }

  public CompoundTag writeNBT() {
    CompoundTag compoundTag = new CompoundTag();
    compoundTag.putInt("output", this.output);
    return compoundTag;
  }

  public void readNBT(CompoundTag compoundTag) {
    this.output = compoundTag.getInt("output");
  }

  public PanelCellVoxelShape getShape() {
    return PanelCellVoxelShape.QUARTERCELLSLAB;
  }

  public void addInfo(IOverlayBlockInfo overlayBlockInfo, PanelTile panelTile, PosInPanelCell posInPanelCell) {
    overlayBlockInfo.addText("Output", Integer.valueOf(this.output).toString());
    overlayBlockInfo.setPowerOutput(0);
  }
}
