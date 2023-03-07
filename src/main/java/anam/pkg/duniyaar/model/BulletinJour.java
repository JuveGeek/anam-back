package anam.pkg.duniyaar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "bulletinJour")
@Table
public class BulletinJour {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(nullable=false, updatable=false)
	private Long id;
    
	private String titre;
	
	private String description;
   
	private String fileDownloadUri;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	
	public BulletinJour() {
		
	}
	
    public BulletinJour(String titre, String description, String fileDownloadUri) {
		super();
		
		this.titre = titre;
		this.description=description;
		this.fileDownloadUri=fileDownloadUri;
	}
	
	public BulletinJour(Long id, String titre, String description, String fileDownloadUri) {
		super();
		this.id = id;
		this.titre = titre;
		this.description=description;
		this.fileDownloadUri = fileDownloadUri;
		
	}
	public String getFileDownloadUri() {
		return fileDownloadUri;
	}
	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
 
}
