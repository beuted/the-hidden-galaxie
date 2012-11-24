package jeu;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
 
/**
 *
 * @author Spiegel
 *
 */
public class SlickGame extends StateBasedGame {
 
    public static final int MAINMENUSTATE          = 0;
    public static final int GAMEPLAYSTATE          = 1;
    public static final int MAPEDITORSTATE         = 2;
    
    public SlickGame()
    {
        super("SlickGame");
    }
 
    public static void main(String[] args) throws SlickException
    {
         AppGameContainer app = new AppGameContainer(new SlickGame());
         app.setTargetFrameRate(60);
         app.setDisplayMode(800, 600, false);	 
         app.start();
    }
 
    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.addState(new MainMenuState(MAINMENUSTATE));
        this.addState(new GamePlayState(GAMEPLAYSTATE));
        this.addState(new MapEditorState(MAPEDITORSTATE));
    }
}