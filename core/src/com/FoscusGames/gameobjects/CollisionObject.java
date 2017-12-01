package com.FoscusGames.gameobjects;

import com.badlogic.gdx.math.Rectangle;

public abstract class CollisionObject {
	
	protected Rectangle boundingBox;
	protected boolean isMaterial;
	
	public Rectangle getBoundingBox() {
		return boundingBox;
	}
	
	public boolean isMaterial() {
		return isMaterial;
	}
	


}
