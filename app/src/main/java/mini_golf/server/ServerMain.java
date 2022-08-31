package mini_golf.server;

import com.osreboot.hvol3.core.HvlNetwork;
import com.osreboot.hvol3.foundation.HvlFoundation;
import com.osreboot.hvol3.foundation.HvlFoundationAgentServer;
import com.osreboot.ridhvl2.migration.Keyboard;
import com.osreboot.ridhvl2.template.HvlChronology;
import com.osreboot.ridhvl2.template.HvlDisplay;
import com.osreboot.ridhvl2.template.HvlDisplayWindowed;
import com.osreboot.ridhvl2.template.HvlTemplateI;

import mini_golf.common.MiniGolfDescriptor;

public class ServerMain extends HvlTemplateI {

    public static void main(String[] args) {
        HvlChronology.registerChronology(HvlNetwork.class);
        HvlChronology.registerChronology(HvlFoundation.class);
        new ServerMain(new HvlDisplayWindowed(60, 512, 512, "Mini Golf! | Server", true));
    }

    public ServerMain(HvlDisplay displayArg) {
        super(displayArg);
    }

    @Override
    public void initialize() {
        MiniGolfDescriptor descriptor = new MiniGolfDescriptor();

        descriptor.port = 25565;

        HvlFoundation
                .initialize(new HvlFoundationAgentServer(MiniGolfDescriptor.STATE_LOBBY, descriptor));
        HvlFoundation.connect();
    }

    @Override
    public void update(float delta) {
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
            setExiting();
    }

}
