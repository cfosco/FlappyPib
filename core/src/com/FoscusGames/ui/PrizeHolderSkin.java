package com.FoscusGames.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PrizeHolderSkin {

	public TextureRegion bgArea, prizeArea, nameArea, grpArea, line;
	
	
	public PrizeHolderSkin() {
		
	}
	
	public void flipAllTextures(boolean x, boolean y) {
		
		bgArea.flip(x, y);
		prizeArea.flip(x, y);
		nameArea.flip(x, y);
		grpArea.flip(x, y);
		line.flip(x, y);
	}
	
	
	public void loadSkin(Texture t) {
		
		bgArea = new TextureRegion(t, 0,0,326,142);
		prizeArea = new TextureRegion(t, 0,143,131,132);
		nameArea = new TextureRegion(t, 133,144,293-133,189-144);
		grpArea = new TextureRegion(t, 133,190,293-133,208-190);
		line = new TextureRegion(t, 329,3,12,140);

        flipAllTextures(false, true);
	}
}
