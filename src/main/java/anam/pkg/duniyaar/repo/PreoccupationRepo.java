package anam.pkg.duniyaar.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import anam.pkg.duniyaar.DTO.PreoccupationLimitThree;
import anam.pkg.duniyaar.model.Preoccupation;

@Repository
public interface PreoccupationRepo extends JpaRepositoryImplementation<Preoccupation, Long> {

	@Query(value="SELECT new anam.pkg.duniyaar.DTO.PreoccupationLimitThree(p.id, p.email, p.question) from preoccupation p ORDER BY p.id DESC")
	
	public List<PreoccupationLimitThree> ListPreoccupationLimitThree(Pageable pafeable);
	
	
}
