package com.FoscusGames.gameobjects;

import java.util.Random;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.fpHandlers.FPConstants;
import com.FoscusGames.fpHandlers.ScoreManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;

//
//
//public class RareCoin extends Coin {
//	
//	protected Color color;
//
//	public RareCoin(float x, float y, int width, int height, int movementType, float xLimit, float yLimit, float freq, float scrollSpeed, Bird bird) {
//		
//		super( x,  y,  width, height, movementType,  xLimit,  yLimit,  freq,  scrollSpeed, bird);
//
//		color = new Color (0.7f,0.2f,0.9f,1f);
//	}
//	
//	public RareCoin(float x, float y, float scrollSpeed, Bird bird) {
//		
//		super( x,  y,  15, 15, CatchObject.CIRCLE, 0, 0,  0,  scrollSpeed, bird);
//
//		
//		Random r = new Random();
//		
//		yLimit = r.nextFloat()*20f+15;
//		xLimit =  r.nextFloat()*20f+7;
//		freq = r.nextFloat()*8f+2;
//		
//		color = new Color (0.7f,0.2f,0.9f,1f);
//
//		
//
//	}
//	
//	@Override
//	public void onCatch() {
//		ScoreManager.addScore(50);
//		if(FPConstants.isSoundOn)AssetLoader.coin.play();
//	}
//	
//	@Override 
//	public void draw(Batch batcher) {
//		
//		Color c = batcher.getColor();
//		
//		batcher.setColor(color);
//		
//		batcher.draw(s, totalPosition.x, totalPosition.y, totalPosition.x, totalPosition.y, width, height, 1,1, 0);
//		
//		batcher.setColor(c);
//
//	}
//
//}
