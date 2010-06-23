/**
 * This file is part of SummO.
 *
 * SummO: Ontology Summarization 
 * Copyright (C) 2010 Tullio Landoni (tuland@gmail.com)
 * SummO is a free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/lgpl-3.0.html and http://www.gnu.org/licenses/gpl-3.0.html. 
 */
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
