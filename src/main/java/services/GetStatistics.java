package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import data.ForStatistic;
import data.Questions;
/**
 * 	//---------------------------------------------------------------
	//Service used for forwarding all questions into the statisticsPage.jsp
	//---------------------------------------------------------------
 * 
 * @author Jozef 
 *
 *
 */

@Path("/getstatistics")
public class GetStatistics {

	//for connectionn
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("votingapp");
	
	//--Important-- Request/Response are working only with @Path annotations (services, servlets) otherwise it will be null--
	@Context
	HttpServletRequest request;
	@Context
	HttpServletResponse response;
	
	@GET
	@Path("/allquestionsstatistic")
	public void allQuestions() {
		//checks if the user is signed in nad if signed in as interviewer
		HttpSession session = request.getSession(true);
		if(session.getAttribute("role") != null) {
			if(session.getAttribute("role").toString().contentEquals("interviewer")) {
				EntityManager em = emf.createEntityManager();
				em.getTransaction().begin();
				List<Questions> questions = em.createQuery("SELECT q FROM Questions q").getResultList();
				em.getTransaction().commit();
				em.close();
				request.setAttribute("question_list", questions);
				System.out.println("Questions" + questions);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/statisticsPage.jsp");
				try {
					rd.forward(request, response);
					//rd2.forward(request, response);
		
				} catch (Exception e) {
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
	

}
