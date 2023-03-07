package anam.pkg.duniyaar.DTO;

public class MeteoMediaLimitOne {
	private Long id;
	private String fileDownloadUri;
	
	public MeteoMediaLimitOne(Long id, String fileDownloadUri) {
		super();
		this.id = id;
		this.fileDownloadUri = fileDownloadUri;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MeteoMediaLimitOne() {
		
	}

	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

}
