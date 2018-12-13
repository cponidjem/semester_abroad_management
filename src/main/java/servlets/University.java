package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URL;

import utility.Constants;
import beans.*;

@WebServlet("/university")
public class University extends HttpServlet{
	public static final String CONTENT 			= "university";
    public static final String PATH				= ".";
    
    private UniversityBean getUniversity(int id) {
		URL obj;
		try {
			// TODO call real service
			obj = new URL("http://localhost:8080/RestProject/webapi/UnivRessource/"+id);
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
			UniversityBean university = new ObjectMapper().readValue(response.toString(), UniversityBean.class);
			return university;
		} catch (Exception e) {			
			e.printStackTrace();
			return null;
		}
		
		
    	
    }
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		int id = Integer.parseInt(request.getParameter("id"));
		UniversityBean university = getUniversity(id);
		
		request.setAttribute(Constants.CHAMP_CONTENT, CONTENT);
        request.setAttribute(Constants.CHAMP_TITLE, university.getName());
        request.setAttribute(Constants.CHAMP_PATH, PATH);
        request.setAttribute("university", university);
        
		this.getServletContext().getRequestDispatcher(Constants.VUE).forward(request,response);		
	}
}
