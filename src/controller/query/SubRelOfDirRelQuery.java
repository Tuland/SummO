package controller.query;

public class SubRelOfDirRelQuery extends DefaultDirRelQuery implements SummQuery, DirRelQuery{
	protected static String P_SUBPROP_STR = "rdfs:subPropertyOf";
	
	public SubRelOfDirRelQuery(){
		super();
	}
	
	protected String setQueryString(){ 
		return	"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
				"SELECT ?" + VAR_SUBJ_STR + " ?" + VAR_PROP_STR + " ?" + VAR_OBJ_STR + " " +
				"WHERE {" +
				"      ?x " + P_DOMAIN_STR  + " ?" + VAR_SUBJ_STR + " . " +
				"      ?x " + P_RANGE_STR  + " ?" + VAR_OBJ_STR + " . " +
				"      ?" + VAR_PROP_STR + " " + P_SUBPROP_STR + " ?x ." +	
				"      }";
	}

}
