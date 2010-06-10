package controller;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntProperty;

import model.ClassSummaryModel;
import model.OntoBuilded;
import model.PropSummaryModel;

import static controller.Starter.CONF_PATH;
import static controller.Starter.CONF_EXT;
import static controller.Starter.PROP_SUMMARY_MODEL;
import static controller.Starter.CLASS_SUMMARY_MODEL;

/**
 * @author tuland
 *
 */
public class Translator {
	
	private static final String SUMMARIZATION_CONF_FILE = 	CONF_PATH + 
															"summarization" +
															CONF_EXT;

	private OntoBuilded ontoToSumm;

	private PropSummaryModel propSM;
	private ClassSummaryModel classSM;

	public Translator(	String fileName, 
						PropSummaryModel pSM, 
						ClassSummaryModel cSM) {
		
		propSM = pSM;
		classSM = cSM;
		
		// TODO
		// - 2 tipi di costruttore: 
		//		a) con tre parametri[fileName, PropSummaryModel, ClassSummaryModel]
		//		b) con due parametri[fileName, SuperSummaryModel]
		//			- SuperAeria = Aeria + Skos  
		
		ontoToSumm = new OntoBuilded(SUMMARIZATION_CONF_FILE, fileName);

		ontoToSumm.model.setNsPrefix(CLASS_SUMMARY_MODEL, classSM.conf.getNameSpace());
		ontoToSumm.model.setNsPrefix(PROP_SUMMARY_MODEL, propSM.conf.getNameSpace());
		ontoToSumm.setOnt();
		ontoToSumm.ont.addImport(classSM.ont);
		ontoToSumm.ont.addImport(propSM.ont);
		/* Cosa metto al posto di Sum???? Controllare il file owl ---> Concept!!! 	
		   - Cambiare eventualmente anche summClass */
		ontoToSumm.conceptClass = ontoToSumm.model.createClass(classSM.model.getOntClass(	classSM.conf.getNameSpace() + 
																							classSM.oClass.getConcept()).toString());
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
		
		ObjectProperty dirRelProp = propSM.model.getObjectProperty(	propSM.conf.getNameSpace() + 
																	propSM.oProp.getDirectedRel() );
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
		
		ObjectProperty generalizeProp = propSM.model.getObjectProperty(	propSM.conf.getNameSpace() + 
																		propSM.oProp.getGeneralizeRel() );
		ontoToSumm.model.add(subjectInd, generalizeProp, objectInd);
	}



}
