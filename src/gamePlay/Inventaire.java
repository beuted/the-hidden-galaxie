package gamePlay;

import jeu.Case;
import jeu.Map;
import jeu.Tileset;
import jeu.Tuple;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Inventaire {
	private final int hauteurInv = 5;
	private final int largeurInv = 5;
	private final int tailleInventaire = hauteurInv*largeurInv;
	private Tuple tailleEcran;
	private Objet[] inventaire = new Objet[tailleInventaire];
	/* Remarque : un objet dans l'inventaire a coord a null */
	private SpriteSheet spriteSheet;
	private Tileset tilesetObjet;
	
	private int numObjetSelec = 1;
	
	public Inventaire(Tileset tilesetObjet, Tuple tailleEcran) {
		this.tailleEcran = tailleEcran;
		
		// TODO AVIRER ===================
		inventaire[0] = new Objet(null, 22);
		inventaire[2] = new Objet(null, 4);
		inventaire[5] = new Objet(null, 8);
		inventaire[24] = new Objet(null, 22);
		// ===============================
		this.tilesetObjet = tilesetObjet;
		
		try {
			 this.spriteSheet = new SpriteSheet("img/inventaire.png",32,32);
		} catch (SlickException e) {
			System.out.println("ERREUR : Impossible de charger inventaire.png");
			e.printStackTrace();
		}
	}
	
	/**
	 * Insere un objet dans l'inventaire en mettant son champ coord a null
	 */
	private boolean insererObjet(Objet obj) {
		for (int i = 0; i < tailleInventaire; i++) {
			if (inventaire[i] == null) {
				obj.coord = null;
				inventaire[i] = obj;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Fonction permettant de ramasser un objet pour le placer dans
	 * l'inventaire.
	 * @param coordObjet - coord de l'objet a ramasser
	 * @param map - carte du jeu
	 */
	public void ramasserObjet(Tuple coordObjet, Map map) {
		Case caseTemp = map.getCase(coordObjet.y, coordObjet.x);
		if (insererObjet(caseTemp.objet))
			caseTemp.objet = null;
	}
	
	/**
	 * Fonction permettant de poser un objet de son inventaire.
	 * @param coordObjet - coord de l'objet a poser
	 * @param map - carte du jeu
	 * @param numObjet - numéro de l'objet dans l'inventaire
	 */
	public void poserObjet(Tuple coordObjet, Map map, int numObjet) {
		if (numObjet >= tailleInventaire || numObjet < 0)
			return;
		Case caseTemp = map.getCase(coordObjet.y, coordObjet.x);
		caseTemp.objet = inventaire[numObjet];
		inventaire[numObjet] = null;
	}
	
	/**
	 * Affiche l'inventaire et ce qu'il contient
	 * @param tailleEcran - taille de l'ecran en case
	 */
	public void afficher(Tuple tailleEcran) {
		//dessine le fond de l'inventaire
		spriteSheet.draw(tailleEcran.x/2*32 -120, tailleEcran.y/2*32-120);
		
		//dessine la selection d'un objet
		if (numObjetSelec >=0 && numObjetSelec < this.tailleInventaire) {
		int jtemp = numObjetSelec % 5;
		int itemp = (numObjetSelec - jtemp)/5;
		tilesetObjet.dessinerTile(99,tailleEcran.x/2*32 -62 + jtemp*36 , tailleEcran.y/2*32 -104 +itemp*36);
		}
		// dessine les objets
		for (int i = 0; i<5; i++) {
			for (int j = 0; j<5; j++) {
				if (inventaire[i*5+j] != null)
					tilesetObjet.dessinerTile(inventaire[i*5+j].numTile,tailleEcran.x/2*32 -62 + j*36 , tailleEcran.y/2*32 -104 +i*36);
			}
		}
	}
	
	/**
	 * @param mouse - coord de la sourie
	 * @return - Retourne le numéro de l'objet sur lequel la sourie est placé ou 
	 *  -1 si la sourie n'est sur aucun objet.
	 */
	public void majNumObjetSelec(Tuple mouse) {
		for (int i = 0; i<5; i++) {
			for (int j = 0; j<5; j++) {
				if (inventaire[i*5+j] != null) {
					Tuple pos = new Tuple(tailleEcran.x/2*32 -62 + j*36, tailleEcran.y/2*32 -104 +i*36);
					if(mouse.x >= pos.x && mouse.y >= pos.y && mouse.x <= pos.x+32 && mouse.y <= pos.y+32) {
						this.numObjetSelec = i*5+j;
						return;
					}
				}
			}
		}
		this.numObjetSelec =-1;
	}
	
	/**
	 * @return - le numéro de l'objet selectionné dans l'inventaire
	 */
	public int getNumObjetSelec() {
		return this.numObjetSelec;
	}
		
}
