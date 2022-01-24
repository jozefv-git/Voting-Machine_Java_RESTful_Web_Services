package data;

/**
* Model class for answers used in GetResultsControll class
* @author Jozef
*
*
*/
public class AnsQue_model {
	int answer,userID,questionID,score;
	//private static int counter = 0;


	public AnsQue_model() {


	}

	public AnsQue_model(int answer, int userID, int questionID, int score) {
		this.answer = answer;
		this.userID = userID;
		this.questionID = questionID;
		this.score = score;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}








}