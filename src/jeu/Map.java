package jeu;


import gamePlay.Objet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Map implements Serializable{
	private int tailleEcranX = 25;
	private int tailleEcranY = 19;
	private int tailleCase;
	private Case[][] carte;     //
	private Tileset tileset;
	private Tileset tilesetObjet;
	
	
	
	public Map(Tileset tileset, Tileset tilesetObjet){
		this.tileset = tileset;
		this.tilesetObjet = tilesetObjet;
	}
	public int getTailleEcranY() {return tailleEcranY;}
	public int getTailleEcranX() {return tailleEcranX;}
	
	public int getTailleMapY() {return carte.length;}
	public int getTailleMapX() {return carte[0].length;}
	
	/**
	 * Permet de recuperer une case de la carte qui est circulaire
	 * @param i - abs
	 * @param j - ord
	 * @return  - La case i, j
	 */
	public Case getCase(int i, int j) {
		int newI = MathPerso.mod(i, carte.length);
		int newJ = MathPerso.mod(j, carte[0].length);
		return carte[newI][newJ];
	}
	
	
	/**
	 * Modifie une case de la carte.
	 * Leve une erreur si on sort de la carte
	 * 
	 * @param i    - abscisse de la case
	 * @param j    - ordonnee de la case
	 * @param tile - num du tile a mettr
	 * 
	 */
	public void ModifierMap(int i, int j, int tile) {
		int newI = MathPerso.mod(i, carte.length);
		int newJ = MathPerso.mod(j, carte[0].length);
		this.carte[newI][newJ].numTile = tile;

	}
	
	public void ModifierObjetMap(int i, int j, int tile) {
		int newI = MathPerso.mod(i, carte.length);
		int newJ = MathPerso.mod(j, carte[0].length);
		this.carte[newI][newJ].objet = new Objet(new Tuple(newI,newJ), tile);
	}
	
	/**
	 * Permet de charger un morceau de la carte pour pouvoir se ballader dessus
	 * @param nomMap - nom de la map a charger
	 */
	void chargerMap(String nomMap) {
		try {
			FileInputStream entreeDeFicher  = new FileInputStream(nomMap);
			ObjectInputStream entreeDObjet = new ObjectInputStream(entreeDeFicher);
			this.carte = (Case[][]) entreeDObjet.readObject();
			entreeDObjet.close();
        
		} catch (FileNotFoundException e) {
			e.printStackTrace(); // erreur a ratrapper !
        } catch (IOException e) {
        	e.printStackTrace(); // erreur a ratrapper !
        } catch (ClassNotFoundException e) {
			e.printStackTrace(); // erreur a ratrapper !
		}
        
        if (this.carte !=null) {
        	System.out.println("La carte " + nomMap + " a ete chargee." );
        }
        
    } 
	
	/**
	 * Permet de charger une carte vide
	 * @param nomMap - nom de la map a charger
	 */
	void chargerMapVierge(int taille) {
		
		this.carte = new Case[taille][taille];
		
		for (int i =0; i < taille; i++) {
			for (int j =0; j < taille; j++) {
				this.carte[i][j] = new Case(7);
			}
		}
        
        if (this.carte !=null) {
        	System.out.println("Une carte vierge a ete chargee." );
        }
        
    } 

	/**
	 * 
	 * @param x - coord du coin HG a partir du quel on veut afficher la carte (en pixel)
	 * @param y -
	 */
	public void afficherMap(Tuple HG, Tuple decal) {
		for(int i = HG.y-1, l = HG.y + this.tailleEcranY+1 ; i < l ; i++) {
			int y = (i-HG.y)*32;
			for(int j = HG.x-1, k = HG.x + tailleEcranX+1 ; j < k ; j++) {
				Case caseIJ = this.getCase(i,j);
			    this.tileset.dessinerTile(caseIJ.numTile, (j-HG.x)*32+ decal.x, y + decal.y);
			    if (caseIJ.objet != null)
			    	this.tilesetObjet.dessinerTile(caseIJ.objet.numTile, (j-HG.x)*32+ decal.x, y + decal.y);
			}
		}
	}
	
	/**
	 * Permet d'acceder a la carte (utile pour sauvegarder)
	 */
	public Case[][] getCarte() {
		return this.carte;
	}
}
