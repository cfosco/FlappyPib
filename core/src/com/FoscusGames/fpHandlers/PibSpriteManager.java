package com.FoscusGames.fpHandlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

//Manages Sprites. Never instanciate with empty lists
public class PibSpriteManager {
	
	List<TextureRegion> upSprites;
	List<TextureRegion> downSprites;
	List<TextureRegion> deadSprites;
	TextureRegion currentUpSprite, currentDownSprite, currentDeadSprite, auxUpSprite, auxDownSprite;
	Animation animation;
	Random r;
	
	public PibSpriteManager (List<TextureRegion> upSprites,	List<TextureRegion> downSprites, List<TextureRegion> deadSprites) {
		
		this.upSprites = upSprites;
		this.downSprites = downSprites;
		this.deadSprites = deadSprites;
		
		currentUpSprite = upSprites.get(0);
		currentDeadSprite = deadSprites.get(0);
		currentDownSprite = downSprites.get(0);
		
		r = new Random();
		
		setCurrentAnimation();
		
	}
	
	
	public PibSpriteManager (TextureRegion upSprite, TextureRegion downSprite, TextureRegion deadSprite) {


		upSprites = new ArrayList<TextureRegion>();
		downSprites = new ArrayList<TextureRegion>();
		deadSprites = new ArrayList<TextureRegion>();

		currentUpSprite = upSprite;
		currentDeadSprite = deadSprite;
		currentDownSprite = downSprite;

		upSprites.add(currentUpSprite);
		downSprites.add(currentDownSprite);
		deadSprites.add(currentDeadSprite);
		
		r = new Random();

		setCurrentAnimation();
		
	}
	
	
	public void activatePowerFace(TextureRegion face) {
		//Gdx.app.log("PSM", "activated");
		auxUpSprite = currentUpSprite;
		auxDownSprite = currentDownSprite;
		
		currentUpSprite = face;
		currentDownSprite = face;
	}
	
	public void deactivatePowerFace() {
		//Gdx.app.log("PSM", "deactivated");
		currentUpSprite = auxUpSprite;
		currentDownSprite = auxDownSprite;
	}
	
	private void setCurrentAnimation() {
		TextureRegion[] pibs = {currentUpSprite, currentDownSprite};
        animation = new Animation(0.3f, pibs);
        animation.setPlayMode(Animation.PlayMode.NORMAL);
	}
	
	private TextureRegion randomizeSprite (List<TextureRegion> list) {

		return list.get(r.nextInt(list.size()));
		
	}
	
	public void randomizeAll() {
		randomizeUpDownSprite();
		randomizeDeadSprite();
	}
	
	public void randomizeUpDownSprite() {
		
		int idx = r.nextInt(Math.min(upSprites.size(), downSprites.size()));
		
		currentUpSprite = upSprites.get(idx);
		currentDownSprite = downSprites.get(idx);
		
	}
	
	public void randomizeUpSprite() {
		currentUpSprite = randomizeSprite(upSprites);
		setCurrentAnimation();
	}
	
	public void randomizeDownSprite() {
		currentDownSprite = randomizeSprite(downSprites);
		setCurrentAnimation();
	}
	
	public void randomizeDeadSprite() {
		currentDeadSprite = randomizeSprite(deadSprites);
	}
	
	public TextureRegion getUpSprite() {
		return currentUpSprite;
	}
	
	public TextureRegion getDownSprite() {
		return currentDownSprite;
	}
	
	public TextureRegion getDeadSprite() {
		return currentDeadSprite;
	}
	
	public Animation getAnimation() {
		return animation;
	}

	

	

}
