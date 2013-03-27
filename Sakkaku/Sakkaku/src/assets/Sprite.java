package assets;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import org.lwjgl.opengl.GL11;

public class Sprite {
	private Dimension size;
	private Point origin;
	private Rectangle collisionMask;
	private ByteBuffer image;
	public Sprite(String filename,int originx,int originy) {
		try {
			String[] filetype = filename.split("\\.");
			Texture pSprite = TextureLoader.getTexture(filetype[filetype.length-1],new FileInputStream(filename));
			
			size = new Dimension(pSprite.getImageWidth(),pSprite.getImageHeight());
			origin = new Point(originx,originy);
			collisionMask = new Rectangle(0,0,(int)size.getWidth(),(int)size.getHeight());
			byte[] timage = pSprite.getTextureData();
			image = ByteBuffer.allocate(timage.length);
			image.put(timage);
			//Calculate Collision Box
			//DO IT LATER
			for (int i=0;i<timage.length;i++) {
			System.out.println(timage[i]);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}

	public Sprite(String filename) {
		this(filename,0,0);
	}


}
