package gamePlay;

import jeu.Map;
import jeu.Tuple;

import org.newdawn.slick.Image;

public abstract class Bouton {
	private Image imageUp;
	private Image imageDown;
	protected Tuple coord;
	private int hauteur;
	private int largeur;
	protected boolean dessus;
	
	public Bouton(Image imageUp, Image imageDown, Tuple coord) {
		this.coord = coord;
		this.imageDown = imageDown;
		this.imageUp = imageUp;
		hauteur = imageUp.getHeight();
		largeur = imageUp.getWidth();
	}
	
	public void afficher() {
		if (dessus)
			imageDown.draw(coord.x, coord.y);
		else		
			imageUp.draw(coord.x, coord.y);
	}
	
	public void setDessus(Tuple mouse) {	
		this.dessus = (mouse.x >= coord.x && mouse.y >= coord.y 
				&& mouse.x <= coord.x + largeur && mouse.y <= coord.y + hauteur) ;
	}
	
	public boolean getDessus() {
		return dessus;
	}
	
	public abstract void action(Tuple coordDepart, Tuple tailleBoiteTravail, GestionEvenements gestEvenements, Map map, Inventaire inv);
}