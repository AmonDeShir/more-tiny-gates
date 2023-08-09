package pl.amon.moretinygates.network;

import net.minecraftforge.network.simple.SimpleChannel;
import pl.amon.moretinygates.MoreTinyGates;

import com.dannyandson.tinygates.setup.RegistrationTinyRedstone;
import com.dannyandson.tinyredstone.network.PanelCellSync;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.network.NetworkRegistry;

public class ModNetworkHandler {
    private static SimpleChannel INSTANCE;
    private static int ID = 0;
    private static final String PROTOCOL_VERSION = "1.2";

    private static int nextID() {
      return ID++;
    }

    public static void registerMessages() {
      INSTANCE = NetworkRegistry.newSimpleChannel(
        new ResourceLocation(MoreTinyGates.MODID, "tinyredstone"),
        () -> PROTOCOL_VERSION,
        PROTOCOL_VERSION::equals,
        PROTOCOL_VERSION::equals
      );

      INSTANCE.messageBuilder(PanelCellSync.class,nextID())
        .encoder(PanelCellSync::toBytes)
        .decoder(PanelCellSync::new)
        .consumerNetworkThread(PanelCellSync::handle)
        .add();

      if (ModList.get().isLoaded("tinyredstone")) {
        RegistrationTinyRedstone.registerTinyRedstoneNetworkHandlers(INSTANCE, nextID());
      }
    }

    public static SimpleChannel getINSTANCE() {
      return INSTANCE;
    }

    public static void sendToServer(Object packet) {
      INSTANCE.sendToServer(packet);
    }

}