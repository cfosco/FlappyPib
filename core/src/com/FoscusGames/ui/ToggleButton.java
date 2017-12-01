package com.FoscusGames.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ToggleButton extends SimpleButton {
	
	private TextureRegion t1,t2;
	
	private float originX, originY, widthPercent, heightPercent, rotation = 0;
	private boolean isOn;

	public ToggleButton(float x, float y, float width, float height,
			TextureRegion buttonUp, TextureRegion buttonDown, TextureRegion t1, TextureRegion t2, float widthPercent, float heightPercent) {
		
		
		super(x, y, width, height, buttonUp, buttonDown);
				
				this.t1 = t1;
				this.t2 = t2;
				this.widthPercent = widthPercent;
				this.heightPercent = heightPercent;
				
				originX = x+width/2;
				originY = y+height/2;
		
	}
	
	public ToggleButton(TextureRegion buttonUp, TextureRegion buttonDown, TextureRegion t1, TextureRegion t2, float widthPercent, float heightPercent, boolean isOn) {
		
		super(0,0,0,0,buttonUp, buttonDown);

		this.t1 = t1;
		this.t2 = t2;
		this.widthPercent = widthPercent;
		this.heightPercent = heightPercent;
		
		this.isOn = isOn;
		
		originX = 0;
		originY = 0;
		
	}
	
	@Override
	 public void draw(Batch batcher, float parentAlpha) {
	    	
		
		super.draw(batcher, parentAlpha);

		float auxX = center(getX(), getWidth()*widthPercent, getWidth());
		float auxY = center(getY(), getHeight()*heightPercent, getHeight());
		
		if (isOn) {
			batcher.draw(t1, auxX, auxY, -auxX+originX, -auxY+originY, getWidth()*widthPercent, getHeight()*heightPercent, scale, scale, rotation);
		}
		else {
			batcher.draw(t2, auxX, auxY, -auxX+originX, -auxY+originY, getWidth()*widthPercent, getHeight()*heightPercent, scale, scale, rotation);
		
		}
	
	}
	
	public void toggleState() {
		isOn = !isOn;
	}
	

}
