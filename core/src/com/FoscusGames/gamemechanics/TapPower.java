package com.FoscusGames.gamemechanics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.fpHandlers.FPConstants;
import com.FoscusGames.fpHandlers.ScoreManager;
import com.FoscusGames.gameobjects.PowerToken;
import com.FoscusGames.gameobjects.ScrollableSprite;
import com.FoscusGames.gameworld.GameWorld;
import com.FoscusGames.sound.PowerSoundManager;
import com.FoscusGames.ui.TextIndicator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TapPower extends Power {
	
	protected TextureRegion tapImg;
	protected List<ScrollableSprite> tapTokens;
	protected List<TextIndicator> scoreIndicators;

	protected float tapImgHeight;
	protected float tapImgWidth;
	
	int pipeTapCounter;
	
	float tomiY;
	boolean tomiGenerated;
	float scrollSpeed;
	ScrollableSprite tomi;
	
	public TapPower(Animation a, GameWorld w, float d, Sprite s, String n, TextureRegion powerFace, TextureRegion tapImage,Color c,PowerSoundManager sM) {
		super(a, w, d, s, n, powerFace,c,sM);
		
		tapImg = tapImage;
		tapTokens = new ArrayList<ScrollableSprite>();
		scoreIndicators = new ArrayList<TextIndicator>();
		
		tapImgHeight = 20*0.8f;
		tapImgWidth= 40*0.8f;
		
		pipeTapCounter=0;
		tomiGenerated = false;
		
		scrollSpeed = w.getScroller().getNormalScrollSpeed()*1.5f;
	}
	
	@Override
	protected void use(float runTime) {
		super.use(runTime);
		
		//world.getBird().unMaterialize();
		
		
	}
	
	
	public void addScoreIndicator(float x, float y, String text) {

		scoreIndicators.add(new TextIndicator(x,y,text,color));
	}


	@Override
	public void onClick(float tapX, float tapY) {
		
		if (isInUse) {
			
			if(isTapOnTomi(tapX,tapY)) {
				
				int tomiValue =5;
				float tomiDur = duration*0.4f;
				
				ScoreManager.addScore(tomiValue);
				extendDuration(tomiDur);
				
				scrollSpeed *=1.5f;
				
				addFiredSprite(tapX, tapY, tapImgWidth*1.5f, tapImgHeight*1.5f);
				addScoreIndicator(tapX, tapY, "+"+tomiValue);
				addScoreIndicator(dBarX+dBarWidth/2-2.5f, dBarY, "+"+tomiDur+" secs");
				
				destroyTomi();
			}
			
			else if(world.getScroller().isTapOnPipe(tapX, tapY)) {
				
				int pipeValue = 1;
				
				world.getScroller().destroyTappedPipe(tapX, tapY);
				
				ScoreManager.addScore(pipeValue);
				
				addFiredSprite(tapX, tapY, tapImgWidth, tapImgHeight);
				addScoreIndicator(tapX, tapY, "+"+pipeValue);
				
				pipeTapCounter++;
				
				if(pipeTapCounter>10) {
					generateTomi();
					pipeTapCounter = 0;
				}
			}
			

		
		}
		
	}
	
	public void addFiredSprite(float tapX, float tapY, float width, float height) {
		ScrollableSprite s = new ScrollableSprite(tapImg, world.getScroller().getScrollSpeed());
		s.setBounds(tapX-width/2, tapY-height/2, width, height);
		tapTokens.add(s);
	}
	
	public void generateTomi() {
		
		tomi = new ScrollableSprite(AssetLoader.tomiDead,  world.getScroller().getScrollSpeed()*1.7f);
		
		Random r = new Random();
		
		tomi.setBounds(FPConstants.gameWidth, tomiY, 20, 30);
		
		tomiY = r.nextFloat()*(FPConstants.playableHeight-tomi.getHeight());

		
		tomiGenerated = true;
		
	}
	
	public void destroyTomi() {
		
		tomiGenerated = false;
		
		
	}
	
	
	public boolean isTapOnTomi(float tapX,float tapY) {
		
		if (tomiGenerated) {
			
			if(tapX >= tomi.getX() && tapX <= tomi.getX()+tomi.getWidth()) {
				if(tapY >= tomi.getY() && tapY <= tomi.getY()+tomi.getHeight()) {
					return true;
				}
				return false;
			}
			return false;
			
		}
		
		return false;
	}
	
	@Override
	protected void childUpdate(float runTime) {
		
		
		world.getScroller().setScrollSpeed(scrollSpeed);

		
		for (int i = 0; i<tapTokens.size(); i++) {

			ScrollableSprite s = tapTokens.get(i);
			s.update(Gdx.graphics.getDeltaTime());
			if (s.isScrolledLeft()) {
				tapTokens.remove(s);
			}
		}
		
		if (tomiGenerated) {
			tomi.setY((float)(tomiY+10*Math.sin(5*runTime)));
			
			tomi.update(Gdx.graphics.getDeltaTime());
			
			if(tomi.isScrolledLeft()) {
				destroyTomi();
			}
		}
		
		
		updateScoreIndicators(runTime);


	}
	
	public void updateScoreIndicators(float runTime) {
		for (int i=0;i<scoreIndicators.size();i++) {
			
			scoreIndicators.get(i).update(runTime);
			if (scoreIndicators.get(i).isFinished()) {
				scoreIndicators.remove(i);
			}
			
		}
	}
	
	@Override
	public void childStop() {
		
		world.getBird().materialize();
		world.getScroller().resetScrollSpeed();
		tapTokens.removeAll(tapTokens);
		pipeTapCounter = 0;
		scrollSpeed = world.getScroller().getNormalScrollSpeed()*1.5f;
		scoreIndicators.removeAll(scoreIndicators);
		destroyTomi();

	}

	@Override
	public void childReset() {
		world.getBird().materialize();
		world.getScroller().resetScrollSpeed();
		pipeTapCounter = 0;
		scrollSpeed = world.getScroller().getNormalScrollSpeed()*1.5f;
		tapTokens.removeAll(tapTokens);
		scoreIndicators.removeAll(scoreIndicators);
		destroyTomi();

	}


	@Override
	public void childInterrupt() {

	}


	@Override
	public void childDraw(Batch batcher) {
		
		for (ScrollableSprite s : tapTokens) {

			s.draw(batcher);
		}
		
		for (TextIndicator t : scoreIndicators) {

			t.draw(batcher);
		}

		
		if(tomiGenerated) tomi.draw(batcher);

	}

}
