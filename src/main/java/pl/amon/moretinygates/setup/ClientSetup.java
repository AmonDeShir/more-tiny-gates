package pl.amon.moretinygates.setup;

import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import pl.amon.moretinygates.MoreTinyGates;
import pl.amon.moretinygates.gates.*;

@Mod.EventBusSubscriber(modid = MoreTinyGates.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
  public static void init(final FMLClientSetupEvent event) {
  
  }

  @SuppressWarnings("unused")
  @SubscribeEvent
  public static void onTextureStitch(TextureStitchEvent.Pre event) {
    if (!event.getAtlas().location().equals(InventoryMenu.BLOCK_ATLAS)) {
      return;
    }

    event.addSprite(NANDGate.TEXTURE_NAND_GATE_ON);
    event.addSprite(NANDGate.TEXTURE_NAND_GATE_OFF);

    event.addSprite(NORGate.TEXTURE_NOR_GATE_ON);
    event.addSprite(NORGate.TEXTURE_NOR_GATE_OFF);

    event.addSprite(XNORGate.TEXTURE_XNOR_GATE_ON);
    event.addSprite(XNORGate.TEXTURE_XNOR_GATE_OFF);
  }
}
