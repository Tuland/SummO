package controller;

import com.hp.hpl.jena.query.QuerySolution;
import static helper.QueryHelper.extractURI;

public class GenRelTranlator implements Translator {
	
	private GenRelQuery genQ;
	private Summarizator summ;
	
	public GenRelTranlator(Summarizator summ, GenRelQuery genQ){
		this.genQ = genQ;
		this.summ = summ;
	}

	public void translate(QuerySolution sol) {

		String subj = extractURI(sol, genQ.getVarSubjStr());
		String obj = extractURI(sol, genQ.getVarObjStr());

		summ.writeTripleGeneralizeRel(subj, obj);
		
	}
	

}
