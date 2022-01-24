package services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import data.Politicians;
import data.Questions;
import data.Questionsanswers;

/**
 * Service handles profile page data
 * @author Samu Uunonen
 *
 */
@Path("/profile")
public class ProfileService {
	private boolean logged;
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("votingapp");

	@Context
	HttpServletRequest request;
	@Context
	HttpServletResponse response;

	/**
	 * Dispatch data to profile-page.jsp or profile-page-general.jsp
	 * @param politicID
	 */
	@GET
	@Path("/readprofile/{politicID}")
	public void readProfile(@PathParam("politicID") int politicID) {
		// Read data from database
		Politicians politic = readProfileData(politicID);
		List<Questions> questions = readQuestions();
		List<Questionsanswers> answers = readAnswers(politicID);
		
		// Set data into attributes for JSP file
		
		request.setAttribute("politicians", politic);
		request.setAttribute("questions", questions);
		request.setAttribute("questionsanswers", answers);
		
		HttpSession session = request.getSession();
		String sessionRole = String.valueOf(session.getAttribute("role"));

		if (sessionRole.equalsIgnoreCase("candidate")) {
			logged = true;
		}
		
		RequestDispatcher rd;
		/*
		 * Dispatch into correct page, based on if user is logged in or not.
		 */
		if (logged) {
			rd = request.getRequestDispatcher("/jsp/profile-page.jsp");
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
			rd = request.getRequestDispatcher("/jsp/profile-page-general.jsp");
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
	

	
	/**
	 * Dispatch data into editprofile.jsp
	 * @param politicID
	 */
	@GET
	@Path("/profiletoupdate/{politicID}")
	public void readProfileToUpdate(@PathParam("politicID") int politicID) {
		// Read data from database
		Politicians politic = readProfileData(politicID);
		List<Questions> questions = readQuestions();
		List<Questionsanswers> answers = readAnswers(politicID);

		// Set data into attributes for JSP file
		request.setAttribute("politicians", politic);
		request.setAttribute("questions", questions);
		request.setAttribute("questionsanswers", answers);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/editprofile.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			System.out.println("Error in request dispatcher");
		}
	}
	

	
	
	/**
	 * Updates politicians information and answers to database.
	 * @param politicID
	 * @param firstname
	 * @param lastname
	 * @param nation
	 * @param city
	 * @param age
	 * @param sex
	 * @param party
	 * @param description
	 * @param fileInputStream
	 * @param fileMetaData
	 * @param politicNumber
	 * @param email
	 * @param password
	 * @param answer1
	 * @param answer2
	 * @param answer3
	 * @param answer4
	 * @param answer5
	 * @param answer6
	 * @param answer7
	 * @param answer8
	 * @param answer9
	 * @param answer10
	 * @param answer11
	 * @param answer12
	 * @param answer13
	 * @param answer14
	 * @param answer15
	 * @param answer16
	 * @param answer17
	 * @param answer18
	 * @param answer19
	 * @param answer20
	 * @param req
	 * @param sc
	 * @throws Exception
	 */
	@POST
	@Path("/editprofile/{politicID}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void editProfile(@PathParam("politicID") int politicID,
			@FormDataParam("firstNameTextField") String firstname,
			@FormDataParam("lastNameTextField") String lastname,
			@FormDataParam("nationTextField") String nation,
			@FormDataParam("cityTextField") String city,
			@FormDataParam("ageTextField") String age,
			@FormDataParam("sexTextField") String sex,
			@FormDataParam("partyTextField") String party,
			@FormDataParam("profile-text") String description,
			@FormDataParam("file-textField") InputStream fileInputStream,
			@FormDataParam("file-textField") FormDataContentDisposition fileMetaData,
            @FormDataParam("candidateNumber") String politicNumber,
			@FormDataParam("email-textField") String email,
			@FormDataParam("password-textField") String password,
			@FormDataParam("answer-radio-1") String answer1,
			@FormDataParam("answer-radio-2") String answer2,
			@FormDataParam("answer-radio-3") String answer3,
			@FormDataParam("answer-radio-4") String answer4,
			@FormDataParam("answer-radio-5") String answer5,
			@FormDataParam("answer-radio-6") String answer6,
			@FormDataParam("answer-radio-7") String answer7,
			@FormDataParam("answer-radio-8") String answer8,
			@FormDataParam("answer-radio-9") String answer9,
			@FormDataParam("answer-radio-10") String answer10,
			@FormDataParam("answer-radio-11") String answer11,
			@FormDataParam("answer-radio-12") String answer12,
			@FormDataParam("answer-radio-13") String answer13,
			@FormDataParam("answer-radio-14") String answer14,
			@FormDataParam("answer-radio-15") String answer15,
			@FormDataParam("answer-radio-16") String answer16,
			@FormDataParam("answer-radio-17") String answer17,
			@FormDataParam("answer-radio-18") String answer18,
			@FormDataParam("answer-radio-19") String answer19,
			@FormDataParam("answer-radio-20") String answer20,
			@FormDataParam("answer-radio-21") String answer21,
			@FormDataParam("answer-radio-22") String answer22,
			@FormDataParam("answer-radio-23") String answer23,
			@FormDataParam("answer-radio-24") String answer24,
			@FormDataParam("answer-radio-25") String answer25,
			@Context HttpServletRequest req,
			@Context HttpServletResponse res,
			@Context ServletContext sc) throws Exception {
		/*
		 * Collect answers to arraylist
		 */
		ArrayList<String> formAnswers = new ArrayList<String>();
		formAnswers.add(answer1);
		formAnswers.add(answer2);
		formAnswers.add(answer3);
		formAnswers.add(answer4);
		formAnswers.add(answer5);
		formAnswers.add(answer6);
		formAnswers.add(answer7);
		formAnswers.add(answer8);
		formAnswers.add(answer9);
		formAnswers.add(answer10);
		formAnswers.add(answer11);
		formAnswers.add(answer12);
		formAnswers.add(answer13);
		formAnswers.add(answer14);
		formAnswers.add(answer15);
		formAnswers.add(answer16);
		formAnswers.add(answer17);
		formAnswers.add(answer18);
		formAnswers.add(answer19);
		formAnswers.add(answer20);
		formAnswers.add(answer21);
		formAnswers.add(answer22);
		formAnswers.add(answer23);
		formAnswers.add(answer24);
		formAnswers.add(answer25);
		
		/*
		 *  Upload image file and save image name into database
		 */
		String picture ="id-" + politicID + "-";	// image name starts with this String.
		Politicians oldPolitic = readProfileData(politicID);
		
		// If user haven't upload new image in profile, set old image name from database.
		String filename = fileMetaData.getFileName();
		if (filename.equalsIgnoreCase("")) {
			picture = oldPolitic.getPicture();
	
		} else {
			picture += filename;
			String UPLOAD_PATH = sc.getRealPath("/images") + "/" + picture;
			uploadImage(UPLOAD_PATH, fileInputStream);
		}
		
		/*
		 *  Update politic data into database
		 */
		String role = oldPolitic.getRole(); // role of candidate, it may come from session later
		String oldPassword = oldPolitic.getPssword();
		String savePassword = "";
		// Check if password is changed, and if it is changed crypt new password.
		if (oldPassword.equals(password)) {
			savePassword = password;
		} else {
			Popcorn pop = new Popcorn();
			savePassword = pop.crypt(password);
		}
		
		Politicians politic = new Politicians(politicID, Integer.parseInt(politicNumber), firstname, lastname, nation, party, Integer.parseInt(age), sex, description, city, email, savePassword, picture, role);
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Politicians p = em.find(Politicians.class, politicID);
		if (p != null) {
			em.merge(politic);
		}

		/*
		 * Update if answers exists, or insert new answers into database.
		 */
		List<Questionsanswers> answers = readAnswers(politicID);
		List<Questions> questions = readQuestions();
		if (answers.size() > 0) {
			for  (int i = 0; i < formAnswers.size(); i++) {
				if (formAnswers.get(i) != null) {
					Query query = em.createQuery("UPDATE Questionsanswers a SET a.answer = :answer WHERE a.userID = :userID AND a.questionID = :questionID");
					query.setParameter("answer", Integer.parseInt((String) formAnswers.get(i)));
					query.setParameter("userID", politicID);
					query.setParameter("questionID", questions.get(i).getQuestionID());
					query.executeUpdate();
					System.out.println("Records has been updated: answer: " + formAnswers.get(i) +", questionID: "  + questions.get(i).getQuestionID());
				} 
			}
		} else {
			for (int i = 0; i < formAnswers.size(); i++) {
				if (formAnswers.get(i) != null) {					
					Questionsanswers qa1 = new Questionsanswers();
					qa1.setAnswer(Integer.parseInt((String) formAnswers.get(i)));
					qa1.setUserID(politicID);
					qa1.setQuestionID(questions.get(i).getQuestionID());
					em.persist(qa1);
					System.out.println("New record added: answer: " + formAnswers.get(i) +", questionID: " + qa1.getQuestionID());
				}
			}
		}

		em.getTransaction().commit();
		em.close();
		
		// Read updated data from database
		Politicians newPoliticData = readProfileData(politicID);
		List<Questions> newQuestions = readQuestions();
		List<Questionsanswers> newAnswers = readAnswers(politicID);
		
		// Set data into attributes for JSP file
		request.setAttribute("politicians", newPoliticData);
		request.setAttribute("questions", newQuestions);
		request.setAttribute("questionsanswers", newAnswers);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/profile-page.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			System.out.println("Error in request dispatcher");
		} 
		
	}
	
