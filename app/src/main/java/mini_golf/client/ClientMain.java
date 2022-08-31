package mini_golf.client;

import static com.osreboot.ridhvl2.HvlStatics.hvlLoad;

import com.osreboot.hvol3.core.HvlNetwork;
import com.osreboot.hvol3.foundation.HvlFoundation;
import com.osreboot.ridhvl2.template.HvlChronology;
import com.osreboot.ridhvl2.template.HvlDisplay;
import com.osreboot.ridhvl2.template.HvlDisplayWindowed;
import com.osreboot.ridhvl2.template.HvlTemplateI;

public class ClientMain extends HvlTemplateI {

    public static void main(String[] args) {
        HvlChronology.registerChronology(HvlNetwork.class);
        HvlChronology.registerChronology(HvlFoundation.class);
        new ClientMain(new HvlDisplayWindowed(144, 1080, 720, "Mini Golf! | Client", false));
    }

    public ClientMain(HvlDisplay displayArg) {
        super(displayArg);

    }

    @Override
    public void initialize() {
        hvlLoad("INOF.hvlft");
        NetworkManager.initialize();
        MenuManager.init();

    }

    @Override
    public void update(float delta) {
        MenuManager.update(delta);

    }

}