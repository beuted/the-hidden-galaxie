package gamePlay;

import jeu.Map;
import jeu.PlusCourtChemin;
import jeu.Polices;
import jeu.Tuple;

import org.newdawn.slick.Image;


public class BoutonRamasser extends Bouton {
    
	public BoutonRamasser(Image imageUp, Image imageDown, Tuple coord) {
		super(imageUp, imageDown, coord);
	}
	
	public void afficher() {
		super.afficher();
		if (dessus)
			Polices.Verdana.drawString(coord.x+1, coord.y+1, "ramasser");
		else
			Polices.Verdana.drawString(coord.x, coord.y, "ramasser");
	}

	public void action(Tuple coordDepart, Tuple coordArrivee, Tuple tailleBoiteTravail, GestionEvenements gestEvenements, Map map, Inventaire inv) {
		if (PlusCourtChemin.allerA(coordDepart, coordArrivee, tailleBoiteTravail, gestEvenements, map))
			gestEvenements.addLastEvenement(new Ramassage(null));
	}
}
