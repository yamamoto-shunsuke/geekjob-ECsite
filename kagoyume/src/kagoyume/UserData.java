package kagoyume;

import java.io.Serializable;

public class UserData implements Serializable{
	private int userID;
	private String name;
	private String password;
	private String mail;
	private String address;

	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getUserID() {
		return userID;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMail() {
		return mail;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return address;
	}

	//UserDataをUserDataDTOに変換するメソッド
	//this;ローカル変数とメンバ変数の区別
	public void DTOMapping(UserDataDTO udd) {
		udd.setName(this.name);
		udd.setPassword(this.password);
		udd.setMail(this.mail);
		udd.setAddress(this.address);
	}

	//UserDataDTOをUserDataに変換するメソッド
	public void UDMapping(UserDataDTO udd) {
		userID = udd.getUserID();
		name = udd.getName();
		password = udd.getPassword();
		mail = udd.getMail();
		address = udd.getAddress();
	}
}