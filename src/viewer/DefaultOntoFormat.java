package viewer;

import java.io.FileOutputStream;
import java.io.IOException;

import com.hp.hpl.jena.ontology.OntModel;


public class DefaultOntoFormat implements OntoFormat{
	private OntModel model;
	private String base;
	private String preamble;
	public DefaultOntoFormat (OntModel model, String base, String preamble) {
		this.model = model;
		this.base = base;
		this.preamble = preamble;
	}

	
	/* (non-Javadoc)
	 * @see model.OntoFormat#say(java.io.FileOutputStream)
	 */
	public void say(FileOutputStream out) {
		try {
			out.write(preamble.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.write(out, "RDF/XML-ABBREV", base);
	}

}
