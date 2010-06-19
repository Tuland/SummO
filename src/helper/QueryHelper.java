package helper;

import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;

public class QueryHelper {
	
	public static String extractURI(QuerySolution sol, String varStr){
		RDFNode node = sol.get(varStr) ;
		if ( node.isResource() ) {
			Resource r = (Resource) node ;
				if ( ! r.isAnon() ) {
					return r.getURI();
				}
		}
		return null;
	}

}
