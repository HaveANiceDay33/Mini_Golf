package mini_golf.client;

import static com.osreboot.ridhvl2.HvlStatics.hvlDraw;
import static com.osreboot.ridhvl2.HvlStatics.hvlEnvironment;
import static com.osreboot.ridhvl2.HvlStatics.hvlFont;
import static com.osreboot.ridhvl2.HvlStatics.hvlQuad;
import static com.osreboot.ridhvl2.HvlStatics.hvlQuadc;

import java.util.HashMap;

import com.osreboot.ridhvl2.menu.HvlDefault;
import com.osreboot.ridhvl2.menu.HvlMenu;
import com.osreboot.ridhvl2.menu.component.*;
import com.osreboot.ridhvl2.menu.component.HvlButton.HvlButtonState;
import com.osreboot.ridhvl2.migration.Color;
import com.osreboot.ridhvl2.template.HvlDisplay;

import mini_golf.common.GameText;
import mini_golf.common.MiniGolfDescriptor;

public class MenuManager {

	public static final float MENU_PADDING = 30f,
			MENU_OFFSET_TEXT_X = 8f,
			MENU_OFFSET_TEXT_Y = 4f,
			BUTTON_WIDTH = 200,
			BUTTON_HEIGHT = 70;

	public static final Color COLOR_PRIMARY = new Color(0, 154, 23),
			COLOR_PRIMARY_DIM = new Color(0, 255, 0, 0.1f),
			COLOR_SECONDARY = new Color(135, 206, 235),
			COLOR_SECONDARY_DIM = new Color(135, 206, 235, 0.5f);

	public static final String NAME_SERVER_ADDRESS_FIELD = "fieldServerAddress",
			NAME_LOBBY_PLAYERS_ARRANGER = "arrangerLobbyPlayers";

	public static HvlArranger mainMenu, connecting, lobby;

	public static final String[] PRESETS_SERVER_ADDRESS = {
			"localhost:25565", // LOCAL
			"73.239.1.166:25565",
			"68.1.62.76:25565",
			"104.220.219.62:25565",
			"137.112.153.147" /// ROSE ADDR
	};

