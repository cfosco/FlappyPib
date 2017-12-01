package com.FoscusGames.gameprizes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.FoscusGames.fpHandlers.AssetLoader;
import com.FoscusGames.fpHandlers.FPConstants;
import com.FoscusGames.gameobjects.Bird;
import com.FoscusGames.gameobjects.Prize;
import com.FoscusGames.ui.PrizeHolder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PrizeHandler {
	
	
	private static int legendaryCst = 200;
	private static int epicCst = 50;
	private static int rareCst = 20;
	private static int nothingCst = 3;
	
	
	public static Prize nothing;
	public static List<Prize> generalPrizes;
	private static List<PrizeHolder> acquiredPrizeHolders;
	private static int totalPrizes = FPConstants.prizeQuantity;

	
	private static boolean acquireAllPrizes = FPConstants.acquireAllPrizesDebug;
	
	
	private static Prize finalPrize;
	
	public static void initGeneralPrizes() {
		
		acquiredPrizeHolders = new ArrayList<PrizeHolder>();
		
		generalPrizes = new ArrayList<Prize>();
		
		nothing= new CommonPrize(AssetLoader.nothing, "Nothing. You Suck.", "Yeah, this is a prize too. Congratulations. Fuck off.");
		setAcquired(nothing);
		
		generalPrizes.add( new CommonPrize(AssetLoader.fap, "F.A.P. Membership Card", "Shows that you belong to the most random group in whatsapp."));
		generalPrizes.add( new CommonPrize(AssetLoader.potato, "A Potato", "Good Job. Here's a smiling potato for you."));
		generalPrizes.add( new CommonPrize(AssetLoader.moustache, "Fancy Moustache", "A cool little moustache. Allows you to join the Moustache Brozeurs."));
		generalPrizes.add( new CommonPrize(AssetLoader.dickbutt, "DickButt", "It's an actual fucking DickButt."));
		generalPrizes.add( new CommonPrize(AssetLoader.banana, "A Banana", "Banana for scale."));
		generalPrizes.add( new RarePrize(AssetLoader.s3, "S3 Badge", "It's fucking ugly, but you can pin it to your jacket!"));
		generalPrizes.add( new LegendaryPrize(AssetLoader.wiips, "Fully working Wii Power Supply ", "The unobtainable functioning Wii power supply. Yes, you finally got it."));
		generalPrizes.add( new EpicPrize(AssetLoader.bewbs, "Huge pair of Bewbs", "I'm starting to like this game"));
		generalPrizes.add( new EpicPrize(AssetLoader.approval, "MultiApproval chain of approvals", "It only needs one thumbs up to start. If you receive it, you are officialy Approved."));
		generalPrizes.add( new LegendaryPrize(AssetLoader.taladro, "The PowerDrill", "GIGADRILUUUUUUUUUUUUUUUUUUAAAAAAAAAAAHHHHHHHHHHHHHH! WHO THE HELL DO YOU THINK I AM!!!!"));
		generalPrizes.add( new RarePrize(AssetLoader.pila, "A Battery", "Congratulations, it took you less than a week to obtain it. Go play some Wii without fear, my son."));
		generalPrizes.add( new RarePrize(AssetLoader.laser, "A Laser", "It's just a nice little laser. Pew pew."));
		generalPrizes.add( new LegendaryPrize(AssetLoader.hl3, "Half Life 3 confirmed", "It's unbelievable. By playing this game, you have confirmed that Half Life 3 will confirm that it's not confirmed."));
		generalPrizes.add( new CommonPrize(AssetLoader.moneda, "A Coin", "You're a little bit richer!"));
		generalPrizes.add( new RarePrize(AssetLoader.papel, "Toilet Paper", "You're saved."));
		generalPrizes.add( new EpicPrize(AssetLoader.puta, "Putas Token", "You can use these to pay for Putas. Fuck yeah. Not in real life, though."));



		
		for (Prize p : generalPrizes) {
			p.setGroup("general");
			
			setAcquired(p);

			if (acquireAllPrizes)
			acquiredPrizeHolders.add(buildPrizeHolder(p));
		}
		
	}
	
	public static void generatePrizes(Bird bird) {
		
		
		
		if (bird.getType() == "LePib") {
			
			List<Prize> lePibPrizes = new ArrayList<Prize>();
			
			lePibPrizes.add(new RarePrize(AssetLoader.milo,"Milo, LePib's Sneaky Cat", "You can only see him if he wants you to."));
			lePibPrizes.add(new EpicPrize(AssetLoader.ocgMouse,"LePib's Overly Complex Mouse", "This mouse exceeds complexity standards. It can only be operated by LePib."));
			lePibPrizes.add(new LegendaryPrize(AssetLoader.ortelli,"Guillermo Ortelli, LePib's Sidekick", "The incredible cardboard Ortelli. Likes to randomly appear at parties and have a drink with you."));
			
			bird.addPrizes(lePibPrizes);
			
			for (Prize p : lePibPrizes) {
				p.setGroup(bird.getType());
				
				setAcquired(p);

				if (acquireAllPrizes)
				acquiredPrizeHolders.add(buildPrizeHolder(p));
			}
			
			
		} else if (bird.getType() == "Notaro") {
		
			List<Prize> notaroPrizes = new ArrayList<Prize>();
			
			notaroPrizes.add(new RarePrize(AssetLoader.shirt,"Notaro's Corsarios Shirt", "The sweaty football shirt of Notaro. Beware."));
			notaroPrizes.add(new EpicPrize(AssetLoader.unendingList,  "Notaro's Unending List of Tv Shows", "He has watched them all."));
			notaroPrizes.add(new LegendaryPrize(AssetLoader.infPorn,"Notaro's Infinite Porn Stash", "A legendary artifact that contains all the pornography that will ever exist. Currently possesed by Notaro."));

			
			bird.addPrizes(notaroPrizes);
			
			for (Prize p : notaroPrizes) {
				p.setGroup(bird.getType());
				
				setAcquired(p);

				if (acquireAllPrizes)
				acquiredPrizeHolders.add(buildPrizeHolder(p));
			}
			
			
		} else if (bird.getType() == "Eze") {
			
			List<Prize> ezePrizes = new ArrayList<Prize>();
			
			ezePrizes.add(new RarePrize(AssetLoader.shitPC,"Eze's Shitty PC", "This PC can't even run Tetris, but it's awesome anyway."));
			ezePrizes.add(new EpicPrize(AssetLoader.flyboard,"Eze's Homemade Flyboard", "Directly from Chotilisima, the best flyboard you can make at home with 10 pesos and a lot of time to waste."));
			ezePrizes.add(new LegendaryPrize(AssetLoader.baby,"The Baby", "Ten pounds, half a meter. Eze's Legendary Baby."));

			bird.addPrizes(ezePrizes);
			
			for (Prize p : ezePrizes) {
				p.setGroup(bird.getType());
				
				setAcquired(p);

				if (acquireAllPrizes)
				acquiredPrizeHolders.add(buildPrizeHolder(p));
			}
			
		} else if (bird.getType() == "Tom") {
			
			List<Prize> tomPrizes = new ArrayList<Prize>();
			
			tomPrizes.add(new CommonPrize(AssetLoader.chivo,"Tom's Chivo Smell", "Specially brewed to knock out a dozen elephants."));
			tomPrizes.add(new EpicPrize(AssetLoader.jetpack, "Jetpack Sirio's Jetpack", "Need to travel fast to go spend the night gaming? Put this on and enjoy."));
			tomPrizes.add(new LegendaryPrize(AssetLoader.dax,"The Dax, Tom's Tiny Motorcycle", "Tom can magically transform this miniature motorcycle into an actual motor vehicle."));

			bird.addPrizes(tomPrizes);
			
			for (Prize p : tomPrizes) {
				p.setGroup(bird.getType());
				
				setAcquired(p);
				
				if (acquireAllPrizes)
				acquiredPrizeHolders.add(buildPrizeHolder(p));
			}
			
		} else if (bird.getType() == "Fedoco") {
			
			List<Prize> fedocoPrizes = new ArrayList<Prize>();
			
			fedocoPrizes.add(new RarePrize(AssetLoader.limon,"Fedoco's Lemon", "Lemon is for putos."));
			fedocoPrizes.add(new EpicPrize(AssetLoader.helmet, "Fedoco's \"nos vamos de putas\" helmet", "It is time. Tonight, we fuck those whores."));
			fedocoPrizes.add(new LegendaryPrize(AssetLoader.artifact,"Fedoco's time controlling artifact", "An ancient artifact that allows Fedoco to control time. He only uses it to have more time to play videogames."));

			bird.addPrizes(fedocoPrizes);
			
			for (Prize p : fedocoPrizes) {
				p.setGroup(bird.getType());
				
				setAcquired(p);
				
				if (acquireAllPrizes)
				acquiredPrizeHolders.add(buildPrizeHolder(p));
			}
			
		} else if (bird.getType() == "Cheiras") {
			
			List<Prize> cheirasPrizes = new ArrayList<Prize>();
			
			cheirasPrizes.add(new RarePrize(AssetLoader.dance,"Cheiras' dance moves", "Cheiras has been specially trained by aliens to be a perfect bachata dancer."));
			cheirasPrizes.add(new EpicPrize(AssetLoader.kipa, "Cheiras' Kipa", "Shabbat shalom!"));
			cheirasPrizes.add(new LegendaryPrize(AssetLoader.prepu,"Cheiras' Legendary Foreskin", "Legend says that in the dawn of time, an epic battle raged where Cheiras' foreskin was sealed away to stop him from conquering the jewniverse."));

			bird.addPrizes(cheirasPrizes);
			
			for (Prize p : cheirasPrizes) {
				p.setGroup(bird.getType());
				
				setAcquired(p);
				
				if (acquireAllPrizes)
				acquiredPrizeHolders.add(buildPrizeHolder(p));
			}
			
		} else if (bird.getType() == "Reimon") {
			
			List<Prize> reimonPrizes = new ArrayList<Prize>();
			
			reimonPrizes.add(new RarePrize(AssetLoader.agua,"Salvation Water", "Reimon can use water to cure himself from all alcohol induced debuffs."));
			reimonPrizes.add(new EpicPrize(AssetLoader.shots, "Destructive Shot Streak", "Reimon's way of sharing. Serve 11 shots, drink them all."));
			reimonPrizes.add(new LegendaryPrize(AssetLoader.bordemort,"Bordemort, Reimon's Nemesis", "Beware. The border is near."));

			bird.addPrizes(reimonPrizes);
			
			for (Prize p : reimonPrizes) {
				p.setGroup(bird.getType());
				
				setAcquired(p);
				
				if (acquireAllPrizes)
				acquiredPrizeHolders.add(buildPrizeHolder(p));
			}
			
		} else if (bird.getType() == "Santocho") {
			
			List<Prize> santiPrizes = new ArrayList<Prize>();
			
			santiPrizes.add(new RarePrize(AssetLoader.guitarra,"Santocho's Guitar", "Rocking his way to the top."));
			santiPrizes.add(new EpicPrize(AssetLoader.madera, "Santocho's Wii Controller", "Santocho has the ability to use this shitty wooden board as a functional wii controller. And still win."));
			santiPrizes.add(new LegendaryPrize(AssetLoader.camioneta,"The Camioneta", "The incredible Francia's Camioneta. Has transported more weird things that you can imagine. Including a destroyed french tourist."));

			bird.addPrizes(santiPrizes);
			
			for (Prize p : santiPrizes) {
				p.setGroup(bird.getType());
				
				setAcquired(p);
				
				if (acquireAllPrizes)
				acquiredPrizeHolders.add(buildPrizeHolder(p));
			}
			
		} else if (bird.getType() == "Carlinho") {
			
			List<Prize> carloPrizes = new ArrayList<Prize>();
			
			carloPrizes.add(new RarePrize(AssetLoader.maths,"Carlinho's Math Book", "This guy studies some hardcore shit."));
			carloPrizes.add(new EpicPrize(AssetLoader.mari, "Carlinho's Ever- Growing Marihuana Plant", "It never stops growing. It never stops producing. Carlinho takes great care of it."));
			carloPrizes.add(new LegendaryPrize(AssetLoader.calm,"Carlinho's Legendary Calm", "Carlinho lives in a constant state of transcendent calm. It gives him the ability to study mathematics and kick your ass at Smash."));

			bird.addPrizes(carloPrizes);
			
			for (Prize p : carloPrizes) {
				p.setGroup(bird.getType());
				
				setAcquired(p);
				
				if (acquireAllPrizes)
				acquiredPrizeHolders.add(buildPrizeHolder(p));
			}
			
		} 
		
		else {
			
			List<Prize> nullList = new ArrayList<Prize>();
					
			nullList.add(null);
			
			bird.addPrizes(nullList);
		}
		
	}
	
	public static List<Prize> getPrizesByRarity(List<Prize> prizeList, String rarity) {
		
		List<Prize> list = new ArrayList<Prize>();
		
		for (Prize p : prizeList) {				
			if (p != null) {
				if (p.getRarity() == rarity) {
					list.add(p);
				}
			}
		}
		
		if (list.size() ==0) {
			list.add(null);
		}
		
		return list;
	}
	
	public static List<PrizeHolder> getHoldersByRarity(List<PrizeHolder> prizeList, String rarity) {
		
		List<PrizeHolder> list = new ArrayList<PrizeHolder>();
		
		for (PrizeHolder p : prizeList) {				
			if (p != null) {
				if (p.getPrize().getRarity() == rarity) {
					list.add(p);
				}
			}
		}
		
		if (list.size() ==0) {
			list.add(null);
		}
		
		return list;
		

		
	}
	

	public static void determinePrize(Bird bird, int score) {

		Random r = new Random();
		
		float f = r.nextFloat();
		//Gdx.app.log("PrizeHandler", "score/lC = "+ (float)score/legendaryCst + " , score/eC = "+ (float)score/epicCst + " , score/rC = "+ (float)score/rareCst +" , f = "+f);
		
		List<Prize> finalPrizeList = new ArrayList<Prize>();
		finalPrizeList.addAll(generalPrizes);
		finalPrizeList.addAll(bird.getPrizes());
		
		
		
		if (score < nothingCst) {
			finalPrize = nothing;
		}
		
		else if(f < (float)score/legendaryCst) {
			finalPrize = getRandomPrize(getPrizesByRarity(finalPrizeList, "legendary"));
		} 
		
		else if(f < (float)score/epicCst) {
			finalPrize = getRandomPrize(getPrizesByRarity(finalPrizeList, "epic"));
		}

		else if(f < (float)score/rareCst) {
			finalPrize = getRandomPrize(getPrizesByRarity(finalPrizeList, "rare"));
		}
		
		else {
			
				finalPrize = getRandomPrize(getPrizesByRarity(finalPrizeList, "common"));
		}
		
		
		if (finalPrize != null ) {
			
			if(!finalPrize.gotIt()) {
				
				finalPrize.acquired();
				acquiredPrizeHolders.add( buildPrizeHolder(finalPrize) );
				AssetLoader.savePrize(finalPrize.getName());
				
				if (acquiredPrizeHolders.size()==totalPrizes) {
					FPConstants.allPrizesAcquired = true;
				}
			}
			
		}
		
	}
	
	public static Prize getFinalPrize() {
		
		return finalPrize;
	}
	
	public static List<PrizeHolder> getAcquiredPrizeHolders() {
		return acquiredPrizeHolders;
	}
	
	private static Prize getRandomPrize(List<Prize> prizeList) {
		

		Random r = new Random();
		return prizeList.get(r.nextInt(prizeList.size()));
	}
	
	public static void setAcquired (Prize p) {
		if (AssetLoader.prizeAcquired(p.getName())){
				p.acquired();
				acquiredPrizeHolders.add( buildPrizeHolder(p) );
				
		}
	}
	
	
	public static PrizeHolder buildPrizeHolder (Prize p) {

		
		
		//int currentPrizeHolderQuantity = acquiredPrizeHolders.size();
		//return new PrizeHolder(p, skin, sideMargin, sideMargin + (currentPrizeHolderQuantity*(prizeHolderHeight+margin)),  prizeHolderWidth, prizeHolderHeight);
		if(p!=null){
		if (p.getRarity()== "legendary") return new PrizeHolder(p, AssetLoader.legendarySkin);
		if (p.getRarity()== "epic") return new PrizeHolder(p, AssetLoader.epicSkin);
		if (p.getRarity()== "rare") return new PrizeHolder(p, AssetLoader.rareSkin);
		if (p.getRarity()== "common") return new PrizeHolder(p, AssetLoader.commonSkin);
		return null;
		}
		else return null;

	}
	
	public static int getTotalPrizes() {
		return totalPrizes;
	}

}
