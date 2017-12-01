package com.FoscusGames.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ScoreboardSkin {
	
public TextureRegion bgArea, prizeArea, scoreArea;
	
	public ScoreboardSkin() {
		
	}
	
	public void flipAllTextures(boolean x, boolean y) {
		
		bgArea.flip(x, y);
		prizeArea.flip(x, y);
		scoreArea.flip(x, y);
	}

}
