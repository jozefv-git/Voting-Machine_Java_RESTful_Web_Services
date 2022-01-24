package data;

import java.io.Serializable;

/**
 * Java provides a mechanism, called object serialization where an object can be represented as a sequence of bytes that includes the object's
 * data as well as information about the object's type and the types of data stored in the object.
 * After a serialized object has been written into a file, it can be read from the file and deserialized that is,
 * the type information and bytes that represent the object and its data can be used to recreate the object in memory.
 * 
 * This is model class used for create the object which contain questionID, question text, answer from anonymous user and answer from politician
 * used in GetResults_detail.java service
 * @author Jozef
 *
 *
 */

public class QuestionAnswerPoliticObj implements Serializable {
	private int questionId;
	private String question;
	private int questionAnswer;
	private int politicianAnswer;

	public QuestionAnswerPoliticObj() {

	}

	public QuestionAnswerPoliticObj(int questionId, String question, int questionAnswer, int politicianAnswer) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.questionAnswer = questionAnswer;
		this.politicianAnswer = politicianAnswer;
	}

	public int getQuestionId() {
		return questionId;
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

	public void setQuestionAnswer(int questionAnswer) {
		this.questionAnswer = questionAnswer;
	}

	public int getPoliticianAnswer() {
		return politicianAnswer;
	}

	public void setPoliticianAnswer(int politicianAnswer) {
		this.politicianAnswer = politicianAnswer;
	}



}