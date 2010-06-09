package exception;

public class PrefixesNotAvailable extends Throwable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8932133420455279330L;
	private String attrFailure;
	
	public PrefixesNotAvailable(String attr){
		attrFailure = attr;
	}
	
	public void messageFailure(){
		System.out.println(attrFailure + " is NULL");
	}
}
