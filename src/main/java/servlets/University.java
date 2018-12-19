package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import utility.Constants;
import beans.*;

@WebServlet("/university")
public class University extends HttpServlet{
	public static final String CONTENT 			= "university";
    public static final String PATH				= ".";
    
    private UniversityBean getUniversity(int id) {
    	Client client = ClientBuilder.newClient();
    	Response response = client.target("http://localhost:8080/RestProject/webapi/UnivRessource/"+id).request().get();
    	int status = response.getStatus();
    	String content = response.readEntity(String.class);
    	try {
    		UniversityBean university = new ObjectMapper().readValue(content, UniversityBean.class);
    		return university;
    	} catch (IOException e) {
			// TODO Auto-generated catch block
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
