package com.FoscusGames.gameobjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class ScrollableSprite extends Sprite {


    protected Vector2 velocity;
    protected Vector2 deviation;
    protected boolean isScrolledLeft;

    public ScrollableSprite(TextureRegion t, float scrollSpeed) {
    	super(t);
        velocity = new Vector2(scrollSpeed, 0);
        isScrolledLeft = false;
        deviation = new Vector2(0, 0);
    }
    
    public void setScrollSpeed(float s) {
    	velocity.x = s;
    }

    public void setIncrementalDeviation(float x, float y) {
    	deviation.set(x, y);
    }
    
    public void update(float delta) {
    	
    	Vector2 v = new Vector2(getX(),getY());
    	v.add(velocity.cpy().scl(delta));
    	
    	setPosition(v.x+deviation.x,v.y+deviation.y);
        
        if (getX() + getWidth() < 0) {
            isScrolledLeft = true;
        }
    }

    // Reset: Should Override in subclass for more specific behavior.
    public void reset(float newX) {
        setX(newX);
        isScrolledLeft = false;
    }
    
    public void stop () {
    	velocity.x = 0;
    }
    
    

    public boolean isScrolledLeft() {
        return isScrolledLeft;
    }

}
