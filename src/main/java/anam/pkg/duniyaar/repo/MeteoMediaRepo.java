package anam.pkg.duniyaar.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import anam.pkg.duniyaar.DTO.MeteoMediaLimitOne;
import anam.pkg.duniyaar.model.MeteoMedia;

@Repository
public interface MeteoMediaRepo extends JpaRepositoryImplementation<MeteoMedia, Long> {
	@Query(value="SELECT new anam.pkg.duniyaar.DTO.MeteoMediaLimitOne(m.id, m.fileDownloadUri) from meteoMedia m ORDER BY m.id DESC")
	
	public List<MeteoMediaLimitOne> ListMeteoMediaLimitOne(Pageable pafeable);
}
