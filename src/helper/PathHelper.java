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
	 * @param path 
	 * @return correct path: final slash included
	 */
	public static String verifyPath(String path) {
		return verifyEndString(path, "/");
	}
	
	
	/**
	 * @param nameSpace
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
