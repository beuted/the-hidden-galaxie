package jeu;

import gamePlay.BoiteAction;
import gamePlay.GestionSourie;
import gamePlay.GestionAffichage;
import gamePlay.GestionEvenements;
import gamePlay.Hero;
import gamePlay.GestionClavier;
import gamePlay.Inventaire;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Input;
 
public class GamePlayState extends BasicGameState {
	
	private Tileset tileset = new Tileset("img/link-donjon.png",11);
	private Tileset tilesetObjet = new Tileset("img/objets.png",11);
	//Gestion des evenements
	private GestionEvenements gestEvenement;
	
	//Hero
	private Hero hero;
	
	//Inventaire
	private Inventaire inventaire;
	
	//Carte
	private Map map = new Map(tileset, tilesetObjet);
	private Tuple coordCarte = new Tuple(0,0);
	private Tuple tailleEcran;
	
	//Gestion de l'affichage
	private GestionAffichage gestAffichage;
	private BoiteAction boiteAction;
	private GestionSourie gestionSourie;
	private ETAT_JEU etatJeu = ETAT_JEU.JEU;
	
	
	private int inputCounter = 200;
    int stateID = 1;
    
    GamePlayState( int stateID ) 
    {
       this.stateID = stateID;
    }
 
    @Override
    public int getID() {
        return stateID;
    }
    
    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) {
    	map.chargerMap("maps/mapCM.sa");
    	tailleEcran = new Tuple(map.getTailleEcranX(), map.getTailleEcranY());
    	
    	SpriteSheet sHero = null;
		try {
			 sHero = new SpriteSheet("img/link-anime.png",32,32);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	hero = new Hero(sHero, new Tuple(tailleEcran.x/2, tailleEcran.y/2), 100, 10);
    	inventaire = new Inventaire(tilesetObjet, tailleEcran);
    	gestEvenement = new GestionEvenements(map, inventaire);
    	boiteAction = new BoiteAction(hero, tailleEcran, gestEvenement, map, inventaire);
       	gestionSourie = new GestionSourie(tileset, map, hero, inventaire, tailleEcran, boiteAction);
    	gestAffichage = new GestionAffichage(map, hero, tailleEcran, gestionSourie, inventaire);
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    	gestAffichage.affichage(coordCarte, etatJeu, boiteAction);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	
    	this.inputCounter -= delta; 	
    	Input input = gc.getInput();   	  	
    	
    	if (inputCounter<0) {
    	
    	// Gestion du Clavier
    	this.etatJeu = GestionClavier.entreeClavier(input, this.etatJeu, sbg);
    	
        // Effets sourie  	
        this.etatJeu = gestionSourie.gererClick(gestEvenement, input, etatJeu);

        // TODO c'est le decalage qui fait office d'horloge, pas cool ...
        

    	// Traitement des evenements
    	if (hero.getDecalage().isNull()) {
    		if (!gestEvenement.traiterEvenement(hero, gestAffichage))
    			hero.setIsRunning(false);
    	}
    	
    	// MAJ de coordCarte dont on se sert par la suite
    	coordCarte.x = hero.getCoord().x - tailleEcran.x/2;
    	coordCarte.y = hero.getCoord().y - tailleEcran.y/2;
    	
    	inputCounter = 70;
    	}
    }
}