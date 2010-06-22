/**
 * SummO: Ontology Summarization 
 * Copyright (C) 2010 Tullio Landoni (tuland@gmail.com)
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/. 
 */
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
