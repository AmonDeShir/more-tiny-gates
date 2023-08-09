package pl.amon.moretinygates.setup;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import pl.amon.moretinygates.MoreTinyGates;

@Mod.EventBusSubscriber(modid = MoreTinyGates.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
  public static void init(final FMLClientSetupEvent event) {
  
  }
}
