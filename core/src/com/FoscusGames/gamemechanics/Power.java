package com.FoscusGames.gamemechanics;

import java.util.Random;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.FoscusGames.TweenAccesors.SpriteAccessor;
import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.fpHandlers.FPConstants;
import com.FoscusGames.gameworld.GameWorld;
import com.FoscusGames.sound.PowerSoundManager;
import com.FoscusGames.ui.PowerNameHolder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Power {
	
	protected Animation anim, presAnim;
	protected TextureRegion face;
	protected GameWorld world;
	protected float duration,elapsed, initialDuration;
	protected float start, activationTime, presTime, lastTime,endTime, leewayTime;
	protected boolean available, firstUpdateRunningCall, firstUpdatePresCall, isInUse, presFinished, hasPresAnim,leeway,justFinished, tweenFinished;
	
	
	protected float presAnimX, presAnimY, presAnimWidth, presAnimHeight;
	
	protected TweenManager manager;
	protected Sprite bgSprite;
	protected TweenCallback cb;
	protected String name;
	
	protected Color color;
	
	protected PowerNameHolder nameHolder;
	
	protected PowerSoundManager soundMgr;
	
	private float bgSpriteStartTime;
	private float bgSpriteInitialX, bgSpriteY,bgSpriteFinalX;
	//protected boolean unlocked = false;
	
	//ALTERNATE SPRITES
	Sprite altBgSprite, normalBgSprite;
	TextureRegion altFace;
	float altProb;
	boolean altEnabled,altActivated;
	
	
	//DURATION BAR

	float dBarWidth;
	float dBarHeight ;
	float dBarY;
	float dBarX;
	
	public Power(Animation a, GameWorld w, float d, Sprite s, String n, TextureRegion powerFace, Color c, PowerSoundManager soundManager) {
		anim = a;
		world = w;
		duration = d;
		initialDuration = d;
		available = true;
		isInUse = false;
		bgSprite = s;		
		name = n;
		face = powerFace;
		color = c;
		
		hasPresAnim = false;

		firstUpdateRunningCall = true;
		firstUpdatePresCall =true;
		presFinished = false;
		justFinished = false;
		leeway=false;
		endTime = 0;
		leewayTime = 2f;
		
		elapsed = 0;
		
		presTime = 1.9f;		//PRESENTATION TIME

		nameHolder = new PowerNameHolder(n, presTime ,color);
		
		soundMgr = soundManager;
		
		
		altFace = powerFace;
		altBgSprite = s;
		normalBgSprite = s;
		altProb=0;
		altEnabled=false;
		altActivated = false;
		
		
//		presAnimX = w.getBird().getX();
//		presAnimY = w.getBird().getY();
//		presAnimHeight = w.getBird().getWidth();
//		presAnimWidth = w.getBird().getHeight();
		
		setupDurationBar();
		
		setupBgSprite();
	}
	
	protected void setupDurationBar() {
		dBarWidth = FPConstants.gameWidth*0.8f;
		dBarHeight = FPConstants.gameHeight*0.1f;
		dBarY = FPConstants.gameHeight - (FPConstants.gameHeight-FPConstants.playableHeight)/2 -dBarHeight/2 + FPConstants.gameHeight*0.02f;
		dBarX = FPConstants.gameWidth/2-dBarWidth/2;
	}
	
	public void use() {
		
		use(world.getRunTime());
		
	}
	
	protected void use(float runTime) {

		if(available) {
			available = false;
			isInUse = true;
			activationTime = runTime;
			
			world.prepareTransition(255,255,255,0.4f);
			
			soundMgr.saveCurrentVolume();
			soundMgr.applyPresVolume();
			soundMgr.playSweep();
			
			
		}
	}
	
	public void update() {
		update(world.getRunTime());
	}
	
	protected void update(float runTime) {
		
		if(isInUse) {
			
			if(!presFinished) updatePres(runTime);		//!presFinished //runTime-activationTime <= presTime
			else updateRunning(runTime);
	
		}
		
		if(justFinished) {
			endTime = runTime;	
			justFinished = false;
			leeway = true;
			world.getBird().unMaterialize();
		} else if (leeway) {
			world.getBird().changeOpacity((float)(Math.sin(15*runTime)+2)/4);
			if(runTime-endTime > leewayTime || isInUse || !world.getBird().isAlive()) {
				world.getBird().materialize();
				world.getBird().changeOpacity(1f);
				leeway = false;
				
			}
		}
	}
	
	protected void updateRunning(float runTime) {
		
		if(firstUpdateRunningCall) {
				
				presFinished = true;
				start = runTime;
				world.getScroller().resetScrollSpeed();
				world.getBird().unFreeze();
				firstUpdateRunningCall = false;
				soundMgr.playRunSound();
				soundMgr.playAuxSound();
				soundMgr.applyRunVolume();
				
				//Gdx.app.log("Power:updateRunning", "firstURCAll");
			}
			
		childUpdate(runTime);
		
		//elapsed =runTime-start;
		
		elapsed += Gdx.graphics.getDeltaTime();
		
		
		if ( elapsed > duration) {
			stop();
		}
	}
	
	
	protected void updatePres(float runTime) {
		
		if(firstUpdatePresCall) {
			
			if(altEnabled) {
				Random r = new Random();
				float roll = r.nextFloat();
				
				if(roll <= altProb)  {
					altActivated = true;
					world.getBird().activatePowerFace(altFace);
					bgSprite = altBgSprite;
					setupBgSprite();
				} else { 
					world.getBird().activatePowerFace(face);
					bgSprite = normalBgSprite;
					setupBgSprite();
				}
			}
			
			else world.getBird().activatePowerFace(face);

			world.getScroller().stop();
			
			
			firstUpdatePresCall = false;
			world.resetRunTime();
			soundMgr.playCatchphrase();
			world.getBird().freeze();
			
			bgSpriteStartTime = 0;
		}
		
		
		nameHolder.update(runTime);
		
		updateBgSprite(runTime);
		
		Gdx.app.log("Power updatePres", "Sprite X is " + bgSprite.getX());
		//Gdx.app.log("Power updatePres", "Delta:" + Gdx.graphics.getDeltaTime());

	  	manager.update(Gdx.graphics.getDeltaTime());		//delta
	  	
	  	//if(!soundMgr.isCatchphrase()) presFinished=true;

	}
	
	private void updateBgSprite(float runTime) {
		
		float t = (runTime-bgSpriteStartTime)/presTime;
		if (t>=1) t=1;
		
		Gdx.app.log("Power updatePres", "t is " + t);
		Gdx.app.log("Power updatePres", "bgSpriteFinalX is " + bgSpriteFinalX);

		bgSprite.setX((bgSpriteFinalX-bgSpriteInitialX)*t+bgSpriteInitialX);		//TO DO: MODIFICAR

	}

	
	private void setupTween() {
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        manager = new TweenManager();

        cb = new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                tweenFinished = true;
                presFinished=true;
            }
        };

        
        resetTween();
       
    }
	
	private void resetTween() {
		 Tween.to(bgSprite, SpriteAccessor.ALPHA, presTime*0.25f)							//Transition time
		 .target(0.9f).ease(TweenEquations.easeInOutQuad).repeatYoyo(1, presTime*0.5f)		//High time
         .setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE)
         .start(manager);
	}
	
	private void setupBgSprite() {
		bgSprite.setColor(new Color(1f,1f,1f,0)); 		//bgSprite.setColor(0.2f,0.7f,1,0);
		
		//float width = FPConstants.gameWidth;
        float height = FPConstants.playableHeight ;//FPConstants.groundHeight;
        
        float desiredHeight = height * 1.0f;
        float scale = desiredHeight/bgSprite.getHeight();
        float width = bgSprite.getWidth()*scale;
        
        Gdx.app.log("Power", "bgSpriteWidth is " + width);
        
//        float desiredWidth = width * 1.0f;
//        float scale = desiredWidth / bgSprite.getWidth();
        

		//bgSpriteXSlide = 30;
		bgSpriteInitialX = -width/2;
		bgSpriteFinalX = FPConstants.gameWidth/2-width/2; //(width / 2) - (bgSprite.getWidth() / 2) -10;

        Gdx.app.log("Power", "bgSpriteFinalX is " + bgSpriteFinalX);
		bgSpriteY =  0;   //(height / 2) - (bgSprite.getHeight() / 2);

        bgSprite.setSize(bgSprite.getWidth() * scale, bgSprite.getHeight() * scale);
        bgSprite.setPosition(bgSpriteInitialX,bgSpriteY);
        setupTween();
		
	}
	
	private void resetBgSprite() {
		
		bgSprite.setPosition(bgSpriteInitialX, bgSpriteY);
		
		resetTween();
		//bgSpriteStartTime = 0;
	}
	
	public boolean isAvailable() {
		return available;
	}
	
	public void setUnavailable () {
		available = false;
	}
