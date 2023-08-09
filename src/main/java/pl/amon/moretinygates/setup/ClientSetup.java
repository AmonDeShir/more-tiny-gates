package pl.amon.moretinygates.setup;

import com.dannyandson.tinygates.blocks.GateBlockRenderer;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import pl.amon.moretinygates.MoreTinyGates;

@Mod.EventBusSubscriber(modid = MoreTinyGates.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
  public static void init(final FMLClientSetupEvent event) {
  
  }

  @SubscribeEvent
  public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event){
    Registration.NAND_GATE_BLOCK.registerBlockEntityRenderers(event);
    Registration.NOR_GATE_BLOCK.registerBlockEntityRenderers(event);
    Registration.XNOR_GATE_BLOCK.registerBlockEntityRenderers(event);
    Registration.DIODE_BLOCK.registerBlockEntityRenderers(event);
    Registration.LIMITER_BLOCK.registerBlockEntityRenderers(event);

    event.registerBlockEntityRenderer(Registration.GENERATOR_BLOCK_ENTITY.get(), GateBlockRenderer::new);
  }
}
