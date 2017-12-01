package com.FoscusGames.fpHandlers;

import com.FoscusGames.gamemechanics.CoinCatchPower;
import com.FoscusGames.gamemechanics.DashPower;
import com.FoscusGames.gamemechanics.DashTapPower;
import com.FoscusGames.gamemechanics.JumpDashPower;
import com.FoscusGames.gamemechanics.LaserPower;
import com.FoscusGames.gamemechanics.MathCatchPower;
import com.FoscusGames.gamemechanics.PaperCatchPower;
import com.FoscusGames.gamemechanics.Power;
import com.FoscusGames.gamemechanics.PutasCatchPower;
import com.FoscusGames.gamemechanics.TapPower;
import com.FoscusGames.gameworld.GameWorld;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PowerLoader {
	
	public static Power athaPower, ezePower, danPower, carloPower;
	public static DashPower santiPower, fedePower, tomPower, reimonPower, notaroPower;
	
	public static void load(GameWorld world) {
		
		initPowers(world);
		setAnimations();
		

	}
	
	
	public static void initPowers(GameWorld world) {
		
		athaPower = new TapPower(null, world, 6, new Sprite(AssetLoader.athaFiredBg), "YOU\'RE FIRED!!", AssetLoader.athaFiredFace, AssetLoader.itemFired, new Color(245/255f,56/255f,19/255f,1f), SoundLoader.athaPowerSoundMgr);
		reimonPower = new DashTapPower(AssetLoader.dashAnimation, world, 5,new Sprite(AssetLoader.reimonAguaBg), 250, "AGUAAAAAAA!!!", AssetLoader.reimonAguaFace, new Color(162/255f,221/255f,255/255f,1f),SoundLoader.reimonPowerSoundMgr);
		ezePower = new LaserPower(null, world, 8, new Sprite(AssetLoader.ezeLaserBg),"PEW PEW PEW!!", AssetLoader.ezeLaserFace, new Color(255/255f,120/255f,120/255f,1f),SoundLoader.ezePowerSoundMgr);
		tomPower = new PaperCatchPower(AssetLoader.dashAnimation, world,6,new Sprite(AssetLoader.tomShitBg), 200, "UN CAGO EN EL LABURO!!",AssetLoader.tomiTeleFace, new Color(171/255f,131/255f,95/255f,1f),SoundLoader.tomiPowerSoundMgr);
		fedePower = new PutasCatchPower(AssetLoader.dashAnimation, world, 4,new Sprite(AssetLoader.fedePutasBg),300,"NOS VAMOS DE PUTAS!!", AssetLoader.fedeCascoFace, new Color(255/255f,172/255f,238/255f,1f),SoundLoader.fedePowerSoundMgr);
		carloPower = new MathCatchPower(null, world, 10,new Sprite(AssetLoader.carloCalmBg),"ABSOLUTE CALM.", AssetLoader.carloCalmFace, new Color(70/255f,255/255f,70/255f,1f),SoundLoader.carloPowerSoundMgr);
		notaroPower = new DashPower(AssetLoader.dashAnimation, world, 4.5f, new Sprite(AssetLoader.notaroCorsariosBg), 500, "CORSARIOS!!!", AssetLoader.notaroCorsariosFace, new Color(80/255f,120/255f,255/255f,1f),SoundLoader.notaroPowerSoundMgr);
		santiPower = new JumpDashPower(AssetLoader.camioAnimation, world, 5.5f,new Sprite(AssetLoader.santiCamioBg),400, "A LA CAMIONETA!!", AssetLoader.santiCamioFace, Color.WHITE,SoundLoader.santiPowerSoundMgr) ;
		danPower =  new CoinCatchPower(AssetLoader.ringAnimation, world, 13, new Sprite(AssetLoader.danJewBg), "MAS MONEDAS!!", AssetLoader.danJewFace, new Color(255/255f,250/255f,163/255f,1f),SoundLoader.danPowerSoundMgr);
		
		athaPower.setPresTime(1.4f);
		reimonPower.setPresTime(1.3f);
		//ezePower.setPresTime(1f);
		tomPower.setPresTime(1.2f);
		fedePower.setPresTime(1.3f);
		//carloPower.setPresTime(1f);
		notaroPower.setPresTime(1.7f);
		//santiPower.setPresTime(1f);
		danPower.setPresTime(1.1f);

		fedePower.enableSlowDeceleration();
		notaroPower.enableAlternateSprites(AssetLoader.notaroMeteoroBg, AssetLoader.notaroMeteoroFace, 0.05f);
	}
	
	public static void setAnimations() {
		
		float aguaAnimWidth = 2f*FPConstants.pibWidth;
		float aguaAnimHeight = aguaAnimWidth;
		float aguaAnimX = FPConstants.pibWidth-5;
		float aguaAnimY = -aguaAnimHeight+FPConstants.pibHeight*0.5f;
		
		reimonPower.setPresAnimation(AssetLoader.aguaAnimation, aguaAnimX, aguaAnimY, aguaAnimWidth, aguaAnimHeight);
		
		float circleAnimWidth = 2*FPConstants.gameWidth;
		float circleAnimHeight = circleAnimWidth;
		float circleAnimX = FPConstants.pibWidth/2-circleAnimWidth/2;
		float circleAnimY = FPConstants.pibHeight/2-circleAnimHeight/2;
		
		carloPower.setPresAnimation(AssetLoader.circleAnimation, circleAnimX, circleAnimY, circleAnimWidth, circleAnimHeight);
		fedePower.setPresAnimation(AssetLoader.circleAnimation, circleAnimX, circleAnimY, circleAnimWidth, circleAnimHeight);
		santiPower.setPresAnimation(AssetLoader.circleAnimation, circleAnimX, circleAnimY, circleAnimWidth, circleAnimHeight);
		tomPower.setPresAnimation(AssetLoader.circleAnimation, circleAnimX, circleAnimY, circleAnimWidth, circleAnimHeight);
		ezePower.setPresAnimation(AssetLoader.circleAnimation, circleAnimX, circleAnimY, circleAnimWidth, circleAnimHeight);
		notaroPower.setPresAnimation(AssetLoader.circleAnimation, circleAnimX, circleAnimY, circleAnimWidth, circleAnimHeight);
		danPower.setPresAnimation(AssetLoader.circleAnimation, circleAnimX, circleAnimY, circleAnimWidth, circleAnimHeight);
		athaPower.setPresAnimation(AssetLoader.circleAnimation, circleAnimX, circleAnimY, circleAnimWidth, circleAnimHeight);
		
	}
	

}
