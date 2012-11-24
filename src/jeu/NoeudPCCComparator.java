package jeu;

import java.util.Comparator;

/**
 * Comparateur d'evenement utilisé dans la file de priorité dans la
 * gestion des evenements
 *
 * @see GestionEvenements
 */
public class NoeudPCCComparator  implements Comparator<NoeudPCC> {
	
	@Override
	public int compare(NoeudPCC n1, NoeudPCC n2) {
		if (n1.cout_f < n2.cout_f) {
			return -1;
		} else if (n1.cout_f == n2.cout_f) {
			return 0;
		} else {
			return 1;
		}
	}
}
