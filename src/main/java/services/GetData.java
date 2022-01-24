package services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.ResponseBuilder;

import java.io.*;


import data.CountOfCand;
import data.InvitationCodes;
import data.Politicians;
import data.QueAnsRanUser;
import data.Questions;
import data.Status;

/**
 * Service used to operate with questions for the quiz and forwarding to resultPage.jsp
 * @author Cezar
 *
 */
@Path("/getdata")
public class GetData {
	//---------------------------------------------------------------
	//Service used for forwarding into the resultPage.jsp
	//Contains another service methods
	//---------------------------------------------------------------
	//for connectionn
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("votingapp");
	
	//--Important-- Request/Response are working only with @Path annotations (services, servlets) otherwise it will be null--
	@Context
	HttpServletRequest request;
	@Context
	HttpServletResponse response;
	
	
	/**
	 * Method used to read all questions from the database
	 */
	@GET
	@Path("/allquestions")
	public void allQuestions() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		//getting all questions
		List<Questions> allQuestions = em.createQuery("SELECT q FROM Questions q").getResultList();
		em.getTransaction().commit();
		em.close();
		//sending the list to the jsp
		request.setAttribute("allQuestions", allQuestions);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/Questions.jsp");
		try {
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	/**
	 * method used to store the random user results in the db and redirect to results page
	 * @param userAnswers - receives all questions, their ids and random user answers as a string from a form
	 */

	//method used to store the random user results in the db and redirect to results page
	//after user finish the quiz and click on the show results button

	@POST
	@Path("/storeresults")
	@Consumes("application/x-www-form-urlencoded")
	public void storeResults(@FormParam("values") String userAnswers) {
		//get questionID, question, questionAnswer of site visitor as a string parameter
	    //split it to get each question with its id and answer separately
	    String[] userAnswersDivided1 = userAnswers.split("-");
	    
	    List<String[]> dividedAnswers = new ArrayList<String[]>();//list of arrays of string for questionID, question, questionAnswer
	    List<QueAnsRanUser> dividedAnswers1 = new ArrayList<QueAnsRanUser>();

	    for (String s : userAnswersDivided1) {
	    	String[] answerDivision = s.split("_");
	    	dividedAnswers.add(answerDivision);
	    	QueAnsRanUser questionAnswer = new QueAnsRanUser();
	    	questionAnswer.setQuestionId(Integer.parseInt(dividedAnswers.get(dividedAnswers.size()-1)[0]));
	    	questionAnswer.setQuestion(dividedAnswers.get(dividedAnswers.size()-1)[1]);
	    	questionAnswer.setQuestionAnswer(Integer.parseInt(dividedAnswers.get(dividedAnswers.size()-1)[2]));
	    	dividedAnswers1.add(questionAnswer);
	    	
	    }//spliting again to separate id and answer from question. saving information into objects and adding each object to list
	    
	    //adding all answers anonymously in the db for statistic
	    EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    for(QueAnsRanUser qaru : dividedAnswers1) {
	    	em.persist(qaru);
	    }
	    em.getTransaction().commit();
	    em.close();
	    
	  //Sending answers to GetResults.java
	    GetResults gr = new GetResults();
	    gr.linkToPage(request,dividedAnswers1); // sending request and answers into the GetResults.java
	  //Redirecting into the resultPage.jsp
	    request.setAttribute("results", dividedAnswers1);
	    RequestDispatcher rd=request.getRequestDispatcher("/jsp/resultPage.jsp");
	    
	    HttpSession session =request.getSession();  //return pre exististing session if not exist-create the new one
		//Saving session atributes into the list  //Session will be getted from GetResults_detail
	    session.setAttribute("answers", dividedAnswers1);
	    
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}