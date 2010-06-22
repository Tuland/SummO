package model.helper;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @author tuland
 *
 */
public class ProtegeHelper {
	
	public static final String PROTEGE_INIT_FILE = "SupportFiles/protegeInit.txt";
	
	public static final String PROTEGE_SM = "@summaryModel@";
	public static final String PROTEGE_SMNS = "@summaryModelNS@";
	public static final String PROTEGE_TNS = "@thisNS@";
	
	
	/**
	 * 
	 * @param preamble	a the protege preamble not converted yet
	 * @param convMap	a mapping for the substitution
	 * @return the preamble with the correct substitutions
	 * @see model.PropSummaryModel (aeria). ClassSummaryModel is not considered (Skos is already included)
	 */
	public static String convertTags(String preamble, HashMap <String,String> convMap)  {
		Iterator<String> convSetIterator = convMap.keySet().iterator();
		String key;
		while (convSetIterator.hasNext()) {
			key = convSetIterator.next();
			preamble = preamble.replaceAll(key, convMap.get(key));
		}
		return preamble;
	}
	
	
	/**
	 * @param preamble			a protege preamble not converted yet
	 * @param summaryModel		a substitute of PROTEGE_SM
	 * @param summaryModelNS	a substitute of PROTEGE_SMNS
	 * @param thisNS 			a substitute of PROTEGE_TNS
	 * @return the preamble with the correct substitutions
	 * @see model.PropSummaryModel (aeria). ClassSummaryModel is not considered (Skos is already included)
	 */
	public static String convertTags(String preamble, String summaryModel, String summaryModelNS, String thisNS) {
		HashMap<String, String> convMap = new HashMap<String, String>();
		convMap.put(PROTEGE_SM, summaryModel);
		convMap.put(PROTEGE_SMNS, summaryModelNS);
		convMap.put(PROTEGE_TNS, thisNS);
		
		return convertTags(preamble, convMap);
	}
	


}
