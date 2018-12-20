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
		request.setAttribute(Constants.CHAMP_CONTENT, CONTENT);
        request.setAttribute(Constants.CHAMP_TITLE, TITLE);
        request.setAttribute(Constants.CHAMP_PATH, PATH);
		this.getServletContext().getRequestDispatcher(Constants.VUE).forward(request,response);		
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
		 * request format : POST request with beans.Client as json body 
		 * expected response : 
		 * entity : token string, status : 200 for success
		 * entity : empty, status : 401 for failure
		 * */
	}
}
