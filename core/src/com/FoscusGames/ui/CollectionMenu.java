package com.FoscusGames.ui;

import java.util.ArrayList;
import java.util.List;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.fpHandlers.FPConstants;
import com.FoscusGames.fpHandlers.SoundLoader;
import com.FoscusGames.gameprizes.PrizeHandler;
import com.FoscusGames.gameworld.GameWorld;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class CollectionMenu extends Menu{
	
	private List<PrizeHolder> acquiredPrizeHolders;
	private DoubleTextureButton backButton;
	
	
	public CollectionMenu (Batch batcher, Camera cam, GameWorld world) {
		

		super(batcher, cam, world);

		initButtons();
		
		container = createContainer();
	    
	    stage.addActor(container);
	    
	    

		
	}
	
	
	
	public Table createPrizeTable() {
		
		float holderWidth = screenWidth*0.95f;
		float holderHeight = holderWidth*0.30f;
		float labelHeight = screenHeight*0.1f;
		float padValue = 10;
		float labelWidth = holderWidth*0.6f;

		LabelStyle style = new LabelStyle(AssetLoader.font, Color.WHITE);
		
		MySprite legendaryLabel = new MySprite(AssetLoader.legendary);
		MySprite epicLabel = new MySprite(AssetLoader.epic);
		MySprite rareLabel = new MySprite(AssetLoader.rare);
		MySprite commonLabel = new MySprite(AssetLoader.common);
		
		acquiredPrizeHolders = PrizeHandler.getAcquiredPrizeHolders();
		
		
		// Refactorizar para usar solo la List acqquiredPrizeHolders, hacer solo 1 for por rarity e ir sacando los Holders ya usadoss
		List<PrizeHolder> legendaryPrizes, epicPrizes, rarePrizes, commonPrizes;
		
		Table prizeTable = new Table();
		
		legendaryPrizes = PrizeHandler.getHoldersByRarity(acquiredPrizeHolders, "legendary");
		epicPrizes = PrizeHandler.getHoldersByRarity(acquiredPrizeHolders, "epic");
		rarePrizes = PrizeHandler.getHoldersByRarity(acquiredPrizeHolders, "rare");
		commonPrizes = PrizeHandler.getHoldersByRarity(acquiredPrizeHolders, "common");

		for (PrizeHolder p : legendaryPrizes) {
			if(p != null) {
			prizeTable.add(p).width(holderWidth).height(holderHeight).pad(padValue);
			prizeTable.row();
			}
		}
		//prizeTable.add(legendaryLabel).width(labelWidth).height(labelHeight);
		//prizeTable.row();
		
		for (PrizeHolder p : epicPrizes) {
			if(p != null) {
			prizeTable.add(p).width(holderWidth).height(holderHeight).pad(padValue);
			prizeTable.row();
			}
		}
		//prizeTable.add(epicLabel).width(labelWidth).height(labelHeight);
		//prizeTable.row();

		for (PrizeHolder p : rarePrizes) {
			if(p != null) {
			prizeTable.add(p).width(holderWidth).height(holderHeight).pad(padValue);
			prizeTable.row();
			}
		}
		//prizeTable.add(rareLabel).width(labelWidth).height(labelHeight);
		//prizeTable.row();

		for (PrizeHolder p : commonPrizes) {
			if(p != null) {
			prizeTable.add(p).width(holderWidth).height(holderHeight).pad(padValue);
			prizeTable.row();
			}
		}
		//prizeTable.add(commonLabel).width(labelWidth).height(labelHeight);
		//prizeTable.row();

		
		return prizeTable;
	}

    public void initButtons() {
    	backButton = new DoubleTextureButton ( 0, 
						0, 
							300, 
							100, 
							AssetLoader.buttonUp,
							AssetLoader.buttonDown,
							AssetLoader.backToMenuText,
							0.9f,
							0.4f);
		
		backButton.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				backButton.isPressed = true;
				return true;
			}
		
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				backButton.isPressed = false;
				if(FPConstants.isSoundOn) SoundLoader.buttonPress.play();
				myWorld.backToMenu();
			}
		});
    }
    
    @Override
    public Table createContainer() {
    	

		Table container = new Table(); 
		

		float scrollPaneWidth = screenWidth;
		float scrollPaneHeight = screenHeight*0.7f;
		
    	elements = createPrizeTable();

    	int totalPrizes = PrizeHandler.getTotalPrizes();
		int acquiredPrizes = acquiredPrizeHolders.size();


		MySprite line = new MySprite(AssetLoader.buttonDown);
		
		LabelStyle style = new LabelStyle(AssetLoader.font, Color.WHITE);
		style.background = line;
		Label prizesObtained = new Label("Prizes: " + acquiredPrizes + "/" + totalPrizes , style);
		Label completionPercent =new Label ("Completion:\n"+100*acquiredPrizes/totalPrizes + "%", style);
		
		prizesObtained.setAlignment(Align.center);
		completionPercent.setAlignment(Align.center);
		
		List<TextureRegion> titleTex = new ArrayList<TextureRegion>();
		titleTex.add(AssetLoader.buttonDown);
		titleTex.add(AssetLoader.prizeCollectionText);
		
		float[] percents = {1,1,0.8f,0.35f};
		
		MultiSprite title = new MultiSprite(titleTex, percents);

		
		elements.align(Align.bottom);
		scrollPane = new ScrollPane(elements);
		

		container.add(scrollPane).width(scrollPaneWidth).height(scrollPaneHeight).colspan(3);
	    container.row();
	    container.add(line).width(screenWidth).height(screenHeight*0.01f).colspan(3);
	    container.row();
		container.add(prizesObtained).width(screenWidth*0.25f);
    	
		container.add(backButton).width(screenWidth*0.5f).height(screenHeight*0.1f);
		
		container.add(completionPercent).width(screenWidth*0.25f);
		container.row();
		
		container.add(title).width(screenWidth).height(screenHeight*0.19f).colspan(3);
		//container.row();
		
	    container.setBounds(0,0,screenWidth, screenHeight);
	    

		scrollPane.setForceScroll(false, true);
	    scrollPane.layout();
		scrollPane.setScrollPercentY(100);
	    
	    return container;
    }



}
