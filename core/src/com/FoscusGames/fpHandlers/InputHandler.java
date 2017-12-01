package com.FoscusGames.fpHandlers;

import java.util.ArrayList;
import java.util.List;

import com.FoscusGames.gameobjects.Bird;
import com.FoscusGames.gameworld.GameWorld;
import com.FoscusGames.ui.DoubleTextureButton;
import com.FoscusGames.ui.PibHolder;
import com.FoscusGames.ui.SimpleButton;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class InputHandler implements InputProcessor{

	
	private GameWorld myWorld;
	
	private List<SimpleButton> menuButtons;
//	private List<SimpleButton> pibButtons;
	private List<SimpleButton> GOButtons;


    private DoubleTextureButton pibSelectButton, collectionButton, playButton, soundButton, bgButton, cBackToMenuButton;
    private DoubleTextureButton backToMenuButton;
    private DoubleTextureButton retryButton;

    private float scaleFactorX;
    private float scaleFactorY;
    
    private float prevX;
    private float prevY;
    
    private List<PibHolder> pibHolders;
    
    
    //TEST
    public static int i = 1;


	public InputHandler(GameWorld world, float scaleFactX, float scaleFactY) {
		
		myWorld = world;
		
		int midPointY = myWorld.getMidPointY();
		
		scaleFactorX = scaleFactX;
		scaleFactorY = scaleFactY;
		
		pibHolders = new ArrayList<PibHolder>();
		
		menuButtons = new ArrayList<SimpleButton>();
		GOButtons = new ArrayList<SimpleButton>();
		
		initMenuButtons(midPointY);
		initGOButtons(midPointY);
		initPibSelectButtons();
		//initCollectionButtons();
		
		
	}
	
	public void initCollectionButtons() {
		
		float playButtonPrefWidth = 70;
		float playButtonPrefHeight = 30;
		
		cBackToMenuButton = new DoubleTextureButton ( 0, 
													  0, 
													playButtonPrefWidth, 
													playButtonPrefHeight, 
													AssetLoader.buttonUp,
													AssetLoader.buttonDown,
													AssetLoader.backToMenuText,
													0.9f,
													0.4f);
		
	}
	
	public void initPibSelectButtons() {
		
		
		List<Bird> pibs= myWorld.getPibs();
		
		List<TextureRegion> skin = new ArrayList<TextureRegion>();
		skin.add(AssetLoader.BRPibArea);
		skin.add(AssetLoader.BRBar);
		skin.add(AssetLoader.BRBar);
		skin.add(AssetLoader.BREndFlip);
		skin.add(AssetLoader.BREnd);
		skin.add(AssetLoader.BRPrizeSlot);
	
		float margin = 6;
		float pibHolderWidth = 100;
		float pibHolderHeight = 40;
		
		for(Bird pib : pibs) {
			
			
			pibHolders.add( new PibHolder (  pib, skin, margin, margin+(margin+pibHolderHeight)*pibs.indexOf(pib), pibHolderWidth, pibHolderHeight));
							
			
		}
		
	}
	
	public void initGOButtons(int midPointY) {
		
	 float buttonWidth = 50;
	 float buttonHeight = 20;
	 float buttonY = midPointY+65f;

		retryButton = new DoubleTextureButton ( 3*(136 / 4f) - (buttonWidth / 2), 
										 buttonY, 
										 buttonWidth, 
										 buttonHeight, 
										 AssetLoader.buttonUp,
										 AssetLoader.buttonDown,
										 AssetLoader.retryText,
										 0.7f,
										 0.55f);
		
		backToMenuButton = new DoubleTextureButton ( (136 / 4f) - (buttonWidth / 2), 
											buttonY,
											buttonWidth, 
											buttonHeight, 
											AssetLoader.buttonUp,
											AssetLoader.buttonDown,
											AssetLoader.backToMenuText,
											0.85f, 
											0.55f);
		

		GOButtons.add(retryButton);
		GOButtons.add(backToMenuButton);
		
	}
	
	public void initMenuButtons(int midPointY) {
		
		int playButtonHeight = 30;
		int playButtonWidth = 70;
		int playButtonY = midPointY+30;
		int pibSelectButtonHeight = 25;
		int pibSelectButtonWidth = 40;
		int collectionButtonHeight = pibSelectButtonHeight;
		int collectionButtonWidth = pibSelectButtonWidth;
		int soundButtonWidth = playButtonWidth/4;
		int soundButtonHeight = soundButtonWidth;
		int margin = 6;
		
		playButton = new DoubleTextureButton ( 136/2-playButtonWidth/2, 
										playButtonY, 
										playButtonWidth, 
										playButtonHeight, 
										AssetLoader.buttonUp,
										AssetLoader.buttonDown,
										AssetLoader.playText,
										0.7f,
										0.55f);
		
		pibSelectButton = new DoubleTextureButton ( 136/3-pibSelectButtonWidth/2, 
				   							playButtonY + playButtonHeight + margin, 
											 pibSelectButtonWidth, 
											 pibSelectButtonHeight,
											 AssetLoader.buttonUp,
											 AssetLoader.buttonDown,
											 AssetLoader.pibSelectText,
												0.8f,
												0.35f);

		collectionButton = new DoubleTextureButton ( 2*136/3-collectionButtonWidth/2, 
											 playButtonY + playButtonHeight + margin, 
											 collectionButtonWidth, 
											 collectionButtonHeight,
											 AssetLoader.buttonUp,
											 AssetLoader.buttonDown ,
											 AssetLoader.collectionText,
												0.8f,
												0.35f);
		
		soundButton = new DoubleTextureButton ( (136/2-playButtonWidth/2)/2-soundButtonWidth/2, 
				 							playButtonY+playButtonHeight/2-soundButtonHeight/2 , 
											 soundButtonWidth, 
											 soundButtonHeight,
											 AssetLoader.buttonUp,
											 AssetLoader.buttonDown ,
											 AssetLoader.soundIcon,
												0.5f,
												0.5f);
		
		bgButton = new DoubleTextureButton ((136/2+playButtonWidth/2)+(136-playButtonWidth)/4-soundButtonWidth/2, 
											 playButtonY+playButtonHeight/2-soundButtonHeight/2 , 
											 soundButtonWidth, 
											 soundButtonHeight,
											 AssetLoader.buttonUp,
											 AssetLoader.buttonDown ,
											 AssetLoader.bgIcon,
												0.5f,
												0.5f);

		
		menuButtons.add(playButton);
		menuButtons.add(pibSelectButton);
		menuButtons.add(collectionButton);
		menuButtons.add(soundButton);
		menuButtons.add(bgButton);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		
		Bird myBird = myWorld.getBird();

        // Can now use Space Bar to play the game
        if (keycode == Keys.SPACE) {

            if (myWorld.isMenu()) {
                myWorld.ready();
            } else if (myWorld.isReady()) {
                myWorld.start();
            }

            myBird.onClick(0,0);

            if (myWorld.isGameOver() || myWorld.isHighScore()) {
                myWorld.restart();
            }

        }
        
        if (keycode == Keys.B) {
        	myWorld.bgType = (myWorld.bgType + 1) %AssetLoader.backgrounds.size();
        }
        
        if (keycode == Keys.S) {
        	
        	pibHolders.get(0).setScale(pibHolders.get(0).getScale()+0.1f);
        	
        	
        	i= (i+1)%4;
        	
        	
        	
        }
        
        if (keycode == Keys.D) {
        	
        	pibHolders.get(0).setScale(pibHolders.get(0).getScale()-0.1f);
        	
        	i= (i+1)%4;
        	
        }

        return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);
		
		
		Bird myBird = myWorld.getBird();
		
		if(myWorld.isMenu()) {
			for(SimpleButton b : menuButtons) {
				b.isTouchDown(screenX, screenY);
			}
			
		} else if (myWorld.isReady()) {
			myWorld.start();
			myBird.onClick(screenX, screenY);
			prevX=screenX;
			prevY=screenY;
			
		} else if (myWorld.isRunning()) {
			myBird.onClick(screenX,screenY);
			prevX=screenX;
			prevY=screenY;
			
		} else if (myWorld.isGameOver() || myWorld.isHighScore()) {

			retryButton.isTouchDown(screenX, screenY);
			backToMenuButton.isTouchDown(screenX, screenY);
			
		}
		
		else if (myWorld.isPibSelect()) {
			
			
			for(PibHolder p : pibHolders) {
				p.isTouchDown(screenX,screenY);

			}
		}
		
		else if (myWorld.isCollection()) {
			
			cBackToMenuButton.isTouchDown(screenX, screenY);
		}
		
		
		/*if (myWorld.isGameOver()||myWorld.isHighScore()) {
			myWorld.restart();
		}*/
		
		
		return true;
		
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        if (myWorld.isMenu()) {
            if (playButton.isTouchUp(screenX, screenY)) {
                myWorld.ready();
                return true;
            }
            
            if (pibSelectButton.isTouchUp(screenX, screenY)) {
                myWorld.pibSelect();
                return true;
            }
            
            
            if (collectionButton.isTouchUp(screenX, screenY)) {
                myWorld.collection();
                return true;
            }
            
            if (bgButton.isTouchUp(screenX, screenY)) {
            	myWorld.bgType = (myWorld.bgType + 1) %AssetLoader.backgrounds.size();
            	return true;
            }
            
            if (soundButton.isTouchUp(screenX, screenY)) {
            	myWorld.soundOptions();
            	return true;
            }
            
        } 
        else if (myWorld.isPibSelect()){
        	
        	
        	for (PibHolder p : pibHolders) {
        		if(p.isTouchUp(screenX, screenY)) {
        			myWorld.selectPib(pibHolders.indexOf(p));
        			
        		}
        		
        	}
        	
        }
        else if (myWorld.isGameOver() || myWorld.isHighScore()) {
        	 if (retryButton.isTouchUp(screenX, screenY)) {
                 myWorld.restart();
                 return true;
             }
             
             if (backToMenuButton.isTouchUp(screenX, screenY)) {
                 myWorld.backToMenu();
                 return true;
             }
        }
        else if (myWorld.isCollection()) {

            if (cBackToMenuButton.isTouchUp(screenX, screenY)) {
                myWorld.backToMenu();
                return true;
            }
        }
        
        else if (myWorld.isRunning()) {
			float dist = (float) Math.sqrt((screenX-prevX)*(screenX-prevX) + (screenY-prevY)*(screenY-prevY));
			
			if(dist > 30) {
				Gdx.app.log("InputHandler", "Using power because distance = "+dist);
				myWorld.getBird().usePower();
			}
		}

        return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	

    private int scaleX(int screenX) {
        return (int) (screenX / scaleFactorX);
    }

    private int scaleY(int screenY) {
        return (int) (screenY / scaleFactorY);
    }

    public List<SimpleButton> getMenuButtons() {
        return menuButtons;
    }
    
    public List<PibHolder> getPibHolders() {
        return pibHolders;
    }
    
    public List<SimpleButton> getGOButtons() {
    	return GOButtons;
    }
    
    public DoubleTextureButton getCollectionBackButton() {
    	return cBackToMenuButton;
    }
	

}
