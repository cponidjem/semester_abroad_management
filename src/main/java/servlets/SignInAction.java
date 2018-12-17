package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Client;
import beans.Role;
import utility.Constants;

@WebServlet("/sign_in_action")
public class SignInAction extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("client")==null) {
			//Get request parameters
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			Role role = Role.valueOf(request.getParameter("role"));
			
			//Create client
			Client client = new Client(login, password, role);
			
			// TODO : call API with client as json string
			if (login.equals("user") && password.equals("user")) {
				session.setAttribute("client", client);
			}
			// TODO 
		}
		response.sendRedirect("home_page");
	
		
	}
}
