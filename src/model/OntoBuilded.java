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
import static helper.ProtegeHelper.convertTags;
import static helper.ProtegeHelper.PROTEGE_INIT_FILE;
import static helper.IOHelper.readFileAsString;

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
	 * @param individualStr	a string representation of the individual to get
	 * @return a resource that represents an individual node in ontology model
	 */
	public Individual getInd(String individualStr) {
		System.out.println(conf.getNameSpace() + individualStr + "!!!!!!!!!!!!!!!!!!!!!!");
		return model.getIndividual(conf.getNameSpace() + individualStr);
	}
	
	/**
	 * @param individualStr is a string representation of the individual to write in
	 * @return a resource that represents an Individual node in this model
	 */
	public Individual writeInd(String individualStr) {
		return conceptClass.createIndividual(conf.getNameSpace() + individualStr);
	}
	
	/**
	 * @param individualStr		a string representation of the individual to write in
	 * @param indNameSpaceStr	a string representation of the individual to write in
	 * @return a resource that represents an individual node in this model
	 */
	public Individual writeInd(String individualStr, String indNameSpaceStr){
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
 	 * @param summaryModelNS	a name space referred to the summary model
 	 * @see PropSummaryModel
 	 */
 	public void saveProtegeFile(String summaryModelNS) {
 		saveProtegeFile(defalultOutputFile(), summaryModelNS);
 	}
 	
 	/**
 	 * @param fileStr			a string representation of the file address used to save
 	 * @param summaryModelNS	the name space referred to the summary model
 	 * @see PropSummaryModel
 	 */
 	public void saveProtegeFile(String fileStr, String summaryModelNS) {
 		String preamble = protegePreamble(summaryModelNS);
 		saveFile(fileStr, preamble);
 	}
 	
 	/**
 	 * Save the ontology in a file (defined in the configuration file)
 	 */
 	public void saveFile(){
 		saveFile(defalultOutputFile(), "");
 	}
 	
 	/**
 	 * Save the ontology in a file 
 	 * @param fileStr	a string representation of the file address used to save
 	 * @param preamble	a preamble to insert at the top of the file
 	 */
 	public void saveFile(String fileStr, String preamble){
 		OutputStream out = null;
 		try {
			out = new FileOutputStream(fileStr);
			out.write(preamble.getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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
 	
	/**
	 * @param summaryModelNS	the name space of the summary model
	 * @return a Protege preamble with correct namespaces 
	 */
	private String protegePreamble(String summaryModelNS){
		String pp = null;
		try {
			pp = readFileAsString(PROTEGE_INIT_FILE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return protegePreamble(summaryModelNS, pp);
	}
	
	/**
	 * @param summaryModelNS	the name space of the summary model
	 * @param protegeStr		a Protege preamble string with internal system tags
	 * @see helper.ProtegeHelper for the tags
	 * @return a Protege preamble with correct namespaces 
	 */
	private String protegePreamble(String summaryModelNS, String protegeStr){
		return protegePreamble(summaryModelNS, protegeStr, gConf.getPropSummaryModel());
	}
	
	
	/**
	 * @param summaryModelNS	the name space of the summary model
	 * @param protegeStr 		a Protege preamble string with internal system tags
	 * @param prefixPropSM		a string representation of the prefix to assign to the (prop) summary model namespace
	 * @see helper.ProtegeHelper for the tags
	 * @return a Protege preamble with correct namespaces 
	 */
	private String protegePreamble(String summaryModelNS, String protegeStr, String prefixPropSM){
		return convertTags(protegeStr, prefixPropSM, summaryModelNS, conf.getNameSpace());
	}
	
	/**
	 * @return the address referred to the default output file
	 */
	private String defalultOutputFile() {
		return gConf.getOutputPath()  + conf.getName();
	}
	
}
