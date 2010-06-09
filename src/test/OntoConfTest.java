package test;

import static org.junit.Assert.*;
import static helper.ProtegeHelper.*;

import model.OntoConf;

import org.junit.Test;

import controller.Starter;


public class OntoConfTest {
	private static final String EXT = "owl";
	private static final String PATH = "http://127.0.0.1/";
	private static final String NAME = "onto";
	private static final String BASE = "http://www.semanticweb.org/ontologies/base";
	private static final String Q_MARK = "\"";
	
	
	@Test
	public void testProtegePreamble(){
		String smns = "http://www.semanticweb.org/ontologies/smb";
		
		OntoConf onto = new OntoConf(PATH, NAME, EXT, BASE);
		String pStr = PROTEGE_SM + " " +
						Q_MARK + PROTEGE_SMNS + Q_MARK + "\n" +
						"base " + Q_MARK + PROTEGE_TNS + Q_MARK ;
		String correctStr = Starter.SUMMARY_MODEL + " " +
							Q_MARK + smns + Q_MARK + "\n" +
							"base " + Q_MARK + onto.nameSpace + Q_MARK;
		
		assertEquals(correctStr, onto.protegePreamble(smns, pStr));
	}
	
	
	// Old test before the refactory
	// Now buildPathAttributes is into the constructor
	@Test
	public void testBuildPathAttributes_slashIntoPath() {
		String path_withSlash = "http://127.0.0.1/";
		String path_withoutSlash = "http://127.0.0.1";
		
		String correctPath = "http://127.0.0.1/onto.owl";
		
		// With slash 
		OntoConf onto1 = new OntoConf(path_withSlash, NAME, EXT, "");
		assertEquals(correctPath, onto1.url);
		// Whithout slash
		OntoConf onto2 = new OntoConf(path_withoutSlash, NAME, EXT, "");
		assertEquals(correctPath, onto2.url);
	}
	
	// Old test before the refactory
	// Now buildPathAttributes is into the constructor
	@Test
	public void testBuildPathAttributes_extensionIntoPath() {
		String name_withoutExtension = "onto";
		String name_withExtension = "onto.owl";
		
		String correctPath = "http://127.0.0.1/onto.owl";
		
		// Without extenesion
		OntoConf onto1 = new OntoConf(PATH, name_withoutExtension, EXT, "");
		assertEquals(correctPath, onto1.url);
		// System.out.println(onto1.url);
		// With extenesion
		OntoConf onto2 = new OntoConf(PATH, name_withExtension, EXT, "");
		assertEquals(correctPath, onto2.url);
	}

}