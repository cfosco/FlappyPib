package com.FoscusGames.FlappyPib;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.fpHandlers.SoundLoader;
import com.FoscusGames.screens.SplashScreen;
import com.badlogic.gdx.Game;


public class FPGame extends Game {

	@Override
	public void create() {
		
		
		//setScreen( new LoadingScreen());
		
		AssetLoader.load();
		SoundLoader.load();
		
		setScreen( new SplashScreen(this));
	}
	
	@Override
	public void dispose() {
		
		super.dispose();
		AssetLoader.dispose();
		SoundLoader.dispose();
	}
}