/*	public boolean isUnlocked() {
		return unlocked;
	}
	
	public void unlock() {
		unlocked = true;
	}
	*/
	
	public void draw(Batch batcher) {
		
		drawPowerAvailableIndicator(batcher);
	
		
		if(isInUse && !presFinished) {
			
			if(hasPresAnim) {				
						
				Color c =batcher.getColor();
				batcher.setColor(color);
				batcher.draw(presAnim.getKeyFrame(world.getRunTime()), world.getBird().getX()+presAnimX, world.getBird().getY()+presAnimY, presAnimX, presAnimY, presAnimWidth, presAnimHeight, 1, 1, 0);
				batcher.setColor(c);
			}
			
			nameHolder.draw(batcher);
		}
		
		if( isInUse && presFinished) {		//isInUse &&
			
			childDraw(batcher);
			
			drawDurationBar(batcher);
			
		}
	}
	
	public void drawPowerAvailableIndicator(Batch batcher) {
		if(!isInUse && world.getBird().isAlive()) {
			float width = FPConstants.gameWidth*0.8f;
			float height = FPConstants.gameHeight*0.15f;
			batcher.draw(AssetLoader.powerAvailable, FPConstants.gameWidth/2-width/2, FPConstants.gameHeight-(FPConstants.nonPlayableHeight-FPConstants.groundHeight)/2-height/2, width, height);

		}
		
	}
	
	public void drawDurationBar(Batch batcher) {
		

		float dBarBorderWidth = dBarWidth*0.1f;
		float dBarLineWidth = dBarWidth-2*dBarBorderWidth*0.8f -(dBarWidth-2*dBarBorderWidth*0.8f)*elapsed/duration;
		
		float dBarMovingX = dBarX+dBarBorderWidth*0.8f + (dBarWidth-2*dBarBorderWidth*0.8f)*elapsed/duration;
		
		Color c=batcher.getColor();
		batcher.setColor(color);
		batcher.draw(AssetLoader.dBar, dBarMovingX, dBarY+dBarHeight*0.3f, dBarLineWidth, dBarHeight*0.4f);
		batcher.setColor(c);
		
		batcher.draw(AssetLoader.dBarLeftBorder, dBarX, dBarY, dBarBorderWidth, dBarHeight);
		batcher.draw(AssetLoader.dBarBody, dBarX+dBarBorderWidth, dBarY, dBarWidth-2*dBarBorderWidth, dBarHeight);
		batcher.draw(AssetLoader.dBarRightBorder, dBarX+dBarWidth-dBarBorderWidth, dBarY, dBarBorderWidth, dBarHeight);
		
		//batcher.setColor(c);

	}
	
	public void drawBg(Batch batcher) {
		
		if(isInUse) {
			if(!presFinished) {
				bgSprite.draw(batcher);
			}
		}
	}
	
	

	
	
