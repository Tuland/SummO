package controller;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntProperty;

import model.ClassSummaryModel;
import model.OntoBuilded;
import model.PropSummaryModel;

import static controller.Starter.gConf;

/**
 * @author tuland
 *
 */
public class Translator {

	private OntoBuilded ontoToSumm;

	private PropSummaryModel propSM;
	private ClassSummaryModel classSM;
	
	/**
	 * @param fileName is the file name of ontology to translate  
	 * @param pSM is a PropSummaryModel instance (ontology mediator in the translation) 
	 * @param PropSMStr is a string that identifies the PropSummaryModel instance
	 * @param cSM is a ClassSummaryModel instance (ontology mediator in the translation)
	 * @param ClassSMStr is a string that identifies the ClassSummaryModel instance
	 */
	public Translator(	String fileName, 
						PropSummaryModel pSM, 
						String PropSMStr, 
						ClassSummaryModel cSM, 
						String ClassSMStr) {
		
		propSM = pSM;
		classSM = cSM;
		
		// TODO
		// - 2 tipi di costruttore: 
		//		a) con tre parametri[fileName, PropSummaryModel, ClassSummaryModel]
		//		b) con due parametri[fileName, SuperSummaryModel]
		//			- SuperAeria = Aeria + Skos  
		
		ontoToSumm = new OntoBuilded(gConf.getSummConfFile(), fileName);

		ontoToSumm.model.setNsPrefix(ClassSMStr, classSM.conf.getNameSpace());
		ontoToSumm.model.setNsPrefix(PropSMStr, propSM.conf.getNameSpace());
		ontoToSumm.setOnt();
		ontoToSumm.ont.addImport(classSM.ont);
		ontoToSumm.ont.addImport(propSM.ont);

		ontoToSumm.conceptClass = ontoToSumm.model.createClass(classSM.model.getOntClass(	classSM.conf.getNameSpace() + 
																							classSM.oClass.getConcept()).toString());
	}

	/**
	 * @param fileName is the file name of ontology to translate
	 * @param pSM is a PropSummaryModel instance (ontology mediator in the translation) 
	 * @param cSM is a ClassSummaryModel instance (ontology mediator in the translation)
	 */
	public Translator(	String fileName, 
						PropSummaryModel pSM, 
						ClassSummaryModel cSM) {
		this(	fileName, 
				pSM, 
				gConf.getPropSummaryModel(), 
				cSM, 
				gConf.getClassSummaryModel());

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
