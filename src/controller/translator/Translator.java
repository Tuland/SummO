package controller.translator;

import com.hp.hpl.jena.query.QuerySolution;

public interface Translator {
	void translate(QuerySolution sol);
	void translate(QuerySolution sol, boolean verbose);
}
