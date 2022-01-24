package data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//---------------------------------------------------------------
//Entity class for QueAnsRanUser 
//Used with statistic
//Entity classes must be defined inside the persistance.xml file (src/java/META-INF/persistance.xml) othervise the connection with db will not work
//Name of the entity class must be same as the name of the table for what is entity created in this case name of the table is QueAnsRanUser
//- attributes must have same name as name of the columns inside the table
//---------------------------------------------------------------
/**
 * Class used to save/read random user answers in the database and operate with this data in the program
 * @author Cezar
 *
 */

@Entity
public class QueAnsRanUser implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int justId;
	private int questionId;
	private String question;
	private int questionAnswer;
	
	public QueAnsRanUser() {
		
	}

	public int getQuestionId() {
		return questionId;
	}

	public int getJustId() {
		return justId;
	}

	public void setJustId(int justId) {
		this.justId = justId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(int questionAnser) {
		this.questionAnswer = questionAnser;
	}
	
	
}
