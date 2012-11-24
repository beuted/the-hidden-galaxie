package gamePlay;

import org.newdawn.slick.SpriteSheet;

import jeu.Tuple;

public class Hero extends Character {
	
	public Hero(SpriteSheet spriteSheet,Tuple coord, int vie, int force) {
		super(spriteSheet, coord, vie, force);
	}
	
	public void afficher(Tuple tailleEcran) {
		
		if(isRunning) {
			switch (direction) {
			case HAUT :courirH.draw(tailleEcran.x/2*32, tailleEcran.y/2*32);break;
			case BAS :courirB.draw(tailleEcran.x/2*32, tailleEcran.y/2*32);break;
			case GAUCHE :courirG.draw(tailleEcran.x/2*32, tailleEcran.y/2*32);break;
			case DROITE :courirD.draw(tailleEcran.x/2*32, tailleEcran.y/2*32);break;		
			}
		} else {
			switch (direction) {
			case HAUT :reposH.draw(tailleEcran.x/2*32,tailleEcran.y/2*32);break;
			case BAS :reposB.draw(tailleEcran.x/2*32,tailleEcran.y/2*32);break;
			case GAUCHE :reposG.draw(tailleEcran.x/2*32,tailleEcran.y/2*32);break;
			case DROITE :reposD.draw(tailleEcran.x/2*32,tailleEcran.y/2*32);break;		
			}
		}
	}
	
	
}
