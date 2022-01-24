package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//---------------------------------------------------------------
//Entity class for Politicians 
//Entity classes must be defined inside the persistance.xml file (src/java/META-INF/persistance.xml) othervise the connection with db will not work
//Name of the entity class must be same as the name of the table for what is entity created in this case name of the table is Politicians
//- attributes must have same name as name of the columns inside the table
//---------------------------------------------------------------
@Entity
public class Politicians {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int politicID;
	private int politicNumber;
	private String firstName;
	private String lastName;
	private String nation;
	private String party;
	private int age;
	private String sex;
	private String description;
	private String city;
	private String email;
	private String pssword;
	private String picture;
	private String role;

	public Politicians() {

	}

	
	public Politicians(String politicNumber, String firstName, String lastName, String nation, String party, String age,
			String sex, String description, String city, String email, String pssword, String picture) {
		super();
		this.setPoliticNumber(politicNumber);
		this.firstName = firstName;
		this.lastName = lastName;
		this.nation = nation;
		this.party = party;
		this.setAge(age);
		this.sex = sex;
		this.description = description;
		this.city = city;
		this.email = email;
		this.pssword = pssword;
		this.picture = picture;
	}

	public Politicians(int politicID, int politicNumber, String firstName, String lastName, String nation, String party,
			int age, String sex, String description, String city, String email, String pssword, String picture,
			String role) {
		super();
		this.politicID = politicID;
		this.politicNumber = politicNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nation = nation;
		this.party = party;
		this.age = age;
		this.sex = sex;
		this.description = description;
		this.city = city;
		this.email = email;
		this.pssword = pssword;
		this.picture = picture;
		this.role = role;
	}

	public int getPoliticID() {
		return politicID;
	}

	public void setPoliticID(int politicID) {
		this.politicID = politicID;
	}
	
	public void setPoliticId(String id) {
		try {
			this.politicID = Integer.parseInt(id);
		}
		catch(NumberFormatException | NullPointerException e) {
			//Do nothing - the value is not changed
		}
	}

	public int getPoliticNumber() {
		return politicNumber;
	}

	public void setPoliticNumber(int politicNumber) {
		this.politicNumber = politicNumber;
	}
	
	public void setPoliticNumber(String politicNumber) {
		try {
			this.politicNumber = Integer.parseInt(politicNumber);
		}
		catch(NumberFormatException | NullPointerException e) {
			//Do nothing - the value is not changed
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public void setAge(String age) {
		try {
			this.age = Integer.parseInt(age);
		}
		catch(NumberFormatException | NullPointerException e) {
			//Do nothing - the value is not changed
		}
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPssword() {
		return pssword;
	}

	public void setPssword(String pssword) {
		this.pssword = pssword;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
