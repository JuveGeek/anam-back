package anam.pkg.duniyaar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import anam.pkg.duniyaar.DTO.CrConseilMinistreLimitOne;
import anam.pkg.duniyaar.exception.CRConseilMinistreNotFindException;
import anam.pkg.duniyaar.model.CRConseilMinistre;
import anam.pkg.duniyaar.repo.CRConseilMinistreRepo;

@Service
public class CRConseilMinistreService {
	private final CRConseilMinistreRepo cRConseilMinistreRepo;
	
	@Autowired
	public CRConseilMinistreService(CRConseilMinistreRepo cRConseilMinistreRepo) {
		this.cRConseilMinistreRepo=cRConseilMinistreRepo;
	}
	
	public List<CrConseilMinistreLimitOne> getListCRConseilMinistreLimitOne(){
		
		return cRConseilMinistreRepo.ListCRConseilMinistreLimitOne((PageRequest.of(0, 1)));	
	}
	
	public CRConseilMinistre addCRConseilMinistre(CRConseilMinistre cRConseilMinistre) {
		
		return cRConseilMinistreRepo.save(cRConseilMinistre);
	}
	
	public CRConseilMinistre updateCRConseilMinistre(CRConseilMinistre cRConseilMinistre) {
		return cRConseilMinistreRepo.save(cRConseilMinistre);
	}
	
	public CRConseilMinistre findBulletinJourPById(Long id){
		return cRConseilMinistreRepo.findById(id)
				.orElseThrow(()->new CRConseilMinistreNotFindException("cRConseilMinistre by id"+id+"was not found"));
	}
	
	public void deleteCRConseilMinistre(Long id) {
		
		cRConseilMinistreRepo.deleteById(id);
	}

	public CRConseilMinistreRepo getCRConseilMinistreRepo() {
		return cRConseilMinistreRepo;
	}

	public List<CRConseilMinistre> findAllCRConseilMinistres() {
	
		return cRConseilMinistreRepo.findAll();
	}
}
