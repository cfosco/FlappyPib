package com.FoscusGames.sound;

import java.util.List;
import java.util.Random;

import com.FoscusGames.fpHandlers.FPConstants;
import com.FoscusGames.fpHandlers.SoundLoader;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class PowerSoundManager {
	
	Sound  runSound, pibSound, auxSound;
	Sound catchphrase;
	
	boolean scoreSoundOn, jumpSoundOn, listType;
	float presVolume,runVolume, prevVolume;
	
	List<Sound> auxSounds, runSounds, pibSounds;
	List<Sound> catchphrases;

	Random r;
	
	public PowerSoundManager(Sound catchphrase, Sound runSound, Sound pibSound, Sound auxiliarySound) {
		
		this.catchphrase = catchphrase;
		this.auxSound = auxiliarySound;
		this.runSound = runSound;
		this.pibSound = pibSound;
		
		scoreSoundOn = false;
		jumpSoundOn = false;
		
		presVolume = FPConstants.musicVolume*0.3f;
		runVolume = FPConstants.musicVolume*0.6f;
		prevVolume = FPConstants.musicVolume*0.6f;
		
		listType = false;
	}
	

	public PowerSoundManager(List<Sound> catchphrase, List<Sound> runSound, List<Sound> pibSound, List<Sound> auxiliarySound) {
		
		if(catchphrase != null)this.catchphrase = catchphrase.get(0);
		if(auxiliarySound != null)this.auxSound = auxiliarySound.get(0);
		if(runSound != null)this.runSound = runSound.get(0);
		if(pibSound != null)this.pibSound = pibSound.get(0);
		
		scoreSoundOn = false;
		jumpSoundOn = false;
		
		this.catchphrases = catchphrase;
		this.auxSounds = auxiliarySound;
		this.runSounds = runSound;
		this.pibSounds = pibSound;
		
		presVolume = FPConstants.musicVolume*0.3f;
		runVolume = FPConstants.musicVolume*0.6f;
		prevVolume = FPConstants.musicVolume*0.6f;
		
		listType = true;
		
		r = new Random();
	}
	
	
	public Sound getAuxSound() {
		return auxSound;
	}

	public Sound getRunSound() {
		return runSound;
	}

	public Sound getPibSound() {
		return pibSound;
	}
	
	
//	public boolean isCatchphrase() {
//		return catchphrase.isPlaying();
//	}

	public void playCatchphrase() {
		if(FPConstants.isSoundOn) {
			if(listType) {
				
				playRandomCatchphrase();
			}
			
			else if(catchphrase!=null)catchphrase.play();
			}
		
		
		}
	

	public void playAuxSound() {
		if(FPConstants.isSoundOn) {
			
			if(listType) {
				
				if(auxSounds != null)playSound(auxSounds, r.nextInt(auxSounds.size()));
			}
			
			else if(auxSound!=null)auxSound.play();
			}
		}

	public void playPibSound() {
		if(FPConstants.isSoundOn) {
			
			if(listType) {
				
				if(pibSounds != null)playSound(pibSounds, r.nextInt(pibSounds.size()));
			}
			
			else if(pibSound!=null)pibSound.play();
			}
		}

	public void playRunSound() {
		if(FPConstants.isSoundOn) {
			
			if(listType) {
				
				if(runSounds != null)playSound(runSounds, r.nextInt(runSounds.size()));
			}
			
			else if(runSound!=null)runSound.play();
			}
		}

	
	public void setScoreSoundOn(boolean b) {
		scoreSoundOn = b;
	}
	
	public void setJumpSoundOn(boolean b) {
		jumpSoundOn = b;
	}

	public boolean isJumpSoundOn() {
		return jumpSoundOn;
	}
	
	public boolean isScoreSoundOn() {
		return scoreSoundOn;
	}


	public void setMusicVolume(float f) {
		
		SoundLoader.menuMusic.setVolume(f);
		
	}

	public void resetMusicVolume() {
		SoundLoader.menuMusic.setVolume(prevVolume);
	}
	
	
	
	public void setPresVolume(float f) {

		presVolume = f;
		
	}

	public void setRunVolume(float f) {

		runVolume = f;
		
	}


	public void saveCurrentVolume() {
		prevVolume = SoundLoader.menuMusic.getVolume();
		
	}


	public void applyPresVolume() {
		SoundLoader.menuMusic.setVolume(presVolume);
		
	}

	public void applyRunVolume() {
		SoundLoader.menuMusic.setVolume(runVolume);
		
	}



	public void playSweep() {
		Random r = new Random();
		if(r.nextFloat()<=0.99)SoundLoader.chirpSweep.play();
		else SoundLoader.noiseSweep.play();
		
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
	
	public void playRandomCatchphrase() {
		if(FPConstants.isSoundOn) {
			if(catchphrases != null) {
				if (catchphrases.size() != 0) {
		
					int i = r.nextInt(catchphrases.size());
					if(catchphrases.get(i) != null) catchphrases.get(i).play();
					
				}
			}
		}
	}
	


	
}
