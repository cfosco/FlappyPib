package com.FoscusGames.gameobjects;

import java.util.Random;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.fpHandlers.FPConstants;
import com.FoscusGames.fpHandlers.ScoreManager;
import com.FoscusGames.fpHandlers.SoundLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Coin extends PowerToken {
	
	public static int NORMAL=1;
	public static int SPECIAL = 2;
	public static int RARE = 3;
	
	private int type;
	Random r;
	

//	public Coin(float x, float y, int width, int height, int movementType, float xLimit, float yLimit, float freq, float scrollSpeed, Bird bird, Color color, int value, int type) {
//		
//		super(x, y, width, height, movementType, xLimit, yLimit, new Sprite(AssetLoader.itemCoin), scrollSpeed, bird, freq, color, value, 0 );
//		
//		this.type=type;
//		
//	}
//	
	
	public Coin(float x, float y, float scrollSpeed, Bird bird, int type) {
		
		super(x,y,10,12,CatchObject.VERTICAL, 0,0 , new Sprite(AssetLoader.itemCoin), scrollSpeed, bird, 0, new Color(1f,1f,1f,1f), 0, 0);

		this.type = type;
		
		setAddsTime(false);
		setAddsScore(true);
		
		r = new Random();
		
		if (type == NORMAL) {
				
		
		yLimit = r.nextFloat()*10f+5;
		xLimit =  r.nextFloat()*10f+5;
		freq = r.nextFloat()*10f+3;
		
		value = 1;
		
		}
		
		if (type == SPECIAL) {

			m = CatchObject.CIRCLE;
			width = 12;
			height = 14;
			
			yLimit = r.nextFloat()*10f+10;
			xLimit =  r.nextFloat()*10f+4;
			freq = r.nextFloat()*10f+2;
			
			color = new Color (0.9f,0.5f,0.5f,1f);
			value =5;
		}
		
		if (type == RARE) {
			

			
			m = CatchObject.CIRCLE;
			width = 15;
			height = 15;
			
			yLimit = r.nextFloat()*20f+15;
			xLimit =  r.nextFloat()*20f+7;
			freq = r.nextFloat()*8f+2;
			
			color = new Color (0.7f,0.2f,0.9f,1f);
			value=20;

		
		}
		
		else {	//Create a Normal Coin
			
		yLimit = r.nextFloat()*10f+5;
		xLimit =  r.nextFloat()*10f+5;
		freq = r.nextFloat()*10f+3;
		
		}
		


	}
	

	
	@Override
	public void onCatch() {
		ScoreManager.addScore(value);
		if(FPConstants.isSoundOn) {
			
			float roll = r.nextFloat();
			SoundLoader.coin.play();
			
			if (roll <= 0.3f) SoundLoader.sisisi.play();
			else if(roll <= 0.6f)SoundLoader.monedas.play();
		}
	}

	

}
