package servlets;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.Role;
import utility.Constants;

@WebServlet("/sign_in_action")
public class SignInAction extends HttpServlet {
	public static final String CONTENT 			= "home_page";
    public static final String TITLE 			= "Home page";
    public static final String PATH				= ".";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get session
		HttpSession session = request.getSession();
		
		//If there is no httpSession client
		if(session.getAttribute("client")==null) {
			
			//Get request parameters
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			Role role = Role.valueOf(request.getParameter("role"));
			
			//Create client
			beans.Client client = new beans.Client(login, password, role);
			
			//Call REST API Authentication service and get token
			Boolean ok = signIn(client);
			
			//If authentication failed (status code 401 or empty body)
			if(!ok) {
				
				//Set failure message
				 request.setAttribute(Constants.CHAMP_MESSAGE, "Authentication failed");
				 
			//If authentication succeeded (status code 200)
			} else {
				//Store client object in httpSession
				session.setAttribute("client", client);
				
				//Set success message
				request.setAttribute(Constants.CHAMP_MESSAGE, "Successfully logged in");
			}
		}
		
		//Redirect to home page
		request.setAttribute(Constants.CHAMP_CONTENT, CONTENT);
        request.setAttribute(Constants.CHAMP_TITLE, TITLE);
        request.setAttribute(Constants.CHAMP_PATH, PATH);
		this.getServletContext().getRequestDispatcher(Constants.VUE).forward(request,response);		
	}
	
	private Boolean signIn(beans.Client client) throws JsonProcessingException {
		
		
		Boolean ok = false;
		
		//System.out.println(Entity.entity(client, MediaType.APPLICATION_JSON));
		
		//Build POST request and get response
		Client httpClient = ClientBuilder.newClient();
    	Response response = httpClient.target("http://localhost:8080/ServicePath").request().post(Entity.entity(client, MediaType.APPLICATION_JSON));
    	
    	/*ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(client);
		
		System.out.println(jsonInString);
		*/
    	
    	//TODO Cindy Replace with real service path
    	
    	//Get status code
    	int status = response.getStatus();
    	
    	//If authentication succeeded
    	if (status == 200) {    		
    		//Read body as token
    		ok = true;
    	}	
		
		return ok;
	}
}