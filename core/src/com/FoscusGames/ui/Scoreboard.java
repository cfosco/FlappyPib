package com.FoscusGames.ui;

import java.util.List;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.gameobjects.Prize;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Scoreboard extends Holder {
	
	

	private Prize prize;
	private ScoreboardSkin skin;
	private float prizeAreaWidth, prizeAreaHeight, scoreAreaWidth, scoreAreaHeight, nameAreaWidth, nameAreaHeight,  scoreboardWidth, scoreboardHeight, rarAreaWidth, rarAreaHeight, marginX, marginY, originX, originY;
	
	private float X;
	private float Y;
	private float scale = 1;
	
	
	
	public Scoreboard(ScoreboardSkin skin, float X, float Y, float scorebWidth, float scorebHeight) {
		this.X = X;
		this.Y = Y;
		this.prize = null;
		this.skin = skin;
		
		
		
		this.scoreboardWidth = scorebWidth;
		this.scoreboardHeight = scorebHeight;
		
		if (this.scoreboardWidth>this.scoreboardHeight) {
			this.scoreboardHeight = 2*this.scoreboardWidth;
		}
		
		calculateWidthsHeights();

	}
	
	
	public void calculateWidthsHeights() {
		

		marginX= scoreboardWidth*0.053f;
		marginY= scoreboardHeight*0.035f;

		nameAreaHeight = scoreboardHeight * 0.2f;
		scoreAreaHeight = scoreboardHeight * 0.2f;
		prizeAreaHeight = scoreboardHeight * 0.4f;
		rarAreaHeight = scoreboardHeight -nameAreaHeight-scoreAreaHeight-prizeAreaHeight-4*marginY;
		
		scoreAreaWidth = scoreboardWidth-4*marginX;
		prizeAreaWidth = scoreboardWidth-4*marginX;
		rarAreaWidth = scoreboardWidth-2*marginX;
		nameAreaWidth = scoreboardWidth-2*marginX;

		
		originX = X+scoreboardWidth/2;
		originY = Y+scoreboardHeight/2;
		
	}
	
	
	public void setPrize(Prize p) {
		prize = p;
	}
	
	@Override
	public void draw(Batch batcher, float parentAlpha) {
		
	}
	
	public void draw(Batch batcher, int score, Prize finalPrize, float runTime) {
		
		prize = finalPrize;

		float scoreAreaX = center(X, scoreAreaWidth, scoreboardWidth);
		float scoreAreaY = Y+marginY;

		float prizeAreaX = scoreAreaX;
		float prizeAreaY = scoreAreaY+scoreAreaHeight+marginY;
		
		float nameAreaX = X+marginX;
		float nameAreaY = prizeAreaY + prizeAreaHeight;
		
		float rarAreaX = nameAreaX;
		float rarAreaY = Y+scoreboardHeight - marginY - rarAreaHeight;
		
		
		batcher.draw(skin.bgArea, X, Y, -X+originX, -Y+originY, scoreboardWidth, scoreboardHeight, scale, scale, rot);
		batcher.draw(skin.scoreArea, scoreAreaX, scoreAreaY, -scoreAreaX+originX, -scoreAreaY+originY, scoreAreaWidth, scoreAreaHeight, scale, scale, rot);
		batcher.draw(skin.prizeArea, prizeAreaX, prizeAreaY,-prizeAreaX+originX,-prizeAreaY+originY, prizeAreaWidth, prizeAreaHeight, scale, scale, rot);
		
		
		drawScore(batcher, scoreAreaX, scoreAreaY, score);
		
		
		if (prize != null) {
		drawRarity(batcher, rarAreaX, rarAreaY);
		
		drawName(batcher, nameAreaX, nameAreaY);

		drawPrize(batcher, prizeAreaX, prizeAreaY, runTime);
		}
	}
	
	
	public void drawScore(Batch batcher, float scoreAreaX, float scoreAreaY, int score) {
	
		float scoreWidth = scoreAreaWidth/3;
		float highScoreX = scoreAreaX + scoreAreaWidth-scoreWidth;
		
		float cst = scoreAreaHeight*0.07f;
		
		this.drawTextCentered(batcher, "" + score, scoreAreaX, scoreAreaY+cst, scoreWidth, scoreAreaHeight, AssetLoader.font, scale);
		
		this.drawTextCentered(batcher, "" + AssetLoader.getHighScore(), highScoreX, scoreAreaY+cst, scoreWidth, scoreAreaHeight, AssetLoader.font, scale);
		
		
	}
	
	public void drawRarity(Batch batcher, float areaX, float areaY) {
		
		this.drawInSlot(batcher, prize.getRaritySprite(), areaX, areaY, -areaX+originX, -areaY+originY, rarAreaWidth, rarAreaHeight);
		
	}
	
	public void drawName(Batch batcher, float areaX, float areaY) {
		
		this.drawTextMultilineAutoCentered(batcher, prize.getName(), areaX, areaY, nameAreaWidth, nameAreaHeight, prize.getFont(), scale);
		
	}
	
	public void drawPrize(Batch batcher, float areaX, float areaY, float runTime) {
		
		float cst = prizeAreaHeight*0.09f;
		
		if (prize.getRarity() == "legendary") {
			this.drawInSlot(batcher, prize.getSprite(), areaX, areaY-3+(float)(1.5*Math.sin(5*runTime))+cst, -areaX+originX, -areaY-cst+originY, prizeAreaWidth, prizeAreaHeight-cst);

		} else {
			this.drawInSlot(batcher, prize.getSprite(), areaX, areaY+cst, -areaX+originX, -areaY-cst+originY, prizeAreaWidth, prizeAreaHeight-cst);

		}
		

	}

}
