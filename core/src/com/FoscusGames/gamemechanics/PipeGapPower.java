package com.FoscusGames.gamemechanics;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.gameworld.GameWorld;
import com.FoscusGames.sound.PowerSoundManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PipeGapPower extends Power {
	
	private float bigPipeGap;

	public PipeGapPower(Animation a, GameWorld w, float d, Sprite s, String name, TextureRegion face, Color c,PowerSoundManager sM) {
		super(a, w, d, s, name, face,c,sM);
		
		bigPipeGap = 60;
	}

	@Override
	protected void use(float runTime) {
		
		super.use(runTime);
		
		world.getScroller().changePipeVerticalGap(bigPipeGap);
		
	}


	@Override
	public void childStop() {
		world.getScroller().resetPipeVerticalGap();

		
	}
	
	@Override
	public void childInterrupt() {
	}
	
	@Override 
	public void childReset() {
		world.getScroller().resetPipeVerticalGap();
	}

	@Override
	public void childDraw(Batch batcher) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void childUpdate(float runTime) {
		// TODO Auto-generated method stub
		
	}

}
