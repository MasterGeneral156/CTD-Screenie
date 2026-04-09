package mastergeneral156.ctd.screenie;

import net.neoforged.neoforge.common.ModConfigSpec;

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
