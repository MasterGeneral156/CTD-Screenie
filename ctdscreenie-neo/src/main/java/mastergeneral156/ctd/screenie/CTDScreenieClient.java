/*
	Project:	CTD Screenie 26.2
	File:		mastergeneral156.ctd.screenie.CTDScreenieClient
	Author:		TheMasterGeneral
	Website: 	https://github.com/MasterGeneral156/CTD-Screenie
	License:	MIT License

				Copyright (c) 2026 TheMasterGeneral

				Permission is hereby granted, free of charge, to any person obtaining a copy
				of this software and associated documentation files (the "Software"), to deal
				in the Software without restriction, including without limitation the rights
				to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
				copies of the Software, and to permit persons to whom the Software is
				furnished to do so, subject to the following conditions:

				The above copyright notice and this permission notice shall be included in all
				copies or substantial portions of the Software.

				THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
				IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
				FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
				AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
				LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
				OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
				SOFTWARE.
*/
package mastergeneral156.ctd.screenie;

import com.mojang.blaze3d.pipeline.RenderTarget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Screenshot;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = CTDScreenie.MODID, dist = Dist.CLIENT)
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

        if (tickCounter >= interval && (!Config.CLIENT.disableScreenshot.getAsBoolean())) {
            tickCounter = 0;
            takeScreenshot(mc);
        }
    }

    private static void takeScreenshot(Minecraft mc) {
        assert mc.level != null;
        if (mc.level.isClientSide()) {
            Screenshot.grab(
                    mc.gameDirectory,
                    mc.gameRenderer.mainRenderTarget(),
                    component -> {
                        if (mc.player != null && (!Config.CLIENT.disableChatSend.getAsBoolean())) {
                            mc.player.sendSystemMessage(component);
                        }
                    }
            );
        }
    }


}
