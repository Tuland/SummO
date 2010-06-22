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
