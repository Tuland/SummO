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
