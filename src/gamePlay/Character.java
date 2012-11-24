package gamePlay;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import jeu.Tuple;

public class Character extends Mobil {
	
	protected SpriteSheet spriteSheet;
	
	protected Image reposH;
	protected Image reposG;
	protected Image reposD;
	protected Image reposB;
	protected Animation courirH;
	protected Animation courirB;
	protected Animation courirD;
	protected Animation courirG;
	protected boolean isRunning = true;
	protected DIRECTION direction = DIRECTION.HAUT;
	
	protected Tuple decalage = new Tuple(0,0); // decalage entre deux cases
	protected int pasDecalage = 4;
	
	protected int vie;
	protected int force;
	
	public Character(SpriteSheet spriteSheet, Tuple coord, int vie, int force) {
		super(coord);
		this.spriteSheet = spriteSheet;
		
		this.reposH = spriteSheet.getSprite(0, 0);
		this.reposD = spriteSheet.getSprite(2, 0);
		this.reposG = spriteSheet.getSprite(5, 0);
		this.reposB = spriteSheet.getSprite(6, 0);
		this.courirH = new Animation(spriteSheet, 0,0,1,0,true, 150, true);
		this.courirD = new Animation(spriteSheet, 2,0,3,0,true, 150, true);
		this.courirG = new Animation(spriteSheet, 4,0,5,0,true, 150, true);
		this.courirB = new Animation(spriteSheet, 6,0,7,0,true, 150, true);
		
		this.vie = vie;
		this.force = force;
		
	}
	
	public void setDirection (DIRECTION d) {
		this.direction = d;
	}
	
	public void setIsRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	/**
	 * Recupere le decalage entre deux case qu'a le perso
	 * @return
	 */
	public Tuple getDecalage() {
		return this.decalage;
	}
	
	/**
	 * Incremente le decalage d'une direction de 64 pour
	 * compenser le chgt de case.
	 * @param d - direction deplacement
	 */
	public void incrementeDecalage(DIRECTION d) {
		if (d != null) {
			switch (d) {
			case HAUT :   setDecalage(0,-32);break; 
			case BAS :    setDecalage(0,32);break; 
			case DROITE : setDecalage(32,0);break; 
			case GAUCHE : setDecalage(-32,0);break; 
			}
		}
	}
	
	private void setDecalage(int x, int y) {
		this.decalage.x = x;
		this.decalage.y = y;
	}
	
	/* Mise a jour du decalage */
	public void majDecalage() {	
		
		if (decalage.x > 0)
			decalage.plusNx(-pasDecalage);
		else if (decalage.x < 0)
			decalage.plusNx(pasDecalage);
		else if (decalage.y > 0)
			decalage.plusNy(-pasDecalage);
		else if (decalage.y < 0)
			decalage.plusNy(pasDecalage);
	}
	
}
