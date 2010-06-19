package controller;

public class DefaultDirRelQuery implements SummQuery, DirRelQuery{
	
	private static String VAR_SUBJ_STR = "subj";
	private static String VAR_OBJ_STR = "obj";
	private static String VAR_PROP_STR = "prop";
	
	private static String P_DOMAIN_STR = "rdfs:domain";
	private static String P_RANGE_STR = "rdfs:range";
	
	private String varSubjStr;
	private String varObjStr;
	private String varPropStr;
	private String queryStr;

	public DefaultDirRelQuery(	String varSubjStr, 
								String varObjStr,
								String varPropStr) {
		this.varSubjStr = varSubjStr;
		this.varObjStr = varObjStr;
		this.varPropStr = varPropStr;
		
		this.queryStr = 
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
			"SELECT ?" + varSubjStr + " ?" + varPropStr + " ?" + varObjStr + " " +
			"WHERE {" +
			"      ?" + varPropStr + " " + P_DOMAIN_STR  +" ?" + varSubjStr + " . " +
			"      ?" + varPropStr + " " + P_RANGE_STR  +" ?" + varObjStr + " . " +
			"      }";
	}
	
	public DefaultDirRelQuery(){
		this(VAR_SUBJ_STR, VAR_PROP_STR, VAR_OBJ_STR);
	}

	public String getQueryStr() {
		return queryStr;
	}

	public String getVarSubjStr() {
		return varSubjStr;
	}

	public String getVarObjStr() {
		return varObjStr;
	}

	public String getVarPropStr() {
		return varPropStr;
	}


}
