package com.FoscusGames.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DoubleTextureButton extends SimpleButton{
	
	private TextureRegion auxTexture;
	private float widthPercent;
	private float heightPercent;
	private float auxX, auxY, originX, originY,  rotation = 0;

	public DoubleTextureButton(float x, float y, float width, float height,
			TextureRegion buttonUp, TextureRegion buttonDown, TextureRegion auxTex, float widthPercent, float heightPercent) {
		super(x, y, width, height, buttonUp, buttonDown);
		
		this.auxTexture = auxTex;
		this.widthPercent = widthPercent;
		this.heightPercent = heightPercent;
		
		originX = x+width/2;
		originY = y+height/2;
		
	}
	
	@Override
	public void draw(Batch batcher, float parentAlpha) {

		super.draw(batcher, parentAlpha);

		auxX = center(getX(), getWidth()*widthPercent, getWidth());
		auxY = center(getY(), getHeight()*heightPercent, getHeight());
		batcher.draw(auxTexture, auxX, auxY, -auxX+originX, -auxY+originY, getWidth()*widthPercent, getHeight()*heightPercent, scale, scale, rotation);
	}
	


}
