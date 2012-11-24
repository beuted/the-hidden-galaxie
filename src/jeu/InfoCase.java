package jeu;

public class InfoCase {
	
	
	public static boolean isPassable(int numTile) {
		switch (numTile) {
			case 1:case 2: case 7:case 8:case 9:case 10:
			case 18:case 19:case 20:case 21:
			case 25:case 26:case 27:case 29:case 30:case 31:case 32:return true;
		
			default: return false;
		}
	}
	
}
