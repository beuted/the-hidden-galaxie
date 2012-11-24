package jeu;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

import gamePlay.DIRECTION;
import gamePlay.GestionEvenements;
import gamePlay.Deplacement;

public class PlusCourtChemin {
	
	private static NoeudPCC deja_dans_liste(Tuple t, AbstractCollection<NoeudPCC> list){
		Iterator<NoeudPCC> it = list.iterator();
		while (it.hasNext()) {
			NoeudPCC n = it.next();
			if (n.coord.equals(t)) {
				return n;
			}
		}
		return null;
	}
	
	
	private static void ajouter_cases_adjacentes(Tuple n, Tuple boiteTravailHG, Tuple boiteTravailBD, Tuple arrivee, PriorityQueue<NoeudPCC> listeOuverte, ArrayList<NoeudPCC> listeFermee, Map map) {
	    NoeudPCC tmp = new NoeudPCC();
	    /* on met tous les noeud adjacents dans la liste ouverte (+vérif) */
	    for (int i=n.x-1; i<=n.x+1; i++) {
	        if ((i<boiteTravailHG.x) || (i>boiteTravailBD.x))  /* en dehors de l'image, on oublie */
	            continue;
	        for (int j=n.y-1; j<=n.y+1; j++) {
	            if ((j<boiteTravailHG.y) || (j>boiteTravailBD.y))   /* en dehors de la partie de carte visible, on oublie */
	                continue;
	        	if(j-n.y != 0 && i-n.x != 0 )
	        		continue;
	        	if(j-n.y == 0 && i-n.x == 0 )
	        		continue;
                
	            if (!InfoCase.isPassable(map.getCase(j, i).numTile)) /* obstace, terrain non franchissable, on oublie */
	                continue;

	            tmp.coord = new Tuple(i,j);
	            Tuple it = new Tuple(i,j);
	            NoeudPCC noeud = deja_dans_liste(it, listeFermee);
	            if (noeud == null){ /* Si on pas deja etudier le noeud i, j */
	                /* On choppe le noeud en coord n (noeud centrale) */
		            noeud = deja_dans_liste(n, listeFermee);

	                /* calcul du cout G du noeud en cours d'étude : cout du parent + distance jusqu'au parent */
	                tmp.cout_g = noeud.cout_g + 1;//n.distance(new Tuple(i,j));  
	                /* calcul du cout H du noeud à la destination */
	                tmp.cout_h = arrivee.distance(new Tuple(i,j));
	                tmp.cout_f = tmp.cout_g + tmp.cout_h;
	                tmp.parent = n;
	                
	                noeud = deja_dans_liste(it, listeOuverte);
	                if (noeud != null) {
	                    /* le noeud est déjà présent dans la liste ouverte, il faut comparer les couts */
	                    if (tmp.cout_f < noeud.cout_f) {
	                        /* si le nouveau chemin est meilleur, on met à jour */
	                        listeOuverte.remove(noeud);
	                        noeud=tmp;
	                        listeOuverte.add(noeud);
	                    }

	                    /* le noeud courant a un moins bon chemin, on ne change rien */
	                } else {
	                    /* le noeud n'est pas présent dans la liste ouverte, on l'y ajoute */
	                	
	                    listeOuverte.add(tmp.clone());
	                }
	            }
	        }
	    }
	}
	
