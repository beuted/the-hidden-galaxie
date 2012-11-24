package slickTest;
 
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;



public class SimpleTest extends BasicGame {
	
	 Image plane = null;
	 Image land = null;
	 float x = 400;
	 float y = 300;
	 float scale = 1;
	
	public SimpleTest() {
		super("SimpleTest");
	}
 
	@Override
	public void init(GameContainer container) throws SlickException {
        plane = new Image("img/avion.png");
        land = new Image("img/fond.jpeg");	
	}
 
	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		
        Input input = container.getInput();
        
        if(input.isKeyDown(Input.KEY_Q))
        {
            plane.rotate(-0.2f * delta);
        }
 
        if(input.isKeyDown(Input.KEY_D))
        {
            plane.rotate(0.2f * delta);
        }
 
        if(input.isKeyDown(Input.KEY_Z))
        {
            float hip = 0.4f * delta;
 
            float rotation = plane.getRotation();
 
            x+= hip * Math.sin(Math.toRadians(rotation));
            y-= hip * Math.cos(Math.toRadians(rotation));
        }
 
        if(input.isKeyDown(Input.KEY_2))
        {
            scale += (scale >= 5.0f) ? 0 : 0.1f;
            plane.setCenterOfRotation(plane.getWidth()/2.0f*scale, plane.getHeight()/2.0f*scale);
        }
        if(input.isKeyDown(Input.KEY_1))
        {
            scale -= (scale <= 1.0f) ? 0 : 0.1f;
            plane.setCenterOfRotation(plane.getWidth()/2.0f*scale, plane.getHeight()/2.0f*scale);
        }
        if(input.isKeyDown(Input.KEY_3))
        {
        	try {
        	FileOutputStream sortieDeFicher = sortieDeFicher = new FileOutputStream("MaSauvegarde.sa");
            ObjectOutputStream sortieDObjet = new ObjectOutputStream(sortieDeFicher);
            int[][] A = {{1,2,3,3,3,3,3,3,3,3},
            			 {1,2,3,3,3,3,3,3,3,3},
            			 {1,2,3,3,3,3,3,3,3,3},
            			 {1,2,3,3,3,3,3,3,3,3},
            			 {1,2,3,3,1,1,3,3,3,3},
            			 {1,2,3,3,3,1,3,3,3,3},
            			 {1,2,3,3,3,3,1,3,3,3},
            			 {1,2,3,3,3,3,3,3,3,3},
            			 {1,2,3,3,3,3,3,3,3,3},
            			 {1,2,3,3,3,3,3,3,3,3}};
            sortieDObjet.writeObject(A);
            sortieDObjet.close();
        	} catch (FileNotFoundException ex) {
            
            // Que faire en cas d'erreur.
        	} catch (IOException ex) {
            
        	}
        }
	}
 
	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		land.draw(0, 0);
		plane.draw(x, y, scale);
	}
 
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new SimpleTest());
			app.setShowFPS(true);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}