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
public class PropSummaryModel extends OntoLoaded{
	public PropSet oProp;

	public PropSummaryModel(String confFile, String propFile) 
		throws HasNoModelException, OntologyException {
		
		super(confFile);
		
		try {
			oProp = Yaml.loadType(new File(propFile), PropSet.class);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
