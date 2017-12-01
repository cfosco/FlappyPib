package com.FoscusGames.ui;

import com.FoscusGames.gameworld.GameWorld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class Menu {
	
	protected Stage stage;
	protected Table container;
	protected Table elements;
	protected ScrollPane scrollPane;
	protected GameWorld myWorld;
	
	float screenWidth, screenHeight;

	
	public Menu (Batch batcher, Camera cam, GameWorld world) {
		

		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		
		Viewport viewport = new ScreenViewport(cam);
		this.stage = new Stage(viewport);
		myWorld = world;
		
	    
		
	}
	


	public void setInputProcessor() {
    	Gdx.input.setInputProcessor(stage);

	}
	
	
    public void draw() {
    	
        this.stage.act();
        this.stage.draw();
    }

	public abstract Table createContainer();
	
	
	

}
