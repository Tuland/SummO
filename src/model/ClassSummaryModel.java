package model;

import java.io.File;
import java.io.FileNotFoundException;

import org.ho.yaml.Yaml;

import com.hp.hpl.jena.ontology.OntologyException;
import com.hp.hpl.jena.rdf.model.HasNoModelException;

import static controller.Starter.gConf;

public class ClassSummaryModel extends OntoLoaded {
	public ClassSetBean oClass;

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

}
