package com.FoscusGames.gameobjects;

public class Ground extends Scrollable {
	
	public Ground (float x, float y, float width, float height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
		
		
	}

	public void onRestart(float x, float scrollSpeed) {
		position.x = x;
		velocity.x = scrollSpeed;
		
		boundingBox.setX(x);
		
	}



}
