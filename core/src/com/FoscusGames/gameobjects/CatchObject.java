package com.FoscusGames.gameobjects;

import com.FoscusGames.fpHandlers.CollisionHandler;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class CatchObject extends Scrollable {
	
	//Vector2 position;
	
	protected int m;
	protected float xLimit;
	protected float yLimit;
	
	protected float runTime;
	
	protected float freq;
	
	public boolean collision;
	
	public static int VERTICAL = 1;
	public static int CIRCLE = 2;
	
	Bird bird;
	Sprite s;
	
	private Vector2 totalPosition;

	
	
	public CatchObject(float x, float y, int width, int height, int movementType, float xLimit, float yLimit, Sprite s, float scrollSpeed, Bird bird, float f) {
		
		super(x,  y,  width, height, scrollSpeed);
		
		setTotalPosition(new Vector2(x,y));
		m = movementType;
		this.xLimit = xLimit;
		this.yLimit = yLimit;
		this.s = s;
		
		this.bird = bird;
		this.freq = f;
		
		collision = false;
		
		runTime = 0;

	}
	
	public void update(float delta) {
		
		super.update(delta);
		
		runTime +=delta;
		
		if(m == VERTICAL) {
			
			getTotalPosition().y = position.y+yLimit*(float)Math.sin(freq*runTime);
			getTotalPosition().x = position.x;
			
		}
		
		if(m == CIRCLE) {
			
			getTotalPosition().x = position.x+xLimit*(float)Math.cos(freq*runTime);
			getTotalPosition().y = position.y+yLimit*(float)Math.sin(freq*runTime);
			
		}
		
		
		boundingBox.setPosition(getTotalPosition());
		

		
		
		if (CollisionHandler.collides(this, bird)) {
			collision = true;
		}
		
		
		
	}
	
	public void draw(Batch batcher) {
		
		
		
		batcher.draw(s, getTotalPosition().x, getTotalPosition().y, getTotalPosition().x, getTotalPosition().y, width, height, 1,1, 0);
		
		
		
	}
	
	public float getX() {
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}

	public void onCatch() {
		
	}

	public Vector2 getTotalPosition() {
		return totalPosition;
	}

	public void setTotalPosition(Vector2 totalPosition) {
		this.totalPosition = totalPosition;
	}
	

}
