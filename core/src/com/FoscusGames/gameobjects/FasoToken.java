package com.FoscusGames.gameobjects;

import java.util.Random;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class FasoToken extends PowerToken {
	

	public FasoToken(float x, float y, int width, int height, float scrollSpeed, Bird bird, float duration, int v, Color c) {
		super(x, y, width, height, CatchObject.CIRCLE, 0, 0, new Sprite(AssetLoader.itemFaso), scrollSpeed, bird, 0, c,v,duration);

		setValues();
		
		
		setAddsScore(false);
		setAddsTime(true);
	}

	public FasoToken(Vector2 pos, float scrollSpeed, Bird bird, float dur) {

		super(pos.x, pos.y, 30, 15, CatchObject.CIRCLE, 0, 0, new Sprite(AssetLoader.itemFaso), scrollSpeed, bird, 0, new Color(1f,1f,1f,1f),0,dur);
		
		setValues();
		
		setAddsScore(false);
		setAddsTime(true);
	}
	
	public void setValues() {
		Random r = new Random();
		
		yLimit = 15;
		xLimit =  10;
		freq = r.nextFloat()*2f+3f;
		
	}

}
