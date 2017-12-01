package com.FoscusGames.gameobjects;

import java.util.Random;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.fpHandlers.FPConstants;
import com.FoscusGames.fpHandlers.ScoreManager;
import com.FoscusGames.fpHandlers.SoundLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class PlantaToken extends PowerToken {
	
	public PlantaToken(float x, float y, int width, int height, float scrollSpeed, Bird bird, float duration, int v, Color c) {
		super(x, y, width, height, CatchObject.VERTICAL, 0, 0, new Sprite(AssetLoader.itemPlanta), scrollSpeed, bird, 0, c,v,duration);

		setValues();
		
		
		setAddsScore(true);
		setAddsTime(true);
	}

	public PlantaToken(Vector2 pos, float scrollSpeed, Bird bird, float dur, int v) {

		super(pos.x, pos.y, 55, 55, CatchObject.VERTICAL, 0, 0, new Sprite(AssetLoader.itemPlanta), scrollSpeed, bird, 0, new Color(1f,1f,1f,1f),v,dur);
		
		setValues();
		
		
		setAddsScore(true);
		setAddsTime(true);
	}
	
	public void setValues() {
		//Random r = new Random();
		
		yLimit = 28;	//r.nextFloat()*10f+10;
		xLimit =  28;	//r.nextFloat()*10f+10;
		freq = 4f; //r.nextFloat()*3f+3f;
		
	}
	
	@Override
	public void onCatch() {

		
		if(addsScore()) {
			ScoreManager.addScore(value);
		}
		
		if(addsTime()) {

			bird.getPower().extendDuration(getExtraDuration());
		}
		
		if(FPConstants.isSoundOn)SoundLoader.zenBell.play();
	}

}
