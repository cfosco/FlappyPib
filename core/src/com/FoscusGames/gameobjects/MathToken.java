package com.FoscusGames.gameobjects;

import java.util.Random;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class MathToken extends PowerToken {

	

	public MathToken(float x, float y, int width, int height, float scrollSpeed, Bird bird, float duration, int v, Color c) {
		super(x, y, width, height, CatchObject.VERTICAL, 0, 0, new Sprite(AssetLoader.itemMaths), scrollSpeed, bird, 0, c,v,duration);

		setValues();
		
		setAddsScore(true);
		setAddsTime(false);
	}

	public MathToken(Vector2 pos, float scrollSpeed, Bird bird, int value) {

		super(pos.x, pos.y, 25, 17, CatchObject.VERTICAL, 0, 0, new Sprite(AssetLoader.itemMaths), scrollSpeed, bird, 0, new Color(1f,1f,1f,1f),value,0);
		
		setValues();
		
		
		setAddsScore(true);
		setAddsTime(false);
	}
	
	
	
	public void setValues() {
		Random r = new Random();
		
		yLimit = r.nextFloat()*10f+5;
		xLimit =  r.nextFloat()*10f+5;
		freq = r.nextFloat()*5f+3f;
		
	}
}
