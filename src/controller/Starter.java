/**
 * SummO: Ontology Summarization 
 * Copyright (C) 2010 Tullio Landoni (tuland@gmail.com)
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/. 
 */
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
		printLicense();
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
	
	private static void printLicense(){
		System.out.println(
			"SummO  Copyright (C) 2010  Tullio Landoni (tuland@gmail.com)\n" +
			"This program comes with ABSOLUTELY NO WARRANTY.\n" +
			"This is free software, and you are welcome to redistribute it " +
			"under certain conditions.\n" + 
			"Details: http://www.gnu.org/licenses/gpl.html \n\n");
	}


}
