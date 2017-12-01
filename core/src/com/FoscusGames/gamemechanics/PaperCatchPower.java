package com.FoscusGames.gamemechanics;

import java.util.Random;

import com.FoscusGames.fpHandlers.FPConstants;
import com.FoscusGames.gameobjects.PaperToken;
import com.FoscusGames.gameobjects.PowerToken;
import com.FoscusGames.gameworld.GameWorld;
import com.FoscusGames.sound.PowerSoundManager;
import com.FoscusGames.ui.TextIndicator;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PaperCatchPower extends DashCatchPower{

	
	float MAX_MOVING_ITEMS = 6;
	int tokenCounter;
	Color paperColor;
	
	public PaperCatchPower(Animation a, GameWorld w, float d, Sprite s,float speed, String n, TextureRegion powerFace, Color c,PowerSoundManager sM) {
		super(a, w, d, s, speed, n, powerFace,c,sM);
		
		tokenCounter = 0;
	}

	@Override
	public void addNewItem() {

		float x;
		float y;
		int width = 20;
		int height = 20;
		float itemSeparation = 55;
		float extraDuration = 0;
		int value = 1;
		float maxToken = 35;
		
		Random r = new Random();
		
		if (items.size()<MAX_MOVING_ITEMS) {
			
			float rotation = r.nextFloat();
			
			if(items.size() == 0) {
				x = FPConstants.gameWidth;
				y = r.nextInt(Math.round(FPConstants.playableHeight)) ;
			} else {
				x = items.get(items.size()-1).getX() + itemSeparation;
				y = r.nextInt(Math.round(FPConstants.playableHeight-height));				
			}
			
			if (tokenCounter >= maxToken) maxToken = tokenCounter;
			
			paperColor = new Color(1 -(180f/255)*tokenCounter/maxToken,1-(213f/255)*tokenCounter/maxToken,1f-tokenCounter/maxToken,1f);

			
			items.add(new PaperToken(x, y,  width, height, world.getScroller().getNormalScrollSpeed()*5, world.getBird(), extraDuration, value,paperColor,rotation));	//Enviar el bird esta como el culo	
	
			
			tokenCounter++;
			
		}
	}
	
	
	@Override 
	public void childReset() {
		super.childReset();
		tokenCounter=0;
	}
	
	@Override 
	public void childStop() {
		super.childStop();
		tokenCounter=0;
	}

	@Override
	public void addScoreIndicator(PowerToken c) {
		
		scoreIndicators.add(new TextIndicator(c.getTotalPosition().x, c.getTotalPosition().y, "+"+c.getValue(), paperColor));
		
	}

}
