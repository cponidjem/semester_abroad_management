package beans;

public class Client {
	private String login;
	private String password;
	private Role role;
	private String token;
	
	public Client(String login, String password, Role role) {
		this.login = login;
		this.password = password;
		this.role = role;
	}
	
	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}
	public Role getRole() {
		return role;
	}
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
