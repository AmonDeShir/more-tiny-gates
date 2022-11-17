package pl.amon.moretinygates.setup;

import net.minecraft.world.item.Item;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import pl.amon.moretinygates.MoreTinyGates;

public class Registration {
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MoreTinyGates.MODID);

  public static void register() {
    ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
  }

  public static void registerPanelCells(){
  
  }
}
