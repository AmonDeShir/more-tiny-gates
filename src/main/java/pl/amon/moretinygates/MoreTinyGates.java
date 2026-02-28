package pl.amon.moretinygates;

import com.dannyandson.tinygates.setup.RegistrationTinyRedstone;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import pl.amon.moretinygates.setup.ClientSetup;
import pl.amon.moretinygates.setup.Registration;

@Mod(MoreTinyGates.MODID)
public class MoreTinyGates
{
    public static final String MODID = "moretinygates";

    public MoreTinyGates(IEventBus modEventBus) {

        Registration.register(modEventBus);
        RegistrationTinyRedstone.register();

        if (FMLEnvironment.dist.isClient()) {
            modEventBus.addListener(ClientSetup::init);
        }

        modEventBus.addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event) {
        Registration.registerPanelCells();
    }
}
