package controller;

import java.io.Serializable;

public class ConfBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2200179586991823121L;
	
	private String confPath;
	private String confExt;
	private String fieldConfPath;
	private String outputPath;
	private String propSummaryModel;
	private String classSummaryModel;
	
	private String propSMConfFile;
	private String propSMFieldFile;
	
	private String classSMConfFile;
	private String classSMFieldFile;
	
	private String summNameConfFile;
	private String summConfFile;
	
	public ConfBean() {
			
	}
		

	public String getConfPath() {
		return confPath;
	}

	public void setConfPath(String confPath) {
		this.confPath = confPath;
	}

	public String getConfExt() {
		return confExt;
	}

	public void setConfExt(String confExt) {
		this.confExt = confExt;
	}

	public String getFieldConfPath() {
		return fieldConfPath;
	}

	public void setFieldConfPath(String fieldConfPath) {
		this.fieldConfPath = fieldConfPath;
	}

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public String getPropSummaryModel() {
		return propSummaryModel;
	}

	public void setPropSummaryModel(String propSummaryModel) {
		this.propSummaryModel = propSummaryModel;
	}

	public String getClassSummaryModel() {
		return classSummaryModel;
	}

	public void setClassSummaryModel(String classSummaryModel) {
		this.classSummaryModel = classSummaryModel;
	}
	
	public String getSummNameConfFile() {
		return summNameConfFile;
	}


	public void setSummNameConfFile(String summNameFile) {
		this.summNameConfFile = summNameFile;
	}
	
	public String getPropSMConfFile() {
		if (propSMConfFile == null) {
			buildConf();
		}
		
		return propSMConfFile;
	}


	public String getPropSMFieldFile() {
		if (propSMFieldFile == null) {
			buildConf();
		}
		return propSMFieldFile;
	}


	public String getClassSMConfFile() {
		if (classSMConfFile == null) {
			buildConf();
		}
		return classSMConfFile;
	}


	public String getClassSMFieldFile() {
		if (classSMFieldFile == null) {
			buildConf();
		}
		return classSMFieldFile;
	}
	
	public String getSummConfFile() {
		if (summConfFile == null) {
			buildConf();
		}
		return summConfFile;
	}
	
	private void buildConf(){
		propSMConfFile = 	confPath + 
							propSummaryModel + 
							confExt;
		propSMFieldFile = 	fieldConfPath + 
							propSummaryModel + 
							confExt;
		
		classSMConfFile = 	confPath + 
							classSummaryModel + 
							confExt;
		classSMFieldFile = 	fieldConfPath + 
							classSummaryModel + 
							confExt;
		summConfFile =  confPath +
						summNameConfFile +
						confExt;
	}
	
}
