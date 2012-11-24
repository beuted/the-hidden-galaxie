package jeu;

import java.awt.Font;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class Polices {
	private static Font font = new Font("Verdana", Font.BOLD, 12);
	public static UnicodeFont Verdana;
	
	static public void Initialise() {
		Verdana = new UnicodeFont(font);
		Verdana.getEffects().add(new ColorEffect(java.awt.Color.black));
	    Verdana.addAsciiGlyphs();
	    try {
			Verdana.loadGlyphs();
		} catch (SlickException e) {
			System.out.println("ERREUR : impossible de loadGlyphs pour la police Verdana");
			e.printStackTrace();
		}
	}
}
