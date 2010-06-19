package controller;

import static helper.QueryHelper.extractURI;

import com.hp.hpl.jena.query.QuerySolution;

public class DirRelTranslator implements Translator{
	private DirRelQuery dirRelQ;
	private Summarizator summ;
	
	public DirRelTranslator(Summarizator summ, DirRelQuery dirRelQ){
		this.dirRelQ = dirRelQ;
		this.summ = summ;
	}
	
	public void translate(QuerySolution sol) {

		String subj = extractURI(sol, dirRelQ.getVarSubjStr());
		String prop = extractURI(sol, dirRelQ.getVarPropStr());
		String obj = extractURI(sol, dirRelQ.getVarObjStr());

		summ.writeTripleDirRel(subj, prop, obj);
		
	}

}
