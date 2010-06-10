package model;

import static helper.IOHelper.readFileAsString;
import static helper.PathHelper.*;
import static controller.Starter.SUMMARY_MODEL;
import static helper.ProtegeHelper.convertTags;

import java.io.IOException;
import java.util.HashMap;

import exception.PrefixesNotAvailable;


/**
 * @author tuland
 * This class provides general ontology infos. 
 */
public class OntoConf {
	
	private static final String PROTEGE_INIT_FILE = "SupportFiles/protegeInit.txt";
	
	public String path;
	public String name;
	public String url;
	public String ext;
	public String nameSpace;
	public String base;
	
	
	
	/**
	 * Constructor
	 */
	public OntoConf(){
		
	}
	
	/**
	 * @param nameStr is the ontology name
	 * @param extStr is the file extension
	 * @param baseStr is the base ontology
	 * @param pathStr is the ontology path
	 */
	public OntoConf(String pathStr, 
					String nameStr, 
					String extStr, 
					String baseStr ){
		this();
		path = pathStr;
		name = nameStr;
		ext = extStr;
		base = baseStr;
		buildPathAttributes("");
	}
	
	
	/**
	 * Build path and nameSpace
	 * @param baseAddition is the addition to complete the base name
	 */
	public void buildPathAttributes(String nameAddition) {
		path = verifyBase(path);
		name = verifyName(name + nameAddition, ext);
		url = buildPath(path, name);
		nameSpace = buildNameSpace(url);
		base = base + nameAddition;
		
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
		return convertTags(protegeStr, SUMMARY_MODEL, summaryModelNS, nameSpace);
	}
	
	private String buildPath(String base, String name){
		return base + name;
	}
	
	private String buildNameSpace(String path){
		return path + "#";
	}


}
