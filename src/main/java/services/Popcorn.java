package services;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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


import data.InvitationCodes;
import data.Politicians;
import data.Questions;
import data.Questionsanswers;
import data.Status;

/**
 * 
 * Service for login, registration and forwarding to Dashboard.jsp, loginpage.jsp, profile-page.jsp
 * @author Samantha
 *
 */
@Path("/login")
public class Popcorn {
	//Context is used to be able to use requestdispatcher and forward request and response
	@Context HttpServletRequest request; 
	@Context HttpServletResponse response; 
	//Creates an EntityManagerFactory to be able to use persistence and work with jpa
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("votingapp");
	
	/**
	 * Method for registering a new candidate
	 * @param code - received from a form
	 * @param fname - received from a form
	 * @param lname - received from a form
	 * @param email - received from a form
	 * @param party - received from a form
	 * @param psswrd - received from a form
	 */
	@POST
	@Path("/registraion")
	@Consumes("application/x-www-form-urlencoded")
	public void registation(@FormParam ("code") String code, @FormParam ("fname") String fname, @FormParam ("lname") String lname, @FormParam ("email") String email, @FormParam ("party") String party, @FormParam ("password") String psswrd) {
		//Creates a Status object to save and send the error status to the jsp file
		Status message = new Status();
		//Encrypts the password
		String hpass = crypt(psswrd);
		//Creates an EntityManager to be able to work with the database
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		//Checks if there is an account already registered with such an email
		List <Politicians> regemails = em.createQuery("SELECT e FROM Politicians e WHERE e.email LIKE :checkmail").setParameter("checkmail", email).getResultList();
		em.getTransaction().commit();
		//If there is one, it redirects back to the registration page
		if (regemails.size() != 0) {
			message.setCode("The email is already registerd!");
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/registration.jsp");
			try {
				rd.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}//If there isn't it continues processing
		else {
			em.getTransaction().begin();
			//Checks with a query if there is such an entry in the Invitationcodes table with the specified code and email

			List <InvitationCodes> allemails = em.createQuery("SELECT i FROM InvitationCodes i WHERE i.mail LIKE :checkemail AND i.code LIKE :checkcode").setParameter("checkemail", email).setParameter("checkcode", code).getResultList();

			em.getTransaction().commit();
			//If there isn't such an entry, sets the error code for the user and redirects to the registration page
			if (allemails.size() == 0) {
				message.setCode("Invalid email for this code or the code does not exist!");
				request.setAttribute("message", message);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/registration.jsp");
				try {
					rd.forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}//if there is such an entry, it continues processing
			else {
				//Creates a new Politicians object to be inserted in the database
				Politicians Politic = new Politicians ();
				Politic.setFirstName(fname);
				Politic.setLastName(lname);
				Politic.setEmail(email);
				Politic.setParty(party);

				Politic.setPssword(hpass);

				Politic.setRole("candidate");
				
				em.getTransaction().begin();
				//Inserts the just created object in the database
				em.persist(Politic);
				em.getTransaction().commit();
				//Redirects to the login page
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/loginpage.jsp");
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
		
		
	}
	
	
	/**
	 * Method for logging in
	 * @param email - received from a form
	 * @param psswrd - received from a form
	 */
	@POST
	@Path("/logingin")
	@Consumes("application/x-www-form-urlencoded")
	public void login(@FormParam("email") String email, @FormParam("password") String psswrd) {
		//Encrypts the provided password to check it with the one in the database
		String hpass = crypt(psswrd);
		//Creates an EntityManager to be able to work with the database
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		//Checks if there is such an entry in the database with the provided email and password
		List <Politicians> regemails = em.createQuery("SELECT e FROM Politicians e WHERE e.email LIKE :checkmail AND e.pssword LIKE :checkpasswrd").setParameter("checkmail", email).setParameter("checkpasswrd", hpass).getResultList();
		em.getTransaction().commit();
		//If there is such an entry, it continues processing
		if (regemails.size() != 0) {
			//Start a session
			HttpSession session = request.getSession();
			//Set the required session attributes with the needed data of the logged in politician/interviewer
			session.setAttribute("role", regemails.get(0).getRole());
 			session.setAttribute("politicID",regemails.get(0).getPoliticID());
 			//Based on what kind of account is logged in, it redirects to the correct page
 			if (session.getAttribute("role").equals("candidate")) {
 				ProfileService ps = new ProfileService();
 				int politicID = regemails.get(0).getPoliticID();
 				Politicians politic = ps.readProfileData(politicID);
 				List<Questions> questions = ps.readQuestions();
 				List<Questionsanswers> answers = ps.readAnswers(politicID);
 				
 				// Set data into attributes for JSP file
 				
 				request.setAttribute("politicians", politic);
 				request.setAttribute("questions", questions);
 				request.setAttribute("questionsanswers", answers);
 				
 				RequestDispatcher rd = request.getRequestDispatcher("/jsp/profile-page.jsp");
 				
// 				String link = ps.readProfile(request, regemails.get(0).getPoliticID());
// 				RequestDispatcher rd=request.getRequestDispatcher(link);
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
 				RequestDispatcher rd=request.getRequestDispatcher("/jsp/Dashboard.jsp");
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
	}
	

	/**
	 * Method for logging out
	 * It is accessed from a link on the page
	 */
	@GET
	@Path("/singingout")
	public void singout() {
		//Gets the session
		HttpSession session = request.getSession(true);
		//Terminates the session
		session.invalidate();
		//Redirects to the starting page
		RequestDispatcher rd = request.getRequestDispatcher("/intro.jsp");
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
	
	//Method to encrypt the password
	public static String crypt(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }

        MessageDigest digester;
        try {
            digester = MessageDigest.getInstance("MD5");

            digester.update(str.getBytes());
            byte[] hash = digester.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
                } else {
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
                }
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}

