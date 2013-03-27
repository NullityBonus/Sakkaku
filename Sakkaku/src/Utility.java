import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;


public class Utility {

	public static String userOS() {
		String os = System.getProperty("os.name").toUpperCase();
if (os.indexOf("WIN") >= 0) {
	return "Windows";
} else if (os.indexOf("MAC") >= 0) {
	return "MacOSX";
} else if (os.indexOf("SUNOS") >= 0) {
	return "Solaris";
} else if (os.indexOf("NIX") >= 0 || os.indexOf("NUX") >= 0) {
	return "Linux";
}
else {
	return "Unsupported";
}
		
	}
	
	public Texture loadTexture(String filename) throws IOException {
		String[] fileType = filename.split(".");
		Texture tTexture = TextureLoader.getTexture(fileType[fileType.length-1].toUpperCase(), this.getClass().getResourceAsStream(filename));
		return tTexture;
	}
	//Quick Shapes
	
	
}
