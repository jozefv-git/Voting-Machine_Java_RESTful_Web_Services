package data;

/**
 * 
 * Class used for sending success/error text to the user
 * @author Cezar
 *
 */
public class Status {
	
	private String code;
	
	public Status() {
		
	}
	
	public Status(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
