package model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.HasNoModelException;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.RDFWriter;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import exception.PrefixesNotAvailable;


import static controller.Starter.gConf;

/**
 * @author tuland
 * This class performs an ontology created step by step
 */
public class OntoBuilded extends OntoPack{
	public RDFWriter writer;
	public OntClass conceptClass;

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
		writer.setProperty("xmlbase", conf.getBase());

	}
	
	/**
	 * Create a new ontology and import it into the OntoPack instance
	 */
	public void setOnt() throws HasNoModelException{
		if (model == null) {
			throw new HasNoModelException(ont);
		}
		ont = model.createOntology(conf.getBase());
	}
	
	
	/**
	 * @param individualStr is a string representation of the individual to get
	 * @return a resource that represents an individual node in ontology model
	 */
	public Individual getIndividual(String individualStr) {
		return model.getIndividual(conf.getNameSpace() + individualStr);
	}
	
	/**
	 * @param individualStr is a string representation of the individual to write in
	 * @return a resource that represents an Individual node in this model
	 */
	public Individual writeIndividual(String individualStr) {
		return conceptClass.createIndividual(conf.getNameSpace() + individualStr);
	}
	
	/**
	 * @param individualStr is a string representation of the individual to write in
	 * @param indNameSpaceStr is a string representation of the individual to write in
	 * @return a resource that represents an individual node in this model
	 */
	public Individual writeIndividual(String individualStr, String indNameSpaceStr){
		return conceptClass.createIndividual(indNameSpaceStr + individualStr);
	}
	
 	/**
 	 * Print the statements list stored in the ontology model
 	 */
 	public void printListStatements() {
 		StmtIterator iter = model.listStatements();

 		// print out the predicate, subject and object of each statement
 	 	while (iter.hasNext()) {
 	 		Statement stmt      = iter.nextStatement();  // get next statement
 	 		Resource  subject   = stmt.getSubject();     // get the subject
 	 		Property  predicate = stmt.getPredicate();   // get the predicate
 	 		RDFNode   object    = stmt.getObject();      // get the object

 	 		System.out.print(subject.toString());
 	 		System.out.print(" " + predicate.toString() + " ");
 	 		if (object instanceof Resource) {
 	 			System.out.print(object.toString());
 	 		} else {
 	 			// object is a literal
 	 			System.out.print(" \"" + object.toString() + "\"");
 	 		}
 	 		    
 	 		System.out.println(" .");
 	 	} 
 	}
 	
 	/**
 	 * Save the ontology in a file (defined in the configuration file)
 	 */
 	public void saveFile(){
 		saveFile(gConf.getOutputPath() + conf.getName());
 	}
 	
 	/**
 	 * Save the ontology in a file 
 	 * @param fileStr is a string representation of the file address used to save
 	 */
 	public void saveFile(String fileStr){
 		OutputStream out = null;
 		try {
			out = new FileOutputStream(fileStr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.write(out, "RDF/XML-ABBREV", conf.getBase());
		
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
	

}
