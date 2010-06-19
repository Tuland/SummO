package controller;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

import model.OntoLoaded;

public class StatementFinder {
	
	private QueryExecution qe;
	private Query query;
	private boolean verbose;
	
	public StatementFinder(SummQuery summQ, OntoLoaded onto, boolean verbose){
		this.verbose = verbose;
		query = QueryFactory.create(summQ.getQueryStr());
		
		// Execute the query and obtain results
		this.qe = QueryExecutionFactory.create(query, onto.model);
	}
	
	public StatementFinder(SummQuery summQ, OntoLoaded onto){
		this(summQ, onto, false);
	}
	
	
	public ResultSet start(){
		ResultSet results = qe.execSelect();
		if (verbose) {
			ResultSetFormatter.out(System.out, results, query);
		}
		return results;
	}
	
	public void halt(){
		qe.close();
	}

}
