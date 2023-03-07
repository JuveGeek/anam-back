package anam.pkg.duniyaar.Request;

public class UserRequest {
	
	String email;
	String password;
	
	public String getEmail() {
		return email;
	}

	public UserRequest() {
		super();
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//String userName;
	
	
	public UserRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

}
