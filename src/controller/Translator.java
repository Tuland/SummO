package controller;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntProperty;

import model.OntoBuilded;
import model.PropSummaryModel;

/**
 * @author tuland
 *
 */
public class Translator {
	
	private static final String CONF_PATH = "Conf/";
	private static final String REL_CONF_PATH = "Conf/Rel/";
	private static final String CONF_EXT = ".yml";
	private static final String SUMMARY_MODEL_CONF_FILE = 	CONF_PATH + 
															Starter.SUMMARY_MODEL + 
															CONF_EXT;
	private static final String SUMMARIZATION_CONF_FILE = 	CONF_PATH + 
															"summarization" +
															CONF_EXT;
	
	private static final String SUMMARY_MODEL_REL_FILE = 	REL_CONF_PATH +
															Starter.SUMMARY_MODEL +
															CONF_EXT;

	private PropSummaryModel summModel;
	private OntoBuilded ontoToSumm;


	public Translator(String fileName) {
		summModel = new PropSummaryModel(SUMMARY_MODEL_CONF_FILE, SUMMARY_MODEL_REL_FILE);
		// TODO
		// Devo importare anche SKOS?
		// - spostare i new SummaryModel in Starter
		// - parametrizzare questo costruttore con aeria + skos
		// - 2 tipi di costruttore: 
		//		a) con tre parametri[fileName, SummaryModel, SupportSummaryModel]
		//		b) con due parametri[fileName, SuperSummaryModel]
		//			- SuperAeria = Aeria + Skos  
		// * E' possibile? Non so dove prendere concept e dove rel! Potrebbero provenire dalla stessa risorsa!
		// * Quello che crea probblemi e' SOLO summary.summClass = summary.model.createClass ! --> usa SKOS non aeria

		
		ontoToSumm = new OntoBuilded(SUMMARIZATION_CONF_FILE, fileName);
		ontoToSumm.model.setNsPrefix(summModel.conf.getName(), summModel.conf.getBase());
		ontoToSumm.setOnt();
		ontoToSumm.ont.addImport(summModel.ont);
		/* Cosa metto al posto di Sum???? Controllare il file owl ---> Concept!!! 	
		   - Cambiare eventualmente anche summClass */
		ontoToSumm.summClass = ontoToSumm.model.createClass(summModel.model.getOntClass(summModel.conf.getNameSpace() + 
																						"Summ").toString());

	}
	
	/**
	 * Write a RDF statement with a direct relation
	 * @param subjectStr is the subject of the statement 
	 * @param propertyStr is the property of the statement
	 * @param objectStr is the property of the statement
	 */
	public void writeTripleDirRel(String subjectStr, String propertyStr, String objectStr) {
		Individual subjectInd = ontoToSumm.getIndividual(subjectStr);
		Individual objectInd = ontoToSumm.getIndividual(objectStr);
		
		ObjectProperty dirRelProp = summModel.model.getObjectProperty(	summModel.conf.getNameSpace() + 
																		summModel.oProp.getDirectedRel() );
		OntProperty relationProp = ontoToSumm.model.createObjectProperty(	ontoToSumm.conf.getNameSpace() +
																			propertyStr );
		
		dirRelProp.addSubProperty(relationProp);
		ontoToSumm.model.add(subjectInd, relationProp, objectInd);
	}
	
	/**
	 * Write a RDF statement with a generalize relation
	 * @param subjectStr is the subject of the statement
	 * @param objectStr is the subject of the statement
	 */
	public void writeTripleGeneralizeRel(String subjectStr, String objectStr){
		Individual subjectInd = ontoToSumm.getIndividual(subjectStr);
		Individual objectInd = ontoToSumm.getIndividual(objectStr);
		
		ObjectProperty generalizeProp = summModel.model.getObjectProperty(	summModel.conf.getNameSpace() + 
																			summModel.oProp.getGeneralizeRel() );
		ontoToSumm.model.add(subjectInd, generalizeProp, objectInd);
	}



}
