package anam.pkg.duniyaar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "bulletinHebdo")
@Table
public class BulletinHebdo {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(nullable=false, updatable=false)
	private Long id;
    
	private String titre;
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
	public String getFileDownloadUri() {
		return fileDownloadUri;
	}
	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}
	
    public BulletinHebdo(Long id, String titre, String fileDownloadUri) {
		super();
		this.id = id;
		this.titre = titre;
		this.fileDownloadUri = fileDownloadUri;
	}
    public BulletinHebdo(String titre, String fileDownloadUri) {
		super();
		
		this.titre = titre;		this.fileDownloadUri = fileDownloadUri;
	}
    
    public BulletinHebdo() {
    	
    }
 
}
