/**
 * SummO: Ontology Summarization 
 * Copyright (C) 2010 Tullio Landoni (tuland@gmail.com)
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/. 
 */
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
