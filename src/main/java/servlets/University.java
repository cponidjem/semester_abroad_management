package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import utility.Constants;
import beans.*;

@WebServlet("/university")
public class University extends HttpServlet{
	public static final String CONTENT 			= "university";
    public static final String PATH				= ".";
    
    private UniversityBean getUniversity(int id, HttpServletRequest request) {
    	UniversityBean university = null;
    	
    	//Build GET request and get response
    	Client client = ClientBuilder.newClient();
    	Response response = client.target("http://localhost:8080/RestProject/webapi/UnivRessource/"+id).request().get();
    	//TODO Cindy Replace with real service path
    	
    	//Get status code
    	int status = response.getStatus();
    	
    	if (status == 404) {
    		request.setAttribute(Constants.CHAMP_MESSAGE, "This university does not exist");
    	} else if(status == 401) {
    		request.setAttribute(Constants.CHAMP_MESSAGE, "You are not authorized to view this information");
    	} else {
        	try {
        		//Read json body as University object
        		university = new ObjectMapper().readValue(response.readEntity(String.class), UniversityBean.class);
        		return university;
        	} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}	
    	}
    	
    	return university;
    	/*TODO Cedric
		 * universities service (get a specific university identified by its id)
		 * request format : GET request at path/id
		 * expected response : 
		 * entity : University university as json, status : 200 for success
		 * entity : empty, status : 404 if not found
		 * entity : empty, status : 401 if not authorized (or is it 403 ?)
		 * */
    	
    	
    }
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		//Get university id from request parameter
		int id = Integer.parseInt(request.getParameter("id"));
		
		////Call REST API Universities service and get university
		UniversityBean university = getUniversity(id, request);
		
		request.setAttribute(Constants.CHAMP_CONTENT, CONTENT);
		request.setAttribute(Constants.CHAMP_PATH, PATH);
		
		if(university != null) {
			 request.setAttribute(Constants.CHAMP_TITLE, university.getName());
			 request.setAttribute("university", university);
		} 
		
		this.getServletContext().getRequestDispatcher(Constants.VUE).forward(request,response);		
	}
}
