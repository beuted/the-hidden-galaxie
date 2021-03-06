package gamePlay;

import java.util.ArrayList;
import java.util.Iterator;

import jeu.Map;
import jeu.Tuple;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class BoiteAction {
	
	private Image boiteAction;
	private Image bordHaut;
	private Image boite;
	private Image bordBas;
	private Image boutonUp;
	private Image boutonDown;
	
	private Hero hero;
	private Tuple tailleEcran;
	private GestionEvenements gestEvenements;
	private Map map;
	private Inventaire inventaire;
	
	//private Tuple caseCible = new Tuple(0,0);  //Case au sens de ce qui s'affiche a l'ecran
	private Tuple coordBoite = new Tuple(0,0); // coord en pixel de la boite sur l'ecran
	private ArrayList<Bouton> listeBoutons = new ArrayList<Bouton>(nbMaxAction);
	
	//Constantes
	private static final int hauteurBord   = 6;
	private static final int largeurBoite  = 3*32;
	private static final int largeurBouton = 64;
	private static final int hauteurBouton = 18;
	private static final int nbMaxAction   = 3;
	private static final Inventaire Inventaire = null;
	
	public BoiteAction(Hero hero, Tuple tailleEcran, GestionEvenements gestEvenements, Map map, Inventaire inventaire) {
		
		this.hero = hero;
		this.tailleEcran = tailleEcran;
		this.gestEvenements = gestEvenements;
		this.map = map;
		this.inventaire = inventaire;
		
		try {
			boiteAction = new Image("img/boite_action.png");
		} catch (SlickException e) {
			System.out.println("ERREUR : Impossible de charger boite_action.png;");
			e.printStackTrace();
		}
		bordHaut = boiteAction.getSubImage(0, 0, largeurBoite, hauteurBord );
		boite = boiteAction.getSubImage(0, hauteurBord , largeurBoite, 32);
		bordBas = boiteAction.getSubImage(0, hauteurBord +32, largeurBoite, hauteurBord );
		boutonUp = boiteAction.getSubImage(0, 2*hauteurBord +32, largeurBouton, hauteurBouton);
		boutonDown = boiteAction.getSubImage(0, 2*hauteurBord +32 +hauteurBouton, largeurBouton, hauteurBouton);
	}
	
	public void afficher() {
		int i;
		
		bordHaut.draw(coordBoite.x, coordBoite.y);
		for (i = 0; i < listeBoutons.size(); i++) {
			boite.draw(coordBoite.x, coordBoite.y + i*32+6);
			listeBoutons.get(i).afficher();
		}
		bordBas.draw(coordBoite.x, coordBoite.y+i*32+6);
	}
	
	/**
	 * Dans le cas d'un click sur la map on retient la case cible
	 * On ajoute aussi les boutons possibles.
	 * @param caseCible -
	 * @param ramasser - 
	 */
	public void setCaseCible(Tuple mouse, boolean ramasser, Tuple caseCible) {
		listeBoutons.clear();
		this.coordBoite.x = mouse.x;
		this.coordBoite.y = mouse.y;
		if (ramasser) {
			Tuple coordBouton = new Tuple(coordBoite.x+16 ,coordBoite.y+6+7);
			listeBoutons.add(new BoutonRamasser(boutonUp, boutonDown, coordBouton, caseCible));
		}
		//int nbBoutons = listeBoutons.size();
	}
	
	/**
	 * Dans le cas d'un click sur l'inventaire on retient l'objet cible
	 * On ajoute aussi les boutons possibles.
	 * @param objetInvCible 
	 * @param caseCible -
	 * @param ramasser - 
	 */
	public void setObjetInvCible(Tuple mouse, boolean poser, int objetInvCible) {
		listeBoutons.clear();
		//inventaire.
		if (poser) {
			this.coordBoite.x = mouse.x;
			this.coordBoite.y = mouse.y;
			Tuple coordBouton = new Tuple(coordBoite.x+16 ,coordBoite.y+6+7);
			listeBoutons.add(new BoutonPoser(boutonUp, boutonDown, coordBouton, objetInvCible));
		}
		//int nbBoutons = listeBoutons.size();
	}
	
	
	/**
	 * Actualise la variable int boutonSelec qui va permettre d'afficher le bouton comme
	 * enfoncé ou non entre autre
	 * @param mouse - coord de la sourie;
	 */
	public void checkBoutonSelec(Tuple mouse) {
		Iterator<Bouton> it = listeBoutons.iterator();
		while (it.hasNext()) {
			Bouton b = it.next();
			b.setDessus(mouse);
		}
	}
	
	/**
	 * Fonction gérant un click de la sourie dans l'état BOITE_ACTION
	 */
	public void click() {
		Iterator<Bouton> it = listeBoutons.iterator();
		while (it.hasNext()) {
			Bouton b = it.next();
			if(b.getDessus()) {
				b.action(hero.getCoord(), tailleEcran, gestEvenements, map, inventaire);
			}
		}
	}
}
