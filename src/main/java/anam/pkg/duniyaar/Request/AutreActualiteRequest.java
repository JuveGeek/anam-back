package anam.pkg.duniyaar.Request;

public class AutreActualiteRequest {
	private String titre;
	private String description;
	
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public AutreActualiteRequest(String titre, String description) {
		super();
		this.titre = titre;
		this.description = description;
	}
	
	public AutreActualiteRequest() {
		super();
		
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
