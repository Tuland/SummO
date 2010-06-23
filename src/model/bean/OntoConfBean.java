/**
 * This file is part of SummO.
 *
 * SummO: Ontology Summarization 
 * Copyright (C) 2010 Tullio Landoni (tuland@gmail.com)
 * SummO is a free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/. 
 */
package model.bean;


import static helper.PathHelper.*;

import java.io.Serializable;
import java.util.HashMap;

import model.exception.PrefixesNotAvailable;



/**
 * This class (JavaBean) provides general ontology infos.
 * Using YAML is necessary invoke updateFields after initialization
 * @author tuland
 *  
 */
public class OntoConfBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8127623103952332440L;
	
	private String path;
	private String name;
	private String url;
	private String ext;
	private String nameSpace;
	private String base;
	
	public OntoConfBean(){
		
	}
	
	/**
	 * @param nameStr	an ontology name
	 * @param extStr	a file extension
	 * @param baseStr	a base ontology
	 * @param pathStr	an ontology path
	 */
	public OntoConfBean(String pathStr, 
					String nameStr, 
					String extStr, 
					String baseStr ){
		this();
		path = verifyPath(pathStr);
		name = nameStr;
		ext = extStr;
		base = baseStr;
		updateFields("");
	}
	
	
	/**
	 * This method recalculate the values of name, url, base and nameSpace
	 * Using YAML is necessary invoke it after initialization
	 * @param nameAddition	an addition (string) to complete the base name
	 */
	public void updateFields(String nameAddition) {
		name = verifyName(name + nameAddition, ext);
		url = buildUrl(path, name);
		base = base + nameAddition;
		nameSpace = buildNameSpace(base);
		
		System.out.println("Url: " + url);
		System.out.println("Base: " + base);
	}
	
	/**
	 * @return a map that store the available prefixes
	 * @throws PrefixesNotAvailable
	 */
	public HashMap<String, String> getPrefixes() throws PrefixesNotAvailable{
		if (base == null) {
			throw new PrefixesNotAvailable("base");
		}
		if (nameSpace == null) {
			throw new PrefixesNotAvailable("nameSpace");
		}
		
		HashMap<String, String> prefixes = new HashMap<String, String>();
		prefixes.put("", nameSpace);
		prefixes.put("base", base);
		
		return prefixes;
	}
	

	public String getPath() {
		return verifyPath(path);
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public String getNameSpace() {
		return nameSpace;
	}


	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

}
