package anam.pkg.duniyaar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "rSahel")
@Table
public class RSahel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFileDownloadUri() {
		return fileDownloadUri;
	}
	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}
	
	public RSahel(Long id, String titre, String description, String fileDownloadUri) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.fileDownloadUri=fileDownloadUri;
	}
	
	public RSahel() {
		super();
	}

	public RSahel(String titre, String description, String fileDownloadUri) {
		super();
		
		this.titre = titre;
		this.description = description;
		this.fileDownloadUri=fileDownloadUri;
	}
	
	
	
}
