package servlets;

import utility.Constants;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sign_in")
public class SignIn extends HttpServlet {
    public static final String CONTENT 			= "sign_in";
    public static final String TITLE 			= "Sign in";
    public static final String PATH				= ".";
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
        request.setAttribute(Constants.CHAMP_CONTENT, CONTENT);
        request.setAttribute(Constants.CHAMP_TITLE, TITLE);
        request.setAttribute(Constants.CHAMP_PATH, PATH);
		this.getServletContext().getRequestDispatcher(Constants.VUE).forward(request,response);		
	}
	
	

}
