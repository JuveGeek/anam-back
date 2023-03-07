package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.DTO.BulletinBGPLimitOne;
import anam.pkg.duniyaar.exception.BulletinBGPNotFindException;
import anam.pkg.duniyaar.model.BulletinBGP;
import anam.pkg.duniyaar.repo.BulletinBGPRepo;

@Service
public class BulletinBGPService {
	private final BulletinBGPRepo bulletinBGPRepo;
	
	@Autowired
	public BulletinBGPService(BulletinBGPRepo bulletinBGPRepo) {
		this.bulletinBGPRepo=bulletinBGPRepo;
	}
	
	public List<BulletinBGPLimitOne> getListBulletinBGPLimitOne(){
		
		return bulletinBGPRepo.ListBulletinBGPLimitOne((PageRequest.of(0, 1)));	
	}
	
	public BulletinBGP addBulletinBGP(BulletinBGP bulletinBGP) {
		
		return bulletinBGPRepo.save(bulletinBGP);
	}
	
	public BulletinBGP updateBulletinBGP(BulletinBGP bulletinBGP) {
		return bulletinBGPRepo.save(bulletinBGP);
	}
	
	public BulletinBGP findBulletinBGPById(Long id){
		return bulletinBGPRepo.findById(id)
				.orElseThrow(()->new BulletinBGPNotFindException("BulletinBGP by id"+id+"was not found"));
	}
	
	public void deleteBulletinBGP(Long id) {
		
		bulletinBGPRepo.deleteById(id);
	}

	public BulletinBGPRepo getBulletinBGPRepo() {
		return bulletinBGPRepo;
	}

	public List<BulletinBGP> findAllBulletinBGPs() {
		
		return bulletinBGPRepo.findAll();
	}
}
