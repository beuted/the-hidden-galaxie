package jeu;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import mapEditor.EffetsCarteME;
import mapEditor.GestionAffichage;
import mapEditor.GestionClavier;
import mapEditor.ListeBlocksME;
import mapEditor.ETAT_EDITOR;
import mapEditor.ListeObjetsME;
import mapEditor.Selection;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
 
public class MapEditorState extends BasicGameState {
	
	private Tileset tileset = new Tileset("img/link-donjon.png",11);
	private Tileset tilesetObjet = new Tileset("img/objets.png",11);
	private Map map = new Map(tileset, tilesetObjet);
	private Tuple coordCarte = new Tuple(0,0);
	private Tuple mouse = new Tuple(0,0);
	private ETAT_EDITOR etatEditor = ETAT_EDITOR.EDITOR;
	private Selection selec = new Selection();
	
	
	private Tuple tailleMap;
	
	private ListeBlocksME listeBlocksME = new ListeBlocksME(tileset);
	private ListeObjetsME listeObjetsME = new ListeObjetsME(tilesetObjet);
	private EffetsCarteME effetsCarteME = new EffetsCarteME(tileset, tilesetObjet);
	private GestionAffichage gestAffichage = new GestionAffichage(map, effetsCarteME, listeBlocksME, listeObjetsME);
	
	private int stateID = 2;
    private int inputCounter = 0;
    
    MapEditorState( int stateID ) 
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
    	tailleMap = new Tuple(map.getTailleMapX(),map.getTailleMapY());
    }
 
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    	gestAffichage.affichage(coordCarte, etatEditor, selec);
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	
    	this.inputCounter -= delta;
    	Input input = gc.getInput();
    	mouse.x = input.getMouseX(); mouse.y = input.getMouseY();
    	boolean click = input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
    	
    	if (inputCounter<0) {
    	
    	//Deplacements		
    	this.etatEditor = GestionClavier.gererClavier(input, coordCarte, etatEditor, map, sbg);
    			
        
        //Selection du block
    	switch (etatEditor) {
    	case EDITOR :
    		effetsCarteME.selection(mouse, click, map, coordCarte, selec);
    		break;
    	case BLOCKS :
    		etatEditor = listeBlocksME.selection(mouse, click, effetsCarteME, etatEditor, selec);
    		break;
    	case OBJETS :
    		etatEditor = listeObjetsME.selection(mouse, click, effetsCarteME, etatEditor, selec);
    		break;
    	}
        
        
    	//TODO Bof comme maj des coords ...
    	if (coordCarte.x > tailleMap.x || coordCarte.y < -tailleMap.x)
    		coordCarte.x = MathPerso.mod(coordCarte.x, tailleMap.x);
    	if (coordCarte.y > tailleMap.y || coordCarte.y < -tailleMap.y)
    		coordCarte.y = MathPerso.mod(coordCarte.y, tailleMap.y);
    	
    	
        inputCounter = 70;
    	}
        
    }
 
}