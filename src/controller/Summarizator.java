package controller;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntProperty;

import model.ClassSummaryModel;
import model.OntoBuilded;
import model.OntoLoaded;
import model.PropSummaryModel;

import static controller.Starter.gConf;

/**
 * @author tuland
 *
 */
public class Summarizator {

	private OntoBuilded ontoSummarized;

	private PropSummaryModel propSM;
	private ClassSummaryModel classSM;
	
	/**
	 * @param fileName 		the file name of ontology to translate  
	 * @param pSM 			a PropSummaryModel instance (ontology mediator in the translation) 
	 * @param PropSMStr 	a string that identifies the PropSummaryModel instance
	 * @param cSM 			a ClassSummaryModel instance (ontology mediator in the translation)
	 * @param ClassSMStr 	a string that identifies the ClassSummaryModel instance
	 */
	public Summarizator(	String fileName, 
						PropSummaryModel pSM, 
						String PropSMStr, 
						ClassSummaryModel cSM, 
						String ClassSMStr) {
		
		propSM = pSM;
		classSM = cSM;
		
		ontoSummarized = new OntoBuilded(gConf.getSummConfFile(), fileName);

		ontoSummarized.model.setNsPrefix(ClassSMStr, classSM.conf.getNameSpace());
		ontoSummarized.model.setNsPrefix(PropSMStr, propSM.conf.getNameSpace());
		ontoSummarized.setOnt();
		ontoSummarized.ont.addImport(classSM.ont);
		ontoSummarized.ont.addImport(propSM.ont);
		
		// TODO build a not verbose modality
		/*
		Protege' doesn't show individuals when the class is getted from aeria/skos
		
		ontoSummarized.conceptClass = classSM.model.getOntClass(	classSM.conf.getNameSpace() + 
																	classSM.getOClass().getConcept() );
		*/
		
		ontoSummarized.conceptClass = ontoSummarized.model.createClass(classSM.model.getOntClass(	classSM.conf.getNameSpace() + 
																									classSM.getOClass().getConcept()).toString());
		
	}

	/**
	 * @param fileName 	the file name of ontology to translate
	 * @param pSM 		a PropSummaryModel instance (ontology mediator in the translation) 
	 * @param cSM 		a ClassSummaryModel instance (ontology mediator in the translation)
	 */
	public Summarizator(	String fileName, 
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
	 * @param subjectStr 	the subject of the statement 
	 * @param propertyStr	the property of the statement
	 * @param objectStr 	the property of the statement
	 */
	public void writeTripleDirRel(String subjectStr, String propertyStr, String objectStr) {
		
		Individual subjectInd = ontoSummarized.writeInd(subjectStr);
		Individual objectInd = ontoSummarized.writeInd(objectStr);
		
		// Individual subjectInd = ontoSummarized.getInd(subjectStr);
		// Individual objectInd = ontoSummarized.getInd(objectStr);
		
		ObjectProperty dirRelProp = propSM.model.getObjectProperty(	propSM.conf.getNameSpace() + 
																	propSM.getOProp().getDirectedRel() );
		OntProperty relationProp = ontoSummarized.model.createObjectProperty(	ontoSummarized.conf.getNameSpace() +
																				propertyStr );
		dirRelProp.addSubProperty(relationProp);
		
		
		if (subjectInd == null ) {
			System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		}
		
		
		ontoSummarized.model.add(subjectInd, relationProp, objectInd);
	}
	
	/**
	 * Write a RDF statement with a generalize relation
	 * @param subjectStr	the subject of the statement
	 * @param objectStr		the subject of the statement
	 */
	public void writeTripleGeneralizeRel(String subjectStr, String objectStr){
		Individual subjectInd = ontoSummarized.getInd(subjectStr);
		Individual objectInd = ontoSummarized.getInd(objectStr);
		
		ObjectProperty generalizeProp = propSM.model.getObjectProperty(	propSM.conf.getNameSpace() + 
																		propSM.getOProp().getGeneralizeRel() );
		
		ontoSummarized.model.add(subjectInd, generalizeProp, objectInd);
		
		 
	}

	/**
	 * @return ontology after (total/partial) summarization
	 */
	public OntoBuilded getOntoSummarized() {
		return ontoSummarized;
	}
	
	/**
	 * Save the summarized ontology in a file (in the default location). This method apply the protege' preamble
	 * @see ConfBean
	 * @see OntoLoaded
	 */
	public void savePPOntoSummarized(){
		ontoSummarized.saveProtegeFile(propSM.conf.getNameSpace());
	}
	
	/**
	 * Save the summarized ontology in a file (in the default location). This method doesn't apply the protege' preamble
	 * @see ConfBean
	 * @see OntoLoaded
	 */
	public void saveOntoSummarized(){
		ontoSummarized.saveFile();
	}


}
