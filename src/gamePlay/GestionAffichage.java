package gamePlay;

import jeu.ETAT_JEU;
import jeu.Map;
import jeu.Tuple;

public class GestionAffichage {
	private GestionSourie effetsCarteGP;
	private Map map;
	private Hero hero;
	private Inventaire inventaire;
	private Tuple tailleEcran;
	

	
	public GestionAffichage(Map map, Hero hero, Tuple tailleEcran, GestionSourie effetsCarteGP, Inventaire inventaire) {
		this.map = map;
		this.hero = hero;
		this.tailleEcran = tailleEcran;
		this.effetsCarteGP = effetsCarteGP;
		this.inventaire = inventaire;
		
	}
	
	/**
	 * Affiche tout ce qu'il y a à afficher dans l'etat GamePlay
	 * @param coordCarteX - coord de la carte
	 * @param coordCarteY - 
	 */
	public void affichage(Tuple coordCarte, ETAT_JEU etatJeu, BoiteAction boiteAction) {
		switch(etatJeu) {
		case JEU : 
			this.hero.majDecalage();
			this.map.afficherMap(coordCarte, this.hero.getDecalage());
			this.hero.afficher(tailleEcran);
			this.effetsCarteGP.afficher();break;
		case INVENTAIRE :
			this.map.afficherMap(coordCarte, this.hero.getDecalage());
			this.hero.afficher(tailleEcran);
			this.inventaire.afficher(tailleEcran);
			this.hero.majDecalage();break;
		case BOITE_ACTION :
			this.hero.majDecalage();
			this.map.afficherMap(coordCarte, this.hero.getDecalage());
			this.hero.afficher(tailleEcran);
			this.effetsCarteGP.afficher();
			boiteAction.afficher();break;
			
		default	: break;		
		}   	
	}
	
}