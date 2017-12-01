package com.FoscusGames.fpHandlers;

import com.FoscusGames.gameobjects.Bird;
import com.FoscusGames.gameobjects.CatchObject;
import com.FoscusGames.gameobjects.CollisionObject;
import com.FoscusGames.gameobjects.Laser;
import com.FoscusGames.gameobjects.ScrollHandler;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class CollisionHandler {
//	
//	
//	
	
	public static boolean collides(CollisionObject a, CollisionObject b) {
		
		if(!a.isMaterial() || !b.isMaterial())
			return false;

				return (Intersector.overlaps(a.getBoundingBox(), b.getBoundingBox()));
			
	}
//	
//	
	public static boolean collides (Bird bird, ScrollHandler scroller) {
		
		if(!bird.isMaterial())
			return false;

		return scroller.collides(bird);
		
		
	}
	
	public static boolean collides (Laser laser, ScrollHandler scroller) {
		

		return scroller.collides(laser);
		
		
	}
	
	public static boolean collides (Bird bird, Rectangle ground) {

		return Intersector.overlaps(bird.getBoundingCircle(), ground);
	
	}
	
	public static boolean collides (CatchObject obj, Bird bird) {
		
		return Intersector.overlaps(bird.getBoundingCircle(), obj.getBoundingBox());
	}
//	
//	
//	
//	public boolean collides(Bird bird, Pipe pipe) {
//		
//		if(!bird.isMaterial())
//			return false;
//
//
//		if (pipe.position.x < bird.getX() + bird.getWidth()) {
//			
//			if (pipe.isUpPipe) {
//				return (Intersector.overlaps(bird.getBoundingCircle(), pipe.barUp) || Intersector.overlaps(bird.getBoundingCircle(), pipe.skullUp));
//			}
//			
//			if (pipe.isDownPipe) {
//				return (Intersector.overlaps(bird.getBoundingCircle(), pipe.barDown) || Intersector.overlaps(bird.getBoundingCircle(), pipe.skullDown));
//			}
//			 
//		}
//		
//		return false;
//	}
//	
//	public boolean collides(Laser laser) {
//		
//		
//		
//		if (pipe.position.x < laser.getBoundingBox().getX() + laser.getBoundingBox().getWidth()) {
//			
//			if ((Intersector.overlaps(laser.getBoundingBox(), pipe.barUp) || Intersector.overlaps(laser.getBoundingBox(), pipe.skullUp) ) && isUpPipe)
//			{
//				isUpPipe = false;
//				return true;
//			}
//			else if ((Intersector.overlaps(laser.getBoundingBox(), pipe.barDown) || Intersector.overlaps(laser.getBoundingBox(), pipe.skullDown)) && isDownPipe)
//			{
//				isDownPipe = false;
//				return true;
//			}
//			
//		}
//		
//		return false;
//	}

}
