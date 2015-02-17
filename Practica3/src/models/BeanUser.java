package models;

public class BeanUser {

	private String fullName = "";
	private String username = "";
	private String mail = "";
	private String pwd = "";
	private String pwd_check = "";
	private int[] error = { 0, 0, 0, 0, 0 };
	
	public BeanUser(){
		this.fullName = "";
		this.username = "";
		this.mail = "";
		this.pwd = "";
		this.pwd_check = "";
	}

	/* Getters */
	public String getFullName() {
		return fullName;
	}

	public String getUsername() {
		return username;
	}

	public String getMail() {
		return mail;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public String getPwd_check() {
		return pwd_check;
	}

	public int[] getError() {
		return error;
	}

	/* Setters */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public void setPwd_check(String pwd_check) {
		this.pwd_check = pwd_check;
	}

	/* Logic Functions */

	public boolean isComplete() {
		System.out.println("isComplete? " + (hasValue(getUsername()) && hasValue(getUsername())
				&& hasValue(getMail()) && hasValue(getPwd()) && hasValue(getPwd_check())));
		return (hasValue(getUsername()) && hasValue(getUsername())
				&& hasValue(getMail()) && hasValue(getPwd()) && hasValue(getPwd_check()));
	}

	private boolean hasValue(String val) {
		return ((val != null) && (!val.equals("")));
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
	 * @return true when all errors have jumped.
	 */
	public boolean isOnCreate(){
		for(int errNmbr : error){
			if( errNmbr == 0 )
				return true;
		}
		return false;
	}
	
	/**
	 * @return true if there's some error in the error array
	 */
	public boolean hasErrors(){
		for(int errNmbr : error){
			if( errNmbr == 1 )
				return true;
		}
		return false;
	}
	
	/**
	 * Define mistakes values
	 * @author Marc
	 */
	public enum ErrorList{
		ERROR_FULLNAME(0),
		ERROR_USERNAME(1),
		ERROR_MAIL(2),
		ERROR_PWD(3),
		ERROR_PWDCHECK(4);
		private int value;
		private ErrorList(int value){
			this.value = value;
		}
		public int getValue() {
			return value;
		}
	}

}