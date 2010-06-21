package controller.translator;

import static controller.helper.TranslationHelper.extractURI;
import static controller.helper.TranslationHelper.printInclusionFailure;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.query.QuerySolution;

import controller.Summarizator;
import controller.query.DirRelQuery;

/**
 * @author tuland
 * Directed relationship translator
 */
public class DirRelTranslator implements Translator{
	private DirRelQuery dirRelQ;
	private Summarizator summ;
	
	public DirRelTranslator(Summarizator summ, DirRelQuery dirRelQ){
		this.dirRelQ = dirRelQ;
		this.summ = summ;
	}
	
	/* (non-Javadoc)
	 * @see controller.translator.Translator#translate(com.hp.hpl.jena.query.QuerySolution, boolean)
	 */
	public void translate(QuerySolution sol, boolean verbose) {

		String subj = extractURI(sol, dirRelQ.getVarSubjStr());
		String prop = extractURI(sol, dirRelQ.getVarPropStr());
		String obj = extractURI(sol, dirRelQ.getVarObjStr());

		if (subj !=null && prop !=null && obj !=null) {
			summ.writeTripleDirRel(subj, prop, obj);
		}  else if (verbose) {
			List<String> list = new ArrayList<String>();
			populateList(list, subj, prop, obj);
			printInclusionFailure("dir rel", list);
		}
		
	}

	/* (non-Javadoc)
	 * @see controller.translator.Translator#translate(com.hp.hpl.jena.query.QuerySolution)
	 */
	public void translate(QuerySolution sol) {
		translate(sol, false);	
	}
	
	private List<String> populateList(List<String> list, String el1, String el2, String el3) {
		if (el1 != null) {
			list.add(el1);
		}
		
		if (el2 != null) {
			list.add(el2);
		}
		
		if (el3 !=null) {
			list.add(el3);
		}
		return list;
	}
}
