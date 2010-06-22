package model.bean;

import java.io.Serializable;

/**
 * @author tuland
 *
 */
public class ClassSetBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6275146230386991858L;
	private String concept;
	
	public ClassSetBean(){
		
	}
	
	public ClassSetBean(String conceptStr) {
		this();
		concept = conceptStr;
	}

	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

}

