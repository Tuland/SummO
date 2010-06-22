package controller.printer;

import model.OntoLoaded;
import controller.OntoBuilder;

/**
 * @author tuland
 *
 */
public class SimplePrinter implements Printer{

	/* (non-Javadoc)
	 * @see controller.printer.Printer#print(model.OntoLoaded, controller.OntoBuilder)
	 */
	public void print(OntoLoaded sourceOnto, OntoBuilder builder) {
		builder.saveOntoSummarized();
	}

}
