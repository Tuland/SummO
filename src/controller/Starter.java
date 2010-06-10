package controller;

import model.ClassSummaryModel;
import model.PropSummaryModel;


/**
 * @author tuland
 * This class is the launcher
 */
public class Starter {
	
	public static final String CONF_PATH = "Conf/";
	private static final String FIELD_CONF_PATH = "Conf/Rel/";
	
	
	public static final String PROP_SUMMARY_MODEL = "aeria";
	public static final String CLASS_SUMMARY_MODEL = "skos";
	
	public static final String CONF_EXT = ".yml";
	
	private static final String PROP_SM_CONF_FILE = CONF_PATH + 
													PROP_SUMMARY_MODEL + 
													CONF_EXT;
	
	private static final String PROP_SM_FIELD_FILE = 	FIELD_CONF_PATH +
														PROP_SUMMARY_MODEL +
														CONF_EXT;

	
	private static final String CLASS_SM_CONF_FILE = 	CONF_PATH + 
														CLASS_SUMMARY_MODEL + 
														CONF_EXT;

	private static final String CLASS_SM_FIELD_FILE = 	FIELD_CONF_PATH +
														CLASS_SUMMARY_MODEL +
														CONF_EXT;
	


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Inizializzare i summaryModel QUI
		
		// TODO QUI NON VA PASSATO UN SOLO FILE MA UNA LISTA DI FILE
		PropSummaryModel propSM = new PropSummaryModel(PROP_SM_CONF_FILE, PROP_SM_FIELD_FILE);
		ClassSummaryModel classSM = new ClassSummaryModel(CLASS_SM_CONF_FILE, CLASS_SM_FIELD_FILE);

		Translator translator = new Translator("prova.owl", propSM, classSM);
		
	}



}
