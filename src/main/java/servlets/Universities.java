package servlets;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.UniversityBean;
import utility.Constants;

@WebServlet("/universities")
public class Universities extends HttpServlet{
	public static final String CONTENT 			= "universities";
    public static final String TITLE 			= "Universities";
    public static final String PATH				= ".";
    
    private List<UniversityBean> getUniversities() {
		URL obj;
		try {
			// TODO call real service
			obj = new URL("http://localhost:8080/RestProject/webapi/UnivRessource");
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();			
			List<UniversityBean> universities = new ObjectMapper().readValue(response.toString(), new TypeReference<List<UniversityBean>>(){});
			return universities;
		} catch (Exception e) {			
			e.printStackTrace();
			return new ArrayList<UniversityBean>();
		}    	
    }
    
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
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String keywords = request.getParameter("keywords");
		String country = request.getParameter("country");
		String field = request.getParameter("field");
		
		List<UniversityBean> universities = this.getUniversities();
		
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
        request.setAttribute("universities", universities);
		this.getServletContext().getRequestDispatcher(Constants.VUE).forward(request,response);
	}
}
