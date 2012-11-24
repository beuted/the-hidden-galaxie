package gamePlay;

import jeu.Map;


public class EvtDeplacement extends Evenement {
	
	public EvtDeplacement(DIRECTION direction) {
		super(direction);
	}
	
	void executer(Hero hero, Inventaire unused, Map unused1) {
		hero.setDirection(direction);
		hero.setIsRunning(true);
		hero.incrementeDecalage(direction);
		switch (this.direction) {
		case HAUT:
			//coordCarte.plusNy(-1);
			hero.coord.y--; break;
		case BAS:	
			//coordCarte.plusNy(1);
			hero.coord.y++; break;
		case DROITE:	
			//coordCarte.plusNx(1);
			hero.coord.x++; break;
		case GAUCHE:	
			//coordCarte.plusNx(-1);
			hero.coord.x--; break;
		}
			
	}
}
