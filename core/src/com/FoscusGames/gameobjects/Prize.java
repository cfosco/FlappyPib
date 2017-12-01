package com.FoscusGames.gameobjects;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Prize {
	
	protected TextureRegion sprite;
	protected String name;
	protected String description;
	protected String group;
	protected boolean acquired;
	
	
	public Prize (TextureRegion s, String n, String d) {
		sprite = s;
		name = n;
		description = d;
		acquired = false;
		group = "general";
		
	}
	
	public Prize (TextureRegion s, String n, String d, String g) {
		sprite = s;
		name = n;
		description = d;
		acquired = false;
		group = g;
	}
	
	public boolean gotIt() {
		return acquired;
	}
	
	public void acquired() {
		acquired = true;
	}

	public TextureRegion getSprite() {
		return sprite;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setGroup(String g) {
		group =g;
	}

	public String getGroup() {
		return group;
	}
	
	public void draw(Batch batcher, float X, float Y,  float height, float width) {
		
		batcher.draw(sprite, X, Y, height, width);
		
	}
	
	/*
	public void drawGroup(Batch batcher, float X, float Y, float scale) {
		
		float prevScale = AssetLoader.whiteFont.getScaleX();
		AssetLoader.whiteFont.setScale(scale);
		AssetLoader.whiteFont.draw(batcher, this.description, X, Y);
		AssetLoader.whiteFont.setScale(prevScale);
	}
	*/
	
	/*public void drawDescription(Batch batcher, float X, float Y, float scale) {

		float prevScale = AssetLoader.whiteFont.getScaleX();
		AssetLoader.whiteFont.setScale(scale);
		AssetLoader.whiteFont.draw(batcher, this.description, X, Y);
		AssetLoader.whiteFont.setScale(prevScale);
		
	}*/
	
	public abstract TextureRegion getRaritySprite();
	
	public abstract void drawRarity(Batch batcher, float X, float Y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rot);
	
	//public abstract void drawName(Batch batcher, float X, float Y, float scale);
	
	public abstract String getRarity();
	
	public abstract BitmapFont getFont();

}
