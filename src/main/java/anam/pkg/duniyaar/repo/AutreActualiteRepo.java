package anam.pkg.duniyaar.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import anam.pkg.duniyaar.DTO.AutreActualiteLimitThree;
import anam.pkg.duniyaar.model.AutreActualite;

@Repository		
public interface AutreActualiteRepo extends JpaRepositoryImplementation<AutreActualite, Long> {

@Query(value="SELECT new anam.pkg.duniyaar.DTO.AutreActualiteLimitThree(a.id, a.titre, a.description, a.fileDownloadUri) from autreActualite a ORDER BY a.id DESC")
	
	public List<AutreActualiteLimitThree> ListAutreActualiteLimitThree(Pageable pafeable);
}
