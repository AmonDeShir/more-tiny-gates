package pl.amon.moretinygates.gates.multiblock;

import java.util.Objects;

import com.dannyandson.tinyredstone.blocks.Side;

public class MultiblockIO {
  public char id;
  public Side side;

  public MultiblockIO(char id, Side side) {
    this.id = id;
    this.side = side;
  }

  @Override
  public int hashCode() {
      return Objects.hash(id, side);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
        return false;
    }
    
    MultiblockIO other = (MultiblockIO) obj;
    return id == other.id && side == other.side;
  }
}
