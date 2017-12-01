package com.FoscusGames.gameobjects;

import java.util.Random;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.fpHandlers.FPConstants;
import com.FoscusGames.fpHandlers.ScoreManager;
import com.FoscusGames.fpHandlers.SoundLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class PaperToken extends PowerToken {

	float rotation;

	

	public PaperToken(float x, float y, int width, int height, float scrollSpeed, Bird bird, float duration, int v, Color c, float rot) {
		super(x, y, width, height, CatchObject.VERTICAL, 0, 0, new Sprite(AssetLoader.itemPapel), scrollSpeed, bird, 0, c,v,duration);

		Random r = new Random();
		
		yLimit = r.nextFloat()*10f+5;
		xLimit =  r.nextFloat()*10f+5;
		freq = r.nextFloat()*10f+3;
		
		rotation = rot;
		
		setAddsScore(true);
		setAddsTime(false);
	}
	
	@Override 
	public void draw(Batch batcher) {
		
		Color c = batcher.getColor();
		
		batcher.setColor(color);
		
		batcher.draw(s, getTotalPosition().x, getTotalPosition().y, getTotalPosition().x, getTotalPosition().y, width, height, 1,1, rotation);
		
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
		
		SoundLoader.fartLow.play();
	}
	
//	@Override
//	public void onCatch() {
//		ScoreManager.addScore(value);
//		if(FPConstants.isSoundOn)AssetLoader.coin.play();
//		
//		//bird.getPower().increaseTokenCounter(1);
//		
//		bird.getPower().extendDuration(extraDuration);
//	}

	
}
