package com.FoscusGames.ui;

import java.util.ArrayList;
import java.util.List;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

public abstract class Holder extends Widget {
	

	protected float X;
	protected float Y;
	protected float scale = 1;
	protected float rot = 0;
	
	
	

	public float center (float pos, float length, float areaLength) {
		return pos+areaLength/2-length/2;
	}
//	
//	public float getX() {
//		return X;
//	}
//	
//	public float getY() {
//		return Y;
//	}
	
	public float getScale() {
		return scale;
	}
	
	public void drawInSlot(Batch batcher, TextureRegion sprite, float slotX, float slotY, float originX, float originY, float slotWidth, float slotHeight) {
		
		float sWidth = sprite.getRegionWidth();
		float sHeight = sprite.getRegionHeight();
		
		
		
		batcher. draw(sprite, center(slotX, sWidth*slotHeight/sHeight, slotWidth), center(slotY, slotHeight, slotHeight), originX, originY, sWidth*slotHeight/sHeight, slotHeight, scale, scale, rot);

		
	}
	
	public void drawTextWrapped(Batch batcher, String str, float X, float Y, float areaWidth, BitmapFont font, float scale ) {
		
    	
    	
		float prevScaleX =font.getScaleX();
		float prevScaleY = font.getScaleY();
		font.setScale(prevScaleX*scale, prevScaleY*scale);
    	font.drawWrapped(batcher, str,  X, Y, areaWidth);
    	font.setScale(prevScaleX, prevScaleY);
    	
    	
		
	}
	
	public void drawTextCenteredAndWrapped(Batch batcher, String str, float areaX, float areaY, float areaWidth, float areaHeight, BitmapFont font, float scale) {
		


    	
		float prevScaleX =font.getScaleX();
		float prevScaleY = font.getScaleY();
		font.setScale(prevScaleX*scale, prevScaleY*scale);
    	
		TextBounds tb = font.getWrappedBounds(str, areaWidth);
		
    	font.drawWrapped(batcher, str,  center(areaX, tb.width, areaWidth), center(areaY, -tb.height, areaHeight), areaWidth);
    	font.setScale(prevScaleX, prevScaleY);
		
	}
	
	public void drawTextCenteredMultiline(Batch batcher, String[] lines, float areaX, float areaY, float areaWidth, float areaHeight, BitmapFont font, float scale) {
		
		
		for(int i = 0; i<lines.length; i++) {
			
		drawTextCentered(batcher, lines[i], areaX, areaY+i*areaHeight/lines.length, areaWidth, areaHeight/lines.length, font, scale);
		
		}
		
	}
	
	public void drawTextCenteredMultiline(Batch batcher, List<String> lines, float areaX, float areaY, float areaWidth, float areaHeight, BitmapFont font, float scale) {
		
		
		int i = 0;
		
		for(String str : lines) {
			
			drawTextCentered(batcher, str, areaX, areaY+i*areaHeight/lines.size(), areaWidth, areaHeight/lines.size(), font, scale);
			i++;
		}
		
	}
	
	public void drawTextMultilineAutoCentered(Batch batcher, String str, float areaX, float areaY, float areaWidth, float areaHeight, BitmapFont font, float scale) {
		
		//No mandar una str rara, tipo un espacio, porque explota todo
		
		float prevScaleX =font.getScaleX();
		float prevScaleY = font.getScaleY();
		font.setScale(prevScaleX*scale, prevScaleY*scale);
		
		List<String> lines = divideString(str, areaWidth, font);
		
		drawTextCenteredMultiline(batcher, lines, areaX,areaY, areaWidth, areaHeight, font, 1);
		
	 	font.setScale(prevScaleX, prevScaleY);
	}
		
	public void drawTextCentered(Batch batcher, String str, float areaX, float areaY, float areaWidth, float areaHeight, BitmapFont font, float scale) {
		
			float prevScaleX =font.getScaleX();
			float prevScaleY = font.getScaleY();
			font.setScale(prevScaleX*scale, prevScaleY*scale);
			TextBounds tb = font.getBounds(str);

			font.draw(batcher, str, center(areaX,tb.width, areaWidth),  center(areaY,-tb.height, areaHeight));
			
	    	font.setScale(prevScaleX, prevScaleY);

			
		}
	
