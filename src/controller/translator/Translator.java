package controller.translator;

import com.hp.hpl.jena.query.QuerySolution;

/**
 * @author tuland
 *
 */
public interface Translator {
	/**
	 * @param sol	a solution of a SPARQL query
	 */
	/**
	 * @param sol
	 */
	void translate(QuerySolution sol);
}
