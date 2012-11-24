package gamePlay;

import jeu.Map;

public abstract class Evenement {
	protected DIRECTION direction;
	
	public Evenement(DIRECTION direction) {
		this.direction = direction;
	}
	
	abstract void executer(Hero hero, Inventaire inv, Map map);
}