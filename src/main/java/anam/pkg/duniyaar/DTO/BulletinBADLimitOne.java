package anam.pkg.duniyaar.DTO;

public class BulletinBADLimitOne {
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
	
	
    public BulletinBADLimitOne(Long id, String titre, String fileDownloadUri) {
		super();
		this.id = id;
		this.titre = titre;
		this.setFileDownloadUri(fileDownloadUri);
	}
    
    public BulletinBADLimitOne() {
		super();
		
	}
	
	public String getFileDownloadUri() {
		return fileDownloadUri;
	}
	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}
}
