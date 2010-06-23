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
public class EmbeddedOntoFormat implements OntoFormat{
	
	private static final String INIT_COMMENT = "<!--BEGIN_SUMM\n";
	private static final String FINAL_COMMENT = "\nEND_SUMM-->";
	private static final String BOX_STR = "*";
	private static final int BOX_DIM = 100;
	
	private OntModel model;
	private String base;
	private OntoFormat format;
	private String preamble;
	
	public EmbeddedOntoFormat (	OntModel model, 
								String base, 
								String preamble, 
								OntoFormat format) {
		this.model = model;
		this.base = base;
		this.format = format;
		this.preamble = preamble;
	}

	/* (non-Javadoc)
	 * @see model.OntoFormat#say(java.io.FileOutputStream)
	 */
	public void say(FileOutputStream out) {
		try {
			out.write(topComment());
			out.write(preamble.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.write(out, "RDF/XML-ABBREV", base);
		
		try {
			out.write(bottomComment());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		format.say(out);
		
	}
	
	private byte[] topComment() {
		String comment = 	INIT_COMMENT +
							repeatStr(BOX_DIM, BOX_STR) + "\n" +
							"\n" ;
		return comment.getBytes();
	}
	
	private byte[] bottomComment() {
		String comment = 	"\n" + repeatStr(BOX_DIM, BOX_STR) + 
							FINAL_COMMENT + 
							"\n" ;
		return comment.getBytes();
	}
	
	private String repeatStr(int n, String str){
		String resultStr = "";
		for (int i = 0; i < n; i++) {
			resultStr = resultStr.concat(str);
		}
		return resultStr;
	}
	

}
