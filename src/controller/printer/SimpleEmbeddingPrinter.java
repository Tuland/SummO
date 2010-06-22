package controller.printer;

import controller.OntoBuilder;
import model.OntoLoaded;

/**
 * Embedding
 * @author tuland
 *
 */
public class SimpleEmbeddingPrinter implements Printer{
	
	/* (non-Javadoc)
	 * @see controller.printer.Printer#print(model.OntoLoaded, controller.OntoBuilder)
	 */
	public void print(OntoLoaded sourceOnto, OntoBuilder builder) {
		builder.saveEmbendingOntoSumm(sourceOnto);
	}

}
