package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//---------------------------------------------------------------
//not used
//---------------------------------------------------------------
@Entity
public class ForStatistic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionId;
	private int answer;
	
	public ForStatistic() {
		
	}
	
	

	public ForStatistic(int questionId, int answer) {
		super();
		this.questionId = questionId;
		this.answer = answer;
	}



	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionID(int questionId) {
		this.questionId = questionId;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	
}
