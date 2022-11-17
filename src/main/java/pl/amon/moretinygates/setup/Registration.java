package pl.amon.moretinygates.setup;

import com.dannyandson.tinygates.items.PanelCellGateItem;
import com.dannyandson.tinyredstone.TinyRedstone;
import pl.amon.moretinygates.gates.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import pl.amon.moretinygates.MoreTinyGates;

public class Registration {
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MoreTinyGates.MODID);

  public static final RegistryObject<Item> TINY_NAND_GATE_ITEM = ITEMS.register("tiny_nand_gate", PanelCellGateItem::new);
  //public static final RegistryObject<Item> TINY_NOR_GATE_ITEM = ITEMS.register("tiny_nor_gate", PanelCellGateItem::new);
  //public static final RegistryObject<Item> TINY_XOR_GATE_ITEM = ITEMS.register("tiny_xnor_gate", PanelCellGateItem::new);
  
  public static void register() {
    ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
  }

  public static void registerPanelCells(){
    TinyRedstone.registerPanelCell(NANDGate.class, TINY_NAND_GATE_ITEM.get());
    //TinyRedstone.registerPanelCell(ORGate.class, TINY_NOR_GATE_ITEM.get());
    //TinyRedstone.registerPanelCell(XORGate.class, TINY_XOR_GATE_ITEM.get());
  }
}
