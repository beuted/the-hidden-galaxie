package mapEditor;

import jeu.MouseGestion;
import jeu.Tileset;
import jeu.Tuple;

public class ListeObjetsME {
	Tileset tileset;
	
	public boolean dessus = false;
	private int dessusI = 0, dessusJ = 0;
	
	public ListeObjetsME(Tileset tileset) {
		this.tileset = tileset;
	}
	
	public void afficher() {
			// affichage des cases
			for(int i = 0; i <= 8 ; i++) {
				int y = i * 32;
				for(int j = 0 ; j <= 10 ; j++) {
					tileset.dessinerTile(j+11*i, j*32, y);
				}
			}
			// affichage de la selection
			if (dessus) 
				this.afficherSelection();
	}
	
	private void afficherSelection() {
		tileset.dessinerTile(99, dessusJ*32, dessusI*32);
	}
	
	public ETAT_EDITOR selection(Tuple mouse, boolean click, EffetsCarteME effetsCarteME, ETAT_EDITOR etatEditor, Selection selec) {
		for(int i = 0; i <= 8 ; i++) {
			int y = i * 32;
			for(int j = 0 ; j <= 10 ; j++) {
				if (MouseGestion.inside(mouse, j*32, y, 32, 32)) {
					dessus = true;
					dessusI = i;
					dessusJ = j;
					if ( click ) {
						dessus = false;
						selec.numTile = 11*i +j;
						selec.etatEditor = ETAT_EDITOR.OBJETS;
						return ETAT_EDITOR.EDITOR;
					}
					break;
				}
			}
		}
		return etatEditor;
	}
	
}
