package gamePlay;

import jeu.Map;

public class EvtRamasser extends Evenement {

	public EvtRamasser(DIRECTION direction) {
		super(direction);
	}

	@Override
	void executer(Hero hero, Inventaire inv, Map map) {
		inv.ramasserObjet(hero.getCoord(), map);
	}

}
