package com.FoscusGames.gameworld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.fpHandlers.CollisionHandler;
import com.FoscusGames.fpHandlers.FPConstants;
import com.FoscusGames.fpHandlers.InputHandler;
import com.FoscusGames.fpHandlers.PowerLoader;
import com.FoscusGames.fpHandlers.ScoreManager;
import com.FoscusGames.fpHandlers.SoundLoader;
import com.FoscusGames.gameobjects.Bird;
import com.FoscusGames.gameobjects.ScrollHandler;
import com.FoscusGames.gameprizes.PrizeHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

public class GameWorld {
	
	public enum GameState {
		
		MENU,PIBSELECT,READY,RUNNING,GAMEOVER,HIGHSCORE,COLLECTION,SOUNDOPTIONS
	}
	
	private Bird bird;
	private ScrollHandler scroller;
	private Rectangle dirt;
	private GameState currentState;
	private List<Bird> pibs;
	private GameRenderer renderer;
	private InputHandler ih;
	
	private float runTime = 0;
	public int bgType = 0;
	public int midPointY;
	Random r;
	
	
	public GameWorld(int midPointY) {
		
		this.midPointY = midPointY;
		

		PrizeHandler.initGeneralPrizes();
		
		scroller = new ScrollHandler(this);	 //midPointY+66);

		
		initPibs();
		
		 r = new Random();
		
		bird = pibs.get(r.nextInt(pibs.size()));
		scroller = new ScrollHandler(this);	 //midPointY+66);
		dirt = new Rectangle(0, FPConstants.playableHeight, FPConstants.gameWidth, 11);//midPointY +66, 136, 11);
		currentState = GameState.MENU;
		if(FPConstants.isMusicOn)SoundLoader.menuMusic.play();
		
		//FPConstants.groundHeight = 63;
		FPConstants.midPointY = midPointY;
		
	}
	
	public void initPibs() {
		
		pibs = new ArrayList<Bird>();
		
		
		PowerLoader.load(this);

		float pibX = 33;
		float pibY = midPointY-5;
		
		
		pibs.add(new Bird(pibX,pibY, "Notaro", AssetLoader.notaroSpriteMgr , SoundLoader.notaroSoundMgr, PowerLoader.notaroPower));
		pibs.add(new Bird(pibX,pibY, "LePib", AssetLoader.athaSpriteMgr, SoundLoader.athaSoundMgr, PowerLoader.athaPower));
		pibs.add(new Bird(pibX,pibY, "Eze", AssetLoader.ezeSpriteMgr, SoundLoader.ezeSoundMgr, PowerLoader.ezePower));
		pibs.add(new Bird(pibX,pibY, "Tom", AssetLoader.tomiSpriteMgr, SoundLoader.tomiSoundMgr, PowerLoader.tomPower));
		pibs.add(new Bird(pibX,pibY, "Fedoco", AssetLoader.fedeSpriteMgr, SoundLoader.fedeSoundMgr, PowerLoader.fedePower));
		pibs.add(new Bird(pibX,pibY, "Reimon", AssetLoader.reimonSpriteMgr, SoundLoader.reimonSoundMgr, PowerLoader.reimonPower));
		pibs.add(new Bird(pibX,pibY, "Cheiras", AssetLoader.danSpriteMgr,SoundLoader.danSoundMgr, PowerLoader.danPower));
		pibs.add(new Bird(pibX,pibY, "Santocho", AssetLoader.santiSpriteMgr, SoundLoader.santiSoundMgr, PowerLoader.santiPower));
		pibs.add(new Bird(pibX,pibY, "Carlinho", AssetLoader.carloSpriteMgr, SoundLoader.carloSoundMgr, PowerLoader.carloPower));
		
		
	}

	
	public void update(float delta) {
		runTime += delta;
		
		switch (currentState) {
		case READY:
		case MENU:
			updateReady(delta);
			break;
			
		case RUNNING:
			updateRunning(delta);
			break;
		
			
			
		default:
			
			break;
			
		}
		
	}
	
	
	
