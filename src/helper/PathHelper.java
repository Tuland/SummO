package helper;

public class PathHelper {
	
	/**
	 * @param n is the ontology name
	 * @return correct name: extension included
	 */
	public static String verifyName(String name, String ext) {
		if (! name.matches("^.+\\.[a-zA-Z]{2,3}$")){
			name = name + "." + ext;
		}
		return name;
	}
	
	/**
	 * @param b is the ontology base
	 * @return correct base: final slash included
	 */
	public static String verifyPath(String path) {
		if (! path.endsWith("/")) {
			path = path + "/";
		}
		return path;
	}
	
	public static String buildNameSpace(String base){
		return base + "#";
	}
	
	public static String buildUrl(String path, String name){
		return path + name;
	}

}
