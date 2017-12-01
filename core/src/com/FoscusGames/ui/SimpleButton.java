package com.FoscusGames.ui;

import com.FoscusGames.fpHandlers.FPConstants;
import com.FoscusGames.fpHandlers.SoundLoader;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

public class SimpleButton extends Widget {
	//protected float x, y, width, height;
	protected float scale = 1;

    protected TextureRegion buttonUp;
    protected TextureRegion buttonDown;

    protected Rectangle bounds;

    protected boolean isPressed = false;

    public SimpleButton(float x, float y, float width, float height, TextureRegion buttonUp, TextureRegion buttonDown) {

    	this.setPosition(x, y);
        
        this.setWidth(width);
        this.setHeight(height);
        this.buttonUp = buttonUp;
        this.buttonDown = buttonDown;

        bounds = new Rectangle(x, y, width, height);

    }

    public boolean isClicked(int screenX, int screenY) {
        return bounds.contains(screenX, screenY);
    }

    @Override
    public void draw(Batch batcher, float parentAlpha) {
    	calculateBounds();
        batcher.setColor(1,1,1,1);

        if (isPressed) {
            batcher.draw(buttonDown, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        } else {
            batcher.draw(buttonUp, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        }
    }

    public boolean isTouchDown(int screenX, int screenY) {

        if (bounds.contains(screenX, screenY)) {
            isPressed = true;
            return true;
        }

        return false;
    }

    public boolean isTouchUp(int screenX, int screenY) {
        
        // It only counts as a touchUp if the button is in a pressed state.
        if (bounds.contains(screenX, screenY) && isPressed) {
            isPressed = false;
            if(FPConstants.isSoundOn) SoundLoader.buttonPress.play();
            return true;
        }
        
        // Whenever a finger is released, we will cancel any presses.
        isPressed = false;
        return false;
    }
    
    public boolean isPressed() {
    	return isPressed;
    }

    public void calculateBounds() {
    	bounds.height = getHeight();
    	bounds.width = getWidth();
    	bounds.x = getX();
    	bounds.y = getY();
    	
    }
    

	public float center (float pos, float length, float areaLength) {
		return pos+areaLength/2-length/2;
	}
    
    
    
}
