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

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import beans.UniversityBean;
import utility.Constants;

@WebServlet("/universities")
public class Universities extends HttpServlet{
	public static final String CONTENT 			= "universities";
    public static final String TITLE 			= "Universities";
    public static final String PATH				= ".";
    
    private String[] getAllCountries() throws JsonParseException, JsonMappingException, IOException {
    	String[] countries = {};
    	
    	Client client = ClientBuilder.newClient();
    	ObjectMapper objectMapper = new ObjectMapper();
    	Response response = client.target("http://localhost:9090/sambackend/countries").request().get();
    	int status = response.getStatus();
    	
    	if(status == 200){
    		String content = response.readEntity(String.class);
    		countries = objectMapper.readValue(content, String[].class);
    	} 
    	
    	return countries;
    }
    
    private String[] getAllFields() throws JsonParseException, JsonMappingException, IOException {
    	String[] fields = {};
    	
    	Client client = ClientBuilder.newClient();
    	ObjectMapper objectMapper = new ObjectMapper();
    	Response response = client.target("http://localhost:9090/sambackend/fields").request().get();
    	int status = response.getStatus();
    	
    	if(status == 200){
    		String content = response.readEntity(String.class);
    		fields = objectMapper.readValue(content, String[].class);
    	} 
    	
    	return fields;
    }
    
    private List<UniversityBean> getUniversities(String country, String field) {
    	Client client = ClientBuilder.newClient();
    	Response response = client.target("http://localhost:8080/RestProject/webapi/UnivRessource").queryParam("country", country).queryParam("field", field).request().get();
    	int status = response.getStatus();
    	String content = response.readEntity(String.class);
    	try {
			List<UniversityBean> universities = new ObjectMapper().readValue(content, new TypeReference<List<UniversityBean>>(){});
			return universities;
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ArrayList<UniversityBean>();
		}	
    	
    }
    
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		String [] countries = this.getAllCountries();		
		String [] fields  = this.getAllFields();		
		
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
		
		List<UniversityBean> universities = this.getUniversities(country,field);
		
		String [] countries = this.getAllCountries();		
		String [] fields  = this.getAllFields();	
		
		request.setAttribute(Constants.CHAMP_CONTENT, CONTENT);
        request.setAttribute(Constants.CHAMP_TITLE, TITLE);
        request.setAttribute(Constants.CHAMP_PATH, PATH);
        request.setAttribute("countries", countries);
        request.setAttribute("fields", fields);
        request.setAttribute("universities", universities);
		this.getServletContext().getRequestDispatcher(Constants.VUE).forward(request,response);
	}
}