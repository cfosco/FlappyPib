package com.FoscusGames.gameobjects;

import com.FoscusGames.fpHandlers.FPConstants;
import com.FoscusGames.fpHandlers.ScoreManager;
import com.FoscusGames.gameworld.GameWorld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;

public class ScrollHandler {
	
	private Pipe pipe1, pipe2, pipe3;
	private Ground frontGround, backGround;
	private GameWorld gameWorld;
	
	private float scrollSpeed;


    public static final int SCROLL_SPEED = -54;	
    public static final int PIPE_SEPARATION = 53;	//Previous: 49
    
    
    public ScrollHandler(GameWorld gameWorld) {
    	
    	this.gameWorld = gameWorld;
    	
    	FPConstants.groundHeight = FPConstants.nonPlayableHeight*0.2f;
    	
    	frontGround = new Ground(0,FPConstants.playableHeight,FPConstants.gameWidth+10, FPConstants.groundHeight, SCROLL_SPEED);	//11
    	backGround = new Ground(frontGround.getTailX(), FPConstants.playableHeight, FPConstants.gameWidth+10,FPConstants.groundHeight, SCROLL_SPEED);
    	
    	pipe1 = new Pipe(210, 0, 22, 60, SCROLL_SPEED, FPConstants.playableHeight);
    	pipe2 = new Pipe(pipe1.getTailX()+PIPE_SEPARATION, 0, 22, 60, SCROLL_SPEED, FPConstants.playableHeight); //IN TUTORIAL: 22,70 !!!!!!!!!!!!!!!!!!!!
    	pipe3 = new Pipe(pipe2.getTailX()+PIPE_SEPARATION, 0, 22, 60, SCROLL_SPEED, FPConstants.playableHeight);
    	
    }

    public void update(float delta) {
    	
    	frontGround.update(delta);
    	backGround.update(delta);
    	
    	pipe1.update(delta);
    	pipe2.update(delta);
    	pipe3.update(delta);
    	
        if (pipe1.isScrolledLeft()) {
            pipe1.reset(pipe3.getTailX() + PIPE_SEPARATION);
        } else if (pipe2.isScrolledLeft()) {
            pipe2.reset(pipe1.getTailX() + PIPE_SEPARATION);

        } else if (pipe3.isScrolledLeft()) {
            pipe3.reset(pipe2.getTailX() + PIPE_SEPARATION);
        }
        
        if (frontGround.isScrolledLeft()) {
            frontGround.reset(backGround.getTailX());

        } else if (backGround.isScrolledLeft()) {
            backGround.reset(frontGround.getTailX());
        }
        
        
        //NEW
        Bird bird=gameWorld.getBird();
        
        if (!pipe1.isScored() && pipe1.getX() + (pipe1.getWidth() / 2) < bird.getX() + bird.getWidth()) {
            ScoreManager.addScore(1);
            pipe1.setScored(true);
            bird.playScoreSound();
        } else if (!pipe2.isScored()  && pipe2.getX() + (pipe2.getWidth() / 2) < bird.getX() + bird.getWidth()) {
        	ScoreManager.addScore(1);
            pipe2.setScored(true);
            bird.playScoreSound();

        } else if (!pipe3.isScored() && pipe3.getX() + (pipe3.getWidth() / 2) < bird.getX() + bird.getWidth()) {
        	ScoreManager.addScore(1);
            pipe3.setScored(true);
            bird.playScoreSound();

        }
        
        

    }
    
    public void changePipeVerticalGap(float newValue) {
    	
    	pipe1.changeVerticalGap(newValue);
    	pipe2.changeVerticalGap(newValue);
    	pipe3.changeVerticalGap(newValue);
    	
    	
    }
    
    public void resetPipeVerticalGap() {

    	pipe1.resetVerticalGap();
    	pipe2.resetVerticalGap();
    	pipe3.resetVerticalGap();
    	
    }
    
    
    public void drawPipes(Batch batcher) {
    	pipe1.draw(batcher);
    	pipe2.draw(batcher);
    	pipe3.draw(batcher);
    }
    
    public boolean isTapOnPipe(float tapX, float tapY) {

    	if (pipe1.isTapOnPipe(tapX, tapY)) return true;
    	if (pipe2.isTapOnPipe(tapX, tapY)) return true;
    	if (pipe3.isTapOnPipe(tapX, tapY)) return true;
    	
    	return false;
    	
    }
    
	public void destroyTappedPipe(float tapX, float tapY) {
		
		pipe1.destroyTapped(tapX, tapY);
		pipe2.destroyTapped(tapX, tapY);
		pipe3.destroyTapped(tapX, tapY);
		
	}
    
    
    public Ground getFrontGround() {
        return frontGround;
    }

    public Ground getBackGround() {
        return backGround;
    }

    public Pipe getPipe1() {
        return pipe1;
    }

    public Pipe getPipe2() {
        return pipe2;
    }

    public Pipe getPipe3() {
        return pipe3;
    }

	public boolean collides(Bird bird) {

		return (pipe1.collides(bird) || pipe2.collides(bird) || pipe3.collides(bird));
		
	}
	
	public boolean collides(Laser laser) {

		return (pipe1.collides(laser) || pipe2.collides(laser) || pipe3.collides(laser));
		
	}
	
	

	public void stop () {
		
		frontGround.stop();
    	backGround.stop();
    	
    	pipe1.stop();
    	pipe2.stop();
    	pipe3.stop();
		
		
	}

	public void onRestart() {
		frontGround.onRestart(0, SCROLL_SPEED);
        backGround.onRestart(frontGround.getTailX(), SCROLL_SPEED);
        pipe1.onRestart(210, SCROLL_SPEED);
        pipe2.onRestart(pipe1.getTailX() + PIPE_SEPARATION, SCROLL_SPEED);
        pipe3.onRestart(pipe2.getTailX() + PIPE_SEPARATION, SCROLL_SPEED);
		
	}

	public void updateReady(float delta) {
		frontGround.update(delta);
		backGround.update(delta);
		
		   if (frontGround.isScrolledLeft()) {
	            frontGround.reset(backGround.getTailX());

	        } else if (backGround.isScrolledLeft()) {
	            backGround.reset(frontGround.getTailX());

	        }
		
	}
	
	public void setScrollSpeed(float s) {

		//Gdx.app.log("Scrollhandler", "setScrollSpeed Called with s = "+s);
		scrollSpeed = s;
		
    	frontGround.setScrollSpeed(s);
    	backGround.setScrollSpeed(s);
    	
    	pipe1.setScrollSpeed(s);
    	pipe2.setScrollSpeed(s);
    	pipe3.setScrollSpeed(s);
	}
	
	public void resetScrollSpeed() {
		
		//Gdx.app.log("Scrollhandler", "reset called");

		
		scrollSpeed = SCROLL_SPEED;

    	frontGround.setScrollSpeed(SCROLL_SPEED);
    	backGround.setScrollSpeed(SCROLL_SPEED);
    	
    	pipe1.setScrollSpeed(SCROLL_SPEED);
    	pipe2.setScrollSpeed(SCROLL_SPEED);
    	pipe3.setScrollSpeed(SCROLL_SPEED);
	}

	public float getNormalScrollSpeed() {
		// TODO Auto-generated method stub
		return SCROLL_SPEED;
	}

	public void hidePipes() {
		pipe1.hide();
		pipe2.hide();
		pipe3.hide();
		
	}
	
	public void unHidePipes() {
		pipe1.unHide();
		pipe2.unHide();
		pipe3.unHide();
		
	}

	public float getScrollSpeed() {
		return scrollSpeed;
	}


	
}
