import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Map;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

public class GameController {
	final static Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
	Dimension winSize = new Dimension((int)scrSize.getWidth()/2,(int)scrSize.getHeight()/2);
	String winCaption = "This is a test";
	boolean draw3D = false;
	public GameController() throws LWJGLException {
		
		
			Display.setDisplayMode(new DisplayMode((int)winSize.getWidth(),(int)winSize.getHeight()));
			Display.setTitle(winCaption);
			Display.create();
			GameLogic.init();
			glMatrixMode(GL_PROJECTION);
			glLoadIdentity();
			glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
			glMatrixMode(GL_MODELVIEW);
			
		while(!Display.isCloseRequested()) {
			if (draw3D == true) glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			else glClear(GL_COLOR_BUFFER_BIT);
			
			
			
			
			GameLogic.preDrawUpdate();
			GameLogic.Draw();
			Display.update();
			Display.sync(60);
			GameLogic.postDrawUpdate();
		}
		Display.destroy();
		System.exit(0);
		
	}
	
	
	
}
