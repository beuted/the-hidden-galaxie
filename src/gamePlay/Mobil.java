package gamePlay;

import java.io.Serializable;

import jeu.Tuple;

public class Mobil implements Serializable {
	protected Tuple coord;
	
	Mobil(Tuple coord) {
		this.coord = coord;
	}
	
	public Tuple getCoord() {
		return this.coord;
	}
	
	public void setCoord(int x, int y) {
		this.coord.x = coord.x;
		this.coord.y = coord.y;
	}
}
