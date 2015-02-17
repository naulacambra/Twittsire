package models;

public class BeanLogin {

	private String username = "";
	private String pwd = "";
	private int[] error = {0,0};
	
	public String getUsername() {
		return username;
	}

	public String getPwd() {
		return pwd;
	}
	
	public int[] getError() {
		return error;
	}
	/* Setters */
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	/* Logic Functions */
	public boolean isComplete() {
	    return(hasValue(getUsername()) &&
	    		hasValue(getPwd()));
	}
	
	private boolean hasValue(String val) {
		return((val != null) && (!val.equals("")));
	}
	/**
	 * Sets an error flag in the correspondent position
	 * of the array.
	 * @param pos position in the array of errors
	 */
	public void setError(int pos){
		error[pos] = 1;
	}
	/**
	 * Define los errores
	 * @author Marc
	 */
	public enum ErrorList{
		ERROR_USERNAME(0),
		ERROR_PWD(1);
		private int value;
		private ErrorList(int value){
			this.value = value;
		}
		public int getValue() {
			return value;
		}
	}
}
