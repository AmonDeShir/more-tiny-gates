package pl.amon.moretinygates;

import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import pl.amon.moretinygates.gates.multiblock.AbstractTinyMultiblock;
import pl.amon.moretinygates.gates.multiblock.MultiblockRegistry;
import pl.amon.moretinygates.network.ModNetworkHandler;
import pl.amon.moretinygates.setup.ClientSetup;
import pl.amon.moretinygates.setup.ModSetup;
import pl.amon.moretinygates.setup.Registration;

@Mod(MoreTinyGates.MODID)
public class MoreTinyGates
{
    public static final String MODID = "moretinygates";

    public MoreTinyGates() {
        Registration.register();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::init);


        if(FMLEnvironment.dist.isClient()) {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
        }

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        // register everything
        Registration.registerPanelCells();
        ModNetworkHandler.registerMessages();
    }

    public static void registerMultiblock(String id, Class<? extends AbstractTinyMultiblock> multiblock, Item item) {
        MultiblockRegistry.registerMultiblock(id, multiblock, item);
    }
}
