package com.FoscusGames.gameobjects;

import java.util.Random;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.fpHandlers.FPConstants;
import com.FoscusGames.fpHandlers.ScoreManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
//
//public class SpecialCoin extends Coin{
////
////	private static int value = 10;
////
////	public SpecialCoin(float x, float y, int width, int height, int movementType, float xLimit, float yLimit, float freq, float scrollSpeed, Bird bird) {
////		
////		super( x,  y,  width, height, movementType,  xLimit,  yLimit,  freq,  scrollSpeed, bird,value);
////
////		color = new Color (0.9f,0.5f,0.5f,1f);
////	}
////	
////	public SpecialCoin(float x, float y, float scrollSpeed, Bird bird) {
////		
////		super( x,  y,  12, 14, CatchObject.CIRCLE, 0, 0,  0,  scrollSpeed, bird, value);
////
////		
////		Random r = new Random();
////		
////		yLimit = r.nextFloat()*10f+10;
////		xLimit =  r.nextFloat()*10f+4;
////		freq = r.nextFloat()*10f+2;
////		
////		color = new Color (0.9f,0.5f,0.5f,1f);
////
////		
////
////	}
////	
////	@Override
////	public void onCatch() {
////		ScoreManager.addScore(value);
////		if(FPConstants.isSoundOn)AssetLoader.coin.play();
////	}
////	
////	@Override 
////	public void draw(Batch batcher) {
////		
////		Color c = batcher.getColor();
////		
////		batcher.setColor(color);
////		
////		batcher.draw(s, totalPosition.x, totalPosition.y, totalPosition.x, totalPosition.y, width, height, 1,1, 0);
////		
////		batcher.setColor(c);
////
////	}

//}
