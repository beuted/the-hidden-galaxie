package gamePlay;

import jeu.Map;
import jeu.Polices;
import jeu.Tuple;

import org.newdawn.slick.Image;


public class BoutonPoser extends Bouton {
    
	private int numObjet;
	
	public BoutonPoser(Image imageUp, Image imageDown, Tuple coord, int numObjet) {
		super(imageUp, imageDown, coord);
		this.numObjet = numObjet;
	}
	
	public void afficher() {
		super.afficher();
		if (dessus)
			Polices.Verdana.drawString(coord.x+1, coord.y+1, "poser");
		else
			Polices.Verdana.drawString(coord.x, coord.y, "poser");
	}

	public void action(Tuple coordDepart, Tuple tailleBoiteTravail, GestionEvenements gestEvenements, Map map, Inventaire inv) {
		gestEvenements.addLastEvenement(new EvtPoser(null, numObjet));
	}
}
