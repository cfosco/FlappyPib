package com.FoscusGames.fpHandlers;

public class ScoreManager {
	
	private static int score;
	
	public static void setScore(int s) {
		score = s;
	}
	
	public static int getScore() {
		return score;
	}
	
	public static void addScore(int inc) {
		score +=inc;
	}

}
