package controller.summarizator;

import model.OntoLoaded;
import controller.Migrator;
import controller.OntoBuilder;
import controller.NodeFinder;
import controller.query.DefaultDirRelQuery;
import controller.translator.DirRelTranslator;

/**
 * @author tuland
 *
 */
public class DirRelSummarizator implements Summarizator{

	/* (non-Javadoc)
	 * @see controller.summarizator.Summarizator#summarize(model.OntoLoaded, controller.OntoBuilder)
	 */
	public void summarize(OntoLoaded ontoL, OntoBuilder builder) {
		DefaultDirRelQuery dirRelQ = new DefaultDirRelQuery();
		DirRelTranslator dirRelTranslator = new DirRelTranslator(builder, dirRelQ);
		NodeFinder finder = new NodeFinder(dirRelQ, ontoL);
		Migrator migrator = new Migrator(dirRelTranslator, finder);
		migrator.start();
	}
	
}
