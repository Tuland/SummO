package controller.query;

/**
 * @author tuland
 *
 */
public class DefaultDirRelQuery implements SummQuery, DirRelQuery{
	
	protected static String VAR_SUBJ_STR = "subj";
	protected static String VAR_OBJ_STR = "obj";
	protected static String VAR_PROP_STR = "prop";
	
	protected static String P_DOMAIN_STR = "rdfs:domain";
	protected static String P_RANGE_STR = "rdfs:range";
	
	private String varSubjStr;
	private String varObjStr;
	private String varPropStr;
	private String queryStr;

	/**
	 * Implement directed relationship query
	 * @param varSubjStr	a variable name of subject 
	 * @param varObjStr		a variable name of object 
	 * @param varPropStr	a variable name of relationship (property) 
	 */
	public DefaultDirRelQuery(	String varSubjStr, 
								String varObjStr,
								String varPropStr) {
		this.varSubjStr = varSubjStr;
		this.varObjStr = varObjStr;
		this.varPropStr = varPropStr;
		
		this.queryStr = setQueryString();
	}
	
	/**
	 * Implement directed relationship query
	 */
	public DefaultDirRelQuery(){
		this(VAR_SUBJ_STR, VAR_PROP_STR, VAR_OBJ_STR);
	}

	/* (non-Javadoc)
	 * @see controller.query.SummQuery#getQueryStr()
	 */
	public String getQueryStr() {
		return queryStr;
	}

	/* (non-Javadoc)
	 * @see controller.query.DirRelQuery#getVarSubjStr()
	 */
	public String getVarSubjStr() {
		return varSubjStr;
	}

	/* (non-Javadoc)
	 * @see controller.query.DirRelQuery#getVarObjStr()
	 */
	public String getVarObjStr() {
		return varObjStr;
	}

	/* (non-Javadoc)
	 * @see controller.query.DirRelQuery#getVarPropStr()
	 */
	public String getVarPropStr() {
		return varPropStr;
	}

	protected String setQueryString(){ 
		return	"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
				"SELECT ?" + varSubjStr + " ?" + varPropStr + " ?" + varObjStr + " " +
				"WHERE {" +
				"      ?" + varPropStr + " " + P_DOMAIN_STR  + " ?" + varSubjStr + " . " +
				"      ?" + varPropStr + " " + P_RANGE_STR  + " ?" + varObjStr + " . " +
				"      }";
	}

}
