package jeu;

import gamePlay.Objet;

import java.io.Serializable;

public class Case implements Serializable {
	public int numTile;
	private Character perso;
	public Objet objet = null;
	
	public Case (int numTile) {
		this.numTile = numTile;
	}
}
