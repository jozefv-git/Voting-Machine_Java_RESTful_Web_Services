package client;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.*;

@WebServlet(
	    name = "PrintAnswers",
	    urlPatterns = {"/printanswers"}
	)

/**
 * Servlet used to download a .txt file with user answers
 * @author Cezar
 *
 */
public class DownloadClient extends HttpServlet {

	@Override
	  public void doPost(HttpServletRequest request, HttpServletResponse response) 
	      throws IOException, ServletException {

		response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    //get questionID, question, questionAnswer of site visitor as a string
	    String userAnswers = request.getParameter("values");
	    String[] userAnswersDivided1 = userAnswers.split("-");//split it to get each question with its id and answer separately
	    
	    
	    List<String[]> dividedAnswers = new ArrayList<String[]>();//list of arrays of string for questionID, question, questionAnswer


	    for (String s : userAnswersDivided1) {
	    	String[] answerDivision = s.split("_");
	    	dividedAnswers.add(answerDivision);

	    }//spliting again to separate id and answer from question
	    

		File f = new File("answers.txt");//create a new file or use the existing one
		try {
			FileWriter myWriter = new FileWriter(f);
	        //writing answers to the file
	        for (int i = 0; i<dividedAnswers.size(); i++) {
	        	myWriter.write((i+1) + ". " + dividedAnswers.get(i)[1] + "\n");
	        	switch(Integer.parseInt(dividedAnswers.get(i)[2])) {
	        		case 0:
	        			myWriter.write("You did not choose an answer!\n\n");
	        			break;
	    			case 1:
	    				myWriter.write("Totally disagree!\n\n");
	        			break;
					case 2:
						myWriter.write("Slightly disagree!\n\n");
	        			break;
					case 3:
						myWriter.write("Neutral!\n\n");
						break;
					case 4:
						myWriter.write("Slightly agree!\n\n");
	        			break;
					case 5:
						myWriter.write("Totally agree!\n\n");
	        			break;
	    			default:
		
	        	}
	        	
	        	
	        }
	        myWriter.close();

	    } catch (Exception e) {
	    }

		// reads input file from an absolute path
	    //download the file
	    FileInputStream inStream = new FileInputStream(f);
	     
	    // if you want to use a relative path to context root:
	    String relativePath = getServletContext().getRealPath("");
	    System.out.println("relativePath = " + relativePath);
	     
	    // obtains ServletContext
	    ServletContext context = getServletContext();
	     
	    // gets MIME type of the file
	    String mimeType = context.getMimeType(f.getAbsolutePath());
	    if (mimeType == null) {        
	        // set to binary type if MIME mapping not found
	        mimeType = "application/octet-stream";
	    }
	    System.out.println("MIME type: " + mimeType);
	     
	    // modifies response
	    response.setContentType(mimeType);
	    response.setContentLength((int) f.length());
	     
	    // forces download
	    String headerKey = "Content-Disposition";
	    String headerValue = String.format("attachment; filename=\"%s\"", f.getName());
	    response.setHeader(headerKey, headerValue);
	     
	    // obtains response's output stream
	    OutputStream outStream = response.getOutputStream();
	     
	    byte[] buffer = new byte[4096];
	    int bytesRead = -1;
	     
	    while ((bytesRead = inStream.read(buffer)) != -1) {
	        outStream.write(buffer, 0, bytesRead);
	    }
	     
	    inStream.close();
	    outStream.close(); 
	    
	}
}
