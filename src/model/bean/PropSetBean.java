/**
 * This file is part of SummO.
 *
 * SummO: Ontology Summarization 
 * Copyright (C) 2010 Tullio Landoni (tuland@gmail.com)
 * SummO is a free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program. If not, see http://www.gnu.org/licenses/. 
 */
package model.bean;

import java.io.Serializable;

/**
 * @author tuland
 *
 */
public class PropSetBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7415426707477750919L;
	private String directedRel;
	private String interGeneralizeRel;
	private String generalizeRel;
	
	public PropSetBean(){
		
	}
	
	public PropSetBean(	String directedRelStr, 
						String interGeneralizeRelStr, 
						String generalizeRelStr) {
		this();
		directedRel = directedRelStr;
		interGeneralizeRel = interGeneralizeRelStr;
		generalizeRel = generalizeRelStr;
	}

	public String getDirectedRel() {
		return directedRel;
	}

	public void setDirectedRel(String directedRel) {
		this.directedRel = directedRel;
	}

	public String getInterGeneralizeRel() {
		return interGeneralizeRel;
	}

	public void setInterGeneralizeRel(String interGeneralizeRel) {
		this.interGeneralizeRel = interGeneralizeRel;
	}

	public String getGeneralizeRel() {
		return generalizeRel;
	}

	public void setGeneralizeRel(String generalizeRel) {
		this.generalizeRel = generalizeRel;
	}
	
}
