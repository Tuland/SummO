package model;

import java.io.FileOutputStream;
import java.io.IOException;

import com.hp.hpl.jena.ontology.OntModel;

public class EmbeddedOntoFormat implements OntoFormat{
	
	private static final String INIT_COMMENT = "<!--BEGIN_SUMM\n";
	private static final String FINAL_COMMENT = "\nEND_SUMM-->";
	private static final String BOX_STR = "*";
	private static final int BOX_DIM = 100;
	
	private OntModel model;
	private String base;
	private OntoFormat format;
	private String preamble;
	
	public EmbeddedOntoFormat (	OntModel model, 
								String base, 
								String preamble, 
								OntoFormat format) {
		this.model = model;
		this.base = base;
		this.format = format;
		this.preamble = preamble;
	}

	/* (non-Javadoc)
	 * @see model.OntoFormat#say(java.io.FileOutputStream)
	 */
	public void say(FileOutputStream out) {
		try {
			out.write(topComment());
			out.write(preamble.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.write(out, "RDF/XML-ABBREV", base);
		
		try {
			out.write(bottomComment());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		format.say(out);
		
	}
	
	private byte[] topComment() {
		String comment = 	INIT_COMMENT +
							repeatStr(BOX_DIM, BOX_STR) + "\n" +
							"\n" ;
		return comment.getBytes();
	}
	
	private byte[] bottomComment() {
		String comment = 	"\n" + repeatStr(BOX_DIM, BOX_STR) + 
							FINAL_COMMENT + 
							"\n" ;
		return comment.getBytes();
	}
	
	private String repeatStr(int n, String str){
		String resultStr = "";
		for (int i = 0; i < n; i++) {
			resultStr = resultStr.concat(str);
		}
		return resultStr;
	}
	

}
