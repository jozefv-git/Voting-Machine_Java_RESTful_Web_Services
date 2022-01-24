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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import data.CountOfCand;
import data.InvitationCodes;
import data.Politicians;
import data.Status;
/**
 * Service used for operating with candidates in candidates_list and forwarding to Candidates_list.jsp,Questions.jsp
 * @author Cezar
 *
 */
@Path("/getdata1")
public class Candidates {
	
	
	//---------------------------------------------------------------
	//Service used for forwarding into the Candidates_list.jsp,Questions.jsp
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
	 * method used to retrieve all candidates from the db and their count
	 */
	@GET
	@Path("/getcandidates")
	public void getCandidates() {
		//checks if the user is signed in nad if signed in as interviewer
		HttpSession session = request.getSession(true);
		if(session.getAttribute("role") != null) {
			if(session.getAttribute("role").toString().contentEquals("interviewer")) {
			    List<CountOfCand> candCount =  new ArrayList<CountOfCand>();//list of nr of candidates objects
			    EntityManager em = emf.createEntityManager();
			    em.getTransaction().begin();
			    //retrieving all candidates from the db
			    List<Politicians> candidateList = em.createQuery("SELECT p FROM Politicians p WHERE p.role LIKE :role").setParameter("role", "candidate").getResultList();
			    em.getTransaction().commit();
			    em.close();
			    CountOfCand aNr = new CountOfCand(candidateList.size());
			    candCount.add(aNr);//getting the amount of candidates
			    //sending data to the jsp
			    request.setAttribute("nrOfCands", candCount);
			    request.setAttribute("allCands", candidateList);
			    RequestDispatcher rd=request.getRequestDispatcher("/jsp/Candidates_list.jsp");
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
				
	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * method that deletes a candidate
	 * @param id - receives the id of the candidate to be deleted as a PathParam
	 */
	@GET //get because we don't use clients and browser didn't support sending delete request through js
	@Path("/deletecandidate/{p1}")
	public void deleteACand(@PathParam("p1") int id) {
		//checks if the user is signed in nad if signed in as interviewer
		HttpSession session = request.getSession(true);
		if(session.getAttribute("role") != null) {
			if(session.getAttribute("role").toString().contentEquals("interviewer")) {
				EntityManager em = emf.createEntityManager();
				em.getTransaction().begin();
				//checking if there is such a candidate
				Politicians p = em.find(Politicians.class, id);
				if (p != null) {
					em.remove(p);
				}
				em.getTransaction().commit();
				//reloads the page after deleting it
				getCandidates();
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
	 * method used to store the invitation code and email in the db
	 * @param code
	 * @param mail
	 * receives the parameters from a form
	 */
	@POST
	@Path("/storecode")
	@Consumes("application/x-www-form-urlencoded")
	public void storeCode(@FormParam("invitationCode") String code, @FormParam("emailAddress") String mail) {
		//creating new invitationcode object
		InvitationCodes invCode = new InvitationCodes(code, mail);
		boolean a = false;
		boolean b = false;
		Status status = new Status();
		//checking if code isnt null
		if(invCode.getCode().length() != 0) {
			
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			//retrieving everything from the table and looping through it to see if there are already such entries
			//not the best practice could be done easier by selecting by email and invitation code
			List<InvitationCodes> checkCodes = em.createQuery("SELECT c FROM InvitationCodes c").getResultList();
			for (InvitationCodes invi: checkCodes) {
				if (invi.getCode().equals(code)) {
					a = true;
				}
				if (invi.getMail().equals(mail)) {
					b = true;
				}
			}
			if (!a) {
				if (!b) {
					//adding the new object to the db
					em.persist(invCode);
					em.getTransaction().commit();
					em.close();
					status.setCode("Invite code sent");
					request.setAttribute("status", status);
					getCandidates();
					
					//printing error messages for the user
				} else {
					em.close();
					status.setCode("Mail already used");
					request.setAttribute("status", status);
					getCandidates();
					
				}
			} else {
				em.close();
				status.setCode("Code already used!");
				request.setAttribute("status", status);
				getCandidates();
				
			}
		} else {
			status.setCode("Null code");
			request.setAttribute("status", status);
			getCandidates();
			
		}
		
	}
}
