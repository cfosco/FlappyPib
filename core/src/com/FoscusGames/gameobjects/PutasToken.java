package com.FoscusGames.gameobjects;

import java.util.Random;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.fpHandlers.FPConstants;
import com.FoscusGames.fpHandlers.ScoreManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PutasToken extends PowerToken {
	

	public PutasToken(float x, float y, int width, int height, float scrollSpeed, Bird bird, float duration, int v, Color c) {
		
		//float x, float y, int width, int height, int movementType, float xLimit, float yLimit, Sprite s, float scrollSpeed, Bird bird, float f, Color c, int v, float extraDur
		
		super(x, y, width, height, CatchObject.VERTICAL, 0, 0, new Sprite(AssetLoader.itemPutas), scrollSpeed, bird, 0, c,v,duration);
		
		Random r = new Random();
		
		yLimit =  r.nextFloat()*10f+14;
		xLimit =   r.nextFloat()*10f+14;
		freq =  r.nextFloat()*7f+5;
		
		setAddsScore(false);
		setAddsTime(true);
	}
	
//	public PutasToken(float x, float y, int width, int height, float scrollSpeed, Bird bird, float duration, int v,Color c) {
//		super(x, y, width, height, CatchObject.VERTICAL, 0, 0, new Sprite(AssetLoader.itemPutas), scrollSpeed, bird, 0);
//
//		extraDuration = duration;
//		Random r = new Random();
//		
//		yLimit = r.nextFloat()*10f+5;
//		xLimit =  r.nextFloat()*10f+5;
//		freq = r.nextFloat()*10f+3;
//		color = c;
//		value = v;
//	}

	
//	@Override
//	public void onCatch() {
//		ScoreManager.addScore(value);
//		if(FPConstants.isSoundOn)AssetLoader.coin.play();
//		
//		bird.getPower().extendDuration(extraDuration);
//	}


	

}
