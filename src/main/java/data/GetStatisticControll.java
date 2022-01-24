package data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
/**
 * 	//---------------------------------------------------------------
	//Java class with methods (for calculations)- methods are used inside the statisticsPage.jsp 
	//---------------------------------------------------------------
 * 
 * @author Jozef 
 *
 *
 */

public class GetStatisticControll {

	//for connectionn
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("votingapp");
	
	public int getQuestionCount(int id) {
		// This method is returning int (sum) of all questions with same id
		int question_count = 0;
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		//Getting count for questionId column where questionId = id
		//---------------------------------------------------------------

		
		//query of single object
		//Positional parameters are prefixed with a question mark (?) followed the numeric position of the parameter in the query.
		//use of positional parameters with java persistance language is faster to execute compare to using column names
		//---------------------------------------------------------------
		Query query = (Query) em.createQuery("SELECT COUNT(q.questionId) FROM QueAnsRanUser q WHERE q.questionId LIKE ?1");
		question_count = Integer.parseInt(query.setParameter(1, id) // we need to parse it into the int bcs query is retriving Long insttead of int
				.getSingleResult().toString());
		System.out.println("Ids:" + id);
		System.out.println("Print count: " + question_count);
		em.getTransaction().commit();
		em.close();
		return question_count;
	}

	public int getQuestion1Count(int id){
		// This method is returning int (sum) of all questions with same id and same
		// answer
		// So we are getting sum of selected answer for selected question
		int question_count = 0;
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		//Getting count for questionId column where questionId = id
				Query query = (Query) em.createQuery("SELECT COUNT(q.questionId) FROM QueAnsRanUser q WHERE q.questionId LIKE ?1 AND q.questionAnswer LIKE ?2");
				question_count = Integer.parseInt(query.setParameter(1, id).setParameter(2, 1) // we need parse to Integer bcs query is retriving Long instead of int
						.getSingleResult().toString());		
				em.getTransaction().commit();
		em.close();
		return question_count;
	}
	
	public int getQuestion2Count(int id){
		// This method is returning int (sum) of all questions with same id and same
		// answer
		// So we are getting sum of selected answer for selected question
		int question_count = 0;
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		//Getting count for questionId column where questionId = id
				Query query = (Query) em.createQuery("SELECT COUNT(q.questionId) FROM QueAnsRanUser q WHERE q.questionId LIKE ?1 AND q.questionAnswer LIKE ?2");
				question_count = Integer.parseInt(query.setParameter(1, id).setParameter(2, 2)
						.getSingleResult().toString());		
				em.getTransaction().commit();
		em.close();
		return question_count;
	}
	
	
	public int getQuestion3Count(int id){
		// This method is returning int (sum) of all questions with same id and same
		// answer
		// So we are getting sum of selected answer for selected question
		int question_count = 0;
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		//Getting count for questionId column where questionId = id
				Query query = (Query) em.createQuery("SELECT COUNT(q.questionId) FROM QueAnsRanUser q WHERE q.questionId LIKE ?1 AND q.questionAnswer LIKE ?2");
				question_count = Integer.parseInt(query.setParameter(1, id).setParameter(2, 3)
						.getSingleResult().toString());		
				em.getTransaction().commit();
		em.close();
		return question_count;
	}
	

	public int getQuestion4Count(int id){
		// This method is returning int (sum) of all questions with same id and same
		// answer
		// So we are getting sum of selected answer for selected question

		int question_count = 0;
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		//Getting count for questionId column where questionId = id
				Query query = (Query) em.createQuery("SELECT COUNT(q.questionId) FROM QueAnsRanUser q WHERE q.questionId LIKE ?1 AND q.questionAnswer LIKE ?2");
				question_count = Integer.parseInt(query.setParameter(1, id).setParameter(2, 4)
						.getSingleResult().toString());		
				em.getTransaction().commit();
		em.close();
		return question_count;
	}

	public int getQuestion5Count(int id){
		// This method is returning int (sum) of all questions with same id and same
		// answer
		// So we are getting sum of selected answer for selected question
		int question_count = 0;
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		//Getting count for questionId column where questionId = id
				Query query = (Query) em.createQuery("SELECT COUNT(q.questionId) FROM QueAnsRanUser q WHERE q.questionId LIKE ?1 AND q.questionAnswer LIKE ?2");
				question_count = Integer.parseInt(query.setParameter(1, id).setParameter(2, 5)
						.getSingleResult().toString());		
				em.getTransaction().commit();
		em.close();
		return question_count;
	}
}
