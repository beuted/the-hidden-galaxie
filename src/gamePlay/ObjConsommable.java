package gamePlay;

import jeu.Tuple;

public class ObjConsommable extends Objet{
	
	int vieRendue;

	public ObjConsommable(Tuple coord, int numTile, int vieRendue) {
		super(coord, numTile);
		this.vieRendue = vieRendue;
	}

}
