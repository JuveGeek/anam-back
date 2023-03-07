package anam.pkg.duniyaar.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import anam.pkg.duniyaar.DTO.BulletinBADLimitOne;
import anam.pkg.duniyaar.model.BulletinBAD;

@Repository
public interface BulletinBADRepo extends JpaRepositoryImplementation<BulletinBAD, Long> {
	
	@Query(value="SELECT new anam.pkg.duniyaar.DTO.BulletinBADLimitOne(b.id, b.titre, b.fileDownloadUri) from bulletinBAD b ORDER BY b.id DESC")
	
	public List<BulletinBADLimitOne> ListBulletinBADLimitOne(Pageable pafeable);
}
