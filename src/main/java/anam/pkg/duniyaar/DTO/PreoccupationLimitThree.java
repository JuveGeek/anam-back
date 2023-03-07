package anam.pkg.duniyaar.DTO;

public class PreoccupationLimitThree {

	private Long id;
	private String email;
	private String question;
	
	public PreoccupationLimitThree() {
		super();
		
	}
	
	public PreoccupationLimitThree(Long id, String email, String question) {
		super();
		this.id = id;
		this.email = email;
		this.question = question;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
    
}
