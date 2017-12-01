package com.FoscusGames.ui;

import java.util.List;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.gameobjects.Bird;
import com.FoscusGames.gameobjects.Prize;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PibHolder extends Holder{
	
	private Bird pib;
	private TextureRegion nameArea, prizeArea, nameEnd, prizeEnd, pibArea, prizeSlot;
	private float pibAreaWidth, pibAreaHeight, prizeAreaWidth, prizeAreaHeight, 
	nameAreaWidth, nameAreaHeight, nameEndWidth, prizeEndWidth, pibHolderHeight, pibHolderWidth, originX, originY;
	private List<Prize> prizes;
	private SimpleButton button;
	
	
	float nameMargin;
	float fitInCst;
	float nameFactor;
	
	public PibHolder (Bird pib, List<TextureRegion> skin, float X, float Y, float pHW, float pHH) {
		
		this.pib = pib;
		this.setX(X);
		this.setY(Y);
		
		this.setWidth(pHW);
		this.setHeight(pHH);

		button = new SimpleButton ( X, 
									Y, 
									pibHolderWidth, 
									pibHolderHeight, 
									null,
									null );
		
		pibArea = skin.get(0);
		nameArea = skin.get(1);
		prizeArea = skin.get(2);
		nameEnd = skin.get(3);
		prizeEnd = skin.get(4);
		prizeSlot = skin.get(5);
		
		prizes = pib.getPrizes();
		
		calculateWidthsHeights();
		
		
	}
	
	public void calculateWidthsHeights() {

		fitInCst = pibHolderHeight * (6.0f/30);
		nameMargin = pibHolderHeight *0.045f;
		nameFactor = pibHolderHeight /40;

		pibAreaHeight = pibHolderHeight;
		pibAreaWidth = pibAreaHeight;
		nameAreaWidth = pibHolderWidth-pibAreaWidth;
		prizeAreaHeight = pibAreaHeight*0.4f;
		//prizeAreaWidth = prizes.size()*(prizeAreaHeight)+fitInCst;
		prizeAreaWidth = nameAreaWidth;
		nameAreaHeight = pibAreaHeight*0.3f;
		nameEndWidth = nameAreaHeight;
		prizeEndWidth = prizeAreaHeight;

		originX = X+pibAreaWidth/2;
		originY = Y+pibAreaHeight/2;
		
		button.setX(X);
		button.setY(Y);
		
		button.setWidth(pibHolderWidth);
		button.setHeight(pibHolderHeight);
	
		
	}
	
	@Override
	public void draw(Batch batcher, float parentAlpha) {
		
		X = this.getX();
		Y = this.getY();
		pibHolderHeight = this.getHeight();
		pibHolderWidth = this.getWidth();
		
		this.calculateWidthsHeights();
		
		float pAX = X+pibAreaWidth-fitInCst;
		float pAY = Y+pibAreaHeight/2;
		float nAX = X+pibAreaWidth-fitInCst-1;
		float nAY = Y+pibAreaHeight/6;
		
		Color c = batcher.getColor();
		if(pib.allPrizesAcquired()) {
			
			batcher.setColor(new Color(1f,0.7f,0.1f,1f));	//(new Color(1f,65/138f,6/226f,1f));      new Color(226/94f,187/138f,94/226f,1f));
		}
		
		batcher.draw(prizeArea, pAX, pAY, -pAX+originX, -pAY+originY, prizeAreaWidth, prizeAreaHeight, scale, scale, rot);
    	batcher.draw(prizeEnd, pAX+prizeAreaWidth-1, pAY,  -pAX-prizeAreaWidth+originX, -pAY+originY, prizeEndWidth, prizeAreaHeight,scale,scale,rot);
    	
    	batcher.draw(nameArea, nAX, nAY, -nAX+originX, -nAY+originY, nameAreaWidth, nameAreaHeight,scale,scale,rot);
    	batcher.draw(nameEnd, nAX+nameAreaWidth-1, nAY, -nAX-nameAreaWidth+originX, -nAY+originY, nameEndWidth, nameAreaHeight,scale,scale,rot);
    	
    	batcher.draw(pibArea, X, Y, pibAreaWidth/2, pibAreaHeight/2, pibAreaWidth, pibAreaHeight,scale,scale,rot);
    	
    	if(pib.allPrizesAcquired()) batcher.setColor(c);
    	
    	drawPrizes(batcher, X+pibAreaWidth, pAY);
    	
    	//drawName(batcher, (pibAreaWidth/2+nameMargin)*scale+originX, (center(nAY, 5, nameAreaHeight)-originY)*scale+originY);

    	drawTextCenteredY(batcher, pib.getType(), (pibAreaWidth/2+nameMargin)*scale+originX, nAY , nameAreaHeight*scale, AssetLoader.whiteFont, nameFactor*scale );
    	
    	
    	float pibHeight = pibAreaHeight*0.8f;
    	float pibWidth = pibHeight*pib.getWidth()/pib.getHeight();
    	
    	//Esto lo tendria que hacer el pib
    	batcher.draw(pib.getUpSprite(),center(X, pibWidth, pibAreaWidth), center(Y,pibHeight,pibAreaHeight), pibWidth/2, pibHeight/2, pibWidth, pibHeight, scale, scale, rot);	
		
	}
	


	
	public void drawPrizes(Batch batcher, float X, float Y) {
		
		float margin = nameMargin;	
		
		float height1=(prizeAreaHeight-2*margin);
		float height2 = (prizeAreaWidth/prizes.size());
		
		float slotHeight = height1>height2?height2:height1;
		
		for (Prize p : prizes) {
			float slotX =  X+margin+ (prizes.indexOf(p)*(slotHeight+margin));
			float slotY = center(Y, slotHeight, prizeAreaHeight);
			
			batcher.draw(prizeSlot,slotX, slotY, -slotX+originX, -slotY+originY, slotHeight, slotHeight, scale, scale, rot);
			
			if (p.gotIt()) {
				
				//Esta función es general de los holders y permite dibujar lo que sea de forma centrada en un "slot", es decir contenedor aleatorio
				drawInSlot(batcher, p.getSprite(), slotX, slotY, -slotX+originX, -slotY+originY, slotHeight, slotHeight);
				
			}
		}
		
	
	}
	
	/*public void drawPrizeInSlot(Batch batcher, Prize p, float X, float Y, float slotWidth, float slotHeight) {
		

		float pWidth = p.getSprite().getRegionWidth();
		float pHeight = p.getSprite().getRegionHeight();
		
		batcher.draw(p.getSprite(), center(X,slotHeight*pWidth/pHeight, slotHeight),Y, slotHeight*pWidth/pHeight, slotHeight); //center(X, slotHeight*pWidth/pHeight, slotHeight), center(Y, pHeight, slotHeight)
		
	}*/
	
	
	
	public boolean isTouchDown(int screenX, int screenY) {

		return button.isTouchDown(screenX, screenY);
	}
	

    public boolean isTouchUp(int screenX, int screenY) {

    	return button.isTouchUp(screenX, screenY);
    }
    
    
    public void getBigger() {
    	scale *= 1.2f;
    }
    
    public void getSmaller() {
    	scale *= 1/1.2f;
    }
    

    public boolean isPressed() {
    	return button.isPressed();
    }
    
    
    public void setScale(float s) {
    	scale = s;
    }
    
    public float getScale() {
    	return scale;
    }
    
    
}
