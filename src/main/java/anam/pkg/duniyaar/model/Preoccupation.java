package anam.pkg.duniyaar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "preoccupation")
@Table
public class Preoccupation {
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	
	private String question;

	public void setEmail(String email) {
		this.email = email;
	}

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(nullable=false, updatable=false)
	private Long id;
    
	public Preoccupation(Long id, String email, String question) {
		super();
		this.id = id;
		this.email = email;
		this.question = question;
	}

	public Preoccupation() {
		super();
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	private String email;
   
	
}
