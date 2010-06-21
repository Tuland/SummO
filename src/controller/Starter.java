package controller;

import java.io.File;
import java.io.FileNotFoundException;

import org.ho.yaml.Yaml;

import controller.bean.ConfBean;
import controller.query.DefaultDirRelQuery;
import controller.query.DefaultGenRelQuery;
import controller.query.SubRelOfDirRelQuery;
import controller.translator.DirRelTranslator;
import controller.translator.GenRelTranlator;

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
			
			//summ.getOntoSummarized().writeInd("http://www.pippo.net/ciao#AltraCosa");
			//summ.writeTripleDirRel("http://www.siti.it/ciao#IlMioSoggetto", "http://www.siti.it/ciao#LaMiaProprieta", "IlMioOggetto");
			//summ.writeTripleGeneralizeRel("IlMioSoggetto", "IlMioOggetto");
			
			StatementFinder sFinder;
			Migrator migrator;
			
			// Generalize
			
			DefaultGenRelQuery genQ = new DefaultGenRelQuery();
			GenRelTranlator genRelTranslator = new GenRelTranlator(summ, genQ);
			sFinder = new StatementFinder(genQ, ontoL);
			migrator = new Migrator(genRelTranslator, sFinder);
			migrator.start();
						
			// Directed relationship 
				
			DirRelTranslator dirRelTranslator;
				
			DefaultDirRelQuery dirRelQ = new DefaultDirRelQuery();
			dirRelTranslator = new DirRelTranslator(summ, dirRelQ);
			sFinder = new StatementFinder(dirRelQ, ontoL);
			migrator = new Migrator(dirRelTranslator, sFinder);
			migrator.start();
			
			// SubRelationship of Directed relationship
			
			SubRelOfDirRelQuery subRelQ = new SubRelOfDirRelQuery();
			dirRelTranslator = new DirRelTranslator(summ, subRelQ);
			sFinder = new StatementFinder(subRelQ, ontoL);
			migrator = new Migrator(dirRelTranslator, sFinder);
			migrator.start();
			
			
			// Save in a file
			summ.savePPEmbendingOntoSumm(ontoL);
			//summ.savePPOntoSummarized();
			
		}
			
	}


}
