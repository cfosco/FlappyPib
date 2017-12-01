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

public class JumpDashPower extends DashPower {

	public JumpDashPower(Animation a, GameWorld w, float d, Sprite s,
			float speed, String name, TextureRegion face, Color c,PowerSoundManager sM) {
		super(a, w, d, s, speed, name, face, c,sM);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void childDraw(Batch batcher) {
		
		super.childDraw(batcher);
	}
	
	@Override
	protected void use(float runTime) {
		
		super.use(runTime);

		world.getBird().unMaterialize();
		
		world.getBird().setJumpFromGround(true);
		
	}
	
	@Override 
	protected void childUpdate(float runTime) {
		
			super.childUpdate(runTime);
		
			
			world.getBird().hide();
			
	}
	
	
	

	
	@Override
	public void childStop() {
		
		super.childStop();
		world.getBird().unhide();

		
		world.getBird().setJumpFromGround(false);

		
	}
	

	@Override 
	public void childReset() {

		
		//world.getBird().setJumpFromGround(false);

	}
	
	@Override
	public void childInterrupt() {
//		super.childInterrupt();
//		
//		world.getBird().unhide();

	}



}
