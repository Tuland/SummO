package controller.helper;

import java.util.List;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

public class TranslationHelper {
	
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
	
	public static String extractURI(QuerySolution sol, String varStr){
		return extractURI(sol, varStr, false);
	}
	
	public static void informNotIncluded(String relTag, List<String> list){
		System.out.println("Inclusion FAILED in " + relTag + ":");
		for (String element : list) {
			System.out.println(element);
		}
	}

}
