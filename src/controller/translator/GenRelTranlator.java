/**
 * This file is part of SummO.
 *
 * SummO: Ontology Summarization 
 * Copyright (C) 2010 Tullio Landoni (tuland@gmail.com)
 * SummO is a free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/lgpl-3.0.html and http://www.gnu.org/licenses/gpl-3.0.html. 
 */
package controller.translator;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.query.QuerySolution;

import controller.OntoBuilder;
import controller.query.GenRelQuery;
import static controller.helper.TranslationHelper.extractURI;
import static controller.helper.TranslationHelper.printInclusionFailure;

/**
 * Generalize relationship translator
 * @author tuland
 * 
 */
public class GenRelTranlator implements Translator {
	
	private GenRelQuery genQ;
	private OntoBuilder summ;
	private boolean verboseOut;
	private boolean verboseIn;
	
	public GenRelTranlator(	OntoBuilder summ, 
							GenRelQuery genQ, 
							boolean verboseIn,
							boolean verboseOut){
		this.genQ = genQ;
		this.summ = summ;
		this.verboseOut = verboseOut;
		this.verboseIn = verboseIn;
	}
	
	public GenRelTranlator(OntoBuilder summ, GenRelQuery genQ){
		this(summ, genQ, false, false);
	}

	/* (non-Javadoc)
	 * @see controller.translator.Translator#translate(com.hp.hpl.jena.query.QuerySolution, boolean)
	 */
	public void translate(QuerySolution sol) {

		String subj = extractURI(sol, genQ.getVarSubjStr());
		String obj = extractURI(sol, genQ.getVarObjStr());

		if (subj !=null && obj !=null) {
			
			if (verboseIn){
				System.out.println("SUBJ " + subj);
				System.out.println("OBJ " + obj);
				System.out.println();
			}
			summ.writeTripleGeneralizeRel(subj, obj);
			
		} else if (verboseOut) {
			
			List<String> list = new ArrayList<String>();
			populateList(list, subj, obj);
			printInclusionFailure("gen rel", list);
			
		}
	}	
	
	private List<String> populateList(List<String> list, String el1, String el2) {
		if (el1 != null) {
			list.add(el1);
		}
		if (el2 != null) {
			list.add(el2);
		}
		return list;
	}
	
}
