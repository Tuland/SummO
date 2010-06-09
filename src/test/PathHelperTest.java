package test;

import static org.junit.Assert.*;

import org.junit.Test;

import static helper.PathHelper.*;

public class PathHelperTest {

	@Test
	public void testVerifyName_withoutExtension() {
		String name_withoutExtension = "onto";
		String name_withExtension = "onto.owl";
		String ext = "owl";
		assertEquals(name_withExtension, verifyName(name_withoutExtension, ext));
	}

	@Test
	public void testVerifyBase_withoutSlash() {
		String base_withSlash = "http://127.0.0.1/";
		String base_withoutSlash = "http://127.0.0.1";
		assertEquals(base_withSlash, verifyBase(base_withoutSlash));
	}


}
