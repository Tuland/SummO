package controller.printer;

import controller.OntoBuilder;
import model.OntoLoaded;


/**
 * @author tuland
 *
 */
public interface Printer {
	/**
	 * @param sourceOnto	a source ontology
	 * @param builder		an ontology builder
	 */
	void print(OntoLoaded sourceOnto, OntoBuilder builder);
}
