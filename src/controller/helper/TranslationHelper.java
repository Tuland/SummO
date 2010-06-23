/**
 * This file is part of SummO.
 *
 * SummO: Ontology Summarization 
 * Copyright (C) 2010 Tullio Landoni (tuland@gmail.com)
 * SummO is a free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/lgpl-3.0.html and http://www.gnu.org/licenses/gpl-3.0.html. 
 */
package controller.helper;

import java.util.List;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * @author tuland
 *
 */
public class TranslationHelper {
	
	/**
	 * @param sol 		a solution from a SPARQL query 
	 * @param varStr	a variable name included in the SPARQL query
	 * @param verbose	a flag that enables the verbose mode. It shows refused nodes
	 * @return the URI of the node identified by the variable name. If the node is anonymous or is not a resource, the method return null
	 */
	public static String extractURI(QuerySolution sol, String varStr, boolean verbose){
		RDFNode node = sol.get(varStr) ;
		if ( node.isResource() ) {
			Resource r = (Resource) node ;
				if ( ! r.isAnon() ) {
					return r.getURI();
				} else if (verbose) {
					System.out.println("Anonymous: " + node.toString());
				}
		} else if (verbose) {
			System.out.println("Not a Resource: " + node.toString());
		}
		
		return null;
	}
	
	/**
	 * @param sol		a solution from a SPARQL query 
	 * @param varStr	a variable name included in the SPARQL query
	 * @return the URI of the node identified by the variable name. If the node is anonymous or is not a resource, the method return null
	 */
	public static String extractURI(QuerySolution sol, String varStr){
		return extractURI(sol, varStr, false);
	}
	
	/**
	 * Inform about the inclusion failure of a node set
	 * @param relName	a relation name
	 * @param list		a node name list 
	 */
	public static void printInclusionFailure(String relName, List<String> list){
		System.out.println("Inclusion FAILED in " + relName + ":");
		for (String element : list) {
			System.out.println(element);
		}
	}

}
