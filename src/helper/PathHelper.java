/**
 * SummO: Ontology Summarization 
 * Copyright (C) 2010 Tullio Landoni (tuland@gmail.com)
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/. 
 */
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
