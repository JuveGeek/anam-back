package anam.pkg.duniyaar.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import anam.pkg.duniyaar.DTO.ActualiteMeteoLimitOne;

import anam.pkg.duniyaar.model.ActualiteMeteo;

@Repository
public interface ActualiteMeteoRepo extends JpaRepositoryImplementation<ActualiteMeteo, Long>{

	@Query(value="SELECT new anam.pkg.duniyaar.DTO.ActualiteMeteoLimitOne(a.id, a.titre, a.description,a.fileDownloadUri) from actualiteMeteo a ORDER BY a.id DESC")
	
	public List<ActualiteMeteoLimitOne> ListActualiteMeteoLimitOne(Pageable pafeable);
	
}