	public void drawTextCenteredY(Batch batcher, String str, float posX, float areaY, float areaHeight, BitmapFont font, float scale) {
		
		float prevScaleX =font.getScaleX();
		float prevScaleY = font.getScaleY();
		font.setScale(prevScaleX*scale, prevScaleY*scale);
		TextBounds tb = font.getBounds(str);

		font.draw(batcher, str, posX,  center(areaY,-tb.height, areaHeight));
		
    	font.setScale(prevScaleX, prevScaleY);

		
	}
	
	
	public List<String> divideString (String str, float areaWidth, BitmapFont font) {
		
		TextBounds tb = font.getBounds(str);
		List<String> multiLineStr = new ArrayList<String>();

		String line = "";
		String lineAux;
		
		
		if (tb.width>areaWidth) {
			
			if (str.contains(" ")) {
				
				
				String[] parts = str.split(" ");
				
				for (String s : parts) {
					
					lineAux = line.concat(s+ " ");
					
					tb = font.getBounds(lineAux);
					
					if (tb.width>areaWidth) {

						multiLineStr.add(new String(line));
						
						line = new String(s + " ");
						
					} else {
						
						line = lineAux;
					}
					
				}				
					
				multiLineStr.add(line);	
				return multiLineStr;
			} else {
				multiLineStr.add(str);
				return multiLineStr;
			}
			
			
		} else {
			multiLineStr.add(str);
			return multiLineStr;
		}
			
		
	}
		
	
	// No es muy eficiente
	public List<String> divideStringByRecurrence (String str, float areaWidth, BitmapFont font) {
		
		TextBounds tb = font.getBounds(str);
		String firstPart = str;
		String secondPart = "";
		List<String> secondPartDivided, list, multiLineStr = new ArrayList<String>();
		float firstPartWidth;
		int i= 1;
		
		if (tb.width>areaWidth) {
			
			do {
				
				list = divideStringInTwo(firstPart, areaWidth, font);
				
				firstPart = list.get(0);
				secondPart = list.get(1).concat(" "+secondPart);
				
				tb = font.getBounds(firstPart);
				
			} while (tb.width > areaWidth && firstPart.contains(" "));
			
			
			tb = font.getBounds(secondPart);
			if (tb.width>areaWidth) {
				secondPartDivided = divideStringByRecurrence(secondPart, areaWidth, font);
	
				multiLineStr.add(firstPart);
				multiLineStr.addAll(secondPartDivided);
				
				return multiLineStr;
			}
			else {
				multiLineStr.add(firstPart);
				
				if (secondPart != "")
					multiLineStr.add(secondPart);
				
				return multiLineStr;
			}
		} else {
			multiLineStr.add(str);
			
			return multiLineStr;
		}
		
		/*
		
		if(tb.width>areaWidth) {
			
			if (str.contains(" ")) {
				do {
	
					String[] parts = string.split(" ");
					
					firstPart = parts[0];
					
					for (i = 1; i<parts.length-1; i++){
						firstPart.concat(" "+parts[i]);
					}
					
					secondPart = parts[i].concat(secondPart);
					
					tb = font.getBounds(firstPart);
					
					firstPartWidth = tb.width;
					
					string = firstPart;
				
					} while(firstPartWidth > areaWidth); 
				
				secondPartList = divideString(secondPart, areaWidth, font);
			
				multiLineStr.add(string);
				multiLineStr.addAll(secondPartList);
				
				return multiLineStr;
				
			} else {
			multiLineStr.add(str);
			return multiLineStr;
			}
		} else {
			multiLineStr.add(str);
			return multiLineStr;
			}	
			
				*/
		
	}
	
	
	public List<String> divideStringInTwo (String str, float areaWidth, BitmapFont font) {
		
		TextBounds tb = font.getBounds(str);
		List<String> dividedStr = new ArrayList<String>();
		String firstPart, secondPart;
		
		if (tb.width> areaWidth) {
			
			if (str.contains(" ")) {
				
				String[] parts = str.split(" ");
				
				firstPart = parts[0];
				
				for (int i = 1; i<parts.length-1; i++){
					firstPart = firstPart.concat(" "+parts[i]);
				}
				
				secondPart = parts[parts.length-1];
				
				dividedStr.add(firstPart);
				dividedStr.add(secondPart);
				
				return dividedStr;
	
			} else {
				
				dividedStr.add(str);
				dividedStr.add("");
				return dividedStr;
				
			}
			
		}else {

			dividedStr.add(str);
			dividedStr.add("");
			return dividedStr;
				
			}
			
	}
	
	@Override
	public abstract void draw(Batch batcher, float parentAlpha); 
	

}
