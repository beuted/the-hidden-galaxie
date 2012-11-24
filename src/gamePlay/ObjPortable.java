package gamePlay;

import jeu.Tuple;

public class ObjPortable extends Objet{
	
	int defense;
	int attaque;

	public ObjPortable(Tuple coord, int numTile, int defense, int attaque) {
		super(coord, numTile);
		this.defense = defense;
		this.attaque = attaque;
	}

}
