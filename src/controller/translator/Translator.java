package controller.translator;

import com.hp.hpl.jena.query.QuerySolution;

public interface Translator {
	/**
	 * @param sol	a solution of a SPARQL query
	 */
	void translate(QuerySolution sol);
	/**
	 * @param sol		a solution of a SPARQL query
	 * @param verbose	a flag the enable verbose mode.
	 */
	void translate(QuerySolution sol, boolean verbose);
}
