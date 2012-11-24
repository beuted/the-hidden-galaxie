package mapEditor;

import jeu.Map;
import jeu.Tuple;

public class GestionAffichage {
	
	private Map map;
	private EffetsCarteME effetsCarteME;
	private ListeBlocksME listeBlocksME;
	private ListeObjetsME listeObjetsME;
	private Tuple decalage = new Tuple(0,0);
	
	
	public GestionAffichage(Map map, EffetsCarteME effetsCarteME, ListeBlocksME listeBlocksME, ListeObjetsME listeObjetsME) {
		this.map = map;
		this.effetsCarteME = effetsCarteME;
		this.listeBlocksME = listeBlocksME;
		this.listeObjetsME = listeObjetsME;
		
	}
	
	public void affichage(Tuple coordCarte, ETAT_EDITOR etatEditor, Selection selec) {
		switch (etatEditor) {
		case EDITOR :
			map.afficherMap(coordCarte, decalage);
			effetsCarteME.afficherSelection(selec); break;
		case BLOCKS : 
			map.afficherMap(coordCarte, decalage);
			listeBlocksME.afficher(); break;
		case OBJETS :
			map.afficherMap(coordCarte, decalage);
			listeObjetsME.afficher(); break;
		}
	}
}
