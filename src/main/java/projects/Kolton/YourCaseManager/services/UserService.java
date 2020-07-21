package projects.Kolton.YourCaseManager.services;

import java.text.DecimalFormat;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import projects.Kolton.YourCaseManager.models.Address;
import projects.Kolton.YourCaseManager.models.Login;
import projects.Kolton.YourCaseManager.models.User;
import projects.Kolton.YourCaseManager.view.MainMenu;

import javax.sql.DataSource;



public class UserService {
	private String BASE_URL = "http://localhost:8080";
	RestTemplate template;
	private DecimalFormat decimalFormat = new DecimalFormat("##.00");
	private MainMenu mainMenu;
	//Constructor
	public UserService(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
		template = new RestTemplate();
		
	}
	//Helper Methods
	public HttpEntity<User> makeUserEntity(User user){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<User> entity = new HttpEntity<User>(user, headers);
		return entity;
	}
	
	public HttpEntity<Login> makeLoginEntity(Login login){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Login> entity = new HttpEntity<Login>(login, headers);
		return entity;
	}
	
	
	
	
	//--------------------Start UserService methods--------------
	public boolean validateUsername(String username) {
		return template.getForObject(BASE_URL + "/validate/" + username, Boolean.class);
	}
	
	public boolean validateLogin(Login login) {
		HttpEntity<Login> entity = this.makeLoginEntity(login);
		boolean response = template.postForEntity(BASE_URL + "/validate", entity, Boolean.class).getBody().booleanValue();
		return response;
	}
	
	public void createUser(Login login) {
		HttpEntity<Login> entity = this.makeLoginEntity(login);
		template.postForObject(BASE_URL + "/users", entity, User.class);
	}
	
	public User getUserInfo(String username) {
		return template.getForObject(BASE_URL + "/users/" + username, User.class);
	}
	
	
	public void updateUserAddress(Address address, User user) {
		//TODO
	}

	
	public void updateUserInfo(User user) {
		HttpEntity<User> entity = this.makeUserEntity(user);
		template.put(BASE_URL + "/users/", entity);
		
	}

}
