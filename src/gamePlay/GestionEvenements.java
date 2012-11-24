package gamePlay;

import java.util.Deque;
import java.util.LinkedList;

import jeu.Map;

public class GestionEvenements {
	
	private Inventaire inventaire;
	private Map map;
	private Deque<Evenement> pileEvenement;
	
	public GestionEvenements(Map map, Inventaire inventaire) {
		pileEvenement = new LinkedList<Evenement>();
		this.map = map;
		this.inventaire = inventaire;
	}
	
	public void addFirstEvenement(Evenement e) {
		pileEvenement.addFirst(e);
	}
	
	public void addLastEvenement(Evenement e) {
		pileEvenement.addLast(e);
	}
	
	public boolean traiterEvenement(Hero hero, GestionAffichage gestAffichage) {
		if (!pileEvenement.isEmpty()) {
		(pileEvenement.pop()).executer(hero, inventaire, map);
		return true;
		}
		return false;
	}
	
	public boolean isEmpty() {
		return this.pileEvenement.isEmpty();
	}
	
}
