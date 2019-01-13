package beans;

public class Client {
	private String login;
	private String password;
	private Role authority;
	
	public Client(String login, String password, Role role) {
		this.login = login;
		this.password = password;
		this.authority = role;
	}
	
	public String getLogin() {
		return login;
	}
	public String getPassword() {
		return password;
	}
	public Role getAuthority() {
		return authority;
	}
	
	
}
