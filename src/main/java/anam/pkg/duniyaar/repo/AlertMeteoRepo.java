package anam.pkg.duniyaar.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import anam.pkg.duniyaar.DTO.AlertMeteoLimitOne;
import anam.pkg.duniyaar.model.AlertMeteo;

@Repository
public interface AlertMeteoRepo extends JpaRepositoryImplementation<AlertMeteo, Long> {
	@Query(value="SELECT new anam.pkg.duniyaar.DTO.AlertMeteoLimitOne(a.id, a.titre) from alertMeteo a ORDER BY a.id DESC")
	
	public List<AlertMeteoLimitOne> ListAlertMeteoLimitOne(Pageable pafeable);
}
