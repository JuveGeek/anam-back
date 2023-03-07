package anam.pkg.duniyaar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "autreActualite")
@Table
public class AutreActualite {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String titre;
	
	@Column(length=600)
	private String description;
	public AutreActualite(String titre, String description, String fileDownloadUri) {
		super();
		this.titre = titre;
		this.description = description;
		this.fileDownloadUri = fileDownloadUri;
	}

	private String fileDownloadUri;
	
	public AutreActualite(Long id, String titre, String description, String fileDownloadUri) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.fileDownloadUri=fileDownloadUri;
	}
	
	public AutreActualite() {
		super();

	}
	
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
	

}
