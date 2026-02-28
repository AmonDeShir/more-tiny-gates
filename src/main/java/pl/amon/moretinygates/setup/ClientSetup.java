package pl.amon.moretinygates.setup;

import com.dannyandson.tinygates.blocks.GateBlockRenderer;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import pl.amon.moretinygates.MoreTinyGates;

@EventBusSubscriber(modid = MoreTinyGates.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
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
