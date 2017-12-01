package com.FoscusGames.gamemechanics;

import com.FoscusGames.gameobjects.Bird;
import com.FoscusGames.gameobjects.ScrollHandler;
import com.FoscusGames.gameworld.GameWorld;
import com.FoscusGames.sound.PowerSoundManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DashPower extends Power {
	
	float speed;
	boolean firstAcceleration, groundDash;
	float accelerationTime;
	float cstSpeedTime;
	float decelerationTime;
	
	public DashPower (Animation a, GameWorld w, float d, Sprite s, float speed, String name, TextureRegion face, Color c, PowerSoundManager sM) {
		super(a, w, d, s, name, face,c, sM);
		
		this.speed = speed;
		firstAcceleration = true;
		
		 accelerationTime = 0.015f*5*300/speed;
		 cstSpeedTime = 0.8f*duration;
		 decelerationTime = duration - cstSpeedTime - accelerationTime;
	}



	@Override
	public void childDraw(Batch batcher) {
		
		if(isInUse) {
			
		Color c=batcher.getColor();
		batcher.setColor(color);
		
		Bird pib = world.getBird();
		
		float spriteWidth = 3*pib.getHeight()+30; 	//Extra Width para englobar al pib 
		float spriteHeight = pib.getWidth() + 25;				//Extra Height para englobar al pib
		
		float animX = pib.getX()-spriteWidth+pib.getHeight();
		float animY = pib.getY()+pib.getOriginY()-spriteHeight/2;
				
		batcher.draw(anim.getKeyFrame(world.getRunTime()), animX, animY, animX, animY, spriteWidth, spriteHeight, 1, 1, 0);
		
		batcher.setColor(c);
		}
	}
	
	@Override
	protected void use(float runTime) {
		
		super.use(runTime);

		world.getBird().unMaterialize();
		
		if(groundDash) world.getBird().setJumpFromGround(true);
		
	}
	
	@Override 
	protected void childUpdate(float runTime) {
		
			world.getBird().setRotation(90);

			world.getBird().setX(dashFunction( world.getBird().getStartX(), 55, 2.1f));
			world.getScroller().setScrollSpeed(-dashFunction(-ScrollHandler.SCROLL_SPEED, speed, 0));
		
	}
	
	
	
	
	protected float dashFunction( float initialX, float maxX, float var) {
		

		
		//float dashTime = runTime-start;
		
		
			
		if(elapsed <= accelerationTime && firstAcceleration ) {
			
			
			return initialX + elapsed*maxX/accelerationTime;
			
			
		}
		
		else if (elapsed <= cstSpeedTime+accelerationTime) {
			
			firstAcceleration = false;
			
			return (float) (initialX + maxX + var*Math.sin(30*elapsed-accelerationTime));
			
		}
		
		else {
			if (elapsed>duration) elapsed=duration;
			
			return (float) (initialX + maxX - maxX*(elapsed-cstSpeedTime-accelerationTime)/decelerationTime);
			
		}
		
	}
	
	@Override
	public void childStop() {
		
		world.getBird().materialize();
		world.getScroller().resetScrollSpeed();
		world.getBird().resetX();
		world.getBird().resetRotation();
		firstAcceleration = true;

		
	}
	
	@Override
	public void childInterrupt() {
		world.getBird().materialize();
		world.getScroller().resetScrollSpeed();
	}
	
	@Override 
	public void childReset() {

		world.getBird().resetX();
		world.getBird().resetRotation();
		firstAcceleration = true;
	}

	public void enableSlowDeceleration() {
		 cstSpeedTime = 0.6f*duration;
		 decelerationTime = duration - cstSpeedTime - accelerationTime;
		
	}





}
