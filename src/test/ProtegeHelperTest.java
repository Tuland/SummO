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
