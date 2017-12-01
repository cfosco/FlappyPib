package com.FoscusGames.ui;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

public class MultiSprite extends Widget {
	

	private List<TextureRegion> texList;
	float[] p;
	
	public MultiSprite(List<TextureRegion> texList, float[] percentList) {
		
		this.texList = texList;
		this.p = percentList;
	}
	
	@Override
	public void draw(Batch batcher, float parentAlpha) {
		
		
		batcher.setColor(1f,1f,1f,1f);
		int i;
		
		for (TextureRegion t : texList) {
			if (t!=null) {
			i = texList.indexOf(t);
			
			batcher.draw(t, center(getX(), getWidth()*p[2*i], getWidth()), center(getY(), getHeight()*p[2*i+1], getHeight()), getOriginX(), getOriginY(), p[2*i]*getWidth(), p[2*i+1]*getHeight(), getScaleX(), getScaleY(), getRotation());
		
			}
			
		}
		
	}
	

	public float center (float pos, float length, float areaLength) {
		return pos+areaLength/2-length/2;
	}

}
