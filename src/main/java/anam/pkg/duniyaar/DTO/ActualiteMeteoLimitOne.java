package anam.pkg.duniyaar.DTO;

public class ActualiteMeteoLimitOne {
	private Long id;
	private String titre;
	private String description;
	private String fileDownloadUri;
	
	public ActualiteMeteoLimitOne(Long id, String titre, String description, String fileDownloadUri) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.fileDownloadUri=fileDownloadUri;
	}
	
	public ActualiteMeteoLimitOne() {
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
