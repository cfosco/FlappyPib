package com.FoscusGames.gamemechanics;

import java.util.Random;

import com.FoscusGames.gameobjects.Bird;
import com.FoscusGames.gameobjects.Coin;
import com.FoscusGames.gameobjects.PowerToken;
import com.FoscusGames.gameworld.GameWorld;
import com.FoscusGames.sound.PowerSoundManager;
import com.FoscusGames.ui.TextIndicator;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class CoinCatchPower extends ItemCatchPower {

	public CoinCatchPower(Animation a, GameWorld w, float d, Sprite s, String n, TextureRegion powerFace, Color c,PowerSoundManager sM) {
		super(a, w, d, s, n, powerFace,c,sM);
		
		
	}
	
	@Override
	protected void use(float runTime) {
		super.use(runTime);
		world.getScroller().hidePipes();
		
		
	}


	@Override
	public void addNewItem() {
		float itemSeparation = 40;
		
		
		if (items.size()<MAX_MOVING_ITEMS) {
			
			float roll = r.nextFloat();
			
			Vector2 pos = generateNewPosition(itemSeparation);
			
			
			if(roll <= 0.9f) { 	//Normal Coin
				

				items.add(new Coin(pos.x, pos.y,  world.getScroller().getNormalScrollSpeed(), world.getBird(), Coin.NORMAL));	//Enviar el bird esta como el culo	

			}
			
			else if (roll <=0.99f) {	//Special coin
				
				
				items.add(new Coin(pos.x,pos.y, world.getScroller().getNormalScrollSpeed(), world.getBird(), Coin.SPECIAL));		
				//Gdx.app.log("ItemCatchPower", "Adding special coin" );

			}
			
			else {		//Rare coin
				items.add(new Coin(pos.x,pos.y, world.getScroller().getNormalScrollSpeed(), world.getBird(), Coin.RARE));		
				//Gdx.app.log("ItemCatchPower", "Adding rare coin" );


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

	@Override
	public void childInterrupt() {
		// TODO Auto-generated method stub

	}


	@Override
	public void childDraw(Batch batcher) {
		
		if(isInUse) {
			
		Bird pib = world.getBird();
		
		float spriteWidth = 2*pib.getHeight(); 	//Extra Width para englobar al pib 
		float spriteHeight = spriteWidth;		//Extra Height para englobar al pib
		
		float animX = pib.getX()+pib.getWidth()/2-spriteWidth/2;
		float animY = pib.getY()+pib.getHeight()/2-spriteHeight/2;
				
		batcher.draw(anim.getKeyFrame(world.getRunTime()), animX, animY, animX, animY, spriteWidth, spriteHeight, 1, 1, 0);
		
		}
		
		super.childDraw(batcher);

	}


	@Override
	public void addScoreIndicator(PowerToken c) {
		
		scoreIndicators.add(new TextIndicator(c.getTotalPosition().x, c.getTotalPosition().y, "+"+c.getValue(), Color.WHITE));
		
	}

}
