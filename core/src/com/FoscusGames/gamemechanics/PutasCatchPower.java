package com.FoscusGames.gamemechanics;

import java.util.Random;

import com.FoscusGames.fpHandlers.FPConstants;
import com.FoscusGames.gameobjects.CatchObject;
import com.FoscusGames.gameobjects.PowerToken;
import com.FoscusGames.gameobjects.PutasToken;
import com.FoscusGames.gameworld.GameWorld;
import com.FoscusGames.sound.PowerSoundManager;
import com.FoscusGames.ui.TextIndicator;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PutasCatchPower extends DashCatchPower{

	float MAX_MOVING_ITEMS = 3;
	
	
	
	public PutasCatchPower(Animation a, GameWorld w, float d, Sprite s,float speed, String n, TextureRegion powerFace,Color c,PowerSoundManager sM) {
		super(a, w, d, s, speed, n, powerFace,c,sM);
		
	}
	
	

	@Override
	public void addNewItem() {

		float x;
		float y;
		int width = 20;
		int height = 25;
		float itemSeparation = 100;
		float extraDuration = duration*0.25f;
		int value = 0;
		Color sColor = new Color(1f,1f,0.6f,1f);
		
		Random r = new Random();
		
		if (items.size()<MAX_MOVING_ITEMS) {
			
			float roll = r.nextFloat();
			
			
			if(items.size() == 0) {
				x = FPConstants.gameWidth;
				y = r.nextInt(Math.round(FPConstants.playableHeight)) ;
			} else {
				x = items.get(items.size()-1).getX() + itemSeparation;
				y = r.nextInt(Math.round(FPConstants.playableHeight-height));				
			}
			
			if(roll <= 0.99f) { 	//Normal Token
				

				items.add(new PutasToken(x, y,  width, height, world.getScroller().getNormalScrollSpeed()-20, world.getBird(), extraDuration, value, new Color(1f,1f,1f,1f)));	//Enviar el bird esta como el culo	

			}
			
			else   {	//Special Token
				
				
				items.add(new PutasToken(x, y, width, height, world.getScroller().getNormalScrollSpeed()-30, world.getBird(), extraDuration*4, value*4,sColor));		

			}
			
			
		}
	}




	

	

}