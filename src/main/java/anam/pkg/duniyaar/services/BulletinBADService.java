package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.DTO.BulletinBADLimitOne;
import anam.pkg.duniyaar.exception.BulletinBADNotFindException;
import anam.pkg.duniyaar.model.BulletinBAD;
import anam.pkg.duniyaar.repo.BulletinBADRepo;

@Service
public class BulletinBADService {
	private final BulletinBADRepo bulletinBADRepo;
	
	@Autowired
	public BulletinBADService(BulletinBADRepo bulletinBADRepo) {
		this.bulletinBADRepo=bulletinBADRepo;
	}
	
	public List<BulletinBADLimitOne> getListBulletinBADLimitOne(){
		
		return bulletinBADRepo.ListBulletinBADLimitOne((PageRequest.of(0, 1)));	
	}
	
	public BulletinBAD addBulletinBAD(BulletinBAD bulletinBAD) {
		
		return bulletinBADRepo.save(bulletinBAD);
	}
	
	public BulletinBAD updateBulletinBAD(BulletinBAD bulletinBAD) {
		return bulletinBADRepo.save(bulletinBAD);
	}
	
	public BulletinBAD findBulletinBADById(Long id){
		return bulletinBADRepo.findById(id)
				.orElseThrow(()->new BulletinBADNotFindException("BulletinBAD by id"+id+"was not found"));
	}
	
	public void deleteBulletinBAD(Long id) {
		
		bulletinBADRepo.deleteById(id);
	}

	public BulletinBADRepo getBulletinBADRepo() {
		return bulletinBADRepo;
	}

	public List<BulletinBAD> findAllBulletinBAD() {
		return bulletinBADRepo.findAll();
	}
}
