package controller;

import java.io.File;
import java.io.FileNotFoundException;

import org.ho.yaml.Yaml;

import model.ClassSummaryModel;
import model.PropSummaryModel;


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
		try {
			gConf = Yaml.loadType(new File(GENERAL_CONF_FILE), ConfBean.class);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PropSummaryModel propSM = new PropSummaryModel();
		ClassSummaryModel classSM = new ClassSummaryModel();

		// TODO QUI NON VA PASSATO UN SOLO FILE MA UNA LISTA DI FILE
		Translator translator = new Translator("prova.owl", propSM, classSM);
		
	}



}
