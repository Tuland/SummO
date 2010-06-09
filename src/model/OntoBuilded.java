package model;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.HasNoModelException;
import com.hp.hpl.jena.rdf.model.RDFWriter;

import exception.PrefixesNotAvailable;

/**
 * @author tuland
 * This class performs an ontology created step by step
 */
public class OntoBuilded extends OntoPack{
	public RDFWriter writer;
	public OntClass summClass;

	public OntoBuilded(String confFile, String fileName) throws HasNoModelException {
		super(confFile, fileName);
		
		try {
			model.setNsPrefixes(conf.getPrefixes());
		} catch (PrefixesNotAvailable e) {
			e.messageFailure();
		}
		
		if (model == null) {
			throw new HasNoModelException(writer); 
		}
		
		writer = model.getWriter("RDF/XML-ABBREV");
		writer.setProperty("xmlbase", conf.base);

	}
	
	/**
	 * Create a new ontology and import it into the OntoPack instance
	 */
	public void setOnt() throws HasNoModelException{
		if (model == null) {
			throw new HasNoModelException(ont);
		}
		ont = model.createOntology(conf.base);
	}
	
	
	public Individual getIndividual(String conceptStr) {
		return model.getIndividual(conf.nameSpace + conceptStr);
	}

}
