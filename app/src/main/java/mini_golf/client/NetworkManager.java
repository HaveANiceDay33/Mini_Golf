package mini_golf.client;

import com.osreboot.hvol3.foundation.HvlFoundation;
import com.osreboot.hvol3.foundation.HvlFoundationAgentClient;
import com.osreboot.hvol3.foundation.module.HvlModuleMenuLink;

import mini_golf.client.module.LobbyModule;
import mini_golf.common.MiniGolfDescriptor;

public class NetworkManager {
    private static MiniGolfDescriptor descriptor;

    public static void initialize() {

        descriptor = new MiniGolfDescriptor();
        HvlFoundation.initialize(new HvlFoundationAgentClient(descriptor));

        HvlFoundation.register(MiniGolfDescriptor.STATE_LOBBY, () -> {
            return new LobbyModule();
        });

        HvlFoundation.register(MiniGolfDescriptor.STATE_LOBBY, () -> {
            return new HvlModuleMenuLink(MenuManager.lobby);
        });
    }

    public static void connect(String addressArg, int portArg) {
        descriptor.address = addressArg;
        descriptor.port = portArg;

        HvlFoundation.connect();
    }

    public static void disconnect() {
        HvlFoundation.disconnect();
    }
}
