package pl.amon.moretinygates.gates.multiblock;

import java.util.HashMap;

import com.dannyandson.tinyredstone.blocks.PanelCellPos;
import com.dannyandson.tinyredstone.blocks.PanelCellSegment;
import com.dannyandson.tinyredstone.blocks.Side;

import net.minecraft.client.multiplayer.chat.LoggedChatMessage.Player;

public abstract class AbstractTinyMasterMultiblock extends AbstractTinyMultiblock {
  protected HashMap<MultiblockIO, Integer> inputs = new HashMap<>();
  protected HashMap<MultiblockIO, Integer> outputs = new HashMap<>();
  protected HashMap<Character, MultiblockIO> element_outputs = new HashMap<>();
  protected HashMap<Character, AbstractTinySlaveMultiblock> elements = new HashMap<>();
  protected Boolean valid = false;
  protected Boolean initialized = false;
  private boolean makeDirty = false;

  public abstract String getMultiblockID();
  public abstract void logic();
  public abstract boolean isValid();
  public abstract char id();
  protected abstract void updateOutputTexture(AbstractTinySlaveMultiblock slave, char id);
  protected abstract void updateMasterTexture();

  public void removeSlave(char id) {
    this.elements.remove(id);
    this.valid = false;
    this.makeInvalid();
  }

  public void makeInvalid() {
    var iterator = elements.values().iterator();

    while (iterator.hasNext()) {
      var element = iterator.next();
      element.texture = element.loadUnconnectedTexture();
      element.master = null;
    }
  }

  public int getSlaveOutput(Side side, char id) {
    return getSlaveOutput(new MultiblockIO(id, side));
  }

  public int getSlaveOutput(MultiblockIO output) {
    Integer value = outputs.get(output);
    return value == null ? 0 : value;
  }

  public void updateSlaveInputs(PanelCellPos cellPos, char id) {
    boolean changed = false;
    
    if (updateInput(cellPos, new MultiblockIO(id, Side.LEFT))) {
      changed = true;
    }

    if (updateInput(cellPos, new MultiblockIO(id, Side.RIGHT))) {
      changed = true;
    }

    if (updateInput(cellPos, new MultiblockIO(id, Side.FRONT))) {
      changed = true;
    }

    if (updateInput(cellPos, new MultiblockIO(id, Side.BACK))) {
      changed = true;
    }

    if (changed) {
      logic();
    }
  }

  public boolean updateInput(PanelCellPos cellPos, MultiblockIO input) {
    Integer value = inputs.get(input);

    if (value != null) {
      var neighbor = cellPos.getNeighbor(input.side);
      int newValue = neighbor == null ? 0 : neighbor.getWeakRsOutput();
      
      if (value != newValue) {
        inputs.replace(input, newValue);
        return true;
      }
    }

    return false;
  }


  public void getSlaveOutput(Side side, char id, int value) {
    setOutput(new MultiblockIO(id, side), value);
  }

  public boolean neighborChanged(PanelCellPos cellPos) {
    if (!valid) {
      return false;
    }

    this.updateSlaveInputs(cellPos, id());

    return false;
  }

  public void setOutput(MultiblockIO output, int value) {
    Integer actualValue = outputs.get(output);

    if (!valid || actualValue == null || actualValue == value) {
      return;
    }

    if (output.id == id()) {
      this.makeDirty = true;
      this.updateMasterTexture();
      return;
    }

    outputs.replace(output, value);
    var slave = elements.get(output.id);

    if (slave != null) {
      slave.makeDirty = true;
      updateOutputTexture(slave, output.id);
    }
  }


  public boolean onBlockActivated(PanelCellPos cellPos, PanelCellSegment segmentClicked, Player player) {
    return false;
  }

  public boolean hasActivation() {
    return true;
  }

  public boolean tick(PanelCellPos cellPos) {
    if (this.makeDirty) {
      this.makeDirty = false;
      return true;
    }

    if (!this.initialized) {
      valid = isValid();
      this.initialized = true;
    }

    return false;
  }
}

