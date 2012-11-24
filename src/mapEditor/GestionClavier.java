package mapEditor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import jeu.Case;
import jeu.Map;
import jeu.SlickGame;
import jeu.Tuple;

import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class GestionClavier {
	
	
	public static ETAT_EDITOR gererClavier(Input input, Tuple coordCarte, ETAT_EDITOR etatEditor, Map map, StateBasedGame sbg) {
		
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			sbg.enterState(SlickGame.MAINMENUSTATE);
		}
		
		if(input.isKeyDown(Input.KEY_S))
        {
        	try {
        	FileOutputStream sortieDeFicher = new FileOutputStream("maps/mapCM.sa");
            ObjectOutputStream sortieDObjet = new ObjectOutputStream(sortieDeFicher);
            Case[][] A = map.getCarte();
            sortieDObjet.writeObject(A);
            sortieDObjet.close();
            System.out.println("La carte a été sauvegardée");
        	} catch (FileNotFoundException ex) {
        		System.out.println("ERREUR : Impossible de sauvegarder la carte");
            // Que faire en cas d'erreur.
        	} catch (IOException ex) {
        		System.out.println("ERREUR : Impossible de sauvegarder la carte");
        		ex.printStackTrace(); 
        	}
        }
		switch(etatEditor) {
		case EDITOR :
			if(input.isKeyDown(Input.KEY_UP))
			{
				coordCarte.y--;
			}
			
			if(input.isKeyDown(Input.KEY_DOWN))
			{
				coordCarte.y++;
			}
			
			if(input.isKeyDown(Input.KEY_RIGHT))
			{
				coordCarte.x++;
			}
			
			if(input.isKeyDown(Input.KEY_LEFT))
			{
				coordCarte.x--;
			}
			if(input.isKeyDown(Input.KEY_E))
			{
				return ETAT_EDITOR.BLOCKS;
			} 
			else if(input.isKeyDown(Input.KEY_O))
			{
				return ETAT_EDITOR.OBJETS;
			} break;
			
		case BLOCKS :
			if(input.isKeyDown(Input.KEY_E))
			{
				return ETAT_EDITOR.EDITOR;
			} 
			else if(input.isKeyDown(Input.KEY_O))
			{
				return ETAT_EDITOR.OBJETS;
			} break;
			
		case OBJETS :
			if(input.isKeyDown(Input.KEY_E))
			{
				return ETAT_EDITOR.BLOCKS;
			}
			else if(input.isKeyDown(Input.KEY_O))
			{
				return ETAT_EDITOR.EDITOR;
			} break;
			
		default :
			break;
		}
		return etatEditor;
	}
	
}
