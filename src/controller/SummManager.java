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

import static helper.IOHelper.listYamlFiles;
import static controller.Starter.gConf;

import java.io.File;
import java.util.List;

import controller.printer.Printer;
import controller.summarizator.Summarizator;

import model.ClassSummaryModel;
import model.OntoLoaded;
import model.PropSummaryModel;

/**
 * This class manage the summarization process
 * @author tuland
 *
 */
public class SummManager {
	
	private List<Summarizator> summList;
	private String inputConfPath;
	private PropSummaryModel propSM;
	private ClassSummaryModel classSM;
	
	/**
	 * @param inputConfPath  a string representation of path that refers a configuration folder. 
	 * This folder includes configuration files that identifies source ontologies   
	 * @param summList		a list that contains methods to apply in the summarization process
	 */
	public SummManager(String inputConfPath, List<Summarizator> summList) {
		// Load 2 summary model (relations -> aeria, concept -> skos)
		this.propSM = new PropSummaryModel();
		this.classSM = new ClassSummaryModel();
		
		this.summList = summList;
		this.inputConfPath = inputConfPath;
	}
	
	/**
	 * @param summList	a list that contains methods to apply in the summarization process
	 */
	public SummManager(List<Summarizator> summList) {
		this(gConf.getInputConfPath(), summList);
	}
	
	
	/**
	 * Start the summarization process
	 * @param printer	a model to print the summarized ontology 
	 */
	public void start(Printer printer){
		OntoLoaded ontoL = null;
		for (File cFile : listYamlFiles(inputConfPath)) {
			ontoL = new OntoLoaded(cFile.toString());

			OntoBuilder builder = new OntoBuilder(ontoL.conf.getName(), propSM, classSM);
			
			for (Summarizator summ : summList) {
				summ.summarize(ontoL, builder);
			}
			
			// Save in a file
			printer.print(ontoL, builder);
			
		}
	}
	
}
