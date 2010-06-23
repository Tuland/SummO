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
public class DefaultGenRelQuery implements SummQuery, GenRelQuery{
	
	private static String VAR_SUBJ_STR = "subj";
	private static String VAR_OBJ_STR = "obj";
	
	private static String PROP_STR = "rdfs:subClassOf";
		
	private String varSubjStr;
	private String propStr;
	private String varObjStr;
	private String queryStr;
	
	/**
	 * @param varSubjStr
	 * @param propStr
	 * @param varObjStr
	 */
	public DefaultGenRelQuery(String varSubjStr, String propStr ,String varObjStr){
		this.varSubjStr = varSubjStr;
		this.varObjStr = varObjStr;
		this.propStr = propStr;
		
		this.queryStr = setQueryStr();
	}
	
	/**
	 * @param subjStr a variable name of subject
	 * @param objStr a variable name of object
	 */
	public DefaultGenRelQuery(String subjStr, String objStr) {
		this(subjStr, PROP_STR , objStr);
	}
	
	/**
	 * Implement generalize relationship query
	 */
	public DefaultGenRelQuery(){
		this(VAR_SUBJ_STR, VAR_OBJ_STR);
	}

	/* (non-Javadoc)
	 * @see controller.query.GenRelQuery#getVarSubjStr()
	 */
	public String getVarSubjStr() {
		return varSubjStr;
	}

	/* (non-Javadoc)
	 * @see controller.query.GenRelQuery#getVarObjStr()
	 */
	public String getVarObjStr() {
		return varObjStr;
	}

	/* (non-Javadoc)
	 * @see controller.query.SummQuery#getQueryStr()
	 */
	public String getQueryStr() {
		return queryStr;
	}
	
	protected String setQueryStr(){
		return "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
				"SELECT ?" + varSubjStr + " ?" + varObjStr + " " +
				"WHERE {" +
				"      ?" + varSubjStr + " " + propStr  +" ?" + varObjStr +
				"      }";
	}


}
