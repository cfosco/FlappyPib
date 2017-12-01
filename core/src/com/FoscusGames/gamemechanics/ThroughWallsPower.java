package com.FoscusGames.gamemechanics;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.gameworld.GameWorld;
import com.FoscusGames.sound.PowerSoundManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ThroughWallsPower extends Power {
	
	
	public ThroughWallsPower (Animation a, GameWorld w, float duration, Sprite s, String name, TextureRegion face, Color c,PowerSoundManager sM) {
		super(a,w,duration, s, name, face,c,sM);
		
	}
	
	@Override
	protected void use(float runTime) {
		super.use(runTime);
		world.getBird().unMaterialize();
		
	}
	
	@Override
	public void childStop() {
		world.getBird().changeOpacity(1.0f);
		world.getBird().materialize();
		world.getScroller().resetScrollSpeed();
		
	}
	
	@Override
	public void childInterrupt() {
		stop();
		
	}
	
	@Override
	public void childReset() {
	}


	@Override
	public void childDraw(Batch batcher) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void childUpdate(float runTime) {
		world.getBird().changeOpacity((float)(Math.sin(5*runTime)+2)/4);


		world.getScroller().setScrollSpeed(-100);
	}


}
