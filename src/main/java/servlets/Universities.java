package servlets;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utility.Constants;

@WebServlet("/universities")
public class Universities extends HttpServlet{
	public static final String CONTENT 			= "universities";
    public static final String TITLE 			= "Universities";
    public static final String PATH				= ".";
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String [] countries  = {"Canada", "Japan", "China","New-Zealand"};
		String [] fields  = {"Computer Sciences","Physics","Psychology"};
        
		/* TODO 
		 * call API "get all countries" 
		 * transform json array into array
		 * or json into array list
		 * */
        
        /* TODO 
		 * call API "get all fields" 
		 * transform json array into array
		 * */
		
		
		request.setAttribute(Constants.CHAMP_CONTENT, CONTENT);
        request.setAttribute(Constants.CHAMP_TITLE, TITLE);
        request.setAttribute(Constants.CHAMP_PATH, PATH);
        request.setAttribute("countries", countries);
        request.setAttribute("fields", fields);
		this.getServletContext().getRequestDispatcher(Constants.VUE).forward(request,response);		
	}
}
