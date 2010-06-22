/**
 * SummO: Ontology Summarization 
 * Copyright (C) 2010 Tullio Landoni (tuland@gmail.com)
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/. 
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;

import model.bean.OntoConfBean;

import org.ho.yaml.Yaml;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * This class joins different ontology views:
 * <ul>
 * <li> configuration (general data)</li>
 * <li> model (Jena model)</li>
 * <li> ontology (real content provided by Jena)</li>
 * </ul>
 * @author tuland
 * 
 */
public class OntoPack {
	public OntoConfBean conf;
	public OntModel model;
	public Ontology ont;
		
	/**
	 * Load an existing ontology
	 * @param confFile	a configuration file path. This file must be a YAML file
	 */
	public OntoPack(String confFile) {
		System.out.println("** LOADING **");
		setConf(confFile);
		setModel();
	}
	
	/**
	 * Create an ontology
	 * @param fileName	a ontology name
	 * @param confFile	a configuration file path. This file must be a YAML file
	 */
	public OntoPack(String confFile, String fileName) {
		System.out.println("** BUILDING **");
		setConf(confFile, fileName);
		setModel();
	}
	
	/**
	 * Build general ontology infos
	 * @param confFile	a configuration file path. This file must be a YAML file
	 * @param fileName	the name of the ontology file to translate (if there's no one use an empty string) 
	 */
	private void setConf(String confFile, String fileName ){
		// YAML
		// Read AERIA config to load the ontology
		try {
			conf = Yaml.loadType(new File(confFile), OntoConfBean.class);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		conf.updateFields(fileName);
	}
	
	/**
	 * Build general ontology infos
	 * @param confFile	a configuration file path. This file must be a YAML file
	 */
	private void setConf(String confFile){
		setConf(confFile, "" );
	}
	
	/**
	 * Build the ontology model
	 */
	private void setModel(){
		model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
	}
		
}
