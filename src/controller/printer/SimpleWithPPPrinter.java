package controller.printer;

import model.OntoLoaded;
import controller.OntoBuilder;

/**
 * Protege preamble
 * @author tuland
 *
 */
public class SimpleWithPPPrinter implements Printer{

	/* (non-Javadoc)
	 * @see controller.printer.Printer#print(model.OntoLoaded, controller.OntoBuilder)
	 */
	public void print(OntoLoaded sourceOnto, OntoBuilder builder) {
		builder.savePPOntoSummarized();
	}

}
