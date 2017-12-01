package com.FoscusGames.gameobjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.FoscusGames.fpHandlers.FPConstants;
import com.FoscusGames.fpHandlers.PibSpriteManager;
import com.FoscusGames.gamemechanics.Power;
import com.FoscusGames.gameprizes.PrizeHandler;
import com.FoscusGames.sound.PibSoundManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class Bird extends CollisionObject{
	
	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;
	
	private float opacity;
	private float rotation;
	private int width;
	private int height;
	private boolean click;
	private boolean jumping;
	//private double jumpAnimTime;
	private boolean birdRotates;
	private int jumpHeight;
	private float startX;
	
	private float ON_GND_SEP_CST = 5;
	
	private float originalY;
	
	private boolean isAlive,hidden;
	private boolean isMaterial;
	
	private Power power;
	
	private List<Prize> prizes;

	private String type;
	//private TextureRegion downSprite, upSprite, deadSprite, normalDeadSprite, specialDeadSprite;
	//private Animation animation;
	
	private PibSoundManager soundMgr;
	private PibSpriteManager spriteMgr;
	
	private Circle boundingCircle;
	
	private boolean jumpFromGround;
	private boolean frozen;
	
	
	 
	 public Bird(float x, float y, String type, PibSpriteManager spriteM, PibSoundManager soundM, Power p) {
		 
	        this.width = FPConstants.pibWidth;
	        this.height = FPConstants.pibHeight;
	        position = new Vector2(x,y);
	        startX = x;
	        velocity = new Vector2(0, 0);
	        acceleration = new Vector2(0, 460);
	        //jumpAnimTime = 0.22;
	        birdRotates = false;
	        jumpHeight = 130;
	        this.type = type;
	        spriteMgr = spriteM;
	        soundMgr = soundM;
	        power = p;
	        
	        opacity = 1.0f;
	        
//	        TextureRegion[] birds = {downSprite, upSprite};
//	        animation = new Animation(0.3f, birds);
//	        animation.setPlayMode(Animation.PlayMode.NORMAL);
//	        
	        
	        originalY =y;
	        
	        isAlive = true;
	        isMaterial = true;
	        jumpFromGround = false;
	        frozen = false;
	        
	        boundingCircle = new Circle();
	        
	        
	        prizes = new ArrayList<Prize>();
	        
	        PrizeHandler.generatePrizes(this);
	       
	        
	    }
	 
	 public void update(float delta) {

	        velocity.add(acceleration.cpy().scl(delta));

	        if (velocity.y > 400) {
	            velocity.y = 400;
	        }
	        
	        if (position.y < -13) {
	        	position.y = -13;
	        	velocity.y = 0;
	        }
	        
	        if(velocity.y >= 0) {
	        	jumping=false;
	        }
	
	        position.add(velocity.cpy().scl(delta));
	        
	        
	        if(jumpFromGround) {

		    	if (position.y+height>= FPConstants.playableHeight+ON_GND_SEP_CST) {
		    		position.y=  FPConstants.playableHeight+ON_GND_SEP_CST-height;
		    	}
	        }

	        
	        boundingCircle.set(position.x + width/2, position.y + height/2, width/2);
	        
	        if ( !isAlive) {
	        	
        		rotation += velocity.y * delta;
                if (rotation > 180) {
                    rotation = 180;
                }

        	}
	        
	       
	        
	        if(birdRotates) {
	        	if (velocity.y <0) {
	        		rotation -=400*delta;
	        
	        
	        		if (rotation < -20) {
	        			rotation = -20;
	        		}
	        	}
	        
	        	if (isFalling() || !isAlive) {
	        	
	        		rotation += 280 * delta;
	                if (rotation > 90) {
	                    rotation = 90;
	                }

	        	}
	 		}
	        
	        
	        power.update();
	        
	 }
	 
	 public void rotate(float r) {
		 rotation += r;
	 }
	 
	 public void resetRotation() {
		 rotation = 0;
	 }
	 
	 
	 	public boolean isFalling() {
		    return velocity.y > 110;
		}

	 
	 	public boolean clicked() {
	 		return click;
	 	}
	 	
	 	
	 	public boolean isJumping() {
	 		return jumping;
	 	}
	 	
//	 	public void noLongerJumping() {
//	 		jumping = false;
//	 	}
//	 	
	 	public void resetClick() {
	 		click = false;
	 	}

	    public void onClick(float screenX, float screenY) {
	    	
	    	
	    	if(isAlive() && !isFrozen()) {
	    		if (power.isJumpSoundAllowed())soundMgr.playJumpSound();
	    		else power.playPowerJumpSound();
	    		click = true;
	    		jumping = true;
	    		
	    		if(jumpFromGround) {
	    			if (onGround()) velocity.y = -2*jumpHeight;

	    		}
	    		
	    		else {
	    			velocity.y = -jumpHeight;
	    		}
	    		
		    	power.onClick(screenX, screenY);

	    	}
	    	
	    }
	    
	    public boolean onGround() {
	    	
	    	if (position.y+height>= FPConstants.playableHeight+ON_GND_SEP_CST) {
	    		return true;
	    	}
	    	return false;
	    }
	    
	    public void playScoreSound() {
	    	if (power.isScoreSoundAllowed())soundMgr.playScoreSound();
	    }
	    
	    public void playChooseSound() {
	    	soundMgr.playRandomChooseSound();
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

	    public float getRotation() {
	        return rotation;
	    }

//		public double getJumpAnimationTime() {
//			return jumpAnimTime;
//		}

		public Circle getBoundingCircle() {
			return boundingCircle;
		}

		public boolean isAlive() {
			return isAlive;
		}
		
		public void die() {
			
			soundMgr.playRandomDeathSound();
			isAlive = false;
			velocity.y = 0;
			
			power.setUnavailable();
			if(power.isInUse()) power.interrupt();
			//spriteMgr.randomizeDeadSprite();
		}
		
		public void decelerate() {
			acceleration.y = 0;
		}

		public void onRestart(int y) {
			rotation = 0;
			position.y = y;
			velocity.x = 0;
			velocity.y = 0;
			acceleration.x = 0;
			acceleration.y = 460;
			isAlive = true;
			spriteMgr.randomizeAll();
			power.reset();
			
		}

		public void updateReady(float runTime) {
			position.y = 2 * (float) Math.sin(7 * runTime) + originalY;
			
		}
		
		public String getType() {
			return type;
		}

		public void addPrizes(List<Prize> prizes) {
			this.prizes = prizes;
			
		}
		
		public List<Prize> getPrizes() {
			return prizes;
			
		}

		public TextureRegion getDownSprite() {
			return spriteMgr.getDownSprite();
		}
		
		public TextureRegion getUpSprite() {
			return spriteMgr.getUpSprite();
		}
		
		public TextureRegion getDeadSprite() {
			return spriteMgr.getDeadSprite();
		}
		
		public Animation getAnimation() {
			return spriteMgr.getAnimation();
		}
		
		
		public boolean isMaterial() {
			return isMaterial;
		}
		
		public void materialize(){
			isMaterial=true;
		}
		
		public void unMaterialize() {
			isMaterial=false;
		}
		
		public void changeOpacity(float newValue) {
			opacity = newValue;
		}
		
		public float getOpacity() {
			return opacity;
		}
		
		public float getOriginY () {
			return height/2.0f;
		}
		
		public void setX(float x) {
			this.position.x = x;
		}
		
		public void resetX() {
			this.position.x = startX;
		}
		
		public float getStartX() {
			return startX;
		}
		
		public void usePower() {
			
			
				if (power.isAvailable()) {
					power.use();
				}
		}
		
		
		public void drawPower(Batch batcher) {
			power.draw(batcher);
		}
		
		public void drawPowerBg(Batch batcher) {
			power.drawBg(batcher);
		}
		
		
		public void draw(Batch batcher) {		    	

		    if(!hidden) {
		        if(this.isJumping()) {
//		        	
//		        	if(startOfJump ==0) {
//		        		startOfJump = runTime;
//		        	}

		        /*	batcher.draw(this.getAnimation().getKeyFrame(runTime),this.getX(), this.getY(), 
		        			this.getWidth()/2.0f, this.getHeight()/2.0f,
		        			this.getWidth(), this.getHeight(), 1, 1, this.getRotation());
		        	
		        	*/
		        	
		        	Color color = batcher.getColor();
		        	float oldAlpha = color.a;
		        	color.a = this.getOpacity();
		        	batcher.setColor(color);
		        	
		        	batcher.draw(this.getUpSprite(),this.getX(), this.getY(), 
		        			this.getWidth()/2.0f, this.getHeight()/2.0f,
		        			this.getWidth(), this.getHeight(), 1, 1, this.getRotation());
		        	
		        	color.a = oldAlpha;
		        	batcher.setColor(color);
		        	
		        	
//		        	if(runTime-startOfJump > this.getJumpAnimationTime()) {
//		        		this.noLongerJumping();
//		        		startOfJump =0;
//		        	}
		        	
		        	
		        	
		        }
		        else if (!this.isAlive()) {
		        	batcher.draw(this.getDeadSprite(),this.getX(), this.getY(), this.getWidth()/2.0f, this.getHeight()/2.0f,this.getWidth(), this.getHeight(), 1, 1, this.getRotation());
		        	
		        	
		        }
		        else{
		        	

		        	Color color = batcher.getColor();
		        	float oldAlpha = color.a;
		        	color.a = this.getOpacity();
		        	batcher.setColor(color);
		        	
		        	batcher.draw(this.getDownSprite(),this.getX(), this.getY(),this.getWidth()/2.0f, this.getHeight()/2.0f,this.getWidth(), this.getHeight(), 1, 1, this.getRotation());
		        	
		        	
		        	color.a = oldAlpha;
		        	batcher.setColor(color);
		        	
		        }
		        

		    }     
		        this.drawPower(batcher);
		   
			
		}
		
		public void freeze() {
			velocity.setZero();
			acceleration.setZero();
			frozen = true;
		}
		
		public void unFreeze() {
			
			frozen = false;
			acceleration.set(0, 460);
			
		}
		
		public boolean isFrozen() {
			return frozen;
		}

		public void setRotation(float r) {
			rotation = r;
			
		}
		
		public void activatePowerFace(TextureRegion face) {
			spriteMgr.activatePowerFace(face);
		}
		
		public void deactivatePowerFace() {
			spriteMgr.deactivatePowerFace();
		}
		
		public void hide() {
			hidden=true;
		}
		
		public void unhide() {
			hidden=false;
		}

		public Power getPower() {
			return power;
		}

		public void setJumpFromGround(boolean b) {
			jumpFromGround = b;
			
		}

		public boolean allPrizesAcquired() {
			boolean b = true;
			for (Prize p:prizes) {
				if(!p.gotIt()) {
					b = false;
				}
			}
			return b;
		}

		public void setStaticY(float f) {
			// TODO Auto-generated method stub
			
		}

		public void prepareSounds() {
			soundMgr.prepareSounds();
			
		}
		
		
		

}
