/**
 * SummO: Ontology Summarization 
 * Copyright (C) 2010 Tullio Landoni (tuland@gmail.com)
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/. 
 */
package controller;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;

import controller.translator.Translator;

/**
 * This class move concepts and relations from a source ontology to a summarized ontology
 * @author tuland
 * 
 */

public class Migrator {
	
	private Translator translator;
	private NodeFinder sFinder;
	private ResultSet rSet;
	
	/**
	 * @param translator	a manager that talks to a summarized ontology
	 * @param sFinder		a manager that talks to a source ontology
	 */
	public Migrator(Translator translator, NodeFinder sFinder){
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
