package viewer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Manage the ontology printing
 * @author tuland
 */
public class OntoPrinter {
	private OntoFormat format;
	/**
	 * @param format	a printer format for ontologies
	 */
	public OntoPrinter(OntoFormat format) {
		this.format = format;
	}
	
	/**
	 * Save in a file theontology
	 * @param outputFile	an output file path  
	 */
	public void print(String outputFile){
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(outputFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			format.say(out);
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	}

}