//	public void drawTextCentered(Batch batcher, String str, float areaX, float areaY, float areaWidth, float areaHeight, BitmapFont font, float scale) {
//		
//		float prevScaleX =font.getScaleX();
//		float prevScaleY = font.getScaleY();
//		font.setScale(prevScaleX*scale, prevScaleY*scale);
//		TextBounds tb = font.getBounds(str);
//
//		font.draw(batcher, str, center(areaX,tb.width, areaWidth),  center(areaY,-tb.height, areaHeight));
//		
//    	font.setScale(prevScaleX, prevScaleY);
//
//		
//	}
//	
//	public float center (float pos, float length, float areaLength) {
//		return pos+areaLength/2-length/2;
//	}
	
	public void stop() {
		
		isInUse = false;
		
		world.getBird().deactivatePowerFace();
		
		resetBooleans();
		
		childStop();
		
		resetBgSprite();
		
		soundMgr.resetMusicVolume();
		
		elapsed=0;
		
		justFinished = true;
		available = true;	//Comentar en vFinal
	}
	
	public void interrupt() {
		
		isInUse = false;
		
		world.getBird().deactivatePowerFace();
		
		resetBooleans();
		
		soundMgr.resetMusicVolume();
		
		childInterrupt();
	}
	
	public void reset() {
		
		available = true; //Comentar en vFinal

		resetBooleans();
		
		childReset();
		
		resetBgSprite();
		
		elapsed=0;

	}
	
	protected void resetBooleans() {
		firstUpdateRunningCall = true;
		firstUpdatePresCall = true;
		presFinished = false;
	}
	
	
	public void setPresAnimation(Animation anim, float x, float y, float w,float h) {
		hasPresAnim = true;
		presAnimX = x;
		presAnimY = y;
		presAnimHeight = h;
		presAnimWidth = w;
		
		presAnim = anim;
	}
	
	public void onClick(float screenX, float screenY) {
		//Override if necesary
	}

	public boolean isInUse() {
		return isInUse;
	}
	
	public void extendDuration(float extraDuration) {
		
		elapsed -= extraDuration;
		
		if (elapsed < 0) elapsed = 0;
	}

	

	
	protected abstract void childUpdate(float runTime);
	public abstract void childReset();
	public abstract void childStop();
	public abstract void childInterrupt();
	public abstract void childDraw(Batch batcher);

	public PowerSoundManager getSoundMgr() {
		return soundMgr;
	}

	public boolean isJumpSoundAllowed() {
		if(isInUse){
			return soundMgr.isJumpSoundOn();
		}
		else return true;
	}

	public boolean isScoreSoundAllowed() {
		if(isInUse)return soundMgr.isScoreSoundOn();
		
		else return true;
	}

	public void playPowerJumpSound() {
		soundMgr.playPibSound();
		
	}

	public void setPresTime(float t) {
		presTime = t;
		setupTween();
		
	}
	

	public void enableAlternateSprites(TextureRegion bg,TextureRegion face, float f) {
		altFace = face;
		altBgSprite =  new Sprite(bg);
		altProb = f;
		altEnabled = true;
	}






}
