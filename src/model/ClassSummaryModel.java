package model;

import java.io.File;
import java.io.FileNotFoundException;

import org.ho.yaml.Yaml;

import com.hp.hpl.jena.ontology.OntologyException;
import com.hp.hpl.jena.rdf.model.HasNoModelException;

public class ClassSummaryModel extends OntoLoaded {
	public ClassSetBean oClass;

	public ClassSummaryModel(String confFile, String propFile) 
		throws HasNoModelException, OntologyException {
		
		super(confFile);
		
		try {
			oClass = Yaml.loadType(new File(propFile), ClassSetBean.class);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
