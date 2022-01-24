package services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import data.GetResultsControll;
import data.Politicians;
import data.QueAnsRanUser;
import data.QuestionAnswerPoliticObj;
import data.Questionsanswers;
//import rest.ProfileService;
/**
 * 	//---------------------------------------------------------------
	//Service used for forwarding into the resultPage_detail.jsp
	//Contains another service methods
	//---------------------------------------------------------------
	 * 
 * @author Jozef 
 *
 *
 */

@Path("/getresultsdetail")
public class GetResults_detail {

	//for connectionn
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("votingapp");

	//--Important-- Request/Response are working only with @Path annotations (services, servlets) otherwise it will be null--
	@Context
	HttpServletRequest request;
	@Context
	HttpServletResponse response;
	GetResultsControll controll = new GetResultsControll();
	GetResults getresults  = new GetResults();
	
	@GET
	@Path("/getpoliticiandetail/{userid}") //getting politician ID from detail from ResultPage.jsp an sending it into the ResultPage_detail.jsp 
	public void PoliticianDetail(@PathParam("userid") int userID) {
		
		HttpSession session =request.getSession();  //return pre exististing session if not exist-create the new one
		//Saving session atributes into the list  //Session will be getted from GetResults_detail
		List<QueAnsRanUser> questionsGetted = new ArrayList<QueAnsRanUser>();
		questionsGetted = (List<QueAnsRanUser>) session.getAttribute("answers");	
		
		// Detailed politician info
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin(); // letters in the query must be allways the same!
		
		//positional parameters in the query
		//---------------------------------------------------------------
		//Positional parameters are prefixed with a question mark (?) followed the numeric position of the parameter in the query.
		//Use of positional parameters with java persistence language is faster to execute compare to using column names
		//---------------------------------------------------------------
		//Getting detailed politic as single result
		Politicians politician = new Politicians();
		Query query = (Query) em.createQuery("SELECT p FROM Politicians p WHERE p.politicID LIKE ?1");
		politician = (Politicians) query.setParameter(1, userID)
				.getSingleResult();
		// Getting List of all questions for selected ID from the table Questionanswers
		List<Questionsanswers> questions = em.createQuery("SELECT q FROM Questionsanswers q WHERE q.userID LIKE ?1") 
				.setParameter(1, userID)
				.getResultList(); // list	
		em.getTransaction().commit();
		em.close();
		
		ArrayList<Questionsanswers> politicianAnswers = new ArrayList<Questionsanswers>();

		for (Questionsanswers question : questions) {
				politicianAnswers.add(question); //saving questions into the new array
		}

		// Connecting values from user array and politician array into one, so we can
		// use it in our resultpage detail.
		System.out.println("Size of the politic answers" + politicianAnswers.size());

		
		System.out.println("Questions from getresults: " + questionsGetted);
		//Creating a object what we will send into the resultPage_detail.jsp
		ArrayList<QuestionAnswerPoliticObj> questionUserPoliticAnswer = new ArrayList<QuestionAnswerPoliticObj>();
		for (int x = 0; x < politicianAnswers.size()-1; x++) {
			System.out.println("Sizes:" + x);
			QuestionAnswerPoliticObj answerPoliticObj = new QuestionAnswerPoliticObj();
			answerPoliticObj.setPoliticianAnswer(politicianAnswers.get(x).getAnswer()); // This is from our politician																	// answers
			answerPoliticObj.setQuestion(questionsGetted.get(x).getQuestion()); // Those are values from our session																// (my answers)
			answerPoliticObj.setQuestionAnswer(questionsGetted.get(x).getQuestionAnswer());
			answerPoliticObj.setQuestionId(questionsGetted.get(x).getQuestionId());
			questionUserPoliticAnswer.add(answerPoliticObj);

		}
				request.setAttribute("questionsAnswersUserPoliticList", questionUserPoliticAnswer);
				request.setAttribute("politicDetail", politician);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/resultPage_detail.jsp");
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
