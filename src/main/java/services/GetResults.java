package services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import data.CountOfCand;
import data.GetResultsControll;
import data.Politicians;
import data.QueAnsRanUser;
import data.QuestionAnswerPoliticObj;
import data.Questionsanswers;
import io.opencensus.stats.Aggregation.Sum;
/**
 * //---------------------------------------------------------------
	//Java class used with GetData service
	//---------------------------------------------------------------
 * 
 * @author Jozef 
 *
 *
 */

public class GetResults {
	//for connectionn
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("votingapp");
	GetResultsControll controll = new GetResultsControll();
	List<QueAnsRanUser> resultsArrayList = new ArrayList<QueAnsRanUser>();

	public void getQuestions(List<QueAnsRanUser> list1) {
		resultsArrayList = list1; // getting questions from getData and saving it into the resultsArrayList
	 System.out.println("Skuskaa:" + resultsArrayList);
	}

	public void getPolitician1(HttpServletRequest request) {
		ArrayList<Integer> politicianIds = new ArrayList<Integer>(); 
		// Saving 3 best matched user ids into the ArrayList<Integer> from Set
		for (Integer answerIntegers : controll.getAnswers(resultsArrayList)) {
			politicianIds.add(answerIntegers);
		}
		System.out.println("Resultlist:" + resultsArrayList);
		// System.out.println("Politik ID:" + String.valueOf(politicianIds.get(0)));
		// em.createQuery("SELECT p FROM Politicians p WHERE p.politicID LIKE
		// :id").setParameter("id", politicianIds.get(0)).getResultList(); -> Slower execute
		
		// Query with positional parameters
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin(); // letters in the query must be alllways the same!
		//positional parameters in the query
		//---------------------------------------------------------------
		//Positional parameters are prefixed with a question mark (?) followed the numeric position of the parameter in the query.
		//Use of positional parameters with java persistence language is faster to execute compare to using column names
		//---------------------------------------------------------------
		List<Politicians> politiciansList = em.createQuery("SELECT p FROM Politicians p WHERE p.politicID LIKE ?1") // -> faster execute																	// execute
				.setParameter(1, politicianIds.get(0)) // our best candidate
				.getResultList(); // list of all politicians with selected id from the table
		em.getTransaction().commit();
		em.close(); 
		System.out.println("PoliticList new: " + politiciansList);
		for (Politicians politician : politiciansList) {
			System.out.println("Politician forwarded: " + politician);
			System.out.println("Text Test request: " + request);
			request.setAttribute("politic1", politician);
			System.out.println("Politician1:" + politician.getFirstName());
		}
	
 
	}

	public void getPolitician2(HttpServletRequest request) {
		ArrayList<Integer> politicianIds = new ArrayList<Integer>(); 
		// Saving 3 best matched user ids into the ArrayList<Integer> from Set
		for (Integer answerIntegers : controll.getAnswers(resultsArrayList)) {
			politicianIds.add(answerIntegers);
		}
		// Query with positional parameters
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin(); // letters in the query must be alllways the same!
		List<Politicians> politiciansList = em.createQuery("SELECT p FROM Politicians p WHERE p.politicID LIKE ?1") // -> faster execute																	// execute
				.setParameter(1, politicianIds.get(1)) // our second best candidate
				.getResultList(); // list of all politicians with selected id from the table
		em.getTransaction().commit();
		em.close();

		for (Politicians politician : politiciansList) {
			System.out.println("Politician2:" + politician.getFirstName());
				request.setAttribute("politic2", politician);
		}

	}
	

	public void getPolitician3(HttpServletRequest request) {
		ArrayList<Integer> politicianIds = new ArrayList<Integer>(); 
		// Saving 3 best matched user ids into the ArrayList<Integer> from Set
		for (Integer answerIntegers : controll.getAnswers(resultsArrayList)) {
			politicianIds.add(answerIntegers);
		}
		// Query with positional parameters
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin(); // letters in the query must be alllways the same!
		List<Politicians> politiciansList = em.createQuery("SELECT p FROM Politicians p WHERE p.politicID LIKE ?1") // -> faster execute																	// execute
				.setParameter(1, politicianIds.get(2)) // our third best candidate
				.getResultList(); // list of all politicians with selected id from the table
		em.getTransaction().commit();
		em.close();

		for (Politicians politician : politiciansList) {
			System.out.println("Politician3:" + politician.getFirstName());
				request.setAttribute("politic3", politician);
		}

	}
	//This method is needed to asign request from GetData into GetResults.java - the reason is that we cannot work with request
	//even if we initialized it in here it will be giving allways null bcs this class isnt calling via path, the request,response is only working with services/servlets....
	public void linkToPage(HttpServletRequest request, List<QueAnsRanUser> list2) {
		getQuestions(list2);
		getPolitician1(request);
		getPolitician2(request);
		getPolitician3(request);
	}
	
}
