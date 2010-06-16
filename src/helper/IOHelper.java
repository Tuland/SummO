package helper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class IOHelper {
	
	/**
	 * @param filePath is a path string. It refers to the file that will be converted.  
	 * @return the file content in a string
	 * @throws java.io.IOException
	 */
	public static String readFileAsString(String filePath) throws java.io.IOException{
	    byte[] buffer = new byte[(int) new File(filePath).length()];
	    BufferedInputStream f = new BufferedInputStream(new FileInputStream(filePath));
	    f.read(buffer);
	    return new String(buffer);
	}
	
	/**
	 * @param path is the string representation of a path
	 * @return an array that storages yaml files included in the folder (path)    
	 */
	public static File[] listYamlFiles(String path){
		File cDir = new File(path);
		return cDir.listFiles(new YamlFilter());
	}


}
