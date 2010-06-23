/**
 * This file is part of SummO.
 *
 * SummO: Ontology Summarization 
 * Copyright (C) 2010 Tullio Landoni (tuland@gmail.com)
 * SummO is a free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/. 
 */
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
 * This class builds the summarized ontology
 * @author tuland
 *
 */
public class OntoBuilder {

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
	public OntoBuilder(	String fileName, 
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
	public OntoBuilder(	String fileName, 
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
		
		ObjectProperty dirRelProp = propSM.model.getObjectProperty(	propSM.conf.getNameSpace() + 
																	propSM.getOProp().getDirectedRel() );
		OntProperty relationProp = ontoSummarized.model.createObjectProperty( propertyStr );
		dirRelProp.addSubProperty(relationProp);
		
		ontoSummarized.model.add(subjectInd, relationProp, objectInd);
	}
	
	/**
	 * Write a RDF statement with a generalize relation
	 * @param subjectStr	the subject of the statement
	 * @param objectStr		the subject of the statement
	 */
	public void writeTripleGeneralizeRel(String subjectStr, String objectStr){
		Individual subjectInd = ontoSummarized.writeInd(subjectStr);
		Individual objectInd = ontoSummarized.writeInd(objectStr);
		
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
	 * @see controller.bean.ConfBean
	 * @see model.OntoLoaded
	 */
	public void savePPOntoSummarized(){
		ontoSummarized.saveProtegeFile(propSM.conf.getNameSpace());
	}
	
	/**
	 * Save the summarized ontology in a file (in the default location). This method doesn't apply the protege' preamble
	 * @see controller.bean.ConfBean
	 * @see model.OntoLoaded
	 */
	public void saveOntoSummarized(){
		ontoSummarized.saveFile();
	}
	
	/**
	 * Save a source ontology embedding the ontology summarized
	 * @param sourceOnto	a source ontology
	 */
	public void saveEmbendingOntoSumm(OntoLoaded sourceOnto){
		ontoSummarized.saveEmbending(sourceOnto);
	}
	
	/**
	 * Save a source ontology embedding the ontology summarized (with protege' preamble)
	 * @param sourceOnto	a source ontology
	 */
	public void savePPEmbendingOntoSumm(OntoLoaded sourceOnto){
		ontoSummarized.savePPEmbending(sourceOnto, propSM.conf.getNameSpace());
	}

}
