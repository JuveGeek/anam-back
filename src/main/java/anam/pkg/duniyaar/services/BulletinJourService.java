package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.DTO.BulletinJourLimitOne;
import anam.pkg.duniyaar.exception.BulletinJourNotFindException;
import anam.pkg.duniyaar.model.BulletinJour;
import anam.pkg.duniyaar.repo.BulletinJourRepo;

@Service
public class BulletinJourService {
	private final BulletinJourRepo bulletinJourRepo;
	
	@Autowired
	public BulletinJourService(BulletinJourRepo bulletinJourRepo) {
		this.bulletinJourRepo=bulletinJourRepo;
	}
	
	public List<BulletinJourLimitOne> getListBulletinJourLimitOne(){
		
		return bulletinJourRepo.ListBulletinJourLimitOne((PageRequest.of(0, 1)));	
	}
	
	public BulletinJour addBulletinJour(BulletinJour bulletinJour) {
		
		return bulletinJourRepo.save(bulletinJour);
	}
	
	public BulletinJour updateBulletinJour(BulletinJour bulletinJour) {
		return bulletinJourRepo.save(bulletinJour);
	}
	
	public BulletinJour findBulletinJourPById(Long id){
		return bulletinJourRepo.findById(id)
				.orElseThrow(()->new BulletinJourNotFindException("BulletinJour by id"+id+"was not found"));
	}
	
	public void deleteBulletinJour(Long id) {
		
		bulletinJourRepo.deleteById(id);
	}

	public BulletinJourRepo getBulletinJourRepo() {
		return bulletinJourRepo;
	}

	public List<BulletinJour> findAllAlertMeteos() {
		return bulletinJourRepo.findAll();
	}
}
