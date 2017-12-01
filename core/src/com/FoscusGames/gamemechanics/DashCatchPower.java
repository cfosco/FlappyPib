package com.FoscusGames.gamemechanics;

import java.util.ArrayList;
import java.util.List;

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

public abstract class DashCatchPower extends DashPower implements ItemCatcher {



	protected List<TextIndicator> scoreIndicators;
	protected List<PowerToken> items;
	
	public DashCatchPower(Animation a, GameWorld w, float d, Sprite s, float speed, String n, TextureRegion powerFace, Color c,PowerSoundManager sM) {
		super(a, w, d, s, speed, n, powerFace,c,sM);


		items = new ArrayList<PowerToken>();
		scoreIndicators = new ArrayList<TextIndicator>();
	}
	
	@Override
	protected void childUpdate(float runTime) {

		super.childUpdate(runTime);
		addNewItem();
		updateItems();
		updateScoreIndicators(runTime);
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


	@Override
	public void childReset() {
		super.childReset();

		items.removeAll(items);

		scoreIndicators.removeAll(scoreIndicators);
	}

	@Override
	public void childStop() {
		super.childStop();
		items.removeAll(items);

		scoreIndicators.removeAll(scoreIndicators);
	}

	@Override
	public void childInterrupt() {
		super.childInterrupt();

	}


	@Override
	public void childDraw(Batch batcher) {
		super.childDraw(batcher);
		
		for (CatchObject c : items) {
			c.draw(batcher);
		}
		
		for (TextIndicator t : scoreIndicators) {

			t.draw(batcher);
		}

	}

	@Override
	public void addScoreIndicator(PowerToken c) {
		
		if(c.addsScore())scoreIndicators.add(new TextIndicator(c.getTotalPosition().x, c.getTotalPosition().y, "+"+c.getValue(),Color.WHITE));	//Pareceria que al dibujar una font no se toma en cuenta el Color..
		
		if(c.addsTime())scoreIndicators.add(new TextIndicator(dBarX+dBarWidth/2-2.5f, dBarY, "+"+c.getExtraDuration()+" secs",new Color(1f,0.7f,1f,1f)));
	}


}
