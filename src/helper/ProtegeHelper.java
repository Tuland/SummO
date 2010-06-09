package helper;

import java.util.HashMap;
import java.util.Iterator;

public class ProtegeHelper {
	public static final String PROTEGE_SM = "@summaryModel@";
	public static final String PROTEGE_SMNS = "@summaryModelNS@";
	public static final String PROTEGE_TNS = "@thisNS@";
	
	
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
	 * @param preamble is the protege preamble not converted yet
	 * @param summaryModel is the substitute of PROTEGE_SM
	 * @param summaryModelNS is the substitute of PROTEGE_SMNS
	 * @param thisNS is the substitute of PROTEGE_TNS
	 * @return the preamble with the correct substitutions
	 */
	public static String convertTags(String preamble, String summaryModel, String summaryModelNS, String thisNS) {
		HashMap<String, String> convMap = new HashMap<String, String>();
		convMap.put(PROTEGE_SM, summaryModel);
		convMap.put(PROTEGE_SMNS, summaryModelNS);
		convMap.put(PROTEGE_TNS, thisNS);
		
		return convertTags(preamble, convMap);
	}
	


}
