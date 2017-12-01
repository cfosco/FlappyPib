package com.FoscusGames.gameworld;

import java.util.List;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.FoscusGames.TweenAccesors.Value;
import com.FoscusGames.TweenAccesors.ValueAccessor;
import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.fpHandlers.FPConstants;
import com.FoscusGames.fpHandlers.InputHandler;
import com.FoscusGames.fpHandlers.ScoreManager;
import com.FoscusGames.gameobjects.Bird;
import com.FoscusGames.gameobjects.Ground;
import com.FoscusGames.gameobjects.Pipe;
import com.FoscusGames.gameobjects.Prize;
import com.FoscusGames.gameobjects.ScrollHandler;
import com.FoscusGames.gameprizes.PrizeHandler;
import com.FoscusGames.ui.CollectionMenu;
import com.FoscusGames.ui.PibHolder;
import com.FoscusGames.ui.PibSelectMenu;
import com.FoscusGames.ui.Scoreboard;
import com.FoscusGames.ui.SimpleButton;
import com.FoscusGames.ui.SoundOptionsMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class GameRenderer {


	
	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
    private SpriteBatch batcher;
    
    private int midPointY;
	
    
    //Game Objects
    private ScrollHandler scroller;
    private Ground frontGround, backGround;
    private Pipe pipe1, pipe2, pipe3;

    
    //Game Assets
    private TextureRegion  ground;
//    private TextureRegion birdMid, birdDown, birdUp;
//    private TextureRegion skullUp, skullDown, bar, bg;
    private TextureRegion ready, gameOver, highScore, common, rare, epic, legendary, title, scoreboard;
    
    // Tween
    private TweenManager manager;
    private Value alpha = new Value();
    private Color transitionColor;
    
    //Buttons
    private List<SimpleButton> menuButtons,  GOButtons;
    private List<PibHolder> pibHolders;

	
	private double startOfJump = 0;
		
	
	
	//----------TEST
	private Stage stage;
	private Table scrollTable, container;
	private ScrollPane sPane;
	
	private Scoreboard scoreb;
	
	private CollectionMenu collectionMenu;
	private PibSelectMenu pibSelectMenu;
	private SoundOptionsMenu soundOptionsMenu;
	
	
	
	public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
		
		myWorld = world;
		this.midPointY = midPointY;
//		this.gameHeight=gameHeight;
		
		InputHandler ih = (InputHandler) Gdx.input.getInputProcessor();
		
		menuButtons = ih.getMenuButtons();
		pibHolders = ih.getPibHolders();
		GOButtons = ih.getGOButtons();
		
		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, gameHeight);

		
		
        batcher = new SpriteBatch();
        //batcher.setProjectionMatrix(cam.combined);
		
		shapeRenderer = new ShapeRenderer();
		//shapeRenderer.setProjectionMatrix(cam.combined);
		
        batcher.setProjectionMatrix(cam.combined);
		shapeRenderer.setProjectionMatrix(cam.combined);
		
		initGameObjects();
		initAssets();
		
		
		initStage(gameHeight);
		
		
		
		
		scoreb = new Scoreboard(AssetLoader.deepBlueSkin, 22, midPointY - 59, 97, 118);
		
		pibSelectMenu = new PibSelectMenu(batcher, cam, myWorld);
		soundOptionsMenu = new SoundOptionsMenu(batcher,cam,myWorld);
		
		//FIN TEST
		

		transitionColor = new Color();
		prepareTransition(0, 0, 0, .5f);

	}
	
	public void initStage(int gameHeight) {
		
		 	this.stage = new Stage();
	       // Gdx.input.setInputProcessor(this.stage);

	        scrollTable = new Table();

	        scrollTable.add(pibHolders.get(0));

	        sPane = new ScrollPane(scrollTable);
	        
	        container = new Table();
	        container.setFillParent(true);
	        container.add(sPane).fill().expand();

	        this.stage.addActor(container);
	}
	
	
	
	public void render(float delta, float runTime) {

	        Gdx.gl.glClearColor(0, 1, 0, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        
			//cam.setToOrtho(true, FPConstants.gameWidth, FPConstants.gameHeight);

//	        cam.translate(1,1);
//	        cam.update();
	        
	        


	        
	        
	        shapeRenderer.begin(ShapeType.Filled);
	        
	        drawBackground();
	       
	        shapeRenderer.end();
	        
	        batcher.begin();
	        batcher.disableBlending();

	        drawBackgroundTex();

	        batcher.enableBlending();

	        drawBgPowerEffects();
	        
	        myWorld.getScroller().drawPipes(batcher);
	        
	        drawGround();
	        

	       
	        
	        
	        if (myWorld.isRunning()) {
	            drawBird(runTime);
	            drawScore();
	        } else if (myWorld.isReady()) {
	            drawBird(runTime);
	            drawReady();
	        } else if (myWorld.isMenu()) {
	            drawBirdCentered(runTime);
	            drawMenuUI();
	        } else if (myWorld.isPibSelect()){
	        	drawPibSelect(runTime);
	        
	        } else if (myWorld.isCollection()) {
	        	drawCollection(runTime);
	        	
	        } else if (myWorld.isSoundOptions()) {
	        	drawSoundOptions(runTime);
			
	        
	        } else if (myWorld.isGameOver()) {
	        
	        	
	            drawBird(runTime);
				scoreb.draw(batcher, ScoreManager.getScore(), PrizeHandler.getFinalPrize(), runTime);
	            drawGameOver();
	            drawGOButtons();
	            
	        } else if (myWorld.isHighScore()) {
	        	
				drawBird(runTime);
				scoreb.draw(batcher,ScoreManager.getScore(), PrizeHandler.getFinalPrize(), runTime);
				drawHighScore();
				drawGOButtons();
	        }
	        
	        
	        
	        batcher.end();
	        drawTransition(delta);
	        
	       if(FPConstants.collisionDebugMode) {
	    	   drawHitBoxes();
	       }

	             
	}
	
	private void drawBgPowerEffects() {
		myWorld.getBird().drawPowerBg(batcher);
	}
	
	private void drawBackgroundTex() {
		
		float y = FPConstants.gameHeight/1.8f;
		
	       batcher.draw((AssetLoader.backgrounds).get(myWorld.bgType), 0, y, FPConstants.gameWidth, FPConstants.playableHeight-y);	//midPointY + 23,43
	     
	}
	
	private void drawBackground() {
		

	 
        //Background
        if (myWorld.bgType ==1) {
        	shapeRenderer.setColor(1/255f,7/255f,21/255f,1);
        } else {
        	shapeRenderer.setColor(0,0,0,1);
        }
        	

        shapeRenderer.rect(0,0,FPConstants.gameWidth,FPConstants.gameHeight);

        
	}
	
	 
	private void drawGround() {
		
		drawDirt();
        batcher.draw(ground, frontGround.getX(), frontGround.getY(),frontGround.getWidth(), frontGround.getHeight());
        batcher.draw(ground, backGround.getX(), backGround.getY(),backGround.getWidth(), backGround.getHeight());
        
    }
	
	private void drawDirt() {
		
		  //Dirt
		batcher.end();
		shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(0.4f,0.4f,0.4f,1);
        shapeRenderer.rect(0,FPConstants.playableHeight, FPConstants.gameWidth, FPConstants.gameHeight-FPConstants.playableHeight);//midPointY+77,136,52);
        shapeRenderer.end();
        batcher.begin();

        
	}
	
	
	private void drawPibSelect(float runTime) {
		
		 
		/*
		for (PibHolder p : pibHolders) {
            p.draw(batcher, 1);
        }
		*/
		batcher.end();
		
		pibSelectMenu.draw();
		
		batcher.begin();
		
	}
	
	private void drawCollection (float runTime) {
		/*
		if (PrizeHandler.getAcquiredPrizeHolders().size() !=0) {
			for (PrizeHolder p : PrizeHandler.getAcquiredPrizeHolders()) {
				if (p != null)
					p.draw(batcher, 1);
			}
		}
		
		
		*/
		batcher.end();
		
		collectionMenu.draw();
		
		batcher.begin();
		
		//GOButtons.get(1).draw(batcher);
		
		

	}
	
	private void drawSoundOptions (float runTime) {
		
		batcher.end();
		
		soundOptionsMenu.draw();
		
		batcher.begin();
	}



    
    private void drawBirdCentered(float runTime) {	//CAMBIAR
    	
    	Bird bird = myWorld.getBird();
    	
        batcher.draw(bird.getUpSprite(), 59, bird.getY() - 15,
                bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
                bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
    }

    private void drawBird(float runTime) {
    	
    	myWorld.getBird().draw(batcher);
    }
//    	
//    	Bird bird = myWorld.getBird();
//    	
//
//    	
//        if(bird.isJumping()) {
//        	
//        	if(startOfJump ==0) {
//        		startOfJump = runTime;
//        	}
//
//        /*	batcher.draw(bird.getAnimation().getKeyFrame(runTime),bird.getX(), bird.getY(), 
//        			bird.getWidth()/2.0f, bird.getHeight()/2.0f,
//        			bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
//        	
//        	*/
//        	
//        	Color color = batcher.getColor();
//        	float oldAlpha = color.a;
//        	color.a = bird.getOpacity();
//        	batcher.setColor(color);
//        	
//        	batcher.draw(bird.getUpSprite(),bird.getX(), bird.getY(), 
//        			bird.getWidth()/2.0f, bird.getHeight()/2.0f,
//        			bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
//        	
//        	color.a = oldAlpha;
//        	batcher.setColor(color);
//        	
//        	
//        	if(runTime-startOfJump > bird.getJumpAnimationTime()) {
//        		bird.noLongerJumping();
//        		startOfJump =0;
//        	}
//        	
//        	
//        	
//        }
//        else if (!bird.isAlive()) {
//        	batcher.draw(bird.getDeadSprite(),bird.getX(), bird.getY(), 
//        			bird.getWidth()/2.0f, bird.getHeight()/2.0f,
//        			bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
//        	
//        	
//        }
//        else{
//        	
//
//        	Color color = batcher.getColor();
//        	float oldAlpha = color.a;
//        	color.a = bird.getOpacity();
//        	batcher.setColor(color);
//        	
//        	batcher.draw(bird.getDownSprite(),bird.getX(), bird.getY(), 
//        			bird.getWidth()/2.0f, bird.getHeight()/2.0f,
//        			bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
//        	
//        	
//        	color.a = oldAlpha;
//        	batcher.setColor(color);
//        	
//        }
//        
//
//        
//        bird.drawPower(batcher);
//
//
//    }

    private void drawMenuUI() {
    	
    	float titleWidth = 136*0.8f;
    	float titleHeight = titleWidth*title.getRegionHeight()/title.getRegionWidth();
    	
        batcher.draw(title, 136 / 2 - titleWidth/2, midPointY - 60,titleWidth,titleHeight);

        for (SimpleButton button : menuButtons) {
            button.draw(batcher, 1);
        }

    }
    
//    private void drawScoreboard(float runTime) {
//    	
//		batcher.draw(scoreboard, 22, midPointY - 59, 97, 118);
//		
//		drawPrize(PrizeHandler.getFinalPrize(), runTime);
//		
//		
//
//		int length = ("" + ScoreManager.getScore()).length();
//		AssetLoader.font.draw(batcher, "" + ScoreManager.getScore(),37 - (2 * length), midPointY - 49);
//
//		int lengthHS = ("" + AssetLoader.getHighScore()).length();
//		AssetLoader.font.draw(batcher, "" + AssetLoader.getHighScore(),94 - (2 * lengthHS), midPointY - 49);
//
//	}
    
    
    private void drawPrize(Prize p, float runTime) {
    	
    	if (p != null) {
    		
    		
    	String rarity = p.getRarity();
    	drawRaritySprite(rarity);
    	
    	if (rarity == "legendary") {
    		batcher.draw(p.getSprite(), 27, (float)(1.5*Math.sin(5*runTime)) + midPointY-20, 80, 40);
    	}
    	else {
    		batcher.draw(p.getSprite(), 27,  midPointY-20, 80, 40);

    	}
		
		
		if (rarity == "common") {
			drawName(p.getName(), AssetLoader.whiteFont);
			
		}
		else if (rarity == "rare") {
			drawName(p.getName(), AssetLoader.blueFont);
			
		}
		else if (rarity == "epic") {
			drawName(p.getName(), AssetLoader.purpleFont);
			
		}
		else if (rarity == "legendary") {
			drawName(p.getName(), AssetLoader.orangeFont);
			
		}
		
    	}
    	
    }
    
    
    
    private void drawRaritySprite(String r) {
    	
    	if (r == "common") {
    		batcher.draw(common, 63, midPointY + 47, 38, 7);
    		
    	} else if (r == "rare") {
    		batcher.draw(rare, 63, midPointY + 47, 22, 7);
    		
    	} else if (r == "epic") {
    		batcher.draw(epic, 63, midPointY + 47, 21, 7);
    		
    	} else if (r == "legendary") {
    		batcher.draw(legendary, 63, midPointY + 47, 48, 7);

    		
    	} 
    		
    }
    
    private void drawName(String string, BitmapFont font) {
    	

		
    	if (string.contains("-")) {
    		String[] parts = string.split("-");

        	font.draw(batcher, parts[0], 60 - (2*parts[0].length()), midPointY+30);
        	font.draw(batcher, parts[1], 60 - (2*parts[1].length()), midPointY+37);
        	
    	} else {
    	    font.draw(batcher, string, 63 - (2*string.length()), midPointY+30);
    	}
    	
    	
    }

    
    
    
    
    private void drawScore() {
        int length = ("" + ScoreManager.getScore()).length();
        AssetLoader.shadow.draw(batcher, "" + ScoreManager.getScore(),68 - (3 * length), midPointY - 82);
        AssetLoader.font.draw(batcher, "" + ScoreManager.getScore(), 68 - (3 * length), midPointY - 83);
    }
    
    
    
    
    
    private void drawGOButtons() {
		for (SimpleButton button : GOButtons) {
            button.draw(batcher,1);
        }
	}

	private void drawReady() {
		batcher.draw(ready, 36, midPointY - 50, 68, 14);
	}

	private void drawGameOver() {
		batcher.draw(gameOver, 24, midPointY - 80, 92, 14);
	}
	
	private void drawHighScore() {
		batcher.draw(highScore, 22, midPointY - 80, 96, 14);
	}
    
    
    
	public void prepareTransition(int r, int g, int b, float duration) {
		transitionColor.set(r / 255.0f, g / 255.0f, b / 255.0f, 1);
		alpha.setValue(1);
		Tween.registerAccessor(Value.class, new ValueAccessor());
		manager = new TweenManager();
		Tween.to(alpha, -1, duration).target(0).ease(TweenEquations.easeOutQuad).start(manager);
		
	}

	private void drawTransition(float delta) {
	        if (alpha.getValue() > 0) {
	            manager.update(delta);
	            Gdx.gl.glEnable(GL20.GL_BLEND);
	            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
	            shapeRenderer.begin(ShapeType.Filled);
	            shapeRenderer.setColor(transitionColor.r, transitionColor.g, transitionColor.b, alpha.getValue());
	            shapeRenderer.rect(0, 0, 136, 300);
	            shapeRenderer.end();
	            Gdx.gl.glDisable(GL20.GL_BLEND);

	        }
	    }
		
	
	public CollectionMenu getCollectionMenu() {
		return collectionMenu;
	}
	
	public void setCollectionMenu() {
		collectionMenu = new CollectionMenu(batcher, cam, myWorld);
		collectionMenu.setInputProcessor();
	}
	
	public void setPibSelectMenu() {
		pibSelectMenu.setInputProcessor();
	}
	
	public void setSoundOptionsMenu() {
		
		soundOptionsMenu.setInputProcessor();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void initGameObjects() {
	        scroller = myWorld.getScroller();
	        frontGround = scroller.getFrontGround();
	        backGround = scroller.getBackGround();
	        pipe1 = scroller.getPipe1();
	        pipe2 = scroller.getPipe2();
	        pipe3 = scroller.getPipe3();   
	        
	        
	    }

	private void initAssets() {
//	        bg = AssetLoader.bg;
	        ground = AssetLoader.ground;
//	        birdDown = AssetLoader.birdDown;
//	        birdUp = AssetLoader.birdUp;
//	        skullUp = AssetLoader.pipeUp;
//	        skullDown = AssetLoader.pipeDown;
//	        bar = AssetLoader.bar;
	        ready = AssetLoader.ready;
			gameOver = AssetLoader.gameOver;
			highScore = AssetLoader.highScore;
			scoreboard = AssetLoader.scoreboard;
			title = AssetLoader.title;
			common = AssetLoader.common;
			rare = AssetLoader.rare;
			epic = AssetLoader.epic;
			legendary = AssetLoader.legendary;
	    }
	 
    private void drawHitBoxes() {
    	Bird bird = myWorld.getBird();
    	
    	 shapeRenderer.begin(ShapeType.Line);
	        shapeRenderer.setColor(Color.RED);
	        shapeRenderer.circle(bird.getBoundingCircle().x, bird.getBoundingCircle().y, bird.getBoundingCircle().radius);
	        
     shapeRenderer.rect(pipe1.getBarUp().x, pipe1.getBarUp().y,
             pipe1.getBarUp().width, pipe1.getBarUp().height);
     shapeRenderer.rect(pipe2.getBarUp().x, pipe2.getBarUp().y,
             pipe2.getBarUp().width, pipe2.getBarUp().height);
     shapeRenderer.rect(pipe3.getBarUp().x, pipe3.getBarUp().y,
             pipe3.getBarUp().width, pipe3.getBarUp().height);

     // Bar down for pipes 1 2 and 3
     shapeRenderer.rect(pipe1.getBarDown().x, pipe1.getBarDown().y,
             pipe1.getBarDown().width, pipe1.getBarDown().height);
     shapeRenderer.rect(pipe2.getBarDown().x, pipe2.getBarDown().y,
             pipe2.getBarDown().width, pipe2.getBarDown().height);
     shapeRenderer.rect(pipe3.getBarDown().x, pipe3.getBarDown().y,
             pipe3.getBarDown().width, pipe3.getBarDown().height);

     // Skull up for Pipes 1 2 and 3
     shapeRenderer.rect(pipe1.getSkullUp().x, pipe1.getSkullUp().y,
             pipe1.getSkullUp().width, pipe1.getSkullUp().height);
     shapeRenderer.rect(pipe2.getSkullUp().x, pipe2.getSkullUp().y,
             pipe2.getSkullUp().width, pipe2.getSkullUp().height);
     shapeRenderer.rect(pipe3.getSkullUp().x, pipe3.getSkullUp().y,
             pipe3.getSkullUp().width, pipe3.getSkullUp().height);

     // Skull down for Pipes 1 2 and 3
     shapeRenderer.rect(pipe1.getSkullDown().x, pipe1.getSkullDown().y,
             pipe1.getSkullDown().width, pipe1.getSkullDown().height);
     shapeRenderer.rect(pipe2.getSkullDown().x, pipe2.getSkullDown().y,
             pipe2.getSkullDown().width, pipe2.getSkullDown().height);
     shapeRenderer.rect(pipe3.getSkullDown().x, pipe3.getSkullDown().y,
             pipe3.getSkullDown().width, pipe3.getSkullDown().height);
     
     shapeRenderer.end();
    }

	    
}


