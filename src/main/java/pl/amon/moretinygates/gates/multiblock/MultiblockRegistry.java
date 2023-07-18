package pl.amon.moretinygates.gates.multiblock;

import java.util.HashMap;
import java.util.Map;

import com.dannyandson.tinyredstone.TinyRedstone;
import net.minecraft.world.item.Item;

public class MultiblockRegistry {
  private static final Map<String, Class<? extends AbstractTinyMultiblock>> multiblockPanelCell = new HashMap<>();
  private static final Map<String, Item> multiblockItem = new HashMap<>();

  public static void registerMultiblock(String id, Class<? extends AbstractTinyMultiblock> multiblock, Item item) {
    TinyRedstone.registerPanelCell(multiblock, item);
    multiblockPanelCell.put(id, multiblock);
    multiblockItem.put(id, item);
  }

  public static Item getMultiblockItem(String id) {
    return multiblockItem.get(id);
  }

  public static Class<? extends AbstractTinyMultiblock> getMultiblockClass(String id) {
    return multiblockPanelCell.get(id);
  }
}
