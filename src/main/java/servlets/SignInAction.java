package servlets;

import java.io.IOException;

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

import beans.*;
import utility.Constants;

@WebServlet("/sign_in_action")
public class SignInAction extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get session
		HttpSession session = request.getSession();
		
		//If there is no httpSession
		if(session.getAttribute("client")==null) {
			
			//Get request parameters
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			Role role = Role.valueOf(request.getParameter("role"));
			
			//Create client
			beans.Client client = new beans.Client(login, password, role);
			
			//Call REST API Authentication service and get token
			String token = signIn(client);
			
			//If authentication failed (status code 401 or empty body)
			if(token.equals("")) {
				
				//Set failure message
				 request.setAttribute(Constants.CHAMP_MESSAGE, "Authentication failed");
				 
			//If authentication succeeded (status code 200 and issued token)
			} else {
				
				//Store token in client object
				client.setToken(token);
				
				//Store client object in httpSession
				session.setAttribute("client", client);
				
				//Set success message
				request.setAttribute(Constants.CHAMP_MESSAGE, "Successfully logged in");
			}
		}
		
		//Redirect to home page
		response.sendRedirect("home_page");		
	}
	
	private String signIn(beans.Client client) {
		//Initialize token to empty string
		String token = "";
		
		//Build POST request and get response
		Client httpClient = ClientBuilder.newClient();
    	Response response = httpClient.target("http://localhost:8080/ServicePath").request().post(Entity.entity(client, MediaType.APPLICATION_JSON));
    	
    	//Get status code
    	int status = response.getStatus();
    	
    	//If authentication succeeded
    	if (status == 200) {
    		
    		//Read body as token
    		token = response.readEntity(String.class);
    	}	
		
		return token;
		
		/*TODO Cedric
		 * authentication service
		 * request format : POST request with beans.client as json body 
		 * expected response : token string
		 * */
	}
}
