package controller.summarizator;

import controller.OntoBuilder;
import model.OntoLoaded;

/**
 * @author tuland
 *
 */
public interface Summarizator {
	/**
	 * @param ontoL		a source ontology
	 * @param builder	an ontology builder
	 */
	void summarize(OntoLoaded ontoL, OntoBuilder builder);
}
