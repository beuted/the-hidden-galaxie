package jeu;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tileset {
	
	Image image;
	int largeur;
	int startGameScale = 1;
	
	public Tileset(String url, int largeur) {
		// Chargement de l'image dans l'attribut image
		this.largeur = largeur;
		try {
			this.image = new Image(url);
		} catch (SlickException e) {
			e.printStackTrace();
			System.out.println("Erreur : Impossible de charger le Tileset " + url);
		}
		
	}
	
	// Méthode de dessin du tile numéro "numero" dans le contexte 2D "context" aux coordonnées xDestination et yDestination
	public void dessinerTile(int numTile, int posx, int posy)  {
		
		int xSourceEnTiles = numTile % this.largeur;
		//if(xSourceEnTiles == -1) xSourceEnTiles = this.largeur;
		int ySourceEnTiles = (int) (Math.floor(numTile / this.largeur));
		
		int xSource = (xSourceEnTiles) * 34;
		int ySource = (ySourceEnTiles) * 34;
		
		Image decoupe = this.image.getSubImage(xSource, ySource, 32, 32);
		decoupe.draw(posx, posy, startGameScale);
	}

}