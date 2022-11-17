package pl.amon.moretinygates.setup;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import pl.amon.moretinygates.MoreTinyGates;

@Mod.EventBusSubscriber(modid = MoreTinyGates.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {
  public static final CreativeModeTab ITEM_GROUP = com.dannyandson.tinygates.setup.ModSetup.ITEM_GROUP;

  public static void init(final FMLCommonSetupEvent event) {
  }
}
