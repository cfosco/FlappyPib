package com.FoscusGames.gameobjects;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.fpHandlers.CollisionHandler;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;




public class Laser extends CollisionObject {
	private TextureRegion laserTexture;
	private Rectangle boundingBox;
	private float speed;
	public boolean hit,tooFar;
	
	private ScrollHandler scroller;
	
	public Laser (float x, float y, float speed, ScrollHandler scroller) {
		
		laserTexture = AssetLoader.itemLaser;
		hit = false;
		tooFar = false;
		boundingBox = new Rectangle();
		
		this.boundingBox.setX(x);
		this.boundingBox.setY(y);
		this.speed = speed;
		
		this.boundingBox.setWidth(40);
		this.boundingBox.setHeight(8);
		
		this.scroller = scroller;
		
	}
	
	public void update (float runTime) {
		
		boundingBox.setX( boundingBox.getX() + speed);
		
		
		if 	(CollisionHandler.collides(this,scroller)) {
			this.onHit();
		}
		
		if (boundingBox.getX() > 136) {
			this.onTooFar();
		}
		
	}
	
	public void onTooFar() {
		tooFar = true;
	}
	
	public void onHit() {
		
		hit = true;
		//playHitAnim();
	}
	
	
	public void draw(Batch batcher) {
		
		batcher.draw(laserTexture, boundingBox.getX(), boundingBox.getY(), boundingBox.getX(), boundingBox.getY(), boundingBox.getWidth(), boundingBox.getHeight(), 1, 1, 0);
	}

	public Rectangle getBoundingBox() {
		return boundingBox;
	}
	
	
	
	
}


