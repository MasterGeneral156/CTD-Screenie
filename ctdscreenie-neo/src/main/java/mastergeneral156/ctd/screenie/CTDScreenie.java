package mastergeneral156.ctd.screenie;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;

@Mod(CTDScreenie.MODID)
public class CTDScreenie {
    public static final String MODID = "ctdscreenie";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CTDScreenie(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_SPEC);
    }
}
