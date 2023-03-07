package anam.pkg.duniyaar.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import anam.pkg.duniyaar.DTO.BulletinBGPLimitOne;
import anam.pkg.duniyaar.model.BulletinBGP;

@Repository
public interface BulletinBGPRepo extends JpaRepositoryImplementation<BulletinBGP, Long>{
	@Query(value="SELECT new anam.pkg.duniyaar.DTO.BulletinBGPLimitOne(b.id, b.titre, b.fileDownloadUri) from bulletinBGP b ORDER BY b.id DESC")
	
	public List<BulletinBGPLimitOne> ListBulletinBGPLimitOne(Pageable pafeable);
}
