package com.saltosion.gladiator.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.saltosion.gladiator.GladiatorBrawler;
import com.saltosion.gladiator.util.Name;

public class DesktopLauncher {

	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Name.GAME_NAME;
		config.width = 1280;
		config.height = 720;
		config.vSyncEnabled = true; // Change this to false for performance testing
		config.foregroundFPS = 0;
		config.addIcon("sprites/icon_16.png", Files.FileType.Internal);
		config.addIcon("sprites/icon_32.png", Files.FileType.Internal);
		config.addIcon("sprites/icon_128.png", Files.FileType.Internal);
		LwjglApplication app = new LwjglApplication(new GladiatorBrawler(), config);
	}
}
