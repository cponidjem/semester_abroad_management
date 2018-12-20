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

@WebServlet("/sign_out_action")
public class SignOutAction extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		signOut(request);
		response.sendRedirect("home_page");
	}
	
	private void signOut(HttpServletRequest request) {
		//Get session
		HttpSession session = request.getSession();
		
		//Get client from session
		beans.Client client = (beans.Client)session.getAttribute("client");		
		
		//If there is a httpSession client
		if(client!=null) {
			
			//Get client token
			String token = client.getToken();
			
			//Build DELETE request and get response
			Client httpClient = ClientBuilder.newClient();
	    	Response response = httpClient.target("http://localhost:8080/ServicePath").request().header("token", token).delete();
	    	//TODO Cindy Replace with real service path
	    	
	    	//Get status code
	    	int status = response.getStatus();
	    	
	    	//If the action has been enacted but the response does not include an entity
	    	if (status == 204) {
	    		System.out.println("Sign out succeeded");
	    	}
	    	
	    	request.getSession().invalidate();
		}
		
		/*TODO Cedric
		 * authentication service (delete token ?)
		 * request format : DELETE request with client token in headers 
		 * expected response : 
		 * entity : empty, status : 204 for success
		 * entity : empty, status : 404 for failure
		 * */
	}

}
