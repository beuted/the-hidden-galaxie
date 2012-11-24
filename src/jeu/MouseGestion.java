package jeu;

public class MouseGestion {
	static public boolean inside(Tuple mouse, int posX, int posY, int largeur, int hauteur) {
		 return(( mouse.x >= posX && mouse.x <= posX + largeur) && 
				 ( mouse.y >= posY && mouse.y <= posY + hauteur));
	}
}
