package pl.amon.moretinygates.network;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import pl.amon.moretinygates.MoreTinyGates;

@EventBusSubscriber(modid = MoreTinyGates.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModNetworkHandler {

    @SubscribeEvent
    public static void registerMessages(RegisterPayloadHandlersEvent event) {
    }
}