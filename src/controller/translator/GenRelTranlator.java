package controller.translator;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.query.QuerySolution;

import controller.Summarizator;
import controller.query.GenRelQuery;
import static controller.helper.TranslationHelper.extractURI;
import static controller.helper.TranslationHelper.printInclusionFailure;

/**
 * @author tuland
 * Generalize relationship translator
 */
public class GenRelTranlator implements Translator {
	
	private GenRelQuery genQ;
	private Summarizator summ;
	
	public GenRelTranlator(Summarizator summ, GenRelQuery genQ){
		this.genQ = genQ;
		this.summ = summ;
	}

	/* (non-Javadoc)
	 * @see controller.translator.Translator#translate(com.hp.hpl.jena.query.QuerySolution, boolean)
	 */
	public void translate(QuerySolution sol, boolean verbose) {

		String subj = extractURI(sol, genQ.getVarSubjStr());
		String obj = extractURI(sol, genQ.getVarObjStr());

		if (subj !=null && obj !=null) {
			summ.writeTripleGeneralizeRel(subj, obj);
		} else if (verbose) {
			List<String> list = new ArrayList<String>();
			populateList(list, subj, obj);
			printInclusionFailure("gen rel", list);
		}
	}
	
	/* (non-Javadoc)
	 * @see controller.translator.Translator#translate(com.hp.hpl.jena.query.QuerySolution)
	 */
	public void translate(QuerySolution sol) {
		translate(sol, false);
	}
	
	private List<String> populateList(List<String> list, String el1, String el2) {
		if (el1 != null) {
			list.add(el1);
		}
		if (el2 != null) {
			list.add(el2);
		}
		return list;
	}
	
}
