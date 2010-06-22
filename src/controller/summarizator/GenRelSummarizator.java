package controller.summarizator;

import controller.Migrator;
import controller.OntoBuilder;
import controller.NodeFinder;
import controller.query.DefaultGenRelQuery;
import controller.translator.GenRelTranlator;
import model.OntoLoaded;

/**
 * @author tuland
 *
 */
public class GenRelSummarizator implements Summarizator{
	
	/* (non-Javadoc)
	 * @see controller.summarizator.Summarizator#summarize(model.OntoLoaded, controller.OntoBuilder)
	 */
	public void summarize(OntoLoaded ontoL, OntoBuilder builder){		
		DefaultGenRelQuery genQ = new DefaultGenRelQuery();
		GenRelTranlator genRelTranslator = new GenRelTranlator(builder, genQ);
		NodeFinder finder = new NodeFinder(genQ, ontoL);
		Migrator migrator = new Migrator(genRelTranslator, finder);
		migrator.start();
	}

}
