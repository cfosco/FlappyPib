package com.FoscusGames.ui;

import java.util.List;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.gameobjects.Prize;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PrizeHolder extends Holder{
	

	private Prize prize;
	private TextureRegion nameArea, prizeArea, bgArea;
	private float grpTextFactor, nameTextFactor, descTextFactor, prizeAreaWidth, prizeAreaHeight, nameAreaWidth, nameAreaHeight, prizeHolderWidth, prizeHolderHeight, rarAreaWidth, rarAreaHeight, margin, originX, originY, descAreaWidth;
	private PrizeHolderSkin skin;
	
	private float X;
	private float Y;
	private float scale = 1;
	
	public PrizeHolder(Prize prize, PrizeHolderSkin skin) {
		this.prize = prize;
		this.skin = skin;
		
	}
	
	public PrizeHolder(Prize prize, PrizeHolderSkin skin, float X, float Y, float prizeHolderWidth, float prizeHolderHeight) {
		this.prize = prize;
		//this.X = X;
		//this.Y = Y;
		
		this.setPosition(X, Y);
		this.setHeight(prizeHolderHeight);
		this.setWidth(prizeHolderWidth);
		
		this.skin = skin;
		
		if (this.prizeHolderHeight>this.prizeHolderWidth) {
			this.prizeHolderWidth = 2*this.prizeHolderHeight;
		}
		
		calculateWidthsHeights();

	}
	
	public void calculateWidthsHeights() {
		

		margin= prizeHolderHeight*0.05f;

		prizeAreaHeight = prizeHolderHeight-2*margin;
		prizeAreaWidth = prizeAreaHeight;
		rarAreaWidth = prizeHolderWidth*0.07f;
		rarAreaHeight = prizeHolderHeight;
		nameAreaWidth = prizeHolderWidth - 3*margin-prizeAreaWidth-rarAreaWidth;
		nameAreaHeight = prizeHolderHeight * 0.35f;
		descAreaWidth = nameAreaWidth;


		originX = X+prizeHolderWidth/2;
		originY = Y+prizeHolderHeight/2;
		
		nameTextFactor = prizeHolderHeight/50;
		descTextFactor = prizeHolderHeight/82;
		grpTextFactor = prizeHolderHeight/85;
		
	}
	
	@Override
	public void draw(Batch batcher, float parentAlpha) {
		
		X = this.getX();
		Y = this.getY();
		prizeHolderHeight = this.getHeight();
		prizeHolderWidth = this.getWidth();
		
		this.calculateWidthsHeights();
		
		if(prize != null) {
		
		float nameAreaX = X+2*margin*0.9f+prizeAreaWidth;
		float nameAreaY = Y+ margin;
		
		float descAreaX = nameAreaX;
		float descAreaY = nameAreaY+nameAreaHeight+margin*0.8f;
		
		
		batcher.draw(skin.bgArea, X, Y, -X+originX, -Y+originY, prizeHolderWidth, prizeHolderHeight, scale, scale, rot);
		
		drawLine(batcher);
		
		drawName(batcher, nameAreaX, nameAreaY);
		
		drawDescription(batcher, descAreaX, descAreaY);
		
		drawRarity(batcher);
		
		drawGroup(batcher);
		
		drawPrize(batcher);
		
		}

	}
	
	public void drawPrize(Batch batcher) {
		batcher.draw(skin.prizeArea, X+margin,Y+margin,-X-margin+originX,-Y-margin+originY, prizeAreaWidth, prizeAreaHeight, scale, scale, rot);

		this.drawInSlot(batcher, prize.getSprite(), X+margin, Y+margin, -X-margin+originX, -Y-margin+originY, prizeAreaWidth, prizeAreaHeight);

		
	}
	
	public void drawName(Batch batcher, float nameAreaX, float nameAreaY) {
		
		float fitCst=nameAreaHeight*0.05f;
		
		batcher.draw(skin.nameArea, nameAreaX, nameAreaY, -nameAreaX+originX, -nameAreaY+originY, nameAreaWidth, nameAreaHeight, scale, scale,rot);

		//TO DO: No usar esta funcion en tiempo de ejecución
		drawTextMultilineAutoCentered(batcher, prize.getName(), nameAreaX, nameAreaY, nameAreaWidth, nameAreaHeight-fitCst, prize.getFont(), nameTextFactor*scale);		
		
		//Gdx.app.log("drawName in PHolder", "nameAreaX = " + nameAreaX+ " nameAreaY = " + nameAreaY+ "\nnameAreaWidth = " + nameAreaWidth+ " nameAreaHeiht = " + nameAreaHeight ); 

	}
	
	public void drawDescription(Batch batcher, float descAreaX, float descAreaY) {
		
		
		drawTextWrapped(batcher, prize.getDescription(), descAreaX, descAreaY, descAreaWidth, AssetLoader.blackFont, descTextFactor*scale);
	}
	
	public void drawGroup(Batch batcher) {
			

		float grpAreaWidth = nameAreaWidth;
		float grpAreaHeight = prizeHolderHeight*0.12f;
		
		float fitCst=grpAreaHeight*0.05f;

		
		float grpX = X+2*margin*0.9f+prizeAreaWidth;
		float grpY = Y+prizeHolderHeight-margin-grpAreaHeight;
		
		
		batcher.draw(skin.grpArea, grpX, grpY, -grpX+originX, -grpY+originY, grpAreaWidth, grpAreaHeight, scale, scale,rot);

		this.drawTextCentered(batcher, "Group: "+prize.getGroup(), grpX, grpY, grpAreaWidth, grpAreaHeight-fitCst, AssetLoader.whiteFontNoOutline, grpTextFactor*scale);
	
	}
	
	public void drawLine(Batch batcher) {
		
		float lineWidth = rarAreaWidth*0.3f;
		float lineHeight = prizeHolderHeight;
		
		
		float lineX = X+prizeHolderWidth - rarAreaWidth-lineWidth;
		float lineY = Y;
		
		batcher.draw(skin.line, lineX, lineY, -lineX+originX, -lineY+originY, lineWidth, lineHeight, scale, scale, rot);
		
	}
	
	
	public void drawRarity(Batch batcher) {
		
		float rHeight = prize.getRaritySprite().getRegionHeight();
		float rWidth = prize.getRaritySprite().getRegionWidth();
		
		float realHeight = rarAreaWidth*0.5f;
		float realWidth = realHeight*rWidth/rHeight;
		
		float rarX = X + prizeHolderWidth-rarAreaWidth/2+ rHeight/2 ;
		float rarY = center(Y, realWidth, rarAreaHeight);
		

		float rarCenterX = 0;
		float rarCenterY = 0;
		
		
		//this.drawInSlot(batcher, prize.getRaritySprite(), rarX, rarY, -rarX+originX, -rarY+originY, rarAreaWidth, rarAreaHeight);
		
		
		prize.drawRarity(batcher, rarX, rarY, rarCenterX, rarCenterY, realWidth , realHeight , scale, scale, 90);
		
	}
	
	public void setPrize(Prize p) {
		prize = p;
	}
	
	public Prize getPrize() {
		return prize;
	}

}
