package com.FoscusGames.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class LoadingScreen implements Screen {

    private SpriteBatch batcher;

    private Texture logoTexture;
    private TextureRegion logo;
    private Sprite sprite;
    
	
	public LoadingScreen () {
		logoTexture = new Texture(Gdx.files.internal("data/EarthLikePlanet.jpg"));
        logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        
		logo = new TextureRegion(logoTexture, 0,0,1990, 798);
	}

	@Override
	public void show() {		
		sprite = new Sprite(logo);
		sprite.setColor(1,1,1,1);
		
		float width = Gdx.graphics.getWidth();
	    float height = Gdx.graphics.getHeight();
	    float desiredWidth = width * 1.0f;
	    float scale = desiredWidth / sprite.getWidth();
	
	    sprite.setSize(sprite.getWidth() * scale, sprite.getHeight() * scale);
	    sprite.setPosition((width / 2) - (sprite.getWidth() / 2), (height / 2)
	            - (sprite.getHeight() / 2));
	    batcher = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batcher.begin();
        sprite.draw(batcher);
        batcher.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		logoTexture.dispose();
		
	}
	
}
