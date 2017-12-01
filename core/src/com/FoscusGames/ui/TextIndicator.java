package com.FoscusGames.ui;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;

public class TextIndicator {
	
	String text;
	Color color;
	float movType, duration,x,y,width, height,scale, distance, initialY,elapsed,startTime;
	boolean started,finished;
	BitmapFont font;
	
	public static int VERTICAL = 1;
	
	public TextIndicator(float x, float y, float areaWidth, float areaHeight, float scale, String text, int movType, float duration, float distance, Color c, BitmapFont f) {
		this.text = text;
		this.movType = movType;
		this.duration = duration;
		
		this.x = x;
		this.y=y;
		this.initialY = y;
		this.scale=scale;
		this.width = areaWidth;
		this.height = areaHeight;
		this.distance = distance;

		this.elapsed = 0;
		this.color = c;
		started = false;
		font = f;
	}

	public TextIndicator(float x, float y, String text, Color c) {
		this.text = text;
		this.movType = TextIndicator.VERTICAL;
		this.duration = 0.6f;
		
		this.x = x;
		this.y=y;
		this.scale=0.4f;

		this.width = 5;
		this.height = 5;
		this.distance = 18;
		this.initialY = y;
		this.elapsed = 0;
		this.color = c;
		
		started = false;
		font = AssetLoader.font;
	}
	
	public TextIndicator(float x, float y, String text) {
		this.text = text;
		this.movType = TextIndicator.VERTICAL;
		this.duration = 0.6f;
		
		this.x = x;
		this.y=y;
		this.scale=0.4f;

		this.width = 5;
		this.height = 5;
		this.distance = 18;
		this.initialY = y;
		this.elapsed = 0;
		this.color = Color.WHITE;
		font = AssetLoader.font;
		
		started = false;
	}
	
	public TextIndicator(float x, float y, String text, float distance, float duration, BitmapFont font, float scale) {
		this.text = text;
		this.movType = TextIndicator.VERTICAL;
		this.duration = duration;
		
		this.x = x;
		this.y=y;
		this.scale=scale;

		this.width = 5;
		this.height = 5;
		this.distance = distance;
		this.initialY = y;
		this.elapsed = 0;
		this.color = Color.WHITE;
		this.font = font;
		
		started = false;
	}
	
	
	public void draw(Batch batcher) {
		
		
		drawTextCentered(batcher, text, x,y,width, height, font, scale);
		
	}
	
	public void update(float runTime) {
	
		
		if(started) {
			elapsed = runTime-startTime;
			if(elapsed>duration) {
				finished = true;
				elapsed = duration;
			}
			
			if (movType == VERTICAL) {
				y = initialY-(distance)*elapsed/duration;
				
			}
		
		}
		
		else {
			startTime = runTime;
			started = true;
		}
		
		
	}
	
	
	public boolean isFinished() {
		return finished;
	}
	
	public void drawTextWrapped(Batch batcher, String str, float X, float Y, float areaWidth, BitmapFont font, float scale ) {
		
    	
    	
		float prevScaleX =font.getScaleX();
		float prevScaleY = font.getScaleY();
		font.setScale(prevScaleX*scale, prevScaleY*scale);
    	font.drawWrapped(batcher, str,  X, Y, areaWidth);
    	font.setScale(prevScaleX, prevScaleY);
    	
    	
		
	}
	
	public void drawTextCenteredColor(Batch batcher, String str, float areaX, float areaY, float areaWidth, float areaHeight, BitmapFont font, float scale, Color color) {
		Color c = batcher.getColor();
		
		batcher.setColor(color);
		
		drawTextCentered(batcher, str, areaX,areaY,areaWidth, areaHeight, font, scale);
		
		batcher.setColor(c);
	}

	public void drawTextCentered(Batch batcher, String str, float areaX, float areaY, float areaWidth, float areaHeight, BitmapFont font, float scale) {
		
		float prevScaleX =font.getScaleX();
		float prevScaleY = font.getScaleY();
		font.setScale(prevScaleX*scale, prevScaleY*scale);
		TextBounds tb = font.getBounds(str);

		font.draw(batcher, str, center(areaX,tb.width, areaWidth),  center(areaY,-tb.height, areaHeight));
		
    	font.setScale(prevScaleX, prevScaleY);

		
	}
	

	public float center (float pos, float length, float areaLength) {
		return pos+areaLength/2-length/2;
	}
	
}
