package com.FoscusGames.gamemechanics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.FoscusGames.fpHandlers.FPConstants;
import com.FoscusGames.gameobjects.CatchObject;
import com.FoscusGames.gameobjects.PowerToken;
import com.FoscusGames.gameworld.GameWorld;
import com.FoscusGames.sound.PowerSoundManager;
import com.FoscusGames.ui.TextIndicator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class ItemCatchPower extends Power implements ItemCatcher{

	
	List<PowerToken> items;
	List<TextIndicator> scoreIndicators;
	
	protected int MAX_MOVING_ITEMS = 4;
	
	Random r;

	
	public ItemCatchPower(Animation a, GameWorld w, float d, Sprite s, String n, TextureRegion powerFace, Color c,PowerSoundManager sM) {
		super(a, w, d, s, n, powerFace,c,sM );
		
		
		items = new ArrayList<PowerToken>();
		scoreIndicators = new ArrayList<TextIndicator>();
		
		r = new Random();
		
	}
	

	@Override
	protected void childUpdate(float runTime) {
	
		
		addNewItem();

		updateItems();
		
		updateScoreIndicators(runTime);

		
	}
	
	 public Vector2 generateNewPosition(float itemSeparation) {
		 
		 Vector2 pos = new Vector2();
		 
		if(items.size() == 0) {
			pos.x = FPConstants.gameWidth;
			pos.y = r.nextInt(Math.round(FPConstants.playableHeight)) ;
		} else {
			pos.x = items.get(items.size()-1).getX() + itemSeparation;
			pos.y = r.nextInt(Math.round(FPConstants.playableHeight-10));				
		}
		
		return pos;
	 
	 }
		
	
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
	public void childReset() {

		items.removeAll(items);

		scoreIndicators.removeAll(scoreIndicators);

	}

	@Override
	public void childStop() {

		items.removeAll(items);

		scoreIndicators.removeAll(scoreIndicators);

	}

	@Override
	public void childInterrupt() {
		// TODO Auto-generated method stub

	}


	@Override
	public void childDraw(Batch batcher) {
		
		
		for (CatchObject i : items) {
			i.draw(batcher);
		}

		for (TextIndicator t : scoreIndicators) {

			t.draw(batcher);
		}
	}
	
	public abstract void addNewItem();
	
	
	public void addScoreIndicator(PowerToken c) {
		
		if(c.addsScore())scoreIndicators.add(new TextIndicator(c.getTotalPosition().x, c.getTotalPosition().y, "+"+c.getValue(),Color.WHITE));	//Pareceria que al dibujar una font no se toma en cuenta el Color..
		
		if(c.addsTime())scoreIndicators.add(new TextIndicator(dBarX+dBarWidth/2-2.5f, dBarY, "+"+c.getExtraDuration()+" secs",new Color(1f,0.7f,1f,1f)));
	}


}
