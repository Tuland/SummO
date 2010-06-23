/**
 * This file is part of SummO.
 *
 * SummO: Ontology Summarization 
 * Copyright (C) 2010 Tullio Landoni (tuland@gmail.com)
 * SummO is a free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/lgpl-3.0.html and http://www.gnu.org/licenses/gpl-3.0.html. 
 */
package controller;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

import controller.query.SummQuery;

import model.OntoLoaded;

/**
 * This class is a interpreter that talks with the source ontology in the summarization process
 * @author tuland
 */
public class NodeFinder {
	
	private QueryExecution qe;
	private Query query;
	private boolean verbose;
	
	/**
	 * @param summQ		a query model
	 * @param onto		a source ontology
	 * @param verbose	a flag that enables the verbose mode. It shows SPARQL query results. 
	 */
	public NodeFinder(SummQuery summQ, OntoLoaded onto, boolean verbose){
		this.verbose = verbose;
		query = QueryFactory.create(summQ.getQueryStr());
		
		this.qe = QueryExecutionFactory.create(query, onto.model);
	}
	
	/**
	 * @param summQ		a query model
	 * @param onto		a source ontology
	 */
	public NodeFinder(SummQuery summQ, OntoLoaded onto){
		this(summQ, onto, false);
	}
	
	/**
	 * Start query execution
	 */
	public ResultSet start(){
		ResultSet results = qe.execSelect();
		if (verbose) {
			ResultSetFormatter.out(System.out, results, query);
		}
		return results;
	}
	
	
	/**
	 * Finish query execution
	 */
	public void stop(){
		qe.close();
	}

}
