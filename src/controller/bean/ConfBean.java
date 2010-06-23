/**
 * This file is part of SummO.
 *
 * SummO: Ontology Summarization 
 * Copyright (C) 2010 Tullio Landoni (tuland@gmail.com)
 * SummO is a free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/. 
 */
package controller.bean;

import java.io.Serializable;

import static helper.PathHelper.buildFileAddr;

/**
 * @author tuland
 *
 */
public class ConfBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2200179586991823121L;
	
	private String confExt;
	
	private String confPath;
	private String propConfPath;
	private String classConfPath;
	private String outputPath;
	private String inputConfPath;
	
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

	public String getClassConfPath() {
		return classConfPath;
	}

	public void setClassConfPath(String fieldConfPath) {
		this.classConfPath = fieldConfPath;
	}
	
	public String getPropConfPath() {
		return propConfPath;
	}

	public void setPropConfPath(String fieldConfPath) {
		this.propConfPath = fieldConfPath;
	}

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}
	
	public String getInputConfPath() {
		return inputConfPath;
	}


	public void setInputConfPath(String inputConfPath) {
		this.inputConfPath = inputConfPath;
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
		propSMConfFile = 	buildFileAddr(	confPath, 
											propSummaryModel, 
											confExt);
		propSMFieldFile = 	buildFileAddr(	propConfPath, 
											propSummaryModel, 
											confExt);
		classSMConfFile = 	buildFileAddr(	confPath, 
											classSummaryModel,
											confExt);
		classSMFieldFile = 	buildFileAddr(	classConfPath, 
											classSummaryModel,			
											confExt);
		summConfFile =  	buildFileAddr( 	confPath,
											summNameConfFile,
											confExt);
	}

	
}
