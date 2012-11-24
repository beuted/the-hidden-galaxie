package mapEditor;

import jeu.Map;
import jeu.MouseGestion;
import jeu.Tileset;
import jeu.Tuple;

public class EffetsCarteME {
	
	Tileset tileset;
	Tileset tilesetObjet;
	private int dessusI = 0, dessusJ = 0;
	
	
	public EffetsCarteME(Tileset tileset, Tileset tilesetObjet) {
		this.tileset = tileset;
		this.tilesetObjet = tilesetObjet;
	}
	
	
	public void afficherSelection(Selection selec) {
		switch(selec.etatEditor) {
			case BLOCKS : tileset.dessinerTile(selec.numTile, dessusJ*32, dessusI*32);
				break;
			case OBJETS : tilesetObjet.dessinerTile(selec.numTile, dessusJ*32, dessusI*32);
				break;
		}
	}
	
	/**
	 * Renvoie true si on click
	 * 
	 * @param mouseX - position X de la sourie
	 * @param mouseY - position Y de la sourie
	 * @param click  - Nous dit si on est clické
	 * @param map    - map a modifier si click
	 */
	public void selection(Tuple mouse, boolean click, Map map,Tuple coordCarte, Selection selec) {
		for(int i = 0; i <= 18 ; i++) {
			int y = i * 32;
			for(int j = 0 ; j <= 24 ; j++) {
				if (MouseGestion.inside(mouse, j*32, y, 32, 32)) {
					dessusI = i;
					dessusJ = j;
					if ( click ) {
						if (selec.etatEditor == ETAT_EDITOR.BLOCKS) 
							map.ModifierMap(i+coordCarte.y, j+coordCarte.x, selec.numTile);
						else
							map.ModifierObjetMap(i+coordCarte.y, j+coordCarte.x, selec.numTile);
					}
					break;
				}
			}
		}
	}
	
}
