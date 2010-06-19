package controller;

import com.hp.hpl.jena.query.QuerySolution;

public interface Translator {
	void translate(QuerySolution sol);
}
