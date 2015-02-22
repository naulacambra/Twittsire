package models;

public class Tweet {
	private String text;
	private int idUser;
	public String[] definition = {"text", "idUser"};
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
}
