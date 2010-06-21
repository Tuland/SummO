package model;

import java.io.IOException;

import model.exception.PrefixesNotAvailable;

import viewer.DefaultOntoFormat;
import viewer.EmbeddedOntoFormat;
import viewer.OntoFormat;
import viewer.OntoPrinter;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.HasNoModelException;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.RDFWriter;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;




import static controller.Starter.gConf;
import static helper.IOHelper.readFileAsString;
import static model.helper.ProtegeHelper.PROTEGE_INIT_FILE;
import static model.helper.ProtegeHelper.convertTags;

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
		return model.getIndividual(conf.getNameSpace() + individualStr);
	}
	
	/**
	 * @param individualStr is a string representation of the individual to write in
	 * @return a resource that represents an Individual node in this model
	 */
	public Individual writeInd(String individualStr) {
		return conceptClass.createIndividual(individualStr);
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
 	 * @param outputFile			a string representation of the file address used to save
 	 * @param summaryModelNS	the name space referred to the summary model
 	 * @see PropSummaryModel
 	 */
 	public void saveProtegeFile(String outputFile, String summaryModelNS) {
 		String preamble = protegePreamble(summaryModelNS);
 		saveFile(outputFile, preamble);
 	}
 	
 	/**
 	 * Save the ontology in a file (defined in the configuration file)
 	 */
 	public void saveFile(){
 		saveFile(defalultOutputFile(), "");
 	}
 	
 	/**
 	 * Save the ontology in a file 
 	 * @param outputFile	a string representation of the file address used to save
 	 * @param preamble		a preamble to insert at the top of the file
 	 */
 	public void saveFile(String outputFile, String preamble){
 		OntoPrinter printer = new OntoPrinter(new DefaultOntoFormat(	model,
 																		conf.getBase(), 
 																		preamble));
 		printer.print(outputFile);
 	}
 	
	/**
	 * @param summaryModelNS	the name space of the summary model
	 * @return a Protege preamble with correct namespaces 
	 */
	public String protegePreamble(String summaryModelNS){
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
	 * @see model.helper.ProtegeHelper for the tags
	 * @return a Protege preamble with correct namespaces
	 */
	public String protegePreamble(String summaryModelNS, String protegeStr){
		return protegePreamble(summaryModelNS, protegeStr, gConf.getPropSummaryModel());
	}
	
	/**
	 * @param originalOnto		the pre-summarization ontology
	 * @param summaryModelNS	the name space of the summary model
	 */
	public void savePPEmbending(OntoLoaded originalOnto, String summaryModelNS){
		String preamble = protegePreamble(summaryModelNS);
		saveEmbending(defalultOutputFile(), originalOnto, preamble);
	}
	
	/**
	 * @param outputFile		a string representation of the file address used to save
	 * @param originalOnto		the pre-summarization ontology
	 * @param summaryModelNS	the name space of the summary model
	 */
	public void savePPEmbending(String outputFile, OntoLoaded originalOnto, String summaryModelNS){
		String preamble = protegePreamble(summaryModelNS);
		saveEmbending(outputFile, originalOnto, preamble);
	}
	
	/**
	 * @param originalOnto	the pre-summarization ontology
	 */
	public void saveEmbending(OntoLoaded originalOnto){
		saveEmbending(defalultOutputFile(), originalOnto, "");
	}
	
	
	/**
	 * @param outputFile	a string representation of the file address used to save
	 * @param originalOnto	the pre-summarization ontology
	 * @param preamble		a preamble to insert at the top of the file
	 */
	public void saveEmbending(String outputFile, OntoLoaded originalOnto, String preamble){
		OntoFormat internalFormat = new DefaultOntoFormat(	originalOnto.model,
															originalOnto.conf.getBase(),
															"");
		OntoFormat format = new EmbeddedOntoFormat(	model, 
													conf.getBase(),
													preamble,
													internalFormat);
 		OntoPrinter printer = new OntoPrinter(format);
 		printer.print(outputFile);
	}
	
	/**
	 * @param summaryModelNS	the name space of the summary model
	 * @param protegeStr 		a Protege preamble string with internal system tags
	 * @param prefixPropSM		a string representation of the prefix to assign to the (prop) summary model namespace
	 * @see model.helper.ProtegeHelper for the tags
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
