package anam.pkg.duniyaar.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import anam.pkg.duniyaar.DTO.BulletinHebdoLimitOne;
import anam.pkg.duniyaar.model.BulletinHebdo;

@Repository
public interface BulletinHebdoRepo extends JpaRepositoryImplementation<BulletinHebdo, Long> {
	@Query(value="SELECT new anam.pkg.duniyaar.DTO.BulletinHebdoLimitOne(b.id, b.titre, b.fileDownloadUri) from bulletinHebdo b ORDER BY b.id DESC")
	
	public List<BulletinHebdoLimitOne> ListBulletinHebdoLimitOne(Pageable pafeable);
}