	/**
	 * Delete politician and answers from database.
	 * @param politicID
	 */
	@GET
	@Path("/deletepolitician/{politicID}")
	public void deletePolitician(@PathParam("politicID") int politicID) {
		
		List<Questions> questions = readQuestions();
		System.out.println(questions.size());

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		// Delete politic information
		Politicians politic = em.find(Politicians.class, politicID);
		em.remove(politic);
		System.out.println("user is deleted");
		
		// Delete answers
		deleteAnswers(politicID);
		
		em.getTransaction().commit();
		em.close(); 
		
		RequestDispatcher rd = request.getRequestDispatcher("/intro.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			System.out.println("Error in request dispatcher");
		}
		
	}
	
	/**
	 * Reset politcian's answers by deleting answers from database
	 * @param politicID
	 */
	@GET
	@Path("/resetanswers/{politicID}")
	public void resetAnswers(@PathParam("politicID") int politicID) {
		deleteAnswers(politicID);
		readProfileToUpdate(politicID);
	}
	
	
	
	/**
	 * Read politicians information from database
	 * @param politicID
	 * @return
	 */
	protected Politicians readProfileData(int politicID) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Politicians politic = em.find(Politicians.class, politicID);
		em.getTransaction().commit();
		em.close();
		
		return politic;
	}
	
	/**
	 * Read questions from database
	 * @return
	 */
	protected List<Questions> readQuestions() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Questions> list = em.createQuery("SELECT q FROM Questions q").getResultList();
		em.getTransaction().commit();
		em.close();
		
		return list;
	}
	
	
	/**
	 * Read answers from database
	 * @return
	 */
	protected List<Questionsanswers> readAnswers(int politicID) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT a FROM Questionsanswers a WHERE a.userID = :userID ORDER BY a.questionID");
		query.setParameter("userID", politicID);
		List<Questionsanswers> list = query.getResultList();
		em.getTransaction().commit();
		em.close();
		
		return list;
	}
	
	/**
	 * Delete answers from database
	 * @param politicID
	 */
	private void deleteAnswers(int politicID) {
		List<Questionsanswers> answers = readAnswers(politicID);
		if (answers.size() != 0) {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();
			Query query = em.createQuery("DELETE FROM Questionsanswers u WHERE u.userID = :userID");
			query.setParameter("userID", politicID);
			query.executeUpdate();
			em.getTransaction().commit();
			em.close();
		} 
	}
	
	/**
	 * Upload image to local pc
	 * @param UPLOAD_PATH
	 * @param fileInputStream
	 */
	private void uploadImage(String UPLOAD_PATH, InputStream fileInputStream) {
		try{
			int read = 0;
	        byte[] bytes = new byte[1024];
	        OutputStream out = new FileOutputStream(new File(UPLOAD_PATH));
	        while ((read = fileInputStream.read(bytes)) != -1) 
	        {
	            out.write(bytes, 0, read);
	        }
	        out.flush();
	        out.close();
	        System.out.println("Image uploaded to " + UPLOAD_PATH);
	    } catch (IOException e) {
	        throw new WebApplicationException("Error while uploading file. Please try again !!");
	    }
	}
	
	
}