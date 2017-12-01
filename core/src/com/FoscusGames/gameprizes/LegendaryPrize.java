package com.FoscusGames.gameprizes;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.gameobjects.Prize;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LegendaryPrize extends Prize {

	public LegendaryPrize(TextureRegion s, String n, String d, String g) {
		super(s, n, d, g);
		
	}
	
	public LegendaryPrize(TextureRegion s, String n, String d) {
		super(s, n, d);
	}
	
	@Override
	public void drawRarity(Batch batcher, float X, float Y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rot) {
		
		batcher.draw(AssetLoader.legendary, X, Y, originX, originY, width, height, scaleX, scaleY, rot);
		
	}
	
	@Override
	public TextureRegion getRaritySprite() {
		return AssetLoader.legendary;
	}
	
	/*@Override
	public void drawName(Batch batcher, float X, float Y, float scale) {
		
		float prevScale = AssetLoader.orangeFont.getScaleX();
		AssetLoader.orangeFont.setScale(scale);
		AssetLoader.orangeFont.draw(batcher, this.name, X, Y);
		AssetLoader.orangeFont.setScale(prevScale);
	}*/

	@Override
	public String getRarity() {
		return "legendary";
	}
	
	public BitmapFont getFont() {
		return AssetLoader.orangeFont;
	}

}
