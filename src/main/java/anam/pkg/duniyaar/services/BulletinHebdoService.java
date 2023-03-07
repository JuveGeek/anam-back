package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.DTO.BulletinHebdoLimitOne;
import anam.pkg.duniyaar.exception.BulletinHebdoNotFindException;
import anam.pkg.duniyaar.model.BulletinHebdo;
import anam.pkg.duniyaar.repo.BulletinHebdoRepo;

@Service
public class BulletinHebdoService {
private final BulletinHebdoRepo bulletinHebdoRepo;
	
	@Autowired
	public BulletinHebdoService(BulletinHebdoRepo bulletinHebdoRepo) {
		this.bulletinHebdoRepo=bulletinHebdoRepo;
	}
	
	public List<BulletinHebdoLimitOne> getListBulletinHebdoLimitOne(){
		
		return bulletinHebdoRepo.ListBulletinHebdoLimitOne((PageRequest.of(0, 1)));	
	}
	
	public BulletinHebdo addBulletinHebdo(BulletinHebdo bulletinHebdo) {
		
		return bulletinHebdoRepo.save(bulletinHebdo);
	}
	
	public BulletinHebdo updateBulletinHebdo(BulletinHebdo bulletinHebdo) {
		return bulletinHebdoRepo.save(bulletinHebdo);
	}
	
	public BulletinHebdo findBulletinHebdoById(Long id){
		return bulletinHebdoRepo.findById(id)
				.orElseThrow(()->new BulletinHebdoNotFindException("BulletinHebdo by id"+id+"was not found"));
	}
	
	public void deleteBulletinHebdo(Long id) {
		
		bulletinHebdoRepo.deleteById(id);
	}

	public BulletinHebdoRepo getBulletinHebdoRepo() {
		return bulletinHebdoRepo;
	}

	public List<BulletinHebdo> findAllBulletinHebdos() {
		return bulletinHebdoRepo.findAll();
	}
}
