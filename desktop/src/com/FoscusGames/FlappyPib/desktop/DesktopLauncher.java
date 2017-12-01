package com.FoscusGames.FlappyPib.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.FoscusGames.FlappyPib.FPGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		  	config.title = "Flappy Pib";
	        config.width = 544;		//272
	        config.height = 816;	//816
		new LwjglApplication(new FPGame(), config);
	}
}
