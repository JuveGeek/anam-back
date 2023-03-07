package anam.pkg.duniyaar.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import anam.pkg.duniyaar.DTO.BulletinJourLimitOne;
import anam.pkg.duniyaar.model.BulletinJour;

@Repository
public interface BulletinJourRepo extends JpaRepositoryImplementation<BulletinJour, Long> {
	@Query(value="SELECT new anam.pkg.duniyaar.DTO.BulletinJourLimitOne(b.id, b.titre, b.description, b.fileDownloadUri) from bulletinJour b ORDER BY b.id DESC")
	
	public List<BulletinJourLimitOne> ListBulletinJourLimitOne(Pageable pafeable);
}
