/**
 * This file is part of SummO.
 *
 * SummO: Ontology Summarization 
 * Copyright (C) 2010 Tullio Landoni (tuland@gmail.com)
 * SummO is a free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/. 
 */
package controller.query;

/**
 * @author tuland
 *
 */
public class SubRelOfDirRelQuery extends DefaultDirRelQuery implements SummQuery, DirRelQuery{
	protected static String P_SUBPROP_STR = "rdfs:subPropertyOf";
	
	public SubRelOfDirRelQuery(){
		super();
	}
	
	protected String setQueryString(){ 
		return	"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
				"SELECT ?" + VAR_SUBJ_STR + " ?" + VAR_PROP_STR + " ?" + VAR_OBJ_STR + " " +
				"WHERE {" +
				"      ?x " + P_DOMAIN_STR  + " ?" + VAR_SUBJ_STR + " . " +
				"      ?x " + P_RANGE_STR  + " ?" + VAR_OBJ_STR + " . " +
				"      ?" + VAR_PROP_STR + " " + P_SUBPROP_STR + " ?x ." +	
				"      }";
	}

}
