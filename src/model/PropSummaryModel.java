package model;

import java.io.File;
import java.io.FileNotFoundException;

import org.ho.yaml.Yaml;

import com.hp.hpl.jena.ontology.OntologyException;
import com.hp.hpl.jena.rdf.model.HasNoModelException;

import static controller.Starter.gConf;

/**
 * @author tuland
 *
 */
public class PropSummaryModel extends OntoLoaded{
	public PropSetBean oProp;

	public PropSummaryModel(String confFile) 
		throws HasNoModelException, OntologyException {
		
		super(gConf.getPropSMConfFile());
		
		try {
			oProp = Yaml.loadType(	new File(confFile), 
									PropSetBean.class);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public PropSummaryModel() {
		this(gConf.getPropSMFieldFile());
	}

}
