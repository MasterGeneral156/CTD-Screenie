package mastergeneral156.ctd.screenie;

import net.minecraft.client.Minecraft;
import net.minecraft.client.Screenshot;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

import java.io.File;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = CTDScreenie.MODID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = CTDScreenie.MODID, value = Dist.CLIENT)
public class CTDScreenieClient {
    public CTDScreenieClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    private static int tickCounter = 0;

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();

        if (mc.level == null || mc.player == null) return;

        tickCounter++;

        int interval = Config.CLIENT.screenshotIntervalTicks.get();

        if (tickCounter >= interval) {
            tickCounter = 0;
            takeScreenshot(mc);
        }
    }

    private static void takeScreenshot(Minecraft mc) {
        Screenshot.grab(
                mc.gameDirectory,
                mc.getMainRenderTarget(),
                component -> {
                    if (mc.player != null) {
                        mc.player.sendSystemMessage(component);
                    }
                }
        );
    }


}
