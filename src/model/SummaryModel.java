package model;

import static controller.Starter.gConf;

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
	private PropSetBean oProp;
	private ClassSetBean oClass;

	/**
	 * @param confFile is the configuration file to initialize this summary mode
	 * @throws HasNoModelException
	 * @throws OntologyException
	 */
	public SummaryModel(String confFile) 
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
	
	public SummaryModel() {
		this(gConf.getUnifiedSMFieldFile());
	}

	public PropSetBean getOProp() {
		return oProp;
	}

	public ClassSetBean getOClass() {
		return oClass;
	}

}