	public static void init() {

		HvlDefault.setDefaults(new HashMap<>());

		HvlDefault.put(new HvlArranger(true, 0f, 1f));

		HvlDefault.put(new HvlSpacer(16f));

		HvlDefault.put(new HvlLabel(hvlFont(0), "DEFAULT TEXT", COLOR_PRIMARY, 0.5f)
				.offset(MENU_OFFSET_TEXT_X, MENU_OFFSET_TEXT_Y)
				.align(0f, 0f).overrideHeight(20f));

		HvlDefault.put(new HvlButtonLabeled(hvlFont(0), "DEFAULT TEXT", COLOR_PRIMARY, 1f, (d, e, b, s) -> {
			boolean active = s == HvlButtonState.OFF;
			b.set(HvlButtonLabeled.TAG_TEXT_COLOR, COLOR_PRIMARY);
			hvlDraw(hvlQuadc(e.getX() + BUTTON_WIDTH / 2, e.getY() + BUTTON_HEIGHT / 2, e.getWidth() + 4,
					e.getHeight() + 4), COLOR_PRIMARY);
			hvlDraw(hvlQuad(e.getX(), e.getY(), e.getWidth(), e.getHeight()),
					active ? COLOR_SECONDARY : COLOR_SECONDARY_DIM);
		}).offset(MENU_OFFSET_TEXT_X, MENU_OFFSET_TEXT_Y).align(0.45f, 0.5f).overrideSize(128f, 20f));

		HvlDefault.put(new HvlCheckboxLabeled(hvlFont(0), "DEFAULT TEXT", COLOR_PRIMARY, 1f, (d, e, b, s, a) -> {
			boolean active = s == HvlButtonState.OFF ^ a;
			b.set(HvlButtonLabeled.TAG_TEXT_COLOR, active ? COLOR_PRIMARY : COLOR_SECONDARY);
			hvlDraw(hvlQuad(e.getX(), e.getY(), e.getWidth(), e.getHeight()), COLOR_PRIMARY);
			if (active)
				hvlDraw(hvlQuad(e.getX() + 1f, e.getY() + 1f, e.getWidth() - 2f, e.getHeight() - 2f), COLOR_SECONDARY);
		}).offset(MENU_OFFSET_TEXT_X, MENU_OFFSET_TEXT_Y).align(0f, 0.5f).overrideSize(40f, 20f));

		HvlDefault.put(new HvlRule(true, 1f, 2f, new Color(0.5f, 0.5f, 0.5f)).align(0.5f, 0.5f).overrideHeight(16f));

		HvlDefault.put(new HvlField(hvlFont(0), "", COLOR_PRIMARY, 0.5f, (d, e, b, s) -> {
			hvlDraw(hvlQuad(e.getX(), e.getY(), e.getWidth(), e.getHeight()), COLOR_PRIMARY);
			if (s == HvlButtonState.OFF)
				hvlDraw(hvlQuad(e.getX() + 1f, e.getY() + 1f, e.getWidth() - 2f, e.getHeight() - 2f), COLOR_SECONDARY);
			if (s == HvlButtonState.HOVER)
				hvlDraw(hvlQuad(e.getX() + 1f, e.getY() + 1f, e.getWidth() - 2f, e.getHeight() - 2f), COLOR_PRIMARY);
			if (s == HvlButtonState.ON)
				hvlDraw(hvlQuad(e.getX() + 1f, e.getY() + 1f, e.getWidth() - 2f, e.getHeight() - 2f), COLOR_PRIMARY);
		}).offset(MENU_OFFSET_TEXT_X, MENU_OFFSET_TEXT_Y).align(0f, 0.5f).overrideSize(256f, 20f));

		mainMenu = HvlArranger.fromDefault();
		mainMenu.set(HvlArranger.TAG_DRAW, (d, e, c) -> {
			hvlDraw(hvlQuad(0, 0, HvlDisplay.getWidth(), HvlDisplay.getHeight()), COLOR_SECONDARY);
			HvlArranger.DEFAULT_DRAW.run(d, e, c);
			hvlFont(0).drawc(GameText.GAME_TITLE, e.getX() + e.getWidth() / 2f,
					e.getY() + hvlFont(0).getHeight("A", 0.5f) * 0.5f, COLOR_PRIMARY, 2f);
			hvlFont(0).drawc(GameText.GAME_VERSION + MiniGolfDescriptor.GAME_MAJOR_VERSION,
					e.getX() + e.getWidth() / 2f,
					e.getY() + 50, COLOR_PRIMARY, 1f);
		});

		mainMenu.add(HvlButtonLabeled.fromDefault().text("Singleplayer")
				.clicked(b -> {

				})
				.overrideSize(BUTTON_WIDTH, BUTTON_HEIGHT)
				.overridePosition(HvlDisplay.getWidth() / 2 - BUTTON_WIDTH / 2, HvlDisplay.getHeight() / 2 - 200));
		mainMenu.add(HvlSpacer.fromDefault().set(HvlSpacer.TAG_OVERRIDE_WIDTH, null));
		mainMenu.add(HvlButtonLabeled.fromDefault().text("Multiplayer")
				.clicked(b -> {
					NetworkManager.connect("localhost", 25565);
					HvlMenu.set(connecting);
				})
				.overrideSize(BUTTON_WIDTH, BUTTON_HEIGHT)
				.overridePosition(HvlDisplay.getWidth() / 2 - BUTTON_WIDTH / 2, HvlDisplay.getHeight() / 2 - 100));
		mainMenu.add(HvlSpacer.fromDefault().set(HvlSpacer.TAG_OVERRIDE_WIDTH, null));
		mainMenu.add(HvlButtonLabeled.fromDefault().text("Quit").clicked(b -> {
			ClientMain.newest().setExiting();
		}).overrideSize(BUTTON_WIDTH, BUTTON_HEIGHT).overridePosition(HvlDisplay.getWidth() / 2 - BUTTON_WIDTH / 2,
				HvlDisplay.getHeight() / 2));

		lobby = HvlArranger.fromDefault();
		lobby.set(HvlArranger.TAG_DRAW, (d, e, c) -> {
			hvlDraw(hvlQuad(0, 0, HvlDisplay.getWidth(), HvlDisplay.getHeight()), COLOR_SECONDARY);
			HvlArranger.DEFAULT_DRAW.run(d, e, c);
			hvlFont(0).drawc(GameText.GAME_TITLE, e.getX() + e.getWidth() / 2f,
					e.getY() + hvlFont(0).getHeight("A", 0.5f) * 0.5f, COLOR_PRIMARY, 2f);
			hvlFont(0).drawc(GameText.GAME_VERSION + MiniGolfDescriptor.GAME_MAJOR_VERSION,
					e.getX() + e.getWidth() / 2f,
					e.getY() + 100, COLOR_PRIMARY, 1f);
		});
		lobby.add(HvlButtonLabeled.fromDefault().text("Disconnect")
				.clicked(b -> {
					NetworkManager.disconnect();
					HvlMenu.set(mainMenu);
				})
				.overrideSize(BUTTON_WIDTH, BUTTON_HEIGHT)
				.overridePosition(HvlDisplay.getWidth() / 2 - BUTTON_WIDTH / 2, HvlDisplay.getHeight() / 2 - 100));
		lobby.add(HvlSpacer.fromDefault().set(HvlSpacer.TAG_OVERRIDE_WIDTH, null));
		lobby.add(HvlButtonLabeled.fromDefault().text("Quit").clicked(b -> {
			NetworkManager.disconnect();
			ClientMain.newest().setExiting();
		}).overrideSize(BUTTON_WIDTH, BUTTON_HEIGHT).overridePosition(HvlDisplay.getWidth() / 2 - BUTTON_WIDTH / 2,
				HvlDisplay.getHeight() / 2));

		connecting = HvlArranger.fromDefault();
		connecting.set(HvlArranger.TAG_DRAW, (d, e, c) -> {
			hvlDraw(hvlQuad(0, 0, HvlDisplay.getWidth(), HvlDisplay.getHeight()), COLOR_SECONDARY);
			HvlArranger.DEFAULT_DRAW.run(d, e, c);
			hvlFont(0).drawc(GameText.GAME_TITLE, e.getX() + e.getWidth() / 2f,
					e.getY() + hvlFont(0).getHeight("A", 0.5f) * 0.5f, COLOR_PRIMARY, 2f);
			hvlFont(0).drawc(GameText.GAME_VERSION + MiniGolfDescriptor.GAME_MAJOR_VERSION,
					e.getX() + e.getWidth() / 2f,
					e.getY() + 100, COLOR_PRIMARY, 1f);
			hvlFont(0).drawc(GameText.CONNECTING, e.getX() + e.getWidth() / 2f,
					e.getY() + 200, COLOR_PRIMARY, 1f);
		});

		HvlMenu.set(mainMenu);

	}

	public static void update(float delta) {
		HvlMenu.operate(delta, hvlEnvironment(MENU_PADDING, MENU_PADDING, HvlDisplay.getWidth() - MENU_PADDING * 2f,
				HvlDisplay.getHeight() - MENU_PADDING * 2f));
	}
}
