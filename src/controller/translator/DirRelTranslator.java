package controller.translator;

import static controller.helper.TranslationHelper.extractURI;
import static controller.helper.TranslationHelper.printInclusionFailure;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.query.QuerySolution;

import controller.OntoBuilder;
import controller.query.DirRelQuery;

/**
 * Directed relationship translator
 * @author tuland
 * 
 */
public class DirRelTranslator implements Translator{
	private DirRelQuery dirRelQ;
	private OntoBuilder summ;
	private boolean verboseIn;
	private boolean verboseOut;
	
	/**
	 * @param summ			a summarizator
	 * @param dirRelQ		a query model
	 * @param verboseIn		a flag that enables verbose mode. It shows included nodes
	 * @param verboseOut	a flag that enables verbose mode. It shows excluded nodes
	 */
	public DirRelTranslator(	OntoBuilder summ, 
								DirRelQuery dirRelQ, 
								boolean verboseIn, 
								boolean verboseOut){
		this.dirRelQ = dirRelQ;
		this.summ = summ;
		this.verboseIn = verboseIn;
		this.verboseOut = verboseOut;
	}
	
	/**
	 * @param summ		a summarizator
	 * @param dirRelQ	a query model
	 */
	public DirRelTranslator(OntoBuilder summ, DirRelQuery dirRelQ){
		this(summ, dirRelQ, false, false);
	}
	
	/* (non-Javadoc)
	 * @see controller.translator.Translator#translate(com.hp.hpl.jena.query.QuerySolution, boolean)
	 */
	public void translate(QuerySolution sol) {
		String subj = extractURI(sol, dirRelQ.getVarSubjStr());
		String prop = extractURI(sol, dirRelQ.getVarPropStr());
		String obj = extractURI(sol, dirRelQ.getVarObjStr());
		

		if (subj !=null && prop !=null && obj !=null) {
			
			if (verboseIn){
				System.out.println("SUBJ " + subj);
				System.out.println("PROP " + prop);
				System.out.println("OBJ " + obj);
				System.out.println();
			}
			summ.writeTripleDirRel(subj, prop, obj);
			
		}  else if (verboseOut) {
			
			List<String> list = new ArrayList<String>();
			populateList(list, subj, prop, obj);
			printInclusionFailure("dir rel", list);
			
		}
		
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
