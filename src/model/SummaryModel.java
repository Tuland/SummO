package model;

import java.io.File;
import java.io.FileNotFoundException;

import org.ho.yaml.Yaml;

import com.hp.hpl.jena.ontology.OntologyException;
import com.hp.hpl.jena.rdf.model.HasNoModelException;

/**
 * @author tuland
 *
 */
public class SummaryModel extends OntoLoaded{
	public PropSet prop;

	public SummaryModel(String confFile, String relFile) 
		throws HasNoModelException, OntologyException {
		
		super(confFile);
		
		try {
			prop = Yaml.loadType(new File(relFile), PropSet.class);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
