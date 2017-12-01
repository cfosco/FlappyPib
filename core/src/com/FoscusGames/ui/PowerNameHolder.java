package com.FoscusGames.ui;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.fpHandlers.FPConstants;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;

public class PowerNameHolder extends Holder {
	
	float width,height;
	float nameTextX, nameHolderX;
	float presTime;
	String name;
	Color color;
	
	public PowerNameHolder(String nam, float pTime, Color color) {

		width = FPConstants.gameWidth*1.4f;
		height = FPConstants.gameHeight*0.15f;
		nameTextX=FPConstants.gameWidth;
		nameHolderX=-width;
		Y = FPConstants.gameHeight*0.6f;
		
		name=nam;
		presTime=pTime;
		this.color = color; 
		
	}
	
	public void update(float runTime) {
		
		
		float d = FPConstants.gameWidth*0.1f;
		float start = -width;
		float A = FPConstants.gameWidth/2-width/2-d;
		float B = FPConstants.gameWidth/2-width/2+d;
		float end = FPConstants.gameWidth;
		
			
		
		nameHolderX = nameMovFunction(runTime,start,A,B,end);
		nameTextX = nameMovFunction(runTime,end,B,A,start); 
	
	}

	public void draw(Batch batcher) {
		draw(batcher, 1f);
	}
	
	@Override
	public void draw(Batch batcher, float parentAlpha) {
		Color c = batcher.getColor();
		batcher.setColor(color);
		
		batcher.draw(AssetLoader.powerNameHolder, nameHolderX, Y, 0, 0,  width,height, 1,1,0);
		drawTextCentered(batcher, name, nameTextX, Y+height*0.1f, width, height*0.8f, AssetLoader.font, 0.75f);

		batcher.setColor(c);

		
	}

	
	protected float nameMovFunction(float t, float startPoint, float A, float B, float endPoint) {
		
		float t1 = presTime*0.2f;
		float t2 = presTime*0.6f+t1;
		float t3 = presTime;
		
		
		if(t<t1) {
			return startPoint+(A-startPoint)*t/t1;
		}
		
		else if(t<t2) {
			return t*(B-A)/(t2-t1)+(B-t2*(B-A)/(t2-t1));
		}
		
		else {
			return t*(endPoint-B)/(t3-t2)+(B-t2*(endPoint-B)/(t3-t2));
		}
		
		
	}


}

