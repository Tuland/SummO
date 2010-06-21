package controller;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

import controller.translator.Translator;

public class Migrator {
	
	private Translator translator;
	private StatementFinder sFinder;
	private ResultSet rSet;
	
	public Migrator(Translator translator, StatementFinder sFinder){
		this.translator = translator;
		this.sFinder = sFinder;
	}
	
	public void start(){
		try{
			rSet = sFinder.start();
			while (rSet.hasNext()) {
				QuerySolution sol = rSet.next();

				translator.translate(sol, true);
			}
			
		} finally { sFinder.halt(); }
		
	}
	
}
