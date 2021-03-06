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

import java.io.File;
import java.io.FileNotFoundException;

import model.bean.ClassSetBean;

import org.ho.yaml.Yaml;

import com.hp.hpl.jena.ontology.OntologyException;
import com.hp.hpl.jena.rdf.model.HasNoModelException;

import static controller.Starter.gConf;

/**
 * @author tuland
 *
 */
public class ClassSummaryModel extends OntoLoaded {
	private ClassSetBean oClass;

	/**
	 * @param confFile	a configuration file to initialize this summary mode 
	 * @throws HasNoModelException
	 * @throws OntologyException
	 */
	public ClassSummaryModel(String confFile) 
		throws HasNoModelException, OntologyException {
		
		super(gConf.getClassSMConfFile());
		
		try {
			oClass = Yaml.loadType(new File(confFile), ClassSetBean.class);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ClassSummaryModel() {
		this(gConf.getClassSMFieldFile());
	}

	public ClassSetBean getOClass() {
		return oClass;
	}

}

