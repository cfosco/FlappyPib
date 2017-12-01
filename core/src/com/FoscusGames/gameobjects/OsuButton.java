package com.FoscusGames.gameobjects;

import java.util.Random;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.fpHandlers.SoundLoader;
import com.badlogic.gdx.graphics.g2d.Batch;

public class OsuButton {

	
	float elapsed, duration, maxCircleDiameter, circleDiameter, buttonDiameter,x,y,startTime;
	boolean started,finished;
	Random r;
	
	public OsuButton(float x, float y, float d) {
		
		elapsed = 0;
		duration = 1.8f;
		
		this.x=x;
		this.y=y;

		buttonDiameter = d;
		
		maxCircleDiameter = 3*buttonDiameter;
		
		circleDiameter = maxCircleDiameter;
		
		started = false;
		finished = false;
		startTime = 0;
		
		r = new Random();
	}

	public void update(float runTime) {
		
		if(started) {
			elapsed = runTime-startTime;
			
			if(elapsed>duration) {
				finished = true;
				elapsed = duration;
			}
			
			
			circleDiameter = maxCircleDiameter - (maxCircleDiameter-buttonDiameter)*elapsed/duration;
			
		}
		
		else {
			started = true;
			startTime = runTime;
		}

	}
	

	public boolean isFinished() {
		return finished;
	}
	
	public float getDuration () {
		return duration;
	}
	
	public float getElapsed() {
		return elapsed;
	}
	
	public float getButtonDiameter() {
		return buttonDiameter;
	}
	
	
	
	public void draw(Batch batcher) {
	
		batcher.draw(AssetLoader.BRPibArea, x-buttonDiameter/2, y-buttonDiameter/2, buttonDiameter, buttonDiameter);
		batcher.draw(AssetLoader.whiteCircle, x-circleDiameter/2,y-circleDiameter/2,circleDiameter,circleDiameter);
		
	}

	public boolean isTapped(float tapX, float tapY) {

		if(tapX>=x-buttonDiameter/2 && tapX<=x+buttonDiameter/2) {
			if(tapY>=y-buttonDiameter/2 && tapY <= y+buttonDiameter/2){
				return true;
			}
			return false;
		}
		
		return false;
	}

	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}

	public void playFailSound() {
		
		
		SoundLoader.coin.play();
		
	}

	public void playT1Sound() {	
		
		SoundLoader.reimonFoscoSounds.get(1).play();		
	}
	
	public void playT2Sound() {	
		
		SoundLoader.reimonFoscoSounds.get(0).play();		
	}
	
	public void playT3Sound() {	
		
		
		int i = r.nextInt(SoundLoader.reimonFogSounds.size());
		
		SoundLoader.reimonFogSounds.get(i).play();		
	}
	
	
	
	
}
