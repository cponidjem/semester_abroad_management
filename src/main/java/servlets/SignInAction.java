package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utility.Constants;

@WebServlet("/sign_in_action")
public class SignInAction extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("role")==null) {
			String role = request.getParameter("role");
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			// TODO : call API
			if (login.equals("user") && password.equals("user")) {
				session.setAttribute("role", role);
				session.setAttribute("login", login);
			}
			// TODO 
		}
		response.sendRedirect("home_page");
	
		
	}
}
