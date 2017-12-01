package com.FoscusGames.ui;

import java.util.ArrayList;
import java.util.List;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.gameobjects.Bird;
import com.FoscusGames.gameprizes.PrizeHandler;
import com.FoscusGames.gameworld.GameWorld;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class PibSelectMenu extends Menu{
	
	
	public PibSelectMenu(Batch batcher, Camera cam, GameWorld world) {
		super(batcher, cam, world);
		

		container = createContainer();
	    
	    stage.addActor(container);
		
	}

	@Override
	public Table createContainer() {
		

		Table container = new Table(); 
		

		float scrollPaneWidth = screenWidth;
		float scrollPaneHeight = screenHeight*0.85f;
		
    	elements = createPibTable();

		List<TextureRegion> titleTex = new ArrayList<TextureRegion>();
		titleTex.add(AssetLoader.buttonDown);
		titleTex.add(AssetLoader.selectYourPibText);
		
		float[] percents = {1,1,0.8f,0.35f};
		
		MultiSprite title = new MultiSprite(titleTex, percents);
		
		
		scrollPane = new ScrollPane(elements);
		

		container.add(scrollPane).width(scrollPaneWidth).height(scrollPaneHeight);
	    container.row();
		
		container.add(title).width(screenWidth).height(screenHeight*0.15f);
		
	    container.setBounds(0,0,screenWidth, screenHeight);
	    

		scrollPane.setForceScroll(false, true);
	    scrollPane.layout();
		scrollPane.setScrollPercentY(100);
		
		return container;
	}
	
	public Table createPibTable() {
		
		Table pibHolderTable = new Table();
		
		
		List<Bird> pibs= myWorld.getPibs();
		
		List<TextureRegion> skin = new ArrayList<TextureRegion>();
		skin.add(AssetLoader.BRPibArea);
		skin.add(AssetLoader.BRBar);
		skin.add(AssetLoader.BRBar);
		skin.add(AssetLoader.BREndFlip);
		skin.add(AssetLoader.BREnd);
		skin.add(AssetLoader.BRPrizeSlot);
	
		float margin = 6;
		float pibHolderWidth = screenWidth*0.8f;
		float pibHolderHeight = screenHeight*0.2f;
		
		for(Bird pib : pibs) {
			
			PibHolder pibHolder = new PibHolder (  pib, skin, margin, margin+(margin+pibHolderHeight)*pibs.indexOf(pib), pibHolderWidth, pibHolderHeight);
			
			final int pibIndex = pibs.indexOf(pib);
			
			pibHolder.addListener(new InputListener() {
				//private boolean wasDragged = false;
				private float prevX, prevY;
				public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
					//pibHolder.isPressed = true;
					
					prevX = x;
					prevY = y;
					//wasDragged = false;
					return true;
				}
				
				public void touchDragged(InputEvent event, float x, float y, int pointer) {
					
					
					//wasDragged = true;
				    
				}
			
				public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
					//pibHolder.isPressed = false;
					/*if(!wasDragged) {
						myWorld.selectPib(pibIndex);
					}*/
					float dist = (float) Math.sqrt((x-prevX)*(x-prevX) + (y-prevY)*(y-prevY));
					
					if(dist < 5) {
						myWorld.selectPib(pibIndex);
					}
				}				
			});
			
			pibHolderTable.add(pibHolder).width(pibHolderWidth).height(pibHolderHeight).pad(screenHeight*0.01f).padRight(screenWidth*0.07f);		
			pibHolderTable.row();
			
		}
		
		return pibHolderTable;
	}

}