	private static void retrouver_chemin(ArrayList<NoeudPCC> listeFermee, Tuple coordArrivee, Tuple coordDepart, GestionEvenements gestEvenement){
	    /* l'arrivée est le dernier élément de la liste fermée */
	    NoeudPCC tmp = listeFermee.get(listeFermee.size()-1);

	    
	    Tuple n = new Tuple(coordArrivee.x,coordArrivee.y);
	    Tuple prec = new Tuple(tmp.parent.x,tmp.parent.y);

	    // tant qu'on arrive pas au premier
	    while (!prec.equals(coordDepart)){
	    	if (n.x-prec.x > 0) {
	    		gestEvenement.addFirstEvenement(new Deplacement(DIRECTION.DROITE));
	    	} else if (n.x-prec.x < 0) {
	    		gestEvenement.addFirstEvenement(new Deplacement(DIRECTION.GAUCHE));
	    	} else if (n.y-prec.y > 0) {
	    		gestEvenement.addFirstEvenement(new Deplacement(DIRECTION.BAS));
	    	} else if (n.y-prec.y < 0) {
	    		gestEvenement.addFirstEvenement(new Deplacement(DIRECTION.HAUT));
	    	} else if (!(n.y == prec.y && n.x == prec.x)){
	    		System.out.println("ERREUR : PlusCourtChemin est faux. on va de " + prec.x + ", " + prec.y + " a "+ n.x + ", " + n.y);
	    	}
	        
	        n.x = prec.x;
	        n.y = prec.y;

	        tmp = deja_dans_liste(tmp.parent, listeFermee);
	        prec.x  = tmp.parent.x;
	        prec.y = tmp.parent.y;
	    }
	    if (n.x-prec.x > 0) {
    		gestEvenement.addFirstEvenement(new Deplacement(DIRECTION.DROITE));
    	} else if (n.x-prec.x < 0) {
    		gestEvenement.addFirstEvenement(new Deplacement(DIRECTION.GAUCHE));
    	} else if (n.y-prec.y > 0) {
    		gestEvenement.addFirstEvenement(new Deplacement(DIRECTION.BAS));
    	} else if (n.y-prec.y < 0) {
    		gestEvenement.addFirstEvenement(new Deplacement(DIRECTION.HAUT));
    	} else if (!(n.y == prec.y && n.x == prec.x)){
    		System.out.println("ERREUR : PlusCourtChemin est faux. on va de " + prec.x + ", " + prec.y + " a "+ n.x + ", " + n.y);
    	}
	}

	
	
	
	/* Passe un noeud de la liste ouverte vers la liste fermée. */
	private static void ajouter_liste_fermee(Tuple t, PriorityQueue<NoeudPCC> listeOuverte, ArrayList<NoeudPCC> listeFermee){
		NoeudPCC noeud = deja_dans_liste(t, listeOuverte);
	    listeFermee.add(noeud);

	    /* il faut le supprimer de la liste ouverte, ce n'est plus une solution explorable */
	    if (!listeOuverte.remove(noeud))
	    	System.out.println("Impossible de supprimer le noeud il n'apparatient pas a la liste ouverte" );
	    return;
	}

	
	public static boolean allerA(Tuple coordDepart, Tuple coordArrivee, Tuple tailleBoiteTravail, GestionEvenements gestEvenements, Map map) {
		// Structures necessaires
		Comparator<NoeudPCC> comparator = new NoeudPCCComparator();
		PriorityQueue<NoeudPCC> listeOuverte = new PriorityQueue<NoeudPCC>(10, comparator);
		ArrayList<NoeudPCC> listeFermee = new ArrayList<NoeudPCC>();
		
		Tuple boiteTravailHG = new Tuple(coordDepart.x - tailleBoiteTravail.x/2, coordDepart.y - tailleBoiteTravail.y/2);
		Tuple boiteTravailBD = new Tuple(boiteTravailHG.x+tailleBoiteTravail.x, boiteTravailHG.y+tailleBoiteTravail.y); 
		
		// Initialisation
		NoeudPCC depart = new NoeudPCC(coordDepart);
		depart.parent = new Tuple(coordDepart.x,coordDepart.y);
		
	    /* déroulement de l'algo A* */

	    /* initialisation du noeud courant */
	    Tuple courant = new Tuple(coordDepart.x,coordDepart.y);
	    
	    /* ajout de courant dans la liste ouverte */
	    listeOuverte.add(depart);
	    ajouter_liste_fermee(courant, listeOuverte, listeFermee);
	    ajouter_cases_adjacentes(courant, boiteTravailHG, boiteTravailBD, coordArrivee,  listeOuverte, listeFermee, map);
	    
	    int i = 0;
	    /* tant que la destination n'a pas été atteinte et qu'il reste des noeuds à explorer dans la liste ouverte */
	    while( !((courant.x == coordArrivee.x) && (courant.y == coordArrivee.y))
	            &&
	           (!listeOuverte.isEmpty()) //&& (i<20)
	         ){
	    	i++;
	        /* on cherche le meilleur noeud de la liste ouverte, on sait qu'elle n'est pas vide donc il existe */
	        NoeudPCC noeudCourant = listeOuverte.poll();
	        courant = noeudCourant.coord;
	        
	        /* on le passe dans la liste fermee, il ne peut pas déjà y être */
	        listeFermee.add(noeudCourant);

	        /* on recommence la recherche des noeuds adjacents */
	        ajouter_cases_adjacentes(courant, boiteTravailHG, boiteTravailBD, coordArrivee,  listeOuverte, listeFermee, map);
	    }

	    /* si la destination est atteinte, on remonte le chemin */
	    if ((courant.x == coordArrivee.x) && (courant.y == coordArrivee.y)){
	        retrouver_chemin(listeFermee, coordArrivee, coordDepart, gestEvenements);
	        return true;
	    }else{
	        /* pas de solution */
	    	return false;
	    }
	}
	
}
