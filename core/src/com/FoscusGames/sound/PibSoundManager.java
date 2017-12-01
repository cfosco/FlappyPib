package com.FoscusGames.sound;

import java.util.List;
import java.util.Random;

import com.FoscusGames.fpHandlers.FPConstants;
import com.FoscusGames.fpHandlers.SoundLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class PibSoundManager {

	public static final int LINKED = 0;
	public static final int FIRST = 1;
	public static final int RANDOM = 2;
	public static final int CHOSEN = 3;
	public static final int LINKED1 = 4;
	public static final int LINKED2 = 5;
	private List<Sound> jump;
	private List<Sound> death;
	private List<Sound> score;
	private List<Sound> choose;
	
	private Random r;
	
	private boolean linkedJumpSounds,  isLinkedScoreSoundsProb;
	private int currentJumpSoundIndex, currentScoreSoundIndex;
	private float linkedScoreSoundsProb;
	private int scoreSoundState, jumpSoundState, chosenScoreIdx, chosenJumpIdx;
	private boolean isSpecialJumpSounds;
	private float linkedJumpSoundsProb;
	
	
	public PibSoundManager(List<Sound> jump, List<Sound> death, List<Sound> score, List<Sound> choose) {
		this.jump = jump;
		this.death = death;
		this.score = score;
		this.choose = choose;
		
		r = new Random();
		
		currentJumpSoundIndex = 0;
		currentScoreSoundIndex = 0;
		
		chosenScoreIdx = 0;
		chosenJumpIdx = 0;
		
		
		linkedScoreSoundsProb=0.1f;
		isLinkedScoreSoundsProb = false;

		isSpecialJumpSounds = false;
		
		scoreSoundState = RANDOM;
		jumpSoundState = RANDOM;

		
	}
	
//	public void resetSounds() {
//		chosenScoreIdx = 0;
//		chosenJumpIdx = 0;
//	}
//	
	public void prepareSounds() {
		
		if(isLinkedScoreSoundsProb) {
			
			if(r.nextFloat()<= linkedScoreSoundsProb) {
				scoreSoundState = LINKED;
			}
			
			else {
				scoreSoundState = CHOSEN;
			}
		
		}
		
		if(isSpecialJumpSounds) {
			
			float roll = r.nextFloat();
			
			if(roll <= 0.3) {
				jumpSoundState = CHOSEN;
				Gdx.app.log("Pib Sound Mgr", "Chosen jump sound");
			}
			
			else if (roll <= 0.6f) {
				jumpSoundState = LINKED1;
				currentJumpSoundIndex = 5;
				Gdx.app.log("Pib Sound Mgr", "Linked1 jump sound");
			}
			
			else if (roll <= 0.9f) {
				jumpSoundState = LINKED2;
				currentJumpSoundIndex = 8;
				Gdx.app.log("Pib Sound Mgr", "Linked2 jump sound");
			}
			
			else {
				jumpSoundState = LINKED;
				Gdx.app.log("Pib Sound Mgr", "LinkedAll jump sound");
			}
		
		
		}
		
		if(scoreSoundState == CHOSEN) {
			
			chosenScoreIdx = r.nextInt(score.size());
		}
		
		if(jumpSoundState == CHOSEN) {
			
			chosenJumpIdx = r.nextInt(jump.size());
		}
		
	}
	
	
	public void playJumpSound() {
		
		switch(jumpSoundState) {
		
		case LINKED:
			playNextJumpSound();
			break;
			
		case LINKED1:
			playNextJumpSound1();
			break;
			
		case LINKED2:
			playNextJumpSound2();
			break;
			
		case CHOSEN:
			playChosenJumpSound();
			break;
		
		default:
			playRandomJumpSound();
			break;
		}
		
//		
//		if(linkedJumpSounds) playNextJumpSound();
//		else playRandomJumpSound();
	}
	
	
	public void playChosenJumpSound() {
		playJumpSound(chosenJumpIdx);
	}
	
	
	public void playNextJumpSound() {
		
		playJumpSound(currentJumpSoundIndex);
		
		currentJumpSoundIndex+=1;
		
		if(currentJumpSoundIndex>=jump.size()) {
			currentJumpSoundIndex =0;
		}
	}
	
	public void playNextJumpSound1() {
		
		playJumpSound(currentJumpSoundIndex);
		
		currentJumpSoundIndex+=1;
		
		if(currentJumpSoundIndex>=8) {
			currentJumpSoundIndex =5;
		}
	}
	
	public void playNextJumpSound2() {
		
		playJumpSound(currentJumpSoundIndex);
		
		currentJumpSoundIndex+=1;
		
		if(currentJumpSoundIndex>=jump.size()) {
			currentJumpSoundIndex =8;
		}
	}
	
	public void playScoreSound() {
		
		switch(scoreSoundState) {
		
		case LINKED:
			playNextScoreSound();
			break;
			
		case FIRST:
			playFirstScoreSound();
			break;
			
		case CHOSEN:
			playChosenScoreSound();
			break;
		
		default:
			playRandomScoreSound();
			break;
		}
		
		
	}
	
	
	public void playChosenScoreSound() {
		playScoreSound(chosenScoreIdx);
	}
	
	public void playFirstScoreSound() {
		
		playScoreSound(0);
		
	}
	
	public void playNextScoreSound() {
		
		playScoreSound(currentScoreSoundIndex);
		
		currentScoreSoundIndex+=1;
		
		if(currentScoreSoundIndex>=score.size()) {
			currentScoreSoundIndex =0;
		}
	}
	
	public void playNextSound(List<Sound> list, int currentSoundIndex) {
		
		playSound(list,currentSoundIndex);
		
		currentSoundIndex+=1;
		
		if(currentSoundIndex>=jump.size()) {
			currentSoundIndex =0;
		}
		
		
	}

	
	public void playJumpSound(int i) {
		
		playSound(jump, i);
		
	}
	
	public void playScoreSound(int i) {
		
		playSound(score, i);
		
	}
	
	public void playDeathSound(int i) {
	
		playSound(death, i);
	
	}
	
	public void playChooseSound(int i) {
		
		playSound(choose, i);
		
	}
	
	public void playRandomJumpSound() {		
		
		if(FPConstants.classicSound && FPConstants.isSoundOn) {
			SoundLoader.flap.play();
		}
		else {
		
		if(jump.size() != 0) 
			playJumpSound(r.nextInt(jump.size()));
		}
	}
	
	public void playRandomScoreSound() {
		
		if(FPConstants.classicSound && FPConstants.isSoundOn) {
			SoundLoader.coin.play();
		} else {
			
		if(score.size() != 0) 
			playScoreSound(r.nextInt(score.size()));
			
		}
	}		
	
	public void playRandomDeathSound() {
		
	
		if(FPConstants.isSoundOn) SoundLoader.dead.play();
		
		if(!FPConstants.classicSound) {
		
			if(death.size() != 0) 
				playDeathSound(r.nextInt(death.size()));
		}
		
	}

	
	public void playRandomChooseSound() {
		
		if(FPConstants.classicSound && FPConstants.isSoundOn) {
			SoundLoader.coin.play();
		} else {
		
		if(choose.size() != 0) 
			playChooseSound(r.nextInt(choose.size()));
	
		}
		
		
	}

	public void dispose() {
		for (Sound s : jump) {
			s.dispose();
		}
		for (Sound s : death) {
			s.dispose();
		}
		for (Sound s : score) {
			s.dispose();
		}
		for (Sound s : choose) {
			s.dispose();
		}
	}
	
	public void playSound(List<Sound> list, int i) {
		if(FPConstants.isSoundOn) {
			if(list != null) {
				if (list.size() != 0) {
					if (i >= list.size() || i<0) i = list.size()-1;
						if (list.get(i) != null) {
							list.get(i).play();
						}
				}
			}
		}
	}


	public void enableLinkedJumpSounds() {
		jumpSoundState = LINKED;
		
	}


	public void enableLinkedScoreSounds() {
		scoreSoundState = LINKED;
		
	}


	public void enableLinkedScoreSoundsWithProbability(float p) {

		isLinkedScoreSoundsProb = true;
		this.linkedScoreSoundsProb = p;
		
		
	}
	
	public void setScoreSoundState(int s) {
		scoreSoundState = s;
	}

	public void enableSpecialJumpSounds() {
		isSpecialJumpSounds = true;
	}
}
