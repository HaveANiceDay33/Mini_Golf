package mini_golf.common;

import com.osreboot.hvol3.core.HvlDescriptorClient;
import com.osreboot.hvol3.core.HvlDescriptorServer;

public class MiniGolfDescriptor implements HvlDescriptorClient, HvlDescriptorServer {

    public static final int STATE_LOBBY = 0,
            STATE_GAME = 1,
            STATE_POST_HOLE = 2,
            STATE_POST_MATCH = 3;

    public static final String GAME_TITLE = GameText.GAME_TITLE,
            GAME_MAJOR_VERSION = "0.0.1";

    public String address;
    public int port;

    @Override
    public String getGameMajorVersion() {

        return GAME_MAJOR_VERSION;
    }

    @Override
    public String getGameTitle() {

        return GAME_TITLE;
    }

    @Override
    public String getServerAddress() {

        return address;
    }

    @Override
    public int getServerPort() {

        return port;
    }

}