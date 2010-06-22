package controller.translator;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.query.QuerySolution;

import controller.OntoBuilder;
import controller.query.GenRelQuery;
import static controller.helper.TranslationHelper.extractURI;
import static controller.helper.TranslationHelper.printInclusionFailure;

/**
 * Generalize relationship translator
 * @author tuland
 * 
 */
public class GenRelTranlator implements Translator {
	
	private GenRelQuery genQ;
	private OntoBuilder summ;
	private boolean verboseOut;
	private boolean verboseIn;
	
	public GenRelTranlator(	OntoBuilder summ, 
							GenRelQuery genQ, 
							boolean verboseIn,
							boolean verboseOut){
		this.genQ = genQ;
		this.summ = summ;
		this.verboseOut = verboseOut;
		this.verboseIn = verboseIn;
	}
	
	public GenRelTranlator(OntoBuilder summ, GenRelQuery genQ){
		this(summ, genQ, false, false);
	}

	/* (non-Javadoc)
	 * @see controller.translator.Translator#translate(com.hp.hpl.jena.query.QuerySolution, boolean)
	 */
	public void translate(QuerySolution sol) {

		String subj = extractURI(sol, genQ.getVarSubjStr());
		String obj = extractURI(sol, genQ.getVarObjStr());

		if (subj !=null && obj !=null) {
			
			if (verboseIn){
				System.out.println("SUBJ " + subj);
				System.out.println("OBJ " + obj);
				System.out.println();
			}
			summ.writeTripleGeneralizeRel(subj, obj);
			
		} else if (verboseOut) {
			
			List<String> list = new ArrayList<String>();
			populateList(list, subj, obj);
			printInclusionFailure("gen rel", list);
			
		}
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
