package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.ho.yaml.Yaml;

import controller.bean.ConfBean;
import controller.printer.EmbeddingWithPPPrinter;
import controller.summarizator.DirRelSummarizator;
import controller.summarizator.GenRelSummarizator;
import controller.summarizator.SubRelSummarizator;
import controller.summarizator.Summarizator;


/**
 * This class is the launcher
 * @author tuland
 * 
 */
public class Starter {
	
	public static final String GENERAL_CONF_FILE = "Conf/conf.yml";
	public static ConfBean gConf;

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Load the configuration file
		try {
			gConf = Yaml.loadType(new File(GENERAL_CONF_FILE), ConfBean.class);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Define summarization
		List<Summarizator> summList = new ArrayList<Summarizator>();
		summList.add(new GenRelSummarizator());
		summList.add(new DirRelSummarizator());
		summList.add(new SubRelSummarizator());
		
		// Run summarization end print results
		SummManager manager = new SummManager(summList);
		manager.start(new EmbeddingWithPPPrinter()); 		
	}


}
