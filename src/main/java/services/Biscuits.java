package services;

import java.io.IOException;
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
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import data.CountOfQuestions;
import data.Politicians;
import data.Questions;
import data.Status;
/**
 * Service for redirecting to Questions_list.jsp and adding, getting, editing, removing questions from the database
 * @author Samantha
 *
 */
@Path("/questionlist")
public class Biscuits {
	//Context is used to be able to use requestdispatcher and forward request and response
	@Context HttpServletRequest request; 
	@Context HttpServletResponse response;
	//Creates an EntityManagerFactory to be able to use persistence and work with jpa
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("votingapp");
	
	
	/**
	 * Method for reading all questions from the database
	 */
	@GET
	@Path("/questionsedit")
	public void questionlists () {
		//checks if the user is signed in nad if signed in as interviewer
		HttpSession session = request.getSession(true);
		if(session.getAttribute("role") != null) {
			if(session.getAttribute("role").toString().contentEquals("interviewer")) {
				//Creates an EntityManager to be able to work with the database
				EntityManager em=emf.createEntityManager();
				em.getTransaction().begin();
				//Reads all questions from the database
				List <Questions> questions = em.createQuery("SELECT q FROM Questions q").getResultList();
				em.getTransaction().commit();
				List <CountOfQuestions> countquestions = new ArrayList<CountOfQuestions>();
				CountOfQuestions coq = new CountOfQuestions(questions.size());
				//Sets the total number of questions to the list of objects
				countquestions.add(coq);
				//Sends as request attributes all questions and the total number of them
			    request.setAttribute("BlobQuestions", questions);
			    request.setAttribute("numberofquestions", countquestions);
			    
			    //Redirects to the questions list jsp file
			    RequestDispatcher rd=request.getRequestDispatcher("/jsp/Questions_list.jsp");
				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		} else {
				
			}
			
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/loginpage.jsp");
			try {
				rd.forward(request, response);
				//rd2.forward(request, response);
	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	/**
	 * Method for adding a question in the database
	 * @param question - received from a form
	 */
	@POST
	@Path("/addquestion")
	@Consumes("application/x-www-form-urlencoded")
	public void addingquestion (@FormParam ("question") String question) {
		//Checks if the question isn't null
		if(question.length() > 0)
		{	
			//Creates a new object of the new question and a new message for the user
			Questions quest = new Questions();
			Status message = new Status(); 
			quest.setQuestionText(question);
			//Creates an EntityManager to be able to work with the database
			EntityManager em=emf.createEntityManager();
			em.getTransaction().begin();
			//Checks if there is already such a question in the database
			List <Questions> allquestions = em.createQuery("SELECT c FROM Questions c WHERE c.questionText LIKE :cuestiontext").setParameter("cuestiontext", question).getResultList();
			em.getTransaction().commit();
			
			//If there isn't such an entry, it continues processing
			if (allquestions.size() == 0) {
				em.getTransaction().begin();
				//Insert the new question in the database
				em.persist(quest);
				em.getTransaction().commit();
				message.setCode("Question added!");
				//Sends as a request attribute the message for the user
				request.setAttribute("addmessage", message);
				//Call the method that gets all questions so that the page will be reloaded
				questionlists();
			} else {
				message.setCode("Question already exists!");
				//Sends as a request attribute the message for the user
				request.setAttribute("addmessage", message);
				//Call the method that gets all questions so that the page will be reloaded
				questionlists();
				
			}
		} else 
		{
			//Creates a new object for message for the user
			Status message = new Status();
			message.setCode("Please enter a valid question!");
			//Sends as a request attribute the message for the user
			request.setAttribute("addmessage", message);
			//Call the method that gets all questions so that the page will be reloaded
			questionlists();
		}
		
		
	}
	
	
	/**
	 * Method for deleting a question from the database
	 * Uses POST annotation, because it takes data from a form
	 * @param delquestion - received from a form
	 */
	@POST
	@Path("/deletequestion")
	@Consumes("application/x-www-form-urlencoded")
	public void deletequestion (@FormParam ("inputquestion") int delquestion) {
		//Creates an EntityManager to be able to work with the database
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		//Find the entry with the specified id
		Questions quest = em.find(Questions.class, delquestion);
		//If there is such an entry it deletes it
		if (quest != null) {
			
			em.remove(quest);
			
		}
		em.getTransaction().commit();
		//Call the method that gets all questions so that the page will be reloaded
		questionlists();
	}
	

	/**
	 * Method for editing a question
	 * @param questionId - received from a form
	 * @param newquestion - received from a form
	 */
	@POST
	@Path("/editquestion")
	@Consumes("application/x-www-form-urlencoded")
	public void editingquestions (@FormParam ("questionid") int questionId, @FormParam ("newquestion") String newquestion) {
		//Creates a new questions object with the new question provided by the user
		Questions question = new Questions(questionId, newquestion);
		//Creates an EntityManager to be able to work with the database
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		//Find the entry with the specified id
		Questions quest = em.find(Questions.class, questionId);
		//If there is such an entry it replaces it with new object
		if (quest != null) {
			
			em.merge(question);
		}
		em.getTransaction().commit();
		//Call the method that gets all questions so that the page will be reloaded
		questionlists();
	}
	
	

}

