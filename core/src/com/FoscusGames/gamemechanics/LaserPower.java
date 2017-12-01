package com.FoscusGames.gamemechanics;

import java.util.ArrayList;
import java.util.List;

import com.FoscusGames.fpHandlers.FPConstants;
import com.FoscusGames.fpHandlers.ScoreManager;
import com.FoscusGames.gameobjects.Laser;
import com.FoscusGames.gameworld.GameWorld;
import com.FoscusGames.sound.PowerSoundManager;
import com.FoscusGames.ui.TextIndicator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LaserPower extends Power {
	
	private float timeBetweenLasers;
	private float laserSpeed;
	private float lastLaserTime;
	private List<Laser> lasers;
	private float eyeCst;
	private int laserValue;
	
	private List<TextIndicator> scoreIndicators;

	public LaserPower(Animation a, GameWorld w, float d, Sprite s, String name, TextureRegion face, Color c,PowerSoundManager sM) {
		super(a, w, d, s, name, face,c,sM);

		timeBetweenLasers =0.1f;
		laserSpeed = 6f;
		lastLaserTime = 0;
		eyeCst = 4;
		laserValue = 2;
		lasers = new ArrayList<Laser>();
		scoreIndicators = new ArrayList<TextIndicator>();
		
	}
	
//	@Override
//	protected void use(float runTime) {
//		
//		super.use(runTime);
//		
//		
//	}
	
	@Override
	protected void childUpdate(float runTime) {
		
		//Gdx.app.log("LaserPower","runTime "+runTime+", lastlasertime "+lastLaserTime);

		addNewLaser(runTime);
		updateLasers(runTime);
		updateScoreIndicators(runTime);
	}
	
	public void addNewLaser(float runTime) {
		if (runTime - lastLaserTime >= timeBetweenLasers) {
			lasers.add(new Laser(world.getBird().getX(), world.getBird().getY()+eyeCst, laserSpeed, world.getScroller()));		//10 = eyesCst
			lastLaserTime = runTime;
			Gdx.app.log("LaserPower","addingLaser");
		}
	}
	
	public void updateLasers(float runTime) {
		for (int i=0;i<lasers.size();i++) {
			
			lasers.get(i).update(runTime);
			if (lasers.get(i).hit) {
				
				scoreIndicators.add(new TextIndicator(FPConstants.gameWidth/2+15,FPConstants.gameHeight*0.15f,"+"+laserValue, color));
				ScoreManager.addScore(laserValue);
				lasers.remove(lasers.get(i));

			}
			else if (lasers.get(i).tooFar) {
				lasers.remove(lasers.get(i));
			}
			
		}
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
	public void childDraw(Batch batcher) {

		for (Laser l : lasers) {

			l.draw(batcher);
		}
		
		for (TextIndicator t : scoreIndicators) {

			t.draw(batcher);
		}
		
	}
	
	
	@Override 
	public void childReset() {
		lastLaserTime=0;
	}

	@Override
	public void childStop() {
		
		lasers.removeAll(lasers);
		scoreIndicators.removeAll(scoreIndicators);
		lastLaserTime=0;

	}

	@Override
	public void childInterrupt() {
		lasers.removeAll(lasers);
		scoreIndicators.removeAll(scoreIndicators);
	}

	
	


	

}
