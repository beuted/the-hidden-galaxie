package gamePlay;

import jeu.Tuple;
import java.io.Serializable;

public class Objet extends Mobil implements Serializable {

	public int numTile;
	//protected SpriteSheet spriteSheet;
	//protected Image image;
	
	public Objet(Tuple coord, int numTile) {
		super(coord);
		this.numTile = numTile;
	}
	
	
	
}
