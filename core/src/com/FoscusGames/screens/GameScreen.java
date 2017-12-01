package com.FoscusGames.screens;

import com.FoscusGames.fpHandlers.FPConstants;
import com.FoscusGames.fpHandlers.InputHandler;
import com.FoscusGames.gameworld.GameRenderer;
import com.FoscusGames.gameworld.GameWorld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {
	
	private GameWorld world;
	private GameRenderer renderer;
	private float runTime = 0;
	private InputHandler ih;
	
	public GameScreen () {
		
		 float screenWidth = Gdx.graphics.getWidth();
	     float screenHeight = Gdx.graphics.getHeight();
	     float gameWidth = 136;
	     float gameHeight = screenHeight / (screenWidth / gameWidth);
	     
	     FPConstants.gameWidth = gameWidth;
	     FPConstants.gameHeight = gameHeight;
	     
	     FPConstants.playableHeight = gameHeight*0.8f;
	     FPConstants.nonPlayableHeight = FPConstants.gameHeight-FPConstants.playableHeight;

	     int midPointY = (int) (gameHeight / 2);
		

	     world = new GameWorld(midPointY);
		
		ih = new InputHandler(world, screenWidth / gameWidth, screenHeight / gameHeight);
        Gdx.input.setInputProcessor(ih);
		
		renderer = new GameRenderer(world, (int) gameHeight, midPointY);
		world.setRenderer(renderer);
		world.setInputHandler(ih);

		
		//Gdx.app.log("GameScreen", "Attached");

	}

	@Override
	public void render(float delta) {
		
		
		this.runTime += delta;
		world.update(delta);
		renderer.render(delta, runTime);
		

	}

	@Override
	public void resize(int width, int height) {
		
		Gdx.app.log("GameScreen", "resizing");
	}



	@Override
	public void show() {
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

}
