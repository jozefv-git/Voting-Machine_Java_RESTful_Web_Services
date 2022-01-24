package data;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;

import javax.persistence.GenerationType;

import javax.persistence.Id;

/**
 * Class used to store/read invitation codes and mail in the databse
 * @author Cezar
 *
 */

@Entity
public class InvitationCodes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int id;

	private String code;

	private String mail;

	
	public InvitationCodes() {
		
	}
	
	public InvitationCodes(String code, String mail) {

		this.code = code;

		this.mail = mail;

	}
	
	public InvitationCodes(int id, String code, String mail) {
		this.id = id;
		this.code = code;
		this.mail = mail;
	}


	public int getId() {

		return id;

	}

	public void setId(int id) {

		this.id = id;

	}

	public String getCode() {

		return code;

	}

	public void setCode(String code) {

		this.code = code;

	}

	public String getMail() {

		return mail;

	}

	public void setMail(String mail) {

		this.mail = mail;

	}

	
	
}

