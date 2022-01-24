package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//---------------------------------------------------------------
//Entity class for Questionsanswers 
//Entity classes must be defined inside the persistance.xml file (src/java/META-INF/persistance.xml) othervise the connection with db will not work
//Name of the entity class must be same as the name of the table for what is entity created in this case name of the table is Questionsanswers
//- attributes must have same name as name of the columns inside the table
//---------------------------------------------------------------
@Entity
public class Questionsanswers {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionAnswerID;
	private int answer;
	private int userID;
	private int questionID;

	public Questionsanswers() {
		super();
	}
	
	public Questionsanswers(int answer, int userID, int questionID) {
		super();
		this.answer = answer;
		this.userID = userID;
		this.questionID = questionID;
	}
	
	public Questionsanswers(String answer, int userID, int questionID) {
		super();
		this.setAnswer(answer);
		this.userID = userID;
		this.questionID = questionID;
	}

	public Questionsanswers(int questionAnswerID, int answer, int userID, int questionID) {
		super();
		this.questionAnswerID = questionAnswerID;
		this.answer = answer;
		this.userID = userID;
		this.questionID = questionID;
	}
	
	public Questionsanswers(int questionAnswerID, String answer, int userID, int questionID) {
		super();
		this.questionAnswerID = questionAnswerID;
		this.setAnswer(answer);
		this.userID = userID;
		this.questionID = questionID;
	}

	public int getQuestionAnswerID() {
		return questionAnswerID;
	}

	public void setQuestionAnswerID(int questionAnswerID) {
		this.questionAnswerID = questionAnswerID;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}
	
	public void setAnswer(String answer) {
		try {
			this.answer = Integer.parseInt(answer);
		} catch (NumberFormatException | NullPointerException e) {
			// Do nothing - the value is not changed
		}
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	@Override
	public String toString() {
		return "Questionsanswers [questionAnswerID=" + questionAnswerID + ", answer=" + answer + ", userID=" + userID
				+ ", questionID=" + questionID + "]";
	}
	

}
