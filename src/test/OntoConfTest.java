package test;

import static org.junit.Assert.*;
import static helper.ProtegeHelper.*;

import model.OntoConfBean;

import org.junit.Test;


public class OntoConfTest {
	private static final String EXT = "owl";
	private static final String PATH = "http://127.0.0.1/";
	private static final String NAME = "onto";
	private static final String BASE = "http://www.semanticweb.org/ontologies/base";
	private static final String Q_MARK = "\"";
	
	
	@Test
	public void testProtegePreamble(){
		
		String smns = "http://www.semanticweb.org/ontologies/smb";
		String prefixPropSM = "aeria";
		
		OntoConfBean onto = new OntoConfBean(PATH, NAME, EXT, BASE);
		String pStr = PROTEGE_SM + " " +
						Q_MARK + PROTEGE_SMNS + Q_MARK + "\n" +
						"base " + Q_MARK + PROTEGE_TNS + Q_MARK ;
		String correctStr = prefixPropSM + " " +
							Q_MARK + smns + Q_MARK + "\n" +
							"base " + Q_MARK + onto.getNameSpace() + Q_MARK;
		
		assertEquals(correctStr, onto.protegePreamble(smns, pStr, prefixPropSM));
	}
	
	
	// Old test before the refactory
	// Now buildPathAttributes is into the constructor
	@Test
	public void testUpdateFields_slashIntoPath() {
		String path_withSlash = "http://127.0.0.1/";
		String path_withoutSlash = "http://127.0.0.1";
		
		String correctPath = "http://127.0.0.1/onto.owl";
		
		// With slash 
		OntoConfBean onto1 = new OntoConfBean(path_withSlash, NAME, EXT, BASE);
		assertEquals(correctPath, onto1.getUrl());
		// Whithout slash
		OntoConfBean onto2 = new OntoConfBean(path_withoutSlash, NAME, EXT, BASE);
		assertEquals(correctPath, onto2.getUrl());
	}
	
	// Old test before the refactory
	// Now UpdateFields is into the constructor
	@Test
	public void testUpdateFields_extensionIntoPath() {
		String name_withoutExtension = "onto";
		String name_withExtension = "onto.owl";
		
		String correctPath = "http://127.0.0.1/onto.owl";
		
		// Without extenesion
		OntoConfBean onto1 = new OntoConfBean(PATH, name_withoutExtension, EXT, BASE);
		assertEquals(correctPath, onto1.getUrl());
		// System.out.println(onto1.url);
		// With extenesion
		OntoConfBean onto2 = new OntoConfBean(PATH, name_withExtension, EXT, BASE);
		assertEquals(correctPath, onto2.getUrl());
	}

}