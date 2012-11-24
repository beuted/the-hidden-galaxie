package jeu;

import java.io.Serializable;

public class Tuple implements Serializable {
	public int x;
	public int y;
	
	public Tuple (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setx(int x) {
		this.x = x;
	}
	
	public void sety(int y) {
		this.y = y;
	}
	
	public int getx() {
		return x;
	}
	
	public int gety() {
		return y;
	}
	
	public void plusNy(int n){
		y+=n;
	}
	
	public void plusNx(int n){
		x+=n;
	}
	
	public boolean isNull() {
		return (this.x == 0 && this.y == 0);
	}
	
	public void moduler(int periodeX, int periodeY) {
		this.x = MathPerso.mod(this.x, periodeX);
		this.y = MathPerso.mod(this.y, periodeX);
	}
	
	/* calcule la distance entre les points (x1,y1) et (x2,y2) */
	int distance(Tuple arrivee){
	    /* carre de la distance euclidienne */
	    return Math.abs(this.x-arrivee.x) + Math.abs(this.y-arrivee.y);
	}
	
	boolean equals(Tuple t) {
		return (t.x == this.x && t.y == this.y);
	}
	

	
}
