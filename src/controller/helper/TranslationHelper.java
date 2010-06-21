package controller.helper;

import java.util.List;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

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
