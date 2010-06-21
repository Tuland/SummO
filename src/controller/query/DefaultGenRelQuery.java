package controller.query;

public class DefaultGenRelQuery implements SummQuery, GenRelQuery{
	
	private static String VAR_SUBJ_STR = "subj";
	private static String VAR_OBJ_STR = "obj";
	
	private static String PROP_STR = "rdfs:subClassOf";
		
	private String varSubjStr;
	private String propStr;
	private String varObjStr;
	private String queryStr;
	
	/**
	 * @param varSubjStr
	 * @param propStr
	 * @param varObjStr
	 */
	public DefaultGenRelQuery(String varSubjStr, String propStr ,String varObjStr){
		this.varSubjStr = varSubjStr;
		this.varObjStr = varObjStr;
		this.propStr = propStr;
		
		this.queryStr = setQueryStr();
	}
	
	/**
	 * @param subjStr a variable name of subject
	 * @param objStr a variable name of object
	 */
	public DefaultGenRelQuery(String subjStr, String objStr) {
		this(subjStr, PROP_STR , objStr);
	}
	
	/**
	 * Implement generalize relationship query
	 */
	public DefaultGenRelQuery(){
		this(VAR_SUBJ_STR, VAR_OBJ_STR);
	}

	/* (non-Javadoc)
	 * @see controller.query.GenRelQuery#getVarSubjStr()
	 */
	public String getVarSubjStr() {
		return varSubjStr;
	}

	/* (non-Javadoc)
	 * @see controller.query.GenRelQuery#getVarObjStr()
	 */
	public String getVarObjStr() {
		return varObjStr;
	}

	/* (non-Javadoc)
	 * @see controller.query.SummQuery#getQueryStr()
	 */
	public String getQueryStr() {
		return queryStr;
	}
	
	protected String setQueryStr(){
		return "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
				"SELECT ?" + varSubjStr + " ?" + varObjStr + " " +
				"WHERE {" +
				"      ?" + varSubjStr + " " + propStr  +" ?" + varObjStr +
				"      }";
	}


}
