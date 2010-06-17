package controller;

import java.io.File;
import java.io.FileNotFoundException;

import org.ho.yaml.Yaml;

import model.ClassSummaryModel;
import model.OntoLoaded;
import model.PropSummaryModel;

import static helper.IOHelper.listYamlFiles;

/**
 * @author tuland
 * This class is the launcher
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
		
		// Load 2 summary model (relations -> aeria, concept -> skos)
		PropSummaryModel propSM = new PropSummaryModel();
		ClassSummaryModel classSM = new ClassSummaryModel();
		
		// Iterate ontologies to inspect
		OntoLoaded ontoL = null;
		for (File cFile : listYamlFiles(gConf.getInputConfPath())) {
			ontoL = new OntoLoaded(cFile.toString());

			Summarizator summ = new Summarizator(ontoL.conf.getName(), propSM, classSM);
			
			// summ.getOntoSummarized().writeInd("AltraCosa");
			// summ.writeTripleDirRel("IlMioSoggetto", "LaMiaProprieta", "IlMioOggetto");
			
			summ.savePPOntoSummarized();
			
		}
			
	}


}
