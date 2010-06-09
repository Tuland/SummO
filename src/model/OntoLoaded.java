package model;

import com.hp.hpl.jena.ontology.OntologyException;
import com.hp.hpl.jena.rdf.model.HasNoModelException;

/**
 * @author tuland
 * This class performs an ontology completely loaded from a file
 */
public class OntoLoaded extends OntoPack{

	public OntoLoaded(String confFile) throws HasNoModelException, OntologyException {
		super(confFile);
		
		if (model == null) {
			throw new HasNoModelException(ont); 
		}
		
		loadOnt(conf.url);
		
		if (ont == null) {
			throw new OntologyException("Ontology is NULL");
		}
	}
	
	/**
	 * Build the ontology.
	 * @param url is a string representation of the url to read from
	 */
	private void loadOnt(String url){
		model.read(url, "RDF/XML");
		ont = model.getOntology(conf.base);
	}

}
