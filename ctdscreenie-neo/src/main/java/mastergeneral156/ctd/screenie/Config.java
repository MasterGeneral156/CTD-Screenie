/*
	Project:	CTD Screenie 26.2
	File:		mastergeneral156.ctd.screenie.Config
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
