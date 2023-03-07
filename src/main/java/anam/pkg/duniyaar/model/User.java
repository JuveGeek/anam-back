package anam.pkg.duniyaar.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
     
    @Column(nullable = false, unique = true, length = 40)  
    private String email;
     
    @Column(nullable = false, length = 10)
    private String password;
 
    public User() {}
     
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
 
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
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

  
 
    // getters and setters are not shown for brevity   
     
}