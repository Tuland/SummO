package model;

public class PropSet {
	public String directedRel;
	public String interGeneralizeRel;
	public String generalizeRel;
	
	public PropSet(){
		
	}
	
	public PropSet(	String directedRelStr, 
					String interGeneralizeRelStr, 
					String generalizeRelStr) {
		this();
		directedRel = directedRelStr;
		interGeneralizeRel = interGeneralizeRelStr;
		generalizeRel = generalizeRelStr;
	}
	

}
