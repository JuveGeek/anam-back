package anam.pkg.duniyaar.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "meteoMedia")
@Table
public class MeteoMedia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String fileDownloadUri;
	
	public MeteoMedia(Long id, String fileDownloadUri) {
		super();
		this.id = id;
		this.fileDownloadUri = fileDownloadUri;
	}
	
	public MeteoMedia(String fileDownloadUri) {
		super();

		this.fileDownloadUri = fileDownloadUri;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MeteoMedia() {
		
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}
	
	
}
