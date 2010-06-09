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
	public RelAndConceptSet relAndConcept;

	public SummaryModel(String confFile, String relFile) throws HasNoModelException, OntologyException {
		super(confFile);
		
		try {
			relAndConcept = Yaml.loadType(new File(relFile), RelAndConceptSet.class);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}