package com.FoscusGames.gameobjects;

import java.util.Random;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public class Pipe extends Scrollable implements Drawable{
	
	private Random r;
	
	private Rectangle skullUp, skullDown, barUp, barDown;
	
	private float verticalGap = 45;
	public static final int VERTICAL_GAP = 45;
	public static final int SKULL_WIDTH = 24;
	public static final int SKULL_HEIGHT = 11;
	
	private float groundY;
	
	private boolean scored = false;
	private boolean isUpPipe,isDownPipe;
	private boolean hidden;
	
	private float opacity;

	
	public Pipe (float x, float y, int width, int height, float scrollSpeed, float groundY) {
		super (x,y,width,height,scrollSpeed);

	    skullUp = new Rectangle();
	    skullDown = new Rectangle();
	    barUp = new Rectangle();
	    barDown = new Rectangle();
	    this.groundY = groundY;
	    
	    isUpPipe = true;
	    isDownPipe = true;
	    hidden = false;
	    
		
		r = new Random();
	}
	
	
	public void changeVerticalGap(float newValue) {
		
		verticalGap = newValue;
	}
	
	public void resetVerticalGap() {
		verticalGap = VERTICAL_GAP;
	}
	
	
	@Override 
	public void reset (float newX) {
		
		super.reset(newX);
		
		scored = false;
		height = r.nextInt(90)+15;
		
		
		isUpPipe = true;
		isDownPipe = true;
		
	}
	
	@Override
	public void update(float delta) {
		super.update(delta);
		

        barUp.set(position.x, position.y, width, height);
        barDown.set(position.x, position.y + height + verticalGap, width,
                groundY - (position.y + height + verticalGap));

        // Our skull width is 24. The bar is only 22 pixels wide. So the skull
        // must be shifted by 1 pixel to the left (so that the skull is centered
        // with respect to its bar).
        
        // This shift is equivalent to: (SKULL_WIDTH - width) / 2
        skullUp.set(position.x - (SKULL_WIDTH - width) / 2, position.y + height
                - SKULL_HEIGHT, SKULL_WIDTH, SKULL_HEIGHT);
        skullDown.set(position.x - (SKULL_WIDTH - width) / 2, barDown.y,
                SKULL_WIDTH, SKULL_HEIGHT);
		
	}
	
	public Rectangle getSkullUp() {
        return skullUp;
    }

    public Rectangle getSkullDown() {
        return skullDown;
    }

    public Rectangle getBarUp() {
        return barUp;
    }

    public Rectangle getBarDown() {
        return barDown;
    }
    


	public boolean collides(Bird bird) {
		
		if(!bird.isMaterial())
			return false;

		if(hidden) return false;

		if (position.x < bird.getX() + bird.getWidth()) {
			
			if (isUpPipe) {
				if(Intersector.overlaps(bird.getBoundingCircle(), barUp) || Intersector.overlaps(bird.getBoundingCircle(), skullUp)) {
					return true;
				}
			}
			
			if (isDownPipe) {
				return (Intersector.overlaps(bird.getBoundingCircle(), barDown) || Intersector.overlaps(bird.getBoundingCircle(), skullDown));
			}
			 
		}
		
		return false;
	}
	
	public boolean collides(Laser laser) {
		
		if (position.x < laser.getBoundingBox().getX() + laser.getBoundingBox().getWidth()) {
			
			if ((Intersector.overlaps(laser.getBoundingBox(), barUp) || Intersector.overlaps(laser.getBoundingBox(), skullUp) ) && isUpPipe)
			{
				isUpPipe = false;
				return true;
			}
			else if ((Intersector.overlaps(laser.getBoundingBox(), barDown) || Intersector.overlaps(laser.getBoundingBox(), skullDown)) && isDownPipe)
			{
				isDownPipe = false;
				return true;
			}
			
		}
		
		return false;
	}
	
	

	public boolean isScored() {
		
		return scored;
	}

	public void setScored(boolean b) {
		scored = b;
		
	}

	public void onRestart(float x, float scrollSpeed) {

		velocity.x = scrollSpeed;
		reset(x);
		
	}
	

    public void draw(Batch batcher) {
    	
    	Color color = batcher.getColor();
    	float oldAlpha = color.a;
    	color.a = opacity;
    	color.r = 3;

    	if(hidden) {
    	
    	batcher.setColor(color);
    	}
    	
    	
    	if (isUpPipe) {
        batcher.draw(AssetLoader.bar, getX(), getY(), getWidth(),
                getHeight());
    	}
        
    	if(isDownPipe) {
        batcher.draw(AssetLoader.bar, getX(), getY() + getHeight() + verticalGap, 
                getWidth(), Gdx.graphics.getHeight()-getHeight()-verticalGap);	//midPointY + 66 - (getHeight() + verticalGap));
    	}
        
        
        
        drawSkulls(batcher);
        

        if(hidden) {
    	color.a = oldAlpha;
    	batcher.setColor(color);
        }

    }

    public void drawSkulls(Batch batcher) {

    	if(isUpPipe) {
        batcher.draw(AssetLoader.pipeUp, getX() - 1,
                getY() + getHeight() - 14, 24, 14);
    	}
        
    	if (isDownPipe) {
        batcher.draw(AssetLoader.pipeDown, getX() - 1,
                getY() + getHeight() + verticalGap, 24, 14);
    	}

    }

	@Override
	public void draw(Batch batch, float x, float y, float width, float height) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public float getLeftWidth() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void setLeftWidth(float leftWidth) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public float getRightWidth() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void setRightWidth(float rightWidth) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public float getTopHeight() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void setTopHeight(float topHeight) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public float getBottomHeight() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void setBottomHeight(float bottomHeight) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public float getMinWidth() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void setMinWidth(float minWidth) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public float getMinHeight() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void setMinHeight(float minHeight) {
		// TODO Auto-generated method stub
		
	}


	public void hide() {
		
		opacity = 0.2f;
		hidden = true;
		
	}
	
	public void unHide() {
		
		
		hidden = false;
		
	}


	public boolean isTapOnPipe(float tapX, float tapY) {

		if (tapX >= getX() && tapX <= getX()+getWidth()) {
			if(tapY <= getHeight()) {
				if(isUpPipe)return true;
			}
			
			if (tapY >= getY() + getHeight() + verticalGap) {
				if(isDownPipe)return true;
			}
			
			return false;
		}
		
		return false;
		
	}
	
	
	public void destroyTapped(float tapX, float tapY) {
		
		if (tapX >= getX() && tapX <= getX()+getWidth()) {
			if(tapY <= getHeight()) {
				isUpPipe = false;
			}
			
			if (tapY >= getY() + getHeight() + verticalGap) {
				isDownPipe = false;
			}
			
			
		}
		
	
	}

}
