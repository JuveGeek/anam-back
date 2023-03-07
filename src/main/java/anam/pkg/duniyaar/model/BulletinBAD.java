package anam.pkg.duniyaar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "bulletinBAD")
@Table
public class BulletinBAD {
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
	
	
    public BulletinBAD(Long id, String titre, String fileDownloadUri) {
		super();
		this.id = id;
		this.titre = titre;
		this.setFileDownloadUri(fileDownloadUri);
	}
    
    public BulletinBAD() {
		super();
		
	}
	public BulletinBAD(String titre, String fileDownloadUri) {
		this.titre = titre;
		this.setFileDownloadUri(fileDownloadUri);
	}
	public String getFileDownloadUri() {
		return fileDownloadUri;
	}
	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}
}
