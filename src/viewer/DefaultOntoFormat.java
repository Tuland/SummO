/**
 * This file is part of SummO.
 *
 * SummO: Ontology Summarization 
 * Copyright (C) 2010 Tullio Landoni (tuland@gmail.com)
 * SummO is a free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/lgpl-3.0.html and http://www.gnu.org/licenses/gpl-3.0.html. 
 */
package viewer;

import java.io.FileOutputStream;
import java.io.IOException;

import com.hp.hpl.jena.ontology.OntModel;


/**
 * @author tuland
 *
 */
public class DefaultOntoFormat implements OntoFormat{
	private OntModel model;
	private String base;
	private String preamble;
	public DefaultOntoFormat (OntModel model, String base, String preamble) {
		this.model = model;
		this.base = base;
		this.preamble = preamble;
	}

	
	/* (non-Javadoc)
	 * @see model.OntoFormat#say(java.io.FileOutputStream)
	 */
	public void say(FileOutputStream out) {
		try {
			out.write(preamble.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.write(out, "RDF/XML-ABBREV", base);
	}

}