	private void updateReady(float delta) {
			bird.updateReady(runTime);
	        scroller.updateReady(delta);
		
	}
	
	private void updateRunning(float delta) {
		
		if (delta > .15f) {
            delta = .15f;
        }	
		
		bird.update(delta);
		scroller.update(delta);
		
		if (bird.isAlive() && CollisionHandler.collides(bird, scroller))
		{
			scroller.stop();
			bird.die();
			renderer.prepareTransition(255, 255, 255, .3f);

		}
		
		if (CollisionHandler.collides(bird,dirt)) {
			
			if (bird.isAlive()) {
				
				renderer.prepareTransition(255, 255, 255, .3f);
				bird.die();
			}
			
			int score = ScoreManager.getScore();
			
            scroller.stop();
            bird.decelerate();
			PrizeHandler.determinePrize(bird, score);
            currentState = GameState.GAMEOVER;

            
            if (score > AssetLoader.getHighScore()) {
            	
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            }

        }
		
	}

	
	public List<Bird> getPibs() {
		return pibs;
	}
	
	public Bird getBird() {
		
		return bird;
	}
	
	public ScrollHandler getScroller() {
		return scroller;
	}
	
//	public int getScore() {
//		return score;
//	
//	}
	
	public int getMidPointY() {
        return midPointY;
    }
	
//	public void addScore(int inc) {
//		score +=inc;
//	}
	
	public void soundOptions() {
		renderer.setSoundOptionsMenu();
		currentState = GameState.SOUNDOPTIONS;
	}
	
	public void pibSelect() {
		
		renderer.setPibSelectMenu();
		currentState = GameState.PIBSELECT;
	}
	
	public void collection() {
		
		renderer.setCollectionMenu();
		currentState = GameState.COLLECTION;
	}
	
	public void menu() {
        Gdx.input.setInputProcessor(ih);

		renderer.prepareTransition(0,0,0,1f);
		
		/*if (FPConstants.isMusicOn) {
			AssetLoader.menuMusic.play();
		} else {
			AssetLoader.menuMusic.stop();
		}*/
		
		if (SoundLoader.menuMusic.getVolume()<=FPConstants.musicVolume) {
			SoundLoader.menuMusic.setVolume(FPConstants.musicVolume);
		}
		
		currentState = GameState.MENU;
	}
	
	public void ready() {
		
		
		
		if (SoundLoader.menuMusic.isPlaying() && FPConstants.isSoundOn) {
			SoundLoader.menuMusic.setVolume(FPConstants.musicVolume*0.6f);
		}
		
		currentState = GameState.READY;		
		bird.prepareSounds();
		renderer.prepareTransition(0,0,0,1f);
	}

	public void start() {
	        currentState = GameState.RUNNING;
	        
	    }

	public void restart() {
	        ScoreManager.setScore(0);
	        bird.onRestart(midPointY - 5);
	        scroller.onRestart();
	        bgType =r.nextInt(AssetLoader.backgrounds.size());
	        
	        ready();

	    }
	
	public void backToMenu() {
		
		ScoreManager.setScore(0);
        bird.onRestart(midPointY - 5);
        scroller.onRestart();
        menu();

    }


    public void selectPib(int index) {
    	bird = pibs.get(index);
    	bird.playChooseSound();
    	menu();
    	
    }
	
    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

    public boolean isMenu() {
        return currentState == GameState.MENU;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }
    
    public boolean isPibSelect() {
    	return currentState == GameState.PIBSELECT;
    }
    
    public boolean isCollection() {
    	return currentState == GameState.COLLECTION;
    }
    
    public boolean isSoundOptions() {
    	return currentState == GameState.SOUNDOPTIONS;
    }
    

	public void setRenderer(GameRenderer renderer) {
		this.renderer = renderer;
	}
	
	public void setInputHandler(InputHandler ih) {
		this.ih = ih;
	}
	
	public float getRunTime() {
		return runTime;
	}
	
	public void resetRunTime() {
		runTime = 0;
	}
	
	public void prepareTransition(int r,int g,int b,float d) {
		renderer.prepareTransition(r,g,b,d);
	}
	
}
