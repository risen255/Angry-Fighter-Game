package com.angryfighter.game.desktop;

import com.angryfighter.game.Main;
import com.angryfighter.game.feature.Feature;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Saper (Beta) by Rafa³ £omotowski - Politechnika Bia³ostocka";
		config.width = Feature.resX;
		config.height = Feature.resY;
		config.vSyncEnabled = true;
		config.addIcon("GUI//icon.png", Files.FileType.Internal);
		new LwjglApplication(new Main(), config);
	}
}