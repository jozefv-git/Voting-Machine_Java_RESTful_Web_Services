package data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//---------------------------------------------------------------
//Entity class for Questions 
//Entity classes must be defined inside the persistance.xml file (src/java/META-INF/persistance.xml) othervise the connection with db will not work
//Name of the entity class must be same as the name of the table for what is entity created in this case name of the table is Questions
//- attributes must have same name as name of the columns inside the table
//---------------------------------------------------------------

/**
 * Class used to save/read questions in the database and operate with this data in the program
 * @author Samantha
 *
 */
@Entity
public class Questions implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionID;
	private String questionText;
	
	public Questions() {
		
	}
	
	public Questions(int questionID, String questionText) {
		this.questionID = questionID;
		this.questionText = questionText;
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	
	public String toString() {
		return getQuestionID() + ": " + getQuestionText();
	}

}

