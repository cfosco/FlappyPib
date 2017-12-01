package com.FoscusGames.gameobjects;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.fpHandlers.FPConstants;
import com.FoscusGames.fpHandlers.ScoreManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PowerToken extends CatchObject {
	
	protected Color color;
	protected int value;
	private float extraDuration;
	
	private boolean addsScore;
	private boolean addsTime;
	
	
	public PowerToken (float x, float y, int width, int height, int movementType, float xLimit, float yLimit, Sprite s, float scrollSpeed, Bird bird, float f, Color c, int v, float extraDur) {
		super(x,y,width,height,movementType,xLimit,yLimit,s,scrollSpeed,bird,f);
		
		color = c;
		value = v;
		setExtraDuration(extraDur);
		
		setAddsScore(false);
		setAddsTime(false);
	}
	
	@Override 
	public void draw(Batch batcher) {
		
		Color c = batcher.getColor();
		
		batcher.setColor(color);
		
		batcher.draw(s, getTotalPosition().x, getTotalPosition().y, getTotalPosition().x, getTotalPosition().y, width, height, 1,1, 0);
		
		batcher.setColor(c);

	}
	
	@Override
	public void onCatch() {

		
		if(addsScore()) {
			ScoreManager.addScore(value);
		}
		
		if(addsTime()) {

			bird.getPower().extendDuration(getExtraDuration());
		}
	}

	public int getValue() {
		return value;
	}

	public boolean addsScore() {
		return addsScore;
	}

	public void setAddsScore(boolean addsScore) {
		this.addsScore = addsScore;
	}

	public boolean addsTime() {
		return addsTime;
	}

	public void setAddsTime(boolean addsTime) {
		this.addsTime = addsTime;
	}

	public float getExtraDuration() {
		return extraDuration;
	}

	public void setExtraDuration(float extraDuration) {
		this.extraDuration = extraDuration;
	}

}
