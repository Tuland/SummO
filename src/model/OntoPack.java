package model;

import java.io.File;
import java.io.FileNotFoundException;

import org.ho.yaml.Yaml;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * @author tuland
 * This class joins different ontology views: 
 * - configuration (general data)
 * - model (Jena model)
 * - ontology (real content provided by Jena)
 */
public class OntoPack {
	public OntoConfBean conf;
	public OntModel model;
	public Ontology ont;
		
	/**
	 * Load an existing ontology
	 * @param confFile is the configuration file path. This file must be a YAML file
	 */
	public OntoPack(String confFile) {
		System.out.println("** LOADING **");
		setConf(confFile);
		setModel();
	}
	
	/**
	 * Create an ontology
	 * @param fileName is the ontology name
	 * @param confFile is the configuration file path. This file must be a YAML file
	 */
	public OntoPack(String confFile, String fileName) {
		System.out.println("** BUILDING **");
		setConf(confFile, fileName);
		setModel();
	}
	
	
	/**
	 * Build general ontology infos
	 * @param confFile is the configuration file path. This file must be a YAML file
	 * @param fileName is the name of the ontology file to translate (if there's no one use an empty string) 
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
	 * @param confFile is the configuration file path. This file must be a YAML file
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
