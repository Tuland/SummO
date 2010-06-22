/**
 * SummO: Ontology Summarization 
 * Copyright (C) 2010 Tullio Landoni (tuland@gmail.com)
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/. 
 */
package test;

import static model.helper.ProtegeHelper.*;
import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

/**
 * @author tuland
 *
 */
public class ProtegeHelperTest {
	private String str1a = "AAA";
	private String str2a = "BBB";
	private String str3a = "CCC";
	private String str4a = "DDD";
	
	private String str1b = "XXX";
	private String str2b = "YYY";
	private String str3b = "ZZZ";
	private String str4b = "WWW";
	
	private String str;
	private String strCorrect;	

	@Test
	public void testConvertTagsStringHashMapOfStringString() {

		HashMap<String, String> convMap = new HashMap<String, String>();
		convMap.put(str1a, str1b);
		convMap.put(str2a, str2b);
		convMap.put(str3a, str3b);
		convMap.put(str4a, str4b);
		
		str = str1a + " " + str2a + " " + str3a + " " + str4a;
		strCorrect = str1b + " " + str2b + " " + str3b + " " + str4b;
		
		assertEquals(strCorrect, convertTags(str, convMap));
		
	}


}
