package com.FoscusGames.gamemechanics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.fpHandlers.FPConstants;
import com.FoscusGames.gameobjects.OsuButton;
import com.FoscusGames.gameworld.GameWorld;
import com.FoscusGames.sound.PowerSoundManager;
import com.FoscusGames.ui.TextIndicator;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DashTapPower extends DashPower {

	List<TextIndicator> indicators;
	List<OsuButton> buttons;
	float maxExtraDuration,lastButtonTime,timeBetweenButtons,precision;
	float buttonDiameter;
	boolean nailedIt;
	float MAX_SIMULTANEOUS_BUTTONS;
	float T1,T2,T3;
	Random r;
	BitmapFont font;
	
	public DashTapPower(Animation a, GameWorld w, float d, Sprite s, float speed, String name, TextureRegion face, Color c,PowerSoundManager sM) {
		super(a, w, d, s, speed, name, face, c,sM);
		
		indicators = new ArrayList<TextIndicator>();
		buttons = new ArrayList<OsuButton>();
		maxExtraDuration = duration*0.2f;
		nailedIt = false;
		r=new Random();
		lastButtonTime = -1f;
		timeBetweenButtons = 1f;
		
		font = AssetLoader.blueFont;
		
		precision = 0;
		
		buttonDiameter = 15;
		

		MAX_SIMULTANEOUS_BUTTONS =5;
		T1 = 0.97f;
		T2 = 0.9f;
		T3 = 0.8f;
		
	}
	
	@Override
	public void use() {
		super.use();
		
		//world.getBird().setStaticY(FPConstants.playableHeight/2);
	}
	
	@Override
	protected void childUpdate(float runTime) {
	
		super.childUpdate(runTime);
		
		addNewButton(runTime);

		updateButtons(runTime);
		
		updateIndicators(runTime);



	}
	
	@Override 
	public void childDraw(Batch batcher) {
		
		super.childDraw(batcher);
		
		if(isInUse){
			for(OsuButton b : buttons) {
				b.draw(batcher);
			}
			
			for(TextIndicator t : indicators) {
				t.draw(batcher);
			}
		}
		
	}
	

	public void addNewButton(float runTime) {
		
		
		
		
		if(buttons.size()<MAX_SIMULTANEOUS_BUTTONS) {
			if(runTime - lastButtonTime > timeBetweenButtons ) {
				
				float x,y;
				float count =0;
				
				do {
					x = r.nextFloat()*(FPConstants.gameWidth-buttonDiameter)+buttonDiameter;
					y = r.nextFloat()*(FPConstants.playableHeight-buttonDiameter)+buttonDiameter;
					count++;
				} while(!checkPositions(x,y) || count<10);
				
				
				buttons.add(new OsuButton(x,y, buttonDiameter));
				lastButtonTime = runTime;
				timeBetweenButtons = r.nextFloat()*1.2f+0.4f;
			}
			
		}
		
	}
	
	protected boolean checkPositions(float x, float y) {
		
		for(OsuButton b:buttons) {
			if(b.isTapped(x,y)) return false;
		}
		
		return true;
	}
	
	public void updateButtons(float runTime) {
		if(isInUse){
			for (OsuButton b : buttons) {
				b.update(runTime);
			}
		}
		

		for(int i=0;i<buttons.size();i++) {
			if (buttons.get(i).isFinished()) {
				float dur =-maxExtraDuration*0.4f;
				extendDuration(dur);
				addIndicator(dBarX+dBarWidth/2-2.5f, dBarY, dur+" secs");
				addBIndicator(buttons.get(i).getX(), buttons.get(i).getY(), "...",-17,0.8f);
				buttons.get(i).playFailSound();
				buttons.remove(i);
			}
		}
		
	}
	

	@Override
	public void onClick(float tapX, float tapY) {
		
		if (isInUse) {
			
				
			
				OsuButton tappedButton = getTappedButton(tapX,tapY);
				
				if(tappedButton != null) {

					float roll = r.nextFloat();

					float dur = getDurationFromTappedButton(tappedButton);
					
					extendDuration(dur);
					
					if (precision == T1) {
						 addBIndicator(tapX, tapY, "Fosco!!!!",16,0.6f,1.7f);
						 tappedButton.playT1Sound();
						
					} else if (precision == T2) {
						if(roll<=0.8f) addBIndicator(tapX, tapY, "Fosco!",16,0.6f);
						else addBIndicator(tapX, tapY, "Cuchame Fosco",16,0.6f);
						tappedButton.playT2Sound();
						
					} else if (precision == T3) {
						addBIndicator(tapX, tapY, "Fosco...",16,0.6f);
						tappedButton.playT3Sound();
					}
					else {
						
						float dist = -15;
						float indicatorDur = 0.8f;
						
						tappedButton.playFailSound();
						
						if(roll<=0.1f)addBIndicator(tapX, tapY, "Fosh...", dist,indicatorDur);
						else if(roll<=0.2f)addBIndicator(tapX, tapY, "Fosc...",dist,indicatorDur);
						else if(roll<=0.3f)addBIndicator(tapX, tapY, "Ugh...",dist,indicatorDur);
						else if(roll<=0.4f)addBIndicator(tapX, tapY, "Bluegh...",dist,indicatorDur);
						else if(roll<=0.5f)addBIndicator(tapX, tapY, "FFF...",dist,indicatorDur);
						else if(roll<=0.6f)addBIndicator(tapX, tapY, "gua...",dist,indicatorDur);
						else if(roll<=0.7f)addBIndicator(tapX, tapY, "gghe...",dist,indicatorDur);
						else if(roll<=0.8f)addBIndicator(tapX, tapY, "Aguaa...",dist,indicatorDur);
						else if(roll<=0.9f)addBIndicator(tapX, tapY, "Fogsc...",dist,indicatorDur);
						else addBIndicator(tapX, tapY, "Agu...",dist,indicatorDur);
					}
					
					addIndicator(dBarX+dBarWidth/2-2.5f, dBarY, dur+" secs");
					
					removeTappedButton(tappedButton);
				
				}
				
				
				
				
				
			}
		
		}
		
	
	public OsuButton getTappedButton(float tapX, float tapY) {
		
		for (OsuButton b : buttons) {
			if (b.isTapped(tapX, tapY)) {
				return b;
			}
		}
		
		return null;
	}
	
	public void removeTappedButton(OsuButton b) {
		
		buttons.remove(b);
		
	}
	
	
	public float getDurationFromTappedButton(OsuButton b) {
		
		
		float t= b.getElapsed()/b.getDuration();
		
		if (t >= T1) {
			precision = T1;
			return maxExtraDuration;
		}
		
		else if (t >=T2) {

			precision = T2;
			return maxExtraDuration*0.8f;
		}
		
		else if (t >= T3) {

			precision = T3;
			return maxExtraDuration*0.2f;
		}
		
		else {
			precision = 0;
			return -maxExtraDuration*0.2f;
		}
		
		
	}
	


	public void addIndicator(float x, float y, String text) {

		indicators.add(new TextIndicator(x,y,text));
	}
	
	public void addBIndicator(float x, float y, String text, float dist, float dur) {

		indicators.add(new TextIndicator(x,y,text,dist,dur,font,1.2f));
	}
	public void addBIndicator(float x, float y, String text, float dist, float dur, float scale) {

		indicators.add(new TextIndicator(x,y,text,dist,dur,font,scale));
	}
	
	public void updateIndicators(float runTime) {
		for (int i=0;i<indicators.size();i++) {
			
			indicators.get(i).update(runTime);
			if (indicators.get(i).isFinished()) {
				indicators.remove(i);
			}
			
		}
	}
	
	@Override
	public void childStop() {
		
		super.childStop();
		indicators.removeAll(indicators);
		buttons.removeAll(buttons);
		lastButtonTime = -1;

	}

	@Override
	public void childReset() {

		super.childReset();
		indicators.removeAll(indicators);
		buttons.removeAll(buttons);
		lastButtonTime =-1;

	}


	
}
