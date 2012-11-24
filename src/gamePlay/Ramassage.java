package gamePlay;

import jeu.Map;

public class Ramassage extends Evenement {

	public Ramassage(DIRECTION direction) {
		super(direction);
	}

	@Override
	void executer(Hero hero, Inventaire inv, Map map) {
		inv.ramasserObjet(hero.getCoord(), map);
	}

}
