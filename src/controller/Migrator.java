package controller;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

import controller.translator.Translator;

/**
 * @author tuland
 * This class move concepts and relations from a source ontology to a summarized ontology
 */
public class Migrator {
	
	private Translator translator;
	private StatementFinder sFinder;
	private ResultSet rSet;
	
	/**
	 * @param translator	a manager that talks to a summarized ontology
	 * @param sFinder		a manager that talks to a source ontology
	 */
	public Migrator(Translator translator, StatementFinder sFinder){
		this.translator = translator;
		this.sFinder = sFinder;
	}
	
	/**
	 * Start the migration process
	 */
	public void start(){
		try{
			rSet = sFinder.start();
			while (rSet.hasNext()) {
				QuerySolution sol = rSet.next();

				translator.translate(sol);
			}
			
		} finally { sFinder.stop(); }
		
	}
	
}
