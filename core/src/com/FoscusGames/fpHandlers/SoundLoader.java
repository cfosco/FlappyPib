package com.FoscusGames.fpHandlers;

import java.util.ArrayList;
import java.util.List;

import com.FoscusGames.sound.PibSoundManager;
import com.FoscusGames.sound.PowerSoundManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundLoader {
	
		public static Sound dead, flap, coin, buttonPress; //, notaroDeathSound, notaroJumpSound, notaroScoreSound, athaDeathSound, athaJumpSound, athaScoreSound, ezeDeathSound, ezeJumpSound, ezeScoreSound, tomiDeathSound, tomiJumpSound, tomiScoreSound; 
		public static Music menuMusic;	
		public static PibSoundManager notaroSoundMgr, ezeSoundMgr, tomiSoundMgr, athaSoundMgr, fedeSoundMgr, reimonSoundMgr, danSoundMgr, santiSoundMgr, carloSoundMgr;
		public static PowerSoundManager notaroPowerSoundMgr, ezePowerSoundMgr, tomiPowerSoundMgr, athaPowerSoundMgr, fedePowerSoundMgr, reimonPowerSoundMgr, danPowerSoundMgr, santiPowerSoundMgr, carloPowerSoundMgr;
	
		// POWER SOUNDS
		public static Sound shotExplosion, deepExplosion,longCarSound, motorRev, noiseSweep, coinShake,athaFired, athaYouFired,calma,coinDrop,zenBell,chirpSweep,sisisi,monedas,warMachine
							,reimonAgua2, fedeJump, fartLow, fartHigh, notaroAhu;
		public static List<Sound> reimonFoscoSounds,reimonFogSounds;
		public static List<Sound> ezeRunSounds;
		
		
		//POWER CATCHPHRASES
		public static Sound danMonedas,athaDespedido,santiAbrochense,carloCalma, reimonAgua1, fedeSeAcaboLaJoda, tomiMeCague, notaroCorsarios;
		public static List<Sound> ezeCatchphrases;
	
	public static void load() {
		
		loadMusic();
		loadPibSounds();
		loadPowerSounds();
		

	    dead = Gdx.audio.newSound(Gdx.files.internal("data/sounds/dead.wav"));
	    flap = Gdx.audio.newSound(Gdx.files.internal("data/sounds/flapAmplified.wav"));
	    coin = Gdx.audio.newSound(Gdx.files.internal("data/sounds/coin.wav"));
	    
	    buttonPress = Gdx.audio.newSound(Gdx.files.internal("data/sounds/ButtonPress2.wav"));

	}
	
	private static void loadPowerSounds() { //DISPOSE EVERYTHING YOU LOAD

	    shotExplosion = Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/shotgunShort.wav"));
		deepExplosion = Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/depthCharge.mp3"));
	    longCarSound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/carSound5,5sAmplified.wav"));	
		motorRev = Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/motorRevPitchUp.wav"));			
		noiseSweep = Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/upSweep0,35s.wav"));
		chirpSweep = Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/chirp0,35s.wav"));
	    coinShake = Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/coinJump.wav"));
	    athaFired = Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/athaFired.wav"));
	    athaYouFired = Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/athaYoureFired.wav"));
	    calma = Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/carloCalmaPowerJump.wav"));
	    coinDrop = Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/coinDropLoud.wav"));
	    zenBell = Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/zenBell10s.wav"));
	    sisisi =Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/danSisisi.wav"));
	    monedas =Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/danMonedas.wav"));
	    warMachine = Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/warMachine.wav"));
	   	fartLow = Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/fartLow.wav")) ;
	    fartHigh = Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/fartHigh.wav")) ;
	    notaroAhu  = Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/notaroAhu.wav")) ;

	    fedeJump = Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeJump.wav")) ;
	    
	    reimonFoscoSounds = new ArrayList<Sound>();
	    reimonFogSounds = new ArrayList<Sound>();
	    
	    reimonFogSounds.add(Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/reimonFosco1.wav")));
	    reimonFogSounds.add(Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/reimonFosco2.wav")));
	    reimonFogSounds.add(Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/reimonFosco3.wav")));
	    reimonFoscoSounds.add(Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/reimonFoscoClean1.wav")));
	    reimonFoscoSounds.add(Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/reimonFoscoClean2.wav")));
       
	    reimonAgua2 = Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/reimonAgua2.wav"));

	    ezeRunSounds = new ArrayList<Sound>();
	    
	    ezeRunSounds.add(Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/ezePewPew8s.wav")));
	    
//		reimonAgua1
//		reimonAgua2
//		reimonFosco1
//		reimonFosco2
//		reimonFosco3
//		reimonFog1
//		reimonFog2
//		reimonFog3
	    
	    
		loadPowerCatchphrases();
		

		notaroPowerSoundMgr = new PowerSoundManager( notaroCorsarios,shotExplosion,notaroAhu,null);
		santiPowerSoundMgr = new PowerSoundManager(santiAbrochense, longCarSound, motorRev, warMachine);
		danPowerSoundMgr = new PowerSoundManager(danMonedas, coinDrop, coinShake, null);
		athaPowerSoundMgr = new PowerSoundManager(athaDespedido,athaYouFired,athaFired,null);
		carloPowerSoundMgr = new PowerSoundManager(carloCalma, zenBell, calma, null);  	
		reimonPowerSoundMgr = new PowerSoundManager(reimonAgua1, reimonAgua2, reimonAgua2, null);
		fedePowerSoundMgr = new PowerSoundManager(fedeSeAcaboLaJoda, shotExplosion, fedeJump, null);
		tomiPowerSoundMgr = new PowerSoundManager(tomiMeCague, fartLow, fartHigh, null);
		ezePowerSoundMgr = new PowerSoundManager(ezeCatchphrases, ezeRunSounds, null, null);
		
		
		
		carloPowerSoundMgr.setPresVolume(0f);
		carloPowerSoundMgr.setRunVolume(0f);
		
		santiPowerSoundMgr.setPresVolume(0f);
		santiPowerSoundMgr.setRunVolume(0f);
		
	}
	
	private static void loadPowerCatchphrases() {
		danMonedas = Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/danMonedas!.wav"));
	    //athaYouFired = Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/youFired.wav"));
	    athaDespedido = Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/athaEstaDespedidoAmplified.wav"));	//AUMENTAR VOL
	    santiAbrochense = Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/abrochenseShort.wav"));
	    carloCalma = Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/carloCalma.wav"));
	    reimonAgua1 = Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/reimonAgua1.wav"));
	    fedeSeAcaboLaJoda = Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/fedeSeAcaboLaJoda.wav"));
	    tomiMeCague =  Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/tomiMepareceQueMeCague.wav"));
	    notaroCorsarios = Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/notaroCorsarios.wav"));
	    
	    
	    ezeCatchphrases = new ArrayList<Sound>();
	    
	    ezeCatchphrases.add(Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/ezePewPewMotherfucker.wav")));
	    ezeCatchphrases.add(Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/ezeYoureGonnaDie.wav")));
	    ezeCatchphrases.add(Gdx.audio.newSound(Gdx.files.internal("data/sounds/powerSounds/ezeLaserPowerActivated.wav")));
	    

	}
	
	

	private static void loadMusic() {
		menuMusic = Gdx.audio.newMusic(Gdx.files.internal("data/sounds/HobbitsToIsengard.mp3"));
		menuMusic.setLooping(true);
		menuMusic.setVolume(FPConstants.musicVolume);
	}
	
	private static void loadPibSounds() {
		
		List<Sound> fedeJumpSounds = new ArrayList<Sound>();
	    List<Sound> fedeDeathSounds = new ArrayList<Sound>();
	    List<Sound> fedeScoreSounds = new ArrayList<Sound>();
	    List<Sound> fedeChooseSounds = new ArrayList<Sound>();

	    fedeDeathSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeDeath.wav")) );	
	    fedeDeathSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeDeath2.wav")) );	
	    fedeDeathSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeDeath3.wav")) );	
	    fedeDeathSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeDeath4.wav")) );	
	    fedeDeathSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeDeath5.wav")) );	
	    fedeDeathSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeDeath6.wav")) );	
	    fedeDeathSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeDeath7.wav")) );	
	    fedeDeathSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeDeath8.wav")) );	
	    fedeDeathSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeDeath9.wav")) );	
	    fedeJumpSounds.add( fedeJump);
	    fedeJumpSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeJump5.wav")) );	
	    fedeJumpSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeJump2.wav")) );
	    fedeJumpSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeJump3.wav")) );
	    fedeJumpSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeJump4.wav")) );
	    fedeJumpSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeJumpLinked1.wav")) );
	    fedeJumpSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeJumpLinked2.wav")) );
	    fedeJumpSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeJumpLinked3.wav")) );
	    fedeJumpSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeJumpLinked4.wav")) );
	    fedeJumpSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeJumpLinked5.wav")) );
	    fedeJumpSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeJumpLinked6.wav")) );
	    fedeScoreSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeScores.wav")) );
	    fedeScoreSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeScores2.wav")) );
	    fedeScoreSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeScores3.wav")) );
	    fedeScoreSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeScores4.wav")) );
	    fedeScoreSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeScores5.wav")) );
	    fedeScoreSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeScores6.wav")) );
	    fedeScoreSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeScores7.wav")) );
	    fedeScoreSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeScores8.wav")) );
	    fedeScoreSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeScores9.wav")) );
	    fedeChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeChoose.wav")) );
	    fedeChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeChoose2.wav")) );
	    fedeChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeChoose3.wav")) );
	    fedeChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeChoose4.wav")) );
	    fedeChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/FedeChoose5.wav")) );
	    fedeSoundMgr = new PibSoundManager(fedeJumpSounds, fedeDeathSounds, fedeScoreSounds, fedeChooseSounds);
	    
	    fedeSoundMgr.enableSpecialJumpSounds();
	    fedeSoundMgr.setScoreSoundState(PibSoundManager.CHOSEN);
		
		
		
		List<Sound> reimonJumpSounds = new ArrayList<Sound>();
	    List<Sound> reimonDeathSounds = new ArrayList<Sound>();
	    List<Sound> reimonScoreSounds = new ArrayList<Sound>();
	    List<Sound> reimonChooseSounds = new ArrayList<Sound>();

	    reimonDeathSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/ReimonDeath.wav")) );	
	    reimonJumpSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/ReimonJump.wav")) );	
	    reimonScoreSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/ReimonScores.wav")) );
	    reimonChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/ReimonChoose.wav")) );
	    reimonChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/ReimonChoose2.wav")) );
	    reimonSoundMgr = new PibSoundManager(reimonJumpSounds, reimonDeathSounds, reimonScoreSounds, reimonChooseSounds);
		
		
		
		List<Sound> carloJumpSounds = new ArrayList<Sound>();
	    List<Sound> carloDeathSounds = new ArrayList<Sound>();
	    List<Sound> carloScoreSounds = new ArrayList<Sound>();
	    List<Sound> carloChooseSounds = new ArrayList<Sound>();

	    carloDeathSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/CarloDeath.wav")) );
	    carloDeathSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/CarloDeath2.wav")) );		
	    carloJumpSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/CarloJump.wav")) );	
	    carloJumpSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/CarloJump2.wav")) );	
	    carloScoreSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/CarloScores.wav")) );
	    carloChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/CarloChoose.wav")) );
	    carloChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/CarloChoose2.wav")) );
	    carloChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/CarloChoose3.wav")) );
	    
	    carloSoundMgr = new PibSoundManager(carloJumpSounds, carloDeathSounds, carloScoreSounds, carloChooseSounds);
		carloSoundMgr.enableLinkedJumpSounds();
		
		
		
		
		List<Sound> santiJumpSounds = new ArrayList<Sound>();
	    List<Sound> santiDeathSounds = new ArrayList<Sound>();
	    List<Sound> santiScoreSounds = new ArrayList<Sound>();
	    List<Sound> santiChooseSounds = new ArrayList<Sound>();

	    santiDeathSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/SantiDeath.wav")) );	
	    santiJumpSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/SantiJump.wav")) );	
	    santiJumpSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/SantiJump2Shorter.wav")) );
	    santiJumpSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/SantiJump3.wav")) );		
	    santiScoreSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/SantiScores.wav")) );
	    santiScoreSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/SantiScores2.wav")) );
	    santiChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/SantiChoose.wav")) );
	    santiChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/SantiChoose2.wav")) );
	    santiChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/SantiChoose3.wav")) );
	    santiChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/SantiChoose4.wav")) );
	    santiChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/SantiChoose5.wav")) );
	    
	    santiSoundMgr = new PibSoundManager(santiJumpSounds, santiDeathSounds, santiScoreSounds, santiChooseSounds);
		santiSoundMgr.enableLinkedJumpSounds();
		santiSoundMgr.enableLinkedScoreSoundsWithProbability(0.1f);
		

	    
	    List<Sound> danJumpSounds = new ArrayList<Sound>();
	    List<Sound> danDeathSounds = new ArrayList<Sound>();
	    List<Sound> danScoreSounds = new ArrayList<Sound>();
	    List<Sound> danChooseSounds = new ArrayList<Sound>();

	    danDeathSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/DanDeath.wav")) );	
	    danJumpSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/DanJump.wav")) );	
	    danScoreSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/DanScores.wav")) );
	    danChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/DanChoose.wav")) );
	    danChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/DanChoose2.wav")) );
	    danChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/DanChoose3.wav")) );
	    
	    danSoundMgr = new PibSoundManager(danJumpSounds, danDeathSounds, danScoreSounds, danChooseSounds);
		
		
	    
	    List<Sound> notaroJumpSounds = new ArrayList<Sound>();
	    List<Sound> notaroDeathSounds = new ArrayList<Sound>();
	    List<Sound> notaroScoreSounds = new ArrayList<Sound>();
	    List<Sound> notaroChooseSounds = new ArrayList<Sound>();

	    notaroDeathSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/NotaroDeath.wav")) );	
	    notaroJumpSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/NotaroJump.wav")) );	
	   // notaroJumpSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/NotaroJump2.wav")) );	
	    notaroScoreSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/NotaroScores2.wav")) );
	    notaroChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/NotaroChoose.wav")) );
	    
	    notaroSoundMgr = new PibSoundManager(notaroJumpSounds, notaroDeathSounds, notaroScoreSounds, notaroChooseSounds);
	    
	    List<Sound> athaJumpSounds = new ArrayList<Sound>();
	    List<Sound> athaDeathSounds = new ArrayList<Sound>();
	    List<Sound> athaScoreSounds = new ArrayList<Sound>();
	    List<Sound> athaChooseSounds = new ArrayList<Sound>();
	    
	    athaDeathSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/AthaDeath.mp3")) );
	    athaDeathSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/AthaDeath2.wav")) );
	    athaJumpSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/AthaJump.wav")) );
	    //athaJumpSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/AthaJump2.wav")) );
	    athaScoreSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/AthaScores.wav")) );	
	    athaChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/AthaChoose.wav")) );	
	    athaChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/AthaChoose2.wav")) );		
	    athaChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/AthaChoose3.wav")) );

	    
	    athaSoundMgr = new PibSoundManager(athaJumpSounds, athaDeathSounds, athaScoreSounds, athaChooseSounds);
	    
	    List<Sound> ezeJumpSounds = new ArrayList<Sound>();
	    List<Sound> ezeDeathSounds = new ArrayList<Sound>();
	    List<Sound> ezeScoreSounds = new ArrayList<Sound>();
	    List<Sound> ezeChooseSounds = new ArrayList<Sound>();

	    ezeDeathSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/EzeDeath.wav")) );	
	    ezeJumpSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/EzeJump.wav")) );
	    ezeScoreSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/EzeScores.wav")) );		
	    ezeChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/EzeChoose.wav")) );	
	    ezeChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/EzeChoose2.wav")) );		
	    ezeChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/EzeChoose3.wav")) );		
	    ezeChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/EzeChoose4.wav")) );		
	    ezeChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/EzeChoose5.wav")) );		
	    ezeChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/EzeChoose6.wav")) );		
	    ezeChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/EzeChoose7.wav")) );		
	    ezeChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/EzeChoose8.wav")) );		
	    ezeChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/EzeChoose9.wav")) );		
	    ezeChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/EzeChoose10.wav")) );		
	    ezeChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/EzeChoose11.wav")) );		
	    ezeChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/EzeChoose12.wav")) );		
	    ezeChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/EzeChoose13.wav")) );			

	    
	    ezeSoundMgr = new PibSoundManager(ezeJumpSounds, ezeDeathSounds, ezeScoreSounds, ezeChooseSounds);

	    
	    List<Sound> tomiJumpSounds = new ArrayList<Sound>();
	    List<Sound> tomiDeathSounds = new ArrayList<Sound>();
	    List<Sound> tomiScoreSounds = new ArrayList<Sound>();
	    List<Sound> tomiChooseSounds = new ArrayList<Sound>();

	    tomiDeathSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/TomiDeath.wav")) );		
	    //tomiJumpSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/TomiJump.wav")) );
	    tomiJumpSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/TomiJump2.wav")) );
	    tomiScoreSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/TomiScores.wav")) );	
	    tomiChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/TomiChoose.wav")) );	
	    tomiChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/TomiChoose2.wav")) );	
	    tomiChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/TomiChoose3.wav")) );	
	    tomiChooseSounds.add( Gdx.audio.newSound(Gdx.files.internal("data/sounds/TomiChoose4.wav")) );		

	    
	    tomiSoundMgr = new PibSoundManager(tomiJumpSounds, tomiDeathSounds, tomiScoreSounds, tomiChooseSounds);

    
	}
	
	public static void disposeSounds() {

        dead.dispose();
        flap.dispose();
        coin.dispose();
        buttonPress.dispose();
        
        
        disposePibSounds();
	    
    	
    }
	
	public static void disposePibSounds() {


	   notaroSoundMgr.dispose();
	   ezeSoundMgr.dispose();
	   tomiSoundMgr.dispose();
	   athaSoundMgr.dispose();
	   danSoundMgr.dispose();
	   santiSoundMgr.dispose();
	    
    	
    }
    
	public static void disposePowerSounds(){
	    shotExplosion.dispose();
		deepExplosion.dispose();
	    longCarSound.dispose();
		motorRev.dispose();
		noiseSweep.dispose();
	    coinShake.dispose();
	    athaFired.dispose();
	    athaYouFired.dispose();
	    calma.dispose();
	    coinDrop.dispose();
	    zenBell.dispose();
	    sisisi.dispose();
	    monedas.dispose();
	    warMachine.dispose();
	    reimonAgua2.dispose();
	   	fartLow.dispose();
	    fartHigh.dispose();
	    

	    for (Sound s : reimonFoscoSounds) {
	    	s.dispose();
	    }
	    for (Sound s : reimonFogSounds) {
	    	s.dispose();
	    }
//		notaroPowerSoundMgr.dispose();
//		santiPowerSoundMgr.dispose();
//		danPowerSoundMgr.dispose();
//		athaPowerSoundMgr.dispose();
		
		
		disposePowerCatchphrases();
	}
	
	public static void disposePowerCatchphrases() {

		danMonedas.dispose();
	    athaDespedido.dispose();
	    santiAbrochense.dispose();
	    carloCalma.dispose();
	    reimonAgua1.dispose();
	    fedeSeAcaboLaJoda.dispose();
	    tomiMeCague.dispose();
	}
	

	public static void disposeMusic() {
    	menuMusic.dispose();
    }

	public static void dispose() {
		disposeSounds();
		disposeMusic();
		disposePowerSounds();
		
	}
    
	
	

}
