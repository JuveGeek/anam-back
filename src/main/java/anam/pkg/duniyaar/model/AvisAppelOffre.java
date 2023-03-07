package anam.pkg.duniyaar.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "avisAppelOffre")
public class AvisAppelOffre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String titre;
	private String description;
	private Date dateSoumission;
	private Date dateLimite;
	private String structure;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public AvisAppelOffre() {
		super();
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

	public Date getDateSoumission() {
		return dateSoumission;
	}

	public void setDateSoumission(Date dateSoumission) {
		this.dateSoumission = dateSoumission;
	}

	public Date getDateLimite() {
		return dateLimite;
	}

	public void setDateLimite(Date dateLimite) {
		this.dateLimite = dateLimite;
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}
	
	public AvisAppelOffre(Long id, String titre, String description, Date dateSoumission, Date dateLimite,
			String structure) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.dateSoumission = dateSoumission;
		this.dateLimite = dateLimite;
		this.structure = structure;
	}
	
}
