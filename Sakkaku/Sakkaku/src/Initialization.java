import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.lwjgl.LWJGLException;





public class Initialization {
	final static String workPath = System.getProperty("user.dir");
	final static String dependencyURLs = "C:\\Users\\Zero\\Desktop\\tempURLs";
	final public static String OS = Utility.userOS();
final static int OSbit = Integer.parseInt(System.getProperty("sun.arch.data.model"));

	public static void main(String[] params) {
		//Check for dependencies & Directory existence
		if (OS != "Unsupported") {
			fixDependencyDirectories();

			try {

			GameController game = new GameController();
		
			} catch (LWJGLException e) {
				e.printStackTrace();
				System.exit(0);
			}
		
		
		
		}
		
	}
	private static boolean fixDependencyDirectories() {
		//Create Directory if nonexistent

			try {
				if (!Files.exists(Paths.get(workPath + "\\bin"))) {
			Files.createDirectory(Paths.get(workPath + "\\bin"));
			Files.createDirectory(Paths.get(workPath + "\\deps\\natives"));
			//Since directory was unavailable, all dependencies are unavailable. So download them
			if (downloadDependency("all") == false) {
				System.exit(0);
			}
				}
				//Now check for each jar independently
				else {
					if (!Files.exists(Paths.get(workPath + "\\deps\\jinput.jar"))) {
					if (downloadDependency("jinput") == false) {
						System.exit(0);
					}
				}
				if (!Files.exists(Paths.get(workPath + "\\deps\\lwjgl.jar"))) {
					if (downloadDependency("lwjgl") == false) {
						System.exit(0);
					}
				}
				if (!Files.exists(Paths.get(workPath + "\\deps\\lzma.jar"))) {
					if (downloadDependency("lzma") == false) {
						System.exit(0);
					}
				}
				if (!Files.exists(Paths.get(workPath + "\\deps\\slick-util.jar"))) {
					if (downloadDependency("slick-util") == false) {
						System.exit(0);
					}
				}
				//Check for natives
				if (!Files.exists(Paths.get(workPath + "\\deps\\natives"))) {
					Files.createDirectory(Paths.get(workPath + "\\deps\\natives"));
					if (downloadNatives("all") == false) {
						System.exit(0);
					}
				}
				else {
					String[] cList;
					switch (OS) {
					
					case "Windows":
					 if (OSbit == 64) {
						cList = new String[] { "jinput-dx8_64", "jinput-raw_64", "lwjgl64", "OpenAL64" };
					 } else {
						cList = new String[] { "jinput-dx8", "jinput-raw", "lwjgl", "OpenAL32" };
					 }
						break;
					case "MacOSX": 
						cList = new String[] { "libjinput-osx", "liblwjgl", "openal" };
						break;
					case "Linux":
						 if (OSbit == 64) {
							cList = new String[] { "libopenal64", "liblwjgl64", "libjinput-linux64" };
						 } else {
								cList = new String[] { "libopenal", "liblwjgl", "libjinput-linux" };
						 }
							break;
					case "Solaris":
						 if (OSbit == 64) {
							cList = new String[] { "libopenal64", "liblwjgl64" };
						 } else {
								cList = new String[] { "libopenal", "liblwjgl" };
						 }
							break;
					default:
						cList = new String[] { null };
						
					}
					if (cList[0] != null) {
					for (int i=0;i < cList.length;i++) {
						if (!Files.exists(Paths.get(workPath + "\\deps\\natives\\" +cList[i] + ".dll"))) {
							if (downloadNatives(cList[i]) == false) {
								System.exit(0);
							}
						}	
					}
					}
					else {
						System.exit(0);
					}
						
						
						
						
					
					
				}
				}

				
				
				
				
				
				
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		

		
		

	}

	private static boolean downloadDependency(String dep) {
		
		switch(dep) {
		case "jinput":
		case "lwjgl":
		case "lzma":
		case "slick-util":
			try {
			Files.copy(Paths.get(dependencyURLs + "\\" + dep + ".jar"), Paths.get(workPath + "\\deps\\" + dep + ".jar"));
			return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			
		case "all":
			try {
			Files.copy(Paths.get(dependencyURLs + "\\jinput.jar"), Paths.get(workPath + "\\deps\\jinput.jar"));
			Files.copy(Paths.get(dependencyURLs + "\\lwjgl.jar"), Paths.get(workPath + "\\deps\\lwjgl.jar"));
			Files.copy(Paths.get(dependencyURLs + "\\lzma.jar"), Paths.get(workPath + "\\deps\\lzma.jar"));
			Files.copy(Paths.get(dependencyURLs + "\\slick-util.jar"), Paths.get(workPath + "\\deps\\slick-util.jar"));
			return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		default:
			return false;
		
		}
	}

	private static boolean downloadNatives(String nat) {
		try {
		switch (OS) {
		case "Windows":
			if (OSbit == 64) {
				switch (nat) {
				case "jinput-dx8":
				case "jinput-raw":
					Files.copy(Paths.get(dependencyURLs + "\\native\\" + OS.toLowerCase() + "\\" + nat +"_64.dll"), Paths.get(workPath + "\\deps\\natives\\" + nat + "_64.dll"));
					return true;
				case "lwjgl":
				case "OpenAL":
					Files.copy(Paths.get(dependencyURLs + "\\native\\" + OS.toLowerCase() + "\\" + nat +"64.dll"), Paths.get(workPath + "\\deps\\natives\\" + nat + "64.dll"));
					return true;
				case "all":
					Files.copy(Paths.get(dependencyURLs + "\\native\\" + OS.toLowerCase() + "\\jinput-dx8_64.dll"), Paths.get(workPath + "\\deps\\natives\\jinput-dx8_64.dll"));
					Files.copy(Paths.get(dependencyURLs + "\\native\\" + OS.toLowerCase() + "\\jinput-raw_64.dll"), Paths.get(workPath + "\\deps\\natives\\jinput-raw_64.dll"));				
					Files.copy(Paths.get(dependencyURLs + "\\native\\" + OS.toLowerCase() + "\\lwjgl64.dll"), Paths.get(workPath + "\\deps\\natives\\lwjgl64.dll"));	
					Files.copy(Paths.get(dependencyURLs + "\\native\\" + OS.toLowerCase() + "\\OpenAL64.dll"), Paths.get(workPath + "\\deps\\natives\\OpenAL64.dll"));	
					return true;
				default:
					return false;
				}
			} else {
				switch (nat) {
				case "jinput-dx8":
				case "jinput-raw":
				case "lwjgl":
					Files.copy(Paths.get(dependencyURLs + "\\native\\" + OS.toLowerCase() + nat +".dll"), Paths.get(workPath + "\\deps\\natives\\" + nat + ".dll"));
					return true;
				case "OpenAL":
					Files.copy(Paths.get(dependencyURLs + "\\native\\" + OS.toLowerCase() + nat +"32.dll"), Paths.get(workPath + "\\deps\\natives\\" + nat + "32.dll"));
					return true;
				case "all":
					Files.copy(Paths.get(dependencyURLs + "\\native\\" + OS.toLowerCase() + "\\jinput-dx8.dll"), Paths.get(workPath + "\\deps\\natives\\jinput-dx8.dll"));
					Files.copy(Paths.get(dependencyURLs + "\\native\\" + OS.toLowerCase() + "\\jinput-raw.dll"), Paths.get(workPath + "\\deps\\natives\\jinput-raw.dll"));				
					Files.copy(Paths.get(dependencyURLs + "\\native\\" + OS.toLowerCase() + "\\lwjgl.dll"), Paths.get(workPath + "\\deps\\natives\\lwjgl.dll"));	
					Files.copy(Paths.get(dependencyURLs + "\\native\\" + OS.toLowerCase() + "\\OpenAL32.dll"), Paths.get(workPath + "\\deps\\natives\\OpenAL32.dll"));	
					return true;
				default:
					return false;
				}
			}
		case "MacOSX":
			switch (nat) {
			case "libjinput-osx":
			case "liblwjgl":
				Files.copy(Paths.get(dependencyURLs + "\\native\\" + OS.toLowerCase() + "\\" + nat +".jnilib"), Paths.get(workPath + "\\deps\\natives\\" + nat + ".jnilib"));
				return true;
			case "openal":
				Files.copy(Paths.get(dependencyURLs + "\\native\\" + OS.toLowerCase() + "\\" + nat +".dylib"), Paths.get(workPath + "\\deps\\natives\\" + nat + ".dylib"));
				return true;
			case "all":
				Files.copy(Paths.get(dependencyURLs + "\\native\\" + OS.toLowerCase() + "\\liblwjgl.jnilib"), Paths.get(workPath + "\\deps\\natives\\liblwjgl.jnilib"));
				Files.copy(Paths.get(dependencyURLs + "\\native\\" + OS.toLowerCase() + "\\libjinput-osx.jnilib"), Paths.get(workPath + "\\deps\\natives\\libjinput-osx.jnilib"));
				Files.copy(Paths.get(dependencyURLs + "\\native\\" + OS.toLowerCase() + "\\openal.dylib"), Paths.get(workPath + "\\deps\\natives\\openal.dylib"));
				return true;
			default:
				return false;
			}
		case "Linux":
		case "Solaris":
			if (OSbit == 64) {
				switch (nat) {
				case "libjinput-linux":
				case "liblwjgl":
				case "libopenal":
					Files.copy(Paths.get(dependencyURLs + "\\native\\" + OS.toLowerCase() + "\\" + nat +"64.so"), Paths.get(workPath + "\\deps\\natives\\" + nat + "64.so"));
					return true;
				case "all":
					if (OS.equals("Linux")) {
					Files.copy(Paths.get(dependencyURLs + "\\native\\" + OS.toLowerCase() + "\\libjinput-linux64.so"), Paths.get(workPath + "\\deps\\natives\\libjinput-linux64.so"));
					}
					Files.copy(Paths.get(dependencyURLs + "\\native\\" + OS.toLowerCase() + "\\liblwjgl64.so"), Paths.get(workPath + "\\deps\\natives\\liblwjgl64.so"));
					Files.copy(Paths.get(dependencyURLs + "\\native\\" + OS.toLowerCase() + "\\libopenal64.so"), Paths.get(workPath + "\\deps\\natives\\libopenal64.so"));
					return true;
				default:
					return false;
				}
			} else {
				switch (nat) {
				case "libjinput-linux":
				case "liblwjgl":
				case "libopenal":
					Files.copy(Paths.get(dependencyURLs + "\\native\\" + OS.toLowerCase() + "\\" + nat +".so"), Paths.get(workPath + "\\deps\\natives\\" + nat + ".dso"));
					return true;
				case "all":
					if (OS.equals("Linux")) {
					Files.copy(Paths.get(dependencyURLs + "\\native\\" + OS.toLowerCase() + "\\libjinput-linux.so"), Paths.get(workPath + "\\deps\\natives\\libjinput-linux.so"));
					}
					Files.copy(Paths.get(dependencyURLs + "\\native\\" + OS.toLowerCase() + "\\liblwjgl.so"), Paths.get(workPath + "\\deps\\natives\\liblwjgl.so"));
					Files.copy(Paths.get(dependencyURLs + "\\native\\" + OS.toLowerCase() + "\\libopenal.so"), Paths.get(workPath + "\\deps\\natives\\libopenal.so"));
					return true;
				default:
					return false;
				}
			}
			default:
				return false;
		
		
		
		}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		
		
		}

		
		
		
		

}
