package model;

import static controller.Starter.gConf;
import static helper.IOHelper.readFileAsString;
import static helper.PathHelper.*;
import static helper.ProtegeHelper.convertTags;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import exception.PrefixesNotAvailable;


/**
 * @author tuland
 * This class (JavaBean) provides general ontology infos.
 * Using YAML is necessary invoke updateFields after initialization
 *  
 */
public class OntoConfBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8127623103952332440L;

	private static final String PROTEGE_INIT_FILE = "SupportFiles/protegeInit.txt";
	
	private String path;
	private String name;
	private String url;
	private String ext;
	private String nameSpace;
	private String base;
	
	
	
	/**
	 * Constructor
	 */
	public OntoConfBean(){
		
	}
	
	/**
	 * @param nameStr is the ontology name
	 * @param extStr is the file extension
	 * @param baseStr is the base ontology
	 * @param pathStr is the ontology path
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
	 * @param baseAddition is the addition to complete the base name
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
	
	/**
	 * @param summaryModelNS
	 * @return a Protege preamble with correct namespaces 
	 */
	public String protegePreamble(String summaryModelNS){
		String pp = null;
		try {
			pp = readFileAsString(PROTEGE_INIT_FILE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return protegePreamble(summaryModelNS, pp);
	}
	
	/**
	 * @param summaryModelNS
	 * @param protegeStr a Protege preamble string with internal system tags
	 * @see helper.ProtegeHelper for the tags
	 * @return a Protege preamble with correct namespaces 
	 */
	public String protegePreamble(String summaryModelNS, String protegeStr){
		return protegePreamble(summaryModelNS, protegeStr, gConf.getPropSummaryModel());
	}
	
	
	/**
	 * @param summaryModelNS
	 * @param protegeStr a Protege preamble string with internal system tags
	 * @param prefixPropSM is a string representation of the prefix to assign to the (prop) summary model namespace
	 * @see helper.ProtegeHelper for the tags
	 * @return a Protege preamble with correct namespaces 
	 */
	public String protegePreamble(String summaryModelNS, String protegeStr, String prefixPropSM){
		return convertTags(protegeStr, prefixPropSM, summaryModelNS, nameSpace);
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
