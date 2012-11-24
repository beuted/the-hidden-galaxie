package gamePlay;

import jeu.ETAT_JEU;
import jeu.SlickGame;

import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class GestionClavier {
	static public ETAT_JEU entreeClavier(Input input, ETAT_JEU etatJeu, StateBasedGame sbg) {
		
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			sbg.enterState(SlickGame.MAINMENUSTATE);
		}
		
		if(input.isKeyDown(Input.KEY_I))
		{
			if (etatJeu == ETAT_JEU.JEU)
				return ETAT_JEU.INVENTAIRE;
			else 
				return ETAT_JEU.JEU;
		}
		return etatJeu;
	}
	
}
