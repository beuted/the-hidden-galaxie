package jeu;

public class NoeudPCC {
	public int cout_g, cout_h, cout_f;
	public Tuple parent;
	public Tuple coord;
	
	public NoeudPCC() {}
	
	public NoeudPCC(Tuple coord) {
		this.coord = new Tuple(coord.x, coord.y);
	}
	
	public NoeudPCC(Tuple coord, Tuple parent, int cout_g, int cout_h, int cout_f) {
		this.coord = new Tuple(coord.x, coord.y);
		this.parent = new Tuple(parent.x, parent.y);
		this.cout_g = cout_g;
		this.cout_h = cout_h;
		this.cout_f = cout_f;
	}
	
	
	
	public NoeudPCC clone() {
		return (new NoeudPCC(coord, parent, cout_g, cout_h, cout_f));
	}
}
