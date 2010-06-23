/**
 * This file is part of SummO.
 *
 * SummO: Ontology Summarization 
 * Copyright (C) 2010 Tullio Landoni (tuland@gmail.com)
 * SummO is a free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/lgpl-3.0.html and http://www.gnu.org/licenses/gpl-3.0.html. 
 */
package model;

import com.hp.hpl.jena.ontology.OntologyException;
import com.hp.hpl.jena.rdf.model.HasNoModelException;

/**
 * This class performs an ontology completely loaded from a file
 * @author tuland
 * 
 */
public class OntoLoaded extends OntoPack{

	public OntoLoaded(String confFile) throws HasNoModelException, OntologyException {
		super(confFile);
		
		if (model == null) {
			throw new HasNoModelException(ont); 
		}
		
		loadOnt(conf.getUrl());
		
		if (ont == null) {
			throw new OntologyException("Ontology is NULL");
		}
	}
	
	/**
	 * Build the ontology.
	 * @param url	a string representation of the url to read from
	 */
	private void loadOnt(String url){
		model.read(url, "RDF/XML");
		ont = model.getOntology(conf.getBase());
	}

}

