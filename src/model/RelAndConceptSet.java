package model;

/**
 * @author tuland
 *
 */
public class RelAndConceptSet {
	public String directedRel;
	public String interGeneralizeRel;
	public String generalizeRel;
	
	public RelAndConceptSet(){
		
	}
	
	public RelAndConceptSet(String dr, String igr, String gr) {
		this();
		directedRel = dr;
		interGeneralizeRel = igr;
		generalizeRel = gr;
	}
	
	
}
