/**
 * This file is part of SummO.
 *
 * SummO: Ontology Summarization 
 * Copyright (C) 2010 Tullio Landoni (tuland@gmail.com)
 * SummO is a free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/. 
 */
package test;

import static org.junit.Assert.*;

import model.bean.OntoConfBean;

import org.junit.Test;


/**
 * @author tuland
 *
 */
public class OntoConfTest {
	private static final String EXT = "owl";
	private static final String PATH = "http://127.0.0.1/";
	private static final String NAME = "onto";
	private static final String BASE = "http://www.semanticweb.org/ontologies/base";
	
	
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