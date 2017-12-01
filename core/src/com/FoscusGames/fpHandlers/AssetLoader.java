package com.FoscusGames.fpHandlers;

import java.util.ArrayList;
import java.util.List;

import com.FoscusGames.sound.PibSoundManager;
import com.FoscusGames.ui.PrizeHolderSkin;
import com.FoscusGames.ui.ScoreboardSkin;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
	

	//GENERAL ASSETS
	public static Texture titleTex, generalTexture, logoTexture, pibPrizeTextures, generalPrizeTextures, pibTextures, earthLikePlanetTex, redPlanetTex, greenPlanetTex,  buttonTextures, scoreboardTexture1, scoreboardTexture2, scoreboardTexture3;
	public static Texture athaSprites, tomiSprites, ezeSprites, notaroSprites, fedeSprites, reimonSprites, danSprites, carloSprites, santiSprites;
	public static Texture legendarySkinTexture, epicSkinTexture, rareSkinTexture,commonSkinTexture, pibHolderSkinBR;
	public static TextureRegion bg, ground;
	
	public static TextureRegion logo, title, playButtonUp, playButtonDown, earthLikePlanet, redPlanet, greenPlanet;															
	public static TextureRegion birdUp, birdDown;
	public static TextureRegion pipeUp, pipeDown, bar;
	public static TextureRegion ready, gameOver, highScore, scoreboard, retry;
	public static TextureRegion common, rare, epic, legendary;
	public static TextureRegion BRPibArea, BRBar, BREnd, BRPrizeSlot, BREndFlip;
	
	//PIBS
	public static TextureRegion notaroDown, notaroUp, notaroDead, 
	athaDown, athaUp, athaDead, ezeUp, ezeDown, ezeDead, ezeDead2, 
	tomiUp, tomiDown, tomiDead, fedeUp, fedeUp2, fedeDown, fedeDead, reimonUp, reimonDown, reimonDead,
	santiUp, santiDown, santiUp2, santiDown2, santiDead, danUp, danDown, danUp2, danDown2, danDead, carloUp, carloDown, carloDead, carloDead2; 

	public static PibSpriteManager notaroSpriteMgr, ezeSpriteMgr, tomiSpriteMgr, athaSpriteMgr, fedeSpriteMgr, reimonSpriteMgr, danSpriteMgr, santiSpriteMgr, carloSpriteMgr;


	//PODERES
	public static Texture dashTextures, camioTextures, ringTextures, aguaTextures, circleTextures, powerAvailableTex;
	public static Texture ezeLaserBgTex, notaroMeteoroBgTex, notaroCorsariosBgTex, danJewBgTex,	athaFiredBgTex,	carloCalmBgTex,tomShitBgTex,reimonAguaBgTex,fedePutasBgTex,	santiCamioBgTex;
	public static Texture powerFacesTex, powerItemsTex, nameHolderTex;
	public static TextureRegion ezeLaserBg, notaroMeteoroBg , notaroCorsariosBg, danJewBg ,	athaFiredBg ,	carloCalmBg ,tomShitBg ,reimonAguaBg ,fedePutasBg ,	santiCamioBg ;
	public static TextureRegion ezeLaserFace, santiCamioFace, notaroMeteoroFace, notaroCorsariosFace, fedeCascoFace, reimonAguaFace, carloCalmFace, tomiTeleFace, athaFiredFace, danJewFace;
	public static TextureRegion itemLaser, itemCoin, itemPapel, itemPutas,itemFired, itemMaths, itemFaso, itemPlanta,dBarBody, dBar, dBarRightBorder,dBarLeftBorder, powerNameHolder, powerAvailable,whiteCircle;
	public static Animation dashAnimation, camioAnimation, ringAnimation, aguaAnimation, circleAnimation;
	
	
	//GUI
	public static PrizeHolderSkin legendarySkin, epicSkin, rareSkin, commonSkin;
	public static ScoreboardSkin darkBlueSkin, lightBlueSkin, deepBlueSkin;
	
	public static TextureRegion buttonUp, buttonDown, soundIcon, bgIcon, offText, onText,pibSelectText, playText, backToMenuText, collectionText, retryText, selectYourPibText, prizeCollectionText;
	
	
	//PRIZES
	public static TextureRegion nothing, moustache, banana, potato, dickbutt, approval, taladro, pila, hl3,
	ocgMouse, ortelli, infPorn, shitPC, bewbs, s3, wiips, flyboard, shirt, fap, baby,
	unendingList, milo, chivo, jetpack, dax, limon, helmet, artifact, dance, kipa, prepu, agua, shots, bordemort,
	madera, guitarra, camioneta, calm, mari, maths,laser,moneda,papel,puta,faso;
	
	
	//BACKGROUNDS
	public static List<TextureRegion> backgrounds;
	public static List<Texture> backgroundsTex;
	
	
	//FONTS
	public static BitmapFont font, shadow, whiteFont, whiteFontNoOutline,blueFont, orangeFont, purpleFont, blackFont;
	
	//PREFS
	public static Preferences prefs;
	
	
	public static void load() {
		
		loadLogo();
		loadPibs();
		loadBackgrounds();
		loadSkins();
		loadButtons();
		
		generalTexture = new Texture(Gdx.files.internal("data/textureFlappyPib5.png"));
        generalTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        
        playButtonUp = new TextureRegion(generalTexture, 0, 83, 29, 16);
        playButtonDown = new TextureRegion(generalTexture, 29, 83, 29, 16);
        playButtonUp.flip(false, true);
        playButtonDown.flip(false, true);
        
        
        titleTex = new Texture(Gdx.files.internal("data/FlappyPibTitle.png"));
        title = new TextureRegion(titleTex);
        title.flip(false, true);
        
        
        ready = new TextureRegion(generalTexture,60, 83, 34, 7);
        ready.flip(false, true);
        gameOver = new TextureRegion(generalTexture,60, 92, 46, 7);
        gameOver.flip(false, true);
        highScore = new TextureRegion(generalTexture,60, 101, 48, 7);
        highScore.flip(false, true);
        retry = new TextureRegion(generalTexture,60, 110, 33, 7);
        retry.flip(false, true);
        
        common = new TextureRegion(generalTexture,60, 119, 38, 7);
        common.flip(false, true);
        rare = new TextureRegion(generalTexture,60, 128, 24, 7);
        rare.flip(false, true);
        epic = new TextureRegion(generalTexture,60, 137, 23, 7);
        epic.flip(false, true);
        legendary = new TextureRegion(generalTexture,60, 146, 48, 7);
        legendary.flip(false, true);
        
        scoreboard = new TextureRegion(generalTexture,112, 83, 97, 118);
        scoreboard.flip(false, true);

        bg = new TextureRegion(generalTexture, 0, 0, 136, 43);
        bg.flip(false, true);

        ground = new TextureRegion(generalTexture, 0, 43, 143, 11);
        ground.flip(false, true);

        birdDown = new TextureRegion(generalTexture, 170, 14, 36, 58);
        birdDown.flip(false, true);

        //bird = new TextureRegion(generalTexture, 153, 0, 17, 12);
        //bird.flip(false, true);

        birdUp = new TextureRegion(generalTexture, 206, 14, 40, 58);
        birdUp.flip(false, true);


        pipeUp = new TextureRegion(generalTexture, 192, 0, 24, 14);
        // Create by flipping existing skullUp
        pipeDown = new TextureRegion(pipeUp);
        pipeDown.flip(false, true);

        bar = new TextureRegion(generalTexture, 136, 16, 22, 3);
        bar.flip(false, true);
        
        
        
        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.setScale(.25f, -.25f);
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.setScale(.25f, -.25f);
        
        whiteFont = new BitmapFont(Gdx.files.internal("data/RetroComputerOutlined.fnt"));
        whiteFontNoOutline = new BitmapFont(Gdx.files.internal("data/RetroComputer.fnt"));
        blueFont = new BitmapFont(Gdx.files.internal("data/RetroComputerBlueOutlined.fnt"));
        purpleFont = new BitmapFont(Gdx.files.internal("data/RetroComputerPurpleOutlined.fnt"));
        orangeFont = new BitmapFont(Gdx.files.internal("data/RetroComputerOrangeOutlined.fnt"));
        blackFont = new BitmapFont(Gdx.files.internal("data/RetroComputerBlack.fnt"));
        
        float scaleX = .2f;
        float scaleY = -.2f;
        
        whiteFont.setScale(scaleX, scaleY);
        whiteFontNoOutline.setScale(scaleX, scaleY);
        blueFont.setScale(scaleX, scaleY);
        purpleFont.setScale(scaleX, scaleY);
        orangeFont.setScale(scaleX, scaleY);
        blackFont.setScale(scaleX, scaleY);
        
        
        prefs =Gdx.app.getPreferences("FlappyPib");
        
        //SACAR
        //prefs.clear();
        if (!prefs.contains("highScore"))
    	prefs.putInteger("highScore", 0);

        
        
        loadPrizes();
		loadPowerTextures();
		
       

    }
	
	
	private static void loadPowerTextures() {
		
		loadPowerAnimations();
		loadPowerBgSprites();
		loadPowerFaces();
		loadPowerElements();
		
		    
	}
	
	private static void loadPowerElements() {
		
		powerItemsTex = new Texture(Gdx.files.internal("data/powerSprites/Items.png"));
		
		loadPowerItems();
		loadPowerUI();

		
	}
	
	private static void loadPowerUI() {
		

		
		dBarLeftBorder= new TextureRegion(powerItemsTex, 56, 0, 5, 10);
		dBarLeftBorder.flip(false, true);
		
		dBarRightBorder = new TextureRegion(dBarLeftBorder);
		dBarRightBorder.flip(true,false);
		
		dBarBody= new TextureRegion(powerItemsTex, 62, 0, 1, 10);
		dBarBody.flip(false, true);
		
		dBar= new TextureRegion(powerItemsTex, 64, 0, 1, 4);
		dBar.flip(false, true);

		
		powerNameHolder= new TextureRegion(powerItemsTex, 0, 50, 176, 40);
		powerNameHolder.flip(false, true);
		
	}
	
	private static void loadPowerItems() {
		
		powerAvailableTex = new Texture(Gdx.files.internal("data/powerSprites/PowerAvailableIndicator2.png"));
		
		powerAvailable = new TextureRegion(powerAvailableTex, 0, 0, 655, 155);
		powerAvailable.flip(false, true);
		
		itemCoin = new TextureRegion(powerItemsTex, 0, 0, 23, 23);
		itemCoin.flip(false, true);
		
		itemLaser= new TextureRegion(powerItemsTex, 0, 38, 42, 8);
		itemLaser.flip(false, true);
		
		itemPutas= new TextureRegion(powerItemsTex, 23, 0, 33, 36);
		itemPutas.flip(false, true);
		
		itemPapel= new TextureRegion(powerItemsTex, 65, 0, 35, 34);
		itemPapel.flip(false, true);
		
		itemFired= new TextureRegion(powerItemsTex, 100, 0, 76, 38);
		itemFired.flip(false, true);
		
		itemMaths= new TextureRegion(powerItemsTex, 176, 0, 50, 35);
		itemMaths.flip(false, true);

		itemFaso= new TextureRegion(powerItemsTex, 176, 35, 59, 60-35);
		itemFaso.flip(false, true);

		itemPlanta= new TextureRegion(powerItemsTex, 176, 60, 217-176, 98-60);
		itemPlanta.flip(false, true);


		
	}
	
	
	
	private static void loadPowerFaces() {
		int w = 200;
		int h = 300;
		
		powerFacesTex = new Texture(Gdx.files.internal("data/powerSprites/Faces.png"));
		
		ezeLaserFace = new TextureRegion(powerFacesTex, 0*w, 0, w, h);
		ezeLaserFace.flip(false, true);
		notaroMeteoroFace = new TextureRegion(powerFacesTex, 1*w, 0, w, h);
		notaroMeteoroFace.flip(false, true);
		fedeCascoFace = new TextureRegion(powerFacesTex, 2*w, 0, w, h);
		fedeCascoFace.flip(false, true);
		reimonAguaFace = new TextureRegion(powerFacesTex, 3*w, 0, w, h);
		reimonAguaFace.flip(false, true);
		carloCalmFace = new TextureRegion(powerFacesTex, 4*w, 0, w, h);
		carloCalmFace.flip(false, true);
		tomiTeleFace = new TextureRegion(powerFacesTex, 5*w, 0, w, h);
		tomiTeleFace.flip(false, true);
		santiCamioFace = new TextureRegion(powerFacesTex, 6*w, 0, w, h);
		santiCamioFace.flip(false, true);
		athaFiredFace = new TextureRegion(powerFacesTex, 7*w, 0, w, h);
		athaFiredFace.flip(false, true);
		danJewFace = new TextureRegion(powerFacesTex, 8*w, 0, w, h);
		danJewFace.flip(false, true);
		notaroCorsariosFace = new TextureRegion(powerFacesTex, 9*w, 0, w, h);
		notaroCorsariosFace.flip(false, true);

	}
	
	private static void loadPowerAnimations() {
		dashTextures = new Texture(Gdx.files.internal("data/powerSprites/DashSprites.png"));
	    TextureRegion[] dashSprites = {new TextureRegion(dashTextures, 0,0,300,120), new TextureRegion(dashTextures, 0,120,300,120),new TextureRegion(dashTextures, 0,240,300,120)};
	    dashSprites[0].flip(false, true);
	    dashSprites[1].flip(false, true);
	    dashSprites[2].flip(false, true);
	      
	    dashAnimation = new Animation(0.06f, dashSprites); 
	    dashAnimation.setPlayMode(Animation.PlayMode.LOOP);
	      
	    
		camioTextures = new Texture(Gdx.files.internal("data/powerSprites/CamioSprites.png"));
		TextureRegion[] camioSprites = {new TextureRegion(camioTextures, 0,0,300,120), new TextureRegion(camioTextures, 0,120,300,120),new TextureRegion(camioTextures, 0,240,300,120)};
		camioSprites[0].flip(false, true);
		camioSprites[1].flip(false, true);
		camioSprites[2].flip(false, true);
				      
		camioAnimation = new Animation(0.06f, camioSprites); 
		camioAnimation.setPlayMode(Animation.PlayMode.LOOP);
		      
		      
		ringTextures = new Texture(Gdx.files.internal("data/powerSprites/RingSprites.png"));
		TextureRegion[] ringSprites = {new TextureRegion(ringTextures, 0,0,200,200), new TextureRegion(ringTextures, 200,0,200,200),new TextureRegion(ringTextures, 400,0,200,200),
										new TextureRegion(ringTextures, 0,200,200,200), new TextureRegion(ringTextures, 200,200,200,200),new TextureRegion(ringTextures, 400,200,200,200)};
				      
		ringAnimation = new Animation(0.06f, ringSprites); 
		ringAnimation.setPlayMode(Animation.PlayMode.LOOP);
		
		
		

		
		aguaTextures = new Texture(Gdx.files.internal("data/powerSprites/AguaSprites.png"));
		TextureRegion[] aguaSprites = {new TextureRegion(aguaTextures, 0,0,100,100), new TextureRegion(aguaTextures, 100,0,100,100),new TextureRegion(aguaTextures, 200,0,100,100),
										new TextureRegion(aguaTextures, 300,0,100,100), new TextureRegion(aguaTextures, 400,0,100,100),new TextureRegion(aguaTextures, 500,0,100,100),
										new TextureRegion(aguaTextures, 0,100,100,100), new TextureRegion(aguaTextures, 100,100,100,100),new TextureRegion(aguaTextures, 200,100,100,100),
										new TextureRegion(aguaTextures, 300,100,100,100), new TextureRegion(aguaTextures, 400,100,100,100),new TextureRegion(aguaTextures, 500,100,100,100)};


		for (int i = 0;i<12;i++) {
			aguaSprites[i].flip(false,true);
		}
				      
		aguaAnimation = new Animation(0.09f, aguaSprites); 
		aguaAnimation.setPlayMode(Animation.PlayMode.NORMAL);
		
		
		
		
		circleTextures = new Texture(Gdx.files.internal("data/powerSprites/WhiteRingSprites.png"));
		TextureRegion[] circleSprites = {new TextureRegion(circleTextures, 0,0,250,250), new TextureRegion(circleTextures, 250,0,250,250),new TextureRegion(circleTextures, 500,0,250,250),new TextureRegion(circleTextures, 750,0,250,250), 
										new TextureRegion(circleTextures, 0,250,250,250),new TextureRegion(circleTextures, 250,250,250,250),new TextureRegion(circleTextures, 500,250,250,250)};

		whiteCircle = new TextureRegion(circleTextures, 250,250,250,250);
		
		circleAnimation = new Animation(0.03f, circleSprites); 
		circleAnimation.setPlayMode(Animation.PlayMode.NORMAL);
	      
	      

	}
	
	private static void loadPowerBgSprites() {
		
		int w = 300;
		int h = 400;
		
		danJewBgTex = new Texture(Gdx.files.internal("data/powerSprites/DanJewBg.png"));
		danJewBg = new TextureRegion(danJewBgTex, 0, 0, 600,h);
		danJewBg.flip(false, true);

		notaroMeteoroBgTex = new Texture(Gdx.files.internal("data/powerSprites/NotaroMeteoroBg.png"));
		notaroMeteoroBg = new TextureRegion(notaroMeteoroBgTex, 0, 0, 600,h);
		notaroMeteoroBg.flip(false, true);
		

		notaroCorsariosBgTex = new Texture(Gdx.files.internal("data/powerSprites/NotaroCorsariosBg2.png"));
		notaroCorsariosBg = new TextureRegion(notaroCorsariosBgTex, 0, 0, w,h);
		notaroCorsariosBg.flip(false, true);
		
		ezeLaserBgTex = new Texture(Gdx.files.internal("data/powerSprites/EzeLaserBg.png"));
		ezeLaserBg = new TextureRegion(ezeLaserBgTex, 0, 0, w,h);
		ezeLaserBg.flip(false, true);
		
		tomShitBgTex = new Texture(Gdx.files.internal("data/powerSprites/TomWorkBg.png"));
		tomShitBg = new TextureRegion(tomShitBgTex, 0, 0, w,h);
		tomShitBg.flip(false, true);
		
		athaFiredBgTex = new Texture(Gdx.files.internal("data/powerSprites/AthaFiredBg.png"));
		athaFiredBg = new TextureRegion(athaFiredBgTex, 0, 0, w,h);
		athaFiredBg.flip(false, true);
		
		reimonAguaBgTex = new Texture(Gdx.files.internal("data/powerSprites/ReimonAguaBg.png"));
		reimonAguaBg = new TextureRegion(reimonAguaBgTex, 0, 0, w,h);
		reimonAguaBg.flip(false, true);
		
		fedePutasBgTex = new Texture(Gdx.files.internal("data/powerSprites/FedePutasBg.png"));
		fedePutasBg = new TextureRegion(fedePutasBgTex, 0, 0, w,h);
		fedePutasBg.flip(false, true);
		
		santiCamioBgTex = new Texture(Gdx.files.internal("data/powerSprites/SantiCamioBg.png"));
		santiCamioBg = new TextureRegion(santiCamioBgTex, 0, 0, w,h);
		santiCamioBg.flip(false, true);
		
		carloCalmBgTex = new Texture(Gdx.files.internal("data/powerSprites/CarloCalmBg.png"));
		carloCalmBg = new TextureRegion(carloCalmBgTex, 0, 0, w,h);
		carloCalmBg.flip(false, true);
		
	}
	
	private static void loadPrizes() {
		
			int pW = 80;
			int pH = 40;
		
			pibPrizeTextures = new Texture(Gdx.files.internal("data/PibPrizes.png"));
			generalPrizeTextures = new Texture(Gdx.files.internal("data/GeneralPrizes.png"));
			
			
	        nothing = new TextureRegion(generalPrizeTextures, 0,0,pW,pH);
	        nothing.flip(false, true);
	        bewbs = new TextureRegion(generalPrizeTextures, 80,0,pW,pH);
	        bewbs.flip(false, true);
	        s3 = new TextureRegion(generalPrizeTextures, 160,0,pW,pH);
	        s3.flip(false, true);
	        wiips = new TextureRegion(generalPrizeTextures, 0,40,pW,pH);
	        wiips.flip(false, true);
	        fap = new TextureRegion(generalPrizeTextures, 80,40,pW,pH);
	        fap.flip(false, true);
	        potato = new TextureRegion(generalPrizeTextures, 160,40,pW,pH);
	        potato.flip(false, true);
	        moustache = new TextureRegion(generalPrizeTextures, 0,80,pW,pH);
	        moustache.flip(false,true);
	        approval = new TextureRegion(generalPrizeTextures, 80,80,pW,pH);
	        approval.flip(false,true);
	        dickbutt = new TextureRegion(generalPrizeTextures, 160,80,pW,pH);
	        dickbutt.flip(false,true);
	        banana = new TextureRegion(generalPrizeTextures, 0,120,pW,pH);
	        banana.flip(false,true);
	        taladro = new TextureRegion(generalPrizeTextures, 80,120,pW,pH);
	        taladro.flip(false,true);
	        pila = new TextureRegion(generalPrizeTextures, 160,120,pW,pH);
	        pila.flip(false,true);
	        laser = new TextureRegion(generalPrizeTextures, 0,160,pW,pH);
	        laser.flip(false,true);
	        hl3 = new TextureRegion(generalPrizeTextures, 80,160,pW,pH);
	        hl3.flip(false,true);
	        moneda = new TextureRegion(generalPrizeTextures, 160,160,pW,pH);
	        moneda.flip(false,true);
	        papel = new TextureRegion(generalPrizeTextures, 0,200,pW,pH);
	        papel.flip(false,true);
	        puta = new TextureRegion(generalPrizeTextures, 80,200,pW,pH);
	        puta.flip(false,true);
	        
	        
	        
	        
	        shitPC = new TextureRegion(pibPrizeTextures,0,0,pW,pH);
	        shitPC.flip(false,true);
	        flyboard = new TextureRegion(pibPrizeTextures,80,0,pW,pH);
	        flyboard.flip(false,true);
	        baby = new TextureRegion(pibPrizeTextures, 160,0,pW,pH);
	        baby.flip(false, true);
	        infPorn = new TextureRegion(pibPrizeTextures, 0,40,pW,pH);
	        infPorn.flip(false, true);
	        shirt = new TextureRegion(pibPrizeTextures, 80,40,pW,pH);
	        shirt.flip(false, true);
	        unendingList = new TextureRegion(pibPrizeTextures, 160,40,pW,pH);
	        unendingList.flip(false, true);
	        ocgMouse = new TextureRegion(pibPrizeTextures, 0,80,pW,pH);
	        ocgMouse.flip(false, true);
	        ortelli = new TextureRegion(pibPrizeTextures, 80,80,pW,pH);
	        ortelli.flip(false, true);
	        milo = new TextureRegion(pibPrizeTextures, 160,80,pW,pH);
	        milo.flip(false, true);
	        chivo = new TextureRegion(pibPrizeTextures, 0,120,pW,pH);
	        chivo.flip(false, true);
	        jetpack = new TextureRegion(pibPrizeTextures, 80,120,pW,pH);
	        jetpack.flip(false, true);
	        dax = new TextureRegion(pibPrizeTextures, 160,120,pW,pH);
	        dax.flip(false, true);
	        
	        limon = new TextureRegion(pibPrizeTextures, 0,160,pW,pH);
	        limon.flip(false, true);
	        helmet = new TextureRegion(pibPrizeTextures, 80,160,pW,pH);
	        helmet.flip(false, true);
	        artifact = new TextureRegion(pibPrizeTextures, 160,160,pW,pH);
	        artifact.flip(false, true);
	        dance = new TextureRegion(pibPrizeTextures, 0,200,pW,pH);
	        dance.flip(false, true);
	        kipa = new TextureRegion(pibPrizeTextures,80, 200, pW,pH);
	        kipa.flip(false, true);
	        prepu = new TextureRegion(pibPrizeTextures, 160, 200, pW,pH);
	        prepu.flip(false, true);
	        agua = new TextureRegion(pibPrizeTextures, 0,240, pW,pH);
	        agua.flip(false, true);
	        shots = new TextureRegion(pibPrizeTextures, 80, 240, pW,pH);
	        shots.flip(false, true);
	        bordemort = new TextureRegion(pibPrizeTextures, 160, 240, pW,pH);
	        bordemort.flip(false, true);
	        madera = new TextureRegion(pibPrizeTextures, 0,280, pW,pH);
	        madera.flip(false, true);
	        guitarra = new TextureRegion(pibPrizeTextures, 80, 280, pW,pH);
	        guitarra.flip(false, true);
	        camioneta = new TextureRegion(pibPrizeTextures, 160, 280, pW,pH);
	        camioneta.flip(false, true);

	        calm = new TextureRegion(pibPrizeTextures, 0, 320, pW,pH);
	        calm.flip(false, true);
	        mari = new TextureRegion(pibPrizeTextures, 80, 320, pW,pH);
	        mari.flip(false, true);
	        maths = new TextureRegion(pibPrizeTextures, 160, 320, pW,pH);
	        maths.flip(false, true);
	        
	}
	
	private static void loadButtons() {
		buttonTextures = new Texture(Gdx.files.internal("data/Buttons1.png"));
		
		buttonUp = new TextureRegion (buttonTextures, 0,0, 296, 117);
		buttonDown = new TextureRegion (buttonTextures, 0,119,296, 117);
		
		pibSelectText = new TextureRegion (buttonTextures, 1,236, 219,41);
		collectionText = new TextureRegion (buttonTextures, 1,278, 229,41);
		playText = new TextureRegion (buttonTextures, 1,320, 101,41);
		backToMenuText = new TextureRegion (buttonTextures, 1,362, 263,41);
		retryText = new TextureRegion (buttonTextures, 1,404, 116,41);
		selectYourPibText = new TextureRegion (buttonTextures, 1, 446, 311, 41);
		prizeCollectionText = new TextureRegion (buttonTextures, 1, 488, 329, 41);
		
		/*
		soundOnText =  new TextureRegion (buttonTextures, 1, 488, 329, 41);
		soundOffText =  new TextureRegion (buttonTextures, 1, 488, 329, 41);
		musicOnText =  new TextureRegion (buttonTextures, 1, 488, 329, 41);
		musicOffText =  new TextureRegion (buttonTextures, 1, 488, 329, 41);
		normalSoundText =  new TextureRegion (buttonTextures, 1, 488, 329, 41);
		classicSoundText =  new TextureRegion (buttonTextures, 1, 488, 329, 41);
		*/
		

		onText =  new TextureRegion (buttonTextures, 1, 586, 50, 628-586);
		offText =  new TextureRegion (buttonTextures, 1, 630, 70, 672-630);
		
		soundIcon = new TextureRegion (buttonTextures, 1, 531, 57, 586-531);
		bgIcon = new TextureRegion (buttonTextures, 71, 531, 137-71, 586-531);
		

		onText.flip(false, true);
		offText.flip(false, true);

		soundIcon.flip(false, true);
		bgIcon.flip(false, true);
		
		buttonUp.flip(false, true);
		buttonDown.flip(false, true);
		
		pibSelectText.flip(false, true);
		collectionText.flip(false, true);
		playText.flip(false, true);
		backToMenuText.flip(false, true);
		retryText.flip(false,true);
		selectYourPibText.flip(false, true);
		prizeCollectionText.flip(false, true);
		

		
	}
	
	private static void loadSkins() {
		
		loadPibHolderSkins();
		loadPrizeHolderSkins();
		loadScoreboardSkins();
	}
	
	private static void loadPibHolderSkins() {


		pibHolderSkinBR = new Texture(Gdx.files.internal("data/holderSkins/BRPibHolderSkin.png"));
		pibHolderSkinBR.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		BRBar = new TextureRegion (pibHolderSkinBR, 191,19, 6, 46); 
		BREnd = new TextureRegion (pibHolderSkinBR, 213,19, 46, 46);
		BRPibArea = new TextureRegion (pibHolderSkinBR, 0,0, 174, 174);
		BRPibArea.flip(false, true);
		BREndFlip = new TextureRegion (pibHolderSkinBR, 213,19, 46, 46);
		BREndFlip.flip(false, true);
		BRPrizeSlot = new TextureRegion (pibHolderSkinBR, 262,19, 33, 33);
		BRPrizeSlot.flip(false, true);
	}
	
	private static void loadPrizeHolderSkins() {
		


		legendarySkinTexture = new Texture(Gdx.files.internal("data/holderSkins/LegendaryPrizeHolderSkin.png"));
		legendarySkinTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		epicSkinTexture = new Texture(Gdx.files.internal("data/holderSkins/EpicPrizeHolderSkin.png"));
		epicSkinTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		rareSkinTexture = new Texture(Gdx.files.internal("data/holderSkins/RarePrizeHolderSkin.png"));
		rareSkinTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		commonSkinTexture = new Texture(Gdx.files.internal("data/holderSkins/CommonPrizeHolderSkin.png"));
		commonSkinTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		
		legendarySkin = new PrizeHolderSkin();
		epicSkin = new PrizeHolderSkin();
		rareSkin = new PrizeHolderSkin();
		commonSkin = new PrizeHolderSkin();

		
		legendarySkin.loadSkin(legendarySkinTexture);
		epicSkin.loadSkin(epicSkinTexture);
		rareSkin.loadSkin(rareSkinTexture);
		commonSkin.loadSkin(commonSkinTexture);
		
		
	}
	
	private static void loadScoreboardSkins() {
		
		//Que onda esto de loadear 3 skins??

//		scoreboardTexture1 = new Texture(Gdx.files.internal("data/holderSkins/ScoreboardSkin1.png"));
//		scoreboardTexture1.setFilter(TextureFilter.Linear, TextureFilter.Linear);
//		
//
//
//		darkBlueSkin = new ScoreboardSkin();
//		
//		darkBlueSkin.bgArea = new TextureRegion (scoreboardTexture1, 0,0, 294, 460); 
//		darkBlueSkin.prizeArea = new TextureRegion (scoreboardTexture1, 300,105, 259, 175);
//		darkBlueSkin.scoreArea = new TextureRegion (scoreboardTexture1, 300,3, 259, 102);
//		
//		darkBlueSkin.flipAllTextures(false,true);
//		
//		
//		scoreboardTexture2 = new Texture(Gdx.files.internal("data/holderSkins/ScoreboardSkin2.png"));
//		scoreboardTexture2.setFilter(TextureFilter.Linear, TextureFilter.Linear);
//
//		lightBlueSkin = new ScoreboardSkin();	
//		
//		lightBlueSkin.bgArea = new TextureRegion (scoreboardTexture2, 0,0, 294, 460); 
//		lightBlueSkin.prizeArea = new TextureRegion (scoreboardTexture2, 300,105, 259, 175);
//		lightBlueSkin.scoreArea = new TextureRegion (scoreboardTexture2, 300,6, 259, 102);
//		
//		lightBlueSkin.flipAllTextures(false,true);
	
		

		scoreboardTexture3 = new Texture(Gdx.files.internal("data/holderSkins/ScoreboardSkin3.png"));
		scoreboardTexture3.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		deepBlueSkin = new ScoreboardSkin();

		deepBlueSkin.bgArea = new TextureRegion (scoreboardTexture3, 0,0, 294, 460); 
		deepBlueSkin.prizeArea = new TextureRegion (scoreboardTexture3, 300,105, 259, 175);
		deepBlueSkin.scoreArea = new TextureRegion (scoreboardTexture3, 300,3, 259, 102);
		
		deepBlueSkin.flipAllTextures(false,true);
		
	}
	
	
	private static void loadBackgrounds() {
		
		
		backgroundsTex = new ArrayList<Texture>();
		backgrounds = new ArrayList<TextureRegion>();
		
		backgroundsTex.add( new Texture(Gdx.files.internal("data/EarthLikePlanet.jpg")));
		backgroundsTex.add( new Texture(Gdx.files.internal("data/RedPlanet from ground.jpg")));
		backgroundsTex.add( new Texture(Gdx.files.internal("data/GreenAlienPlanet.jpg")));
		backgroundsTex.add( new Texture(Gdx.files.internal("data/DeepBluePlanet.jpg")));
		backgroundsTex.add( new Texture(Gdx.files.internal("data/BluePlanetOverClouds.jpg")));
		backgroundsTex.add( new Texture(Gdx.files.internal("data/PurplePlanet.jpg")));
        //earthLikePlanetTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        
		
        for(Texture t : backgroundsTex) {
        	TextureRegion tr = new TextureRegion(t);
        	tr.flip(false,true);
        	backgrounds.add(tr);
        }
        
        
	}
	
	private static void loadLogo() {

		logoTexture = new Texture(Gdx.files.internal("data/FoscusGames.jpg"));
        logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        
		logo = new TextureRegion(logoTexture, 0,0,1990, 798);
	}
	
	private static void loadPibs() {
       	
       	
       	athaSprites = new Texture(Gdx.files.internal("data/pibSprites/AthaSprites.png"));
       	athaSprites.setFilter(TextureFilter.Linear, TextureFilter.Linear);
       	notaroSprites = new Texture(Gdx.files.internal("data/pibSprites/NotaroSprites.png"));
       	notaroSprites.setFilter(TextureFilter.Linear, TextureFilter.Linear);
       	ezeSprites = new Texture(Gdx.files.internal("data/pibSprites/EzeSprites.png"));
       	ezeSprites.setFilter(TextureFilter.Linear, TextureFilter.Linear);
       	tomiSprites = new Texture(Gdx.files.internal("data/pibSprites/TomiSprites.png"));
       	tomiSprites.setFilter(TextureFilter.Linear, TextureFilter.Linear);
       	fedeSprites = new Texture(Gdx.files.internal("data/pibSprites/FedeSprites.png"));
       	fedeSprites.setFilter(TextureFilter.Linear, TextureFilter.Linear);
       	santiSprites = new Texture(Gdx.files.internal("data/pibSprites/SantiSprites.png"));
       	santiSprites.setFilter(TextureFilter.Linear, TextureFilter.Linear);
       	danSprites = new Texture(Gdx.files.internal("data/pibSprites/DanSprites.png"));
       	danSprites.setFilter(TextureFilter.Linear, TextureFilter.Linear);
       	carloSprites = new Texture(Gdx.files.internal("data/pibSprites/CarloSprites.png"));
       	carloSprites.setFilter(TextureFilter.Linear, TextureFilter.Linear);
       	reimonSprites = new Texture(Gdx.files.internal("data/pibSprites/ReimonSprites.png"));
       	reimonSprites.setFilter(TextureFilter.Linear, TextureFilter.Linear);
       	
        
       	notaroDown = new TextureRegion(notaroSprites, 0,0,200, 300);
		notaroUp = new TextureRegion(notaroSprites, 200,0,200, 300);
		notaroDead = new TextureRegion(notaroSprites, 400,0,200, 300);
		
		athaDown = new TextureRegion(athaSprites, 0,0,200, 300);
		athaUp = new TextureRegion(athaSprites, 200,0,200, 300);
		athaDead = new TextureRegion(athaSprites, 400,0,200, 300);
		
		ezeDown = new TextureRegion(ezeSprites, 0,0,200, 300);
		ezeUp = new TextureRegion(ezeSprites, 200,0,200, 300);
		ezeDead = new TextureRegion(ezeSprites, 400,0,200, 300);
		ezeDead2 = new TextureRegion(ezeSprites, 600,0,200, 300);
		
		tomiDown = new TextureRegion(tomiSprites, 0,0,200, 300);
		tomiUp = new TextureRegion(tomiSprites, 200,0,200, 300);
		tomiDead = new TextureRegion(tomiSprites, 400,0,200, 300);
		
		fedeDown = new TextureRegion(fedeSprites, 0,0,200, 300);
		fedeUp = new TextureRegion(fedeSprites, 200,0,200, 300);
		fedeDead = new TextureRegion(fedeSprites, 400,0,200, 300);
		fedeUp2 = new TextureRegion(fedeSprites, 600, 0, 200, 300);


		reimonDown = new TextureRegion(reimonSprites, 0,0,200, 300);
		reimonUp = new TextureRegion(reimonSprites, 200,0,200, 300);
		reimonDead = new TextureRegion(reimonSprites, 400,0,200, 300);

		danDown = new TextureRegion(danSprites, 0,0,200, 300);
		danUp = new TextureRegion(danSprites, 200,0,200, 300);
		danDown2 = new TextureRegion(danSprites, 600,0,200, 300);
		danUp2 = new TextureRegion(danSprites, 800,0,200, 300);
		danDead = new TextureRegion(danSprites, 400,0,200, 300);

		santiDown = new TextureRegion(santiSprites, 0,0,200, 300);
		santiUp = new TextureRegion(santiSprites, 200,0,200, 300);
		santiDown2 = new TextureRegion(santiSprites, 600,0,200, 300);
		santiDead = new TextureRegion(santiSprites, 400,0,200, 300);

		carloDown = new TextureRegion(carloSprites, 0,0,200, 300);
		carloUp = new TextureRegion(carloSprites, 200,0,200, 300);
		carloDead = new TextureRegion(carloSprites, 400,0,200, 300);
		carloDead2 = new TextureRegion(carloSprites, 600, 0, 200, 300);
		
		 notaroDown.flip(false, true);
		 notaroUp.flip(false, true);
		 notaroDead.flip(false, true);
		 
		 athaDown.flip(false, true);
		 athaUp.flip(false, true);
		 athaDead.flip(false, true);
		 
		 ezeDown.flip(false, true);
		 ezeUp.flip(false, true);
		 ezeDead.flip(false, true);
		 ezeDead2.flip(true, true);
		 
		 tomiDown.flip(false, true);
		 tomiUp.flip(true, true);
		 tomiDead.flip(false, true);

		 fedeDown.flip(false, true);
		 fedeUp.flip(false, true);
		 fedeUp2.flip(false, true);
		 fedeDead.flip(false, true);

		 reimonDown.flip(false, true);
		 reimonUp.flip(false, true);
		 reimonDead.flip(false, true);

		 danDown.flip(false, true);
		 danUp.flip(false, true);
		 danDown2.flip(false, true);
		 danUp2.flip(false, true);
		 danDead.flip(false, true);

		 santiDown.flip(false, true);
		 santiUp.flip(false, true);
		 santiDown2.flip(false, true);
		 //santiUp2.flip(false, true);
		 santiDead.flip(false, true);

		 carloDown.flip(false, true);
		 carloUp.flip(false, true);
		 carloDead.flip(false, true);
		 carloDead2.flip(false, true);
		 
		
		 List<TextureRegion> ezeUpSprites = new ArrayList<TextureRegion>();
		 ezeUpSprites.add(ezeUp);	 
		 List<TextureRegion> ezeDownSprites = new ArrayList<TextureRegion>();
		 ezeDownSprites.add(ezeDown);
		 List<TextureRegion> ezeDeadSprites = new ArrayList<TextureRegion>();
		 ezeDeadSprites.add(ezeDead);
		 ezeDeadSprites.add(ezeDead2);

		 List<TextureRegion> carloUpSprites = new ArrayList<TextureRegion>();
		 carloUpSprites.add(carloUp);	 
		 List<TextureRegion> carloDownSprites = new ArrayList<TextureRegion>();
		 carloDownSprites.add(carloDown);
		 List<TextureRegion> carloDeadSprites = new ArrayList<TextureRegion>();
		 carloDeadSprites.add(carloDead);
		 carloDeadSprites.add(carloDead2);
		 
		 List<TextureRegion> fedeUpSprites = new ArrayList<TextureRegion>();
		 fedeUpSprites.add(fedeUp);	 
		 fedeUpSprites.add(fedeUp2);
		 List<TextureRegion> fedeDownSprites = new ArrayList<TextureRegion>();
		 fedeDownSprites.add(fedeDown);
		 List<TextureRegion> fedeDeadSprites = new ArrayList<TextureRegion>();
		 fedeDeadSprites.add(fedeDead);

		 List<TextureRegion> danUpSprites = new ArrayList<TextureRegion>();
		 danUpSprites.add(danUp);
		 danUpSprites.add(danUp2);
		 List<TextureRegion> danDownSprites = new ArrayList<TextureRegion>();
		 danDownSprites.add(danDown);
		 danDownSprites.add(danDown2);
		 List<TextureRegion> danDeadSprites = new ArrayList<TextureRegion>();
		 danDeadSprites.add(danDead);
		 
		 List<TextureRegion> santiUpSprites = new ArrayList<TextureRegion>();
		 santiUpSprites.add(santiUp);
		 santiUpSprites.add(santiUp);	//Esto esta bien, es asi, no te asustes
		 List<TextureRegion> santiDownSprites = new ArrayList<TextureRegion>();
		 santiDownSprites.add(santiDown);
		 santiDownSprites.add(santiDown2);
		 List<TextureRegion> santiDeadSprites = new ArrayList<TextureRegion>();
		 santiDeadSprites.add(santiDead);
		 
		 
		 notaroSpriteMgr = new PibSpriteManager(notaroUp, notaroDown, notaroDead);	//normal
		 athaSpriteMgr = new PibSpriteManager(athaUp, athaDown, athaDead);	//normal
		 ezeSpriteMgr = new PibSpriteManager(ezeUpSprites, ezeDownSprites, ezeDeadSprites); //List Constructor
		 tomiSpriteMgr = new PibSpriteManager(tomiUp, tomiDown, tomiDead);	//normal
		 fedeSpriteMgr = new PibSpriteManager(fedeUpSprites, fedeDownSprites, fedeDeadSprites);	//ListConstructor
		 reimonSpriteMgr = new PibSpriteManager(reimonUp, reimonDown, reimonDead);	//normal
		 danSpriteMgr = new PibSpriteManager(danUpSprites, danDownSprites, danDeadSprites);	//ListConstructor
		 santiSpriteMgr = new PibSpriteManager(santiUpSprites, santiDownSprites, santiDeadSprites); //List Constructor
		 carloSpriteMgr = new PibSpriteManager(carloUpSprites, carloDownSprites, carloDeadSprites); //List Constructor

		 
		 

	}
	
	
	public static void setHighScore (int val) {
		prefs.putInteger("highScore", val);
		prefs.flush();
	}
	
	public static int getHighScore () {
		return prefs.getInteger("highScore");
	}
	
	public static void savePrize (String prizeKey) {
		prefs.putBoolean(prizeKey, true);
		prefs.flush();
	}
	
	public static boolean prizeAcquired (String prizeKey) {
		return prefs.getBoolean(prizeKey);
	}

    public static void dispose() {
    	
        generalTexture.dispose();
        logoTexture.dispose();
        generalPrizeTextures.dispose();
        pibPrizeTextures.dispose();
        buttonTextures.dispose();
        titleTex.dispose();
        
        
        disposeSkins();
        disposeFonts();
        disposeBackgrounds();
        disposePibSprites();
        disposePowerSprites();
        
    }
    
    private static void disposeSkins() {

//        scoreboardTexture1.dispose();
//        scoreboardTexture2.dispose();
        scoreboardTexture3.dispose();
        
        legendarySkinTexture.dispose();
        epicSkinTexture.dispose();
        rareSkinTexture.dispose();
        commonSkinTexture.dispose();
    	
    }
    
    private static void disposePowerSprites() {
    	dashTextures.dispose();
    	ringTextures.dispose();
    	camioTextures.dispose();
    	aguaTextures.dispose();
    	
    	powerFacesTex.dispose();
    	powerItemsTex.dispose();
    	powerAvailableTex.dispose();
    	
    	ezeLaserBgTex.dispose();
    	notaroMeteoroBgTex.dispose();
    	danJewBgTex.dispose();
    	athaFiredBgTex.dispose();
    	carloCalmBgTex.dispose();
    	tomShitBgTex.dispose();
    	reimonAguaBgTex.dispose();
    	fedePutasBgTex.dispose();
    	santiCamioBgTex.dispose();
    	notaroCorsariosBgTex.dispose();
    	
    	
    }
    
    private static void disposePibSprites() {
    	athaSprites.dispose();
    	notaroSprites.dispose();
    	ezeSprites.dispose();
    	tomiSprites.dispose();
    	fedeSprites.dispose();
    	santiSprites.dispose();
    	danSprites.dispose();
    	carloSprites.dispose();
    	reimonSprites.dispose();
    }
    
    private static void disposeFonts() {

        font.dispose();
        shadow.dispose();
        whiteFont.dispose();
        blueFont.dispose();
        purpleFont.dispose();
        orangeFont.dispose();
    	
    }
    
    
    private static void disposeBackgrounds() {
    	
    	for(Texture t : backgroundsTex) {
    		t.dispose();
    	}
    	
    	
    	
    }

}
