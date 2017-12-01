package com.FoscusGames.gameprizes;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.gameobjects.Prize;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RarePrize extends Prize {


	public RarePrize(TextureRegion s, String n, String d, String g) {
		super(s, n, d, g);
		
	}
	
	public RarePrize(TextureRegion s, String n, String d) {
		super(s, n, d);
	}
	
	@Override
	public void drawRarity(Batch batcher, float X, float Y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rot) {
		
		batcher.draw(AssetLoader.rare, X, Y, originX, originY, width, height, scaleX, scaleY, rot);
		
	}
	
	@Override
	public TextureRegion getRaritySprite() {
		return AssetLoader.rare;
	}
	
	/*@Override
	public void drawName(Batch batcher, float X, float Y, float scale) {
		
		float prevScale = AssetLoader.blueFont.getScaleX();
		AssetLoader.blueFont.setScale(scale);
		AssetLoader.blueFont.draw(batcher, this.name, X, Y);
		AssetLoader.blueFont.setScale(prevScale);
	}*/

	@Override
	public String getRarity() {
		return "rare";
	}
	
	public BitmapFont getFont() {
		return AssetLoader.blueFont;
	}

}
