import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Map;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;
import assets.Sprite;


public class GameLogic {

	public static void init() {
		Sprite temp = new Sprite("C:\\Users\\zerok_000\\Desktop\\tempURLs\\temp.png");
		
	}
	public static void preDrawUpdate() {
		
		
	}
	public static void Draw() {
		glBegin(GL_QUADS);
		
		
		
		glEnd();
		
	}
	public static void postDrawUpdate() {
		
		
	}
	
	
	
}
