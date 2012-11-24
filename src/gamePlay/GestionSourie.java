package gamePlay;

import org.newdawn.slick.Input;

import jeu.ETAT_JEU;
import jeu.InfoCase;
import jeu.Map;
import jeu.MouseGestion;
import jeu.PlusCourtChemin;
import jeu.Tileset;
import jeu.Tuple;

public class GestionSourie {
	
	Tileset tileset;
	private Tuple dessus = new Tuple(0,0);
	private Map map;
	private Hero hero;
	private Inventaire inventaire;
	private Tuple tailleEcran;
	private BoiteAction boiteAction;
	
	
	public GestionSourie(Tileset tileset, Map map, Hero hero, Inventaire inventaire, Tuple tailleEcran, BoiteAction boiteAction) {
		this.tileset = tileset;
		this.map = map;
		this.hero = hero;
		this.tailleEcran = tailleEcran;
		this.boiteAction = boiteAction;
		this.inventaire = inventaire;
	}
	
	
	public void afficher() {
    	this.afficherSelection();
	}
	
	private void afficherSelection() {
		tileset.dessinerTile(88, dessus.x*32, dessus.y*32);
	}
	
	
	/**
	 * Renvoie true si on click
	 * 
	 * @param mouseX - position X de la sourie
	 * @param mouseY - position Y de la sourie
	 */
	public void selection(Tuple mouse) {
		for(int i = 0; i <= 18 ; i++) {
			int y = i * 32;
			for(int j = 0 ; j <= 24 ; j++) {
				if (MouseGestion.inside(mouse, j*32, y, 32, 32)) {
					dessus.y = i;
					dessus.x = j;
					break;
				}
			}
		}
	}
	
	/**
	 * @return - la case ou se situe la sourie
	 */
	public Tuple getDessus() {
		return dessus;
	}
	
	/**
	 * Gere les click de la sourie
	 * @return 
	 */
	public ETAT_JEU gererClick(GestionEvenements gestEvenements, Input input, ETAT_JEU etatJeu) {
		// On créé dest qu'on utilise bcp par la suite
		// TODO forcement un truc mieux a faire ici
		Tuple coordMap = new Tuple(hero.getCoord().x - tailleEcran.x/2, hero.getCoord().y - tailleEcran.y/2);
		Tuple dest = new Tuple(dessus.x + coordMap.x, dessus.y + coordMap.y);
		
		//On actualise le Tuple "dessus"
		Tuple mouse = new Tuple(input.getMouseX(),input.getMouseY());
		this.selection(mouse);   
		
		boolean clickG = input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
		boolean clickD = input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON);
		
		switch (etatJeu) {
		case JEU :
			if (clickG && gestEvenements.isEmpty()) {
				if (InfoCase.isPassable(map.getCase(dest.y, dest.x).numTile))
					PlusCourtChemin.allerA(hero.getCoord(), dest, tailleEcran, gestEvenements, map);
			}
		
			if( clickD && gestEvenements.isEmpty()) {
				boolean ramasser = (map.getCase(dest.y, dest.x).objet != null);
				/* Si il y a au moins un bouton a true */
				if (ramasser) {
					boiteAction.setCaseCible(mouse, ramasser, dest);
					return ETAT_JEU.BOITE_ACTION;
				}
			}
			break;
			
		case BOITE_ACTION :		
			boiteAction.checkBoutonSelec(mouse);
			if ((clickD || clickG) && gestEvenements.isEmpty()) {
				boiteAction.click();
				return ETAT_JEU.JEU;
			}
			break;
			
		case INVENTAIRE :
			inventaire.majNumObjetSelec(mouse);
			if (clickD && gestEvenements.isEmpty()) {
				int objetInvCible = inventaire.getNumObjetSelec();
				boolean poser = (map.getCase(hero.getCoord().y, hero.getCoord().x).objet == null);
				/* Si il y a au moins un bouton a true */
				if (objetInvCible != -1 && poser == true) {
					boiteAction.setObjetInvCible(mouse, poser, objetInvCible);
					return ETAT_JEU.BOITE_ACTION;
				}
			}
			break;
		}
		return etatJeu;
		
	}
	
}
