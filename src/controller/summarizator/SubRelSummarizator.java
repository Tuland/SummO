/**
 * This file is part of SummO.
 *
 * SummO: Ontology Summarization 
 * Copyright (C) 2010 Tullio Landoni (tuland@gmail.com)
 * SummO is a free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/lgpl-3.0.html and http://www.gnu.org/licenses/gpl-3.0.html. 
 */
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
