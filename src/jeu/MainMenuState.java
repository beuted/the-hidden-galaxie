package jeu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
 
public class MainMenuState extends BasicGameState {
 
    int stateID = 0;
 
    Image background = null;
    Image startGameOption = null;
    Image exitOption = null;
    Image mapEditorOption = null;
    
    int menuX = 300;
    int menuY = 150;
    
    float scaleStep = 0.0001f;
 
    float startGameScale = 1;
    float exitScale = 1;
    float mapEditorScale = 1;
    
    MainMenuState( int stateID ) 
    {
       this.stateID = stateID;
    }
 
    @Override
    public int getID() {
        return stateID;
    }
 
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    	//Initialisation des polices
   	 	Polices.Initialise();
   	 	
    	background = new Image("img/fond_menu.jpeg");
    	 
    	// load the menu images
    	Image menuOptions = new Image("img/boutons_menu.png");
    	 
    	startGameOption = menuOptions.getSubImage(0, 0, 377, 71);
    	 
    	exitOption = menuOptions.getSubImage(0, 71, 377, 71);
    	
    	mapEditorOption = menuOptions.getSubImage(0, 142, 377, 71);
    }
 
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    	// render the background
    	background.draw(0, 0);
    	 
    	// Draw menu
    	startGameOption.draw(menuX, menuY, startGameScale);
    	
    	mapEditorOption .draw(menuX, menuY+80, mapEditorScale);
    	
    	exitOption.draw(menuX, menuY+160, exitScale);
    }
 
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    	Input input = gc.getInput();
    	 
    	int mouseX = input.getMouseX();
    	int mouseY = input.getMouseY();
    	 
    	boolean insideStartGame = false;
    	boolean insideMapEditor = false;
    	boolean insideExit = false;
    	
    	// On reppere ou est la sourie
    	if( ( mouseX >= menuX && mouseX <= menuX + startGameOption.getWidth()) &&
    	    ( mouseY >= menuY && mouseY <= menuY + startGameOption.getHeight()) ){
    	    insideStartGame = true;
    	} else if( ( mouseX >= menuX && mouseX <= menuX+ mapEditorOption.getWidth()) &&
    	          ( mouseY >= menuY+80 && mouseY <= menuY+80 + mapEditorOption.getHeight()) ){
    	    insideMapEditor = true;
    	} else if( ( mouseX >= menuX && mouseX <= menuX+ exitOption.getWidth()) &&
    	          ( mouseY >= menuY+160 && mouseY <= menuY+160 + exitOption.getHeight()) ) {
    	    insideExit = true;
    	}
    	
    	// On agit en fonction lors du click
    	if(insideStartGame) {
    	  if(startGameScale < 1.05f)
    	    startGameScale += scaleStep * delta;
    	 
    	  if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ) {
    	    sbg.enterState(SlickGame.GAMEPLAYSTATE);
    	  }
    	} else {
    	  if(startGameScale > 1.0f)
    	    startGameScale -= scaleStep * delta;
    	 
    	 // if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) )
    	   // gc.exit();
    	}
    	
    	// ================
    	
    	if(insideMapEditor) {
      	  if(mapEditorScale < 1.05f)
      	    mapEditorScale += scaleStep * delta;
      	 
      	  if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ) {
      	    sbg.enterState(SlickGame.MAPEDITORSTATE);
      	  }
      	} else {
      	  if(mapEditorScale > 1.0f)
      		mapEditorScale -= scaleStep * delta;
      	 
      	 // if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) )
      	   // gc.exit();
      	}
    	
    	// ================
    	
    	if(insideExit) {
    	   if(exitScale < 1.05f)
    	     exitScale +=  scaleStep * delta;
    	   if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) )
          	   gc.exit();
    	} else {
    	   if(exitScale > 1.0f)
    		   exitScale -= scaleStep * delta;
    	}
    }
}