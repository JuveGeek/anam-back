package anam.pkg.duniyaar.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import anam.pkg.duniyaar.DTO.CrConseilMinistreLimitOne;
import anam.pkg.duniyaar.model.CRConseilMinistre;

@Repository
public interface CRConseilMinistreRepo extends JpaRepositoryImplementation<CRConseilMinistre, Long> {
	@Query(value="SELECT new anam.pkg.duniyaar.DTO.CrConseilMinistreLimitOne(c.id, c.titre, c.description,c.fileDownloadUri) from crConseilMinistre c ORDER BY c.id DESC")
	
	public List<CrConseilMinistreLimitOne> ListCRConseilMinistreLimitOne(Pageable pafeable);
	
}
