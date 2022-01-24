package data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
/**
 * 
 * 
	//---------------------------------------------------------------
	//Java class with methods
	//Methods used inside the service GetResults
	//---------------------------------------------------------------
 * 
 * @author Jozef 
 *
 *
 */

public class GetResultsControll {

	//for connectionn
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("votingapp");

	
	private static ArrayList<Integer> getPoliticians() {
		// This method is returning all politicians ids from politicians table....we are
		// saving it inside the array
		// -> We are returning array of Ids
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin(); // letters in the query must be alllways the same!
		List<Politicians> politiciansList = em.createQuery("SELECT p FROM Politicians p").getResultList();  //list of all politicians from the table
		em.getTransaction().commit();
		em.close();
		ArrayList<Integer> politiciansIDs = new ArrayList<Integer>();

		for (Politicians politicic : politiciansList) {
			politiciansIDs.add(politicic.getPoliticID());
		}
		return politiciansIDs;
	}

	private int userScore(List<QueAnsRanUser> resultsArrayList) {
		// Inside the answers we will have stored our user questions
		ArrayList<Integer> answers = new ArrayList<>();
		for (QueAnsRanUser questionAnswersObj : resultsArrayList) {
			answers.add(questionAnswersObj.getQuestionAnswer());
		}
		// Sum of all values inside the array
		int result = 0;
		int score = 0;
		for (int i = 0; i < answers.size(); i++) {
			result = answers.get(i);
			// result = result + result;
			score = score + result;
		}
		System.out.println("My score is: " + score);
		return score;
	}

	public LinkedHashSet<Integer> getAnswers(List<QueAnsRanUser> resultsArrayList) {
		// Getting all answers for selected politician ID
		// We are saving our politicians id list inside new variable politicians
		ArrayList<Integer> politicians = getPoliticians();
		// System.out.println("Politicians:" + String.valueOf(getPoliticians(con,
		// statement)));

		// Here will be stored our answers objects
		ArrayList<AnsQue_model> answers = new ArrayList<AnsQue_model>();
		// Set score contains hashSet (list) of selected users..set contains also only unique values
		Set<Integer> score = new HashSet<Integer>();

		// Using LinkedHashSet set does not allow to add duplicates into them and maintains the order of the elements in which they were inserted.
		LinkedHashSet<Integer> matchID = new LinkedHashSet<>();

		// for thrue all ids
		for (int i = 0; i < politicians.size(); i++) {
			// Getting polititcian ID
			int politicianID = politicians.get(i);
			System.out.println("Politic Ids: " + politicianID);
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			List<Questionsanswers> questionsanswersList = em.createQuery("SELECT q FROM Questionsanswers q WHERE q.userID LIKE ?1")
					.setParameter(1, politicianID)
					.getResultList();
			
			
			//checking if politic answered at least one question (if politic ID is inside the Questionansanswers table)
			int contains = 0;
			Query queryPolitic = (Query) em.createQuery("SELECT COUNT(p.userID) FROM Questionsanswers p WHERE p.userID LIKE ?1"); //
			contains = Integer.parseInt(queryPolitic.setParameter(1, politicianID) //must use Interger.parse, otherwise query is returning Long // must be politicId
					.getSingleResult().toString());
			
			
			//check if politician exist and if it 
			if(contains > 0) {
			//Getting politic score as one result via query
			int politic_score = 0;
			Query query = (Query) em.createQuery("SELECT SUM(q.answer) FROM Questionsanswers q WHERE q.userID LIKE ?1"); // working with politicianID - befor only with value 4 -FIXED
			politic_score = Integer.parseInt(query.setParameter(1, politicianID) //must use Interger.parse, otherwise query is returning Long // must be politicId
					.getSingleResult().toString()) ;
			em.getTransaction().commit();
			em.close();
			System.out.println("Politic score is: " + politic_score);
			//allow to add only politics into the array only with score more than 0
			if(politic_score > 0) {
			for (Questionsanswers questionsanswers2 : questionsanswersList) {
					// Saving score for selected user into the array list
					// score.add(politic_score);
					AnsQue_model answer_question = new AnsQue_model();
					// answer_question = new AnsQue_model();
					// Getting answers from selected id and saving it inside answer_question model
					// answers.add(resultSet.getInt("answer"));
					answer_question.setUserID(questionsanswers2.getUserID());
					answer_question.setQuestionID(questionsanswers2.getQuestionID());
					answer_question.setAnswer(questionsanswers2.getAnswer());
					// resultsOfTheArray.add(scoreOfArrays(answers));
					answer_question.setScore(politic_score);
					// checking for 3 best candidates
					score.add(politic_score);
					answers.add(answer_question);
				
			}
			}
			
		}

		}

		// Casting from set of scores to arrayList
		ArrayList<Integer> score_ArrayList = new ArrayList<Integer>(score);
		// this array will contains 3 closes scores compare to user score
		ArrayList<Integer> three_score_ArrayList = new ArrayList<Integer>();
		// System.out.println("All politic score:" + String.valueOf(score_ArrayList));
		for (int x = 0; x < 3; x++) {
			int number = closest(userScore(resultsArrayList), score_ArrayList); // Closes method is checking closes value from selected
					System.out.println("Number" + number);											// array compared to our
			// number
			three_score_ArrayList.add(number);

			// if the score is once selected, it will be removed from array, so close
			// function can check array again and find second closes number..
			for (int k = 0; k < score_ArrayList.size(); k++) {
				if (score_ArrayList.get(k) == number) {
					score_ArrayList.remove(k);
				}
			}
		}
		System.out.println("3 best match politics:" + String.valueOf(three_score_ArrayList));
		for(int x = 0; x < three_score_ArrayList.size();x++) {
			for(int y = 0; y < answers.size();y++) {
				if(answers.get(y).getScore() == three_score_ArrayList.get(x)) {
					System.out.println("Politic score:" + String.valueOf(answers.get(y).getScore()));
					// matchID contains 3 best matched politician id inside the array
					matchID.add(answers.get(y).getUserID());
					 System.out.println("Final ids:" + String.valueOf(matchID));
				}
			}
		}

		return matchID;
	}

	private static int closest(int of, ArrayList<Integer> in) {
		// This method is checking closes value from selected array compared to our
		// number
		int min = Integer.MAX_VALUE;
		int closest = of;
		for (int v : in) {
			final int diff = Math.abs(v - of);
			if (diff < min) {
				min = diff;
				closest = v;
			}
		}
		return closest;
	}

}