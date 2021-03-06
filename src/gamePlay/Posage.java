package gamePlay;

import jeu.Map;

public class Posage extends Evenement {

	private int numObjet; //Numéro de l'objet a poser
	
	public Posage(DIRECTION direction, int numObjet) {
		super(direction);
		this.numObjet = numObjet;
	}

	@Override
	void executer(Hero hero, Inventaire inv, Map map) {
		inv.poserObjet(hero.getCoord(), map, numObjet);
	}

}
