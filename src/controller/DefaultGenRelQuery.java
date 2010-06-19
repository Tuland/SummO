package controller;

public class DefaultGenRelQuery implements SummQuery, GenRelQuery{
	
	private static String VAR_SUBJ_STR = "subj";
	private static String VAR_OBJ_STR = "obj";
	
	private static String PROP_STR = "rdfs:subClassOf";
		
	private String varSubjStr;
	private String varObjStr;
	private String queryStr;
	
	public DefaultGenRelQuery(String varSubjStr, String propStr ,String varObjStr){
		this.varSubjStr = varSubjStr;
		this.varObjStr = varObjStr;
		
		this.queryStr = 
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
			"SELECT ?" + varSubjStr + " ?" + varObjStr + " " +
			"WHERE {" +
			"      ?" + varSubjStr + " " + propStr  +" ?" + varObjStr +
			"      }";
			
	}
	
	public DefaultGenRelQuery(String subjStr, String objStr) {
		this(subjStr, PROP_STR , objStr);
	}
	
	public DefaultGenRelQuery(){
		this(VAR_SUBJ_STR, VAR_OBJ_STR);
	}

	public String getVarSubjStr() {
		return varSubjStr;
	}

	public String getVarObjStr() {
		return varObjStr;
	}

	public String getQueryStr() {
		return queryStr;
	}


}
