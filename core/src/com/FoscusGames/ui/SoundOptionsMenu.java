package com.FoscusGames.ui;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.fpHandlers.FPConstants;
import com.FoscusGames.fpHandlers.SoundLoader;
import com.FoscusGames.gameworld.GameWorld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class SoundOptionsMenu extends Menu {

	private DoubleTextureButton backButton;
	private ToggleButton soundToggle, musicToggle, classicSoundToggle;
	BitmapFont font;
	
	public SoundOptionsMenu (Batch batcher, Camera cam, GameWorld world) {
		

		super(batcher, cam, world);
		
		initButtons();
		container = createContainer();
	    
	    stage.addActor(container);

		
	}
	
	@Override
	public Table createContainer() {

		float labelWidth = screenWidth*0.4f;
		float labelHeight = screenHeight*0.2f;
		float toggleWidth = screenWidth*0.2f;
		float toggleHeight = toggleWidth;
		
		Table container = new Table(); 
		
		font= new BitmapFont(Gdx.files.internal("data/text.fnt"));
	    font.setScale(.5f, -.5f);

		LabelStyle style = new LabelStyle(font, Color.WHITE);
		Label soundText = new Label("Sound:" , style);
		Label musicText = new Label ("Music:", style);
		Label classicSoundText = new Label ("Classic Sound:", style);
		
		classicSoundText.setWrap(true);
		
		
		soundText.setAlignment(Align.center);
		musicText.setAlignment(Align.center);
		classicSoundText.setAlignment(Align.center);
		
		
		container.add(backButton).width(screenWidth*0.5f).height(screenHeight*0.1f).colspan(2);
		container.row();
		container.add(classicSoundText).width(labelWidth).height(labelHeight);
		container.add(classicSoundToggle).width(toggleWidth).height(toggleHeight);
		container.row();
		container.add(soundText).width(labelWidth).height(labelHeight);
		container.add(soundToggle).width(toggleWidth).height(toggleHeight);
		container.row();
		container.add(musicText).width(labelWidth).height(labelHeight);
		container.add(musicToggle).width(toggleWidth).height(toggleHeight);
	


	    container.setBounds(0,0,screenWidth, screenHeight);
		
		return container;
	}
	

    public void initButtons() {
    	
    	float toggleWidthPercent = 0.5f;
    	float toggleHeightPercent = 0.4f;
   	
    	soundToggle = new ToggleButton (AssetLoader.buttonUp, AssetLoader.buttonDown, AssetLoader.onText, AssetLoader.offText, toggleWidthPercent, toggleHeightPercent, FPConstants.isSoundOn);
    	
    	musicToggle = new ToggleButton (AssetLoader.buttonUp, AssetLoader.buttonDown, AssetLoader.onText, AssetLoader.offText, toggleWidthPercent, toggleHeightPercent, FPConstants.isMusicOn);
    	
    	classicSoundToggle = new ToggleButton (AssetLoader.buttonUp, AssetLoader.buttonDown, AssetLoader.onText, AssetLoader.offText, toggleWidthPercent, toggleHeightPercent, false);
    	
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
		
		soundToggle.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				soundToggle.isPressed = true;
				
				return true;
			}
		
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				soundToggle.isPressed = false;
				soundToggle.toggleState();
				FPConstants.isSoundOn = !FPConstants.isSoundOn;
				if(FPConstants.isSoundOn) SoundLoader.buttonPress.play();

			}
		});
		
		musicToggle.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				musicToggle.isPressed = true;
				
				return true;
			}
		
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				musicToggle.isPressed = false;
				if(FPConstants.isSoundOn) SoundLoader.buttonPress.play();
				musicToggle.toggleState();
				FPConstants.isMusicOn = !FPConstants.isMusicOn;
				if (FPConstants.isMusicOn) {
					SoundLoader.menuMusic.play();
				} else SoundLoader.menuMusic.stop();
			}
		});
		
		classicSoundToggle.addListener(new InputListener() {
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				classicSoundToggle.isPressed = true;
				
				return true;
			}
		
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				classicSoundToggle.isPressed = false;
				if(FPConstants.isSoundOn) SoundLoader.buttonPress.play();
				classicSoundToggle.toggleState();
				FPConstants.classicSound = !FPConstants.classicSound;
			}
		});
		
		
    }

}
