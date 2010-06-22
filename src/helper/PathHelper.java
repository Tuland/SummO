package helper;

/**
 * @author tuland
 *
 */
public class PathHelper {
	
	/**
	 * @param name	an ontology name
	 * @param ext	a file extension
	 * @return correct name: extension included
	 */
	public static String verifyName(String name, String ext) {
		if (! name.matches("^.+\\.[a-zA-Z]{2,3}$")){
			name = name + "." + ext;
		}
		return name;
	}
	
	/**
	 * @param path	a string representation of the path 
	 * @return correct path: final slash included
	 */
	public static String verifyPath(String path) {
		return verifyEndString(path, "/");
	}
	
	/**
	 * @param nameSpace	a name space (string)
	 * @return correct nameSpace: final # included
	 */
	public static String verifyNameSpace(String nameSpace){
		return verifyEndString(nameSpace, "#");
	}
	
	public static String buildNameSpace(String base){
		return base + "#";
	}
	
	public static String buildUrl(String path, String name){
		return path + name;
	}
	
	public static String buildFileAddr(String confPath, String name, String ext){
		if (name != null){
			return confPath + name + "." + ext;
		} else {
			return null;
		}
	}
	
	private static String verifyEndString(String str, String end) {
		if (! str.endsWith(end)) {
			str = str + end;
		}
		return str;
	}

}
