package com.FoscusGames.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Scrollable extends CollisionObject{
	
    protected Vector2 position;
    protected Vector2 velocity;
    protected float width;
    protected float height;
    protected boolean isScrolledLeft;

    public Scrollable(float x, float y, float width2, float height2, float scrollSpeed) {
        position = new Vector2(x, y);
        velocity = new Vector2(scrollSpeed, 0);
        this.width = width2;
        this.height = height2;
        isScrolledLeft = false;
        
        boundingBox = new Rectangle(x,y,width2,height2);
    }
    
    public void setScrollSpeed(float s) {
    	velocity.x = s;
    }

    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));
        boundingBox.setPosition(position);
        
        // If the Scrollable object is no longer visible:
        if (position.x + width < 0) {
            isScrolledLeft = true;
        }
    }

    // Reset: Should Override in subclass for more specific behavior.
    public void reset(float newX) {
        position.x = newX;
        boundingBox.setX(newX);
        isScrolledLeft = false;
    }
    
    public void stop () {
    	velocity.x = 0;
    }
    
    

    public boolean isScrolledLeft() {
        return isScrolledLeft;
    }

    public float getTailX() {
        return position.x + width;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }


}


