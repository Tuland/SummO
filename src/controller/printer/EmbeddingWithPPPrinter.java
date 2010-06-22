package controller.printer;

import model.OntoLoaded;
import controller.OntoBuilder;

/**
 * Embedding + Protege' preamble
 * @author tuland
 *
 */
public class EmbeddingWithPPPrinter implements Printer{

	/* (non-Javadoc)
	 * @see controller.printer.Printer#print(model.OntoLoaded, controller.OntoBuilder)
	 */
	public void print(OntoLoaded sourceOnto, OntoBuilder builder) {
		builder.savePPEmbendingOntoSumm(sourceOnto);
	}

}
