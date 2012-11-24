package gamePlay;

import jeu.Map;
import jeu.PlusCourtChemin;
import jeu.Polices;
import jeu.Tuple;

import org.newdawn.slick.Image;


public class BoutonRamasser extends Bouton {
	
	private Tuple caseCible;
	
	public BoutonRamasser(Image imageUp, Image imageDown, Tuple coord, Tuple caseCible) {
		super(imageUp, imageDown, coord);
		this.caseCible = caseCible;
	}
	
	public void afficher() {
		super.afficher();
		if (dessus)
			Polices.Verdana.drawString(coord.x+1, coord.y+1, "ramasser");
		else
			Polices.Verdana.drawString(coord.x, coord.y, "ramasser");
	}

	public void action(Tuple coordDepart, Tuple tailleBoiteTravail, GestionEvenements gestEvenements, Map map, Inventaire inv) {
		if (PlusCourtChemin.allerA(coordDepart, caseCible, tailleBoiteTravail, gestEvenements, map))
			gestEvenements.addLastEvenement(new EvtRamasser(null));
	}
}
