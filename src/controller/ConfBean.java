package controller;

import java.io.Serializable;
import static helper.PathHelper.buildFileAddr;

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
	private String unifiedSummaryModel;
	
	private String propSMConfFile;
	private String propSMFieldFile;
	
	private String classSMConfFile;
	private String classSMFieldFile;
	
	private String unifiedSMFieldFile;
	private String unifiedSMConfFile;
	
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
	
	public String getUnifiedSMFieldFile() {
		return unifiedSMFieldFile;
	}
	
	public String getSummConfFile() {
		if (summConfFile == null) {
			buildConf();
		}
		return summConfFile;
	}
	
	public void setUnifiedSummaryModel(String unifiedSummaryModel) {
		this.unifiedSummaryModel = unifiedSummaryModel;
	}


	public String getUnifiedSMConfFile() {
		return unifiedSMConfFile;
	}
	
	private void buildConf(){
		propSMConfFile = 	buildFileAddr(	confPath, 
											propSummaryModel, 
											confExt);
		propSMFieldFile = 	buildFileAddr(	fieldConfPath, 
											propSummaryModel, 
											confExt);
		classSMConfFile = 	buildFileAddr(	confPath, 
											classSummaryModel,
											confExt);
		classSMFieldFile = 	buildFileAddr(	fieldConfPath, 
											classSummaryModel,			
											confExt);
		unifiedSMConfFile = buildFileAddr(	confPath, 
											unifiedSummaryModel,			
											confExt);
		unifiedSMFieldFile = buildFileAddr(	fieldConfPath, 
											unifiedSummaryModel,			
											confExt);
		summConfFile =  	buildFileAddr( 	confPath,
											summNameConfFile,
											confExt);
	}
	

	public String getUnifiedSummaryModel() {
		return unifiedSummaryModel;
	}


	
}
