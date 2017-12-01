package com.FoscusGames.gamemechanics;

import java.util.Random;

import com.FoscusGames.gameobjects.FasoToken;
import com.FoscusGames.gameobjects.MathToken;
import com.FoscusGames.gameobjects.PlantaToken;
import com.FoscusGames.gameobjects.PowerToken;
import com.FoscusGames.gameworld.GameWorld;
import com.FoscusGames.sound.PowerSoundManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class MathCatchPower extends ItemCatchPower {
	
	boolean plantaAvailable;

	public MathCatchPower(Animation a, GameWorld w, float d, Sprite s,String n, TextureRegion powerFace, Color c, PowerSoundManager sM) {
		super(a, w, d, s, n, powerFace, c, sM);
	
		plantaAvailable = false;
	
	}
	
	@Override
	protected void use(float runTime) {
		super.use(runTime);
		world.getScroller().hidePipes();
		
		
	}

	@Override
	public void updateItems() {
		
		for (int i=0;i<items.size();i++) {
			
			items.get(i).update(Gdx.graphics.getDeltaTime());
			
			if (items.get(i).collision) {
				
				addScoreIndicator(items.get(i));
				items.get(i).onCatch();
				
				items.remove(items.get(i));
			}
				
			else if(items.get(i).isScrolledLeft()) {
				items.remove(items.get(i));
			}
			
		}
		
		if(items.size()==0 && plantaAvailable) plantaAvailable = false;
	}



	@Override
	public void addNewItem() {
		
		int value = 1;
		float fasoDuration = duration*0.3f;
		float plantDuration = duration;
		int plantValue = 10;
		float itemSep = 57;
		
		
		if(items.size()<MAX_MOVING_ITEMS && !plantaAvailable) {
		
			float roll = r.nextFloat();
			
			Vector2 pos = generateNewPosition(itemSep);
			
			if(roll<=0.81)items.add(new MathToken(pos, world.getScroller().getScrollSpeed(), world.getBird(), value));
			
			else if(roll<=0.995) items.add(new FasoToken(pos,  world.getScroller().getScrollSpeed(), world.getBird(),fasoDuration));
			
			else {
				items.add(new PlantaToken(pos,  world.getScroller().getScrollSpeed()*0.6f, world.getBird(),plantDuration, plantValue));
				plantaAvailable = true;
			}
		
		}

	}
	

	@Override
	public void childReset() {
		world.getScroller().unHidePipes();

		items.removeAll(items);

		scoreIndicators.removeAll(scoreIndicators);

	}

	@Override
	public void childStop() {
		world.getScroller().unHidePipes();

		items.removeAll(items);

		scoreIndicators.removeAll(scoreIndicators);

	}
	
	

}
