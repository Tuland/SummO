package test;

import static helper.PathHelper.*;
import static org.junit.Assert.*;

import org.junit.Test;


/**
 * @author tuland
 *
 */
public class PathHelperTest {

	@Test
	public void testVerifyName_withoutExtension() {
		String name_withoutExtension = "onto";
		String name_withExtension = "onto.owl";
		String ext = "owl";
		assertEquals(name_withExtension, verifyName(name_withoutExtension, ext));
	}

	@Test
	public void testVerifyPath_withoutSlash() {
		String path_withSlash = "http://127.0.0.1/";
		String path_withoutSlash = "http://127.0.0.1";
		assertEquals(path_withSlash, verifyPath(path_withoutSlash));
	}


}
