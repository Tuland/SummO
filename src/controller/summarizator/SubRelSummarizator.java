package controller.summarizator;

import model.OntoLoaded;
import controller.Migrator;
import controller.OntoBuilder;
import controller.NodeFinder;
import controller.query.SubRelOfDirRelQuery;
import controller.translator.DirRelTranslator;

/**
 * @author tuland
 *
 */
public class SubRelSummarizator implements Summarizator{

	/* (non-Javadoc)
	 * @see controller.summarizator.Summarizator#summarize(model.OntoLoaded, controller.OntoBuilder)
	 */
	public void summarize(OntoLoaded ontoL, OntoBuilder builder) {
		SubRelOfDirRelQuery subRelQ = new SubRelOfDirRelQuery();
		DirRelTranslator dirRelTranslator = new DirRelTranslator(builder, subRelQ);
		NodeFinder finder = new NodeFinder(subRelQ, ontoL);
		Migrator migrator = new Migrator(dirRelTranslator, finder);
		migrator.start();
	}
	
}
