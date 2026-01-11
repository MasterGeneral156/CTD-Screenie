package mastergeneral156.ctd.screenie;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    public static final ModConfigSpec CLIENT_SPEC;
    public static final Client CLIENT;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        CLIENT = new Client(builder);
        CLIENT_SPEC = builder.build();
    }

    public static class Client {
        public final ModConfigSpec.IntValue screenshotIntervalTicks;
        public final ModConfigSpec.BooleanValue disableScreenshot;
        public final ModConfigSpec.BooleanValue disableChatSend;

        Client(ModConfigSpec.Builder builder) {
            builder.push("general");

            screenshotIntervalTicks = builder
                    .comment("Ticks between automatic screenshots (20 ticks = 1 second)")
                    .defineInRange("screenshotIntervalTicks", 6000, 20, Integer.MAX_VALUE);

            disableScreenshot = builder.comment("Disable all features of this mod").define("disableScreenshot", false);
            disableChatSend = builder.comment("Disable sending a chat message").define("disableChatSend", false);

            builder.pop();
        }
    }
}
