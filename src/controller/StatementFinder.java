package controller;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

import controller.query.SummQuery;

import model.OntoLoaded;

public class StatementFinder {
	
	private QueryExecution qe;
	private Query query;
	private boolean verbose;
	
	/**
	 * @param summQ		a query model
	 * @param onto		a source ontology
	 * @param verbose	a flag that enables the verbose mode. It shows SPARQL query results. 
	 */
	public StatementFinder(SummQuery summQ, OntoLoaded onto, boolean verbose){
		this.verbose = verbose;
		query = QueryFactory.create(summQ.getQueryStr());
		
		this.qe = QueryExecutionFactory.create(query, onto.model);
	}
	
	public StatementFinder(SummQuery summQ, OntoLoaded onto){
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
