package anam.pkg.duniyaar.Response;

public class AuthResponse {
	String message;
	Integer userId;
	String accessToken;
	String refreshToken;
	public AuthResponse(String message, Integer userId, String accessToken, String refreshToken) {
		super();
		this.message = message;
		this.userId = userId;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public AuthResponse() {
		super();
	}

}
